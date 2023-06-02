package GUI;

import ClasePOJO.Tarjeta;
import ClasePOJO.Usuario;
import DBManager.DBManagerTarjetas;
import DBManager.DBManagerUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdministrarTarjetas extends JDialog {
    private JPanel WinAdminTarjetas;
    private JPanel box_botones;
    private JPanel box_inputs;
    private JTextField inpIdUsr;
    private JTextField inpNombre;
    private JTextField inpNumTarjeta;
    private JButton btnDelete;
    private JButton btnAdd;
    private JButton btnReturn;
    private JLabel txtAdminTarjetas;
    private JLabel txtNombre;
    private JLabel txtNumTarjeta;
    private JLabel txtIdUsr;
    private JLabel icon;
    private JButton btnBuscar;

    public AdministrarTarjetas() {
        setContentPane(WinAdminTarjetas);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(btnDelete);
        inpNombre.setEnabled(false);
        inpNumTarjeta.setEnabled(false);

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        WinAdminTarjetas.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Añadir la funcionalidad de cada botón
        setListenersBtns();
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Tarjeta> tarjetas = DBManagerTarjetas.obtenerTarjeta();

                // Buscar usuario por ID
                Tarjeta tarjetaBuscada = null;

                for (Tarjeta tarjeta : tarjetas) {
                    if (tarjeta.getIdUsuario() == Integer.parseInt(inpIdUsr.getText())) {
                        tarjetaBuscada = tarjeta;
                        break;
                    }
                }

                if (tarjetaBuscada != null) {
                    // Mostrar los datos del usuario
                    inpNombre.setText(tarjetaBuscada.getNombreTitular());
                    inpNumTarjeta.setText(tarjetaBuscada.getNumeroTarjeta());
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna tarjeta con ese ID enla BBDD", "Error en el ID", JOptionPane.ERROR_MESSAGE);
                    inpIdUsr.setText("");
                }
            }
        });
    }

    private void setListenersBtns() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo = new AnyadirTarjeta();
                dialogo.setTitle("Añadir tarjeta");
                dialogo.setSize(500,400);
                dialogo.setLocationRelativeTo(null);
                dialogo.setVisible(true);
            }
        });
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showOptionDialog(null, "¿Estas seguro de que quieres borrar la tarjeta con número (se muestra el numero de la tarjeta)", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new String[]{"Confirmar", "Cancelar"}, "Confirmar");

                if (opcion == JOptionPane.YES_OPTION) {
                    // Realizar acciones si se selecciona "Confirmar"
                    System.out.println("Confirmar");
                } else if (opcion == JOptionPane.NO_OPTION) {
                    // Realizar acciones si se selecciona "Salir"
                    System.out.println("Cancelar");
                }
            }
        });
    }

    private void styles() {
        txtAdminTarjetas.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JButton> listaBtns = Arrays.asList(btnAdd, btnDelete, btnReturn, btnBuscar);
        MarcosMusic.stylesBtns(listaBtns);
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
        AdministrarTarjetas dialog = new AdministrarTarjetas();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
