package com.acme.bank.service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acme.bank.domain.Movimiento;
import com.acme.bank.repository.MovimientoRepository;
import com.acme.bank.web.dto.MovimientoDTO;
import com.acme.bank.web.dto.ReportDTO;
import com.acme.bank.web.mapper.MovimientoMapper;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

  private final MovimientoRepository movimientoRepository;
  private final MovimientoMapper movimientoMapper;

  public ReportDTO generarReporte(Long clienteId, LocalDate desde, LocalDate hasta) {
    // 1) recuperar movimientos filtrados por cliente y fechas
    List<Movimiento> movimientos = movimientoRepository
        .findByCuentaClientePersonaIdAndFechaBetween(clienteId, desde, hasta);

    // 2) mapear a DTOs
    List<MovimientoDTO> dtos = movimientos.stream()
        .map(movimientoMapper::toDto)
        .toList();

    // 3) calcular totales
    BigDecimal totalCreditos = dtos.stream()
        .map(MovimientoDTO::valor)
        .filter(v -> v.signum() > 0)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal totalDebitos = dtos.stream()
        .map(MovimientoDTO::valor)
        .filter(v -> v.signum() < 0)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    // 4) generar PDF en memoria
    String pdfBase64 = crearPdfBase64(dtos);

    // 5) devolver DTO de reporte
    return new ReportDTO(dtos, totalCreditos, totalDebitos, pdfBase64);
  }

  private String crearPdfBase64(List<MovimientoDTO> dtos) {
    try (var baos = new ByteArrayOutputStream()) {
      // inicializar PDF
      PdfWriter writer = new PdfWriter(baos);
      PdfDocument pdf = new PdfDocument(writer);
      Document doc = new Document(pdf);

      // construir tabla: 6 columnas (id, cuenta, fecha, tipo, valor, saldo)
      Table tabla = new Table(6);
      // encabezados
      tabla.addHeaderCell(new Cell().add(new Paragraph("ID")));
      tabla.addHeaderCell(new Cell().add(new Paragraph("Cuenta")));
      tabla.addHeaderCell(new Cell().add(new Paragraph("Fecha")));
      tabla.addHeaderCell(new Cell().add(new Paragraph("Tipo")));
      tabla.addHeaderCell(new Cell().add(new Paragraph("Valor")));
      tabla.addHeaderCell(new Cell().add(new Paragraph("Saldo")));

      // filas de datos
      for (var dto : dtos) {
        tabla.addCell(new Cell().add(new Paragraph(dto.id().toString())));
        tabla.addCell(new Cell().add(new Paragraph(dto.cuentaNumero().toString())));
        tabla.addCell(new Cell().add(new Paragraph(dto.fecha().toString())));
        tabla.addCell(new Cell().add(new Paragraph(dto.tipo().name())));
        tabla.addCell(new Cell().add(new Paragraph(dto.valor().toString())));
        tabla.addCell(new Cell().add(new Paragraph(dto.saldo().toString())));
      }

      doc.add(tabla);
      doc.close();

      // Base64
      return Base64.getEncoder().encodeToString(baos.toByteArray());
    } catch (Exception e) {
      throw new RuntimeException("Error al generar PDF", e);
    }
  }
}
