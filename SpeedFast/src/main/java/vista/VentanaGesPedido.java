package vista;

import controlador.PedidosControlador;
import modelo.Pedidos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaGesPedido {

    private JPanel panelPrincipal;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTable table1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    private PedidosControlador controlador;

    public VentanaGesPedido() {

        controlador = new PedidosControlador();

        // Para setear los entradas de texto
        textField1.setText("DirecciónParaAgregar 123");

        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Dirección", "Tipo", "Estado"}, 0);

        table1.setModel(modelo);

        controlador.cargarBdPedido(modelo);

        // Lista para Tipo
        comboBox1.addItem("COMIDA");
        comboBox1.addItem("ENCOMIENDA");
        comboBox1.addItem("EXPRESS");

        // Lista para estado
        comboBox2.addItem("PENDIENTE");
        comboBox2.addItem("EN REPARTO");
        comboBox2.addItem("ENTREGADO");



        button1.addActionListener(e -> registrarPedido()); // Accion del boton registrar
        button2.addActionListener(e -> editarPedido());
        button3.addActionListener(e -> eliminarPedido()); // Accion del boton eliminar
    }


    private void registrarPedido() {

        String direccion = textField1.getText();
        String tipo = (String) comboBox1.getSelectedItem();
        String estado = (String) comboBox2.getSelectedItem();

        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Ingresa una dirección.");
            return;
        }

        Pedidos pedido = new Pedidos(direccion,tipo,estado);

        controlador.registrarPedidos(pedido,(DefaultTableModel) table1.getModel());

        textField1.setText("");
    }

    private void editarPedido() {

        int fila = table1.getSelectedRow();

        // Mensaje por si no se seleciona un pedido al editar
        if (fila == -1) {
            JOptionPane.showMessageDialog(null,"Selecciona un pedido para editar.");
            return;
        }

        int id = (int) table1.getValueAt(fila, 0);

        String direccion = textField1.getText();
        String tipo = (String) comboBox1.getSelectedItem();
        String estado = (String) comboBox2.getSelectedItem();

        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Ingresa una dirección.");
            return;
        }

        Pedidos pedido = new Pedidos(direccion,tipo,estado);

        controlador.editarPedidos(id,pedido,(DefaultTableModel) table1.getModel(),fila);

        textField1.setText("");
    }


    private void eliminarPedido() {

        int fila = table1.getSelectedRow();

        // Mensaje por si no se seleciona un pedido al eliminar
        if (fila == -1) {
            JOptionPane.showMessageDialog(null,"Selecciona un pedido para eliminar.");
            return;
        }

        int id = (int) table1.getValueAt(fila, 0);

        controlador.eliminarPedidos(
                id,
                (DefaultTableModel) table1.getModel(),
                fila
        );
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}