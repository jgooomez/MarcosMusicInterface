/*
TODO    Eliminar metodos no usados
 */

package ClasePOJO;

/**
 * Clase que representa a un empleado en una organización.
 */
public class Empleado {
    private final int ID_EMPLEADO;
    private String nombre;
    private int edad;
    private String nacionalidad;
    private final String FECHA_INC;
    private int idDepartamento;

    /**
     * Constructor de la clase Empleado.
     *
     * @param idEmpleado      ID del empleado.
     * @param nombre          Nombre del empleado.
     * @param edad            Edad del empleado.
     * @param nacionalidad    Nacionalidad del empleado.
     * @param fechaINC        Fecha de incorporación del empleado.
     * @param idDepartamento  ID del departamento al que pertenece el empleado.
     */
    public Empleado(int idEmpleado, String nombre, int edad, String nacionalidad, String fechaINC, int idDepartamento) {
        this.ID_EMPLEADO = idEmpleado;
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.FECHA_INC = fechaINC;
        this.idDepartamento = idDepartamento;
    }

    /**
     * Obtiene el ID del empleado.
     *
     * @return El ID del empleado.
     */
    public int getIdEmpleado() {
        return ID_EMPLEADO;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nombre El nombre del empleado a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad del empleado.
     *
     * @return La edad del empleado.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del empleado.
     *
     * @param edad La edad del empleado a establecer.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene la nacionalidad del empleado.
     *
     * @return La nacionalidad del empleado.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del empleado.
     *
     * @param nacionalidad La nacionalidad del empleado a establecer.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene la fecha de incorporación del empleado.
     *
     * @return La fecha de incorporación del empleado.
     */
    public String getFechaINC() {
        return FECHA_INC;
    }

    /**
     * Obtiene el ID del departamento al que pertenece el empleado.
     *
     * @return El ID del departamento al que pertenece el empleado.
     */
    public int getIdDepartamento() {
        return idDepartamento;
    }

    /**
     * Establece el ID del departamento al que pertenece el empleado.
     *
     * @param departamento El ID del departamento a establecer.
     */
    public void setDepartamento(int departamento) {
        this.idDepartamento = departamento;
    }
}
