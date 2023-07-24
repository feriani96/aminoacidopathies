import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IMetabolique } from '../metabolique.model';
import { MetaboliqueService } from '../service/metabolique.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './metabolique-delete-dialog.component.html',
})
export class MetaboliqueDeleteDialogComponent {
  metabolique?: IMetabolique;

  constructor(protected metaboliqueService: MetaboliqueService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.metaboliqueService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
