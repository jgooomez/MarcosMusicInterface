/*
TODO    Eliminar metodos no usados
 */
package ClasePOJO;

import java.util.Date;

/**
 * Clase que representa una tarjeta de crédito o débito asociada a un usuario.
 */
public class Tarjeta {
    private String numeroTarjeta;
    private String telefono;
    private String tipo;
    private String nombreTitular;
    private int cvv;
    private String caducidad;
    private int idUsuario;

    /**
     * Constructor de la clase Tarjeta.
     *
     * @param numeroTarjeta   Número de la tarjeta.
     * @param telefono        Número de teléfono asociado a la tarjeta.
     * @param tipo            Tipo de tarjeta (crédito o débito).
     * @param nombreTitular   Nombre del titular de la tarjeta.
     * @param cvv             Código de seguridad de la tarjeta.
     * @param caducidad       Fecha de caducidad de la tarjeta.
     * @param idUsuario       ID del usuario al que está asociada la tarjeta.
     */
    public Tarjeta(String numeroTarjeta, String telefono, String tipo, String nombreTitular, int cvv, String caducidad, int idUsuario) {
        this.numeroTarjeta = numeroTarjeta;
        this.telefono = telefono;
        this.tipo = tipo;
        this.nombreTitular = nombreTitular;
        this.cvv = cvv;
        this.caducidad = caducidad;
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el número de la tarjeta.
     *
     * @return El número de la tarjeta.
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Establece el número de la tarjeta.
     *
     * @param numeroTarjeta El número de la tarjeta a establecer.
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * Obtiene el número de teléfono asociado a la tarjeta.
     *
     * @return El número de teléfono asociado a la tarjeta.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono asociado a la tarjeta.
     *
     * @param telefono El número de teléfono asociado a la tarjeta a establecer.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el tipo de tarjeta.
     *
     * @return El tipo de tarjeta (crédito o débito).
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de tarjeta.
     *
     * @param tipo El tipo de tarjeta (crédito o débito) a establecer.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el nombre del titular de la tarjeta.
     *
     * @return El nombre del titular de la tarjeta.
     */
    public String getNombreTitular() {
        return nombreTitular;
    }

    /**
     * Establece el nombre del titular de la tarjeta.
     *
     * @param nombreTitular El nombre del titular de la tarjeta a establecer.
     */
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    /**
     * Obtiene el código de seguridad de la tarjeta.
     *
     * @return El código de seguridad de la tarjeta.
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * Establece el código de seguridad de la tarjeta.
     *
     * @param cvv El código de seguridad de la tarjeta a establecer.
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * Obtiene la fecha de caducidad de la tarjeta.
     *
     * @return La fecha de caducidad de la tarjeta.
     */
    public String getCaducidad() {
        return caducidad;
    }

    /**
     * Establece la fecha de caducidad de la tarjeta.
     *
     * @param caducidad La fecha de caducidad de la tarjeta a establecer.
     */
    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    /**
     * Obtiene el ID del usuario al que está asociada la tarjeta.
     *
     * @return El ID del usuario al que está asociada la tarjeta.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario al que está asociada la tarjeta.
     *
     * @param idUsuario El ID del usuario al que está asociada la tarjeta a establecer.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
