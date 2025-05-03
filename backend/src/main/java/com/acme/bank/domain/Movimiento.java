package com.acme.bank.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "movimiento")
public class Movimiento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDate fecha;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private TipoMovimiento tipo;

  @Column(nullable = false)
  private BigDecimal valor;

  @Column(nullable = false)
  private BigDecimal saldo;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cuenta_num", nullable = false)
  private Cuenta cuenta;
}
