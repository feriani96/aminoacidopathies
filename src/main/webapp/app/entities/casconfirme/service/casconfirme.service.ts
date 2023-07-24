import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICasconfirme, NewCasconfirme } from '../casconfirme.model';

export type PartialUpdateCasconfirme = Partial<ICasconfirme> & Pick<ICasconfirme, 'id'>;

export type EntityResponseType = HttpResponse<ICasconfirme>;
export type EntityArrayResponseType = HttpResponse<ICasconfirme[]>;

@Injectable({ providedIn: 'root' })
export class CasconfirmeService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/casconfirmes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(casconfirme: NewCasconfirme): Observable<EntityResponseType> {
    return this.http.post<ICasconfirme>(this.resourceUrl, casconfirme, { observe: 'response' });
  }

  update(casconfirme: ICasconfirme): Observable<EntityResponseType> {
    return this.http.put<ICasconfirme>(`${this.resourceUrl}/${this.getCasconfirmeIdentifier(casconfirme)}`, casconfirme, {
      observe: 'response',
    });
  }

  partialUpdate(casconfirme: PartialUpdateCasconfirme): Observable<EntityResponseType> {
    return this.http.patch<ICasconfirme>(`${this.resourceUrl}/${this.getCasconfirmeIdentifier(casconfirme)}`, casconfirme, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICasconfirme>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICasconfirme[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCasconfirmeIdentifier(casconfirme: Pick<ICasconfirme, 'id'>): number {
    return casconfirme.id;
  }

  compareCasconfirme(o1: Pick<ICasconfirme, 'id'> | null, o2: Pick<ICasconfirme, 'id'> | null): boolean {
    return o1 && o2 ? this.getCasconfirmeIdentifier(o1) === this.getCasconfirmeIdentifier(o2) : o1 === o2;
  }

  addCasconfirmeToCollectionIfMissing<Type extends Pick<ICasconfirme, 'id'>>(
    casconfirmeCollection: Type[],
    ...casconfirmesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const casconfirmes: Type[] = casconfirmesToCheck.filter(isPresent);
    if (casconfirmes.length > 0) {
      const casconfirmeCollectionIdentifiers = casconfirmeCollection.map(
        casconfirmeItem => this.getCasconfirmeIdentifier(casconfirmeItem)!
      );
      const casconfirmesToAdd = casconfirmes.filter(casconfirmeItem => {
        const casconfirmeIdentifier = this.getCasconfirmeIdentifier(casconfirmeItem);
        if (casconfirmeCollectionIdentifiers.includes(casconfirmeIdentifier)) {
          return false;
        }
        casconfirmeCollectionIdentifiers.push(casconfirmeIdentifier);
        return true;
      });
      return [...casconfirmesToAdd, ...casconfirmeCollection];
    }
    return casconfirmeCollection;
  }
}
