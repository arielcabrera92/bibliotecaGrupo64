/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Clases.*;
import AccesoDatos.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
        

/**
 *
 * @author ariel
 */
public class LectorData {
    private Connection con =null;
    
    public LectorData(){
    con=Conexion.getConexion();
    }
    
    public void crearLector(Lector lector){
        String sql="INSERT INTO lector (nombre,dni,domicilio,telefono,estado,mail)" + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, lector.getNombre());
            ps.setString(2, lector.getDni());
            ps.setString(3, lector.getDomicilio());
            ps.setString(4, lector.getTelefono());
            ps.setBoolean(5, lector.isEstado());
            ps.setString(6, lector.getMail());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                lector.setIdLector(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Lector guardado");
            }
            //int filaNueva = ps.executeUpdate();
            //if (filaNueva > 0) {
                //JOptionPane.showMessageDialog(null, "Lector guardado");
            //} else {
                //JOptionPane.showMessageDialog(null, "No se pudo añadir al lector");
            //}
            ps.close();
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Lector "+ex.getMessage());      
        }
    }
    public void modificarLector(Lector lector){
        String sql = "UPDATE lector SET nombre = ?, dni = ?, domicilio = ?, telefono = ?, estado = ?, mail = ?" + " WHERE idLector = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lector.getNombre());
            ps.setString(2, lector.getDni());
            ps.setString(3, lector.getDomicilio());
            ps.setString(4, lector.getTelefono());
            ps.setBoolean(5, lector.isEstado());
            ps.setString(6,lector.getMail());
            ps.setInt(7, lector.getIdLector());
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Datos del socio actualizados exitosamente");
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lector " + ex.getMessage());
        }
    }
    
    public Lector buscarLectorPorDni(String dni) {
        Lector lector = null;
        String sql = "SELECT * FROM lector WHERE dni=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lector = new Lector();
                lector.setIdLector(rs.getInt("idLector"));
                lector.setNombre(rs.getString("nombre"));
                lector.setDni(rs.getString("dni"));
                lector.setDomicilio(rs.getString("domicilio"));
                lector.setTelefono(rs.getString("telefono"));
                lector.setEstado(rs.getBoolean("estado"));
                lector.setMail(rs.getString("mail"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el socio");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lector "+ex.getMessage());
        }
        return lector;
    }
    
        public Lector buscarLectorPorId(int id) {
        Lector lector = null;
        String sql = "SELECT * FROM lector WHERE idLector=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lector = new Lector();
                lector.setIdLector(rs.getInt("idLector"));
                lector.setNombre(rs.getString("nombre"));
                lector.setDni(rs.getString("dni"));
                lector.setDomicilio(rs.getString("domicilio"));
                lector.setTelefono(rs.getString("telefono"));
                lector.setEstado(rs.getBoolean("estado"));
                lector.setMail(rs.getString("mail"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el socio");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lector "+ex.getMessage());
        }
        return lector;
    }
        
    public List<Lector> listarLectores() {
        List<Lector> lectores = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lector WHERE estado = 1 ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lector lector = new Lector();
                lector.setIdLector(rs.getInt("idLector"));
                lector.setNombre(rs.getString("nombre"));
                lector.setDni(rs.getString("dni"));
                lector.setDomicilio(rs.getString("domicilio"));
                lector.setTelefono(rs.getString("telefono"));
                lector.setEstado(true);
                lector.setMail(rs.getString("mail"));
                lectores.add(lector);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla libros "+ex.getMessage());
        }
        return lectores;
    }
    
        public void desactivarLector(int id) {
        try {
            String sql = "UPDATE lector SET estado = 0 WHERE idLector = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila=ps.executeUpdate();
            if(fila==1){
                JOptionPane.showMessageDialog(null, "Se dio de baja al socio");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Lector" + ex.getMessage());
        }
    }
    
    public void activarLector(int id) {
        try {
            String sql = "UPDATE lector SET estado = 1 WHERE idLector = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila=ps.executeUpdate();
            if(fila==1){
                JOptionPane.showMessageDialog(null, "Se dio de alta al socio");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lector" + ex.getMessage() );
        }
    }
}
