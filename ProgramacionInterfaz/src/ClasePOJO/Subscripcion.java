package ClasePOJO;

public class Subscripcion {
    private final int ID;
    private final String TIPO;
    private final double PRECIO;
    private final String DESCRIPCION;

    public Subscripcion(int ID, String TIPO, double PRECIO, String DESCRIPCION) {
        this.ID = ID;
        this.TIPO = TIPO;
        this.PRECIO = PRECIO;
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getID() {
        return ID;
    }

    public String getTIPO() {
        return TIPO;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }
}
