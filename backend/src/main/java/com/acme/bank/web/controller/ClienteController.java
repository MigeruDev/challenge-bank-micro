package com.acme.bank.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ClienteDTO create(@RequestBody ClienteDTO dto) {
    return service.crear(dto);
  }
}
