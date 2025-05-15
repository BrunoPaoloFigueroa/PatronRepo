package model;

public class Cuenta {
    private String numero;
    private String titular;
    private double saldo;

    public Cuenta(String numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getNumero() { return numero; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }

    public static class CuentaBuilder {
        private String numero;
        private String titular;
        private double saldo;

        public CuentaBuilder setNumero(String numero) {
            this.numero = numero;
            return this;
        }

        public CuentaBuilder setTitular(String titular) {
            this.titular = titular;
            return this;
        }

        public CuentaBuilder setSaldo(double saldo) {
            this.saldo = saldo;
            return this;
        }

        public Cuenta build() {
            return new Cuenta(numero, titular, saldo);
        }
    }
}