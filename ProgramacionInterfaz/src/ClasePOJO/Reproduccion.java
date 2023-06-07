/*
TODO    Eliminar metodos no usados
 */
package ClasePOJO;

/**
 * Clase que representa una reproducción de contenido realizada por un usuario.
 */
public class Reproduccion {
    private final int ID_REPRODUCCION;
    private int duracion;
    private int codigoContenido;
    private String fechaReproduccion;
    private String hora;
    private int valoracion;
    private int idUsuario;

    /**
     * Constructor de la clase Reproduccion.
     *
     * @param idReproduccion    ID de la reproducción.
     * @param duracion          Duración de la reproducción en segundos.
     * @param codigoContenido   Código del contenido reproducido.
     * @param fechaReproduccion Fecha de la reproducción.
     * @param hora              Hora de la reproducción.
     * @param valoracion        Valoración dada a la reproducción.
     * @param idUsuario         ID del usuario que realizó la reproducción.
     */
    public Reproduccion(int idReproduccion, int duracion, int codigoContenido, String fechaReproduccion, String hora, int valoracion, int idUsuario) {
        this.ID_REPRODUCCION = idReproduccion;
        this.duracion = duracion;
        this.codigoContenido = codigoContenido;
        this.fechaReproduccion = fechaReproduccion;
        this.hora = hora;
        this.valoracion = valoracion;
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el ID de la reproducción.
     *
     * @return El ID de la reproducción.
     */
    public int getIdReproduccion() {
        return ID_REPRODUCCION;
    }

    /**
     * Obtiene la duración de la reproducción en segundos.
     *
     * @return La duración de la reproducción en segundos.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración de la reproducción en segundos.
     *
     * @param duracion La duración de la reproducción en segundos a establecer.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene el código del contenido reproducido.
     *
     * @return El código del contenido reproducido.
     */
    public int getCodigoContenido() {
        return codigoContenido;
    }

    /**
     * Establece el código del contenido reproducido.
     *
     * @param codigoContenido El código del contenido reproducido a establecer.
     */
    public void setCodigoContenido(int codigoContenido) {
        this.codigoContenido = codigoContenido;
    }

    /**
     * Obtiene la fecha de la reproducción.
     *
     * @return La fecha de la reproducción.
     */
    public String getFechaReproduccion() {
        return fechaReproduccion;
    }

    /**
     * Establece la fecha de la reproducción.
     *
     * @param fechaReproduccion La fecha de la reproducción a establecer.
     */
    public void setFechaReproduccion(String fechaReproduccion) {
        this.fechaReproduccion = fechaReproduccion;
    }

    /**
     * Obtiene la hora de la reproducción.
     *
     * @return La hora de la reproducción.
     */
    public String getHora() {
        return hora;
    }

    /**
     * Establece la hora de la reproducción.
     *
     * @param hora La hora de la reproducción a establecer.
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Obtiene la valoración dada a la reproducción.
     *
     * @return La valoración dada a la reproducción.
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * Establece la valoración dada a la reproducción.
     *
     * @param valoracion La valoración dada a la reproducción a establecer.
     */
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    /**
     * Obtiene el ID del usuario que realizó la reproducción.
     *
     * @return El ID del usuario que realizó la reproducción.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario que realizó la reproducción.
     *
     * @param idUsuario El ID del usuario que realizó la reproducción a establecer.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
