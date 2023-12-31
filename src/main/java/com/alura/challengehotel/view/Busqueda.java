/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.challengehotel.view;

import com.alura.challengehotel.controller.HuespedController;
import com.alura.challengehotel.controller.ReservaController;
import com.alura.challengehotel.model.Huesped;
import com.alura.challengehotel.model.Reserva;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * @author cesar
 */
public class Busqueda extends JFrame {
    private final JPanel contentPane;
    private JPanel header;
    private JTextField txtBuscar;
    private JPanel btnbuscar;
    private JPanel btnAtras;
    private JPanel btnexit;
    private JTabbedPane panel;
    private JPanel btnEditar;
    private JPanel btnEliminar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    private final ReservaController reservaController;
    private final HuespedController huespedController;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/images/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        reservaController = new ReservaController();
        huespedController = new HuespedController();

        initComponents();
        initListeners();

        loadTableFull();

        setResizable(false);
    }

    private void initComponents() {
        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        //modelo = (DefaultTableModel) tbReservas.getModel();
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 4;
            }
        };
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        tbReservas.setModel(modelo);
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(
                Objects.requireNonNull(Busqueda.class.getResource("/images/reservado.png"))), scroll_table, null);
        scroll_table.setVisible(true);

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        //modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 6;
            }
        };
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        tbHuespedes.setModel(modeloHuesped);
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(
                Objects.requireNonNull(Busqueda.class.getResource("/images/pessoas.png"))), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(
                Objects.requireNonNull(Busqueda.class.getResource("/images/Ha-100px.png"))));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        header = new JPanel();
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        btnAtras = new JPanel();
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        btnexit = new JPanel();
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        btnbuscar = new JPanel();
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
    }

    private void initListeners() {
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });

        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });

        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });

        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (txtBuscar.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa tu busqueda");
                    return;
                }
                executeSearch();
            }
        });

        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action("edit");
            }
        });

        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action("delete");
            }
        });
    }

    //Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void loadTableFull() {
        var reservas = this.reservaController.toList();
        reservas.forEach(reserva -> modelo.addRow(
                new Object[]{
                        reserva.getId(),
                        reserva.getFechaEntrada(),
                        reserva.getFechaSalida(),
                        reserva.getValor(),
                        reserva.getFormaPago()
                }));
        var huespedes = this.huespedController.toList();
        huespedes.forEach(huesped -> modeloHuesped.addRow(
                new Object[]{
                        huesped.getId(),
                        huesped.getNombre(),
                        huesped.getApellido(),
                        huesped.getFecha_nacimiento(),
                        huesped.getNacionalidad(),
                        huesped.getTelefono(),
                        huesped.getId_reserva()
                }
        ));
    }

    private void executeSearch() {
        var result = reservaController.find(txtBuscar.getText());
        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay resultados");
            return;
        }
        clearTable();
        for (Map.Entry<Reserva, Huesped> entry : result.entrySet()) {
            Reserva reserva = entry.getKey();
            Huesped huesped = entry.getValue();
            modelo.addRow(new Object[]{
                    reserva.getId(),
                    reserva.getFechaEntrada(),
                    reserva.getFechaSalida(),
                    reserva.getValor(),
                    reserva.getFormaPago()
            });
            modeloHuesped.addRow(new Object[]{
                    huesped.getId(),
                    huesped.getNombre(),
                    huesped.getApellido(),
                    huesped.getFecha_nacimiento(),
                    huesped.getNacionalidad(),
                    huesped.getTelefono(),
                    huesped.getId_reserva()
            });
        }
    }

    private boolean isRowSelected(JTable table) {
        return table.getSelectedRowCount() == 0 || table.getSelectedColumnCount() == 0;
    }

    private int getTabSelected() {
        return panel.getSelectedIndex();
    }

    private void action(String action) {
        if (getTabSelected() == 0) {
            if (isRowSelected(tbReservas)) {
                JOptionPane.showMessageDialog(this, "Por favor, elije un item");
                return;
            }
            if (action.equals("edit")) {
                updateReserva();
            } else {
                deleteReserva();
            }
        } else if (getTabSelected() == 1) {
            if (isRowSelected(tbHuespedes)) {
                JOptionPane.showMessageDialog(this, "Por favor, elije un item");
                return;
            }
            if (action.equals("edit")) {
                updateHuesped();
            } else {
                deleteHuesped();
            }
        }
    }

    private void updateReserva() {
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    try {
                        Reserva reserva = new Reserva(
                                Integer.parseInt(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString()),
                                Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString()),
                                Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString()),
                                Float.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString()),
                                modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString()
                        );

                        int modifiedRows = this.reservaController.update(reserva);

                        JOptionPane.showMessageDialog(this, modifiedRows + " Item modificado con éxito!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Rellena los campos correctamente");
                    }
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void updateHuesped() {
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    try {
                        Huesped huesped = new Huesped(
                                Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString()),
                                modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString(),
                                modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString(),
                                Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString()),
                                modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString(),
                                modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString(),
                                Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString())
                        );

                        int modifiedRows = this.huespedController.update(huesped);

                        JOptionPane.showMessageDialog(this, modifiedRows + " Item modificado con éxito!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Rellena los campos correctamente");
                    }
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void deleteReserva() {
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());

                    int deletedRows = this.reservaController.delete(id);

                    modelo.removeRow(tbReservas.getSelectedRow());

                    JOptionPane.showMessageDialog(this, deletedRows + " Item eliminado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void deleteHuesped() {
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

                    int deletedRows = this.huespedController.delete(id);

                    modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

                    JOptionPane.showMessageDialog(this, deletedRows + " Item eliminado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void clearTable() {
        modelo.getDataVector().clear();
        modeloHuesped.getDataVector().clear();
    }

}
