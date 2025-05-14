package com.acme.bank.web.dto;

import com.acme.bank.domain.TipoCuenta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CuentaDTO(
    @NotNull Long numero,
    @NotNull TipoCuenta tipo,
    @PositiveOrZero BigDecimal saldo,
    @NotNull Boolean estado,
    @NotNull Long clienteId) {
}