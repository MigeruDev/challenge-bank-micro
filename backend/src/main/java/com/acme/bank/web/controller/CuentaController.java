package com.acme.bank.web.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
  @ResponseStatus(HttpStatus.CREATED)
  public CuentaDTO create(@Valid @RequestBody CuentaDTO dto) {
    return service.crear(dto);
  }

  @PutMapping("/{numero}")
  public CuentaDTO update(
      @PathVariable Long numero,
      @Valid @RequestBody CuentaDTO dto) {
    return service.actualizar(numero, dto);
  }

  @DeleteMapping("/{numero}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long numero) {
    service.eliminar(numero);
  }
}
