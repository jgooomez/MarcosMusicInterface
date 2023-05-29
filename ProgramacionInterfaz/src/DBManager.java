import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    // Conexión a la base de datos
    private static Connection conn = null;

    // Configuración de la conexión a la base de datos
    private static final String DB_HOST = "DESKTOP-00LQ1SJ\\SQLEXPRESS";
    private static final String DB_PORT = "1433";
    private static final String DB_NAME = "MARCOSMUSIC";
    private static final String DB_URL = "jdbc:sqlserver://"+DB_HOST+":"+DB_PORT+";databaseName="+DB_NAME+";TrustServerCertificate=True;";
    private static final String DB_USER = "ADMIN";
    private static final String DB_PASS = "ADMIN";
    private static final String DB_SQL_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_SQL_CONN_NO = "ERROR EN LA CONEXIÓN";

    /**
     * Intenta conectar con la base de datos.
     *
     * @return true si pudo conectarse, false en caso contrario
     */
    public static boolean connect() {
        try {
            System.out.print("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("OK!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    /**
     * Cierra la conexión con la base de datos
     */
    public static void close() {
        try {
            System.out.print("Cerrando la conexión...");
            conn.close();
            System.out.println("OK!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connect();
        close();
    }
}
