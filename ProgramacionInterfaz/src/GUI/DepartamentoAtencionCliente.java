package GUI;

import ClasePOJO.Departamento;
import DBManager.DBManagerDepartamento;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DepartamentoAtencionCliente extends JDialog {
    private JPanel WinAtencionCliente;
    private JPanel box_botones;
    private JPanel box_top;
    private JButton btnReturn;
    private JTextField inpNombre;
    private JTextField inpFechaCreacion;
    private JTextField inpNombreEncargado;
    private JTextField inpNumTrabajadores;
    private JTextField inpNumDeSubDepar;
    private JTextField inpIDDepartamento;
    private JButton buttonOK;
    private JButton buttonCancel;

    public DepartamentoAtencionCliente() {
        setContentPane(WinAtencionCliente);
        setModal(true);
        getRootPane().setDefaultButton(btnReturn);
        //rellena los textfild con datos de Atencion al cliente
        setAtClientData();
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        WinAtencionCliente.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
private void setAtClientData(){
    ArrayList<Departamento> departamentos = DBManagerDepartamento.obtenerDatosDepartamento();
    Departamento departamentoActual = null;

    for (Departamento departamento : departamentos){
        if (departamento.getIdDepartamento() == 1) {
            departamentoActual = departamento;
            break;
        }
    }
    if (departamentoActual != null){
        // Mostrar los datos del departamento
        inpIDDepartamento.setText(String.valueOf(departamentoActual.getIdDepartamento()));
        inpNombre.setText(departamentoActual.getNombre());
        inpFechaCreacion.setText(departamentoActual.getFechaCreacion());
        inpNombreEncargado.setText(departamentoActual.getNombreEncargado());
        inpNumTrabajadores.setText(String.valueOf(departamentoActual.getNumTrabajadores()));
        inpNumDeSubDepar.setText(departamentoActual.getNumSubDpto());
    }

}
    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        DepartamentoAtencionCliente dialog = new DepartamentoAtencionCliente();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
