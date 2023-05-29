package DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DBManager.DBManagerConexion.conn;

public class DBManagerCuentaPrincipal {
    // Configuración de la tabla CuentaPrincipal
    private static final String DB_CUENTA_PRINCIPAL = "CuentaPrincipal";
    private static final String DB_CUENTA_PRINCIPAL_SELECT = "SELECT * FROM " + DB_CUENTA_PRINCIPAL;
    private static final String DB_CUENTA_PRINCIPAL_TELEFONO = "telefono";
    private static final String DB_CUENTA_PRINCIPAL_METODO_PAGO = "metodoPago";
    private static final String DB_CUENTA_PRINCIPAL_ID_USUARIO = "idUsuario";

    /**
     * Obtiene toda la tabla CuentaPrincipal de la base de datos
     *
     * @param resultSetType        Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */
    public static ResultSet getTablaCuentaPrincipal(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_CUENTA_PRINCIPAL_SELECT);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene toda la tabla CuentaPrincipal de la base de datos
     *
     * @return ResultSet (por defecto) con la tabla, null en caso de error
     */
    public static ResultSet getTablaCuentaPrincipal() {
        return getTablaCuentaPrincipal(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Imprime por pantalla el contenido de la tabla CuentaPrincipal
     */
    public static void printTablaCuentaPrincipal() {
        try {
            ResultSet rs = getTablaCuentaPrincipal(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int telefono = rs.getInt(DB_CUENTA_PRINCIPAL_TELEFONO);
                String metodoPago = rs.getString(DB_CUENTA_PRINCIPAL_METODO_PAGO);
                int idUsuario = rs.getInt(DB_CUENTA_PRINCIPAL_ID_USUARIO);

                System.out.println(telefono + "\t" + metodoPago + "\t" + idUsuario);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////////////////////////////////
    // MÉTODOS DE UNA SOLA CUENTA PRINCIPAL
    //////////////////////////////////////////////////

    /**
     * Solicita a la BD la cuenta principal con el teléfono indicado
     *
     * @param telefono Teléfono de la cuenta principal
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getCuentaPrincipal(int telefono) {
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_CUENTA_PRINCIPAL_SELECT + " WHERE " + DB_CUENTA_PRINCIPAL_TELEFONO + "='" + telefono + "';";
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
     * Comprueba si en la BD existe la cuenta principal con el teléfono indicado
     *
     * @param telefono Teléfono de la cuenta principal
     * @return verdadero si existe, falso en caso contrario
     */
    public static boolean existsCuentaPrincipal(int telefono) {
        try {
            ResultSet rs = getCuentaPrincipal(telefono);

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
     * Imprime los datos de la cuenta principal con el teléfono indicado
     *
     * @param telefono Teléfono de la cuenta principal
     */
    public static void printCuentaPrincipal(int telefono) {
        try {
            ResultSet rs = getCuentaPrincipal(telefono);
            if (rs == null || !rs.first()) {
                System.out.println("Cuenta principal con teléfono " + telefono + " NO EXISTE");
                return;
            }

            String metodoPago = rs.getString(DB_CUENTA_PRINCIPAL_METODO_PAGO);
            int idUsuario = rs.getInt(DB_CUENTA_PRINCIPAL_ID_USUARIO);

            System.out.println(telefono + "\t" + metodoPago + "\t" + idUsuario);

        } catch (SQLException ex) {
            System.out.println("Error al solicitar la cuenta principal con teléfono " + telefono);
            ex.printStackTrace();
        }
    }

    /**
     * Solicita a la BD insertar una nueva cuenta principal
     *
     * @param telefono    Teléfono de la cuenta principal
     * @param metodoPago  Método de pago de la cuenta principal
     * @param idUsuario   ID del usuario asociado a la cuenta principal
     * @return verdadero si pudo insertarla, falso en caso contrario
     */
    public static boolean insertCuentaPrincipal(int telefono, String metodoPago, int idUsuario) {
        try {
            System.out.print("Insertando cuenta principal...");
            ResultSet rs = getTablaCuentaPrincipal(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            rs.moveToInsertRow();
            rs.updateInt(DB_CUENTA_PRINCIPAL_TELEFONO, telefono);
            rs.updateString(DB_CUENTA_PRINCIPAL_METODO_PAGO, metodoPago);
            rs.updateInt(DB_CUENTA_PRINCIPAL_ID_USUARIO, idUsuario);

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
     * Solicita a la BD eliminar una cuenta principal
     *
     * @param telefono Teléfono de la cuenta principal a eliminar
     * @return verdadero si pudo eliminarla, falso en caso contrario
     */
    public static boolean deleteCuentaPrincipal(int telefono) {
        try {
            System.out.print("Eliminando cuenta principal con teléfono " + telefono + "... ");

            ResultSet rs = getCuentaPrincipal(telefono);

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
