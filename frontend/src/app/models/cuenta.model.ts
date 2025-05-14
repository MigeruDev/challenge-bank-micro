import { TipoCuenta } from './tipo-cuenta.model';

export interface Cuenta {
  numero:     number;
  tipo:       TipoCuenta;
  saldo:      number;
  estado:     boolean;
  clienteId:  number;
}
