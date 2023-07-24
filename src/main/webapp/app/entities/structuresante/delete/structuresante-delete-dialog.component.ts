import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IStructuresante } from '../structuresante.model';
import { StructuresanteService } from '../service/structuresante.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './structuresante-delete-dialog.component.html',
})
export class StructuresanteDeleteDialogComponent {
  structuresante?: IStructuresante;

  constructor(protected structuresanteService: StructuresanteService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.structuresanteService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
