import javax.swing.*;
import java.awt.event.*;

public class Suscripciones extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton tipo1Button;
    private JButton tipo3Button;
    private JButton tipo2Button;
    private JTextField tipoSubscripcionOutput;
    private JTextField precioSubscripcionOutput;
    private JButton buttonCancel;

    public Suscripciones() {
        setContentPane(contentPane);
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        tipo1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Meter los datos de la suscripcion tipo1 para que se muestren en los textField.
            }
        });
        tipo2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Meter los datos de la suscripcion tipo2 para que se muestren en los textField.
            }
        });
        tipo3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Meter los datos de la suscripcion tipo3 para que se muestren en los textField.
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
        Suscripciones dialog = new Suscripciones();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
