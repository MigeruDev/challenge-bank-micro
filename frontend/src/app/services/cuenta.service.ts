import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Cuenta } from '../models/cuenta.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CuentaService extends ApiService {
  private url = `${this.base}/cuentas`;

  list(): Observable<Cuenta[]> {
    return this.http.get<Cuenta[]>(this.url);
  }

  create(c: Cuenta): Observable<Cuenta> {
    return this.http.post<Cuenta>(this.url, c);
  }

  update(numero: number, c: Cuenta): Observable<Cuenta> {
    return this.http.put<Cuenta>(`${this.url}/${numero}`, c);
  }

  delete(numero: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${numero}`);
  }
}
