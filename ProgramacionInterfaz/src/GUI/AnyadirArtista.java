package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class AnyadirArtista extends JDialog {
    private JPanel WinAddArtista;
    private JPanel box_botones;
    private JPanel box_top;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JLabel txtNombre;
    private JLabel txtFechaInicio;
    private JLabel txtNacionalidad;
    private JLabel txtPremios;
    private JLabel txtGeneroMusical;
    private JLabel txtTittle;
    private JButton buttonOK;

    public AnyadirArtista() {
        setContentPane(WinAddArtista);
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
        WinAddArtista.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setListenersBtns();
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        java.util.List<JLabel> listaTexto = Arrays.asList(txtTittle, txtNombre, txtFechaInicio, txtNacionalidad, txtPremios, txtFechaInicio, txtGeneroMusical);
        java.util.List<JButton> listaBtns = Arrays.asList(btnAceptar, btnCancelar);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_top, WinAddArtista);
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
        AnyadirArtista dialog = new AnyadirArtista();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
