
package Clases;

import java.math.BigInteger;




public class Lector {
    private int idLector;
    private String nombre;
    private int dni;
    private String domicilio;
    private BigInteger telefono;
    private boolean estado;
    private String mail;

    public Lector() {
    }

    public Lector(String nombre, int dni, String domicilio, BigInteger telefono, boolean estado, String mail) {
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.estado = estado;
        this.mail = mail;
    }

    public Lector(int idLector, String nombre, int dni, String domicilio, BigInteger telefono, boolean estado, String mail) {
        this.idLector = idLector;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.estado = estado;
        this.mail = mail;
    }

    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    
    
    @Override
    public String toString() {
        return "ID: " + idLector + " - Nombre: " + nombre + " - DNI: " + dni + " - Domicilio: " + domicilio + " - Teléfono: " + telefono + " - Estado: " + estado + " - Mail: " + mail;
    }
    
}
