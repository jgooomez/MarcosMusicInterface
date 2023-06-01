package GUI;

import DBManager.DBManagerUsuarios;

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
    private JLabel txtTittle;
    private JLabel txtIdUsr;
    private JPanel box_input;
    private JPanel box_btns;

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
                    if (DBManagerUsuarios.existsUsuario(Integer.parseInt(inpIdUsr.getText()))) {
                        if (DBManagerUsuarios.deleteUsuario(Integer.parseInt(inpIdUsr.getText()))) {
                            JOptionPane.showConfirmDialog(null, "Se ha borrado el usuario: " + inpIdUsr.getText() + "correctamente");
                        }
                    } else {
                        JOptionPane.showConfirmDialog(null, "No se ha encontrado el usuario: " + inpIdUsr.getText());
                    }
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
