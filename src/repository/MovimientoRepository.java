package repository;

import model.Movimiento;
import java.util.List;

public interface MovimientoRepository {
    boolean guardar(Movimiento movimiento);
    List<Movimiento> listarPorCuenta(String numeroCuenta);
}