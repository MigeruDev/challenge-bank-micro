package com.acme.bank.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class Cliente extends Persona {
  @Column(nullable = false)
  private String contrasena;

  private Boolean estado;
}
