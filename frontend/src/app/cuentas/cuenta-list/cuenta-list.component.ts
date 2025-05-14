import { Component, OnInit } from '@angular/core';
import { CommonModule }      from '@angular/common';
import { FormsModule }       from '@angular/forms';
import { Router }            from '@angular/router';

import { CuentaService }     from '../../services/cuenta.service';
import { Cuenta }            from '../../models/cuenta.model';
import { KeyFilterPipe }     from '../../clientes/key-filter.pipe';

@Component({
  standalone: true,
  selector: 'app-cuenta-list',
  imports: [
    CommonModule,  
    FormsModule, 
    KeyFilterPipe 
  ],
  templateUrl: './cuenta-list.component.html',
})
export class CuentaListComponent implements OnInit {
  cuentas: Cuenta[] = [];
  filter = '';
  
  constructor(
    private svc: CuentaService,
    public  router: Router
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.svc.list().subscribe(data => this.cuentas = data);
  }

  delete(numero: number) {
    if (!confirm('¿Eliminar cuenta?')) return;
    this.svc.delete(numero).subscribe(() => this.load());
  }
}
