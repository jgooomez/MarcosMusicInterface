package GUI;

import DBManager.DBManagerDepartamento;

import javax.swing.*;
import java.awt.event.*;

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
    //Este método llama a otro método de DBManagerDepartamento
    //Se le pasa por variable el id del departamento que corresponde y con el otro método lo busca en la BBDD
    //Si el return de setDepartamentoData es null aparecera una ventana emergente con el error
    //Si el return tiene el objeto mostrara en el Form los datos del objeto
private void atClientData(){
    if (DBManagerDepartamento.setDepartamentoData(6) != null){
        // Mostrar los datos del departamento
        inpIDDepartamento.setText(String.valueOf(DBManagerDepartamento.setDepartamentoData(6).getIdDepartamento()));
        inpNombre.setText(DBManagerDepartamento.setDepartamentoData(6).getNombre());
        inpFechaCreacion.setText(DBManagerDepartamento.setDepartamentoData(6).getFechaCreacion());
        inpNombreEncargado.setText(DBManagerDepartamento.setDepartamentoData(6).getNombreEncargado());
        inpNumTrabajadores.setText(String.valueOf(DBManagerDepartamento.setDepartamentoData(6).getNumTrabajadores()));
        inpNumDeSubDepar.setText(DBManagerDepartamento.setDepartamentoData(6).getNumSubDpto());
    }else {
        JOptionPane.showMessageDialog(WinAtencionCliente,"No se encuentra el departamento en la base de datos","Fallo en la busqueda",JOptionPane.ERROR_MESSAGE);
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
