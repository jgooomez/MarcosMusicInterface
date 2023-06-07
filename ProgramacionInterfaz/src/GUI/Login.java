package GUI;

import javax.swing.*;

public class Login extends JFrame {
    private JPanel winLogin;
    private JTextField inpUsr;
    private JTextField inpPsswd;
    private JLabel txtLogIn;
    private JLabel txtUsr;
    private JLabel txtPsswd;

    static JFrame frame = new JFrame("Login");

    public static void main(String[] args) {
        frame.setContentPane(new Login().winLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Login() {
        txtLogIn.setFont(MarcosMusic.getFontTitle());
    }
}
