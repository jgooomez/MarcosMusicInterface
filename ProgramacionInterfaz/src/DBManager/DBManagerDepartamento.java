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
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
     * Solicita a la BD eliminar un departamento
     *
     * @param idDepartamento ID del departamento a eliminar
     * @return verdadero si pudo eliminarlo, falso en caso contrario
     */
    public static boolean deleteDepartamento(int idDepartamento) {
        try {
            DBManagerConexion.connect();
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

    /**
     * Crea un arraylist de los Objetos POJO Departamento
     * Este metodo se usa en setDepartamentoData para sacar los datos posteriormente en sus FORMS
     * @return departamentos como Arraylist
     */
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

    /**
     * Método para añadir el numero de columnas de una tabla y el nomrbe de cada una
     * @return List con el nombre de columnas
     */
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

    /**
     * Selecciona un departamento proporcionando un id y saca los datos de ese departamento
     * @param id le damos el id del departamentoq que queremos buscar
     * @return Instancia de Departamento o null en caso de no existir ese departamento en la base de datos
     */
    public Departamento getDepartamentoById(int id) {
        String query = "SELECT * FROM departamento WHERE idDepartamento = ?";
        DBManagerConexion.connect();
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

    /**
     *
     * @param departamento Le damos como variable una instancia de la clase POJO Departamento
     * @return devuelve true en caso de actualizar el departamento o false en caso de no actualizarse
     */
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
            CallableStatement statement = DBManagerConexion.getConexion().prepareCall("{CALL CrearDepartamento(?, ?, ?, ?, ?, ?)}");
            statement.setString(1, departamento.getNombre());
            statement.setString(2, departamento.getFechaCreacion());
            statement.setString(3, departamento.getNombreEncargado());
            statement.setInt(4, Integer.parseInt(departamento.getNumTrabajadores()));
            statement.setInt(5, Integer.parseInt(departamento.getNumSubDpto()));
            statement.registerOutParameter(6, Types.INTEGER); // Parámetro de salida para el resultado del procedimiento
            statement.execute();
            int resultado = statement.getInt(6); // Obtiene el valor del parámetro de salida
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
