package GUI;

import DBManager.DBManagerReproduccion;
import DBManager.DBManagerUsuarios;
import ClasePOJO.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public JButton getBtnAddUser() {
        return btnAddUser;
    }

    /**
     * Crea una instancia de la clase Usuarios.
     * Esta clase representa una ventana de diálogo para la gestión de usuarios.
     * Inicializa los componentes gráficos, aplica estilos y configura los listeners de los botones.
     */
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

        // Call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // Call onCancel() on ESCAPE
        WinUsuarios.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Añadir la funcionalidad de cada botón
        setListenersBtns();
    }

    /**
     * Aplica estilos a los componentes gráficos de la ventana de diálogo.
     */
    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JLabel> listaTexto = Arrays.asList(txtTittle, txtNombre, txtEdad, txtIdUsr, txtNacionalidad, txtFotoPerfil, txtNumSeguidores);
        List<JButton> listaBtns = Arrays.asList(btnBuscar, btnAddUser, btnCancel, btnDeleteUsr);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_top, WinUsuarios);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    /**
     * Configura los listeners de los botones.
     */
    private void setListenersBtns() {
        listenerBtnBuscar();

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new FormUsuario();
                dialog.setTitle("Agregar Usuario");
                dialog.setSize(600, 500);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        btnDeleteUsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUsuario = Integer.parseInt(inpIdUsr.getText());
                if (DBManagerUsuarios.existsUsuario(idUsuario)) {
                    boolean existeUsuarioReproducciones = DBManagerReproduccion.existsUsuarioEnReproduccion(idUsuario);

                    if (existeUsuarioReproducciones) {
                        boolean eliminaReproduccionesUsuarios = DBManagerReproduccion.deleteReproduccionesUsuario(idUsuario);
                        if (eliminaReproduccionesUsuarios) {
                            if (DBManagerUsuarios.deleteUsuario(idUsuario)) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente el usuario con id: " + idUsuario, "Eliminacion completada", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "No se ha podido eliminar el usuario con id: " + idUsuario, "Error en la eliminacion", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha podido eliminar las reproducciones del usuario con id: " + idUsuario, "Error en la eliminacion", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (DBManagerUsuarios.deleteUsuario(idUsuario)) {
                            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente el usuario con id: " + idUsuario, "Eliminacion completada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha podido eliminar el usuario con id: " + idUsuario, "Error en la eliminacion", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario con id: " + idUsuario, "Error en la eliminacion", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Configura el listener del botón de búsqueda.
     * Realiza una búsqueda de un usuario por ID en la base de datos y muestra los datos si se encuentra.
     */
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
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningún usuario con ese ID en la BBDD", "Error en el ID", JOptionPane.ERROR_MESSAGE);
                    inpIdUsr.setText("");
                }
            }
        });
    }

    /**
     * Configura el texto de pista y el color inicial del JTextField de ID de usuario.
     */
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
        dispose();
    }

    private void onCancel() {
        this.setVisible(false);
        MarcosMusic.frame.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        Usuarios dialog = new Usuarios();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
