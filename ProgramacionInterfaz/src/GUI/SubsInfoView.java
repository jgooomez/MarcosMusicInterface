package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class SubsInfoView extends JDialog {
    private JPanel viewInfoBase;
    private JLabel txtTitle;
    private JButton btnReturn;
    private JTextField outPrecio;
    private JLabel txtDescripcion;
    private JLabel txtPrecio;
    private JTextArea outDescripcion;

    private void onCancel() {
        dispose();
    }

    public SubsInfoView(String tipo, double precio, String descripcion) {
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

        txtTitle.setText(tipo);
        outDescripcion.setText(descripcion);
        outPrecio.setText(String.valueOf(precio) + "€");

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
        txtTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        txtDescripcion.setForeground(Color.WHITE);
        txtPrecio.setForeground(Color.WHITE);
        java.util.List<JButton> listaBtns = Arrays.asList(btnReturn);
        java.util.List<JPanel> listaPaneles = Arrays.asList(viewInfoBase);
        List<JLabel> listaTexto = Arrays.asList(txtTitle);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }
}
