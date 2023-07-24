import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CasconfirmeComponent } from './list/casconfirme.component';
import { CasconfirmeDetailComponent } from './detail/casconfirme-detail.component';
import { CasconfirmeUpdateComponent } from './update/casconfirme-update.component';
import { CasconfirmeDeleteDialogComponent } from './delete/casconfirme-delete-dialog.component';
import { CasconfirmeRoutingModule } from './route/casconfirme-routing.module';

@NgModule({
  imports: [SharedModule, CasconfirmeRoutingModule],
  declarations: [CasconfirmeComponent, CasconfirmeDetailComponent, CasconfirmeUpdateComponent, CasconfirmeDeleteDialogComponent],
})
export class CasconfirmeModule {}
