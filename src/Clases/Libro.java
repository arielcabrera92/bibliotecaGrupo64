package Clases;



public class Libro {

    private int idLibro;
    private int isbn;
    private String nombre;
    private String tipo;
    private String editorial;
    private String autor;
    private boolean estado;
    private int anio;
    private int ejemplares;
    private EstadoEjemplar disponibilidad;

    public Libro() {
    }

    public Libro(int idLibro, int isbn, String nombre, String tipo, String editorial, String autor, boolean estado, int anio, int ejemplares, EstadoEjemplar disponibilidad) {
        this.idLibro = idLibro;
        this.isbn = isbn;
        this.nombre = nombre;
        this.tipo = tipo;
        this.editorial = editorial;
        this.autor = autor;
        this.estado = estado;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.disponibilidad = disponibilidad;
    }

    public Libro(int isbn, String nombre, String tipo, String editorial, String autor, boolean estado, int anio, int ejemplares, EstadoEjemplar disponibilidad) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.tipo = tipo;
        this.editorial = editorial;
        this.autor = autor;
        this.estado = estado;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.disponibilidad = disponibilidad;
    }

    public Libro(int idLibro, int isbn, String nombre, String tipo, String editorial, String autor, boolean estado, int anio, int ejemplares) {
        this.idLibro = idLibro;
        this.isbn = isbn;
        this.nombre = nombre;
        this.tipo = tipo;
        this.editorial = editorial;
        this.autor = autor;
        this.estado = estado;
        this.anio = anio;
        this.ejemplares = ejemplares;
    }
    

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public EstadoEjemplar getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(EstadoEjemplar disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    
    

    @Override
    public String toString() {
        return "idLibro: " + idLibro + " isbn: " + isbn + " nombre: " + nombre + " tipo: " + tipo + " editorial: " + editorial + " autor: " + autor + " estado: " + estado + " año: " + anio + " ejemplares: " + ejemplares + " disponibilidad: " + disponibilidad;
    }
}
