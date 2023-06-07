package DBManager;

import ClasePOJO.Contenido;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static DBManager.DBManagerConexion.conn;

public class DBManagerContenido {
    // Configuración de la tabla Contenido
    private static final String DB_CN = "Contenido";
    private static final String DB_CN_SELECT = "SELECT * FROM " + DB_CN;
    private static final String DB_CN_COD = "codigo";
    private static final String DB_CN_USR = "idUsuario";
    private static final String DB_CN_TIT = "titulo";
    private static final String DB_CN_GRAB = "lugarGrabacion";
    private static final String DB_CN_VAL = "valoracion";
    private static final String DB_CN_REP = "numeroReproducciones";
    private static final String DB_CN_ALB = "album";
    private static final String DB_CN_ANYO = "anyoLanzamiento";

    /**
     * Obtiene toda la tabla Contenido de la base de datos
     *
     * @param resultSetType        Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */
    public static ResultSet getTablaContenido(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_CN_SELECT);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene toda la tabla Contenido de la base de datos
     *
     * @return ResultSet (por defecto) con la tabla, null en caso de error
     */
    public static ResultSet getTablaContenido() {
        return getTablaContenido(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Imprime por pantalla el contenido de la tabla Contenido
     */
    public static void printTablaContenido() {
        try {
            ResultSet rs = getTablaContenido(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int codigo = rs.getInt(DB_CN_COD);
                int idUsuario = rs.getInt(DB_CN_USR);
                String titulo = rs.getString(DB_CN_TIT);
                String lugarGrabacion = rs.getString(DB_CN_GRAB);
                double valoracion = rs.getDouble(DB_CN_VAL);
                int numeroReproducciones = rs.getInt(DB_CN_REP);
                String album = rs.getString(DB_CN_ALB);
                int anyoLanzamiento = rs.getInt(DB_CN_ANYO);

                System.out.println(codigo + "\t" + idUsuario + "\t" + titulo + "\t" + lugarGrabacion + "\t"
                        + valoracion + "\t" + numeroReproducciones + "\t" + album + "\t" + anyoLanzamiento);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Solicita a la BD el Contenido con el código indicado
     *
     * @param codigo código del Contenido
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getContenido(int codigo) {
        try {
            // Realizamos la consulta SQL
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_CN_SELECT + " WHERE " + DB_CN_COD + "='" + codigo + "';";
            ResultSet rs = stmt.executeQuery(sql);

            // Si no hay primer registro entonces no existe el Contenido.
            if (!rs.first()) {
                return null;
            }

            // Todo bien, devolvemos el Contenido
            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Comprueba si en la BD existe el Contenido con el código indicado
     *
     * @param codigo código del Contenido
     * @return verdadero si existe, false en caso contrario
     */
    public static boolean existsContenido(int codigo) {
        try {
            // Obtenemos el Contenido
            ResultSet rs = getContenido(codigo);

            // Si rs es null, se ha producido un error
            if (rs == null) {
                return false;
            }

            // Si no existe primer registro
            if (!rs.first()) {
                rs.close();
                return false;
            }

            // Todo bien, existe el Contenido
            rs.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Imprime los datos del Contenido con el código indicado
     *
     * @param codigo código del Contenido
     */
    public static void printContenido(int codigo) {
        try {
            // Obtenemos el Contenido
            ResultSet rs = getContenido(codigo);
            if (rs == null || !rs.first()) {
                System.out.println("Contenido " + codigo + " NO EXISTE");
                return;
            }

            int idUsuario = rs.getInt(DB_CN_USR);
            String titulo = rs.getString(DB_CN_TIT);
            String lugarGrabacion = rs.getString(DB_CN_GRAB);
            double valoracion = rs.getDouble(DB_CN_VAL);
            int numeroReproducciones = rs.getInt(DB_CN_REP);
            String album = rs.getString(DB_CN_ALB);
            int anyoLanzamiento = rs.getInt(DB_CN_ANYO);

            System.out.println(codigo + "\t" + idUsuario + "\t" + titulo + "\t" + lugarGrabacion + "\t"
                    + valoracion + "\t" + numeroReproducciones + "\t" + album + "\t" + anyoLanzamiento);

        } catch (SQLException ex) {
            System.out.println("Error al solicitar Contenido " + codigo);
            ex.printStackTrace();
        }
    }

    /**
     * Solicita a la BD insertar un nuevo registro Contenido
     *
     * @param idUsuario           id del Usuario
     * @param titulo              título del Contenido
     * @param lugarGrabacion      lugar de grabación del Contenido
     * @param valoracion          valoración del Contenido
     * @param numeroReproducciones número de reproducciones del Contenido
     * @param album               álbum del Contenido
     * @param anyoLanzamiento     año de lanzamiento del Contenido
     * @return verdadero si pudo insertarlo, false en caso contrario
     */
    public static boolean insertContenido(int idUsuario, String titulo, String lugarGrabacion, double valoracion,
                                          int numeroReproducciones, String album, int anyoLanzamiento) {
        try {
            // Obtenemos la tabla Contenido
            System.out.print("Insertando Contenido " + titulo + "...");
            ResultSet rs = getTablaContenido(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            // Insertamos el nuevo registro
            rs.moveToInsertRow();
            rs.updateInt(DB_CN_USR, idUsuario);
            rs.updateString(DB_CN_TIT, titulo);
            rs.updateString(DB_CN_GRAB, lugarGrabacion);
            rs.updateDouble(DB_CN_VAL, valoracion);
            rs.updateInt(DB_CN_REP, numeroReproducciones);
            rs.updateString(DB_CN_ALB, album);
            rs.updateInt(DB_CN_ANYO, anyoLanzamiento);

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
     * Solicita a la BD eliminar un Contenido
     *
     * @param codigo código del Contenido a eliminar
     * @return verdadero si pudo eliminarlo, false en caso contrario
     */
    public static boolean deleteContenido(int codigo) {
        try {
            System.out.print("Eliminando Contenido " + codigo + "... ");

            // Obtenemos el Contenido
            ResultSet rs = getContenido(codigo);

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

    /**
     * Comprueba si un usuario con el ID indicado existe en la tabla Contenido
     *
     * @param idUsuario ID del usuario a verificar
     * @return verdadero si existe, falso en caso contrario
     */
    public static boolean existsUsuarioEnContenido(int idUsuario) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM Contenido WHERE idUsuario = " + idUsuario;
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

