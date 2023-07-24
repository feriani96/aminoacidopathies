import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { StructureficheComponent } from './list/structurefiche.component';
import { StructureficheDetailComponent } from './detail/structurefiche-detail.component';
import { StructureficheUpdateComponent } from './update/structurefiche-update.component';
import { StructureficheDeleteDialogComponent } from './delete/structurefiche-delete-dialog.component';
import { StructureficheRoutingModule } from './route/structurefiche-routing.module';

@NgModule({
  imports: [SharedModule, StructureficheRoutingModule],
  declarations: [
    StructureficheComponent,
    StructureficheDetailComponent,
    StructureficheUpdateComponent,
    StructureficheDeleteDialogComponent,
  ],
})
export class StructureficheModule {}
