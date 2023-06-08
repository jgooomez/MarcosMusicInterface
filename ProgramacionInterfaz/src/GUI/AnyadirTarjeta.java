package GUI;

import DBManager.DBManagerTarjetas;
import DBManager.DBManagerUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AnyadirTarjeta extends JDialog {
    private JPanel box_datosTarjeta;
    private JTextField inpCVV;
    private JTextField inpNombreTitular;
    private JRadioButton rbtnVisaRadio;
    private JTextField inpNumTarjeta;
    private JLabel txtCVV;
    private JLabel txtNombreTitular;
    private JRadioButton rbtnMastercardRadio;
    private JLabel txtTipo;
    private JLabel txtVisa;
    private JLabel txtMastercard;
    private JLabel txtNumTarjeta;
    private JLabel txtTittle;
    private JLabel txtCad;
    private JTextField inpCaducidad;
    private JLabel txtTlf;
    private JTextField inpTlf;
    private JPanel winAddTarjeta;
    private JButton btnAddTarjeta;
    private JButton btnReturn;
    private JPanel box_btns;
    private int idUser;

    public AnyadirTarjeta(int idUser) {
        this.idUser = idUser;
        setContentPane(winAddTarjeta);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(btnAddTarjeta);

        setListenersBtns();
    }

    private void setListenersBtns() {
        listenerRadioBtns();
        addTarjeta();
        btnReturn.addActionListener(new ActionListener() {
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
        winAddTarjeta.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Método que tras comprobar que los datos de la tarjeta sean válidos, inserta la tarjeta en la BBDD.
     */
    private void addTarjeta() {
        btnAddTarjeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipo = "";
                if (rbtnMastercardRadio.isSelected()) {
                    tipo = "Mastercard";
                } else {
                    tipo = "Visa";
                }

                if (compruebaTarjeta(idUser)) {
                    String numeroSinGuiones = inpNumTarjeta.getText().replace("-", "");
                    reescribirTarjeta();
                    if (DBManagerTarjetas.insertTarjeta(numeroSinGuiones, Integer.parseInt(inpTlf.getText()), tipo, inpNombreTitular.getText(), Integer.parseInt(inpCVV.getText()),inpCaducidad.getText(), idUser)) {
                        JOptionPane.showMessageDialog(null, "El insert se realizó correctamente.");
                        onCancel();
                    } else {
                        JOptionPane.showMessageDialog(null, "El insert no se ha podido realizar.", "Insert incorrecto", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void listenerRadioBtns() {
        rbtnVisaRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rbtnVisaRadio.isSelected()) {
                    rbtnVisaRadio.setSelected(false);
                }
                if (rbtnMastercardRadio.isSelected()) {
                    rbtnMastercardRadio.setSelected(false);
                }
            }
        });

        rbtnMastercardRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rbtnMastercardRadio.isSelected()) {
                    rbtnMastercardRadio.setSelected(false);
                }
                if (rbtnVisaRadio.isSelected()) {
                    rbtnVisaRadio.setSelected(false);
                }
            }
        });
    }

    private void reescribirTarjeta() {
        if (DBManagerTarjetas.deleteTarjetasUsuario(idUser)) {
            System.out.println("Se ha eliminado la tarjeta");
        } else {
            System.out.println("No tiene tarjeta asociada");
        }
    }

    public boolean compruebaTarjeta(int idUser) {
        boolean isValid = true;
        if (!(inpNumTarjeta.getText().matches("^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$"))) {
            JOptionPane.showMessageDialog(null, "El número de tarjeta debe seguir el siguiente formato XXXX-XXXX-XXXX-XXXX.", "Error en el formato", JOptionPane.ERROR_MESSAGE);
            inpNumTarjeta.setText("");
            isValid = false;
        } else if (!(rbtnMastercardRadio.isSelected() || rbtnVisaRadio.isSelected())) {
            JOptionPane.showMessageDialog(null, "Seleccione un método de pago.");
            isValid = false;
        } else if (inpNombreTitular.getText().length() < 3) {
            JOptionPane.showMessageDialog(null, "Nombre de titular no válido, debe tener mínimo tres caracteres.");
            isValid = false;
        } else if ((String.valueOf(inpCVV.getText()).length() != 3)) {
            JOptionPane.showMessageDialog(null, "El CVV debe tener exactamente 3 dígitos.");
            inpCVV.setText("");
            isValid = false;
        } else if (!(inpCaducidad.getText().matches("^(0[1-9]|1[0-2])/(0[1-9]|1[0-9]|2[0-9]|3[01])$"))) {
            JOptionPane.showMessageDialog(null, "Fecha de caducidad incorrecta, formato correcto MM/DD.");
            isValid = false;
        } else if (this.idUser == 0) {
            JOptionPane.showMessageDialog(null, "Error. No se ha podido encontrar el ID del usuario.");
            isValid = false;
        }
        return isValid;
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        rbtnVisaRadio.setBackground(new Color(40, 40, 40));
        rbtnMastercardRadio.setBackground(new Color(40, 40, 40));
        java.util.List<JButton> listaBtns = Arrays.asList(btnReturn, btnAddTarjeta);
        java.util.List<JPanel> listaPaneles = Arrays.asList(winAddTarjeta, box_btns, box_datosTarjeta);
        List<JLabel> listaTexto = Arrays.asList(txtCad, txtCVV, txtTlf, txtNombreTitular, txtTipo, txtVisa, txtMastercard, txtNumTarjeta, txtTittle);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public int idUsuario;

    /*public static void main(String[] args) {
        int idUser = DBManagerUsuarios.getIdUser();
        AnyadirTarjeta dialog = new AnyadirTarjeta(idUser);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }*/
}
