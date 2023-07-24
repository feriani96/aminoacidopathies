import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { CassuspecteFormService, CassuspecteFormGroup } from './cassuspecte-form.service';
import { ICassuspecte } from '../cassuspecte.model';
import { CassuspecteService } from '../service/cassuspecte.service';
import { elienparente } from 'app/entities/enumerations/elienparente.model';

@Component({
  selector: 'jhi-cassuspecte-update',
  templateUrl: './cassuspecte-update.component.html',
})
export class CassuspecteUpdateComponent implements OnInit {
  isSaving = false;
  cassuspecte: ICassuspecte | null = null;
  elienparenteValues = Object.keys(elienparente);

  editForm: CassuspecteFormGroup = this.cassuspecteFormService.createCassuspecteFormGroup();

  constructor(
    protected cassuspecteService: CassuspecteService,
    protected cassuspecteFormService: CassuspecteFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cassuspecte }) => {
      this.cassuspecte = cassuspecte;
      if (cassuspecte) {
        this.updateForm(cassuspecte);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cassuspecte = this.cassuspecteFormService.getCassuspecte(this.editForm);
    if (cassuspecte.id !== null) {
      this.subscribeToSaveResponse(this.cassuspecteService.update(cassuspecte));
    } else {
      this.subscribeToSaveResponse(this.cassuspecteService.create(cassuspecte));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICassuspecte>>): void {
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

  protected updateForm(cassuspecte: ICassuspecte): void {
    this.cassuspecte = cassuspecte;
    this.cassuspecteFormService.resetForm(this.editForm, cassuspecte);
  }
}
