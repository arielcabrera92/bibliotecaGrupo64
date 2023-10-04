
package Clases;


public class Ejemplar {
    private int idLibro;
    private boolean estado;
    private int cantidad ;
    private Libro libro; 
    
    public Ejemplar() {
    }
    
    public Ejemplar(int idLibro, boolean estado, int cantidad, Libro libro) {
        this.idLibro = idLibro;
        this.estado = estado;
        this.cantidad = cantidad;
        this.libro = libro; 
    }

    public Ejemplar(boolean estado, int cantidad, Libro libro) {
        this.estado = estado;
        this.cantidad = cantidad;
        this.libro = libro;
    }
    

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
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
        return "Ejemplar{" + "idLibro=" + idLibro + ", estado=" + estado + ", cantidad=" + cantidad + '}';
    }
    
}
