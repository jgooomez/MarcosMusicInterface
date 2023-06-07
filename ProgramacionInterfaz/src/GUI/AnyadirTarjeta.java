package GUI;

import DBManager.DBManagerTarjetas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class AnyadirTarjeta extends JDialog {
    private JPanel principal;
    private JPanel box_botones;
    private JPanel box_top;
    private JTextField inpTlf;
    private JButton btnConfirmar;
    private JRadioButton rbtnVisaRadio;
    private JRadioButton rbtnMastercardRadio;
    private JTextField inpNumTarjeta;
    private JTextField inpNombreTitular;
    private JTextField inpCVV;
    private JTextField inpCaducidad;
    private JButton btnReturn;
    private JLabel txtTittle;
    private JTextField inpIDUsuario;
    private JLabel idUsuario;
    private JLabel icono;
    private JPanel box_btns;
    private JPanel box_inputs;

    /**
     * Método principal para ejecutar la ventana de AnyadirTarjeta.
     * Crea una instancia de la clase y la muestra.
     */
    public static void main(String[] args) {
        AnyadirTarjeta dialog = new AnyadirTarjeta();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    /**
     * Constructor de la clase AnyadirTarjeta.
     * Configura la apariencia de la ventana y los eventos de los botones.
     */
    public AnyadirTarjeta() {
        styles();  // Aplica estilos a la ventana
        setContentPane(principal);  // Establece el panel principal como el contenido de la ventana
        setModal(true);  // Configura la ventana como modal (bloquea la interacción con otras ventanas)
        getRootPane().setDefaultButton(btnConfirmar);  // Establece el botón "Confirmar" como el botón predeterminado

        // Configura el comportamiento del botón "Volver"
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cierra la ventana actual
            }
        });

        // Configura el comportamiento del botón "Confirmar"
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "";

                if (rbtnMastercardRadio.isSelected()) {
                    tipo = "Mastercard";
                } else {
                    tipo = "Visa";
                }

                // Comprueba la validez de los datos de la tarjeta
                if (compruebaTarjeta()) {
                    // Elimina los guiones del número de tarjeta
                    String numTarjetaParseado = (inpNumTarjeta.getText().replace("-", ""));

                    // Inserta los datos de la tarjeta en la base de datos utilizando DBManagerTarjetas
                    if (DBManagerTarjetas.insertTarjeta(numTarjetaParseado, Integer.parseInt(inpTlf.getText()), tipo, inpNombreTitular.getText(), Integer.parseInt(inpCVV.getText()), inpCaducidad.getText())) {
                        JOptionPane.showMessageDialog(null, "Insert realizado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al realizar el insert.");
                    }
                }
            }
        });
    }

    /**
     * Aplica estilos personalizados a la ventana.
     * Configura el tamaño y fuente del título y aplica estilos a los botones.
     */
    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));  // Establece la fuente y tamaño del título

        // Crea una lista de botones
        List<JButton> listaBtns = Arrays.asList(btnConfirmar, btnReturn);

        // Aplica estilos personalizados a los botones utilizando el método "stylesBtns" de la clase MarcosMusic
        MarcosMusic.stylesBtns(listaBtns);
    }

    /**
     * Comprueba la validez de los datos de la tarjeta.
     * Verifica el formato del número de tarjeta, la selección del método de pago, la longitud del nombre del titular,
     * la longitud del CVV y el formato de la fecha de caducidad.
     * Muestra mensajes de error en caso de que los datos sean inválidos.
     * @return true si los datos son válidos, false en caso contrario.
     */
    public boolean compruebaTarjeta() {
        boolean isValid = true;

        // Verifica el formato del número de tarjeta utilizando una expresión regular
        if (!(inpNumTarjeta.getText().matches("^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$"))) {
            JOptionPane.showMessageDialog(null, "El numero de tarjeta debe seguir el siguiente formato XXXX-XXXX-XXXX-XXXX.", "Error en el formato", JOptionPane.ERROR_MESSAGE);
            inpNumTarjeta.setText("");
            isValid = false;
        } else if (!(rbtnMastercardRadio.isSelected() || rbtnVisaRadio.isSelected())) {  // Verifica si se ha seleccionado un método de pago
            JOptionPane.showMessageDialog(null, "Seleccione un metodo de pago.");
            isValid = false;
        } else if (inpNombreTitular.getText().length() < 3) {  // Verifica la longitud del nombre del titular
            JOptionPane.showMessageDialog(null, "Nombre de titular no valido, debe tener minimo tres caracteres.");
            isValid = false;
        } else if ((String.valueOf(inpCVV.getText()).length() != 3)) {  // Verifica la longitud del CVV
            JOptionPane.showMessageDialog(null, "El CVV debe tener exactamente 3 digitos.");
            inpCVV.setText("");
            isValid = false;
        } else if (!(inpCaducidad.getText().matches("^(0[1-9]|1[0-2])/(0[1-9]|1[0-9]|2[0-9]|3[01])$"))) {  // Verifica el formato de la fecha de caducidad
            JOptionPane.showMessageDialog(null, "Fecha caducidad incorrecta, formato correcto MM/DD");
            isValid = false;
        }

        return isValid;
    }
}
