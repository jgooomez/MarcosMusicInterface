package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class WelcomeScreen extends JFrame {
    private JPanel winWelcomeScreen;
    private JButton btnAdmin;
    private JButton btnUser;
    private JLabel txtTittle;
    private JLabel txtEntrarComo;
    static JFrame frame = new JFrame("WelcomeScreen");

    public static void main(String[] args) {
        frame.setContentPane(new WelcomeScreen().winWelcomeScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public WelcomeScreen(){
        styles();

        // call onCancel() on ESCAPE
        winWelcomeScreen.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(frame);
            }
        });
        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(frame);
            }
        });
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        java.util.List<JLabel> listaTexto = Arrays.asList(txtTittle, txtEntrarComo);
        java.util.List<JButton> listaBtns = Arrays.asList(btnAdmin, btnUser);
        List<JPanel> listaPaneles = Arrays.asList(winWelcomeScreen);
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
}
