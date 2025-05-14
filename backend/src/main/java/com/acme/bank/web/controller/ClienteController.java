package com.acme.bank.web.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.acme.bank.service.ClienteService;
import com.acme.bank.web.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteService service;

  @GetMapping
  public List<ClienteDTO> all() {
    return service.listar();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ClienteDTO create(@Valid @RequestBody ClienteDTO dto) {
    return service.crear(dto);
  }

  @PutMapping("/{id}")
  public ClienteDTO update(
      @PathVariable Long id,
      @Valid @RequestBody ClienteDTO dto) {
    return service.actualizar(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.eliminar(id);
  }
}