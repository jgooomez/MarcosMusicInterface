import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Usuarios extends JDialog {
    private JPanel contentPane;
    private JButton btnBuscar;
    private JButton btnCancel;
    private JTextField inpIdUsr;
    private JTextField outpNacionalidad;
    private JTextField outpNombre;
    private JTextField outpEdad;
    private JTextField outpNumSeguidores;
    private JButton btnAddUser;
    private JLabel txtVerUsuario;

    public Usuarios() {
        styles();
        // Desactivar el JTextField al inicio
        inpIdUsr.setEnabled(false);
        btnBuscar.setEnabled(false);

        // Agregar el MouseListener al JTextField
        inpIdUsr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inpIdUsr.setEnabled(true);      // Habilitar el JTextField al hacer click
            }
        });
        // Establecer el texto de pista y el color inicial del JTextField
        setTextInToInpIdUsr();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnBuscar);
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

        // Añadir la funcionalidad de cada botón
        setListenersBtns();
    }

    private void styles() {
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        txtVerUsuario.setFont(MarcosMusic.getFontTitle());

        btnBuscar.setFocusable(false);
        btnBuscar.setBackground(MarcosMusic.getBtnColor());
        btnBuscar.setCursor(cursor);

        btnAddUser.setFocusable(false);
        btnAddUser.setBackground(MarcosMusic.getBtnColor());
        btnAddUser.setCursor(cursor);

        btnCancel.setFocusable(false);
        btnCancel.setBackground(MarcosMusic.getBtnColor());
        btnCancel.setCursor(cursor);
    }

    private void setListenersBtns() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Añadimos código para que cuando pongamos el Id se rellene los campos del usuario.
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new AgregarUsuario();
                dialog.setTitle("Agregar Usuario");
                dialog.setSize(300,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }

    private void setTextInToInpIdUsr() {
        String textoPista = "Introduzca el ID";
        inpIdUsr.setText(textoPista);
        inpIdUsr.setForeground(Color.GRAY);
        inpIdUsr.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inpIdUsr.getText().equals(textoPista)) {
                    inpIdUsr.setText("");
                    inpIdUsr.setForeground(Color.BLACK);
                    btnBuscar.setEnabled(true);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (inpIdUsr.getText().isEmpty()) {
                    inpIdUsr.setText(textoPista);
                    inpIdUsr.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {

        dispose();
    }

    public static void main(String[] args) {
        Usuarios dialog = new Usuarios();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
