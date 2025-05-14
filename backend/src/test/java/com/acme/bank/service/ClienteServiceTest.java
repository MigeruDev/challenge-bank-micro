package com.acme.bank.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import com.acme.bank.TestcontainersConfiguration;
import com.acme.bank.web.dto.ClienteDTO;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
class ClienteServiceTest {

  @Autowired
  private ClienteService service;

  @Test
  void createPersistsCliente() {
    ClienteDTO dto = new ClienteDTO(
        null, // clienteId
        "Ana", // nombre
        "F", // genero
        30, // edad
        "01020304", // identificacion
        "Calle Falsa 123", // direccion
        "099999999", // telefono
        "secreto", // contrasena
        true // estado
    );

    ClienteDTO saved = service.crear(dto);

    assertThat(saved.clienteId()).isNotNull();
    assertThat(saved.nombre()).isEqualTo("Ana");
  }

}
