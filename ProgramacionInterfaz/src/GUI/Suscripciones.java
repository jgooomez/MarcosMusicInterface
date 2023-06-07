package GUI;

import ClasePOJO.Subscripcion;
import DBManager.DBManagerSubscripcion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Suscripciones extends JDialog {
    private JPanel WinSuscripciones;
    private JButton btnReturn;
    private JPanel box_botones;
    private JButton btnBasica;
    private JButton btnDuo;
    private JButton btnIndividual;
    private JLabel txtTittle;
    private JLabel icono;
    private JPanel box_btns_tipos;
    private JButton btnFamily;
    private JButton btnStudent;
    private ArrayList<Subscripcion> subscripciones = DBManagerSubscripcion.printTablaSubscripcion();

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
                onCancel();
            }
        });

        // Añadir la funcionalidad de cada botón
        setListenersBtns();
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JButton> listaBtns = Arrays.asList(btnReturn, btnBasica, btnIndividual, btnDuo, btnFamily, btnStudent);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_btns_tipos, WinSuscripciones);
        List<JLabel> listaTexto = Arrays.asList(txtTittle);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    private void setListenersBtns() {
        btnBasica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Meter los datos de la suscripcion tipo1 para que se muestren en los textField.
            }
        });
        btnIndividual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //hacer bucle for i para buscar sub por nombre
                JDialog subsInfoView = new SubsInfoView();

                subsInfoView.setTitle("Información");
                subsInfoView.setSize(700, 500);
                subsInfoView.setLocationRelativeTo(null);

                subsInfoView.setVisible(true);
            }
        });
        btnDuo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog subsInfoView = new SubsInfoView();

                subsInfoView.setTitle("Información");
                subsInfoView.setSize(700, 500);
                subsInfoView.setLocationRelativeTo(null);

                subsInfoView.setVisible(true);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        this.setVisible(false);
        MarcosMusic.frame.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        Suscripciones dialog = new Suscripciones();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
