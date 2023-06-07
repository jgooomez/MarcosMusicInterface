package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class DepartamentoRSS extends JDialog {
    private JPanel WinRSS;
    private JPanel box_botones;
    private JPanel box_top;
    private JButton btnReturn;
    private JTextField inpNombre;
    private JTextField inpFechaCreacion;
    private JTextField inpNombreEncargado;
    private JTextField inpNumTrabajadores;
    private JTextField inpNumDeSubDepar;
    private JTextField inpIDDepartamento;
    private JLabel icono;
    private JLabel txtTittle;
    private JLabel txtNumSubDpto;
    private JLabel txtIdDpto;
    private JLabel txtNumTrabajadores;
    private JLabel txtNombreEncargado;
    private JLabel txtFechaCreacion;
    private JLabel txtNombre;

    public DepartamentoRSS() {
        setContentPane(WinRSS);
        styles();
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
        WinRSS.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JButton> listaBtns = Arrays.asList(btnReturn);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_top, WinRSS);
        List<JLabel> listaTexto = Arrays.asList(txtFechaCreacion, txtTittle, txtIdDpto, txtNombre,txtNombreEncargado, txtNumSubDpto, txtNumTrabajadores);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
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
        DepartamentoRSS dialog = new DepartamentoRSS();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
