package ClasePOJO;

public class Reproduccion {
    private int idReproduccion;
    private int duracion;
    private int codigoContenido;
    private String fechaReproduccion;
    private String hora;
    private int valoracion;
    private int idUsuario;

    public Reproduccion(int idReproduccion, int duracion, int codigoContenido, String fechaReproduccion, String hora, int valoracion, int idUsuario) {
        this.idReproduccion = idReproduccion;
        this.duracion = duracion;
        this.codigoContenido = codigoContenido;
        this.fechaReproduccion = fechaReproduccion;
        this.hora = hora;
        this.valoracion = valoracion;
        this.idUsuario = idUsuario;
    }

    public int getIdReproduccion() {
        return idReproduccion;
    }

    public void setIdReproduccion(int idReproduccion) {
        this.idReproduccion = idReproduccion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getCodigoContenido() {
        return codigoContenido;
    }

    public void setCodigoContenido(int codigoContenido) {
        this.codigoContenido = codigoContenido;
    }

    public String getFechaReproduccion() {
        return fechaReproduccion;
    }

    public void setFechaReproduccion(String fechaReproduccion) {
        this.fechaReproduccion = fechaReproduccion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Puedes agregar otros métodos si los necesitas
}
