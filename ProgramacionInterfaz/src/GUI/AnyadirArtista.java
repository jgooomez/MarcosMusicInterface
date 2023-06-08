package GUI;

import ClasePOJO.Artista;
import DBManager.DBManagerArtista;
import DBManager.DBManagerConexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class AnyadirArtista extends JDialog {
    private JPanel WinAddArtista;
    private JPanel box_botones;
    private JPanel box_top;
    private JTextField inpGenero;
    private JTextField inpNombre;
    private JTextField inpFechaInicio;
    private JTextField inpNacionalidad;
    private JTextField inpNPremios;
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
                String nombreArtista = inpNombre.getText();
                String fechaInicio = inpFechaInicio.getText();
                String nacionalidad = inpNacionalidad.getText();
                String numPremiosText = inpNPremios.getText();
                String generoMusical = inpGenero.getText();

                // Validar que todos los campos estén completos
                if (nombreArtista.isEmpty() || fechaInicio.isEmpty() || nacionalidad.isEmpty() || numPremiosText.isEmpty() || generoMusical.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos", "Campos incompletos", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el número de premios sea un entero válido
                int numPremios;
                try {
                    numPremios = Integer.parseInt(numPremiosText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El número de premios debe ser un valor entero válido", "Valor no válido", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear el objeto Artista
                Artista artista = new Artista(nombreArtista, fechaInicio, nacionalidad, numPremios, generoMusical);

                // Insertar el artista en la base de datos
                boolean exito = DBManagerArtista.addArtista(artista);
                if (exito) {
                    JOptionPane.showMessageDialog(null, "Artista agregado exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar el artista", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
        DBManagerConexion.loadDriver();
        DBManagerConexion.connect();

        AnyadirArtista dialog = new AnyadirArtista();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
