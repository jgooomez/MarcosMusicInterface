package GUI;

import DBManager.DBManagerConexion;
import DBManager.DBManagerDepartamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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
    private JButton btnActualizar;
    private JLabel txtTittle;
    private JLabel icono;
    private JPanel box_content;
    private JButton buttonAtras;
    private JButton buttonCancel;

    public Departamentos() {
        setContentPane(WinDepartamentos);
        styles();
        setModal(true);
        setSize(500,500);
        getRootPane().setDefaultButton(btnReturn);
        defineDataRow();
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
                onCancel();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defineDataRow();
            }
        });
        btnEditarDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarDepartamento dialog = new EditarDepartamento();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        btnAnyadirDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnyadirDepartamento dialog = new AnyadirDepartamento();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JLabel> listaTexto = Arrays.asList(txtTittle);
        List<JButton> listaBtns = Arrays.asList(btnActualizar, btnReturn, btnAnyadirDepartamento, btnEditarDepartamento);
        List<JPanel> listaPaneles = Arrays.asList(WinDepartamentos, box_botones, box_top, box_content);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    private void defineDataRow(){
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

    // Método para obtener los nombres de las columnas desde la base de datos
    private List<String> defineColumnName() {

        return DBManagerDepartamento.defineColumnName();
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        this.setVisible(false);
        MarcosMusic.frame.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        DBManagerConexion.connect();
        Departamentos dialog = new Departamentos();
        dialog.setSize(600,600);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
}

