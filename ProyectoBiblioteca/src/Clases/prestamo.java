
package Clases;

import java.time.LocalDate;


public class prestamo {
    private int idPrestamo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int idLibro;
    private int dni;
    private boolean estado;

    public prestamo() {
    }

    public prestamo(int idPrestamo, LocalDate fechaInicio, LocalDate fechaFin, int idLibro, int dni, boolean estado) {
        this.idPrestamo = idPrestamo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idLibro = idLibro;
        this.dni = dni;
        this.estado = estado;
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
    
    @Override
    public String toString() {
        return "Prestamo{" + "idPrestamo=" + idPrestamo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", idLobro=" + idLibro + ", dni=" + dni + ", estado=" + estado +'}';
    }
    
}
