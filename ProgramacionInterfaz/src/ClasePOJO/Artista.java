package ClasePOJO;

public class Artista {
    private int idArtista;
    private String nombre;
    private String fechaInicio;
    private String nacionalidad;
    private int numPremios;

    public int getIdArtista() {
        return idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public int getNumPremios() {
        return numPremios;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public Artista(int idArtista, String nombre, String fechaInicio, String nacionalidad, int numPremios, String generoMusical) {
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.nacionalidad = nacionalidad;
        this.numPremios = numPremios;
        this.generoMusical = generoMusical;
    }

    public Artista(String nombre, String fechaInicio, String nacionalidad, int numPremios, String generoMusical) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.nacionalidad = nacionalidad;
        this.numPremios = numPremios;
        this.generoMusical = generoMusical;
    }

    private String generoMusical;

}
