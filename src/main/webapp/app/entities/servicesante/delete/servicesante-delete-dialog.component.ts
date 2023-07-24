import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IServicesante } from '../servicesante.model';
import { ServicesanteService } from '../service/servicesante.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './servicesante-delete-dialog.component.html',
})
export class ServicesanteDeleteDialogComponent {
  servicesante?: IServicesante;

  constructor(protected servicesanteService: ServicesanteService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.servicesanteService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
