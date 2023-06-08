package GUI;
import DBManager.DBManagerUsuarios;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class Login {
    public JPanel winLogin;
    private JPanel box_inputs;
    private JTextField inpUserName;
    private JTextField inpPassword;
    private JButton btnLogin;
    private JLabel txtTittle;
    private JLabel txtUser;
    private JLabel txtPsswd;
    private JDialog dialogo;

    public Login(JFrame frame) {
        styles();
        dialogo = new JDialog();
        dialogo.setTitle("Log In");
        dialogo.setContentPane(winLogin);
        dialogo.getRootPane().setDefaultButton(btnLogin);
        dialogo.setSize(400, 300);
        dialogo.setEnabled(true);
        dialogo.setLocationRelativeTo(null);
        frame.setVisible(false);
        dialogo.setVisible(true);

        // call onCancel() when cross is clicked
        dialogo.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogo.addWindowListener(new WindowAdapter() {
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
                new MarcosMusic(dialogo);
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
        dialogo.dispose();
    }

    private void onCancel() {
        dialogo.dispose();
    }
}
