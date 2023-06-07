package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class SubsInfoView extends JDialog {
    private JPanel viewInfoBase;
    private JLabel txtTittle;
    private JButton btnReturn;
    private JTextField outPrecio;
    private JTextArea uotDescripcion;
    private JLabel txtDescripcion;
    private JLabel txtPrecio;

    private void onCancel() {
        dispose();
    }

    public SubsInfoView(String tipo, double precio, String descripcion:) {
        styles();
        setContentPane(viewInfoBase);
        setModal(true);
        getRootPane().setDefaultButton(btnReturn);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        txtTittle.setText(tipo);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(String.valueOf(precio));

        // call onCancel() on ESCAPE
        viewInfoBase.registerKeyboardAction(new ActionListener() {
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

        // Añadir la funcionalidad de cada botón
        setListenersBtns();
    }
    private void setListenersBtns() {}
    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        java.util.List<JButton> listaBtns = Arrays.asList();
        java.util.List<JPanel> listaPaneles = Arrays.asList(viewInfoBase);
        List<JLabel> listaTexto = Arrays.asList(txtTittle);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SubsInfoView");
        frame.setContentPane(new SubsInfoView("test", 0.1, "uwu").viewInfoBase);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
