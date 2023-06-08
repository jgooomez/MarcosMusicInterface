package GUI;

import DBManager.DBManagerUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormUsuario extends JDialog {
    public JPanel winFormUsr;
    private JPanel box_datosUsr;
    private JLabel txtNombre;
    private JLabel txtEdad;
    private JLabel txtNacionalidad;
    private JLabel txtNumSeguidores;
    private JTextField inpNombre;
    private JTextField inpEdad;
    private JTextField inpNacionalidad;
    private JTextField inpNumSeguidores;

    private JButton btnAddUsr;
    private JButton btnCancel;
    private JPanel box_inputsUsr;
    private JPanel box_btns;
    private JPanel box_tittle;
    private JLabel txtTittle;
    private JLabel txtDNI;
    private JTextField inpDNI;
    private JTextField inpUsername;
    private JPasswordField inpPassword;
    private JLabel txtPassword;
    private JLabel txtUsername;

    private static final String DNI_REGEX = "\\d{8}[A-HJ-NP-TV-Z]";

    /**
     * Crea una instancia de la clase FormUsuario.
     * Esta clase representa una ventana de diálogo para agregar usuarios.
     * Inicializa los componentes gráficos, aplica estilos y configura los listeners.
     */
    public FormUsuario() {
        setContentPane(winFormUsr);
        styles();
        setModal(true);
        getRootPane().setDefaultButton(btnAddUsr);
        setListenersBtns();
    }

    /**
     * Aplica estilos a los elementos visuales.
     * Aplica estilos a los botones.
     */
    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JButton> listaBtns = Arrays.asList(btnCancel, btnAddUsr);
        List<JPanel> listaPaneles = Arrays.asList(winFormUsr, box_datosUsr, box_tittle, box_inputsUsr, box_btns);
        List<JLabel> listaTexto = Arrays.asList(txtTittle, txtNacionalidad, txtNombre, txtEdad, txtDNI, txtNumSeguidores, txtUsername, txtPassword);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    /**
     * Configura los listeners de los botones y las opciones de pago.
     */
    private void setListenersBtns() {
        /**
         * Método que tras comprobar que los datos del usuario sean válidos, inserta el usuario en la BBDD.
         */

        btnAddUsr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateDNI()){
                    if (compruebaUsuario()) {
                        int idUser = DBManagerUsuarios.insertUsuario(inpNacionalidad.getText(), inpNombre.getText(), Integer.parseInt(inpEdad.getText()), Integer.parseInt(inpNumSeguidores.getText()), inpUsername.getText(), inpPassword.getText());

                        if (idUser != 0) {
                            JOptionPane.showMessageDialog(null, "El insert se realizó correctamente.");

                            int opcion = JOptionPane.showOptionDialog(null, "¿Desea vincular una tarjeta de crédito?", "Tarjeta",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new String[]{"Sí", "No"}, "Sí");

                            if (opcion == 0) {
                                // Aquí puedes usar el ID del usuario para vincularlo con la tarjeta
                                JDialog anyadirTarjeta = new AnyadirTarjeta(idUser);
                                anyadirTarjeta.setTitle("Vista de usuarios");
                                anyadirTarjeta.setSize(700, 500);
                                anyadirTarjeta.setLocationRelativeTo(null);
                                anyadirTarjeta.setVisible(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El insert no se ha podido realizar.", "Insert incorrecto", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(FormUsuario.this, "DNI inválido", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        winFormUsr.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Verifica si los campos de usuario son válidos.
     * @return true si los campos son válidos, false en caso contrario.
     */

    public boolean compruebaUsuario() {
        boolean isValid = true;
        String dni = inpDNI.getText();
        dni = dni.replace(" ", "");

        if (inpNacionalidad.getText().length() == 0 || inpNombre.getText().length() == 0
                || inpEdad.getText().length() == 0 || inpNumSeguidores.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser rellenados.", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        } else if (Integer.parseInt(inpEdad.getText()) < 12) {
            JOptionPane.showMessageDialog(null, "El usuario debe ser mayor de 18 años.", "Error", JOptionPane.ERROR_MESSAGE);
            inpEdad.setText("");
            isValid = false;
        } else if (!verificarNacionalidad(inpNacionalidad.getText())) {
            JOptionPane.showMessageDialog(null, "La nacionalidad no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        }

        return isValid;
    }

    private boolean verificarNacionalidad(String nacionalidad) {
        try {
            String apiUrl = "https://restcountries.com/v2/name/" + nacionalidad;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Si la respuesta contiene datos, significa que la nacionalidad existe
                return response.length() > 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    private boolean validateDNI() {
        String dni = inpDNI.getText();
        Pattern pattern = Pattern.compile(DNI_REGEX);
        Matcher matcher = pattern.matcher(dni);
        return matcher.matches();
    }


    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
