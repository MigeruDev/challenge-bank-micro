package com.acme.bank.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.bank.service.CuentaService;
import com.acme.bank.web.dto.CuentaDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

  private final CuentaService service;

  @GetMapping
  public List<CuentaDTO> all() {
    return service.listar();
  }

  @PostMapping
  public CuentaDTO create(@RequestBody CuentaDTO dto) {
    return service.crear(dto);
  }
}
