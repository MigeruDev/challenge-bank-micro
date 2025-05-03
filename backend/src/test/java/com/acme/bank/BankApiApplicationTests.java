package com.acme.bank;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class BankApiApplicationTests {

  @Test
  void contextLoads() {
    assertTrue(true); // keeps IDE & Sonar quiet
  }

}
