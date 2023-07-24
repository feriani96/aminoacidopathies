import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMetabolique, NewMetabolique } from '../metabolique.model';

export type PartialUpdateMetabolique = Partial<IMetabolique> & Pick<IMetabolique, 'id'>;

export type EntityResponseType = HttpResponse<IMetabolique>;
export type EntityArrayResponseType = HttpResponse<IMetabolique[]>;

@Injectable({ providedIn: 'root' })
export class MetaboliqueService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/metaboliques');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(metabolique: NewMetabolique): Observable<EntityResponseType> {
    return this.http.post<IMetabolique>(this.resourceUrl, metabolique, { observe: 'response' });
  }

  update(metabolique: IMetabolique): Observable<EntityResponseType> {
    return this.http.put<IMetabolique>(`${this.resourceUrl}/${this.getMetaboliqueIdentifier(metabolique)}`, metabolique, {
      observe: 'response',
    });
  }

  partialUpdate(metabolique: PartialUpdateMetabolique): Observable<EntityResponseType> {
    return this.http.patch<IMetabolique>(`${this.resourceUrl}/${this.getMetaboliqueIdentifier(metabolique)}`, metabolique, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMetabolique>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMetabolique[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMetaboliqueIdentifier(metabolique: Pick<IMetabolique, 'id'>): number {
    return metabolique.id;
  }

  compareMetabolique(o1: Pick<IMetabolique, 'id'> | null, o2: Pick<IMetabolique, 'id'> | null): boolean {
    return o1 && o2 ? this.getMetaboliqueIdentifier(o1) === this.getMetaboliqueIdentifier(o2) : o1 === o2;
  }

  addMetaboliqueToCollectionIfMissing<Type extends Pick<IMetabolique, 'id'>>(
    metaboliqueCollection: Type[],
    ...metaboliquesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const metaboliques: Type[] = metaboliquesToCheck.filter(isPresent);
    if (metaboliques.length > 0) {
      const metaboliqueCollectionIdentifiers = metaboliqueCollection.map(
        metaboliqueItem => this.getMetaboliqueIdentifier(metaboliqueItem)!
      );
      const metaboliquesToAdd = metaboliques.filter(metaboliqueItem => {
        const metaboliqueIdentifier = this.getMetaboliqueIdentifier(metaboliqueItem);
        if (metaboliqueCollectionIdentifiers.includes(metaboliqueIdentifier)) {
          return false;
        }
        metaboliqueCollectionIdentifiers.push(metaboliqueIdentifier);
        return true;
      });
      return [...metaboliquesToAdd, ...metaboliqueCollection];
    }
    return metaboliqueCollection;
  }
}
