package com.acme.bank.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.acme.bank.domain.Movimiento;
import com.acme.bank.web.dto.MovimientoDTO;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

  @Mapping(source = "cuenta.numero", target = "cuentaNumero")
  MovimientoDTO toDto(Movimiento entity);

  @Mapping(source = "cuentaNumero", target = "cuenta.numero")
  Movimiento toEntity(MovimientoDTO dto);
}