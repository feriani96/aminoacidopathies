import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { CasdecesbasageFormService, CasdecesbasageFormGroup } from './casdecesbasage-form.service';
import { ICasdecesbasage } from '../casdecesbasage.model';
import { CasdecesbasageService } from '../service/casdecesbasage.service';
import { elienparente } from 'app/entities/enumerations/elienparente.model';
import { elieudeces } from 'app/entities/enumerations/elieudeces.model';

@Component({
  selector: 'jhi-casdecesbasage-update',
  templateUrl: './casdecesbasage-update.component.html',
})
export class CasdecesbasageUpdateComponent implements OnInit {
  isSaving = false;
  casdecesbasage: ICasdecesbasage | null = null;
  elienparenteValues = Object.keys(elienparente);
  elieudecesValues = Object.keys(elieudeces);

  editForm: CasdecesbasageFormGroup = this.casdecesbasageFormService.createCasdecesbasageFormGroup();

  constructor(
    protected casdecesbasageService: CasdecesbasageService,
    protected casdecesbasageFormService: CasdecesbasageFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ casdecesbasage }) => {
      this.casdecesbasage = casdecesbasage;
      if (casdecesbasage) {
        this.updateForm(casdecesbasage);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const casdecesbasage = this.casdecesbasageFormService.getCasdecesbasage(this.editForm);
    if (casdecesbasage.id !== null) {
      this.subscribeToSaveResponse(this.casdecesbasageService.update(casdecesbasage));
    } else {
      this.subscribeToSaveResponse(this.casdecesbasageService.create(casdecesbasage));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICasdecesbasage>>): void {
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

  protected updateForm(casdecesbasage: ICasdecesbasage): void {
    this.casdecesbasage = casdecesbasage;
    this.casdecesbasageFormService.resetForm(this.editForm, casdecesbasage);
  }
}
