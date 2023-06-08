package GUI;

import ClasePOJO.Artista;
import DBManager.DBManagerArtista;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BorrarArtista extends JDialog {
    private JPanel winDeleteArtista;
    private JButton btnBorrar;
    private JButton btnCancelar;
    private JComboBox borrarArtistaComboBox;
    private JPanel box_btns;

    public BorrarArtista() {
        setContentPane(winDeleteArtista);
        setModal(true);
        getRootPane().setDefaultButton(btnBorrar);
        loadArtistas();
        listenerBtnBorrar();
        listenerCerrrarVentana();
    }

    private void listenerCerrrarVentana() {
        btnCancelar.addActionListener(new ActionListener() {
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
        winDeleteArtista.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void listenerBtnBorrar() {
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el departamento seleccionado del JComboBox
                String selectedItem = (String) borrarArtistaComboBox.getSelectedItem();
                if (selectedItem != null) {
                    // Obtener el ID del departamento seleccionado
                    int selectedId = getIdFromSelectedItem(selectedItem);

                    // Obtener los datos del departamento seleccionado
                    Artista artista = getArtistaById(selectedId);

                    if (DBManagerArtista.deleteArtista(artista.getIdArtista())){
                        JOptionPane.showMessageDialog(btnBorrar, "Artista eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(btnBorrar, "Error al eliminar el artista", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
                loadArtistas();
            }
        });
    }

    //Coger el id del item comboBox seleccionado
    private int getIdFromSelectedItem(String selectedItem) {
        // Extraer el ID del departamento seleccionado del texto en el JComboBox
        String[] parts = selectedItem.split(" - ");
        return Integer.parseInt(parts[0]);
    }
    private Artista getArtistaById(int id) {
        DBManagerArtista dbManager = new DBManagerArtista();
        return dbManager.getArtistaById(id);
    }
    private void loadArtistas() {

        ArrayList<ClasePOJO.Artista> artistas = DBManagerArtista.obtenerDatosArtista();

        // Limpiar el JComboBox
        borrarArtistaComboBox.removeAllItems();

        // Agregar los departamentos al JComboBox
        for (Artista artista : artistas) {
            borrarArtistaComboBox.addItem(artista.getIdArtista() + " - " + artista.getNombre());
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
