package GUI;

import ClasePOJO.Concierto;
import DBManager.DBManagerConcierto;
import DBManager.DBManagerConexion;

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
    private JTextField outpLugar;
    private JTextField outpFecha;
    private JTextField outpCiudad;
    private JTextField outpPais;
    private JTextField outpCapacidad;
    private JTextField outpDineroRecaudado;
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
    private JButton btnBuscar;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Conciertos() {
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
        java.util.List<JButton> listaBtns = Arrays.asList(btnBuscar, btnAddConcierto, btnVolver, btnBorrarConcierto);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_top, WinConcierto);
        List<JTextField> listaOutPuts = Arrays.asList(outpCapacidad, outpCiudad, outpFecha, outpLugar, outpPais, outpDineroRecaudado);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
        MarcosMusic.stylesOutPutText(listaOutPuts);
    }

    private void setListenersBtns() {
        btnBuscar.addActionListener(e -> {
            int codigo = Integer.parseInt(inpCodConcierto.getText());
            Concierto concierto = DBManagerConcierto.buscarConciertoPorCodigo(codigo);

            if (concierto != null) {
                outpLugar.setText(concierto.getLugar());
                outpFecha.setText(String.valueOf(concierto.getFecha()));
                outpCiudad.setText(concierto.getCiudad());
                outpPais.setText(concierto.getPais());
                outpCapacidad.setText(String.valueOf(concierto.getCapacidad()));
                outpDineroRecaudado.setText(String.valueOf(concierto.getDineroRecaudado()));

            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el concierto.");
            }

            outpLugar.setText(concierto.getLugar());
            outpFecha.setText(String.valueOf(concierto.getFecha()));
            outpCiudad.setText(concierto.getCiudad());
            outpPais.setText(concierto.getPais());
            outpCapacidad.setText(String.valueOf(concierto.getCapacidad()));
            outpDineroRecaudado.setText(String.valueOf(concierto.getDineroRecaudado()));

        });
        btnAddConcierto.addActionListener(e -> {
            JDialog dialog = new AnyadirConcierto();
            dialog.setTitle("Agregar concierto");
            dialog.setSize(600, 500);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
        btnBorrarConcierto.addActionListener(e -> {
            String codigo = inpCodConcierto.getText();
            if (!codigo.isEmpty()) {
                int codigoConcierto = Integer.parseInt(codigo);

                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar el concierto?", "Confirmación de Eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    if (DBManagerConcierto.deleteConcierto(codigoConcierto)) {
                        JOptionPane.showMessageDialog(null, "Concierto eliminado correctamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        inpCodConcierto.setText("");
                        outpLugar.setText("");
                        outpFecha.setText("");
                        outpCiudad.setText("");
                        outpPais.setText("");
                        outpCapacidad.setText("");
                        outpDineroRecaudado.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR AL BORRAR", "Eliminación Errónea", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese el código del concierto a borrar.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
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

        Conciertos dialog = new Conciertos();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}