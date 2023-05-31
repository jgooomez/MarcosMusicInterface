package ClasePOJO;

public class Usuario {
    private int id;
    private String nacionalidad;
    private String nombre;
    private int edad;
    private int numSeguidores;
    private String fotoPerfil;

    public Usuario(int id, String nacionalidad, String nombre, int edad, int numSeguidores) {
        this.id = id;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.edad = edad;
        this.numSeguidores = numSeguidores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumSeguidores() {
        return numSeguidores;
    }

    public void setNumSeguidores(int numSeguidores) {
        this.numSeguidores = numSeguidores;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}