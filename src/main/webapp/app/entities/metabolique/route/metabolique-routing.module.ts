import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MetaboliqueComponent } from '../list/metabolique.component';
import { MetaboliqueDetailComponent } from '../detail/metabolique-detail.component';
import { MetaboliqueUpdateComponent } from '../update/metabolique-update.component';
import { MetaboliqueRoutingResolveService } from './metabolique-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const metaboliqueRoute: Routes = [
  {
    path: '',
    component: MetaboliqueComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MetaboliqueDetailComponent,
    resolve: {
      metabolique: MetaboliqueRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MetaboliqueUpdateComponent,
    resolve: {
      metabolique: MetaboliqueRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MetaboliqueUpdateComponent,
    resolve: {
      metabolique: MetaboliqueRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(metaboliqueRoute)],
  exports: [RouterModule],
})
export class MetaboliqueRoutingModule {}
