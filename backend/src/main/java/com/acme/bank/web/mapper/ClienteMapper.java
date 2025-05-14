package com.acme.bank.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.acme.bank.domain.Cliente;
import com.acme.bank.web.dto.ClienteDTO;

@Mapper(
  componentModel = "spring",
  unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ClienteMapper {

  /** ENTITY → DTO */
  @Mapping(source = "personaId", target = "clienteId")
  ClienteDTO toDto(Cliente entity);

  /** DTO → ENTITY */
  @Mapping(source = "clienteId", target = "personaId")
  Cliente toEntity(ClienteDTO dto);
}
