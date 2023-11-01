
package Clases;


public class Ejemplar {
    private int idEjemplar;
    private EstadoEjemplar estado;
    private int cantidad ;
    private Libro libro; 

    public Ejemplar() {
    }

    public Ejemplar(EstadoEjemplar estado, int cantidad, Libro libro) {
        this.estado = estado;
        this.cantidad = cantidad;
        this.libro = libro;
    }

    public Ejemplar(int idEjemplar, EstadoEjemplar estado, int cantidad, Libro libro) {
        this.idEjemplar = idEjemplar;
        this.estado = estado;
        this.cantidad = cantidad;
        this.libro = libro;
    }

    public int getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(int idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjemplar estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    
    
    
    
   
    @Override
    public String toString() {
        return "Ejemplar: " + "idLibro: " + idEjemplar + " - estado: " + estado + " - cantidad: " + cantidad;
    }
    
}
