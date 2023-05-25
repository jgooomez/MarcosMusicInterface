import javax.swing.*;
import java.awt.event.*;

public class AdministrarTarjetas extends JDialog {
    private JPanel contentPane;
    private JButton borrarButton;
    private JButton anyadirButton;
    private JTextField idUsuarioInput;
    private JTextField nombreUsuarioOutput;
    private JTextField numTarjetaOutput;
    private JButton volverButton;

    public AdministrarTarjetas() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(borrarButton);

        borrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        anyadirButton.addActionListener(new ActionListener() {
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        anyadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        anyadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo = new AnyadirTarjeta();
                dialogo.setTitle("Añadir tarjeta");
                dialogo.setSize(400,700);
                dialogo.setLocationRelativeTo(null);
                dialogo.setVisible(true);
            }
        });
        borrarButton.addActionListener(new ActionListener() {
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
