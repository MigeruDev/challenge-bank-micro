import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  standalone: true,
  name: 'keyFilter'
})
export class KeyFilterPipe implements PipeTransform {
  /**
   * Filtra un array de objetos, buscando el término en cualquiera de las keys dadas.
   * @param items Array de objetos a filtrar
   * @param fields Lista de keys sobre las que buscar
   * @param term  Término de búsqueda
   */
  transform<T>(items: T[], fields: (keyof T)[], term: string): T[] {
    if (!items || !term) {
      return items;
    }
    const lower = term.toLowerCase();
    return items.filter(item =>
      fields.some(field => {
        const value = item[field];
        return value != null &&
               value.toString().toLowerCase().includes(lower);
      })
    );
  }
}
