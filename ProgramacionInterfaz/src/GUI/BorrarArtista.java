package GUI;

import ClasePOJO.Artista;
import ClasePOJO.Departamento;
import DBManager.DBManagerArtista;
import DBManager.DBManagerDepartamento;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BorrarArtista extends JDialog {
    private JPanel contentPane;
    private JButton btnBorrar;
    private JButton btnCancelar;
    private JComboBox borrarArtistaComboBox;

    public BorrarArtista() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnBorrar);
        loadArtistas();
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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

    public static void main(String[] args) {
        BorrarArtista dialog = new BorrarArtista();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
