package controller;

import model.Cuenta;
import model.Movimiento;
import repository.CuentaRepository;
import repository.MovimientoRepository;
import view.BancoAppGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BancoController {
    private BancoAppGUI view;
    private CuentaRepository cuentaRepo;
    private MovimientoRepository movimientoRepo;

    public BancoController(BancoAppGUI view, CuentaRepository cuentaRepo, MovimientoRepository movimientoRepo) {
        this.view = view;
        this.cuentaRepo = cuentaRepo;
        this.movimientoRepo = movimientoRepo;

        this.view.setCrearCuentaListener(new CrearCuentaListener());
        this.view.setRegistrarMovimientoListener(new RegistrarMovimientoListener());
    }

    private class CrearCuentaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!view.camposValidos()) return;

            String numero = view.getNumero();
            String titular = view.getTitular();
            double saldo = view.getSaldo();

            // Uso del builder estático interno
            Cuenta cuenta = new Cuenta.CuentaBuilder()
                    .setNumero(numero)
                    .setTitular(titular)
                    .setSaldo(saldo)
                    .build();

            boolean creado = cuentaRepo.guardar(cuenta);
            if (creado) {
                view.mostrarMensaje("Cuenta creada con éxito");
                view.limpiarCampos();
            } else {
                view.mostrarMensaje("Error al crear la cuenta. Verifica que el número no exista.");
            }
        }
    }

    private class RegistrarMovimientoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String numeroCuenta = view.getNumeroMovimiento();
            double monto = view.getMontoMovimiento();
            String tipo = view.getTipoMovimiento();
            if (numeroCuenta.isEmpty()) {
                view.mostrarMensaje("Ingrese el número de cuenta para el movimiento");
                return;
            }

            if (monto == 0) {
                view.mostrarMensaje("Ingrese un monto válido para el movimiento");
                return;
            }

            Cuenta cuenta = cuentaRepo.buscar(numeroCuenta);
            if (cuenta == null) {
                view.mostrarMensaje("Cuenta no encontrada");
                return;
            }



            Movimiento movimiento = new Movimiento(monto, tipo, numeroCuenta);

            boolean registrado = movimientoRepo.guardar(movimiento);
            double nuevoSaldo = cuenta.getSaldo();
            if (registrado) {

                // Actualizar saldo en cuenta
                if(tipo=="Retiro"){
                    nuevoSaldo = cuenta.getSaldo() - monto;
                }
                 else if (tipo=="Deposito") {
                    nuevoSaldo = cuenta.getSaldo() + monto;
                }
                 else {
                    nuevoSaldo = cuenta.getSaldo() + monto;
                }

                

                Cuenta cuentaActualizada = new Cuenta.CuentaBuilder()
                        .setNumero(cuenta.getNumero())
                        .setTitular(cuenta.getTitular())
                        .setSaldo(nuevoSaldo)
                        .build();

                cuentaRepo.guardar(cuentaActualizada);

                view.mostrarMensaje("Movimiento registrado con éxito");
                view.limpiarCampos();
            } else {
                view.mostrarMensaje("Error al registrar el movimiento");
            }
        }
    }
}