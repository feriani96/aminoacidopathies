import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IServicesante } from '../servicesante.model';
import { ServicesanteService } from '../service/servicesante.service';

@Injectable({ providedIn: 'root' })
export class ServicesanteRoutingResolveService implements Resolve<IServicesante | null> {
  constructor(protected service: ServicesanteService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IServicesante | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((servicesante: HttpResponse<IServicesante>) => {
          if (servicesante.body) {
            return of(servicesante.body);
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
