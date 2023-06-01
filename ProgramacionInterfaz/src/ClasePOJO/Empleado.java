package ClasePOJO;

import java.util.Date;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private int edad;
    private String nacionalidad;
    private String fechaINC;
    private int idDepartamento;

    public Empleado(int idEmpleado, String nombre, int edad, String nacionalidad, String fechaINC, int idDepartamento) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.fechaINC = fechaINC;
        this.idDepartamento = idDepartamento;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFechaINC() {
        return fechaINC;
    }

    public void setFechaINC(String fechaINC) {
        this.fechaINC = fechaINC;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setDepartamento(int departamento) {
        this.idDepartamento = departamento;
    }
}
