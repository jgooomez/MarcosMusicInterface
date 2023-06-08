package GUI;

import ClasePOJO.Concierto;
import DBManager.DBManagerConcierto;
import DBManager.DBManagerConexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                onOK();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
                dispose();
            }
        });

    }

    private void onOK() {
        String lugar = inpLugar.getText();
        String fechaStr = inpFecha.getText();
        String ciudad = inpCiudad.getText();
        String pais = inpPais.getText();
        String capacidadStr = inpCapacidad.getText();
        String dineroRecaudadoStr = inpDineroRecaudado.getText();

        // Verificar que no haya campos vacíos
        if (lugar.isEmpty() || fechaStr.isEmpty() || ciudad.isEmpty() || pais.isEmpty() || capacidadStr.isEmpty() || dineroRecaudadoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar formato de fecha (yyyy/MM/dd)
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            dateFormat.setLenient(false);
            java.util.Date utilDate = dateFormat.parse(fechaStr);
            java.sql.Date fecha = new java.sql.Date(utilDate.getTime());

            // Verificar que la capacidad sea un número válido
            int capacidad;
            try {
                capacidad = Integer.parseInt(capacidadStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La capacidad debe ser un número entero válido.", "Capacidad Inválida", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que el dinero recaudado sea un número válido
            double dineroRecaudado;
            try {
                dineroRecaudado = Double.parseDouble(dineroRecaudadoStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El dinero recaudado debe ser un número válido.", "Dinero Recaudado Inválido", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Concierto concierto = new Concierto(lugar, fecha, ciudad, pais, capacidad, dineroRecaudado);

            if (DBManagerConcierto.addConcierto(concierto)) {
                JOptionPane.showMessageDialog(null, "Concierto agregado correctamente.", "Agregado Exitoso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar el concierto.", "Error de Agregado", JOptionPane.ERROR_MESSAGE);
            }

            dispose();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Fecha en formato incorrecto. Use el formato yyyy/MM/dd.", "Formato de Fecha Incorrecto", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}