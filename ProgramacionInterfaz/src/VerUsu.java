import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VerUsu extends JDialog {
    private JPanel contentPane;
    private JButton buttonBuscar;
    private JButton buttonCancel;
    private JTextField textFieldIdUsu;
    private JTextField textFieldNacionalidad;
    private JTextField textFieldNombre;
    private JTextField textFieldEdad;
    private JTextField textFieldSeguidores;
    private JButton agregarUsuarioButton;

    public VerUsu() {
        // Desactivar el JTextField al inicio
        textFieldIdUsu.setEnabled(false);

        // Agregar el MouseListener al JTextField
        textFieldIdUsu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textFieldIdUsu.setEnabled(true); // Habilitar el JTextField al hacer clic
            }
        });
        // Establecer el texto de pista y el color inicial del JTextField
        String textoPista = "Introduce el ID";
        textFieldIdUsu.setText(textoPista);
        textFieldIdUsu.setForeground(Color.GRAY);
        textFieldIdUsu.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldIdUsu.getText().equals(textoPista)) {
                    textFieldIdUsu.setText("");
                    textFieldIdUsu.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldIdUsu.getText().isEmpty()) {
                    textFieldIdUsu.setText(textoPista);
                    textFieldIdUsu.setForeground(Color.GRAY);
                }
            }
        });

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonBuscar);
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
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Añadimos codigo para que cuando pongamos el id se rellene los campos del usuario.
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        agregarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JDialog dialog = new AgregarUsuario();
                dialog.setTitle("Agregar Usuario");
                dialog.setSize(300,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {

        dispose();
    }

    public static void main(String[] args) {
        VerUsu dialog = new VerUsu();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
