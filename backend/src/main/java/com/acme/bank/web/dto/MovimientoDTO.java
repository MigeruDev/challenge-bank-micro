package com.acme.bank.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.acme.bank.domain.TipoMovimiento;

public record MovimientoDTO(
    Long id,
    Long cuentaNumero,
    LocalDate fecha,
    TipoMovimiento tipo,
    BigDecimal valor,
    BigDecimal saldo) {
  // Constructor “shortcut” para tests de solo tipo+valor
  public MovimientoDTO(TipoMovimiento tipo, BigDecimal valor) {
    this(null, null, null, tipo, valor, null);
  }
}
