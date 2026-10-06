package controlador;

import dao.PedidosDAO;
import modelo.Pedidos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PedidosControlador implements PedidosDAO {

    //private final ArrayList<Pedidos> pedidos = new ArrayList<>();

    @Override
    public void registrarPedidos(Pedidos pedidos, DefaultTableModel model) {

        // Se define la consulta SQl
        String sql = "INSERT INTO pedidos (direccion, tipo, estado) VALUES (?, ?, ?)";

        // Se assignan los valores del pedido
        try (Connection conn = ConexionBD.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedidos.getDireccion());
            stmt.setString(2, pedidos.getTipo());
            stmt.setString(3, pedidos.getEstado());

            stmt.executeUpdate();

            cargarBdPedido(model); // Para volver a cargar en la ventana la base de datos

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el pedido en la base de datos.");
        }
    }



    // Método para editar los pedidos en la base de datos
    @Override
    public void editarPedidos(int id, Pedidos pedidos, DefaultTableModel model, int filaTabla) {

        String sql = "UPDATE pedidos SET direccion=?, tipo=?, estado=? WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedidos.getDireccion());
            stmt.setString(2, pedidos.getTipo());
            stmt.setString(3, pedidos.getEstado());
            stmt.setInt(4, id);


            stmt.executeUpdate();

            // Para actualizar vista
            model.setValueAt(id, filaTabla, 0);
            model.setValueAt(pedidos.getDireccion(), filaTabla, 1);
            model.setValueAt(pedidos.getTipo(), filaTabla, 2);
            model.setValueAt(pedidos.getEstado(), filaTabla, 3);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al editar el pedido en la base de datos.");
        }
    }

    // Método para eliminar los pedidos en la base de datos
    @Override
    public void eliminarPedidos(int id, DefaultTableModel model, int filaTabla) {
        String sql = "DELETE FROM pedidos WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            model.removeRow(filaTabla);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el pedido de la base de datos.");
        }
    }

    // Metodo para cargar los datos desde la base de datos
    @Override
    public void cargarBdPedido(DefaultTableModel model) {
        String sql = "SELECT * FROM pedidos";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            model.setRowCount(0); // para limpiar la tabla
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        rs.getString("tipo"),
                        rs.getString("estado"),
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar pedidos desde la base de datos.");
        }
    }
}
