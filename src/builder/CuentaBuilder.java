package builder;

import model.Cuenta;

public class CuentaBuilder {
    private String numero;
    private String titular;
    private double saldo;

    public CuentaBuilder conNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public CuentaBuilder conTitular(String titular) {
        this.titular = titular;
        return this;
    }

    public CuentaBuilder conSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    }

    public Cuenta construir() {
        return new Cuenta(numero, titular, saldo);
    }
}