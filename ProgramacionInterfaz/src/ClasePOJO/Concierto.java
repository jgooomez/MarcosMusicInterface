package ClasePOJO;

import java.sql.Date;

public class Concierto {
    private int codigo;
    private String lugar;
    private Date fecha;
    private String ciudad;
    private String pais;
    private int capacidad;
    private double dineroRecaudado;

    public Concierto(int codigo, String lugar, Date fecha,
                     String ciudad, String pais, int capacidad,
                     double dineroRecaudado) {
        this.codigo = codigo;
        this.lugar = lugar;
        this.fecha = fecha;
        this.ciudad = ciudad;
        this.pais = pais;
        this.capacidad = capacidad;
        this.dineroRecaudado = dineroRecaudado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getDineroRecaudado() {
        return dineroRecaudado;
    }

    public void setDineroRecaudado(double dineroRecaudado) {
        this.dineroRecaudado = dineroRecaudado;
    }
}
