package GUI;

import DBManager.DBManagerConexion;
import DBManager.DBManagerDepartamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import static DBManager.DBManagerDepartamento.DB_DEPARTAMENTO_SELECT;

public class Departamentos extends JDialog {
    private JPanel WinDepartamentos;
    private JButton btnReturn;
    private JPanel box_top;
    private JPanel box_botones;
    private JTable tableDepartamentos;
    private JScrollPane scrollDepartamentos;
    private JButton btnAnyadirDepartamento;
    private JButton btnEditarDepartamento;
    private JButton buttonAtras;
    private JButton buttonCancel;

    public Departamentos() {
        setContentPane(WinDepartamentos);
        setModal(true);
        getRootPane().setDefaultButton(btnReturn);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        WinDepartamentos.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnEditarDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarDepartamento dialog = new EditarDepartamento();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        btnAnyadirDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnyadirDepartamento dialog = new AnyadirDepartamento();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        // Obtener los nombres de las columnas de la base de datos
        List<String> columnNames = defineColumnName();

        // Crear el modelo de tabla con los nombres de las columnas
        DefaultTableModel model = new DefaultTableModel();
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }
        try{
        ResultSet rs = DBManagerConexion.getConexion().createStatement().executeQuery(DB_DEPARTAMENTO_SELECT);
        Object[] row = new Object[6];
        while (rs.next()) {
            row[0] = rs.getInt("idDepartamento");
            row[1] = rs.getString("nombre");
            row[2] = rs.getDate("fechaCreacion");
            row[3] = rs.getString("nombreEncargado");
            row[4] = rs.getInt("numTrabajadores");
            row[5] = rs.getInt("numSubDpto");
            model.addRow(row);
        }
    } catch (
    SQLException e) {
        throw new RuntimeException(e);
    }


        tableDepartamentos = new JTable(model);
        scrollDepartamentos.setViewportView(tableDepartamentos);
    }

    //private Object[] defineColumnData() {
    //    return  DBManagerDepartamento.defineColumnData();
    //}

    // Método para obtener los nombres de las columnas desde la base de datos
    private List<String> defineColumnName() {

        return DBManagerDepartamento.defineColumnName();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Departamentos dialog = new Departamentos();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
}

