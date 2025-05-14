package com.acme.bank.service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acme.bank.repository.ClienteRepository;
import com.acme.bank.web.dto.ClienteDTO;
import com.acme.bank.web.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;

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

  public ClienteDTO actualizar(Long id, ClienteDTO dto) {
    // Verifica existencia
    repo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
    var actualizado = mapper.toEntity(dto);
    actualizado.setPersonaId(id);
    return mapper.toDto(repo.save(actualizado));
  }

  public void eliminar(Long id) {
    if (!repo.existsById(id)) {
      throw new EntityNotFoundException("Cliente no encontrado");
    }
    repo.deleteById(id);
  }
}