package DBManager;

import ClasePOJO.Subscripcion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import static DBManager.DBManagerConexion.conn;

public class DBManagerSubscripcion {
    // Configuración de la tabla Subscripcion
    private static final String DB_SUBSCRIPCION = "Subscripcion";
    private static final String DB_SUBSCRIPCION_SELECT = "SELECT * FROM " + DB_SUBSCRIPCION;
    private static final String DB_SUBSCRIPCION_ID = "id";
    private static final String DB_SUBSCRIPCION_TIPO = "tipo";
    private static final String DB_SUBSCRIPCION_PRECIO = "precio";
    private static final String DB_SUBSCRIPCION_DESCRIPCION = "descripcion";

    /**
     * Obtiene toda la tabla Subscripcion de la base de datos
     *
     * @param resultSetType        Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */
    public static ResultSet getTablaSubscripcion(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_SUBSCRIPCION_SELECT);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene toda la tabla Subscripcion de la base de datos
     *
     * @return ResultSet (por defecto) con la tabla, null en caso de error
     */
    public static ResultSet getTablaSubscripcion() {
        return getTablaSubscripcion(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Imprime por pantalla el contenido de la tabla Subscripcion
     */
    public static ArrayList<Subscripcion> printTablaSubscripcion() {
        ArrayList<Subscripcion> subsList = new ArrayList<>();
        try {
            ResultSet rs = getTablaSubscripcion(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int id = rs.getInt(DB_SUBSCRIPCION_ID);
                String tipo = rs.getString(DB_SUBSCRIPCION_TIPO);
                double precio = rs.getDouble(DB_SUBSCRIPCION_PRECIO);
                String descripcion = rs.getString(DB_SUBSCRIPCION_DESCRIPCION);

                subsList.add(new Subscripcion(id, tipo, precio, descripcion));
            }
            rs.close();
            return subsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //////////////////////////////////////////////////
    // MÉTODOS DE UNA SOLA SUBSCRIPCION
    //////////////////////////////////////////////////

    /**
     * Solicita a la BD la subscripción con el ID indicado
     *
     * @param id ID de la subscripción
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getSubscripcion(int id) {
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_SUBSCRIPCION_SELECT + " WHERE " + DB_SUBSCRIPCION_ID + "='" + id + "';";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.first()) {
                return null;
            }

            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Comprueba si en la BD existe la subscripción con el ID indicado
     *
     * @param id ID de la subscripción
     * @return verdadero si existe, falso en caso contrario
     */
    public static boolean existsSubscripcion(int id) {
        try {
            ResultSet rs = getSubscripcion(id);

            if (rs == null) {
                return false;
            }

            if (!rs.first()) {
                rs.close();
                return false;
            }

            rs.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Imprime los datos de la subscripción con el ID indicado
     *
     * @param id ID de la subscripción
     */
    public static void printSubscripcion(int id) {
        try {
            ResultSet rs = getSubscripcion(id);
            if (rs == null || !rs.first()) {
                System.out.println("Subscripción con ID " + id + " NO EXISTE");
                return;
            }

            String tipo = rs.getString(DB_SUBSCRIPCION_TIPO);
            double precio = rs.getDouble(DB_SUBSCRIPCION_PRECIO);

            System.out.println(id + "\t" + tipo + "\t" + precio);

        } catch (SQLException ex) {
            System.out.println("Error al solicitar la subscripción con ID " + id);
            ex.printStackTrace();
        }
    }

    /**
     * Solicita a la BD insertar un nuevo registro de subscripción
     *
     * @param tipo   tipo de subscripción
     * @param precio precio de la subscripción
     * @return verdadero si pudo insertarlo, falso en caso contrario
     */
    public static boolean insertSubscripcion(String tipo, double precio) {
        try {
            System.out.print("Insertando subscripción...");
            ResultSet rs = getTablaSubscripcion(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            rs.moveToInsertRow();
            rs.updateString(DB_SUBSCRIPCION_TIPO, tipo);
            rs.updateDouble(DB_SUBSCRIPCION_PRECIO, precio);

            rs.insertRow();

            rs.close();
            System.out.println("OK!");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Solicita a la BD eliminar una subscripción
     *
     * @param id ID de la subscripción a eliminar
     * @return verdadero si pudo eliminarla, falso en caso contrario
     */
    public static boolean deleteSubscripcion(int id) {
        try {
            System.out.print("Eliminando subscripción con ID " + id + "... ");

            ResultSet rs = getSubscripcion(id);

            if (rs == null) {
                System.out.println("ERROR. ResultSet null.");
                return false;
            }

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

    public static boolean deleteSubscripcionesUsuario(int idUsuario) {
        try {
            // Creamos la consulta SQL para eliminar las suscripciones del usuario
            String sql = "DELETE FROM SubscripcionUsuario WHERE idUsuario = " + idUsuario;

            // Creamos y ejecutamos la sentencia SQL
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(sql);

            // Verificamos si se eliminaron filas
            if (rowsAffected > 0) {
                System.out.println("Se eliminaron " + rowsAffected + " suscripciones del usuario " + idUsuario);
                return true;
            } else {
                System.out.println("No se encontraron suscripciones del usuario " + idUsuario);
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean existsUsuarioEnSubscripcion(int idUsuario) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM SubscripcionUsuario WHERE idUsuario = " + idUsuario;
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

            rs.close();
            return false;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
