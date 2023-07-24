import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ServicesanteFormService, ServicesanteFormGroup } from './servicesante-form.service';
import { IServicesante } from '../servicesante.model';
import { ServicesanteService } from '../service/servicesante.service';

@Component({
  selector: 'jhi-servicesante-update',
  templateUrl: './servicesante-update.component.html',
})
export class ServicesanteUpdateComponent implements OnInit {
  isSaving = false;
  servicesante: IServicesante | null = null;

  editForm: ServicesanteFormGroup = this.servicesanteFormService.createServicesanteFormGroup();

  constructor(
    protected servicesanteService: ServicesanteService,
    protected servicesanteFormService: ServicesanteFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ servicesante }) => {
      this.servicesante = servicesante;
      if (servicesante) {
        this.updateForm(servicesante);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const servicesante = this.servicesanteFormService.getServicesante(this.editForm);
    if (servicesante.id !== null) {
      this.subscribeToSaveResponse(this.servicesanteService.update(servicesante));
    } else {
      this.subscribeToSaveResponse(this.servicesanteService.create(servicesante));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServicesante>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(servicesante: IServicesante): void {
    this.servicesante = servicesante;
    this.servicesanteFormService.resetForm(this.editForm, servicesante);
  }
}
