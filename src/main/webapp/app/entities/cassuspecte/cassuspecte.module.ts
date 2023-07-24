import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CassuspecteComponent } from './list/cassuspecte.component';
import { CassuspecteDetailComponent } from './detail/cassuspecte-detail.component';
import { CassuspecteUpdateComponent } from './update/cassuspecte-update.component';
import { CassuspecteDeleteDialogComponent } from './delete/cassuspecte-delete-dialog.component';
import { CassuspecteRoutingModule } from './route/cassuspecte-routing.module';

@NgModule({
  imports: [SharedModule, CassuspecteRoutingModule],
  declarations: [CassuspecteComponent, CassuspecteDetailComponent, CassuspecteUpdateComponent, CassuspecteDeleteDialogComponent],
})
export class CassuspecteModule {}
