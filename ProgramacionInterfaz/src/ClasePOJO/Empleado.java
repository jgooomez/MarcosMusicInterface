package ClasePOJO;

import java.util.Date;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private int edad;
    private String nacionalidad;
    private String fechaINC;
    private String departamento;

    public Empleado(int idEmpleado, String nombre, int edad, String nacionalidad, Date fechaINC, String departamento) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.fechaINC = String.valueOf(fechaINC);
        this.departamento = departamento;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
