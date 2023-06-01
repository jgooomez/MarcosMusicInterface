package GUI;

import DBManager.DBManagerDepartamento;

import javax.swing.*;
import java.awt.event.*;

public class DepartamentoDisenyo extends JDialog {
    private JPanel WinDisenyo;
    private JButton btnReturn;
    private JTextField inpNombre;
    private JTextField inpFechaCreacion;
    private JTextField inpNombreEncargado;
    private JTextField inpNumTrabajadores;
    private JTextField inpNumDeSubDepar;
    private JTextField inpIDDepartamento;
    private JPanel box_top;
    private JPanel box_botones;
    private JButton buttonOK;
    private JButton buttonCancel;

    public DepartamentoDisenyo() {
        setContentPane(WinDisenyo);
        setModal(true);
        getRootPane().setDefaultButton(btnReturn);
        departamentoDisenyoData();

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        WinDisenyo.registerKeyboardAction(new ActionListener() {
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
    private void departamentoDisenyoData(){
        if (DBManagerDepartamento.setDepartamentoData(2) != null){
            // Mostrar los datos del departamento
            inpIDDepartamento.setText(String.valueOf(DBManagerDepartamento.setDepartamentoData(2).getIdDepartamento()));
            inpNombre.setText(DBManagerDepartamento.setDepartamentoData(2).getNombre());
            inpFechaCreacion.setText(DBManagerDepartamento.setDepartamentoData(2).getFechaCreacion());
            inpNombreEncargado.setText(DBManagerDepartamento.setDepartamentoData(2).getNombreEncargado());
            inpNumTrabajadores.setText(String.valueOf(DBManagerDepartamento.setDepartamentoData(2).getNumTrabajadores()));
            inpNumDeSubDepar.setText(DBManagerDepartamento.setDepartamentoData(2).getNumSubDpto());
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
        DepartamentoDisenyo dialog = new DepartamentoDisenyo();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
