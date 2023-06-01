package ClasePOJO;

import java.util.Date;

public class Tarjeta {
    private String numeroTarjeta;
    private String telefono;
    private String tipo;
    private String nombreTitular;
    private int cvv;
    private Date caducidad;
    private int idUsuario;

    public Tarjeta(String numeroTarjeta, String telefono, String tipo, String nombreTitular, int cvv, Date caducidad, int idUsuario) {
        this.numeroTarjeta = numeroTarjeta;
        this.telefono = telefono;
        this.tipo = tipo;
        this.nombreTitular = nombreTitular;
        this.cvv = cvv;
        this.caducidad = caducidad;
        this.idUsuario = idUsuario;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
