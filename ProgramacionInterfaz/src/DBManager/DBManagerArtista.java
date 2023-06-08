package DBManager;

import ClasePOJO.Artista;

import java.sql.*;
import java.util.ArrayList;

import static DBManager.DBManagerConexion.conn;

public class DBManagerArtista {
    /**
     * Crea un arraylist de los Objetos POJO Artista
     * Este método se usa en setArtistaData() para sacar los datos posteriormente en sus FORMS
     * y en loadArtista() para hacer el desplegable
     * @return artista como Arraylist
     */
    public static ArrayList<Artista> obtenerDatosArtista() {
        ArrayList<Artista> artistas = new ArrayList<>();
        DBManagerConexion.connect();
        Artista artista = null;
        try {
            ResultSet rs = getTablaArtista(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            while (true) {
                assert rs != null;
                if (!rs.next()) break;
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String fechaInicio = rs.getString("fechaInicio");
                String nacionalidad = rs.getString("nacionalidad");
                int numPremios = rs.getInt("numPremios");
                String generoMusical = rs.getString("generoMusical");

                artista = new Artista(id, nombre, nacionalidad, fechaInicio, numPremios, generoMusical);
                artistas.add(artista);
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artistas;
    }
    /**
     * Obtiene toda la tabla Artista de la base de datos
     *
     * @param resultSetType        Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */
    public static ResultSet getTablaArtista(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery("SELECT * FROM Artista");
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * Selecciona un departamento proporcionando un id y saca los datos de ese artista
     *
     * @param id le damos el id del artista que queremos buscar
     * @return Instancia de Artista o null en caso de no existir ese artista en la base de datos
     */
    public Artista getArtistaById(int id) {
        String query = "SELECT * FROM Artista WHERE id = ?";
        DBManagerConexion.connect();
        try (Connection conn = DBManagerConexion.getConexion();
             PreparedStatement statement = conn.prepareStatement(query)) {
            System.out.println(id);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int idArtista = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String fechaInicio = rs.getString("fechaInicio");
                String nacionalidad = rs.getString("nacionalidad");
                int numPremios = rs.getInt("numPremios");
                String generoMusical = rs.getString("generoMusical");

                return new Artista(idArtista, nombre, fechaInicio, nacionalidad, numPremios, generoMusical);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no se encuentra el departamento, devuelve null o lanza una excepción según tus necesidades
    }
    /**
     * Solicita a la BD eliminar un ARTISTA
     *
     * @param idArtista ID del ARTISTA a eliminar
     * @return verdadero si pudo eliminarlo, falso en caso contrario
     */
    public static boolean deleteArtista(int idArtista) {
        try {
            DBManagerConexion.connect();
            System.out.print("Eliminando departamento con ID " + idArtista + "... ");

            ResultSet rs = getArtista(idArtista);

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
    /**
     * Solicita a la BD el artista con el ID indicado
     *
     * @param idArtista ID del artista
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getArtista(int idArtista) {
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Artista" + " WHERE " + "id " + "='" + idArtista + "';";
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
}
