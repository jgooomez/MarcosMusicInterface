package ClasePOJO;

public class Departamento {
    private int idDepartamento;
    private String nombre;
    private String fechaCreacion;
    private String nombreEncargado;
    private String numTrabajadores;
    private String numSubDpto;

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public String getNumTrabajadores() {
        return numTrabajadores;
    }

    public void setNumTrabajadores(String numTrabajadores) {
        this.numTrabajadores = numTrabajadores;
    }

    public String getNumSubDpto() {
        return numSubDpto;
    }

    public void setNumSubDpto(String numSubDpto) {
        this.numSubDpto = numSubDpto;
    }

    public Departamento(int idDepartamento, String nombre, String fechaCreacion, String nombreEncargado, String numTrabajadores, String numSubDpto) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.nombreEncargado = nombreEncargado;
        this.numTrabajadores = numTrabajadores;
        this.numSubDpto = numSubDpto;
    }
}
