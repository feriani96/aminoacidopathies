import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFiche, NewFiche } from '../fiche.model';

export type PartialUpdateFiche = Partial<IFiche> & Pick<IFiche, 'id'>;

type RestOf<T extends IFiche | NewFiche> = Omit<
  T,
  'datemaj' | 'date_enregistrement' | 'date_naissance' | 'date_deces' | 'date_derniere_evaluation' | 'date_diagnostic'
> & {
  datemaj?: string | null;
  date_enregistrement?: string | null;
  date_naissance?: string | null;
  date_deces?: string | null;
  date_derniere_evaluation?: string | null;
  date_diagnostic?: string | null;
};

export type RestFiche = RestOf<IFiche>;

export type NewRestFiche = RestOf<NewFiche>;

export type PartialUpdateRestFiche = RestOf<PartialUpdateFiche>;

export type EntityResponseType = HttpResponse<IFiche>;
export type EntityArrayResponseType = HttpResponse<IFiche[]>;

@Injectable({ providedIn: 'root' })
export class FicheService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fiches');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(fiche: NewFiche): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fiche);
    return this.http.post<RestFiche>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(fiche: IFiche): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fiche);
    return this.http
      .put<RestFiche>(`${this.resourceUrl}/${this.getFicheIdentifier(fiche)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(fiche: PartialUpdateFiche): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fiche);
    return this.http
      .patch<RestFiche>(`${this.resourceUrl}/${this.getFicheIdentifier(fiche)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFiche>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFiche[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFicheIdentifier(fiche: Pick<IFiche, 'id'>): number {
    return fiche.id;
  }

  compareFiche(o1: Pick<IFiche, 'id'> | null, o2: Pick<IFiche, 'id'> | null): boolean {
    return o1 && o2 ? this.getFicheIdentifier(o1) === this.getFicheIdentifier(o2) : o1 === o2;
  }

  addFicheToCollectionIfMissing<Type extends Pick<IFiche, 'id'>>(
    ficheCollection: Type[],
    ...fichesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const fiches: Type[] = fichesToCheck.filter(isPresent);
    if (fiches.length > 0) {
      const ficheCollectionIdentifiers = ficheCollection.map(ficheItem => this.getFicheIdentifier(ficheItem)!);
      const fichesToAdd = fiches.filter(ficheItem => {
        const ficheIdentifier = this.getFicheIdentifier(ficheItem);
        if (ficheCollectionIdentifiers.includes(ficheIdentifier)) {
          return false;
        }
        ficheCollectionIdentifiers.push(ficheIdentifier);
        return true;
      });
      return [...fichesToAdd, ...ficheCollection];
    }
    return ficheCollection;
  }

  protected convertDateFromClient<T extends IFiche | NewFiche | PartialUpdateFiche>(fiche: T): RestOf<T> {
    return {
      ...fiche,
      datemaj: fiche.datemaj?.format(DATE_FORMAT) ?? null,
      date_enregistrement: fiche.date_enregistrement?.format(DATE_FORMAT) ?? null,
      date_naissance: fiche.date_naissance?.format(DATE_FORMAT) ?? null,
      date_deces: fiche.date_deces?.format(DATE_FORMAT) ?? null,
      date_derniere_evaluation: fiche.date_derniere_evaluation?.format(DATE_FORMAT) ?? null,
      date_diagnostic: fiche.date_diagnostic?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFiche: RestFiche): IFiche {
    return {
      ...restFiche,
      datemaj: restFiche.datemaj ? dayjs(restFiche.datemaj) : undefined,
      date_enregistrement: restFiche.date_enregistrement ? dayjs(restFiche.date_enregistrement) : undefined,
      date_naissance: restFiche.date_naissance ? dayjs(restFiche.date_naissance) : undefined,
      date_deces: restFiche.date_deces ? dayjs(restFiche.date_deces) : undefined,
      date_derniere_evaluation: restFiche.date_derniere_evaluation ? dayjs(restFiche.date_derniere_evaluation) : undefined,
      date_diagnostic: restFiche.date_diagnostic ? dayjs(restFiche.date_diagnostic) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFiche>): HttpResponse<IFiche> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFiche[]>): HttpResponse<IFiche[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
