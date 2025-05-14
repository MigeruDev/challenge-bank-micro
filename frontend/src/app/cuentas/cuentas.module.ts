import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { CuentasRoutingModule } from './cuentas-routing.module';

import { CuentaListComponent } from './cuenta-list/cuenta-list.component';
import { CuentaFormComponent } from './cuenta-form/cuenta-form.component';

import { KeyFilterPipe } from '../clientes/key-filter.pipe';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CuentasRoutingModule,
    CuentaListComponent,
    CuentaFormComponent,
    KeyFilterPipe
  ]
})
export class CuentasModule { }
