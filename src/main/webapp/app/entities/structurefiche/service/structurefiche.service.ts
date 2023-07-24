import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStructurefiche, NewStructurefiche } from '../structurefiche.model';

export type PartialUpdateStructurefiche = Partial<IStructurefiche> & Pick<IStructurefiche, 'id'>;

export type EntityResponseType = HttpResponse<IStructurefiche>;
export type EntityArrayResponseType = HttpResponse<IStructurefiche[]>;

@Injectable({ providedIn: 'root' })
export class StructureficheService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/structurefiches');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(structurefiche: NewStructurefiche): Observable<EntityResponseType> {
    return this.http.post<IStructurefiche>(this.resourceUrl, structurefiche, { observe: 'response' });
  }

  update(structurefiche: IStructurefiche): Observable<EntityResponseType> {
    return this.http.put<IStructurefiche>(`${this.resourceUrl}/${this.getStructureficheIdentifier(structurefiche)}`, structurefiche, {
      observe: 'response',
    });
  }

  partialUpdate(structurefiche: PartialUpdateStructurefiche): Observable<EntityResponseType> {
    return this.http.patch<IStructurefiche>(`${this.resourceUrl}/${this.getStructureficheIdentifier(structurefiche)}`, structurefiche, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStructurefiche>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStructurefiche[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getStructureficheIdentifier(structurefiche: Pick<IStructurefiche, 'id'>): number {
    return structurefiche.id;
  }

  compareStructurefiche(o1: Pick<IStructurefiche, 'id'> | null, o2: Pick<IStructurefiche, 'id'> | null): boolean {
    return o1 && o2 ? this.getStructureficheIdentifier(o1) === this.getStructureficheIdentifier(o2) : o1 === o2;
  }

  addStructureficheToCollectionIfMissing<Type extends Pick<IStructurefiche, 'id'>>(
    structureficheCollection: Type[],
    ...structurefichesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const structurefiches: Type[] = structurefichesToCheck.filter(isPresent);
    if (structurefiches.length > 0) {
      const structureficheCollectionIdentifiers = structureficheCollection.map(
        structureficheItem => this.getStructureficheIdentifier(structureficheItem)!
      );
      const structurefichesToAdd = structurefiches.filter(structureficheItem => {
        const structureficheIdentifier = this.getStructureficheIdentifier(structureficheItem);
        if (structureficheCollectionIdentifiers.includes(structureficheIdentifier)) {
          return false;
        }
        structureficheCollectionIdentifiers.push(structureficheIdentifier);
        return true;
      });
      return [...structurefichesToAdd, ...structureficheCollection];
    }
    return structureficheCollection;
  }
}
