package GUI;

import javax.swing.*;
import java.awt.event.*;

public class WelcomeScreen extends JDialog {
    private JPanel principal;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelBotones;
    private JPanel panelCampos;
    private JButton usuarioButton;
    private JButton adminButton;

    public WelcomeScreen() {
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
        usuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abrir el form para meter datos
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abrir el form para meter datos
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
        WelcomeScreen dialog = new WelcomeScreen();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
