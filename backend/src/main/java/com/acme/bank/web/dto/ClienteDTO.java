package com.acme.bank.web.dto;

import jakarta.validation.constraints.*;

public record ClienteDTO(
    @NotNull
    Long clienteId,

    /* Datos personales heredados de Persona */
    @NotBlank 
    @Size(max = 80)
    String nombre,

    @NotBlank 
    @Size(max = 10)
    String genero,

    @NotNull 
    @Min(0)
    Integer edad,

    @NotBlank 
    @Size(max = 20)
    String identificacion,

    @Size(max = 120)
    String direccion,

    @Size(max = 20)
    String telefono,

    /* Propios de Cliente */
    @NotBlank 
    @Size(min = 4, max = 72)
    String contrasena,

    @NotNull
    Boolean estado

) {}
