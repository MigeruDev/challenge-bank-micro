package com.acme.bank.repository;

import com.acme.bank.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
  List<Movimiento> findByCuentaNumeroAndFechaBetween(
      Long numero, LocalDate from, LocalDate to);
}
