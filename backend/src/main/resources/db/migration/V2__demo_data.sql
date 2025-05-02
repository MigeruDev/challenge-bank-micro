/* ================================================================
   V2__demo_data.sql  –  Datos de los casos de uso
   ================================================================ */

-- 1) Personas
INSERT INTO persona (persona_id,nombre,direccion,telefono) VALUES
 (1,'Jose Lema','Otavalo sv y principal','098254785'),
 (2,'Marianela Montalvo','Amazonas y NNUU','097548965'),
 (3,'Juan Osorio','13 junio y Equinoccial','098874587');

-- 1) Clientes
INSERT INTO cliente (cliente_id,contrasena,estado) VALUES
 (1,'1234',TRUE),
 (2,'5678',TRUE),
 (3,'1245',TRUE);

-- 2) Cuentas  (saldo = saldo inicial)
INSERT INTO cuenta (numero,tipo,saldo,estado,cliente_id) VALUES
 (478758,'Ahorros',    2000,TRUE,1),   -- Jose
 (225487,'Corriente',  100,TRUE,2),   -- Marianela
 (495878,'Ahorros',      0,TRUE,3),   -- Juan
 (496825,'Ahorros',    540,TRUE,2);   -- Marianela

-- 3) Nueva cuenta corriente de Jose
INSERT INTO cuenta (numero,tipo,saldo,estado,cliente_id) VALUES
 (585545,'Corriente',1000,TRUE,1);

-- 4) Movimientos  (valor positivo = Deposito, negativo = Retiro)
INSERT INTO movimiento (fecha,tipo,valor,saldo,cuenta_num) VALUES
 ('2022-02-10','Retiro',   -575,1425,478758),
 ('2022-02-10','Deposito',  600, 700,225487),
 ('2022-02-08','Deposito',  150, 150,495878),
 ('2022-02-08','Retiro',   -540,   0,496825);

-- 4.1) Sincronizar saldos finales de cada cuenta
UPDATE cuenta SET saldo = 1425 WHERE numero = 478758;
UPDATE cuenta SET saldo =  700 WHERE numero = 225487;
UPDATE cuenta SET saldo =  150 WHERE numero = 495878;
UPDATE cuenta SET saldo =    0 WHERE numero = 496825;
