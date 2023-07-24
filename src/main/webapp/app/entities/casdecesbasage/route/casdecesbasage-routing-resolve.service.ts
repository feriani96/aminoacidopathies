import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICasdecesbasage } from '../casdecesbasage.model';
import { CasdecesbasageService } from '../service/casdecesbasage.service';

@Injectable({ providedIn: 'root' })
export class CasdecesbasageRoutingResolveService implements Resolve<ICasdecesbasage | null> {
  constructor(protected service: CasdecesbasageService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICasdecesbasage | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((casdecesbasage: HttpResponse<ICasdecesbasage>) => {
          if (casdecesbasage.body) {
            return of(casdecesbasage.body);
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
