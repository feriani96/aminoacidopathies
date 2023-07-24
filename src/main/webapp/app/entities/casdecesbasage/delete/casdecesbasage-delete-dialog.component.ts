import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICasdecesbasage } from '../casdecesbasage.model';
import { CasdecesbasageService } from '../service/casdecesbasage.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './casdecesbasage-delete-dialog.component.html',
})
export class CasdecesbasageDeleteDialogComponent {
  casdecesbasage?: ICasdecesbasage;

  constructor(protected casdecesbasageService: CasdecesbasageService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.casdecesbasageService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
