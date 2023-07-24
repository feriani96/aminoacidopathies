import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ServicesanteComponent } from '../list/servicesante.component';
import { ServicesanteDetailComponent } from '../detail/servicesante-detail.component';
import { ServicesanteUpdateComponent } from '../update/servicesante-update.component';
import { ServicesanteRoutingResolveService } from './servicesante-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const servicesanteRoute: Routes = [
  {
    path: '',
    component: ServicesanteComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ServicesanteDetailComponent,
    resolve: {
      servicesante: ServicesanteRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ServicesanteUpdateComponent,
    resolve: {
      servicesante: ServicesanteRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ServicesanteUpdateComponent,
    resolve: {
      servicesante: ServicesanteRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(servicesanteRoute)],
  exports: [RouterModule],
})
export class ServicesanteRoutingModule {}
