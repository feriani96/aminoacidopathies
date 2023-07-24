import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICassuspecte } from '../cassuspecte.model';
import { CassuspecteService } from '../service/cassuspecte.service';

@Injectable({ providedIn: 'root' })
export class CassuspecteRoutingResolveService implements Resolve<ICassuspecte | null> {
  constructor(protected service: CassuspecteService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICassuspecte | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((cassuspecte: HttpResponse<ICassuspecte>) => {
          if (cassuspecte.body) {
            return of(cassuspecte.body);
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
