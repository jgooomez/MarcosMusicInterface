package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnyadirTarjeta extends JDialog {
    private JPanel contentPane;
    private JButton btnConfirmar;
    private JRadioButton btnVisaRadio;
    private JRadioButton btnMastercardRadio;
    private JTextField inpNumTarjeta;
    private JTextField inpTlf;
    private JTextField inpNombreTitular;
    private JTextField inpCVV;
    private JTextField inpCaducidad;
    private JButton btnReturn;
    private JLabel txtAgregarTarjetaCredito;
    private JTextField inpIDUsuario;
    private JLabel idUsuario;

    public static void main(String[] args) {
        AnyadirTarjeta dialog = new AnyadirTarjeta();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    public AnyadirTarjeta() {
        styles();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnConfirmar);

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (compruebaTarjeta()) {
                    //Hacer el insert en la BBDD.
                }
            }
        });

    }

    private void styles() {
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        txtAgregarTarjetaCredito.setFont(new Font("Calibri", Font.BOLD, 25));

        btnVisaRadio.setFocusable(false);
        btnVisaRadio.setCursor(cursor);
        btnMastercardRadio.setFocusable(false);
        btnMastercardRadio.setCursor(cursor);
        btnConfirmar.setFocusable(false);
        btnConfirmar.setBackground(MarcosMusic.getBtnColor());
        btnConfirmar.setCursor(cursor);
        btnReturn.setFocusable(false);
        btnReturn.setBackground(MarcosMusic.getBtnColor());
        btnReturn.setCursor(cursor);
    }

    public boolean compruebaTarjeta() {
        boolean isValid = true;
        if (!(inpNumTarjeta.getText().matches("^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$"))) {
            JOptionPane.showMessageDialog(null, "El numero de tarjeta debe seguir el siguiente formato XXXX-XXXX-XXXX-XXXX.", "Error en el formato", JOptionPane.ERROR_MESSAGE);
            inpNumTarjeta.setText("");
            isValid = false;
        } else if (!(btnMastercardRadio.isSelected() || btnVisaRadio.isSelected())) {
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
}
