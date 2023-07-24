import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { StructuresanteComponent } from '../list/structuresante.component';
import { StructuresanteDetailComponent } from '../detail/structuresante-detail.component';
import { StructuresanteUpdateComponent } from '../update/structuresante-update.component';
import { StructuresanteRoutingResolveService } from './structuresante-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const structuresanteRoute: Routes = [
  {
    path: '',
    component: StructuresanteComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StructuresanteDetailComponent,
    resolve: {
      structuresante: StructuresanteRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StructuresanteUpdateComponent,
    resolve: {
      structuresante: StructuresanteRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StructuresanteUpdateComponent,
    resolve: {
      structuresante: StructuresanteRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(structuresanteRoute)],
  exports: [RouterModule],
})
export class StructuresanteRoutingModule {}
