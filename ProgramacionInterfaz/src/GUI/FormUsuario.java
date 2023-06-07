package GUI;

import DBManager.DBManagerUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class FormUsuario extends JDialog {
    private JPanel winFormUsr;
    private JPanel box_datosUsr;
    private JLabel txtNombre;
    private JLabel txtEdad;
    private JLabel txtNacionalidad;
    private JLabel txtNumSeguidores;
    private JTextField inpNombre;
    private JTextField inpEdad;
    private JTextField inpNacionalidad;
    private JTextField inpNumSeguidores;

    private JButton btnAddUsr;
    private JButton btnCancel;
    private JPanel box_inputsUsr;
    private JPanel box_btns;
    private JPanel box_tittle;
    private JLabel txtTittle;
    private JLabel txtDNI;
    private JTextField inpDNI;

    /**
     * Crea una instancia de la clase FormUsuario.
     * Esta clase representa una ventana de diálogo para agregar usuarios.
     * Inicializa los componentes gráficos, aplica estilos y configura los listeners.
     */
    public FormUsuario() {
        setContentPane(winFormUsr);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(btnAddUsr);
        setListenersBtns();
    }

    /**
     * Aplica estilos a los elementos visuales.
     * Aplica estilos a los botones.
     */
    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JButton> listaBtns = Arrays.asList(btnCancel, btnAddUsr);
        List<JPanel> listaPaneles = Arrays.asList(winFormUsr, box_datosUsr, box_tittle, box_inputsUsr, box_btns);
        List<JLabel> listaTexto = Arrays.asList(txtTittle, txtNacionalidad, txtNombre, txtEdad, txtDNI, txtNumSeguidores);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    /**
     * Configura los listeners de los botones y las opciones de pago.
     */
    private void setListenersBtns() {
        /**
         * Método que tras comprobar que los datos del usuario sean válidos, inserta el usuario en la BBDD.
         */

        btnAddUsr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (compruebaUsuario()) {
                    if (DBManagerUsuarios.insertUsuario(inpNacionalidad.getText(), inpNombre.getText(), Integer.parseInt(inpEdad.getText()), Integer.parseInt(inpNumSeguidores.getText()))) {
                        JOptionPane.showMessageDialog(null, "El insert se realizó correctamente.");

                        JDialog anyadirTarjeta = new AnyadirTarjeta();
                        anyadirTarjeta.setTitle("Vista de usuarios");
                        anyadirTarjeta.setSize(700, 500);
                        anyadirTarjeta.setLocationRelativeTo(null);
                        anyadirTarjeta.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "El insert no se ha podido realizar.", "Insert incorrecto", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
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
        winFormUsr.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Verifica si los campos de usuario son válidos.
     * @return true si los campos son válidos, false en caso contrario.
     */
    public boolean compruebaUsuario() {
        boolean isValid = true;
        String dni = inpDNI.getText();
        dni = dni.replace(" ", "");

        if (inpNacionalidad.getText().length() == 0 || inpNombre.getText().length() == 0
                || inpEdad.getText().length() == 0 || inpNumSeguidores.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser rellenados.", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        } else if (Integer.parseInt(inpEdad.getText()) < 12) {
            JOptionPane.showMessageDialog(null, "El usuario debe ser mayor de 18 años.", "Error", JOptionPane.ERROR_MESSAGE);
            inpEdad.setText("");
            isValid = false;
        }

        return isValid;
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        FormUsuario dialog = new FormUsuario();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
