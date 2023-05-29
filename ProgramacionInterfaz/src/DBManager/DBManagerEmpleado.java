package DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DBManager.DBManagerConexion.conn;

public class DBManagerEmpleado {
    // Configuración de la tabla Empleado
    private static final String DB_US = "Empleado";
    private static final String DB_US_SELECT = "SELECT * FROM " + DB_US;
    private static final String DB_US_ID = "idEmpleado";
    private static final String DB_US_NAC = "nacionalidad";
    private static final String DB_US_NOM = "nombre";
    private static final String DB_US_ED = "edad";
    private static final String DB_US_FINC = "fechaIncorporacion";
    private static final String DB_US_DEP = "departamento";

    /**
     * Obtiene toda la tabla Empleado de la base de datos
     *
     * @param resultSetType        Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */
    public static ResultSet getTablaEmpleado(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_US_SELECT);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene toda la tabla Empleado de la base de datos
     *
     * @return ResultSet (por defecto) con la tabla, null en caso de error
     */
    public static ResultSet getTablaEmpleado() {
        return getTablaEmpleado(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Imprime por pantalla el contenido de la tabla Empleado
     */
    public static void printTablaEmpleado() {
        try {
            ResultSet rs = getTablaEmpleado(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int id = rs.getInt(DB_US_ID);
                String nacionalidad = rs.getString(DB_US_NAC);
                String nombre = rs.getString(DB_US_NOM);
                int edad = rs.getInt(DB_US_ED);
                String fechaIncorporacion = rs.getString(DB_US_FINC);
                String departamento = rs.getString(DB_US_DEP);

                System.out.println(id + "\t" + nacionalidad + "\t" + nombre + "\t" + edad + "\t" + fechaIncorporacion + "\t" + departamento);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////////////////////////////////
    // MÉTODOS DE UN SOLO Empleado
    //////////////////////////////////////////////////

    /**
     * Solicita a la BD el Empleado con id indicado
     *
     * @param idEmpleado id del Empleado
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getEmpleado(int idEmpleado) {
        try {
            // Realizamos la consulta SQL
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_US_SELECT + " WHERE " + DB_US_ID + "='" + idEmpleado + "';";
            ResultSet rs = stmt.executeQuery(sql);

            // Si no hay primer registro entonces no existe el Empleado.
            if (!rs.first()) {
                return null;
            }

            // Todo bien, devolvemos el Empleado
            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Comprueba si en la BD existe el Empleado con id indicado
     *
     * @param id id del Empleado
     * @return verdadero si existe, false en caso contrario
     */
    public static boolean existsEmpleado(int id) {
        try {
            // Obtenemos el Empleado
            ResultSet rs = getEmpleado(id);

            // Si rs es null, se ha producido un error
            if (rs == null) {
                return false;
            }

            // Si no existe primer registro
            if (!rs.first()) {
                rs.close();
                return false;
            }

            // Todo bien, existe el Empleado
            rs.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Imprime los datos del Empleado con id indicado
     *
     * @param id id del Empleado
     */
    public static void printEmpleado(int id) {
        try {
            // Obtenemos el Empleado
            ResultSet rs = getEmpleado(id);
            if (rs == null || !rs.first()) {
                System.out.println("Empleado " + id + " NO EXISTE");
                return;
            }

            int idEmpleado = rs.getInt(DB_US_ID);
            String nacionalidad = rs.getString(DB_US_NAC);
            String nombre = rs.getString(DB_US_NOM);
            int edad = rs.getInt(DB_US_ED);
            String fechaIncorporacion = rs.getString(DB_US_FINC);
            String departamento = rs.getString(DB_US_DEP);

            System.out.println(idEmpleado + "\t" + nacionalidad + "\t" + nombre + "\t" + edad
                    + "\t" + fechaIncorporacion + "\t" + departamento);

        } catch (SQLException ex) {
            System.out.println("Error al solicitar Empleado " + id);
            ex.printStackTrace();
        }
    }

    /**
     * Solicita a la BD insertar un nuevo registro Empleado
     *
     * @param nacionalidad       nacionalidad del Empleado
     * @param nombre             nombre del Empleado
     * @param edad               edad del Empleado
     * @param fechaIncorporacion fecha de incorporación del Empleado
     * @param departamento       departamento del Empleado
     * @return verdadero si pudo insertarlo, false en caso contrario
     */
    public static boolean insertEmpleado(String nacionalidad, String nombre, int edad, String fechaIncorporacion, String departamento) {
        try {
            // Obtenemos la tabla Empleado
            System.out.print("Insertando Empleado " + nombre + "...");
            ResultSet rs = getTablaEmpleado(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            // Insertamos el nuevo registro
            rs.moveToInsertRow();
            rs.updateString(DB_US_NOM, nombre);
            rs.updateString(DB_US_NAC, nacionalidad);
            rs.updateInt(DB_US_ED, edad);
            rs.updateString(DB_US_FINC, fechaIncorporacion);
            rs.updateString(DB_US_DEP, departamento);

            rs.insertRow();

            // Todo bien, cerramos ResultSet y devolvemos true
            rs.close();
            System.out.println("OK!");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Solicita a la BD eliminar un Empleado
     *
     * @param idEmpleado id del Empleado a eliminar
     * @return verdadero si pudo eliminarlo, false en caso contrario
     */
    public static boolean deleteEmpleado(int idEmpleado) {
        try {
            System.out.print("Eliminando Empleado " + idEmpleado + "... ");

            // Obtenemos el Empleado
            ResultSet rs = getEmpleado(idEmpleado);

            // Si no existe el Resultset
            if (rs == null) {
                System.out.println("ERROR. ResultSet null.");
                return false;
            }

            // Si existe y tiene primer registro, lo eliminamos
            if (rs.first()) {
                rs.deleteRow();
                rs.close();
                System.out.println("OK!");
                return true;
            } else {
                System.out.println("ERROR. ResultSet vacío.");
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
