package com.acme.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.bank.domain.Cliente;
import com.acme.bank.domain.Cuenta;
import com.acme.bank.repository.ClienteRepository;
import com.acme.bank.repository.CuentaRepository;
import com.acme.bank.web.dto.CuentaDTO;
import com.acme.bank.web.mapper.CuentaMapper;

import lombok.RequiredArgsConstructor;

/**
 * Lógica de negocio de Cuenta.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CuentaService {

  private final CuentaRepository repo;
  private final ClienteRepository clienteRepo;
  private final CuentaMapper mapper;

  public List<CuentaDTO> listar() {
    return repo.findAll()
        .stream()
        .map(mapper::toDto)
        .toList();
  }

  public CuentaDTO crear(CuentaDTO dto) {
    Cliente cli = clienteRepo.findById(dto.clienteId())
        .orElseThrow(() -> new IllegalArgumentException("Cliente no existe"));

    Cuenta cuenta = mapper.toEntity(dto);
    cuenta.setCliente(cli);

    return mapper.toDto(repo.save(cuenta));
  }
}
