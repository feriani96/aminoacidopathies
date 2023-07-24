import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CasconfirmeComponent } from '../list/casconfirme.component';
import { CasconfirmeDetailComponent } from '../detail/casconfirme-detail.component';
import { CasconfirmeUpdateComponent } from '../update/casconfirme-update.component';
import { CasconfirmeRoutingResolveService } from './casconfirme-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const casconfirmeRoute: Routes = [
  {
    path: '',
    component: CasconfirmeComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CasconfirmeDetailComponent,
    resolve: {
      casconfirme: CasconfirmeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CasconfirmeUpdateComponent,
    resolve: {
      casconfirme: CasconfirmeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CasconfirmeUpdateComponent,
    resolve: {
      casconfirme: CasconfirmeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(casconfirmeRoute)],
  exports: [RouterModule],
})
export class CasconfirmeRoutingModule {}
