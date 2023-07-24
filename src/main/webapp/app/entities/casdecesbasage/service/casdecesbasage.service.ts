import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICasdecesbasage, NewCasdecesbasage } from '../casdecesbasage.model';

export type PartialUpdateCasdecesbasage = Partial<ICasdecesbasage> & Pick<ICasdecesbasage, 'id'>;

export type EntityResponseType = HttpResponse<ICasdecesbasage>;
export type EntityArrayResponseType = HttpResponse<ICasdecesbasage[]>;

@Injectable({ providedIn: 'root' })
export class CasdecesbasageService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/casdecesbasages');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(casdecesbasage: NewCasdecesbasage): Observable<EntityResponseType> {
    return this.http.post<ICasdecesbasage>(this.resourceUrl, casdecesbasage, { observe: 'response' });
  }

  update(casdecesbasage: ICasdecesbasage): Observable<EntityResponseType> {
    return this.http.put<ICasdecesbasage>(`${this.resourceUrl}/${this.getCasdecesbasageIdentifier(casdecesbasage)}`, casdecesbasage, {
      observe: 'response',
    });
  }

  partialUpdate(casdecesbasage: PartialUpdateCasdecesbasage): Observable<EntityResponseType> {
    return this.http.patch<ICasdecesbasage>(`${this.resourceUrl}/${this.getCasdecesbasageIdentifier(casdecesbasage)}`, casdecesbasage, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICasdecesbasage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICasdecesbasage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCasdecesbasageIdentifier(casdecesbasage: Pick<ICasdecesbasage, 'id'>): number {
    return casdecesbasage.id;
  }

  compareCasdecesbasage(o1: Pick<ICasdecesbasage, 'id'> | null, o2: Pick<ICasdecesbasage, 'id'> | null): boolean {
    return o1 && o2 ? this.getCasdecesbasageIdentifier(o1) === this.getCasdecesbasageIdentifier(o2) : o1 === o2;
  }

  addCasdecesbasageToCollectionIfMissing<Type extends Pick<ICasdecesbasage, 'id'>>(
    casdecesbasageCollection: Type[],
    ...casdecesbasagesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const casdecesbasages: Type[] = casdecesbasagesToCheck.filter(isPresent);
    if (casdecesbasages.length > 0) {
      const casdecesbasageCollectionIdentifiers = casdecesbasageCollection.map(
        casdecesbasageItem => this.getCasdecesbasageIdentifier(casdecesbasageItem)!
      );
      const casdecesbasagesToAdd = casdecesbasages.filter(casdecesbasageItem => {
        const casdecesbasageIdentifier = this.getCasdecesbasageIdentifier(casdecesbasageItem);
        if (casdecesbasageCollectionIdentifiers.includes(casdecesbasageIdentifier)) {
          return false;
        }
        casdecesbasageCollectionIdentifiers.push(casdecesbasageIdentifier);
        return true;
      });
      return [...casdecesbasagesToAdd, ...casdecesbasageCollection];
    }
    return casdecesbasageCollection;
  }
}
