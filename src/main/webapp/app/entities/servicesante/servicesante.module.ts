import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ServicesanteComponent } from './list/servicesante.component';
import { ServicesanteDetailComponent } from './detail/servicesante-detail.component';
import { ServicesanteUpdateComponent } from './update/servicesante-update.component';
import { ServicesanteDeleteDialogComponent } from './delete/servicesante-delete-dialog.component';
import { ServicesanteRoutingModule } from './route/servicesante-routing.module';

@NgModule({
  imports: [SharedModule, ServicesanteRoutingModule],
  declarations: [ServicesanteComponent, ServicesanteDetailComponent, ServicesanteUpdateComponent, ServicesanteDeleteDialogComponent],
})
export class ServicesanteModule {}
