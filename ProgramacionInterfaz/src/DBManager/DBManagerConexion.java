package DBManager;

import java.sql.*;

public class DBManagerConexion {
    public static Connection conn = null;

    public static Connection getConexion() {
        Connection FinalConection = conn;
        return FinalConection;
    }

    //CONEXIÓN
    private static final String DB_HOST = "192.168.18.177";
    //private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "1433";
    private static final String DB_NAME = "MARCOSMUSIC";
    private static final String DB_URL = "jdbc:sqlserver://" + DB_HOST + ":" + DB_PORT + ";encrypt=false;databaseName=" + DB_NAME + ";TrustServerCertificate=True;";
    private static final String DB_USER = "ADMIN";
    private static final String DB_PASS = "ADMIN";
    private static final String DB_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_CONN_FAIL = "ERROR DE CONEXIÓN";


    //METODOS DE CONEXIÓN
    public static boolean loadDriver() {
        try {
            System.out.println("Cargando Driver...");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            System.out.println("OK!");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean connect() {
        try {
            System.out.println("Conectando con la base de datos...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("OK!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean isConnected() {
        try {
            if (conn != null && conn.isValid(0)) {
                System.out.println(DB_CONN_OK);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(DB_CONN_FAIL);
            ex.printStackTrace();
            return false;
        }
    }

    public static void close() {
        try {
            System.out.println("Cerrando la conexión...");
            conn.close();
            System.out.println("OK!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

