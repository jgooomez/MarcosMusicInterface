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
    //Este constructor se usa para generar objetos POJO para administrar la tabla donde se muestran los Departamentos
    //También se usa para mostrar los departamentos en los ComboBox de EliminarDepartamento y EditarDepartamento
    public Departamento(int idDepartamento, String nombre, String fechaCreacion, String nombreEncargado, String numTrabajadores, String numSubDpto) {
        this.idDepartamento=idDepartamento;
        if (nombre == null){
            this.nombre="null";
        }else{
            this.nombre = nombre;
        }
        if (fechaCreacion==null){
            this.fechaCreacion = "2000-01-01";
        }else {
            this.fechaCreacion = fechaCreacion;
        }
        if(nombreEncargado==null){
            this.nombreEncargado = "null";
        }else{
            this.nombreEncargado = nombreEncargado;
        }
        if (numTrabajadores==null){
            this.numTrabajadores = "0";
        }else {
            this.numTrabajadores = numTrabajadores;
        }
        if (numSubDpto==null){
            this.numSubDpto = "0";
        }else {
            this.numSubDpto = numSubDpto;
        }
    }
    //Este constructor sirve para crear un nuevo departamento en AnyadirDepartamento
    //El ID en la base de datos es autoincremental y no es necesario proporcionarlo
    public Departamento(String nombre, String fechaCreacion, String nombreEncargado, String numTrabajadores, String numSubDpto) {
        if (nombre == null){
            this.nombre="null";
        }else{
            this.nombre = nombre;
        }
        if (fechaCreacion==null){
            this.fechaCreacion = "2000-01-01";
        }else {
            this.fechaCreacion = fechaCreacion;
        }
        if(nombreEncargado==null){
            this.nombreEncargado = "null";
        }else{
            this.nombreEncargado = nombreEncargado;
        }
        if (numTrabajadores==null){
            this.numTrabajadores = "0";
        }else {
            this.numTrabajadores = numTrabajadores;
        }
        if (numSubDpto==null){
            this.numSubDpto = "0";
        }else {
            this.numSubDpto = numSubDpto;
        }
    }
}
