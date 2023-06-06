package GUI;

import DBManager.DBManagerConexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MarcosMusic extends JFrame {
    private JPanel principal;
    private JButton btnSuscripciones;
    private JButton btnVerUsuarios;
    private JButton btnDepartamento;
    private JButton btnTarjetas;
    private JButton btnEmpleados;
    private JLabel txtTituloPantallaPrincipal;
    private JPanel panelGeneral;
    private JPanel box_tittle;
    private JPanel box_btns;
    private JPanel emptyBox;
    static JFrame frame = new JFrame("MarcosMusic");
    private List<JButton> listaBtns;
    private List<JPanel> listaPaneles;


    public static void main(String[] args) {
        frame.setContentPane(new MarcosMusic().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Login();
    }

    public MarcosMusic() {
        listaBtns = Arrays.asList(btnDepartamento, btnEmpleados, btnVerUsuarios, btnSuscripciones, btnTarjetas);
        listaPaneles = Arrays.asList(panelGeneral, principal, box_btns, box_tittle, emptyBox);
        stylesBtns(listaBtns);
        stylesPanels(listaPaneles);
        txtTituloPantallaPrincipal.setFont(getFontTitle());
        ImageIcon iconSpotify = new ImageIcon("iconos/Spotify_icon.png");
        frame.setIconImage(iconSpotify.getImage());
        /*
        try {
            URL pathSpotifyIcon = new URL("iconos/Spotify_icon.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("iconos/Spotify_icon.png"));*/

        configurarBotones();
        DBManagerConexion.loadDriver();
        DBManagerConexion.connect();
    }

    private void configurarImagenDeFondo() {
        // Crear un JLabel para contener la imagen de fondo
        JLabel fondo = new JLabel(new ImageIcon("ruta/a/la/imagen/fondo.jpg"));

        // Establecer el tamaño y la posición del JLabel
        fondo.setBounds(0, 0, getWidth(), getHeight());

        // Agregar el JLabel al panel principal
        principal.add(fondo);
    }

    private void configurarBotones() {
        btnVerUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo1 = new Usuarios();
                dialogo1.setTitle("Vista de usuarios");
                dialogo1.setSize(700, 400);
                dialogo1.setLocationRelativeTo(null);
                frame.setVisible(false);
                dialogo1.setVisible(true);
            }
        });
        btnTarjetas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new AdministrarTarjetas();
                dialogo2.setTitle("Administracion de tarjetas");
                dialogo2.setSize(500, 400);
                dialogo2.setLocationRelativeTo(null);
                frame.setVisible(false);
                dialogo2.setVisible(true);
            }
        });
        btnSuscripciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new Suscripciones();
                dialogo2.setTitle("Tipo de suscripciones");
                dialogo2.setSize(800, 400);
                dialogo2.pack();
                dialogo2.setLocationRelativeTo(null);
                frame.setVisible(false);
                dialogo2.setVisible(true);
            }
        });
        btnDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new Departamentos();
                dialogo2.setTitle("Departamentos");
                dialogo2.setSize(400, 400);
                dialogo2.setLocationRelativeTo(null);
                frame.setVisible(false);
                dialogo2.setVisible(true);
            }
        });

        btnEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new Empleados();
                dialogo2.setTitle("Ver Empleados");
                dialogo2.setSize(400, 400);
                dialogo2.setLocationRelativeTo(null);
                frame.setVisible(false);
                dialogo2.setVisible(true);
            }
        });
    }

    public static void stylesBtns(List<JButton> listaBotones) {
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        for (JButton btn:
             listaBotones) {
            btn.setBorder(BorderFactory.createBevelBorder(1, Color.white, Color.white));
            btn.setFocusable(false);
            btn.setBackground(getBtnColor());
            btn.setCursor(cursor);
        }
    }

    public static void stylesPanels(List<JPanel> listaPaneles) {
        for (JPanel panel:
             listaPaneles) {
            panel.setBackground(getBackgroundColor());
        }
    }
    public static Font getFontTitle() {
        return new Font("Calibri", Font.BOLD, 30);
    }

    public static Color getBtnColor() {
        return new Color(30, 215, 96);
    }

    public static Color getBackgroundColor() {
        return new Color(40, 40, 40);
    }
}
