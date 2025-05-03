package com.acme.bank.web.dto;

import com.acme.bank.domain.TipoCuenta;
import java.math.BigDecimal;

public record CuentaDTO(Long numero, TipoCuenta tipo, BigDecimal saldo,
    Boolean estado, Long clienteId) {
}