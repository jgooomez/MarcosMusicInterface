import javax.swing.*;
import java.awt.event.*;

public class Departamentos extends JDialog {
    private JPanel contentPane;
    private JButton buttonAtras;
    private JButton atencionAlClienteButton;
    private JButton sonidoButton;
    private JButton videoButton;
    private JButton redesSocialesButton;
    private JButton diseñoButton;
    private JLabel LabelDepartamento;
    private JLabel Icono;
    private JButton buttonCancel;

    public Departamentos() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonAtras);
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
        buttonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        atencionAlClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoAtencionCliente();
                dialog.setTitle("Atencion al Cliente");
                dialog.setSize(350,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        sonidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoSonido();
                dialog.setTitle("Sonido");
                dialog.setSize(350,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        videoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoVideo();
                dialog.setTitle("Video");
                dialog.setSize(350,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        redesSocialesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoRSS();
                dialog.setTitle("Redes Sociales");
                dialog.setSize(350,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        diseñoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoDiseño();
                dialog.setTitle("Redes Sociales");
                dialog.setSize(350,500);
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
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Departamentos dialog = new Departamentos();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
