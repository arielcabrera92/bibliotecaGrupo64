/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobiblioteca;

import AccesoDatos.*;
import Clases.*;
import java.math.BigInteger;
//import java.sql.Connection;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Jona
 */
public class ProyectoBiblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection con = Conexion.getConexion();
        //------------------PRUEBA GUARDAR LIBROS-------------------------------
        //LibroData ld = new LibroData(); 
        //Libro jdt = new Libro(976, "Juego de Tronos", "Fantasía", "Debolsillo", "George R.R. Martin", true, 2010); 
        //ld.guardarLibro(jdt);
        //Libro tde = new Libro (977, "Tormenta de Espadas", "Fantasía", "Debolsillo", "George R.R. Martin", true, 2015);
        //ld.guardarLibro(tde);
        //LibroData ld2 = new LibroData(); 
        //Libro ntdp = new Libro (658, "Nuestra Señora de París", "Drama", "Alfaguara", "Víctor Hugo", true, 1800);
        //ld2.guardarLibro(ntdp);
        //Libro ele = new Libro (950, "Escrito en las Estrellas", "Romance", "EMECÉ", "Sidney Sheldon", true, 1992);
        //ld2.guardarLibro(ele);
        
        //-------------------PRUEBA BUSCAR LIBRO POR AUTOR Y NOMBRE---------------------
        
        //LibroData ld = new LibroData();
        //List<Libro> libros = ld.buscarLibroPorNombre("Nuestra Señora de París");
        //System.out.println(libros);
        
        //--------------------PRUEBA MODIFICAR LIBRO--------------------------------
        //LibroData ld = new LibroData();
        //Libro libro = new Libro (4, 976, "Juego de Tronos", "Terror", "Debolsillo", "George R.R. Martin", true, 2011);
        //ld.modificarLibro(libro);
        
        //-------------------- PRUEBA ELIMINAR LIBRO-------------------------------
        //LibroData ld = new LibroData(); 
        //ld.eliminarLibro(4);
        
        
        //--------------------PRUEBA LISTAR LIBROS-------------------------------------
        //LibroData ld = new LibroData (); 
        //List<Libro> libros = ld.listarLibros();
        //System.out.println(libros);
        
        //----------------------PRUEBA CREAR LECTOR---------------------------------------
        //LectorData ld = new LectorData(); 
        //Lector esteban = new Lector ("Esteban Perez", 34963258, "Independencia 1254 - Río Cuarto, Córdoba", 3584741258L, true, "ep@gmail.com");
        //ld.crearLector(esteban);
        //Lector Teresa = new Lector ("Teresa Rodriguez", 95369850, "Calle tanto 159 - Buenos Aires", new BigInteger("11698741236"), true, "tr@gmail.com");
        //ld.crearLector(Teresa);
        //Lector maria = new Lector ("Maria Suarez", 30170589, "9 de Julio 585 - Pilar - Buenos Aires", new BigInteger("11632145678"), true, "ms@gmail.com");
        //LectorData ld = new LectorData ();
        //ld.crearLector(maria);
        
        
        
        //--------------------PRUEBA MODIFICAR LECTOR-----------------------------------------
        //LectorData lectorD = new LectorData(); 
        //Lector jonathan = new Lector (1, "Jonathan Dreiszigacker", 24963852, "Miguel David 91 - Entre Ríos", new BigInteger ("3434690604"), true, "jd@gmail.com");
        //lectorD.modificarLector(jonathan);
        
        
        
        // ------------------------PRUEBA BUSCAR LECTOR POR DNI Y POR ID -------------------------------
        //LectorData ld = new LectorData(); 
        //Lector ariel = ld.buscarLectorPorDni(36850403);
        //System.out.println(ariel);
        //Lector teresa = ld.buscarLectorPorId(5);
        //System.out.println(teresa);
        
        
        //------------------------PRUEBA LISTAR LECTORES---------------------------------------------------
        LectorData ld = new LectorData();
        List<Lector> lectores = ld.listarLectores();
        System.out.println(lectores);
    }
    
    
}
