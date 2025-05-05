package com.acme.bank.web.mapper;

import org.mapstruct.Mapper;

import com.acme.bank.domain.Cliente;
import com.acme.bank.web.dto.ClienteDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

  ClienteDTO toDto(Cliente entity);

  Cliente toEntity(ClienteDTO dto);
}
