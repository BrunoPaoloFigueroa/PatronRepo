package model;

public class Movimiento {
    private String cuentaNumero;
    private double monto;
    private String tipo;

    public Movimiento(double monto, String tipo, String cuentaNumero) {
        this.cuentaNumero = cuentaNumero;
        this.monto = monto;
        this.tipo = tipo;
    }

    public String getCuentaNumero() { return cuentaNumero; }
    public double getMonto() { return monto; }
    public String getTipo() { return tipo; }
}