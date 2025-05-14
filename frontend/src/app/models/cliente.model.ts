// src/app/models/cliente.model.ts
export interface Cliente {
  clienteId:     number;
  nombre:        string;
  genero:        string;
  edad:          number;
  identificacion:string;
  direccion:     string;
  telefono:      string;
  contrasena:    string;
  estado:        boolean;
}
