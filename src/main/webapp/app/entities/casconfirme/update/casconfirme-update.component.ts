import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CasconfirmeFormService, CasconfirmeFormGroup } from './casconfirme-form.service';
import { ICasconfirme } from '../casconfirme.model';
import { CasconfirmeService } from '../service/casconfirme.service';
import { IFiche } from 'app/entities/fiche/fiche.model';
import { FicheService } from 'app/entities/fiche/service/fiche.service';
import { elien_parente } from 'app/entities/enumerations/elien-parente.model';

@Component({
  selector: 'jhi-casconfirme-update',
  templateUrl: './casconfirme-update.component.html',
})
export class CasconfirmeUpdateComponent implements OnInit {
  isSaving = false;
  casconfirme: ICasconfirme | null = null;
  elien_parenteValues = Object.keys(elien_parente);

  fichesSharedCollection: IFiche[] = [];

  editForm: CasconfirmeFormGroup = this.casconfirmeFormService.createCasconfirmeFormGroup();

  constructor(
    protected casconfirmeService: CasconfirmeService,
    protected casconfirmeFormService: CasconfirmeFormService,
    protected ficheService: FicheService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareFiche = (o1: IFiche | null, o2: IFiche | null): boolean => this.ficheService.compareFiche(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ casconfirme }) => {
      this.casconfirme = casconfirme;
      if (casconfirme) {
        this.updateForm(casconfirme);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const casconfirme = this.casconfirmeFormService.getCasconfirme(this.editForm);
    if (casconfirme.id !== null) {
      this.subscribeToSaveResponse(this.casconfirmeService.update(casconfirme));
    } else {
      this.subscribeToSaveResponse(this.casconfirmeService.create(casconfirme));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICasconfirme>>): void {
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

  protected updateForm(casconfirme: ICasconfirme): void {
    this.casconfirme = casconfirme;
    this.casconfirmeFormService.resetForm(this.editForm, casconfirme);

    this.fichesSharedCollection = this.ficheService.addFicheToCollectionIfMissing<IFiche>(this.fichesSharedCollection, casconfirme.fiche);
  }

  protected loadRelationshipsOptions(): void {
    this.ficheService
      .query()
      .pipe(map((res: HttpResponse<IFiche[]>) => res.body ?? []))
      .pipe(map((fiches: IFiche[]) => this.ficheService.addFicheToCollectionIfMissing<IFiche>(fiches, this.casconfirme?.fiche)))
      .subscribe((fiches: IFiche[]) => (this.fichesSharedCollection = fiches));
  }
}
