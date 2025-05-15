import controller.BancoController;
import model.Cuenta;
import repository.*;
import view.BancoAppGUI;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            BancoAppGUI vista = new BancoAppGUI();

            try {
                Connection conexion = DBConexion.getConexion();
                CuentaRepository cuentaRepo = new CuentaRepositoryImpl(conexion);
                MovimientoRepository movimientoRepo = new MovimientoRepositoryImpl(conexion);

                new BancoController(vista, cuentaRepo, movimientoRepo);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }

            vista.setVisible(true);
        });
    }
}