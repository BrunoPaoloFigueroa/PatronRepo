package repository;

import model.Movimiento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoRepositoryImpl implements MovimientoRepository {

    private Connection conexion;

    public MovimientoRepositoryImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean guardar(Movimiento movimiento) {
        String sql = "INSERT INTO movimientos(cuentaNumero, tipo, monto) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, movimiento.getCuentaNumero());
            stmt.setString(2, movimiento.getTipo());
            stmt.setDouble(3, movimiento.getMonto());
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Movimiento> listarPorCuenta(String numeroCuenta) {
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT cuentaNumero, tipo, monto FROM movimientos WHERE cuentaNumero = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, numeroCuenta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Movimiento(
                        rs.getDouble("monto"),
                        rs.getString("tipo"),
                        rs.getString("cuentaNumero")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}