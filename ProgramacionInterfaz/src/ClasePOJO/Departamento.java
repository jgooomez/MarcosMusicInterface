/*
TODO   Eliminar metodos sin uso
!      CLASE EN DESUSO
 */


package ClasePOJO;

/**
 * Clase que representa un departamento en una organización.
 */
public class Departamento {
    private final int ID_DEPARTAMENTO;
    private String nombre;
    private final String FECHA_CREACION;
    private String nombreEncargado;
    private String numTrabajadores;
    private String numSubDpto;

    /**
     * Constructor de la clase Departamento.
     *
     * @param idDepartamento  ID del departamento.
     * @param nombre          Nombre del departamento.
     * @param fechaCreacion   Fecha de creación del departamento.
     * @param nombreEncargado Nombre del encargado del departamento.
     * @param numTrabajadores Número de trabajadores en el departamento.
     * @param numSubDpto      Número de subdepartamentos del departamento.
     */
    public Departamento(int idDepartamento, String nombre, String fechaCreacion, String nombreEncargado, String numTrabajadores, String numSubDpto) {
        this.ID_DEPARTAMENTO = idDepartamento;
        this.nombre = nombre;
        this.FECHA_CREACION = fechaCreacion;
        this.nombreEncargado = nombreEncargado;
        this.numTrabajadores = numTrabajadores;
        this.numSubDpto = numSubDpto;
    }

    /**
     * Obtiene el ID del departamento.
     *
     * @return El ID del departamento.
     */
    public int getIdDepartamento() {
        return ID_DEPARTAMENTO;
    }

    /**
     * Obtiene el nombre del departamento.
     *
     * @return El nombre del departamento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del departamento.
     *
     * @param nombre El nombre del departamento a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha de creación del departamento.
     *
     * @return La fecha de creación del departamento.
     */
    public String getFechaCreacion() {
        return FECHA_CREACION;
    }

    /**
     * Obtiene el nombre del encargado del departamento.
     *
     * @return El nombre del encargado del departamento.
     */
    public String getNombreEncargado() {
        return nombreEncargado;
    }

    /**
     * Establece el nombre del encargado del departamento.
     *
     * @param nombreEncargado El nombre del encargado del departamento a establecer.
     */
    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    /**
     * Obtiene el número de trabajadores en el departamento.
     *
     * @return El número de trabajadores en el departamento.
     */
    public String getNumTrabajadores() {
        return numTrabajadores;
    }

    /**
     * Establece el número de trabajadores en el departamento.
     *
     * @param numTrabajadores El número de trabajadores en el departamento a establecer.
     */
    public void setNumTrabajadores(String numTrabajadores) {
        this.numTrabajadores = numTrabajadores;
    }

    /**
     * Obtiene el número de subdepartamentos del departamento.
     *
     * @return El número de subdepartamentos del departamento.
     */
    public String getNumSubDpto() {
        return numSubDpto;
    }

    /**
     * Establece el número de subdepartamentos del departamento.
     *
     * @param numSubDpto El número de subdepartamentos del departamento a establecer.
     */
    public void setNumSubDpto(String numSubDpto) {
        this.numSubDpto = numSubDpto;
    }
}
