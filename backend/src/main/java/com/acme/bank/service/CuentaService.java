package com.acme.bank.service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acme.bank.repository.CuentaRepository;
import com.acme.bank.repository.ClienteRepository;
import com.acme.bank.web.dto.CuentaDTO;
import com.acme.bank.web.mapper.CuentaMapper;
import lombok.RequiredArgsConstructor;

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
    var cli = clienteRepo.findById(dto.clienteId())
        .orElseThrow(() -> new EntityNotFoundException("Cliente no existe"));
    var cuenta = mapper.toEntity(dto);
    cuenta.setCliente(cli);
    return mapper.toDto(repo.save(cuenta));
  }

  public CuentaDTO actualizar(Long numero, CuentaDTO dto) {
    // Verifica existencia de cuenta
    repo.findById(numero)
        .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));
    var actualizada = mapper.toEntity(dto);
    actualizada.setNumero(numero);
    var cli = clienteRepo.findById(dto.clienteId())
        .orElseThrow(() -> new EntityNotFoundException("Cliente no existe"));
    actualizada.setCliente(cli);
    return mapper.toDto(repo.save(actualizada));
  }

  public void eliminar(Long numero) {
    if (!repo.existsById(numero)) {
      throw new EntityNotFoundException("Cuenta no encontrada");
    }
    repo.deleteById(numero);
  }
}