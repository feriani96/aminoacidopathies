import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MetaboliqueComponent } from './list/metabolique.component';
import { MetaboliqueDetailComponent } from './detail/metabolique-detail.component';
import { MetaboliqueUpdateComponent } from './update/metabolique-update.component';
import { MetaboliqueDeleteDialogComponent } from './delete/metabolique-delete-dialog.component';
import { MetaboliqueRoutingModule } from './route/metabolique-routing.module';

@NgModule({
  imports: [SharedModule, MetaboliqueRoutingModule],
  declarations: [MetaboliqueComponent, MetaboliqueDetailComponent, MetaboliqueUpdateComponent, MetaboliqueDeleteDialogComponent],
})
export class MetaboliqueModule {}
