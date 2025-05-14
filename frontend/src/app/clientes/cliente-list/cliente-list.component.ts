import { Component, OnInit } from '@angular/core';
import { CommonModule }      from '@angular/common';
import { FormsModule }       from '@angular/forms';
import { Router }            from '@angular/router';

import { ClienteService }    from '../../services/cliente.service';
import { Cliente }           from '../../models/cliente.model';
import { KeyFilterPipe }     from '../key-filter.pipe';

@Component({
  standalone: true,
  selector: 'app-cliente-list',
  imports: [
    CommonModule,     
    FormsModule,     
    KeyFilterPipe     
  ],
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css']
})
export class ClienteListComponent implements OnInit {
  clientes: Cliente[] = [];
  filter = '';

  constructor(
    private svc: ClienteService,
    public  router: Router
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.svc.list().subscribe(data => this.clientes = data);
  }

  delete(id: number): void {
    if (!confirm('¿Eliminar cliente?')) return;
    this.svc.delete(id).subscribe(() => this.load());
  }
}
