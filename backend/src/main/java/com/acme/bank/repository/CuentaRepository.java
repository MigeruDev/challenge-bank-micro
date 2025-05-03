package com.acme.bank.repository;

import com.acme.bank.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}