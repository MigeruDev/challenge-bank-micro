package com.acme.bank.web.controller;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.acme.bank.web.dto.ReportDTO;
import com.acme.bank.service.ReportService;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportController {

  private final ReportService reportService;

  @GetMapping
  public ReportDTO reporteGeneral(
      @RequestParam Long clienteId,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
    return reportService.generarReporte(clienteId, desde, hasta);
  }
}