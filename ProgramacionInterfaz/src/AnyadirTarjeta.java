import javax.swing.*;
import java.awt.event.*;

public class AnyadirTarjeta extends JDialog {
    private JPanel contentPane;
    private JButton buttonConfirmar;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JTextField textFieldNumTarjeta;
    private JTextField textFieldTelefono;
    private JTextField textFieldNombreTitular;
    private JTextField textFieldCVV;
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

            }
        });
    }



    public static void main(String[] args) {
        AnyadirTarjeta dialog = new AnyadirTarjeta();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
