package GUI;

import DBManager.DBManagerUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa la interfaz gráfica para agregar usuarios.
 */
public class AgregarUsuario extends JDialog {
    private JPanel winAddUsr;
    private JTextField inpNombre;
    private JTextField inpEdad;
    private JTextField inpNacionalidad;
    private JTextField inpNumSeguidores;
    private JButton btnAddUsr;
    private JButton btnCancel;
    private JLabel tittleAddUsr;
    private JLabel txtNombre;
    private JLabel txtEdad;
    private JLabel txtNacionalidad;
    private JLabel txtNumSeguidores;
    private JPanel box_botones;
    private JPanel box_top;
    private JPanel box_inputs;

    /**
     * Constructor de la clase AgregarUsuario.
     * Inicializa la interfaz gráfica y configura los eventos de los botones.
     */
    public AgregarUsuario() {
        setContentPane(winAddUsr);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(btnAddUsr);
        setListenersBtns();
    }

    /**
     * Configura los estilos visuales de los elementos de la interfaz.
     */
    private void styles() {
        tittleAddUsr.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JButton> listaBtns = Arrays.asList(btnAddUsr, btnCancel);
        MarcosMusic.stylesBtns(listaBtns);
    }

    /**
     * Configura los listeners de los botones.
     */
    private void setListenersBtns() {
        /**
         * Configura el comportamiento del botón "Add User".
         * Comprueba los campos de entrada y realiza la inserción del usuario en la base de datos.
         * Muestra mensajes de éxito o error según el resultado de la operación.
         */
        btnAddUsr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (compruebaUsuario()) {
                    if (DBManagerUsuarios.insertUsuario(inpNacionalidad.getText(), inpNombre.getText(), Integer.parseInt(inpEdad.getText()), Integer.parseInt(inpNumSeguidores.getText()))) {
                        JOptionPane.showMessageDialog(null, "El insert se realizó correctamente.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "El insert no se ha podido realizar.", "Insert incorrecto", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        /**
         * Configura el comportamiento del botón "Cancel".
         * Cierra la ventana actual sin realizar ninguna acción.
         */
        btnCancel.addActionListener(new ActionListener() {
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
                dispose();
            }
        });

        /**
         * Configura el comportamiento al presionar la tecla ESCAPE.
         * Llama al método onCancel().
         */
        winAddUsr.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Comprueba si los campos de entrada son válidos.
     * Muestra mensajes de error si algún campo está vacío o si la edad es menor de 18 años.
     * @return true si los campos son válidos, false de lo contrario.
     */
    public boolean compruebaUsuario() {
        boolean isValid = true;

        if (inpNacionalidad.getText().length() == 0 || inpNombre.getText().length() == 0
                || inpEdad.getText().length() == 0 || inpNumSeguidores.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser rellenados.", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        } else if (Integer.parseInt(inpEdad.getText()) < 18) {
            JOptionPane.showMessageDialog(null, "El usuario debe ser mayor de 18 años.", "Error", JOptionPane.ERROR_MESSAGE);
            inpEdad.setText("");
            isValid = false;
        }

        return isValid;
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
        AgregarUsuario dialog = new AgregarUsuario();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
