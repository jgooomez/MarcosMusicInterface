package GUI;

import DBManager.DBManagerUsuarios;
import ClasePOJO.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Usuarios extends JDialog {
    private JPanel WinUsuarios;
    private JButton btnBuscar;
    private JButton btnCancel;
    private JTextField inpIdUsr;
    private JTextField outpNacionalidad;
    private JTextField outpNombre;
    private JTextField outpEdad;
    private JTextField outpNumSeguidores;
    private JButton btnAddUser;
    private JLabel txtTittle;

    private JPanel box_botones;
    private JPanel box_top;
    private JButton btnDeleteUsr;
    private JLabel icono;
    private JLabel txtNumSeguidores;
    private JLabel txtFotoPerfil;
    private JLabel txtEdad;
    private JLabel txtNombre;
    private JLabel txtNacionalidad;
    private JLabel txtIdUsr;

    public Usuarios() {
        styles();
        // Desactivar el JTextField al inicio
        inpIdUsr.setEnabled(false);
        btnBuscar.setEnabled(false);

        // Agregar el MouseListener al JTextField
        inpIdUsr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inpIdUsr.setEnabled(true);  // Habilitar el JTextField al hacer click
            }
        });
        // Establecer el texto de pista y el color inicial del JTextField
        setTextInToInpIdUsr();

        setContentPane(WinUsuarios);
        setModal(true);
        getRootPane().setDefaultButton(btnBuscar);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        WinUsuarios.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Añadir la funcionalidad de cada botón
        setListenersBtns();
    }

    private void styles() {
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        txtTittle.setFont(MarcosMusic.getFontTitle());

        btnBuscar.setFocusable(false);
        btnBuscar.setBackground(MarcosMusic.getBtnColor());
        btnBuscar.setCursor(cursor);

        btnAddUser.setFocusable(false);
        btnAddUser.setBackground(MarcosMusic.getBtnColor());
        btnAddUser.setCursor(cursor);

        btnCancel.setFocusable(false);
        btnCancel.setBackground(MarcosMusic.getBtnColor());
        btnCancel.setCursor(cursor);
    }

    private void setListenersBtns() {
        listenerBtnBuscar();
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new AgregarUsuario();
                dialog.setTitle("Agregar Usuario");
                dialog.setSize(300, 300);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        btnDeleteUsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrarUsuario();
            }
        });
    }

    private void listenerBtnBuscar() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Usuario> usuarios = DBManagerUsuarios.obtenerUsuarios();

                // Buscar usuario por ID
                Usuario usuarioBuscado = null;

                for (Usuario usuario : usuarios) {
                    if (usuario.getId() == Integer.parseInt(inpIdUsr.getText())) {
                        usuarioBuscado = usuario;
                        break;
                    }
                }

                if (usuarioBuscado != null) {
                    // Mostrar los datos del usuario
                    outpNacionalidad.setText(usuarioBuscado.getNacionalidad());
                    outpNombre.setText(usuarioBuscado.getNombre());
                    outpEdad.setText(Integer.toString(usuarioBuscado.getEdad()));
                    outpNumSeguidores.setText(Integer.toString(usuarioBuscado.getNumSeguidores()));
                } else {
                    System.out.println("No se encontró ningún usuario con el ID: " + inpIdUsr);
                }
            }
        });
    }

    private void setTextInToInpIdUsr() {
        String textoPista = "Introduzca el ID";
        inpIdUsr.setText(textoPista);
        inpIdUsr.setForeground(Color.GRAY);
        inpIdUsr.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inpIdUsr.getText().equals(textoPista)) {
                    inpIdUsr.setText("");
                    inpIdUsr.setForeground(Color.BLACK);
                    btnBuscar.setEnabled(true);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inpIdUsr.getText().isEmpty()) {
                    inpIdUsr.setText(textoPista);
                    inpIdUsr.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        Usuarios dialog = new Usuarios();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
