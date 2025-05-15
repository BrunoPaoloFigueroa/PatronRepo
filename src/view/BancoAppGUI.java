package view;


import javax.swing.*;
import java.awt.event.ActionListener;


public class BancoAppGUI extends JFrame {
    private JButton btnCrearCuenta;
    private JButton btnRegistrarMovimiento;

    private JTextField txtNumero;
    private JTextField txtTitular;
    private JTextField txtSaldo;

    private JTextField txtNumeroMovimiento;
    private JTextField txtMontoMovimiento;
    private JComboBox<String> comboTipoMovimiento;

    public BancoAppGUI() {
        setTitle("Banco App");
        setSize(400, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(50, 30, 100, 25);
        add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(150, 30, 200, 25);
        add(txtNumero);

        JLabel lblTitular = new JLabel("Titular:");
        lblTitular.setBounds(50, 70, 100, 25);
        add(lblTitular);

        txtTitular = new JTextField();
        txtTitular.setBounds(150, 70, 200, 25);
        add(txtTitular);

        JLabel lblSaldo = new JLabel("Saldo:");
        lblSaldo.setBounds(50, 110, 100, 25);
        add(lblSaldo);

        txtSaldo = new JTextField();
        txtSaldo.setBounds(150, 110, 200, 25);
        add(txtSaldo);

        btnCrearCuenta = new JButton("Crear Cuenta");
        btnCrearCuenta.setBounds(125, 150, 150, 30);
        add(btnCrearCuenta);

        // Sección para registrar movimientos
        JLabel lblNumeroMovimiento = new JLabel("Cuenta:");
        lblNumeroMovimiento.setBounds(50, 200, 100, 25);
        add(lblNumeroMovimiento);

        txtNumeroMovimiento = new JTextField();
        txtNumeroMovimiento.setBounds(150, 200, 200, 25);
        add(txtNumeroMovimiento);

        JLabel lblMonto = new JLabel("Monto:");
        lblMonto.setBounds(50, 240, 100, 25);
        add(lblMonto);

        txtMontoMovimiento = new JTextField();
        txtMontoMovimiento.setBounds(150, 240, 200, 25);
        add(txtMontoMovimiento);

        JLabel lblTipoMovimiento = new JLabel("Tipo de Movimiento:");
        lblTipoMovimiento.setBounds(50, 270, 120, 25);
        add(lblTipoMovimiento);

        comboTipoMovimiento = new JComboBox<>(new String[] {"Depósito", "Retiro", "Transferencia"});
        comboTipoMovimiento.setBounds(180, 270, 170, 25);
        add(comboTipoMovimiento);

        btnRegistrarMovimiento = new JButton("Registrar Movimiento");
        btnRegistrarMovimiento.setBounds(100, 310, 200, 30);
        add(btnRegistrarMovimiento);
    }

    // Getters para crear cuenta
    public String getNumero() { return txtNumero.getText(); }
    public String getTitular() { return txtTitular.getText(); }

    public double getSaldo() {
        try {
            return Double.parseDouble(txtSaldo.getText());
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor ingresa un valor numérico válido para el saldo.");
            return 0;
        }
    }

    public boolean camposValidos() {
        if (getNumero().isEmpty() || getTitular().isEmpty() || txtSaldo.getText().isEmpty()) {
            mostrarMensaje("Todos los campos deben estar llenos.");
            return false;
        }
        try {
            Double.parseDouble(txtSaldo.getText());
            return true;
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor ingresa un valor numérico válido para el saldo.");
            return false;
        }
    }

    // Getters para movimientos
    public String getNumeroMovimiento() { return txtNumeroMovimiento.getText(); }

    public double getMontoMovimiento() {
        try {
            return Double.parseDouble(txtMontoMovimiento.getText());
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor ingresa un monto válido.");
            return 0;
        }
    }

    public String getTipoMovimiento() {
        return (String) comboTipoMovimiento.getSelectedItem();
    }

    // Listeners
    public void setCrearCuentaListener(ActionListener listener) { btnCrearCuenta.addActionListener(listener); }
    public void setRegistrarMovimientoListener(ActionListener listener) { btnRegistrarMovimiento.addActionListener(listener); }

    public void mostrarMensaje(String mensaje) { JOptionPane.showMessageDialog(this, mensaje); }

    public void limpiarCampos() {
        txtNumero.setText("");
        txtTitular.setText("");
        txtSaldo.setText("");
        txtNumeroMovimiento.setText("");
        txtMontoMovimiento.setText("");
        comboTipoMovimiento.setSelectedIndex(0);
    }
}