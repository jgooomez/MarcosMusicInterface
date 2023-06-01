package GUI;

import javax.swing.*;
import java.awt.event.*;

public class BorrarUsuario extends JDialog {
    private JPanel WinBorrarUsr;
    private JButton bntDeleteUsr;
    private JButton btnVolver;
    private JPanel box_botones;
    private JPanel box_top;
    private JTextField inpIdUsr;
    private JLabel icono;

    public BorrarUsuario() {
        setContentPane(WinBorrarUsr);
        setModal(true);
        getRootPane().setDefaultButton(bntDeleteUsr);
        setListenersBtns();
    }

    private void setListenersBtns() {
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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

    public static void main(String[] args) {
        BorrarUsuario dialog = new BorrarUsuario();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
