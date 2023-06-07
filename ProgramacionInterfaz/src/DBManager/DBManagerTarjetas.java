package DBManager;

import ClasePOJO.Tarjeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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
     * @param idUsuario número de tarjeta
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getTarjeta(int idUsuario) {
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String sql = DB_TARJETA_SELECT + " WHERE " + DB_TARJETA_IDUSUARIO + " = " + idUsuario + ";";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                return null;
            }

            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static int getIdUserTarjeta() {
        try {
            int id = 0;
            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT TOP 1 " + DB_TARJETA_IDUSUARIO + " FROM " + DB_TARJETA + " ORDER BY " + DB_TARJETA_IDUSUARIO + " ASC";
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {
                id = rs.getInt(DB_TARJETA_IDUSUARIO);
            }

            return id;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
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
     * @param numeroTarjeta número de tarjeta
     * @param telefono      teléfono
     * @param tipo          tipo de tarjeta
     * @param nombreTitular nombre del titular
     * @param cvv           CVV
     * @param caducidad     fecha de caducidad
     * @return verdadero si pudo insertarlo, falso en caso contrario
     */
    public static boolean insertTarjeta(String numeroTarjeta, int telefono, String tipo, String nombreTitular, int cvv, String caducidad, int idUser) {
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
            rs.updateInt(DB_TARJETA_IDUSUARIO, idUser);

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

    public static ArrayList<Tarjeta> obtenerTarjeta() {
        ArrayList<ClasePOJO.Tarjeta> tarjetas = new ArrayList<>();

        try {
            ResultSet rs = getTablaTarjeta(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            while (rs.next()) {
                String numero = rs.getString(DB_TARJETA_NUMERO);
                String nombre = rs.getString(DB_TARJETA_NOMBRE);
                String telefono = rs.getString(DB_TARJETA_TELEFONO);
                String tipo = rs.getString(DB_TARJETA_TIPO);
                int cvv = rs.getInt(DB_TARJETA_CVV);
                Date caducidad = rs.getDate(DB_TARJETA_CADUCIDAD);
                int idUsuario = rs.getInt(DB_TARJETA_IDUSUARIO);

                ClasePOJO.Tarjeta tarjeta = new ClasePOJO.Tarjeta(numero, telefono, tipo, nombre, cvv, caducidad, idUsuario);
                tarjetas.add(tarjeta);
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tarjetas;
    }

    public static boolean deleteTarjetasUsuario(int idUsuario) {
        try {
            // Creamos la consulta SQL para eliminar las tarjetas del usuario
            String sql = "DELETE FROM Tarjeta WHERE idUsuario = " + idUsuario;

            // Creamos y ejecutamos la sentencia SQL
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(sql);

            // Verificamos si se eliminaron filas
            if (rowsAffected > 0) {
                System.out.println("Se eliminaron " + rowsAffected + " tarjetas del usuario " + idUsuario);
                return true;
            } else {
                System.out.println("No se encontraron tarjetas del usuario " + idUsuario);
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean existsUserInTarjeta(int idUsuario) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM Tarjeta WHERE idUsuario = " + idUsuario;
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
