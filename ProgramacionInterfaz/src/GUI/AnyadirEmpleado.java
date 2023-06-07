package GUI;

import DBManager.DBManagerEmpleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class AnyadirEmpleado extends JDialog {
    private JPanel winAddEmpleado;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JPanel box_inputs;
    private JPanel box_btns;
    private JTextField inpNombre;
    private JTextField inpNacionalidad;
    private JTextField inpEdad;
    private JTextField inpDepartamento;
    private JTextField inpFechaIncorporacion;
    private JLabel txtTittle;
    private JLabel txtNombre;
    private JLabel txtNacionalidad;
    private JLabel txtEdad;
    private JLabel txtDepartamento;
    private JLabel txtFechaIncorp;

    public AnyadirEmpleado() {
        setContentPane(winAddEmpleado);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(btnAceptar);

        /**
         * Configura el comportamiento del botón "Aceptar".
         * Realiza la validación de los campos de entrada y realiza la inserción del empleado en la base de datos.
         * Muestra mensajes de éxito o error según el resultado de la operación.
         */
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = inpNombre.getText();
                int edad = Integer.parseInt(inpEdad.getText());
                int departamento = Integer.parseInt(inpDepartamento.getText());

                if (nombre.length() < 2) {
                    mostrarError("El nombre debe tener tres caracteres mínimo.");
                } else if (edad < 18) {
                    mostrarError("La edad debe ser mayor a 18 años.");
                } else if (departamento < 1 || departamento > 5) {
                    mostrarError("El número de departamento debe estar entre 1 y 5.");
                } else {
                    if (DBManagerEmpleado.insertEmpleado(
                            inpNacionalidad.getText(),
                            nombre,
                            edad,
                            inpFechaIncorporacion.getText(),
                            departamento)) {
                        mostrarMensaje("El empleado se ha insertado correctamente.");
                        dispose();
                    }
                }
            }

            private void mostrarError(String mensaje) {
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            }

            private void mostrarMensaje(String mensaje) {
                JOptionPane.showMessageDialog(null, mensaje);
            }
        });


        /**
         * Configura el comportamiento del botón "Cancelar".
         * Cierra la ventana actual sin realizar ninguna acción.
         */
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        /**
         * Configura el comportamiento al hacer clic en la cruz de cierre.
         * Cierra la ventana actual.
         */
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        /**
         * Configura el comportamiento al presionar la tecla ESCAPE.
         * Llama al método onCancel().
         */
        winAddEmpleado.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        java.util.List<JButton> listaBtns = Arrays.asList(btnAceptar, btnCancelar);
        java.util.List<JPanel> listaPaneles = Arrays.asList(box_btns, box_inputs, winAddEmpleado);
        List<JLabel> listaTexto = Arrays.asList(txtDepartamento, txtEdad, txtNacionalidad, txtNombre, txtFechaIncorp, txtTittle);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    /**
     * Método invocado cuando se presiona el botón "OK".
     */
    private void onOK() {
        dispose();
    }

    /**
     * Método invocado cuando se cancela la operación.
     */
    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        AnyadirEmpleado dialog = new AnyadirEmpleado();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
