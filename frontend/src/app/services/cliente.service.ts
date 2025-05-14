import { Injectable } from '@angular/core';
import { ApiService }    from './api.service';
import { Cliente }       from '../models/cliente.model';
import { Observable }    from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ClienteService extends ApiService {
  private url = `${this.base}/clientes`;

  list(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.url);
  }

  create(c: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.url, c);
  }

  update(id: number, c: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.url}/${id}`, c);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
