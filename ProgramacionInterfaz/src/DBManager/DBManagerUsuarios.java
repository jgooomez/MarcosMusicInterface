package DBManager;

import ClasePOJO.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static DBManager.DBManagerConexion.conn;

public class DBManagerUsuarios {
    // Configuración de la tabla usuarios
    private static final String DB_US = "usuario";
    private static final String DB_US_SELECT = "SELECT * FROM " + DB_US;
    private static final String DB_US_ID = "idUsuario";
    private static final String DB_US_NAC= "nacionalidad";
    private static final String DB_US_NOM = "nombre";
    private static final String DB_US_ED = "edad";
    private static final String DB_US_NUMSEG = "numSeguidores";
    private static final String DB_US_FOTO = "fotoPerfil";

    /**
     * Obtiene toda la tabla usuarios de la base de datos
     * @param resultSetType Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */

    public static ResultSet getTablaUsuarios(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_US_SELECT);
            //stmt.close();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene toda la tabla usuarios de la base de datos
     *
     * @return ResultSet (por defecto) con la tabla, null en caso de error
     */
    public static ResultSet getTablaUsuarios() {
        return getTablaUsuarios(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Imprime por pantalla el contenido de la tabla usuarios
     */
    public static void printTablaUsuarios() {
        try {
            ResultSet rs = getTablaUsuarios(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int id = rs.getInt(DB_US_ID);
                String nacionalidad = rs.getString(DB_US_NAC);
                String nombre = rs.getString(DB_US_NOM);
                int edad = rs.getInt(DB_US_ED);
                int numSeguidores = rs.getInt(DB_US_NUMSEG);
                String fotoPerfil = rs.getString(DB_US_FOTO);

                System.out.println(id + "\t" + nacionalidad + "\t" + nombre + "\t" + edad + "\t" + numSeguidores + "\t" + fotoPerfil);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////////////////////////////////
    // MÉTODOS DE UN SOLO Usuario
    //////////////////////////////////////////////////
    ;

    /**
     * Solicita a la BD el usuario con id indicado
     * @param idUsuario id del usuario
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getUsuario(int idUsuario) {
        try {
            // Realizamos la consulta SQL
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_US_SELECT + " WHERE " + DB_US_ID + "='" + idUsuario + "';";
            //System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            //stmt.close();

            // Si no hay primer registro entonces no existe el usuario.
            if (!rs.first()) {
                return null;
            }

            // Todo bien, devolvemos el usuario
            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Comprueba si en la BD existe el usuario con id indicado
     *
     * @param id id del usuario
     * @return verdadero si existe, false en caso contrario
     */
    public static boolean existsUsuario(int id) {
        try {
            // Obtenemos el usuario
            ResultSet rs = getUsuario(id);

            // Si rs es null, se ha producido un error
            if (rs == null) {
                return false;
            }

            // Si no existe primer registro
            if (!rs.first()) {
                rs.close();
                return false;
            }

            // Todo bien, existe el usuario
            rs.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Imprime los datos del usuario con id indicado
     *
     * @param id id del usuario
     */
    public static void printUsuario(int id) {
        String nacionalidad;
        try {
            // Obtenemos el usuario
            ResultSet rs = getUsuario(id);
            if (rs == null || !rs.first()) {
                System.out.println("usuario " + id + " NO EXISTE");
                return;
            }

            int idUsuario = rs.getInt(DB_US_ID);
            nacionalidad = rs.getString(DB_US_NAC);
            String nombre = rs.getString(DB_US_NOM);
            int edad = rs.getInt(DB_US_ED);
            int numSeguidores = rs.getInt(DB_US_NUMSEG);
            String fotoPerfil = rs.getString(DB_US_FOTO);

            System.out.println(idUsuario + "\t" + nacionalidad + "\t" + nombre + "\t" + edad
                    + "\t" + numSeguidores + "\t" + fotoPerfil);

        } catch (SQLException ex) {
            System.out.println("Error al solicitar usuario " + id);
            ex.printStackTrace();
        }
    }

        /**
         * Solicita a la BD insertar un nuevo registro usuario
         *
         * @param nombre nombre del usuario
         * @param nacionalidad nacionalidad del usuario
         * @param edad edad del usuario
         * @param numSeguidores numero de seguidores del usuario
         * @param fotoPerfil ruta d ela foto de perfil del usuario
         * @return verdadero si pudo insertarlo, false en caso contrario
         */
        public static boolean insertUsuario(String nacionalidad, String nombre, int edad, int numSeguidores, String fotoPerfil){
            try {
                // Obtenemos la tabla usuarios
                System.out.print("Insertando usuario " + nombre + "...");
                ResultSet rs = getTablaUsuarios(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

                // Insertamos el nuevo registro
                rs.moveToInsertRow();
                rs.updateString(DB_US_NOM, nombre);
                rs.updateString(DB_US_NAC, nacionalidad);
                rs.updateInt(DB_US_ED, edad);
                rs.updateInt(DB_US_NUMSEG, numSeguidores);
                rs.updateString(DB_US_FOTO, fotoPerfil);

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
     * Solicita a la BD eliminar un usuario
     *
     * @param idUsuario id del usuario a eliminar
     * @return verdadero si pudo eliminarlo, false en caso contrario
     */
    public static boolean deleteUsuario(int idUsuario) {
        try {
            System.out.print("Eliminando usuario " + idUsuario + "... ");

            // Obtenemos el usuario
            ResultSet rs = getUsuario(idUsuario);

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

    public static ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            ResultSet rs = getTablaUsuarios(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            while (rs.next()) {
                int id = rs.getInt(DB_US_ID);
                String nacionalidad = rs.getString(DB_US_NAC);
                String nombre = rs.getString(DB_US_NOM);
                int edad = rs.getInt(DB_US_ED);
                int numSeguidores = rs.getInt(DB_US_NUMSEG);
                String fotoPerfil = rs.getString(DB_US_FOTO);

                Usuario usuario = new Usuario(id, nacionalidad, nombre, edad, numSeguidores);
                usuarios.add(usuario);
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuarios;
    }



}

