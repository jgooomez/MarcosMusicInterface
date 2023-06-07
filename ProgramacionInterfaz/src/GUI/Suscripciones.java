package GUI;

import ClasePOJO.Subscripcion;
import DBManager.DBManagerConexion;
import DBManager.DBManagerSubscripcion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Suscripciones extends JDialog {
    private JPanel WinSuscripciones;
    private JButton btnReturn;
    private JPanel box_botones;
    private JButton btnBasica;
    private JButton btnDuo;
    private JButton btnIndividual;
    private JLabel txtTittle;
    private JLabel icono;
    private JPanel box_btns_tipos;
    private JButton btnFamily;
    private JButton btnStudent;
    private ArrayList<Subscripcion> subList;

    public Suscripciones() {
        subList = DBManagerSubscripcion.printTablaSubscripcion();
        styles();
        setContentPane(WinSuscripciones);
        setModal(true);
        getRootPane().setDefaultButton(btnReturn);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        WinSuscripciones.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // Añadir la funcionalidad de cada botón
        setListenersBtns();
    }

    private void styles() {
        txtTittle.setFont(new Font("Calibri", Font.BOLD, 30));
        List<JButton> listaBtns = Arrays.asList(btnReturn, btnBasica, btnIndividual, btnDuo, btnFamily, btnStudent);
        List<JPanel> listaPaneles = Arrays.asList(box_botones, box_btns_tipos, WinSuscripciones);
        List<JLabel> listaTexto = Arrays.asList(txtTittle);
        MarcosMusic.stylesBtns(listaBtns);
        MarcosMusic.stylesPanels(listaPaneles);
        MarcosMusic.stylesTexts(listaTexto);
    }

    private void setListenersBtns() {
        btnBasica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "";
                double precio = 0;
                String descripcion = "";

                for (int i = 0; i < subList.size(); i++) {
                    if (subList.get(i).getTIPO().equals(btnBasica.getText())) {
                        tipo = subList.get(i).getTIPO();
                        precio = subList.get(i).getPRECIO();
                        descripcion = subList.get(i).getDESCRIPCION();
                        break;
                    }
                }


                JDialog subsInfoView = new SubsInfoView(tipo, precio, descripcion);

                subsInfoView.setTitle("Información");
                subsInfoView.pack();
                subsInfoView.setSize(600, 300);
                subsInfoView.setLocationRelativeTo(null);

                subsInfoView.setVisible(true);
            }
        });
        btnIndividual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "";
                double precio = 0;
                String descripcion = "";

                for (int i = 0; i < subList.size(); i++) {
                    if (subList.get(i).getTIPO().equals(btnIndividual.getText())) {
                        tipo = subList.get(i).getTIPO();
                        precio = subList.get(i).getPRECIO();
                        descripcion = subList.get(i).getDESCRIPCION();
                        break;
                    }
                }


                JDialog subsInfoView = new SubsInfoView(tipo, precio, descripcion);

                subsInfoView.setTitle("Información");
                subsInfoView.pack();
                subsInfoView.setSize(600, 300);
                subsInfoView.setLocationRelativeTo(null);

                subsInfoView.setVisible(true);
            }
        });
        btnDuo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "";
                double precio = 0;
                String descripcion = "";

                for (int i = 0; i < subList.size(); i++) {
                    if (subList.get(i).getTIPO().equals(btnDuo.getText())) {
                        tipo = subList.get(i).getTIPO();
                        precio = subList.get(i).getPRECIO();
                        descripcion = subList.get(i).getDESCRIPCION();
                        break;
                    }
                }


                JDialog subsInfoView = new SubsInfoView(tipo, precio, descripcion);

                subsInfoView.setTitle("Información");
                subsInfoView.pack();
                subsInfoView.setSize(600, 300);
                subsInfoView.setLocationRelativeTo(null);

                subsInfoView.setVisible(true);
            }
        });
        btnFamily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "";
                double precio = 0;
                String descripcion = "";

                for (int i = 0; i < subList.size(); i++) {
                    if (subList.get(i).getTIPO().equals(btnFamily.getText())) {
                        tipo = subList.get(i).getTIPO();
                        precio = subList.get(i).getPRECIO();
                        descripcion = subList.get(i).getDESCRIPCION();
                        break;
                    }
                }


                JDialog subsInfoView = new SubsInfoView(tipo, precio, descripcion);

                subsInfoView.setTitle("Información");
                subsInfoView.pack();
                subsInfoView.setSize(600, 300);
                subsInfoView.setLocationRelativeTo(null);

                subsInfoView.setVisible(true);
            }
        });
        btnStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "";
                double precio = 0;
                String descripcion = "";

                for (int i = 0; i < subList.size(); i++) {
                    if (subList.get(i).getTIPO().equals(btnStudent.getText())) {
                        tipo = subList.get(i).getTIPO();
                        precio = subList.get(i).getPRECIO();
                        descripcion = subList.get(i).getDESCRIPCION();
                        break;
                    }
                }


                JDialog subsInfoView = new SubsInfoView(tipo, precio, descripcion);

                subsInfoView.setTitle("Información");
                subsInfoView.pack();
                subsInfoView.setSize(600, 300);
                subsInfoView.setLocationRelativeTo(null);

                subsInfoView.setVisible(true);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        this.setVisible(false);
        MarcosMusic.frame.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {

        Suscripciones dialog = new Suscripciones();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
