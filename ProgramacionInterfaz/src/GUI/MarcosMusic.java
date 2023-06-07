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

    /**
     * Crea una instancia de la clase MarcosMusic.
     * Esta clase representa la ventana principal de la aplicación.
     * Inicializa los componentes gráficos, aplica estilos y configura los listeners de los botones.
     */
    public MarcosMusic() {
        listaBtns = Arrays.asList(btnDepartamento, btnEmpleados, btnVerUsuarios, btnSuscripciones, btnTarjetas);
        listaPaneles = Arrays.asList(panelGeneral, principal, box_btns, box_tittle, emptyBox);
        stylesBtns(listaBtns);
        stylesPanels(listaPaneles);
        txtTituloPantallaPrincipal.setFont(getFontTitle());
        ImageIcon iconSpotify = new ImageIcon("iconos/Spotify_icon.png");
        frame.setIconImage(iconSpotify.getImage());

        configurarBotones();
        DBManagerConexion.loadDriver();
        DBManagerConexion.connect();
    }

    /**
     * Configura los listeners de los botones.
     */
    private void configurarBotones() {
        btnVerUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo1 = new Usuarios();
                dialogo1.setTitle("Vista de usuarios");
                dialogo1.setSize(700, 500);
                dialogo1.setLocationRelativeTo(null);
                frame.setVisible(false);
                dialogo1.setVisible(true);
            }
        });

        btnTarjetas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new AdministrarTarjetas();
                dialogo2.setTitle("Administración de tarjetas");
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
                dialogo2.setSize(400, 300);
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

    /**
     * Aplica estilos a los botones de la lista especificada.
     * @param listaBotones Lista de botones a los que se les aplicarán los estilos.
     */
    public static void stylesBtns(List<JButton> listaBotones) {
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        for (JButton btn : listaBotones) {
            btn.setBorder(BorderFactory.createBevelBorder(1, Color.white, Color.white));
            btn.setFocusable(false);
            btn.setBackground(getBtnColor());
            btn.setCursor(cursor);
        }
    }

    /**
     * Aplica estilos a los paneles de la lista especificada.
     * @param listaPaneles Lista de paneles a los que se les aplicarán los estilos.
     */
    public static void stylesPanels(List<JPanel> listaPaneles) {
        for (JPanel panel : listaPaneles) {
            panel.setBackground(getBackgroundColor());
        }
    }

    /**
     * Aplica estilos a los textos de la lista especificada.
     * @param listaTexto Lista de etiquetas de texto a las que se les aplicarán los estilos.
     */
    public static void stylesTexts(List<JLabel> listaTexto) {
        for (JLabel txt : listaTexto) {
            txt.setForeground(Color.WHITE);
        }
    }

    /**
     * Obtiene la fuente de título utilizada en la interfaz gráfica.
     * @return La fuente de título.
     */
    public static Font getFontTitle() {
        return new Font("Calibri", Font.BOLD, 30);
    }

    /**
     * Obtiene el color de los botones.
     * @return El color de los botones.
     */
    public static Color getBtnColor() {
        return new Color(30, 215, 96);
    }

    /**
     * Obtiene el color de fondo utilizado en la interfaz gráfica.
     * @return El color de fondo.
     */
    public static Color getBackgroundColor() {
        return new Color(40, 40, 40);
    }
}
