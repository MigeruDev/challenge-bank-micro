package com.acme.bank.web.dto;

import java.math.BigDecimal;

import com.acme.bank.domain.TipoMovimiento;

/**
 * DTO usado para registrar movimientos.
 * La fecha se asigna internamente como {@code LocalDate.now()}
 * para simplificar la API (no es necesaria en el payload).
 */
public record MovimientoDTO(
    TipoMovimiento tipo,
    BigDecimal valor) {

  public MovimientoDTO {
    if (valor == null || valor.signum() == 0) {
      throw new IllegalArgumentException("El valor del movimiento debe ser distinto de cero");
    }
  }
}
