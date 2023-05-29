package GUI;

import javax.swing.*;
import java.awt.event.*;

public class AgregarUsuario extends JDialog {
    private JPanel winAddUsr;
    private JTextField inpNombre;
    private JTextField inpEdad;
    private JTextField inpNacionalidad;
    private JTextField inpNumSeguidores;
    private JButton btnAddUsr;
    private JButton btnCancel;
    private JLabel txtNombre;
    private JLabel txtEdad;
    private JLabel txtNacionalidad;
    private JLabel txtNumSeguidores;
    private JPanel box_botones;
    private JPanel box_top;
    private JPanel box_inputs;
    private JLabel tittleAddUser;

    public AgregarUsuario() {
        setContentPane(winAddUsr);
        setModal(true);
        getRootPane().setDefaultButton(btnAddUsr);
        setListenersBtns();
    }

    private void styles() {
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        tittleAddUser.setFont(MarcosMusic.getFontTitle());

        btnAddUsr.setFocusable(false);
        btnAddUsr.setBackground(MarcosMusic.getBtnColor());
        btnAddUsr.setCursor(cursor);

        btnCancel.setFocusable(false);
        btnCancel.setBackground(MarcosMusic.getBtnColor());
        btnCancel.setCursor(cursor);
    }

    private void setListenersBtns() {
        btnAddUsr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //onOK();
                if (compruebaUsuario()) {
                    //Hacer el insert en la BBDD
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
        winAddUsr.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public boolean compruebaUsuario() {
        boolean isValid = true;

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
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AgregarUsuario dialog = new AgregarUsuario();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
