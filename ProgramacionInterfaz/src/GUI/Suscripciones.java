package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Suscripciones extends JDialog {
    private JPanel WinSuscripciones;
    private JTextField inpTipoSus;
    private JTextField inpPrecio;
    private JButton btnReturn;
    private JPanel box_top;
    private JPanel box_botones;
    private JButton btnType1;
    private JButton btnType3;
    private JButton btnType2;
    private JLabel txtTittle;
    private JLabel icono;
    private JLabel txtTipo;
    private JLabel txtPrecio;

    public Suscripciones() {
        styles();
        setContentPane(WinSuscripciones);
        setModal(true);
        getRootPane().setDefaultButton(btnReturn);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        WinSuscripciones.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Añadir la funcionalidad de cada botón
        setListenersBtns();
    }

    private void styles() {
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        txtTittle.setFont(MarcosMusic.getFontTitle());

        btnReturn.setFocusable(false);
        btnReturn.setBackground(MarcosMusic.getBtnColor());
        btnReturn.setCursor(cursor);

        btnType1.setFocusable(false);
        btnType1.setBackground(MarcosMusic.getBtnColor());
        btnType1.setCursor(cursor);

        btnType2.setFocusable(false);
        btnType2.setBackground(MarcosMusic.getBtnColor());
        btnType2.setCursor(cursor);

        btnType3.setFocusable(false);
        btnType3.setBackground(MarcosMusic.getBtnColor());
        btnType3.setCursor(cursor);
    }

    private void setListenersBtns() {
        btnType1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Meter los datos de la suscripcion tipo1 para que se muestren en los textField.
            }
        });
        btnType2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Meter los datos de la suscripcion tipo2 para que se muestren en los textField.
            }
        });
        btnType3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Meter los datos de la suscripcion tipo3 para que se muestren en los textField.
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
        Suscripciones dialog = new Suscripciones();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
