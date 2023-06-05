package GUI;

import DBManager.DBManagerTarjetas;
import DBManager.DBManagerUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class FormUsuario extends JDialog {
    private JPanel winFormUsr;
    private JPanel datosUsr;
    private JLabel txtDatosPersonales;
    private JLabel txtNombre;
    private JLabel txtEdad;
    private JLabel txtNacionalidad;
    private JLabel txtNumSeguidores;
    private JTextField inpNombre;
    private JTextField inpEdad;
    private JTextField inpNacionalidad;
    private JTextField inpNumSeguidores;
    private JPanel datosTarjeta;
    private JLabel txtTarjeta;
    private JTextField inpCVV;
    private JTextField inpCaducidad;
    private JTextField inpNombreTitular;
    private JTextField inpTlf;
    private JTextField inpNumTarjeta;
    private JRadioButton rbtnVisaRadio;
    private JRadioButton rbtnMastercardRadio;
    private JButton btnAddUsr;
    private JButton btnCancel;
    private JPanel box_inputsUsr;
    private JPanel box_btns;
    private JPanel box_tittle;

    public FormUsuario() {
        setContentPane(winFormUsr);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(btnAddUsr);
        setListenersBtns();
    }

    private void styles() {
        List<JButton> listaBtns = Arrays.asList(btnAddUsr, btnCancel);
        MarcosMusic.stylesBtns(listaBtns);
    }

    private void setListenersBtns() {
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

        btnAddUsr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipo = "";
                if (rbtnMastercardRadio.isSelected()) {
                    tipo = "Mastercard";
                } else {
                    tipo = "Visa";
                }

                if (compruebaUsuario() && compruebaTarjeta()) {
                    if (DBManagerUsuarios.insertUsuario(inpNacionalidad.getText(), inpNombre.getText(), Integer.parseInt(inpEdad.getText()), Integer.parseInt(inpNumSeguidores.getText()))) {
                        JOptionPane.showMessageDialog(null, "El insert se realizo correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "El insert no se ha podido realizar el insert", "Insert incorrecto", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
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
        winFormUsr.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public boolean compruebaUsuario() {
        boolean isValid = true;

        if (inpNacionalidad.getText().length() == 0 || inpNombre.getText().length() == 0
                || inpEdad.getText().length() == 0 || inpNumSeguidores.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser rellenados.", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        } else if (Integer.parseInt(inpEdad.getText()) < 12) {
            JOptionPane.showMessageDialog(null, "El usuario debe ser mayor de 18 años.", "Error", JOptionPane.ERROR_MESSAGE);
            inpEdad.setText("");
            isValid = false;
        }

        return isValid;
    }

    public boolean compruebaTarjeta() {
        boolean isValid = true;
        if (!(inpNumTarjeta.getText().matches("^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$"))) {
            JOptionPane.showMessageDialog(null, "El numero de tarjeta debe seguir el siguiente formato XXXX-XXXX-XXXX-XXXX.", "Error en el formato", JOptionPane.ERROR_MESSAGE);
            inpNumTarjeta.setText("");
            isValid = false;
        } else if (!(rbtnMastercardRadio.isSelected() || rbtnVisaRadio.isSelected())) {
            JOptionPane.showMessageDialog(null, "Seleccione un metodo de pago.");
            isValid = false;
        } else if (inpNombreTitular.getText().length() < 3) {
            JOptionPane.showMessageDialog(null, "Nombre de titular no valido, debe tener minimo tres caracteres.");
            isValid = false;
        } else if ((String.valueOf(inpCVV.getText()).length() != 3)) {
            JOptionPane.showMessageDialog(null, "El CVV debe tener exactamente 3 digitos.");
            inpCVV.setText("");
            isValid = false;
        } else if (!(inpCaducidad.getText().matches("^(0[1-9]|1[0-2])/(0[1-9]|1[0-9]|2[0-9]|3[01])$"))) {
            JOptionPane.showMessageDialog(null, "Fecha caducidad incorrecta, formato correcto MM/DD");
            isValid = false;
        }
        return isValid;
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
        FormUsuario dialog = new FormUsuario();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
