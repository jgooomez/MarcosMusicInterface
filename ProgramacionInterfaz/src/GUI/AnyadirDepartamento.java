package GUI;

import ClasePOJO.Departamento;
import DBManager.DBManagerDepartamento;

import javax.swing.*;
import java.awt.event.*;

public class AnyadirDepartamento extends JDialog {
    private JPanel contentPane;
    private JButton btnAnyadir;
    private JButton btnCancelar;
    private JTextField inpNombreDpto;
    private JTextField inpFechaCreacion;
    private JTextField inpJefeDpto;
    private JTextField inpNumSubDpto;
    private JTextField inpNumEmpleados;
    private JLabel txtNumSubDpto;
    private JLabel txtNumEmpleados;
    private JLabel txtNombreEncargado;
    private JLabel txtFechaCreacion;
    private JLabel txtNombreDpto;
    private JPanel box_btns;
    private JPanel box_DatosDpto;

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


    private void newDepartamento() {
        if (inpNombreDpto.getText().isBlank()){

        }
        inpFechaCreacion.getText();
        inpJefeDpto.getText();
        inpNumEmpleados.getText();
        inpNumSubDpto.getText();
        Departamento departamento = new Departamento(
                inpNombreDpto.getText(), inpFechaCreacion.getText(), inpJefeDpto.getText(),
                inpNumEmpleados.getText(), inpNumSubDpto.getText());
        if (DBManagerDepartamento.createDpto(departamento)) {
            JOptionPane.showMessageDialog(null, "Se ha añadido correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
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
}
