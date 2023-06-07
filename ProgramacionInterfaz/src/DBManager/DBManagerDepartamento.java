package DBManager;

import ClasePOJO.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static ClasePOJO.Departamento.*;
import static DBManager.DBManagerConexion.conn;

public class DBManagerDepartamento {
    // Configuración de la tabla Departamento
    private static final String DB_DEPARTAMENTO = "Departamento";
    public static final String DB_DEPARTAMENTO_SELECT = "SELECT * FROM " + DB_DEPARTAMENTO;
    private static final String DB_DEPARTAMENTO_ID = "idDepartamento";
    private static final String DB_DEPARTAMENTO_NOMBRE = "nombre";
    private static final String DB_DEPARTAMENTO_FECHA_CREACION = "fechaCreacion";
    private static final String DB_DEPARTAMENTO_NOMBRE_ENCARGADO = "NombreEncargado";
    private static final String DB_DEPARTAMENTO_NUM_TRABAJADORES = "numTrabajadores";
    private static final String DB_DEPARTAMENTO_NUM_SUBDPTO = "numSubDpto";

    /**
     * Obtiene toda la tabla Departamento de la base de datos
     *
     * @param resultSetType        Tipo de ResultSet
     * @param resultSetConcurrency Concurrencia del ResultSet
     * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
     */
    public static ResultSet getTablaDepartamento(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_DEPARTAMENTO_SELECT);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene toda la tabla Departamento de la base de datos
     *
     * @return ResultSet (por defecto) con la tabla, null en caso de error
     */
    public static ResultSet getTablaDepartamento() {
        return getTablaDepartamento(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * Imprime por pantalla el contenido de la tabla Departamento
     */
    public static void printTablaDepartamento() {
        try {
            ResultSet rs = getTablaDepartamento(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int idDepartamento = rs.getInt(DB_DEPARTAMENTO_ID);
                String nombre = rs.getString(DB_DEPARTAMENTO_NOMBRE);
                String fechaCreacion = rs.getString(DB_DEPARTAMENTO_FECHA_CREACION);
                String nombreEncargado = rs.getString(DB_DEPARTAMENTO_NOMBRE_ENCARGADO);
                int numTrabajadores = rs.getInt(DB_DEPARTAMENTO_NUM_TRABAJADORES);
                int numSubDpto = rs.getInt(DB_DEPARTAMENTO_NUM_SUBDPTO);

                System.out.println(idDepartamento + "\t" + nombre + "\t" + fechaCreacion + "\t" + nombreEncargado + "\t" + numTrabajadores + "\t" + numSubDpto);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////////////////////////////////
    // MÉTODOS DE UN SOLO DEPARTAMENTO
    //////////////////////////////////////////////////

    /**
     * Solicita a la BD el departamento con el ID indicado
     *
     * @param idDepartamento ID del departamento
     * @return ResultSet con el resultado de la consulta, null en caso de error
     */
    public static ResultSet getDepartamento(int idDepartamento) {
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_DEPARTAMENTO_SELECT + " WHERE " + DB_DEPARTAMENTO_ID + "='" + idDepartamento + "';";
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

    /**
     * Comprueba si en la BD existe el departamento con el ID indicado
     *
     * @param idDepartamento ID del departamento
     * @return verdadero si existe, falso en caso contrario
     */
    public static boolean existsDepartamento(int idDepartamento) {
        try {
            ResultSet rs = getDepartamento(idDepartamento);

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
     * Imprime los datos del departamento con el ID indicado
     *
     * @param idDepartamento ID del departamento
     */
    public static void printDepartamento(int idDepartamento) {
        try {
            ResultSet rs = getDepartamento(idDepartamento);
            if (rs == null || !rs.first()) {
                System.out.println("Departamento con ID " + idDepartamento + " NO EXISTE");
                return;
            }

            String nombre = rs.getString(DB_DEPARTAMENTO_NOMBRE);
            String fechaCreacion = rs.getString(DB_DEPARTAMENTO_FECHA_CREACION);
            String nombreEncargado = rs.getString(DB_DEPARTAMENTO_NOMBRE_ENCARGADO);
            int numTrabajadores = rs.getInt(DB_DEPARTAMENTO_NUM_TRABAJADORES);
            int numSubDpto = rs.getInt(DB_DEPARTAMENTO_NUM_SUBDPTO);

            System.out.println(idDepartamento + "\t" + nombre + "\t" + fechaCreacion + "\t" + nombreEncargado + "\t" + numTrabajadores + "\t" + numSubDpto);

        } catch (SQLException ex) {
            System.out.println("Error al solicitar el departamento con ID " + idDepartamento);
            ex.printStackTrace();
        }
    }

    /**
     * Solicita a la BD insertar un nuevo registro de departamento
     *
     * @param idDepartamento  ID del departamento
     * @param nombre          Nombre del departamento
     * @param fechaCreacion   Fecha de creación del departamento
     * @param nombreEncargado Nombre del encargado del departamento
     * @param numTrabajadores Número de trabajadores del departamento
     * @param numSubDpto      Número de subdepartamentos del departamento
     * @return verdadero si pudo insertarlo, falso en caso contrario
     */
    public static boolean insertDepartamento(int idDepartamento, String nombre, String fechaCreacion, String nombreEncargado, int numTrabajadores, int numSubDpto) {
        try {
            System.out.print("Insertando departamento...");
            ResultSet rs = getTablaDepartamento(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            rs.moveToInsertRow();
            rs.updateInt(DB_DEPARTAMENTO_ID, idDepartamento);
            rs.updateString(DB_DEPARTAMENTO_NOMBRE, nombre);
            rs.updateString(DB_DEPARTAMENTO_FECHA_CREACION, fechaCreacion);
            rs.updateString(DB_DEPARTAMENTO_NOMBRE_ENCARGADO, nombreEncargado);
            rs.updateInt(DB_DEPARTAMENTO_NUM_TRABAJADORES, numTrabajadores);
            rs.updateInt(DB_DEPARTAMENTO_NUM_SUBDPTO, numSubDpto);

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
     * Solicita a la BD eliminar un departamento
     *
     * @param idDepartamento ID del departamento a eliminar
     * @return verdadero si pudo eliminarlo, falso en caso contrario
     */
    public static boolean deleteDepartamento(int idDepartamento) {
        try {
            System.out.print("Eliminando departamento con ID " + idDepartamento + "... ");

            ResultSet rs = getDepartamento(idDepartamento);

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

    //Crea un arraylist de los Objetos POJO Departamento
    //Este metodo se usa en setDepartamentoData para sacar los datos posteriormente en sus FORMS
    public static ArrayList<Departamento> obtenerDatosDepartamento() {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        DBManagerConexion.connect();
        try {
            ResultSet rs = getTablaDepartamento(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            while (true) {
                assert rs != null;
                if (!rs.next()) break;
                int id = rs.getInt(DB_DEPARTAMENTO_ID);
                String nombreDto = rs.getString(DB_DEPARTAMENTO_NOMBRE);
                String nombreEncargado = rs.getString(DB_DEPARTAMENTO_NOMBRE_ENCARGADO);
                String fechaCreacion = rs.getString(DB_DEPARTAMENTO_FECHA_CREACION);
                String numTrabajadores = rs.getString(DB_DEPARTAMENTO_NUM_TRABAJADORES);
                String subDto = rs.getString(DB_DEPARTAMENTO_NUM_SUBDPTO);

                Departamento departamento = new Departamento(id, nombreDto, fechaCreacion, nombreEncargado, numTrabajadores, subDto);
                departamentos.add(departamento);
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return departamentos;
    }

    //Método para añadir el numero de columnas de una tabla y el nomrbe de cada una
    public static List<String> defineColumnName() {
        DBManagerConexion.connect();
        List<String> columnNames = new ArrayList<>();
        try {
            ResultSet rs = DBManagerConexion.getConexion().createStatement().executeQuery(DB_DEPARTAMENTO_SELECT);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numCol = rsMetaData.getColumnCount();
            System.out.println(numCol);
            for (int col = 1; col <= numCol; col++) {
                columnNames.add(rsMetaData.getColumnName(col));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return columnNames;
    }

    //selecciona un departamento proporcionando un id y saca los datos de ese departamento
    public Departamento getDepartamentoById(int id) {
        String query = "SELECT * FROM departamento WHERE idDepartamento = ?";
        try (Connection conn = DBManagerConexion.getConexion();
             PreparedStatement statement = conn.prepareStatement(query)) {
            System.out.println(id);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int idDepartamento = rs.getInt("idDepartamento");
                String nombre = rs.getString("nombre");
                String fechaCreacion = rs.getString("fechaCreacion");
                String nombreEncargado = rs.getString("nombreEncargado");
                int numTrabajadores = rs.getInt("numTrabajadores");
                int numSubDpto = rs.getInt("numSubDpto");

                return new Departamento(idDepartamento, nombre, fechaCreacion, nombreEncargado, String.valueOf(numTrabajadores), String.valueOf(numSubDpto));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no se encuentra el departamento, devuelve null o lanza una excepción según tus necesidades
    }

    public boolean actualizarDepartamento(Departamento departamento) {
        DBManagerConexion.connect();
        boolean success = false;
        PreparedStatement statement = null;

        try {
            // Preparar la sentencia SQL para actualizar el departamento
            String query = "UPDATE departamento SET nombre = ?, fechaCreacion = ?, NombreEncargado = ?, numTrabajadores = ?, numSubdpto = ? WHERE idDepartamento = ?";
            statement = DBManagerConexion.getConexion().prepareStatement(query);
            statement.setString(1, departamento.getNombre());
            statement.setString(2, departamento.getFechaCreacion());
            statement.setString(3, departamento.getNombreEncargado());
            statement.setInt(4, Integer.parseInt(departamento.getNumTrabajadores()));
            statement.setInt(5, Integer.parseInt(departamento.getNumSubDpto()));
            statement.setInt(6, departamento.getIdDepartamento());

            // Ejecutar la sentencia SQL
            int rowsAffected = statement.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el PreparedStatement en caso de error o éxito
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return success;
    }

    public static boolean createDpto(Departamento departamento) {
        try {
            CallableStatement statement = DBManagerConexion.getConexion().prepareCall("{CALL CrearDepartamento(?, ?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, departamento.getIdDepartamento());
            statement.setString(2, departamento.getNombre());
            statement.setString(3, departamento.getFechaCreacion());
            statement.setString(4, departamento.getNombreEncargado());
            statement.setInt(5, Integer.parseInt(departamento.getNumTrabajadores()));
            statement.setInt(6, Integer.parseInt(departamento.getNumSubDpto()));
            statement.registerOutParameter(7, Types.INTEGER); // Parámetro de salida para el resultado del procedimiento
            statement.execute();
            int resultado = statement.getInt(7); // Obtiene el valor del parámetro de salida
            if (resultado == 0) {
                return true;
            } else if (resultado == 1) {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}
