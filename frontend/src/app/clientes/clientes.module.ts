import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ClientesRoutingModule } from './clientes-routing.module';

import { ClienteListComponent } from './cliente-list/cliente-list.component';
import { ClienteFormComponent } from './cliente-form/cliente-form.component';
import { KeyFilterPipe     } from './key-filter.pipe';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ClientesRoutingModule,
    
    ClienteListComponent,
    ClienteFormComponent,
    KeyFilterPipe
  ]
})
export class ClientesModule { }
