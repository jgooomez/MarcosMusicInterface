package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class Conciertos extends JDialog {
    private JPanel WinConcierto;
    private JPanel box_botones;
    private JPanel box_top;
    private JTextField inpCodConcierto;
    private JTextField inpLugarConcierto;
    private JTextField inpFechaConcierto;
    private JTextField inpCiudadConcierto;
    private JTextField inpPaisConcierto;
    private JTextField inpCapacidadConcierto;
    private JTextField inpDineroRecaudado;
    private JButton btnAddConcierto;
    private JButton btnBorrarConcierto;
    private JButton btnVolver;
    private JLabel txtCodigo;
    private JLabel txtLugar;
    private JLabel txtFecha;
    private JLabel txtCiudad;
    private JLabel txtPais;
    private JLabel txtCapacidad;
    private JLabel txtDineroRecaudado;
    private JLabel txtTittle;
    private JButton buttonOK;
    private JButton buttonCancel;

    /*public Conciertos() {
        setContentPane(WinConcierto);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        WinConcierto.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setListenersBtns();
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        java.util.List<JLabel> listaTexto = Arrays.asList(txtTittle, txtCodigo, txtLugar, txtFecha, txtCapacidad, txtCiudad, txtDineroRecaudado, txtPais);
        java.util.List<JButton> listaBtns = Arrays.asList(btnAddConcierto, btnVolver, btnBorrarConcierto);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_top, WinConcierto);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    private void setListenersBtns() {
        btnAddConcierto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new AnyadirConcierto();
                dialog.setTitle("Agregar concierto");
                dialog.setSize(600, 500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        btnBorrarConcierto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }*/

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        this.setVisible(false);
        MarcosMusic.frame.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        Conciertos dialog = new Conciertos();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
