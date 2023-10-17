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
    private int dni;
    private boolean estado;
    private Lector lector; 
    private Ejemplar ejemplar; 

    public Prestamos() {
    }

    public Prestamos(int idLibro, int dni, boolean estado, Lector lector, Ejemplar ejemplar) {
        this.idLibro = idLibro;
        this.dni = dni;
        this.estado = estado;
        this.lector = lector;
        this.ejemplar = ejemplar;
    }

    public Prestamos(int idPrestamo, int idLibro, int dni, boolean estado, Lector lector, Ejemplar ejemplar) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.dni = dni;
        this.estado = estado;
        this.lector = lector;
        this.ejemplar = ejemplar;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
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

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }
    
    
    
    
    
    
}

