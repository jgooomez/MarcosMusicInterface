package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class Artista extends JDialog {
    private JPanel WinCanciones;
    private JPanel box_botones;
    private JPanel box_top;
    private JTextField inpIDArtis;
    private JTextField inpNombreArtis;
    private JTextField inpFechaInicioArtis;
    private JTextField inpNacionalidadArtis;
    private JTextField inpPremiosArtis;
    private JTextField inpGeneroMusiArtis;
    private JButton btnAddArtista;
    private JButton btnBorrarArtista;
    private JButton btnVolver;
    private JLabel txtID;
    private JLabel txtTittle;
    private JLabel txtNombre;
    private JLabel txtFechaInicio;
    private JLabel txtNacionalidad;
    private JLabel txtPremios;
    private JLabel txtGeneroMusical;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Artista() {
        setContentPane(WinCanciones);
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
        WinCanciones.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setListenersBtns();


    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        java.util.List<JLabel> listaTexto = Arrays.asList(txtTittle, txtNombre, txtID, txtNacionalidad, txtPremios, txtFechaInicio, txtGeneroMusical);
        java.util.List<JButton> listaBtns = Arrays.asList(btnAddArtista, btnVolver, btnBorrarArtista);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_top, WinCanciones);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    private void setListenersBtns() {
        btnAddArtista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new AnyadirArtista();
                dialog.setTitle("Agregar artista");
                dialog.setSize(600, 500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        btnBorrarArtista.addActionListener(new ActionListener() {
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
    }

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
        Artista dialog = new Artista();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
