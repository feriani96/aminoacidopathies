import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStructuresante, NewStructuresante } from '../structuresante.model';

export type PartialUpdateStructuresante = Partial<IStructuresante> & Pick<IStructuresante, 'id'>;

export type EntityResponseType = HttpResponse<IStructuresante>;
export type EntityArrayResponseType = HttpResponse<IStructuresante[]>;

@Injectable({ providedIn: 'root' })
export class StructuresanteService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/structuresantes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(structuresante: NewStructuresante): Observable<EntityResponseType> {
    return this.http.post<IStructuresante>(this.resourceUrl, structuresante, { observe: 'response' });
  }

  update(structuresante: IStructuresante): Observable<EntityResponseType> {
    return this.http.put<IStructuresante>(`${this.resourceUrl}/${this.getStructuresanteIdentifier(structuresante)}`, structuresante, {
      observe: 'response',
    });
  }

  partialUpdate(structuresante: PartialUpdateStructuresante): Observable<EntityResponseType> {
    return this.http.patch<IStructuresante>(`${this.resourceUrl}/${this.getStructuresanteIdentifier(structuresante)}`, structuresante, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStructuresante>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStructuresante[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getStructuresanteIdentifier(structuresante: Pick<IStructuresante, 'id'>): number {
    return structuresante.id;
  }

  compareStructuresante(o1: Pick<IStructuresante, 'id'> | null, o2: Pick<IStructuresante, 'id'> | null): boolean {
    return o1 && o2 ? this.getStructuresanteIdentifier(o1) === this.getStructuresanteIdentifier(o2) : o1 === o2;
  }

  addStructuresanteToCollectionIfMissing<Type extends Pick<IStructuresante, 'id'>>(
    structuresanteCollection: Type[],
    ...structuresantesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const structuresantes: Type[] = structuresantesToCheck.filter(isPresent);
    if (structuresantes.length > 0) {
      const structuresanteCollectionIdentifiers = structuresanteCollection.map(
        structuresanteItem => this.getStructuresanteIdentifier(structuresanteItem)!
      );
      const structuresantesToAdd = structuresantes.filter(structuresanteItem => {
        const structuresanteIdentifier = this.getStructuresanteIdentifier(structuresanteItem);
        if (structuresanteCollectionIdentifiers.includes(structuresanteIdentifier)) {
          return false;
        }
        structuresanteCollectionIdentifiers.push(structuresanteIdentifier);
        return true;
      });
      return [...structuresantesToAdd, ...structuresanteCollection];
    }
    return structuresanteCollection;
  }
}
