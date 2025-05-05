package com.acme.bank.web.mapper;

import java.time.LocalDate;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.acme.bank.domain.Movimiento;
import com.acme.bank.domain.TipoMovimiento;
import com.acme.bank.web.dto.MovimientoDTO;

/**
 * Conversión Movimiento ⬌ MovimientoDTO.
 *
 * El DTO sólo necesita <tipo, valor>; la fecha se fija a "hoy" y
 * la relación cuenta se ignora (la asigna el servicio).
 */
@Mapper(componentModel = "spring")
public interface MovimientoMapper {

  /* Entidad → DTO */
  MovimientoDTO toDto(Movimiento entity);

  /* DTO → Entidad (NEW) */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "saldo", ignore = true) // lo calcula el servicio
  @Mapping(target = "fecha", ignore = true) // se pone en @AfterMapping
  @Mapping(target = "cuenta", ignore = true)
  Movimiento toEntity(MovimientoDTO dto);

  /* DTO → Entidad (UPDATE) */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "saldo", ignore = true)
  @Mapping(target = "fecha", ignore = true)
  @Mapping(target = "cuenta", ignore = true)
  void updateFromDto(MovimientoDTO dto, @MappingTarget Movimiento target);

  /* --- Hook para fijar la fecha automáticamente --- */
  @AfterMapping
  default void setFechaHoy(@MappingTarget Movimiento target) {
    target.setFecha(LocalDate.now());
  }

  /* --- Hook para normalizar retiros en negativo --- */
  @AfterMapping
  default void normalizarValor(MovimientoDTO dto,
      @MappingTarget Movimiento target) {
    if (dto.tipo() == TipoMovimiento.RETIRO) {
      target.setValor(dto.valor().negate());
    } else {
      target.setValor(dto.valor());
    }
  }
}
