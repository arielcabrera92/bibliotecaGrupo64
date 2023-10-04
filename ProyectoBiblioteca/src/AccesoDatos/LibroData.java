/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Clases.Libro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ariel
 */
public class LibroData {
        
    private Connection con =null;
    
    public LibroData(){
        con=Conexion.getConexion();
    }
    public void guardarLibro(Libro libro){
        String sql="INSERT INTO libros (isbn, nombre, tipo, editorial, autor, estado, anio)" + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, libro.getIsbn());
            ps.setString(2, libro.getNombre());
            ps.setString(3, libro.getTipo());
            ps.setString(4, libro.getEditorial());
            ps.setString(5, libro.getAutor());
            ps.setBoolean(6, libro.isEstado());
            ps.setInt(7, libro.getAnio());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                libro.setIdLibro(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Libro guardado");
            }
            ps.close();
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error al acceder a la tabla libros");      
        }
    }
    
    public List <Libro> buscarLibroPorAutor(String autor) {
        //Libro libro = null;
        List<Libro> libros = new ArrayList<>();
        //String sql = "SELECT idLibro, isbn, nombre, tipo, editorial, autor, estado, anio  FROM libros WHERE autor=?";
        
        try {
            String sql = "SELECT * FROM libros WHERE autor = ?";
            PreparedStatement ps = null;
            ps = con.prepareStatement(sql);
            ps.setString(1, autor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                do {
                    Libro libro = new Libro();
                    libro.setIdLibro(rs.getInt("idLibro"));
                    libro.setIsbn(rs.getInt("isbn"));
                    libro.setNombre(rs.getString("nombre"));
                    libro.setTipo(rs.getString("tipo"));
                    libro.setEditorial(rs.getString("editorial"));
                    libro.setAutor(rs.getString("autor"));
                    libro.setEstado(rs.getBoolean("estado"));
                    libro.setAnio(rs.getInt("anio"));
                    libros.add(libro);
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No existe el libro");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Libros "+ex.getMessage());
        }
        return libros; 
    }
    
    public List <Libro> buscarLibroPorNombre(String nombre) {
        List<Libro> libros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM libros WHERE nombre = ?";
            PreparedStatement ps = null;
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                do{
                    Libro libro = new Libro();
                    libro.setIdLibro(rs.getInt("idLibro"));
                    libro.setIsbn(rs.getInt("isbn"));
                    libro.setNombre(rs.getString("nombre"));
                    libro.setTipo(rs.getString("tipo"));
                    libro.setEditorial(rs.getString("editorial"));
                    libro.setAutor(rs.getString("autor"));
                    libro.setEstado(rs.getBoolean("estado"));
                    libro.setAnio(rs.getInt("anio"));
                    libros.add(libro);  
                }while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No existe el libro");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Libros "+ex.getMessage());
        }
        return libros; 
    }
    public void modificarLibro(Libro libro){
        String sql = "UPDATE libros SET isbn= ?, nombre= ?, tipo= ?, editorial= ?, autor= ?, estado= ?, anio= ?" + " WHERE idLibro=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, libro.getIsbn());
            ps.setString(2, libro.getNombre());
            ps.setString(3, libro.getTipo());
            ps.setString(4, libro.getEditorial());
            ps.setString(5, libro.getAutor());
            ps.setBoolean(6,libro.isEstado());
            ps.setInt(7, libro.getAnio());
            ps.setInt(8, libro.getIdLibro());
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Libro actualizado exitosamente");
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Libros " + ex.getMessage());
        }
    }
    
    public void eliminarLibro(int id) {
        try {
            String sql = "UPDATE libros SET estado = 0 WHERE idLibro = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if(fila==1){
                JOptionPane.showMessageDialog(null, " Se eliminó el libro.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Libros");
        }
    }
    
    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM libros WHERE estado = 1 ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro();

                libro.setIdLibro(rs.getInt("idLibro"));
                libro.setIsbn(rs.getInt("isbn"));
                libro.setNombre(rs.getString("nombre"));
                libro.setTipo(rs.getString("tipo"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setAutor(rs.getString("autor"));
                libro.setEstado(rs.getBoolean("estado"));
                libro.setAnio(rs.getInt("anio"));
                libros.add(libro);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla libros "+ex.getMessage());
        }
        return libros;
    }

    
}
