package GUI;

import javax.swing.*;
import java.awt.event.*;

public class Login extends JDialog {
    private JPanel principal;
    private JPanel panelCampos;
    private JTextField inpUserName;
    private JTextField inpPassword;
    private JButton LOGINButton;
    private JButton buttonOK;
    private JButton buttonCancel;
    private MarcosMusic frame;

    public Login(MarcosMusic frame) {
        this.frame = frame;
        setContentPane(principal);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        principal.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new MarcosMusic();
                frame.setTitle("Bienvenido a Marcos Music");
                frame.setEnabled(true);
                frame.setSize(600, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
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
        MarcosMusic frame = new MarcosMusic();
        frame.setTitle("Bienvenido a Marcos Music");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        // Crear una instancia de Login
        Login loginDialog = new Login(frame);  // Pasar la instancia de MarcosMusic como argumento

        // Mostrar el diálogo de inicio de sesión
        loginDialog.setVisible(true);
    }

}
