import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { StructuresanteFormService, StructuresanteFormGroup } from './structuresante-form.service';
import { IStructuresante } from '../structuresante.model';
import { StructuresanteService } from '../service/structuresante.service';

@Component({
  selector: 'jhi-structuresante-update',
  templateUrl: './structuresante-update.component.html',
})
export class StructuresanteUpdateComponent implements OnInit {
  isSaving = false;
  structuresante: IStructuresante | null = null;

  editForm: StructuresanteFormGroup = this.structuresanteFormService.createStructuresanteFormGroup();

  constructor(
    protected structuresanteService: StructuresanteService,
    protected structuresanteFormService: StructuresanteFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ structuresante }) => {
      this.structuresante = structuresante;
      if (structuresante) {
        this.updateForm(structuresante);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const structuresante = this.structuresanteFormService.getStructuresante(this.editForm);
    if (structuresante.id !== null) {
      this.subscribeToSaveResponse(this.structuresanteService.update(structuresante));
    } else {
      this.subscribeToSaveResponse(this.structuresanteService.create(structuresante));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStructuresante>>): void {
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

  protected updateForm(structuresante: IStructuresante): void {
    this.structuresante = structuresante;
    this.structuresanteFormService.resetForm(this.editForm, structuresante);
  }
}
