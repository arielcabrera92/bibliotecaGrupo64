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
import Clases.Prestamos;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author ariel
 */
public class PrestamoData {
    
    private Connection con =null;
    private LectorData ld = new LectorData(); 
    private Lector lector = new Lector(); 
    private EjemplarData ed = new EjemplarData(); 
    private Ejemplar ejemplar = new Ejemplar(); 
    
    public PrestamoData(){
        con=Conexion.getConexion();
    }
    
    public void solicitarLibro(Lector lector, Ejemplar ejemplar) {
        Prestamos prestamo = new Prestamos();
          
            // Comprobar si hay ejemplares disponibles
        try{
            if (ejemplar.getCantidad() > 0) {
                // Actualizar la cantidad de ejemplares y el estado

                    String updateSql = "UPDATE Ejemplar SET cantidad = cantidad - 1 WHERE idEjemplar = ?";
                    PreparedStatement updateStatement = con.prepareStatement(updateSql);
                    updateStatement.setInt(1, ejemplar.getIdEjemplar());
                    int rowsUpdated = updateStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                // Actualizar el estado del ejemplar en la base de datos si la cantidad llega a 0
                        if (ejemplar.getCantidad() == 1) {
                            String updateStateSql = "UPDATE Ejemplar SET estado = 'PRESTADO' WHERE idEjemplar = ?";
                            PreparedStatement updateStateStatement = con.prepareStatement(updateStateSql);
                            updateStateStatement.setInt(1, ejemplar.getIdEjemplar());
                            updateStateStatement.executeUpdate();
                        }

                // Actualizar el estado en el objeto Ejemplar
                        ejemplar.setCantidad(ejemplar.getCantidad() - 1);
            
                // Crear un registro de préstamo
                        String sql = "INSERT INTO Prestamo (fechaInicio, fechaFin, idLibro, dni, estado) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement ps = con.prepareStatement(sql, org.mariadb.jdbc.Statement.RETURN_GENERATED_KEYS);
                        ps.setDate(1, java.sql.Date.valueOf(prestamo.getFechaInicio()));
                        ps.setDate(2, java.sql.Date.valueOf(prestamo.getFechaFin()));
                        ps.setInt(3, ejemplar.getLibro().getIdLibro());
                        ps.setString(4, lector.getDni());
                        ps.setBoolean(5, true); // Estado de préstamo
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
    
    public void devolverLibro(Lector lector, Ejemplar ejemplar) {
    try {
        // Buscar un préstamo activo para el lector y el ejemplar
        String searchLoanSql = "SELECT idPrestamo FROM prestamo WHERE dni = ? AND idLibro = ? AND estado = true";
        PreparedStatement searchLoanStatement = con.prepareStatement(searchLoanSql);
        searchLoanStatement.setString(1, lector.getDni());
        searchLoanStatement.setInt(2, ejemplar.getLibro().getIdLibro());
        ResultSet loanResultSet = searchLoanStatement.executeQuery();

        if (loanResultSet.next()) {
            int prestamoId = loanResultSet.getInt("idPrestamo");

            // Actualizar el estado de préstamo en la base de datos a false
            String updateLoanSql = "UPDATE prestamo SET estado = false WHERE idPrestamo = ?";
            PreparedStatement updateLoanStatement = con.prepareStatement(updateLoanSql);
            updateLoanStatement.setInt(1, prestamoId);
            int rowsUpdatedLoan = updateLoanStatement.executeUpdate();

            if (rowsUpdatedLoan > 0) {
                // Aumentar la cantidad de ejemplares en la base de datos
                String updateQuantitySql = "UPDATE ejemplar SET cantidad = cantidad + 1 WHERE idEjemplar = ?";
                PreparedStatement updateQuantityStatement = con.prepareStatement(updateQuantitySql);
                updateQuantityStatement.setInt(1, ejemplar.getIdEjemplar());
                int rowsUpdatedQuantity = updateQuantityStatement.executeUpdate();

                // Actualizar el estado del ejemplar si la cantidad llega a 0
                if (rowsUpdatedQuantity > 0 && ejemplar.getCantidad() == 0) {
                    String updateStateSql = "UPDATE ejemplar SET estado = 'DISPONIBLE' WHERE idEjemplar = ?";
                    PreparedStatement updateStateStatement = con.prepareStatement(updateStateSql);
                    updateStateStatement.setInt(1, ejemplar.getIdEjemplar());
                    updateStateStatement.executeUpdate();
                }

                // Actualizar el estado en el objeto Ejemplar
                ejemplar.setCantidad(ejemplar.getCantidad() + 1);
                JOptionPane.showMessageDialog(null, "Devolución realizada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un préstamo activo para el lector y el ejemplar.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al devolver el libro: " + ex.getMessage());
    }
}
    
}
