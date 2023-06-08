package GUI;

import DBManager.DBManagerArtista;

import javax.swing.*;
import java.awt.event.*;

public class BorrarArtista extends JDialog {
    private JButton btnBorrar;
    private JButton btnCancelar;
    private JTextField inpId;
    private JPanel winDeleteArtista;
    private JPanel box_btns;
    private JComboBox borrarArtistaComboBox;

    public BorrarArtista() {
        setContentPane(winDeleteArtista);
        setModal(true);
        getRootPane().setDefaultButton(btnBorrar);

        btnBorrar.addActionListener(e -> {

            int id = Integer.parseInt(inpId.getText());

            if (DBManagerArtista.deleteArtista(id)) {
                JOptionPane.showMessageDialog(btnBorrar, "ArtistaView eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(btnBorrar, "Error al eliminar el artista", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        winDeleteArtista.registerKeyboardAction(new ActionListener() {
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
        BorrarArtista dialog = new BorrarArtista();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
