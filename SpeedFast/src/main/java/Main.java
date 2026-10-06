import vista.VentanaGesPedido;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VentanaGesPedido ventana = new VentanaGesPedido();

            JFrame frame = new JFrame("Gestión de Pedidos");

            frame.setContentPane(ventana.getPanelPrincipal());

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

/*
import controlador.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){

        try (Connection conn = ConexionBD.obtenerConexion()) {
            System.out.println("\u2705 Conexion exitosa.");
        } catch (SQLException e) {
            System.err.println("\u274c Error al conectar con la bases de datos: ");
            e.printStackTrace();
        }
    }
}
*/

