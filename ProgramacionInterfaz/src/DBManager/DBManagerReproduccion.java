package DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ClasePOJO.Reproduccion;
import DBManager.DBManagerConexion;

import static DBManager.DBManagerConexion.conn;

public class DBManagerReproduccion {
    // Configuración de la tabla reproduccion
    private static final String DB_REP = "Reproduccion";
    private static final String DB_REP_SELECT = "SELECT * FROM " + DB_REP;
    private static final String DB_REP_ID = "id";
    private static final String DB_REP_DUR = "duracion";
    private static final String DB_REP_COD = "codigoContenido";
    private static final String DB_REP_FECHA = "fechaReproduccion";
    private static final String DB_REP_HORA = "hora";
    private static final String DB_REP_VAL = "valoracion";
    private static final String DB_REP_IDUS = "idUsuario";

    /**
     * Obtiene toda la tabla reproduccion de la base de datos
     * @param resultSetType Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */

    public static ResultSet getTablaReproduccion(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = DBManagerConexion.getConexion().createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_REP_SELECT);
            //stmt.close();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene toda la tabla reproduccion de la base de datos
     *
     * @return ResultSet (por defecto) con la tabla, null en caso de error
     */
    public static ResultSet getTablaReproduccion() {
        return getTablaReproduccion(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Imprime por pantalla el contenido de la tabla reproduccion
     */
    public static void printTablaReproduccion() {
        try {
            ResultSet rs = getTablaReproduccion(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int id = rs.getInt(DB_REP_ID);
                int duracion = rs.getInt(DB_REP_DUR);
                int codigoContenido = rs.getInt(DB_REP_COD);
                String fechaReproduccion = rs.getString(DB_REP_FECHA);
                String hora = rs.getString(DB_REP_HORA);
                int valoracion = rs.getInt(DB_REP_VAL);
                int idUsuario = rs.getInt(DB_REP_IDUS);

                System.out.println(id + "\t" + duracion + "\t" + codigoContenido + "\t" + fechaReproduccion + "\t" + hora + "\t" + valoracion + "\t" + idUsuario);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////////////////////////////////
    // MÉTODOS DE UNA SOLA REPRODUCCIÓN
    //////////////////////////////////////////////////

    /**
     * Solicita a la BD la reproducción con el id indicado
     * @param idReproduccion id de la reproducción
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getReproduccion(int idReproduccion) {
        try {
            // Realizamos la consulta SQL
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_REP_SELECT + " WHERE " + DB_REP_ID + "='" + idReproduccion + "';";
            ResultSet rs = stmt.executeQuery(sql);

            // Si no hay primer registro entonces no existe la reproducción.
            if (!rs.first()) {
                return null;
            }

            // Todo bien, devolvemos la reproducción
            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Comprueba si en la BD existe la reproducción con el id indicado
     *
     * @param id id de la reproducción
     * @return verdadero si existe, false en caso contrario
     */
    public static boolean existsReproduccion(int id) {
        try {
            // Obtenemos la reproducción
            ResultSet rs = getReproduccion(id);

            // Si rs es null, se ha producido un error
            if (rs == null) {
                return false;
            }

            // Si no existe primer registro
            if (!rs.first()) {
                rs.close();
                return false;
            }

            // Todo bien, existe la reproducción
            rs.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Imprime los datos de la reproducción con el id indicado
     *
     * @param id id de la reproducción
     */
    public static void printReproduccion(int id) {
        try {
            // Obtenemos la reproducción
            ResultSet rs = getReproduccion(id);
            if (rs == null || !rs.first()) {
                System.out.println("Reproducción " + id + " NO EXISTE");
                return;
            }

            int idReproduccion = rs.getInt(DB_REP_ID);
            int duracion = rs.getInt(DB_REP_DUR);
            int codigoContenido = rs.getInt(DB_REP_COD);
            String fechaReproduccion = rs.getString(DB_REP_FECHA);
            String hora = rs.getString(DB_REP_HORA);
            int valoracion = rs.getInt(DB_REP_VAL);
            int idUsuario = rs.getInt(DB_REP_IDUS);

            System.out.println(idReproduccion + "\t" + duracion + "\t" + codigoContenido + "\t" + fechaReproduccion + "\t" + hora + "\t" + valoracion + "\t" + idUsuario);

        } catch (SQLException ex) {
            System.out.println("Error al solicitar reproducción " + id);
            ex.printStackTrace();
        }
    }

    /**
     * Solicita a la BD insertar un nuevo registro de reproducción
     *
     * @param duracion duración de la reproducción
     * @param codigoContenido código del contenido reproducido
     * @param fechaReproduccion fecha de reproducción
     * @param hora hora de reproducción
     * @param valoracion valoración de la reproducción
     * @param idUsuario id del usuario asociado a la reproducción
     * @return verdadero si pudo insertarlo, false en caso contrario
     */
    public static boolean insertReproduccion(int duracion, int codigoContenido, String fechaReproduccion, String hora, int valoracion, int idUsuario) {
        try {
            // Obtenemos la tabla reproduccion
            System.out.print("Insertando reproducción...");
            ResultSet rs = getTablaReproduccion(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            // Insertamos el nuevo registro
            rs.moveToInsertRow();
            rs.updateInt(DB_REP_DUR, duracion);
            rs.updateInt(DB_REP_COD, codigoContenido);
            rs.updateString(DB_REP_FECHA, fechaReproduccion);
            rs.updateString(DB_REP_HORA, hora);
            rs.updateInt(DB_REP_VAL, valoracion);
            rs.updateInt(DB_REP_IDUS, idUsuario);
            rs.insertRow();
            System.out.println(" OK");
            rs.close();

            return true;
        } catch (SQLException ex) {
            System.out.println(" ERROR");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza los datos de una reproducción existente
     *
     * @param idReproduccion id de la reproducción a actualizar
     * @param duracion duración de la reproducción
     * @param codigoContenido código del contenido reproducido
     * @param fechaReproduccion fecha de reproducción
     * @param hora hora de reproducción
     * @param valoracion valoración de la reproducción
     * @param idUsuario id del usuario asociado a la reproducción
     * @return verdadero si pudo actualizarlo, false en caso contrario
     */
    public static boolean updateReproduccion(int idReproduccion, int duracion, int codigoContenido, String fechaReproduccion, String hora, int valoracion, int idUsuario) {
        try {
            // Obtenemos la reproducción
            ResultSet rs = getReproduccion(idReproduccion);

            // Si rs es null, se ha producido un error
            if (rs == null) {
                return false;
            }

            // Si no existe primer registro
            if (!rs.first()) {
                rs.close();
                return false;
            }

            // Actualizamos los datos de la reproducción
            rs.updateInt(DB_REP_DUR, duracion);
            rs.updateInt(DB_REP_COD, codigoContenido);
            rs.updateString(DB_REP_FECHA, fechaReproduccion);
            rs.updateString(DB_REP_HORA, hora);
            rs.updateInt(DB_REP_VAL, valoracion);
            rs.updateInt(DB_REP_IDUS, idUsuario);
            rs.updateRow();
            rs.close();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina una reproducción de la BD
     *
     * @param idReproduccion id de la reproducción a eliminar
     * @return verdadero si se pudo eliminar, false en caso contrario
     */
    public static boolean deleteReproduccion(int idReproduccion) {
        try {
            // Obtenemos la reproducción
            ResultSet rs = getReproduccion(idReproduccion);

            // Si rs es null, se ha producido un error
            if (rs == null) {
                return false;
            }

            // Si no existe primer registro
            if (!rs.first()) {
                rs.close();
                return false;
            }

            // Eliminamos la reproducción
            rs.deleteRow();
            rs.close();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //////////////////////////////////////////////////
    // MÉTODOS DE VARIAS REPRODUCCIONES
    //////////////////////////////////////////////////

    /**
     * Obtiene todas las reproducciones de un usuario específico
     *
     * @param idUsuario id del usuario
     * @return lista de reproducciones del usuario, null en caso de error
     */
    public static ArrayList<Reproduccion> getReproduccionesUsuario(int idUsuario) {
        try {
            // Realizamos la consulta SQL
            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String sql = DB_REP_SELECT + " WHERE " + DB_REP_IDUS + "='" + idUsuario + "';";
            ResultSet rs = stmt.executeQuery(sql);

            // Creamos la lista de reproducciones
            ArrayList<Reproduccion> reproducciones = new ArrayList<>();

            // Iteramos sobre el resultado y agregamos las reproducciones a la lista
            while (rs.next()) {
                int idReproduccion = rs.getInt(DB_REP_ID);
                int duracion = rs.getInt(DB_REP_DUR);
                int codigoContenido = rs.getInt(DB_REP_COD);
                String fechaReproduccion = rs.getString(DB_REP_FECHA);
                String hora = rs.getString(DB_REP_HORA);
                int valoracion = rs.getInt(DB_REP_VAL);

                Reproduccion reproduccion = new Reproduccion(idReproduccion, duracion, codigoContenido, fechaReproduccion, hora, valoracion, idUsuario);
                reproducciones.add(reproduccion);
            }

            rs.close();
            return reproducciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene todas las reproducciones de la BD
     *
     * @return lista de todas las reproducciones, null en caso de error
     */
    public static ArrayList<Reproduccion> getAllReproducciones() {
        try {
            // Realizamos la consulta SQL
            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(DB_REP_SELECT);

            // Creamos la lista de reproducciones
            ArrayList<Reproduccion> reproducciones = new ArrayList<>();

            // Iteramos sobre el resultado y agregamos las reproducciones a la lista
            while (rs.next()) {
                int idReproduccion = rs.getInt(DB_REP_ID);
                int duracion = rs.getInt(DB_REP_DUR);
                int codigoContenido = rs.getInt(DB_REP_COD);
                String fechaReproduccion = rs.getString(DB_REP_FECHA);
                String hora = rs.getString(DB_REP_HORA);
                int valoracion = rs.getInt(DB_REP_VAL);
                int idUsuario = rs.getInt(DB_REP_IDUS);

                Reproduccion reproduccion = new Reproduccion(idReproduccion, duracion, codigoContenido, fechaReproduccion, hora, valoracion, idUsuario);
                reproducciones.add(reproduccion);
            }

            rs.close();
            return reproducciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Imprime por pantalla todas las reproducciones de un usuario específico
     *
     * @param idUsuario id del usuario
     */
    public static void printReproduccionesUsuario(int idUsuario) {
        ArrayList<Reproduccion> reproducciones = getReproduccionesUsuario(idUsuario);

        if (reproducciones == null) {
            System.out.println("Error al obtener las reproducciones del usuario " + idUsuario);
            return;
        }

        if (reproducciones.isEmpty()) {
            System.out.println("El usuario " + idUsuario + " no tiene reproducciones");
            return;
        }

        System.out.println("Reproducciones del usuario " + idUsuario + ":");
        for (Reproduccion reproduccion : reproducciones) {
            System.out.println(reproduccion.getIdReproduccion() + "\t" + reproduccion.getDuracion() + "\t" + reproduccion.getCodigoContenido() + "\t"
                    + reproduccion.getFechaReproduccion() + "\t" + reproduccion.getHora() + "\t" + reproduccion.getValoracion() + "\t" + reproduccion.getIdUsuario());
        }
    }

    /**
     * Imprime por pantalla todas las reproducciones de la BD
     */
    public static void printAllReproducciones() {
        ArrayList<Reproduccion> reproducciones = getAllReproducciones();

        if (reproducciones == null) {
            System.out.println("Error al obtener las reproducciones");
            return;
        }

        if (reproducciones.isEmpty()) {
            System.out.println("No hay reproducciones en la BD");
            return;
        }

        System.out.println("Reproducciones en la BD:");
        for (Reproduccion reproduccion : reproducciones) {
            System.out.println(reproduccion.getIdReproduccion() + "\t" + reproduccion.getDuracion() + "\t" + reproduccion.getCodigoContenido() + "\t"
                    + reproduccion.getFechaReproduccion() + "\t" + reproduccion.getHora() + "\t" + reproduccion.getValoracion() + "\t" + reproduccion.getIdUsuario());
        }
    }
    public static boolean deleteReproduccionesUsuario(int idUsuario) {
        try {
            // Creamos la consulta SQL para eliminar las reproducciones del usuario
            String sql = "DELETE FROM Reproduccion WHERE idUsuario = " + idUsuario;

            // Creamos y ejecutamos la sentencia SQL
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(sql);

            // Verificamos si se eliminaron filas
            if (rowsAffected > 0) {
                System.out.println("Se eliminaron " + rowsAffected + " reproducciones del usuario " + idUsuario);
                return true;
            } else {
                System.out.println("No se encontraron reproducciones del usuario " + idUsuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
