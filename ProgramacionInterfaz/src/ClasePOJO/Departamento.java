package ClasePOJO;

public class Departamento {
    private int idDepartamento;
    private String nombre;
    private String fechaCreacion;
    private String nombreEncargado;
    private String numTrabajadores;
    private String numSubDpto;

    public Departamento(int idDepartamento, String nombre, String fechaCreacion, String nombreEncargado, String numTrabajadores, String numSubDpto) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.nombreEncargado = nombreEncargado;
        this.numTrabajadores = numTrabajadores;
        this.numSubDpto = numSubDpto;
    }
}
