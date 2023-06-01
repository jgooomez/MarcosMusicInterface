package GUI;

import javax.swing.*;
import java.awt.event.*;

public class DepartamentoSonido extends JDialog {
    private JPanel WinSonido;
    private JButton btnReturn;
    private JTextField inpNombre;
    private JTextField inpFechaCreacion;
    private JTextField inpNombreEncargado;
    private JTextField inpNumTrabajadores;
    private JTextField inpNumDeSubDepar;
    private JTextField inpIdDepartamento;
    private JPanel box_botones;
    private JPanel box_top;
    private JLabel icono;
    private JLabel txtTittle;
    private JLabel txtNumSubDpto;
    private JLabel txtIdDpto;
    private JLabel txtNumTrabajadores;
    private JLabel txtNombreEncargado;
    private JLabel txtFechaCreacion;
    private JLabel txtNombre;

    public DepartamentoSonido() {
        setContentPane(WinSonido);
        setModal(true);
        getRootPane().setDefaultButton(btnReturn);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        WinSonido.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btnReturn.addActionListener(new ActionListener() {
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
