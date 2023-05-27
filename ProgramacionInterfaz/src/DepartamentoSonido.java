import javax.swing.*;
import java.awt.event.*;

public class DepartamentoSonido extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textFieldNombre;
    private JTextField textFieldFechaCreacion;
    private JTextField textFieldNombreEncargado;
    private JTextField textFieldNumTrabajadores;
    private JTextField textFieldNumSubDepartamentos;
    private JTextField textFieldIDDepartamento;

    public DepartamentoSonido() {
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
        DepartamentoSonido dialog = new DepartamentoSonido();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
