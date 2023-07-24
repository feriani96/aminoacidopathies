import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CassuspecteComponent } from '../list/cassuspecte.component';
import { CassuspecteDetailComponent } from '../detail/cassuspecte-detail.component';
import { CassuspecteUpdateComponent } from '../update/cassuspecte-update.component';
import { CassuspecteRoutingResolveService } from './cassuspecte-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const cassuspecteRoute: Routes = [
  {
    path: '',
    component: CassuspecteComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CassuspecteDetailComponent,
    resolve: {
      cassuspecte: CassuspecteRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CassuspecteUpdateComponent,
    resolve: {
      cassuspecte: CassuspecteRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CassuspecteUpdateComponent,
    resolve: {
      cassuspecte: CassuspecteRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(cassuspecteRoute)],
  exports: [RouterModule],
})
export class CassuspecteRoutingModule {}
