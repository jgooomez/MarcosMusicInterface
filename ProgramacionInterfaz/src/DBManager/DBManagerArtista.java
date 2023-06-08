package DBManager;

import ClasePOJO.Artista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBManagerArtista {
    private static final String TB_NAME = "Artista";
    private static final String TB_ID = "id";
    private static final String TB_NOMBRE = "nombre";
    private static final String TB_FECHA_INICIO = "fechaInicio";
    private static final String TB_NACIONALIDAD = "nacionalidad";
    private static final String TB_NUM_PREMIOS = "numPremios";
    private static final String TB_GENERO_MUSICAL = "generoMusical";
    private static final String TB_SELECT = "SELECT * FROM " + TB_NAME;

    public static ArrayList<Artista> getArtistas() {
        ArrayList<Artista> artistas = new ArrayList<>();
        try {
            Statement stmt = DBManagerConexion.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(TB_SELECT);

            while (rs.next()) {
                int idArtista = rs.getInt(TB_ID);
                String nombre = rs.getString(TB_NOMBRE);
                String fechaInicio = rs.getString(TB_FECHA_INICIO);
                String nacionalidad = rs.getString(TB_NACIONALIDAD);
                int numPremios = rs.getInt(TB_NUM_PREMIOS);
                String generoMusical = rs.getString(TB_GENERO_MUSICAL);

                artistas.add(new Artista(idArtista, nombre, fechaInicio, nacionalidad, numPremios, generoMusical));
            }
            return artistas;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean addArtista(Artista artista) {
        try {
            String insertQuery = "INSERT INTO " + TB_NAME + " (" +
                    TB_NOMBRE + ", " +
                    TB_FECHA_INICIO + ", " +
                    TB_NACIONALIDAD + ", " +
                    TB_NUM_PREMIOS + ", " +
                    TB_GENERO_MUSICAL +
                    ") VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = DBManagerConexion.conn.prepareStatement(insertQuery);
            stmt.setString(1, artista.getNombre());
            stmt.setString(2, artista.getFechaInicio());
            stmt.setString(3, artista.getNacionalidad());
            stmt.setInt(4, artista.getNumPremios());
            stmt.setString(5, artista.getGeneroMusical());

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteArtista(int idArtista) {
        try {
            String deleteQuery = "DELETE FROM " + TB_NAME + " WHERE " + TB_ID + " = ?";

            PreparedStatement stmt = DBManagerConexion.conn.prepareStatement(deleteQuery);
            stmt.setInt(1, idArtista);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Artista getArtistaPorId(int id) {
        try {
            String selectQuery = "SELECT * FROM " + TB_NAME + " WHERE " + TB_ID + " = ?";

            PreparedStatement stmt = DBManagerConexion.conn.prepareStatement(selectQuery);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idArtista = rs.getInt(TB_ID);
                String nombreArtista = rs.getString(TB_NOMBRE);
                String fechaInicio = rs.getString(TB_FECHA_INICIO);
                String nacionalidad = rs.getString(TB_NACIONALIDAD);
                int numPremios = rs.getInt(TB_NUM_PREMIOS);
                String generoMusical = rs.getString(TB_GENERO_MUSICAL);

                return new Artista(idArtista, nombreArtista, fechaInicio, nacionalidad, numPremios, generoMusical);
            }

            return null; // No se encontró ningún artista con el id especificado
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
