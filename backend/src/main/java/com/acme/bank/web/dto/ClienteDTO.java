package com.acme.bank.web.dto;

public record ClienteDTO(Long id, String nombre, String direccion,
    String telefono, String contrasena, Boolean estado) {
}