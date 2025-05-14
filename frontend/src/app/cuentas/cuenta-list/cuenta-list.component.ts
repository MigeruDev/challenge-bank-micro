import { Component, OnInit } from '@angular/core';
import { CuentaService } from '../../services/cuenta.service';
import { Cuenta }        from '../../models/cuenta.model';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-cuenta-list',
  templateUrl: './cuenta-list.component.html',
  imports: []  // no hace falta si lo importas en el módulo
})
export class CuentaListComponent implements OnInit {
  cuentas: Cuenta[] = [];
  filter = '';

  constructor(
    private svc: CuentaService,
    private router: Router
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
