package GUI;

import DBManager.DBManagerConexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarcosMusic extends JFrame {
    private JPanel principal;
    private JButton btnSuscripciones;
    private JButton btnVerUsuarios;
    private JButton btnDepartamento;
    private JButton btnTarjetas;
    private JButton btnEmpleados;
    private JLabel txtTituloPantallaPrincipal;
    private JPanel panelGeneral;
    static JFrame frame = new JFrame("MarcosMusic");


    public static void main(String[] args) {
        frame.setContentPane(new MarcosMusic().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public MarcosMusic() {
        styles();
        configurarBotones();
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

    private void configurarBotones(){
        btnVerUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo1 = new Usuarios();
                dialogo1.setTitle("Vista de usuarios");
                dialogo1.setSize(600,700);
                dialogo1.setLocationRelativeTo(null);
                dialogo1.setVisible(true);
            }
        });
        btnTarjetas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new AdministrarTarjetas();
                dialogo2.setTitle("Administracion de tarjetas");
                dialogo2.setSize(400,400);
                dialogo2.setLocationRelativeTo(null);
                dialogo2.setVisible(true);
            }
        });
        btnSuscripciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo3 = new Suscripciones();
                dialogo3.setTitle("Tipo de suscripciones");
                dialogo3.setSize(800,400);
                dialogo3.pack();
                dialogo3.setLocationRelativeTo(null);
                dialogo3.setVisible(true);
            }
        });
        btnDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo4 = new Departamentos();
                dialogo4.setTitle("Departamentos");
                dialogo4.setSize(400,400);
                dialogo4.setLocationRelativeTo(null);
                dialogo4.setVisible(true);
            }
        });

        btnEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo5 = new Empleados();
                dialogo5.setTitle("Ver Empleados");
                dialogo5.setSize(400,400);
                dialogo5.setLocationRelativeTo(null);
                dialogo5.setVisible(true);
            }
        });
    }

    private void styles() {
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        txtTituloPantallaPrincipal.setFont(getFontTitle());

        btnVerUsuarios.setFocusable(false);
        btnVerUsuarios.setBackground(getBtnColor());
        btnVerUsuarios.setCursor(cursor);
        btnSuscripciones.setFocusable(false);
        btnSuscripciones.setBackground(getBtnColor());
        btnSuscripciones.setCursor(cursor);
        btnTarjetas.setFocusable(false);
        btnTarjetas.setBackground(getBtnColor());
        btnTarjetas.setCursor(cursor);
        btnDepartamento.setFocusable(false);
        btnDepartamento.setBackground(getBtnColor());
        btnDepartamento.setCursor(cursor);
        btnEmpleados.setFocusable(false);
        btnEmpleados.setBackground(getBtnColor());
        btnEmpleados.setCursor(cursor);
    }

    public static Font getFontTitle() {
        return new Font("Calibri", Font.BOLD, 45);
    }

    public static Color getBtnColor() {
        return new Color(200, 220, 250);
    }
}
