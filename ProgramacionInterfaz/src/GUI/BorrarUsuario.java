package GUI;

import DBManager.DBManagerUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class BorrarUsuario {
    private JPanel WinBorrarUsr;
    private JButton btnDeleteUsr;
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

    /**
     * Crea una instancia de la clase BorrarUsuario.
     * Esta clase representa una ventana de diálogo para borrar un usuario.
     * Inicializa los componentes gráficos, aplica estilos y configura los listeners.
     */
    public BorrarUsuario() {
        dialogo = new JDialog();
        dialogo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialogo.setTitle("Borrar Usuario");
        dialogo.setSize(500, 300);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setContentPane(WinBorrarUsr);
        styles();
        dialogo.setModal(true);
        setListenersBtns();
        dialogo.setVisible(true);
        dialogo.getRootPane().setDefaultButton(btnDeleteUsr);
        dialogo.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    /**
     * Aplica estilos a los elementos visuales.
     * Establece la fuente y tamaño del título y aplica estilos a los botones.
     */
    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JButton> listaBtns = Arrays.asList(btnVolver, btnDeleteUsr);
        MarcosMusic.stylesBtns(listaBtns);
    }

    /**
     * Establece los listeners para los botones.
     * Vuelve hacia atrás ocultando el diálogo actual.
     */
    private void setListenersBtns() {
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LLEGA");
                dialogo.dispose();
            }
        });

        /**
         * Comprueba si existe el usuario en la BBDD, si existe lo borra
         * @param idUsuario
         */
        btnDeleteUsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showOptionDialog(null, "¿Estas seguro de que quieres borrar el usuario con id: " + inpIdUsr.getText(), "Confirmar",
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
                    System.out.println("Cancelar");
                }
            }
        });
    }

    /**
     * Acción a realizar al cerrar la ventana.
     * Cierra la ventana de diálogo.
     */
    private void onCancel() {
        dialogo.dispose();
    }
}
