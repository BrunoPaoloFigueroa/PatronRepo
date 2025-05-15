package repository;

import model.Cuenta;

public interface CuentaRepository {
    boolean guardar(Cuenta cuenta);
    Cuenta buscar(String numero);
}