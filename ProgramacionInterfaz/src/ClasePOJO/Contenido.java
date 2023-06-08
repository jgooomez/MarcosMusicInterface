package ClasePOJO;

public class Contenido {
    private int codigo;
    private int idUsuario;
    private String titulo;
    private String lugarGrabacion;
    private double valoracion;
    private int numeroReproducciones;
    private String album;
    private int anyoLanzamiento;

    public Contenido(int codigo, int idUsuario, String titulo, String lugarGrabacion, double valoracion,
                     int numeroReproducciones, String album, int anyoLanzamiento) {
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.lugarGrabacion = lugarGrabacion;
        this.valoracion = valoracion;
        this.numeroReproducciones = numeroReproducciones;
        this.album = album;
        this.anyoLanzamiento = anyoLanzamiento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLugarGrabacion() {
        return lugarGrabacion;
    }

    public void setLugarGrabacion(String lugarGrabacion) {
        this.lugarGrabacion = lugarGrabacion;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public int getNumeroReproducciones() {
        return numeroReproducciones;
    }

    public void setNumeroReproducciones(int numeroReproducciones) {
        this.numeroReproducciones = numeroReproducciones;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getAnyoLanzamiento() {
        return anyoLanzamiento;
    }

    public void setAnyoLanzamiento(int anyoLanzamiento) {
        this.anyoLanzamiento = anyoLanzamiento;
    }
}
