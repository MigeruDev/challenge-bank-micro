package com.acme.bank.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import com.acme.bank.TestcontainersConfiguration;
import com.acme.bank.web.dto.MovimientoDTO;
import com.acme.bank.domain.TipoMovimiento;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
class MovimientoServiceTest {

    @Autowired
    private MovimientoService movimientoService;

    @Test
    void throwsIfSaldoInsuficiente() {
        MovimientoDTO retiroGrande = new MovimientoDTO(
                TipoMovimiento.RETIRO,
                new BigDecimal("2000"));  // > saldo actual (1425)

        assertThrows(SaldoNoDisponibleException.class,
                     () -> movimientoService.registrarMovimiento(478758L, retiroGrande));
    }
}
