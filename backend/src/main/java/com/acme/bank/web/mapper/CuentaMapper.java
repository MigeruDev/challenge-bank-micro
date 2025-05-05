package com.acme.bank.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.acme.bank.domain.Cuenta;
import com.acme.bank.web.dto.CuentaDTO;

@Mapper(componentModel = "spring", uses = ClienteMapper.class)
public interface CuentaMapper {

  @Mapping(target = "cliente.id", source = "clienteId")
  Cuenta toEntity(CuentaDTO dto);

  @Mapping(target = "clienteId", source = "cliente.id")
  CuentaDTO toDto(Cuenta entity);
}
