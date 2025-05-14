package com.acme.bank.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import com.acme.bank.TestcontainersConfiguration;
import com.acme.bank.domain.TipoCuenta;
import com.acme.bank.web.dto.CuentaDTO;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
class CuentaServiceTest {

  @Autowired
  private CuentaService service;

  @Test
  void createPersistsAndReturnsDto() {
    CuentaDTO dto = new CuentaDTO(
        999999L,
        TipoCuenta.AHORROS,
        new BigDecimal("250"),
        true,
        1L // ← cliente demo cargado por Flyway
    );

    CuentaDTO persisted = service.crear(dto);

    assertThat(persisted.numero()).isEqualTo(999999L);
    assertThat(persisted.saldo()).isEqualByComparingTo("250");
  }
}
