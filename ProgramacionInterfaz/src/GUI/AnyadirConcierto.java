package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class AnyadirConcierto extends JDialog {
    private JPanel WinAddConcierto;
    private JPanel box_botones;
    private JPanel box_top;
    private JTextField inpCapacidad;
    private JTextField inpPais;
    private JTextField inpCiudad;
    private JTextField inpFecha;
    private JTextField inpLugar;
    private JTextField inpDineroRecaudado;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JLabel txtLugar;
    private JLabel txtFecha;
    private JLabel txtCiudad;
    private JLabel txtPais;
    private JLabel txtCapacidad;
    private JLabel txtDinero;
    private JLabel txtTittle;
    private JButton buttonOK;
    private JButton buttonCancel;

    public AnyadirConcierto() {
        setContentPane(WinAddConcierto);
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
        WinAddConcierto.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setListenersBtns();
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        java.util.List<JLabel> listaTexto = Arrays.asList(txtTittle, txtFecha, txtPais, txtCapacidad, txtCiudad, txtDinero, txtLugar);
        java.util.List<JButton> listaBtns = Arrays.asList(btnAceptar, btnCancelar);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_top, WinAddConcierto);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    private void setListenersBtns() {
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
        AnyadirConcierto dialog = new AnyadirConcierto();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}