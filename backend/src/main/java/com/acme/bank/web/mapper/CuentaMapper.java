package com.acme.bank.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.acme.bank.domain.Cliente;
import com.acme.bank.domain.Cuenta;
import com.acme.bank.web.dto.CuentaDTO;

@Mapper(componentModel = "spring", uses = ClienteMapper.class)
public interface CuentaMapper {

  /* ENTITY ← DTO */
  @Mapping(target = "cliente", source = "clienteId")
  Cuenta toEntity(CuentaDTO dto);

  /* DTO ← ENTITY */
  @Mapping(target = "clienteId", source = "cliente.personaId")
  CuentaDTO toDto(Cuenta entity);

  /* helpers */
  default Cliente map(Long id) {
    if (id == null) return null;
    Cliente c = new Cliente();
    c.setPersonaId(id);
    return c;
  }

  default Long map(Cliente c) {
    return (c == null ? null : c.getPersonaId());
  }
}
