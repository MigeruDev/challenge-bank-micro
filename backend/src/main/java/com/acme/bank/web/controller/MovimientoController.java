package com.acme.bank.web.controller;

import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.acme.bank.service.MovimientoService;
import com.acme.bank.web.dto.MovimientoDTO;
import com.acme.bank.web.mapper.MovimientoMapper;
import com.acme.bank.repository.MovimientoRepository;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

  private final MovimientoService movimientoService;
  private final MovimientoMapper mapper;
  private final MovimientoRepository movimientoRepository;

  @PostMapping("/{cuentaNum}")
  public MovimientoDTO crearMovimiento(
      @PathVariable Long cuentaNum,
      @RequestBody MovimientoDTO dto) {
    var mov = movimientoService.registrarMovimiento(cuentaNum, dto);
    return mapper.toDto(mov);
  }

  @GetMapping("/reporte")
  public List<MovimientoDTO> reporte(
      @RequestParam Long cuenta,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
    return movimientoRepository
        .findByCuentaNumeroAndFechaBetween(cuenta, desde, hasta)
        .stream()
        .map(mapper::toDto)
        .toList();
  }
}
