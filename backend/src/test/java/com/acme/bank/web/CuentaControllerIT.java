package com.acme.bank.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.acme.bank.domain.TipoCuenta;
import com.acme.bank.service.CuentaService;
import com.acme.bank.web.controller.CuentaController;
import com.acme.bank.web.dto.CuentaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = CuentaController.class)
class CuentaControllerIT {

  @Autowired
  MockMvc mvc;

  @MockBean
  CuentaService service;

  @Autowired
  ObjectMapper mapper;

  @Test
  void postCreatesCuenta() throws Exception {
    CuentaDTO stubbed = new CuentaDTO(123456L, TipoCuenta.AHORROS,
        new BigDecimal("100"), true, 1L);

    when(service.crear(any(CuentaDTO.class))).thenReturn(stubbed);

    mvc.perform(post("/cuentas")
        .contentType("application/json")
        .content(mapper.writeValueAsString(stubbed)))
        .andExpect(status().isOk());
  }
}
