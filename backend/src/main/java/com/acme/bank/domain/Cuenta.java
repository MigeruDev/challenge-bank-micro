package com.acme.bank.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cuenta")
public class Cuenta {
  @Id
  @Column(name = "numero")
  private Long numero;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo", nullable = false, length = 10)
  private TipoCuenta tipo;

  @Column(name = "saldo", nullable = false)
  private BigDecimal saldo;

  @Column(name = "estado", nullable = false)
  private Boolean estado;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cliente_id", nullable = false)
  private Cliente cliente;
}
