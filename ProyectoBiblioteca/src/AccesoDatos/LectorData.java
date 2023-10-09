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
            ps.setInt(2, lector.getDni());
            ps.setString(3, lector.getDomicilio());
            ps.setObject(4, lector.getTelefono(), java.sql.Types.BIGINT);
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
            ps.setInt(2, lector.getDni());
            ps.setString(3, lector.getDomicilio());
            ps.setObject(4, lector.getTelefono());
            ps.setBoolean(5, lector.isEstado());
            ps.setString(6,lector.getMail());
            ps.setInt(7, lector.getIdLector());
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Lector actualizado exitosamente");
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lector " + ex.getMessage());
        }
    }
    
    public Lector buscarLectorPorDni(int dni) {
        Lector lector = null;
        String sql = "SELECT * FROM lector WHERE dni=? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lector = new Lector();
                lector.setIdLector(rs.getInt("idLector"));
                lector.setNombre(rs.getString("nombre"));
                lector.setDni(rs.getInt("dni"));
                lector.setDomicilio(rs.getString("domicilio"));
                BigDecimal telefonoDecimal = rs.getBigDecimal("telefono");
                BigInteger telefonoInteger = telefonoDecimal.toBigInteger();
                lector.setTelefono(telefonoInteger);
                lector.setEstado(true);
                lector.setMail(rs.getString("mail"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el lector");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lector "+ex.getMessage());
        }
        return lector;
    }
    
        public Lector buscarLectorPorId(int id) {
        Lector lector = null;
        String sql = "SELECT * FROM lector WHERE idLector=? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lector = new Lector();
                lector.setIdLector(rs.getInt("idLector"));
                lector.setNombre(rs.getString("nombre"));
                lector.setDni(rs.getInt("dni"));
                lector.setDomicilio(rs.getString("domicilio"));
                BigDecimal telefonoDecimal = rs.getBigDecimal("telefono");
                BigInteger telefonoInteger = telefonoDecimal.toBigInteger();
                lector.setTelefono(telefonoInteger);
                lector.setEstado(true);
                lector.setMail(rs.getString("mail"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el lector");
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
                lector.setDni(rs.getInt("dni"));
                lector.setDomicilio(rs.getString("domicilio"));
                BigDecimal telefonoDecimal = rs.getBigDecimal("telefono");
                BigInteger telefonoInteger = telefonoDecimal.toBigInteger();
                lector.setTelefono(telefonoInteger);
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
}
