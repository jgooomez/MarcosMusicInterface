package GUI;

import DBManager.DBManagerEmpleado;

import javax.swing.*;
import java.awt.event.*;

public class AnyadirEmpleado extends JDialog {
    private JPanel panelPrincipal;
    private JButton buttonAceptar;
    private JButton buttonCancelar;
    private JPanel panelCampos;
    private JPanel panelBotones;
    private JTextField inpNombre;
    private JTextField inpNacionalidad;
    private JTextField inpEdad;
    private JTextField inpDepartamento;
    private JTextField inpFechaIncorporacion;

    public AnyadirEmpleado() {
        setContentPane(panelPrincipal);
        setModal(true);
        getRootPane().setDefaultButton(buttonAceptar);

        buttonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (inpNombre.getText().length() < 2) {
                    JOptionPane.showMessageDialog(null, "El nombre debe tener tres caracteres mínimo.","Error", JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(inpEdad.getText()) < 18) {
                    JOptionPane.showMessageDialog(null, "La edad debe ser mayor a 18 años.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(inpDepartamento .getText())< 0 || Integer.parseInt(inpDepartamento.getText()) > 5) {
                    JOptionPane.showMessageDialog(null, "El numero de departamento deb estar entre 1 y 5.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (DBManagerEmpleado.insertEmpleado(inpNacionalidad.getText(), inpNombre.getText(), Integer.parseInt(inpEdad.getText()), inpFechaIncorporacion.getText(), Integer.parseInt(inpDepartamento.getText()))) {
                        JOptionPane.showMessageDialog(null, "El empleado se ha insertado correctamente.");
                        dispose();
                    }
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
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
        panelPrincipal.registerKeyboardAction(new ActionListener() {
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

    public static void main(String[] args) {
        AnyadirEmpleado dialog = new AnyadirEmpleado();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
