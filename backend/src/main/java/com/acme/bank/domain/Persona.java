package com.acme.bank.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long personaId;

  private String nombre;
  private String genero;
  private Integer edad;
  private String identificacion;
  private String direccion;
  private String telefono;
}
