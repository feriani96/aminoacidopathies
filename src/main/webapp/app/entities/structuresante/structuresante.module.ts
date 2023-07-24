import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { StructuresanteComponent } from './list/structuresante.component';
import { StructuresanteDetailComponent } from './detail/structuresante-detail.component';
import { StructuresanteUpdateComponent } from './update/structuresante-update.component';
import { StructuresanteDeleteDialogComponent } from './delete/structuresante-delete-dialog.component';
import { StructuresanteRoutingModule } from './route/structuresante-routing.module';

@NgModule({
  imports: [SharedModule, StructuresanteRoutingModule],
  declarations: [
    StructuresanteComponent,
    StructuresanteDetailComponent,
    StructuresanteUpdateComponent,
    StructuresanteDeleteDialogComponent,
  ],
})
export class StructuresanteModule {}
