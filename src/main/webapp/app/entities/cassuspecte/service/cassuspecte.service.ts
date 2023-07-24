import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICassuspecte, NewCassuspecte } from '../cassuspecte.model';

export type PartialUpdateCassuspecte = Partial<ICassuspecte> & Pick<ICassuspecte, 'id'>;

export type EntityResponseType = HttpResponse<ICassuspecte>;
export type EntityArrayResponseType = HttpResponse<ICassuspecte[]>;

@Injectable({ providedIn: 'root' })
export class CassuspecteService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cassuspectes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(cassuspecte: NewCassuspecte): Observable<EntityResponseType> {
    return this.http.post<ICassuspecte>(this.resourceUrl, cassuspecte, { observe: 'response' });
  }

  update(cassuspecte: ICassuspecte): Observable<EntityResponseType> {
    return this.http.put<ICassuspecte>(`${this.resourceUrl}/${this.getCassuspecteIdentifier(cassuspecte)}`, cassuspecte, {
      observe: 'response',
    });
  }

  partialUpdate(cassuspecte: PartialUpdateCassuspecte): Observable<EntityResponseType> {
    return this.http.patch<ICassuspecte>(`${this.resourceUrl}/${this.getCassuspecteIdentifier(cassuspecte)}`, cassuspecte, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICassuspecte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICassuspecte[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCassuspecteIdentifier(cassuspecte: Pick<ICassuspecte, 'id'>): number {
    return cassuspecte.id;
  }

  compareCassuspecte(o1: Pick<ICassuspecte, 'id'> | null, o2: Pick<ICassuspecte, 'id'> | null): boolean {
    return o1 && o2 ? this.getCassuspecteIdentifier(o1) === this.getCassuspecteIdentifier(o2) : o1 === o2;
  }

  addCassuspecteToCollectionIfMissing<Type extends Pick<ICassuspecte, 'id'>>(
    cassuspecteCollection: Type[],
    ...cassuspectesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const cassuspectes: Type[] = cassuspectesToCheck.filter(isPresent);
    if (cassuspectes.length > 0) {
      const cassuspecteCollectionIdentifiers = cassuspecteCollection.map(
        cassuspecteItem => this.getCassuspecteIdentifier(cassuspecteItem)!
      );
      const cassuspectesToAdd = cassuspectes.filter(cassuspecteItem => {
        const cassuspecteIdentifier = this.getCassuspecteIdentifier(cassuspecteItem);
        if (cassuspecteCollectionIdentifiers.includes(cassuspecteIdentifier)) {
          return false;
        }
        cassuspecteCollectionIdentifiers.push(cassuspecteIdentifier);
        return true;
      });
      return [...cassuspectesToAdd, ...cassuspecteCollection];
    }
    return cassuspecteCollection;
  }
}
