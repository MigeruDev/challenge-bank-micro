package com.acme.bank.web.controller;

import com.acme.bank.domain.*;
import com.acme.bank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
  private final ClienteRepository repo;

  @GetMapping
  public List<Cliente> all() {
    return repo.findAll();
  }

  @PostMapping
  public Cliente create(@RequestBody Cliente c) {
    return repo.save(c);
  }
}
