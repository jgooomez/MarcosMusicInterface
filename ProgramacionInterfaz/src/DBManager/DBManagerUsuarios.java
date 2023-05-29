package DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DBManager.DBManagerConexion.conn;

public class DBManagerUsuarios {
    // Configuración de la tabla usuarios
    private static final String DB_US = "usuarios";
    private static final String DB_US_SELECT = "SELECT * FROM " + DB_US;
    private static final String DB_US_ID = "codigo";
    private static final String DB_US_NOM = "nombre";
    private static final String DB_US_DIR = "direccion";
    private static final String DB_US_CIU = "ciudad";
    private static final String DB_US_TEL = "telefono";
    private static final String DB_US_DIA = "diabetico";
    private static final String DB_US_FECH = "fechaNac";
    private static final String DB_US_TUR = "turno";

    /**
     * Obtiene toda la tabla clientes de la base de datos
     * @param resultSetType Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */

    public static ResultSet getTablaPacientes(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_PAC_SELECT);
            //stmt.close();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
