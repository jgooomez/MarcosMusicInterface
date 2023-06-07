package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class Login extends JDialog {
    private JPanel winLogin;
    private JPanel box_inputs;
    private JTextField inpUserName;
    private JTextField inpPassword;
    private JButton btnLogin;
    private JLabel txtTittle;
    private JLabel txtUser;
    private JLabel txtPsswd;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Login() {
        setContentPane(winLogin);
        styles();
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
        winLogin.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        btnLogin.addActionListener(new ActionListener() {
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

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        java.util.List<JLabel> listaTexto = Arrays.asList(txtTittle, txtPsswd, txtUser);
        java.util.List<JButton> listaBtns = Arrays.asList(btnLogin);
        List<JPanel> listaPaneles = Arrays.asList(winLogin, box_inputs);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
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
        Login dialog = new Login();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
