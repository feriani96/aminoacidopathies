import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStructurefiche } from '../structurefiche.model';
import { StructureficheService } from '../service/structurefiche.service';

@Injectable({ providedIn: 'root' })
export class StructureficheRoutingResolveService implements Resolve<IStructurefiche | null> {
  constructor(protected service: StructureficheService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStructurefiche | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((structurefiche: HttpResponse<IStructurefiche>) => {
          if (structurefiche.body) {
            return of(structurefiche.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
