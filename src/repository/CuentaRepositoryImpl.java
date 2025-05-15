package repository;

import model.Cuenta;

import java.sql.*;

public class CuentaRepositoryImpl implements CuentaRepository {

    private Connection conexion;

    public CuentaRepositoryImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean guardar(Cuenta cuenta) {
        // Primero verificamos si la cuenta existe
        if (buscar(cuenta.getNumero()) == null) {
            // Insertar
            String sql = "INSERT INTO cuentas(numero, titular, saldo) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, cuenta.getNumero());
                stmt.setString(2, cuenta.getTitular());
                stmt.setDouble(3, cuenta.getSaldo());
                int filas = stmt.executeUpdate();
                return filas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            // Actualizar saldo y titular (en caso de que cambie)
            String sql = "UPDATE cuentas SET titular = ?, saldo = ? WHERE numero = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, cuenta.getTitular());
                stmt.setDouble(2, cuenta.getSaldo());
                stmt.setString(3, cuenta.getNumero());
                int filas = stmt.executeUpdate();
                return filas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public Cuenta buscar(String numero) {
        String sql = "SELECT numero, titular, saldo FROM cuentas WHERE numero = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cuenta.CuentaBuilder()
                        .setNumero(rs.getString("numero"))
                        .setTitular(rs.getString("titular"))
                        .setSaldo(rs.getDouble("saldo"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}