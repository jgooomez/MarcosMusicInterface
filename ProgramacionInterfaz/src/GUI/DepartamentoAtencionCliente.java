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
        atClientData();
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
private void atClientData(){
    if (DBManagerDepartamento.setAtClientData(1) != null){
        // Mostrar los datos del departamento
        inpIDDepartamento.setText(String.valueOf(DBManagerDepartamento.setAtClientData(1).getIdDepartamento()));
        inpNombre.setText(DBManagerDepartamento.setAtClientData(1).getNombre());
        inpFechaCreacion.setText(DBManagerDepartamento.setAtClientData(1).getFechaCreacion());
        inpNombreEncargado.setText(DBManagerDepartamento.setAtClientData(1).getNombreEncargado());
        inpNumTrabajadores.setText(String.valueOf(DBManagerDepartamento.setAtClientData(1).getNumTrabajadores()));
        inpNumDeSubDepar.setText(DBManagerDepartamento.setAtClientData(1).getNumSubDpto());
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
