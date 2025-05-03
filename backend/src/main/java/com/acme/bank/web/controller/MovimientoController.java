package com.acme.bank.web.controller;

import com.acme.bank.domain.Movimiento;
import com.acme.bank.repository.MovimientoRepository;
import com.acme.bank.service.MovimientoService;
import com.acme.bank.web.dto.MovimientoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

  private final MovimientoService movimientoService;
  private final MovimientoRepository movimientoRepository;

  /** Crear un nuevo movimiento sobre la cuenta indicada. */
  @PostMapping("/{cuentaNum}")
  public Movimiento crearMovimiento(@PathVariable Long cuentaNum,
      @RequestBody MovimientoDTO dto) {
    
    return movimientoService.registrarMovimiento(cuentaNum, dto);
  }

  /** Reporte simple en JSON entre dos fechas. */
  @GetMapping("/reporte")
  public List<Movimiento> reporte(
      @RequestParam Long cuenta,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {

    return movimientoRepository.findByCuentaNumeroAndFechaBetween(cuenta, desde, hasta);
  }
}
