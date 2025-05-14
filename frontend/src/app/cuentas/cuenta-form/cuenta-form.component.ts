import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormGroup
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { CuentaService } from '../../services/cuenta.service';
import { Cuenta } from '../../models/cuenta.model';
import { TipoCuenta } from '../../models/tipo-cuenta.model';

@Component({
  standalone: true,
  selector: 'app-cuenta-form',
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './cuenta-form.component.html'
})
export class CuentaFormComponent implements OnInit {
  form: FormGroup;
  tipos = Object.values(TipoCuenta);
  numero?: number;

  constructor(
    private fb: FormBuilder,
    private svc: CuentaService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // Inicializa el formulario **después** de tener fb disponible
    this.form = this.fb.group({
      numero: [null, [Validators.required]],
      tipo: [TipoCuenta.Ahorro, [Validators.required]],
      saldo: [0, [Validators.required, Validators.min(0)]],
      estado: [true, [Validators.required]],
      clienteId: [null, [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['numero']) {
        this.numero = +params['numero'];
        this.svc.list().subscribe((ctas: Cuenta[]) => {
          const c = ctas.find(x => x.numero === this.numero);
          if (c) {
            // Parcheo manual para alinear tipos
            this.form.patchValue({
              numero: c.numero,
              tipo: c.tipo,
              saldo: c.saldo,
              estado: c.estado,
              clienteId: c.clienteId
            });
          }
        });
      }
    });
  }

  submit(): void {
    if (this.form.invalid) {
      return;
    }
    const payload = this.form.value as Cuenta;
    const call = this.numero
      ? this.svc.update(this.numero, payload)
      : this.svc.create(payload);

    call.subscribe(() => this.router.navigate(['/cuentas']));
  }
}
