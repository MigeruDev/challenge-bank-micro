package com.acme.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.bank.repository.ClienteRepository;
import com.acme.bank.web.dto.ClienteDTO;
import com.acme.bank.web.mapper.ClienteMapper;

import lombok.RequiredArgsConstructor;

/**
 * Lógica de negocio de Cliente.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {

  private final ClienteRepository repo;
  private final ClienteMapper mapper;

  public List<ClienteDTO> listar() {
    return repo.findAll()
        .stream()
        .map(mapper::toDto)
        .toList();
  }

  public ClienteDTO crear(ClienteDTO dto) {
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }
}
