package com.acme.bank.web;

import com.acme.bank.domain.Cliente;
import com.acme.bank.repository.ClienteRepository;
import com.acme.bank.web.controller.ClienteController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClienteController.class)
class ClienteControllerIT {

  @Autowired
  private MockMvc mvc;

  @MockBean // Spring injects a Mockito stub in place of the real repo
  private ClienteRepository clienteRepository;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void postCreatesCliente() throws Exception {
    // stub repo.save to echo the entity back
    when(clienteRepository.save(any(Cliente.class)))
        .thenAnswer(inv -> inv.getArgument(0));

    String body = """
        {
          "nombre": "Ana",
          "contrasena": "1234",
          "estado": true
        }
        """;

    mvc.perform(post("/clientes")
        .contentType("application/json")
        .content(body))
        .andExpect(status().isOk());
  }
}
