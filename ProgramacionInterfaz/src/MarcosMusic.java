import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarcosMusic extends JFrame {
    private JPanel principal;
    private JButton suscripcionButton;
    private JButton verUsuariosButton;
    private JButton departamentoButton;
    private JButton tarjetasButton;
    private JButton empleadosButton;
    static JFrame frame = new JFrame("MarcosMusic");
    public static void main(String[] args) {

        frame.setContentPane(new MarcosMusic().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public MarcosMusic() {
        verUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo1 = new VerUsu();
                dialogo1.setTitle("Vista de usuarios");
                dialogo1.setSize(400,700);
                dialogo1.setLocationRelativeTo(null);
                dialogo1.setVisible(true);
            }
        });
        tarjetasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new AdministrarTarjetas();
                dialogo2.setTitle("Administracion de tarjetas");
                dialogo2.setSize(400,400);
                dialogo2.setLocationRelativeTo(null);
                dialogo2.setVisible(true);
            }
        });
        suscripcionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new Suscripciones();
                dialogo2.setTitle("Tipo de suscripciones");
                dialogo2.setSize(400,400);
                dialogo2.setLocationRelativeTo(null);
                dialogo2.setVisible(true);
            }
        });
    }
}
