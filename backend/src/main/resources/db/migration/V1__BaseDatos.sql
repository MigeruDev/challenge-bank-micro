/* ================================================================
   V1__BaseDatos.sql  –  Esquema exacto
   ================================================================ */

CREATE TABLE persona (
  persona_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre         VARCHAR(80)  NOT NULL,
  genero         VARCHAR(10),
  edad           INT,
  identificacion VARCHAR(20)  UNIQUE,
  direccion      VARCHAR(120),
  telefono       VARCHAR(20)
);

CREATE TABLE cliente (
  cliente_id   BIGINT PRIMARY KEY,
  contrasena   VARCHAR(72) NOT NULL,
  estado       BOOLEAN     NOT NULL,
  CONSTRAINT fk_cliente_persona
    FOREIGN KEY (cliente_id) REFERENCES persona(persona_id)
);

CREATE TABLE cuenta (
  numero       BIGINT PRIMARY KEY,
  tipo         ENUM ('AHORROS','CORRIENTE') NOT NULL,
  saldo        DECIMAL(15,2) NOT NULL,
  estado       BOOLEAN       NOT NULL,
  cliente_id   BIGINT        NOT NULL,
  CONSTRAINT fk_cuenta_cliente
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

CREATE TABLE movimiento (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  fecha        DATE          NOT NULL,
  tipo         ENUM ('DEPOSITO','RETIRO') NOT NULL,
  valor        DECIMAL(15,2) NOT NULL,
  saldo        DECIMAL(15,2) NOT NULL,
  cuenta_num   BIGINT        NOT NULL,
  CONSTRAINT fk_mov_cuenta
    FOREIGN KEY (cuenta_num) REFERENCES cuenta(numero)
);
