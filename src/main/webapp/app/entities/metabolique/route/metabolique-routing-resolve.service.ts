import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMetabolique } from '../metabolique.model';
import { MetaboliqueService } from '../service/metabolique.service';

@Injectable({ providedIn: 'root' })
export class MetaboliqueRoutingResolveService implements Resolve<IMetabolique | null> {
  constructor(protected service: MetaboliqueService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMetabolique | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((metabolique: HttpResponse<IMetabolique>) => {
          if (metabolique.body) {
            return of(metabolique.body);
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
