import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICasconfirme } from '../casconfirme.model';
import { CasconfirmeService } from '../service/casconfirme.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './casconfirme-delete-dialog.component.html',
})
export class CasconfirmeDeleteDialogComponent {
  casconfirme?: ICasconfirme;

  constructor(protected casconfirmeService: CasconfirmeService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.casconfirmeService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
