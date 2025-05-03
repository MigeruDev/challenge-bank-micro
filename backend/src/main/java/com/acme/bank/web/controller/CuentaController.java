package com.acme.bank.web.controller;

import com.acme.bank.domain.*;
import com.acme.bank.repository.*;
import com.acme.bank.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {
  private final CuentaRepository repo;

  @GetMapping
  public List<Cuenta> all() {
    return repo.findAll();
  }

  @PostMapping
  public Cuenta create(@RequestBody CuentaDTO dto) {
    Cuenta c = new Cuenta();
    c.setNumero(dto.numero());
    c.setTipo(dto.tipo());
    c.setSaldo(dto.saldo());
    c.setEstado(dto.estado());
    // cliente fetch omitted for brevity
    return repo.save(c);
  }
}
