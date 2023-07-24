import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IServicesante, NewServicesante } from '../servicesante.model';

export type PartialUpdateServicesante = Partial<IServicesante> & Pick<IServicesante, 'id'>;

export type EntityResponseType = HttpResponse<IServicesante>;
export type EntityArrayResponseType = HttpResponse<IServicesante[]>;

@Injectable({ providedIn: 'root' })
export class ServicesanteService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/servicesantes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(servicesante: NewServicesante): Observable<EntityResponseType> {
    return this.http.post<IServicesante>(this.resourceUrl, servicesante, { observe: 'response' });
  }

  update(servicesante: IServicesante): Observable<EntityResponseType> {
    return this.http.put<IServicesante>(`${this.resourceUrl}/${this.getServicesanteIdentifier(servicesante)}`, servicesante, {
      observe: 'response',
    });
  }

  partialUpdate(servicesante: PartialUpdateServicesante): Observable<EntityResponseType> {
    return this.http.patch<IServicesante>(`${this.resourceUrl}/${this.getServicesanteIdentifier(servicesante)}`, servicesante, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IServicesante>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IServicesante[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getServicesanteIdentifier(servicesante: Pick<IServicesante, 'id'>): number {
    return servicesante.id;
  }

  compareServicesante(o1: Pick<IServicesante, 'id'> | null, o2: Pick<IServicesante, 'id'> | null): boolean {
    return o1 && o2 ? this.getServicesanteIdentifier(o1) === this.getServicesanteIdentifier(o2) : o1 === o2;
  }

  addServicesanteToCollectionIfMissing<Type extends Pick<IServicesante, 'id'>>(
    servicesanteCollection: Type[],
    ...servicesantesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const servicesantes: Type[] = servicesantesToCheck.filter(isPresent);
    if (servicesantes.length > 0) {
      const servicesanteCollectionIdentifiers = servicesanteCollection.map(
        servicesanteItem => this.getServicesanteIdentifier(servicesanteItem)!
      );
      const servicesantesToAdd = servicesantes.filter(servicesanteItem => {
        const servicesanteIdentifier = this.getServicesanteIdentifier(servicesanteItem);
        if (servicesanteCollectionIdentifiers.includes(servicesanteIdentifier)) {
          return false;
        }
        servicesanteCollectionIdentifiers.push(servicesanteIdentifier);
        return true;
      });
      return [...servicesantesToAdd, ...servicesanteCollection];
    }
    return servicesanteCollection;
  }
}
