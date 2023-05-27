import javax.swing.*;
import java.awt.event.*;

public class AgregarUsuario extends JDialog {
    private JPanel contentPane;
    private JButton buttonAgregar;
    private JButton buttonCancelar;
    private JLabel Icono;
    private JLabel Titulo;
    private JLabel labelIdUsuario;
    private JLabel LabelNacionalidad;
    private JLabel LabelNombre;
    private JLabel LabelEdad;
    private JLabel LabelSeguidores;
    private JLabel LabelFoto;
    private JTextField textFieldNombre;
    private JTextField textFieldEdad;
    private JTextField textFieldSeguidores;
    private JTextField textFieldIdUsuario;
    private JTextField textFieldNacionalidad;

    public AgregarUsuario() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonAgregar);

        buttonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
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
        AgregarUsuario dialog = new AgregarUsuario();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
