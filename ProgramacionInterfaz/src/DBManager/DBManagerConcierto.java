package DBManager;

import ClasePOJO.Concierto;

import java.sql.*;
import java.util.ArrayList;

public class DBManagerConcierto {
    private static final String TB_NAME = "Concierto";
    private static final String TB_CODIGO = "codigo";
    private static final String TB_LUGAR = "lugar";
    private static final String TB_FECHA = "fecha";
    private static final String TB_CIUDAD = "ciudad";
    private static final String TB_PAIS = "pais";
    private static final String TB_CAPACIDAD = "capacidad";
    private static final String TB_DINERO_RECAUDADO = "dineroRecaudado";
    private static final String TB_SELECT = "SELECT * FROM " + TB_NAME;

    public static ArrayList<Concierto> getConciertos() {
        ArrayList<Concierto> conciertos = new ArrayList<>();
        try {
            Statement stmt = DBManagerConexion.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(TB_SELECT);

            while (rs.next()) {
                int codigo = rs.getInt(TB_CODIGO);
                String lugar = rs.getString(TB_LUGAR);
                Date fecha = rs.getDate(TB_FECHA);
                String ciudad = rs.getString(TB_CIUDAD);
                String pais = rs.getString(TB_PAIS);
                int capacidad = rs.getInt(TB_CAPACIDAD);
                double dineroRecaudado = rs.getDouble(TB_DINERO_RECAUDADO);

                conciertos.add(new Concierto(codigo, lugar, fecha, ciudad, pais, capacidad, dineroRecaudado));
            }
            return conciertos;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean addConcierto(Concierto concierto) {
        try {
            String insertQuery = "INSERT INTO " + TB_NAME + " (" +
                    TB_LUGAR + ", " +
                    TB_FECHA + ", " +
                    TB_CIUDAD + ", " +
                    TB_PAIS + ", " +
                    TB_CAPACIDAD + ", " +
                    TB_DINERO_RECAUDADO +
                    ") VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = DBManagerConexion.conn.prepareStatement(insertQuery);
            stmt.setString(1, concierto.getLugar());
            stmt.setDate(2, concierto.getFecha());
            stmt.setString(3, concierto.getCiudad());
            stmt.setString(4, concierto.getPais());
            stmt.setInt(5, concierto.getCapacidad());
            stmt.setDouble(6, concierto.getDineroRecaudado());

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteConcierto(int codigo) {
        try {
            String deleteQuery = "DELETE FROM " + TB_NAME + " WHERE " + TB_CODIGO + " = ?";

            PreparedStatement stmt = DBManagerConexion.conn.prepareStatement(deleteQuery);
            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static Concierto buscarConciertoPorCodigo(int codigo) {
        try {
            String selectQuery = "SELECT * FROM " + TB_NAME + " WHERE " + TB_CODIGO + " = ?";

            PreparedStatement stmt = DBManagerConexion.conn.prepareStatement(selectQuery);
            stmt.setInt(1, codigo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String lugar = rs.getString(TB_LUGAR);
                Date fecha = rs.getDate(TB_FECHA);
                String ciudad = rs.getString(TB_CIUDAD);
                String pais = rs.getString(TB_PAIS);
                int capacidad = rs.getInt(TB_CAPACIDAD);
                double dineroRecaudado = rs.getDouble(TB_DINERO_RECAUDADO);

                return new Concierto(codigo, lugar, fecha, ciudad, pais, capacidad, dineroRecaudado);
            } else {
                return null; // No se encontró ningún concierto con el código especificado
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
