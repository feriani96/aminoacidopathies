import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICassuspecte } from '../cassuspecte.model';
import { CassuspecteService } from '../service/cassuspecte.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './cassuspecte-delete-dialog.component.html',
})
export class CassuspecteDeleteDialogComponent {
  cassuspecte?: ICassuspecte;

  constructor(protected cassuspecteService: CassuspecteService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cassuspecteService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
