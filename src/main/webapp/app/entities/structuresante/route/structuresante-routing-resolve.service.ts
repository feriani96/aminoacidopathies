import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStructuresante } from '../structuresante.model';
import { StructuresanteService } from '../service/structuresante.service';

@Injectable({ providedIn: 'root' })
export class StructuresanteRoutingResolveService implements Resolve<IStructuresante | null> {
  constructor(protected service: StructuresanteService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStructuresante | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((structuresante: HttpResponse<IStructuresante>) => {
          if (structuresante.body) {
            return of(structuresante.body);
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
