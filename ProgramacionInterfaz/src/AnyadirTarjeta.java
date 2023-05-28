import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnyadirTarjeta extends JDialog {
    private JPanel contentPane;
    private JButton btnConfirmar;
    private JRadioButton btnVisaRadio;
    private JRadioButton btnMartercardRadio;
    private JTextField inpNumTarjeta;
    private JTextField inpTlf;
    private JTextField inpNombreTitular;
    private JTextField inpCVV;
    private JTextField inpCaducidad;
    private JButton btnReturn;
    private JLabel txtAgregarTarjetaCredito;

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
                if (compruebaTarjeta(inpNumTarjeta, inpCVV, inpNombreTitular, inpCaducidad, btnVisaRadio, btnMartercardRadio)) {
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
        btnMartercardRadio.setFocusable(false);
        btnMartercardRadio.setCursor(cursor);
        btnConfirmar.setFocusable(false);
        btnConfirmar.setBackground(MarcosMusic.getBtnColor());
        btnConfirmar.setCursor(cursor);
        btnReturn.setFocusable(false);
        btnReturn.setBackground(MarcosMusic.getBtnColor());
        btnReturn.setCursor(cursor);
    }

    public static boolean compruebaTarjeta(JTextField textFieldNumTarjeta, JTextField CVVInput, JTextField textFieldNombreTitular,
                                           JTextField textFieldCaducidad, JRadioButton visaRadioButton, JRadioButton mastercardRadioButton ) {
        boolean isValid = true;
        if (!(textFieldNumTarjeta.getText().matches("^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$"))) {
            JOptionPane.showMessageDialog(null, "El numero de tarjeta debe seguir el siguiente formato XXXX-XXXX-XXXX-XXXX.");
            textFieldNumTarjeta.setText("");
            isValid = false;
        } else if (!(mastercardRadioButton.isSelected() || visaRadioButton.isSelected())) {
            JOptionPane.showMessageDialog(null, "Seleccione un metodo de pago.");
            isValid = false;
        } else if (textFieldNombreTitular.getText().length() < 3) {
            JOptionPane.showMessageDialog(null, "Nombre de titular no valido, debe tener minimo tres caracteres.");
            isValid = false;
        } else if ((String.valueOf(CVVInput.getText()).length() != 3)) {
            JOptionPane.showMessageDialog(null, "El CVV debe tener exactamente 3 digitos.");
            CVVInput.setText("");
            isValid = false;
        } else if (!(textFieldCaducidad.getText().matches("^(0[1-9]|1[0-2])/(0[1-9]|1[0-9]|2[0-9]|3[01])$"))) {
            JOptionPane.showMessageDialog(null, "Fecha caducidad incorrecta, formato correcto MM/DD");
            isValid = false;
        }
        return isValid;
    }
}
