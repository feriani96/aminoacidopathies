import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IStructurefiche } from '../structurefiche.model';
import { StructureficheService } from '../service/structurefiche.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './structurefiche-delete-dialog.component.html',
})
export class StructureficheDeleteDialogComponent {
  structurefiche?: IStructurefiche;

  constructor(protected structureficheService: StructureficheService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.structureficheService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
