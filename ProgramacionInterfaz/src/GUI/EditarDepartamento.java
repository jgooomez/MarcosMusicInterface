package GUI;

import ClasePOJO.Departamento;
import DBManager.DBManagerDepartamento;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class EditarDepartamento extends JDialog {
    private JPanel contentPane;
    private JButton btnModificar;
    private JButton buttonCancel;
    private JComboBox editarDepartamentoComboBox;
    private JTextField idDepartamentoTextField;
    private JTextField nombreDepartamentotextField;
    private JTextField fechaDepartamentoTextField;
    private JTextField jefeDepTextField;
    private JTextField numEmpleadosTextField;
    private JTextField numSubDptoTextField;
    private JLabel numSubDptoLabel;
    private JPanel numEmpleadosDptoLabel;
    private JLabel jefeDptoLabel;
    private JLabel fechaDptoLabel;
    private JLabel nombreDptoLabel;
    private JLabel idDptoLabel;

    public EditarDepartamento() {
        setContentPane(contentPane);
        setModal(true);
        idDepartamentoTextField.setEnabled(false);
        getRootPane().setDefaultButton(btnModificar);
        loadDepartamentos();
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarDepartamento();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        editarDepartamentoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el departamento seleccionado del JComboBox
                String selectedItem = (String) editarDepartamentoComboBox.getSelectedItem();
                if (selectedItem != null) {
                    // Obtener el ID del departamento seleccionado
                    int selectedId = getIdFromSelectedItem(selectedItem);

                    // Obtener los datos del departamento seleccionado
                    Departamento departamento = getDepartamentoById(selectedId);

                    // Actualizar los JTextField con los datos del departamento
                    updateTextFields(departamento);
                }
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
    //Coger el id del item comboBox seleccionado
    private int getIdFromSelectedItem(String selectedItem) {
        // Extraer el ID del departamento seleccionado del texto en el JComboBox
        String[] parts = selectedItem.split(" - ");
        return Integer.parseInt(parts[0]);
    }
    //Saca los datos del departamento por ID con el DBManagerDepartamento
    private Departamento getDepartamentoById(int id) {
        DBManagerDepartamento dbManager = new DBManagerDepartamento();
        return dbManager.getDepartamentoById(id);
    }
    private void updateTextFields(Departamento departamento) {
        // Actualizar los JTextField con los datos del departamento
        idDepartamentoTextField.setText(String.valueOf(departamento.getIdDepartamento()));
        nombreDepartamentotextField.setText(departamento.getNombre());
        fechaDepartamentoTextField.setText(departamento.getFechaCreacion());
        jefeDepTextField.setText(departamento.getNombreEncargado());
        numEmpleadosTextField.setText(String.valueOf(departamento.getNumTrabajadores()));
        numSubDptoTextField.setText(String.valueOf(departamento.getNumSubDpto()));
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    private void modificarDepartamento() {
        // Obtener los datos del departamento a modificar de los JTextField
        String nombreDepartamento;
        String fechaCreacion;
        String nombreEncargado;
        String numTrabajadores;
        String numSubDpto;
        if (nombreDepartamentotextField.getText().isBlank())
        {
            nombreDepartamento = "null";
        }else{
            nombreDepartamento = nombreDepartamentotextField.getText();
        }if (fechaDepartamentoTextField.getText().isBlank()){
             fechaCreacion = "2000-01-01";
        }else {
            fechaCreacion = fechaDepartamentoTextField.getText();
        }if (jefeDepTextField.getText().isBlank()){
            nombreEncargado = "null";
        }else{
            nombreEncargado = jefeDepTextField.getText();
        }if (numEmpleadosTextField.getText().isEmpty()){
            numTrabajadores = "0";
        }else {
            numTrabajadores = numEmpleadosTextField.getText();
        }if (numSubDptoTextField.getText().isBlank()){
            numSubDpto = "0";
        }else{
            numSubDpto = numSubDptoTextField.getText();
        }
        int idDepartamento = Integer.parseInt(idDepartamentoTextField.getText());





        // Crear un objeto Departamento con los datos actualizados
        Departamento departamento = new Departamento(idDepartamento, nombreDepartamento, fechaCreacion, nombreEncargado, numTrabajadores, numSubDpto);

        // Actualizar el departamento en la base de datos
        DBManagerDepartamento dbManager = new DBManagerDepartamento();
        boolean success = dbManager.actualizarDepartamento(departamento);

        if (success) {
            JOptionPane.showMessageDialog(this, "Departamento modificado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Actualizar la lista de departamentos en el JComboBox
            loadDepartamentos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el departamento", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadDepartamentos() {

        List<Departamento> departamentos = DBManagerDepartamento.obtenerDatosDepartamento();

        // Limpiar el JComboBox
        editarDepartamentoComboBox.removeAllItems();

        // Agregar los departamentos al JComboBox
        for (Departamento departamento : departamentos) {
            editarDepartamentoComboBox.addItem(departamento.getIdDepartamento() + " - " + departamento.getNombre());
        }
    }

}
