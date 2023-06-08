package GUI;

import ClasePOJO.Tarjeta;
import DBManager.DBManagerTarjetas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa la interfaz gráfica para administrar tarjetas.
 */
public class AdministrarTarjetas extends JDialog {
    private JPanel WinAdminTarjetas;
    private JPanel box_botones;
    private JPanel box_inputs;
    private JTextField inpIdUsr;
    private JTextField outpNombre;
    private JTextField outpNumTarjeta;
    private JButton btnDelete;
    private JButton btnAdd;
    private JButton btnReturn;
    private JLabel txtAdminTarjetas;
    private JLabel txtNombre;
    private JLabel txtNumTarjeta;
    private JLabel txtIdUsr;
    private JLabel icon;
    private JButton btnBuscar;

    /**
     * Constructor de la clase AdministrarTarjetas.
     * Inicializa la interfaz gráfica y configura los eventos de los botones.
     */
    public AdministrarTarjetas() {
        setContentPane(WinAdminTarjetas);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(btnDelete);
        outpNombre.setEnabled(false);
        outpNumTarjeta.setEnabled(false);

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        /**
         * Configura el comportamiento al hacer clic en la cruz de cierre.
         * Llama al método onCancel().
         */
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        /**
         * Configura el comportamiento al presionar la tecla ESCAPE.
         * Llama al método onCancel().
         */
        WinAdminTarjetas.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setListenersBtns();
        setListenerBtnBuscar();
    }

    /**
     * Metodo que busca una tarjeta por el id de usuario y rellena los campos con la información necesaria.
     * @param idUsuario
     */
    private void setListenerBtnBuscar() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Tarjeta> tarjetas = DBManagerTarjetas.obtenerTarjeta();

                // Buscar usuario por ID
                Tarjeta tarjetaBuscada = null;

                for (Tarjeta tarjeta : tarjetas) {
                    if (tarjeta.getIdUsuario() == Integer.parseInt(inpIdUsr.getText())) {
                        tarjetaBuscada = tarjeta;
                        break;
                    }
                }

                if (tarjetaBuscada != null) {
                    // Mostrar los datos del usuario
                    outpNombre.setText(tarjetaBuscada.getNombreTitular());
                    outpNumTarjeta.setText(tarjetaBuscada.getNumeroTarjeta());
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna tarjeta con ese ID en la BBDD", "Error en el ID", JOptionPane.ERROR_MESSAGE);
                    inpIdUsr.setText("");
                }
            }
        });
    }

    /**
     * Configura los listeners de los botones.
     */
    private void setListenersBtns() {
        /**
         * Configura el comportamiento del botón "Return".
         * Llama al método onCancel().
         */
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        /**
         * Configura el comportamiento del botón "Delete".
         * Muestra un cuadro de diálogo de confirmación y elimina la tarjeta si se confirma.
         * Si se cancela, cierra la ventana actual.
         */
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showOptionDialog(null, "¿Estás seguro de que quieres borrar la tarjeta con número: " + outpNumTarjeta.getText(), "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new String[]{"Confirmar", "Cancelar"}, "Confirmar");

                if (opcion == JOptionPane.YES_OPTION) {
                    if (DBManagerTarjetas.deleteTarjetasUsuario(Integer.parseInt(inpIdUsr.getText()))) {
                        JOptionPane.showMessageDialog(null, "Se ha eliminado la tarjeta con éxito.", "Operación completada", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna tarjeta con ese ID en la BBDD", "Error en el ID", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (opcion == JOptionPane.NO_OPTION) {
                    onCancel();
                }
            }
        });
    }

    /**
     * Configura los estilos visuales de los elementos de la interfaz.
     */
    private void styles() {
        txtAdminTarjetas.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JLabel> listaTexto = Arrays.asList(txtAdminTarjetas, txtNombre, txtIdUsr, txtNumTarjeta);
        List<JButton> listaBtns = Arrays.asList(btnDelete, btnReturn, btnBuscar);
        List<JPanel> listaPaneles = Arrays.asList(WinAdminTarjetas, box_botones, box_inputs);
        List<JTextField> listaOutPuts = Arrays.asList(outpNumTarjeta, outpNombre);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
        MarcosMusic.stylesOutPutText(listaOutPuts);
    }

    /**
     * Método invocado cuando se presiona el botón "OK".
     * Agrega tu código aquí.
     */
    private void onOK() {
        dispose();
    }

    /**
     * Método invocado cuando se cancela la operación.
     * Agrega tu código aquí si es necesario.
     */
    private void onCancel() {
        this.setVisible(false);
        MarcosMusic.frame.setVisible(true);
        dispose();
    }
}
