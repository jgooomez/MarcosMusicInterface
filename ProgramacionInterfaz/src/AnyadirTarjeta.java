import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnyadirTarjeta extends JDialog {
    private JPanel contentPane;
    private JButton buttonConfirmar;
    private JRadioButton visaRadioButton;
    private JRadioButton mastercardRadioButton;
    private JTextField textFieldNumTarjeta;
    private JTextField textFieldTelefono;
    private JTextField textFieldNombreTitular;
    private JTextField CVVInput;
    private JTextField textFieldCaducidad;
    private JButton volverButton;
    private JButton buttonCancel;


    public AnyadirTarjeta() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonConfirmar);
        // call onCancel() when cross is clicked

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (compruebaTarjeta(textFieldNumTarjeta, CVVInput, textFieldNombreTitular, textFieldCaducidad, visaRadioButton, mastercardRadioButton)) {
                    //Hacer el insert en la BBDD.
                }
            }
        });

    }

    public static boolean compruebaTarjeta(JTextField textFieldNumTarjeta, JTextField CVVInput, JTextField textFieldNombreTitular,
                                           JTextField textFieldCaducidad, JRadioButton visaRadioButton, JRadioButton mastercardRadioButton ) {
        boolean isValid = true;
        if (!(textFieldNumTarjeta.getText().matches("^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$"))) {
            JOptionPane.showMessageDialog(null, "El numero de tarjeta debe seguir el siguiente formato XXXX-XXXX-XXXX-XXXX.");
            textFieldNumTarjeta.setText("");
            isValid = false;
        } else if (!(String.valueOf(CVVInput).length() == 3)) {
            JOptionPane.showMessageDialog(null, "El CVV debe tener exactamente 3 digitos.");
            CVVInput.setText("");
            isValid = false;
        } else if (textFieldNombreTitular.getText().length() < 3) {
            JOptionPane.showMessageDialog(null, "Nombre de titular no valido, debe tener minimo tres caracteres.");
            isValid = false;
        } else if (!(textFieldCaducidad.getText().matches("^(0[1-9]|1[0-2])/(0[1-9]|1[0-9]|2[0-9]|3[01])$"))) {
            JOptionPane.showMessageDialog(null, "Fecha caducidad incorrecta, formato correcto MM/DD");
            isValid = false;
        } else if (!(mastercardRadioButton.isSelected() || visaRadioButton.isSelected())) {
            JOptionPane.showMessageDialog(null, "Seleccione un metodo de pago.");
            isValid = false;
        }

        return isValid;
    }



    public static void main(String[] args) {
        AnyadirTarjeta dialog = new AnyadirTarjeta();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
