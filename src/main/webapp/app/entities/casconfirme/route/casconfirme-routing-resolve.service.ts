import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICasconfirme } from '../casconfirme.model';
import { CasconfirmeService } from '../service/casconfirme.service';

@Injectable({ providedIn: 'root' })
export class CasconfirmeRoutingResolveService implements Resolve<ICasconfirme | null> {
  constructor(protected service: CasconfirmeService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICasconfirme | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((casconfirme: HttpResponse<ICasconfirme>) => {
          if (casconfirme.body) {
            return of(casconfirme.body);
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
