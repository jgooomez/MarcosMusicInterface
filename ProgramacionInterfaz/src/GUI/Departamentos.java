package GUI;

import javax.swing.*;
import java.awt.event.*;

public class Departamentos extends JDialog {
    private JPanel WinDepartamentos;
    private JButton btnReturn;
    private JPanel box_top;
    private JPanel box_botones;
    private JButton buttonAtras;
    private JButton btnAtencionCliente;
    private JButton btnSonido;
    private JButton btnVideo;
    private JButton btnRSS;
    private JButton btnDiseño;
    private JButton buttonCancel;

    public Departamentos() {
        setContentPane(WinDepartamentos);
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
        WinDepartamentos.registerKeyboardAction(new ActionListener() {
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
        btnAtencionCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoAtencionCliente();
                dialog.setTitle("Atencion al Cliente");
                dialog.setSize(350,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        btnSonido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoSonido();
                dialog.setTitle("Sonido");
                dialog.setSize(350,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        btnVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoVideo();
                dialog.setTitle("Video");
                dialog.setSize(350,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        btnRSS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoRSS();
                dialog.setTitle("Redes Sociales");
                dialog.setSize(350,500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        btnDiseño.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new DepartamentoDisenyo();
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
