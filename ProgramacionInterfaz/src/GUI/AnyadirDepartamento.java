package GUI;

import ClasePOJO.Departamento;
import DBManager.DBManagerDepartamento;

import javax.swing.*;
import java.awt.event.*;

public class AnyadirDepartamento extends JDialog {
    private JPanel contentPane;
    private JButton btnAnyadir;
    private JButton btnCancelar;
    private JTextField idDepartamentoTextField;
    private JTextField nombreDptoTextField;
    private JTextField fechaCreacionTextField;
    private JTextField jefeDptoTextField;
    private JTextField numSubDptoTextField;
    private JTextField numEmpleadosTextField;
    private JLabel numSubDptoLabel;
    private JLabel numEmpleadosLabel;
    private JLabel nombreEncargadoLabel;
    private JLabel fechaCreacionLabel;
    private JLabel nombreDptoLabel;
    private JLabel idDptoLabel;

    public AnyadirDepartamento() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnAnyadir);

        btnAnyadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newDepartamento();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
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
    private void newDepartamento(){
        Departamento departamento= new Departamento(Integer.parseInt(idDepartamentoTextField.getText()),
                nombreDptoTextField.getText(), fechaCreacionTextField.getText(), jefeDptoTextField.getText(),
                numEmpleadosTextField.getText(), numSubDptoTextField.getText());
        if (DBManagerDepartamento.createDpto(departamento)){
            JOptionPane.showMessageDialog(null, "Se ha añadido correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrió un error", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        AnyadirDepartamento dialog = new AnyadirDepartamento();
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

}
