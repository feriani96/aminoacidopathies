import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CasdecesbasageComponent } from '../list/casdecesbasage.component';
import { CasdecesbasageDetailComponent } from '../detail/casdecesbasage-detail.component';
import { CasdecesbasageUpdateComponent } from '../update/casdecesbasage-update.component';
import { CasdecesbasageRoutingResolveService } from './casdecesbasage-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const casdecesbasageRoute: Routes = [
  {
    path: '',
    component: CasdecesbasageComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CasdecesbasageDetailComponent,
    resolve: {
      casdecesbasage: CasdecesbasageRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CasdecesbasageUpdateComponent,
    resolve: {
      casdecesbasage: CasdecesbasageRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CasdecesbasageUpdateComponent,
    resolve: {
      casdecesbasage: CasdecesbasageRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(casdecesbasageRoute)],
  exports: [RouterModule],
})
export class CasdecesbasageRoutingModule {}
