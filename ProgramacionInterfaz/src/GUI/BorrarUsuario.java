package GUI;

import javax.swing.*;
import java.awt.event.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class BorrarUsuario {
    private JPanel WinBorrarUsr;
    private JButton bntDeleteUsr;
    private JButton btnVolver;
    private JPanel box_botones;
    private JPanel box_top;
    private JTextField inpIdUsr;
    private JLabel icono;
    private JLabel txtTittle;
    private JLabel txtIdUsr;
    private JPanel box_input;
    private JPanel box_btns;
    private final JDialog dialogo;

    public BorrarUsuario() {
        dialogo = new JDialog();
        dialogo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialogo.setTitle("Borrar Usuario");
        dialogo.setSize(500, 300);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setContentPane(WinBorrarUsr);
        dialogo.setModal(true);
        setListenersBtns();
        dialogo.setVisible(true);
        dialogo.getRootPane().setDefaultButton(bntDeleteUsr);
        dialogo.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void setListenersBtns() {
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LLEGA");
                dialogo.dispose();
            }
        });
        bntDeleteUsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showOptionDialog(null, "¿Estas seguro de que quieres borrar el usuario con nombre: (se muestra el numero de la tarjeta)", "Confirmar",
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

    private void onCancel() {
        dialogo.dispose();
    }
}
