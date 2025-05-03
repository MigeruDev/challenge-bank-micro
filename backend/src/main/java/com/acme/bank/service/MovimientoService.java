package com.acme.bank.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.bank.domain.TipoMovimiento;
import com.acme.bank.repository.CuentaRepository;
import com.acme.bank.repository.MovimientoRepository;
import com.acme.bank.web.dto.MovimientoDTO;

import lombok.RequiredArgsConstructor;

import com.acme.bank.domain.Cuenta;
import com.acme.bank.domain.Movimiento;

@Service
@RequiredArgsConstructor
@Transactional
public class MovimientoService {

  private final CuentaRepository cuentaRepo;
  private final MovimientoRepository movimientoRepo;

  /**
   * Registra un depósito o retiro y actualiza el saldo de la cuenta.
   * <p>
   * – Para depósitos el valor se guarda positivo.
   * – Para retiros el valor se guarda negativo.
   * Si el nuevo saldo resultara negativo se lanza
   * {@link SaldoNoDisponibleException}.
   *
   * @return el movimiento ya persistido
   */
  public Movimiento registrarMovimiento(Long numeroCuenta, MovimientoDTO dto) {

    // Obtener la cuenta o abortar si no existe
    Cuenta cuenta = cuentaRepo.findById(numeroCuenta)
        .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

    BigDecimal valor = dto.valor();
    if (dto.tipo() == TipoMovimiento.RETIRO) {
      valor = valor.negate(); // retiros se guardan como negativos
    }

    // Calcular el nuevo saldo y validar disponibilidad
    BigDecimal nuevoSaldo = cuenta.getSaldo().add(valor);
    if (nuevoSaldo.signum() < 0) { // 👉 saldo quedaría negativo
      throw new SaldoNoDisponibleException("Saldo no disponible");
    }

    // Persistir el movimiento
    Movimiento movimiento = Movimiento.builder()
        .fecha(LocalDate.now()) // la fecha *siempre* es "hoy"
        .tipo(dto.tipo())
        .valor(valor)
        .saldo(nuevoSaldo) // saldo restante luego de la operación
        .cuenta(cuenta)
        .build();
    movimientoRepo.save(movimiento);

    // Actualizar y guardar la cuenta
    cuenta.setSaldo(nuevoSaldo);
    cuentaRepo.save(cuenta);

    return movimiento;
  }
}
