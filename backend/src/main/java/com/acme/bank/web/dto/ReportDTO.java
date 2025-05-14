package com.acme.bank.web.dto;

import java.math.BigDecimal;
import java.util.List;

public record ReportDTO(
    List<MovimientoDTO> movimientos,
    BigDecimal totalCreditos,
    BigDecimal totalDebitos,
    String pdfBase64) {
}