package GUI;

import ClasePOJO.Empleado;
import DBManager.DBManagerEmpleado;
import DBManager.DBManagerUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Empleados extends JDialog {
    private JPanel WinEmpleados;
    private JButton btnBuscar;
    private JButton btnReturn;
    private JPanel box_botones;
    private JPanel box_top;
    private JTextField inpIDEmpleado;
    private JTextField inpNacionalidad;
    private JTextField inpNombre;
    private JTextField inpEdad;
    private JTextField inpDepartamento;
    private JTextField inpFechaIncorporacion;
    private JLabel txtDepartamiento;
    private JLabel txtFechaCreacion;
    private JLabel txtEdad;
    private JLabel txtNombre;
    private JLabel txtNacionalidad;
    private JLabel txtIdEmpleado;
    private JLabel icono;
    private JLabel txtTittle;

    public Empleados() {
        setContentPane(WinEmpleados);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(btnBuscar);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Empleado> empleados = DBManagerEmpleado.obtenerEmpleados();

                // Buscar empleado por ID
                Empleado empleadoBuscado = null;

                for (Empleado emple : empleados) {
                    if (emple.getIdEmpleado() == Integer.parseInt(inpIDEmpleado.getText())) {
                        empleadoBuscado = emple;
                        break;
                    }
                }

                if (empleadoBuscado != null) {
                    // Mostrar los datos del usuario
                    inpNacionalidad.setText(empleadoBuscado.getNacionalidad());
                    inpNombre.setText(empleadoBuscado.getNombre());
                    inpEdad.setText(Integer.toString(empleadoBuscado.getEdad()));
                    inpDepartamento.setText(Integer.toString(empleadoBuscado.getIdDepartamento()));
                    inpFechaIncorporacion.setText(empleadoBuscado.getFechaINC());
                } else {
                    System.out.println("No se encontró ningún usuario con el ID: " + inpIDEmpleado);
                }
            }
        });

        btnReturn.addActionListener(new ActionListener() {
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
        WinEmpleados.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JButton> listaBtns = Arrays.asList(btnBuscar, btnReturn);
        MarcosMusic.stylesBtns(listaBtns);
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
        Empleados dialog = new Empleados();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
