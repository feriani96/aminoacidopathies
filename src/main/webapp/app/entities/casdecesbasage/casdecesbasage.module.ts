import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CasdecesbasageComponent } from './list/casdecesbasage.component';
import { CasdecesbasageDetailComponent } from './detail/casdecesbasage-detail.component';
import { CasdecesbasageUpdateComponent } from './update/casdecesbasage-update.component';
import { CasdecesbasageDeleteDialogComponent } from './delete/casdecesbasage-delete-dialog.component';
import { CasdecesbasageRoutingModule } from './route/casdecesbasage-routing.module';

@NgModule({
  imports: [SharedModule, CasdecesbasageRoutingModule],
  declarations: [
    CasdecesbasageComponent,
    CasdecesbasageDetailComponent,
    CasdecesbasageUpdateComponent,
    CasdecesbasageDeleteDialogComponent,
  ],
})
export class CasdecesbasageModule {}
