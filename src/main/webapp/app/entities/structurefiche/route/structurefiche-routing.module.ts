import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { StructureficheComponent } from '../list/structurefiche.component';
import { StructureficheDetailComponent } from '../detail/structurefiche-detail.component';
import { StructureficheUpdateComponent } from '../update/structurefiche-update.component';
import { StructureficheRoutingResolveService } from './structurefiche-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const structureficheRoute: Routes = [
  {
    path: '',
    component: StructureficheComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StructureficheDetailComponent,
    resolve: {
      structurefiche: StructureficheRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StructureficheUpdateComponent,
    resolve: {
      structurefiche: StructureficheRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StructureficheUpdateComponent,
    resolve: {
      structurefiche: StructureficheRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(structureficheRoute)],
  exports: [RouterModule],
})
export class StructureficheRoutingModule {}
