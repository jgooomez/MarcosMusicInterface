import javax.swing.*;
import java.awt.event.*;

public class AnyadirTarjeta extends JDialog {
    private JPanel contentPane;
    private JButton confirmarButton;
    private JRadioButton visaRadio;
    private JRadioButton mastercardRadio;
    private JTextField NumTarjetaInput;
    private JTextField TelefonoInput;
    private JTextField TitularInput;
    private JTextField CVVInput;
    private JTextField CaducidadInput;
    private JButton volverButton;
    private JButton buttonCancel;


    public AnyadirTarjeta() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(confirmarButton);
        // call onCancel() when cross is clicked

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
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
