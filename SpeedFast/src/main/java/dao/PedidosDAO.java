package dao;

import modelo.Pedidos;

import javax.swing.table.DefaultTableModel;

public interface PedidosDAO {

    void registrarPedidos(Pedidos pedido, DefaultTableModel model);
    void editarPedidos(int ID, Pedidos pedidos, DefaultTableModel model, int filaTabla);
    void eliminarPedidos(int id, DefaultTableModel model, int filaTabla);
    void cargarBdPedido(DefaultTableModel model);
}
