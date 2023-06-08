package GUI;

import ClasePOJO.Artista;
import DBManager.DBManagerArtista;
import DBManager.DBManagerConexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class ArtistaView extends JDialog {
    private JPanel WinCanciones;
    private JPanel box_botones;
    private JPanel box_top;
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
    private JButton btnBuscar;
    private JLabel txtId;
    private JTextField inpId;
    private JButton buttonOK;
    private JButton buttonCancel;

    public ArtistaView() {
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
        java.util.List<JLabel> listaTexto = Arrays.asList(txtTittle, txtNombre, txtNacionalidad, txtPremios, txtFechaInicio, txtGeneroMusical, txtId);
        java.util.List<JButton> listaBtns = Arrays.asList(btnAddArtista, btnVolver, btnBorrarArtista, btnBuscar);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_top, WinCanciones);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    private void setListenersBtns() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idArtistaText = inpId.getText();

                try {
                    int idArtista = Integer.parseInt(idArtistaText);
                    Artista artista = DBManagerArtista.getArtistaPorId(idArtista);

                    if (artista != null) {
                        // Rellenar los campos de texto con los datos del artista
                        inpNombreArtis.setText(artista.getNombre());
                        inpFechaInicioArtis.setText(artista.getFechaInicio());
                        inpNacionalidadArtis.setText(artista.getNacionalidad());
                        inpPremiosArtis.setText(String.valueOf(artista.getNumPremios()));
                        inpGeneroMusiArtis.setText(artista.getGeneroMusical());
                    } else {
                        // El artista no fue encontrado, puedes mostrar un mensaje de error o limpiar los campos
                        JOptionPane.showMessageDialog(null, "No se encontró ningún artista con el ID especificado", "ArtistaView no encontrado", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // El valor proporcionado no es un entero válido
                    JOptionPane.showMessageDialog(null, "El ID del artista debe ser un número entero", "Valor no válido", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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
                JDialog dialog = new BorrarArtista();
                dialog.setTitle("Agregar artista");
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
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
        DBManagerConexion.loadDriver();
        DBManagerConexion.connect();

        ArtistaView dialog = new ArtistaView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
