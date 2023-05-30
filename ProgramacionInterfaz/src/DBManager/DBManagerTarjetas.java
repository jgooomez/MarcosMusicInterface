package DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DBManager.DBManagerConexion.conn;

public class DBManagerTarjetas {
    // Configuración de la tabla Tarjeta
    private static final String DB_TARJETA = "Tarjeta";
    private static final String DB_TARJETA_SELECT = "SELECT * FROM " + DB_TARJETA;
    private static final String DB_TARJETA_NUMERO = "numeroTarjeta";
    private static final String DB_TARJETA_TELEFONO = "telefono";
    private static final String DB_TARJETA_TIPO = "tipo";
    private static final String DB_TARJETA_NOMBRE = "nombreTitular";
    private static final String DB_TARJETA_CVV = "cvv";
    private static final String DB_TARJETA_CADUCIDAD = "caducidad";
    private static final String DB_TARJETA_IDUSUARIO = "idUsuario";

    /**
     * Obtiene toda la tabla Tarjeta de la base de datos
     *
     * @param resultSetType        Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */
    public static ResultSet getTablaTarjeta(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_TARJETA_SELECT);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene toda la tabla Tarjeta de la base de datos
     *
     * @return ResultSet (por defecto) con la tabla, null en caso de error
     */
    public static ResultSet getTablaTarjeta() {
        return getTablaTarjeta(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Imprime por pantalla el contenido de la tabla Tarjeta
     */
    public static void printTablaTarjeta() {
        try {
            ResultSet rs = getTablaTarjeta(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int numeroTarjeta = rs.getInt(DB_TARJETA_NUMERO);
                int telefono = rs.getInt(DB_TARJETA_TELEFONO);
                String tipo = rs.getString(DB_TARJETA_TIPO);
                String nombreTitular = rs.getString(DB_TARJETA_NOMBRE);
                int cvv = rs.getInt(DB_TARJETA_CVV);
                String caducidad = rs.getString(DB_TARJETA_CADUCIDAD);
                int idUsuario = rs.getInt(DB_TARJETA_IDUSUARIO);

                System.out.println(numeroTarjeta + "\t" + telefono + "\t" + tipo + "\t" + nombreTitular
                        + "\t" + cvv + "\t" + caducidad + "\t" + idUsuario);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////////////////////////////////
    // MÉTODOS DE UNA SOLA TARJETA
    //////////////////////////////////////////////////

    /**
     * Solicita a la BD la tarjeta con el número de tarjeta indicado
     *
     * @param numeroTarjeta número de tarjeta
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getTarjeta(int numeroTarjeta) {
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_TARJETA_SELECT + " WHERE " + DB_TARJETA_NUMERO + "='" + numeroTarjeta + "';";
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
     * Comprueba si en la BD existe la tarjeta con el número de tarjeta indicado
     *
     * @param numeroTarjeta número de tarjeta
     * @return verdadero si existe, falso en caso contrario
     */
    public static boolean existsTarjeta(int numeroTarjeta) {
        try {
            ResultSet rs = getTarjeta(numeroTarjeta);

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
     * Imprime los datos de la tarjeta con el número de tarjeta indicado
     *
     * @param numeroTarjeta número de tarjeta
     */
    public static void printTarjeta(int numeroTarjeta) {
        try {
            ResultSet rs = getTarjeta(numeroTarjeta);
            if (rs == null || !rs.first()) {
                System.out.println("Tarjeta " + numeroTarjeta + " NO EXISTE");
                return;
            }

            int telefono = rs.getInt(DB_TARJETA_TELEFONO);
            String tipo = rs.getString(DB_TARJETA_TIPO);
            String nombreTitular = rs.getString(DB_TARJETA_NOMBRE);
            int cvv = rs.getInt(DB_TARJETA_CVV);
            String caducidad = rs.getString(DB_TARJETA_CADUCIDAD);
            int idUsuario = rs.getInt(DB_TARJETA_IDUSUARIO);

            System.out.println(numeroTarjeta + "\t" + telefono + "\t" + tipo + "\t" + nombreTitular
                    + "\t" + cvv + "\t" + caducidad + "\t" + idUsuario);

        } catch (SQLException ex) {
            System.out.println("Error al solicitar tarjeta " + numeroTarjeta);
            ex.printStackTrace();
        }
    }

    /**
     * Solicita a la BD insertar un nuevo registro de tarjeta
     *
     * @param numeroTarjeta  número de tarjeta
     * @param telefono       teléfono
     * @param tipo           tipo de tarjeta
     * @param nombreTitular  nombre del titular
     * @param cvv            CVV
     * @param caducidad      fecha de caducidad
     * @param idUsuario      id del usuario asociado a la tarjeta
     * @return verdadero si pudo insertarlo, falso en caso contrario
     */
    public static boolean insertTarjeta(String numeroTarjeta, int telefono, String tipo, String nombreTitular, int cvv, String caducidad, int idUsuario) {
        try {
            System.out.print("Insertando tarjeta " + numeroTarjeta + "...");
            ResultSet rs = getTablaTarjeta(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            rs.moveToInsertRow();
            rs.updateString(DB_TARJETA_NUMERO, numeroTarjeta);
            rs.updateInt(DB_TARJETA_TELEFONO, telefono);
            rs.updateString(DB_TARJETA_TIPO, tipo);
            rs.updateString(DB_TARJETA_NOMBRE, nombreTitular);
            rs.updateInt(DB_TARJETA_CVV, cvv);
            rs.updateString(DB_TARJETA_CADUCIDAD, caducidad);
            rs.updateInt(DB_TARJETA_IDUSUARIO, idUsuario);

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
     * Solicita a la BD eliminar una tarjeta
     *
     * @param numeroTarjeta número de tarjeta a eliminar
     * @return verdadero si pudo eliminarla, falso en caso contrario
     */
    public static boolean deleteTarjeta(int numeroTarjeta) {
        try {
            System.out.print("Eliminando tarjeta " + numeroTarjeta + "... ");

            ResultSet rs = getTarjeta(numeroTarjeta);

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
}
