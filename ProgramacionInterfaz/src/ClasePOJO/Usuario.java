/*
TODO    Eliminar metodos no usados
 */
package ClasePOJO;

/**
 * Clase que representa a un usuario en el sistema.
 */
public class Usuario {
    private int id;
    private String nacionalidad;
    private String nombre;
    private int edad;
    private int numSeguidores;
    private String fotoPerfil;

    /**
     * Constructor de la clase Usuario.
     *
     * @param id            ID del usuario.
     * @param nacionalidad  Nacionalidad del usuario.
     * @param nombre        Nombre del usuario.
     * @param edad          Edad del usuario.
     * @param numSeguidores Número de seguidores del usuario.
     */
    public Usuario(int id, String nacionalidad, String nombre, int edad, int numSeguidores) {
        this.id = id;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.edad = edad;
        this.numSeguidores = numSeguidores;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id El ID del usuario a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la nacionalidad del usuario.
     *
     * @return La nacionalidad del usuario.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del usuario.
     *
     * @param nacionalidad La nacionalidad del usuario a establecer.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad del usuario.
     *
     * @return La edad del usuario.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del usuario.
     *
     * @param edad La edad del usuario a establecer.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el número de seguidores del usuario.
     *
     * @return El número de seguidores del usuario.
     */
    public int getNumSeguidores() {
        return numSeguidores;
    }

    /**
     * Establece el número de seguidores del usuario.
     *
     * @param numSeguidores El número de seguidores del usuario a establecer.
     */
    public void setNumSeguidores(int numSeguidores) {
        this.numSeguidores = numSeguidores;
    }

    /**
     * Obtiene la foto de perfil del usuario.
     *
     * @return La foto de perfil del usuario.
     */
    public String getFotoPerfil() {
        return fotoPerfil;
    }

    /**
     * Establece la foto de perfil del usuario.
     *
     * @param fotoPerfil La foto de perfil del usuario a establecer.
     */
    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
