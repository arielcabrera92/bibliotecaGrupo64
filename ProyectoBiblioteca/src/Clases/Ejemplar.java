
package Clases;


public class Ejemplar {
    private int idEjemplar;
    private String estado;
    private int cantidad ;
    private Libro libro; 

    public Ejemplar() {
    }

    public Ejemplar(String estado, int cantidad, Libro libro) {
        this.estado = estado;
        this.cantidad = cantidad;
        this.libro = libro;
    }

    public Ejemplar(int idEjemplar, String estado, int cantidad, Libro libro) {
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

    public String isEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
