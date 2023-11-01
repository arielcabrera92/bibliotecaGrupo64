/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;
import Clases.Ejemplar;
import Clases.EstadoEjemplar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import Clases.Lector;
import Clases.Libro;
import Clases.Prestamos;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author ariel
 */
public class PrestamoData {
    
    private Connection con =null;
    private LectorData lectorData = new LectorData(); 
    private Lector lector = new Lector(); 
    private LibroData libroData = new LibroData(); 
    private Libro libro = new Libro(); 
    
    public PrestamoData(){
        con=Conexion.getConexion();
    }
    
    public void solicitarLibro(Lector lector, Libro libro) {
        Prestamos prestamo = new Prestamos();
          
            // Comprobar si hay ejemplares disponibles
        try{
            if (libro.getEjemplares() > 0) {
                // Actualizar la cantidad de ejemplares y el estado

                    String updateSql = "UPDATE libros SET ejemplares = ejemplares - 1 WHERE idLibro = ?";
                    PreparedStatement updateStatement = con.prepareStatement(updateSql);
                    updateStatement.setInt(1, libro.getIdLibro());
                    int filasModificadas = updateStatement.executeUpdate();

                    if (filasModificadas > 0) {
                // Actualizar la disponibilidad del libro en la base de datos si la cantidad llega a 0
                        if (libro.getEjemplares() == 1) {
                            String updateStateSql = "UPDATE libros SET disponibilidad = 'PRESTADO' WHERE idLibro = ?";
                            PreparedStatement us = con.prepareStatement(updateStateSql);
                            us.setInt(1, libro.getIdLibro());
                            us.executeUpdate();
                        }

                // Actualizar la cantidad de ejemplares en Libro
                        libro.setEjemplares(libro.getEjemplares() - 1);
            
                // Crear un registro de préstamo
                        String sql = "INSERT INTO prestamo (fechaInicio, fechaFin, idLibro, dni, estado) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement ps = con.prepareStatement(sql, org.mariadb.jdbc.Statement.RETURN_GENERATED_KEYS);
                        ps.setDate(1, java.sql.Date.valueOf(prestamo.getFechaInicio()));
                        ps.setDate(2, java.sql.Date.valueOf(prestamo.getFechaFin()));
                        ps.setInt(3, libro.getIdLibro());
                        ps.setString(4, lector.getDni());
                        ps.setBoolean(5, true); 
                        int filas = ps.executeUpdate();
                        ResultSet rs = ps.getGeneratedKeys();
                        if (rs.next()){
                            prestamo.setIdPrestamo(rs.getInt(1));
                            JOptionPane.showMessageDialog(null, "Préstamo realizado con éxito");
                        }
                    }
            }else {
                JOptionPane.showMessageDialog(null, "Todos los ejemplares están prestados");
            } 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Préstamos" + ex.getMessage());
        }
    }
    
    public void devolverLibro(Lector lector, Libro libro) {
        try {
        // Buscar un préstamo activo para el lector y el ejemplar
            String sql = "SELECT idPrestamo FROM prestamo WHERE dni = ? AND idLibro = ? AND estado = 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lector.getDni());
            ps.setInt(2, libro.getIdLibro());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int prestamoId = rs.getInt("idPrestamo");

            // Actualizar el estado de préstamo en la base de datos a false
                String sql2 = "UPDATE prestamo SET estado = 0 WHERE idPrestamo = ?";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setInt(1, prestamoId);
                int filasModificadas = ps2.executeUpdate();

                if (filasModificadas > 0) {
                // Aumentar la cantidad de ejemplares en la base de datos
                    String sql3 = "UPDATE libros SET ejemplares = ejemplares + 1 WHERE idLibro = ?";
                    PreparedStatement ps3 = con.prepareStatement(sql3);
                    ps3.setInt(1, libro.getIdLibro());
                    int filasModificadasCantidad = ps3.executeUpdate();

                // Actualizar la disponibilidad del libro si la cantidad llega a 0
                    if (filasModificadasCantidad > 0 && libro.getEjemplares() == 0) {
                        String sql4 = "UPDATE libros SET disponibilidad = 'DISPONIBLE' WHERE idLibro = ?";
                        PreparedStatement ps4 = con.prepareStatement(sql4);
                        ps4.setInt(1, libro.getIdLibro());
                        ps4.executeUpdate();
                    }

                // Actualizar la disponibilidad en el objeto Libro
                    libro.setEjemplares(libro.getEjemplares() + 1);
                    JOptionPane.showMessageDialog(null, "Devolución realizada");
                }
            } else {
            JOptionPane.showMessageDialog(null, "No se encontró un préstamo activo para el socio ni el libro");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al devolver el libro: " + ex.getMessage());
        }
    }
    
    public Prestamos buscarPrestamoPorId (int id){
        Prestamos prestamo = null; 
        String sql = "SELECT * FROM prestamo WHERE idPrestamo = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                prestamo = new Prestamos();
                prestamo.setIdPrestamo(id);
                prestamo.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                prestamo.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                prestamo.setIdLibro(rs.getInt("idLibro"));
                prestamo.setDni(rs.getString("dni"));
                prestamo.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el préstamo");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Préstamos "+ex.getMessage());
        }
        return prestamo;
        
    }
    public Prestamos buscarPrestamoReciente (int idLibro, String dniLector){
        Prestamos prestamos = null;
        String sql = "Select * FROM prestamo WHERE idLibro = ? AND dni = ?";
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLibro);
            ps.setString(2, dniLector);
            ResultSet rs = ps.executeQuery(); 
            if (rs.next()){
                prestamos = new Prestamos(); 
                prestamos.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamos.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                prestamos.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                prestamos.setIdLibro(idLibro);
                prestamos.setDni(rs.getString(dniLector));
                prestamos.setEstado(rs.getBoolean("estado"));
            }else {
                JOptionPane.showMessageDialog(null, "No se encontró el préstamo");
            }
            ps.close();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Préstamos "+ex.getMessage());
        }
        return prestamos;
    }
    
    public List<Prestamos> listarPrestamosActivos() {
        List<Prestamos> prestamos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM prestamo WHERE estado = 1 ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prestamos prestamo = new Prestamos();

                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                prestamo.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                prestamo.setIdLibro(rs.getInt("idLibro"));
                prestamo.setDni(rs.getString("dni"));
                prestamo.setEstado(rs.getBoolean("estado"));
                prestamos.add(prestamo);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla libros "+ex.getMessage());
        }
        return prestamos;
    }

}
