/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.time.LocalDate;

/**
 *
 * @author ariel
 */
public class Prestamos {
    private int idPrestamo;
    private LocalDate fechaInicio = LocalDate.now();
    private LocalDate fechaFin = fechaInicio.plusWeeks(1);
    private int idLibro;
    private String dni;
    private boolean estado;
    private Lector lector; 
    private Libro libro; 

    public Prestamos() {
    }

    public Prestamos(int idLibro, String dni, boolean estado, Lector lector, Libro libro) {
        this.idLibro = idLibro;
        this.dni = dni;
        this.estado = estado;
        this.lector = lector;
        this.libro = libro;
    }

    public Prestamos(int idPrestamo, int idLibro, String dni, boolean estado, Lector lector, Libro libro) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.dni = dni;
        this.estado = estado;
        this.lector = lector;
        this.libro = libro;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

   
    
   @Override
    public String toString() {
        return "idPrestamo: " + idPrestamo + " Fecha préstamo: " + fechaInicio + " Fecha de devolución: " + fechaFin + " idLibro: " + libro.getIdLibro() + " DNI Lector " + lector.getDni() + " Estado: " + estado; 
    }    

}

