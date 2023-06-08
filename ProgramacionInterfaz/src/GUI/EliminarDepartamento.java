package GUI;

import ClasePOJO.Departamento;
import DBManager.DBManagerDepartamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EliminarDepartamento extends JDialog {
    private JPanel contentPane;
    private JButton btnEliminar;
    private JButton buttonCancel;
    private JComboBox eliminarDepartamentoComboBox;

    public EliminarDepartamento() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnEliminar);
        loadDepartamentos();

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el departamento seleccionado del JComboBox
                String selectedItem = (String) eliminarDepartamentoComboBox.getSelectedItem();
                if (selectedItem != null) {
                    // Obtener el ID del departamento seleccionado
                    int selectedId = getIdFromSelectedItem(selectedItem);

                    // Obtener los datos del departamento seleccionado
                    Departamento departamento = getDepartamentoById(selectedId);

                    if (DBManagerDepartamento.deleteDepartamento(departamento.getIdDepartamento())){
                        JOptionPane.showMessageDialog(btnEliminar, "Departamento eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(btnEliminar, "Error al eliminar el departamento", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
                loadDepartamentos();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    //Coger el id del item comboBox seleccionado
    private int getIdFromSelectedItem(String selectedItem) {
        // Extraer el ID del departamento seleccionado del texto en el JComboBox
        String[] parts = selectedItem.split(" - ");
        return Integer.parseInt(parts[0]);
    }
    private Departamento getDepartamentoById(int id) {
        DBManagerDepartamento dbManager = new DBManagerDepartamento();
        return dbManager.getDepartamentoById(id);
    }
    private void loadDepartamentos() {

        List<Departamento> departamentos = DBManagerDepartamento.obtenerDatosDepartamento(); // Reemplaza por tu lógica para obtener los departamentos

        // Limpiar el JComboBox
        eliminarDepartamentoComboBox.removeAllItems();

        // Agregar los departamentos al JComboBox
        for (Departamento departamento : departamentos) {
            eliminarDepartamentoComboBox.addItem(departamento.getIdDepartamento() + " - " + departamento.getNombre());
        }
    }
    public static void main(String[] args) {
        EliminarDepartamento dialog = new EliminarDepartamento();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
