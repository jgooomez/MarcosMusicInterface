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
        configurarBotones();

    }
    private void configurarImagenDeFondo() {
        // Crear un JLabel para contener la imagen de fondo
        JLabel fondo = new JLabel(new ImageIcon("ruta/a/la/imagen/fondo.jpg"));

        // Establecer el tamaño y la posición del JLabel
        fondo.setBounds(0, 0, getWidth(), getHeight());

        // Agregar el JLabel al panel principal
        principal.add(fondo);
    }

    private void configurarBotones(){
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
        departamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new Departamentos();
                dialogo2.setTitle("Departamentos");
                dialogo2.setSize(400,400);
                dialogo2.setLocationRelativeTo(null);
                dialogo2.setVisible(true);
            }
        });

        empleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogo2 = new Empleados();
                dialogo2.setTitle("Ver Empleados");
                dialogo2.setSize(400,400);
                dialogo2.setLocationRelativeTo(null);
                dialogo2.setVisible(true);
            }
        });
    }
}
