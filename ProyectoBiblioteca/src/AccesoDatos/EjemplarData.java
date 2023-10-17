/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Clases.Lector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Clases.*;
import AccesoDatos.*;
import java.sql.Connection;

/**
 *
 * @author ariel
 */
public class EjemplarData {
    
    private Connection con =null;
    public EjemplarData(){
        con=Conexion.getConexion();
    }
    
    public void crearEjemplar(Ejemplar ejemplar){
 
        String sql="INSERT INTO ejemplar (idLibro,estado,cantidad)" + " VALUES(?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ejemplar.getLibro().getIdLibro());
            ps.setString(2, ejemplar.getEstado().name());
            ps.setInt(3, ejemplar.getCantidad());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                ejemplar.setIdEjemplar(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Ejemplar guardado");
            }
            ps.close();
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error al acceder a la tabla Lector "+ex.getMessage());      
        }
    }
    /*public void guardarInscripcion(Inscripcion insc) {
         String sql = "INSERT INTO inscripcion(idAlumno, idMateria, nota) VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, org.mariadb.jdbc.Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, insc.getAlumno().getIdAlumno());
            ps.setInt(2, insc.getMateria().getidMateria());
            ps.setDouble(3, insc.getNota());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripción Registrada");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }    
     }
*/
    public Ejemplar buscarEjemplarPorId(int id) {
        Ejemplar ejemplar = null;
        String sql = "SELECT * FROM ejemplar WHERE idEjemplar=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ejemplar = new Ejemplar();
                ejemplar.setIdEjemplar(rs.getInt("idEjemplar"));
                Libro libro = new Libro();
                LibroData ld = new LibroData();
                libro = ld.buscarLibroPorId(rs.getInt("idLibro"));
                ejemplar.setLibro(libro);
                ejemplar.setCantidad(rs.getInt("cantidad"));
                EstadoEjemplar ee = EstadoEjemplar.valueOf(rs.getString("estado"));
                ejemplar.setEstado(ee);
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe el ejemplar");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Lector "+ex.getMessage());
        }
        return ejemplar;
    }
}
