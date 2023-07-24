import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { EtablissementFormService, EtablissementFormGroup } from './etablissement-form.service';
import { IEtablissement } from '../etablissement.model';
import { EtablissementService } from '../service/etablissement.service';
import { IStructuresante } from 'app/entities/structuresante/structuresante.model';
import { StructuresanteService } from 'app/entities/structuresante/service/structuresante.service';

@Component({
  selector: 'jhi-etablissement-update',
  templateUrl: './etablissement-update.component.html',
})
export class EtablissementUpdateComponent implements OnInit {
  isSaving = false;
  etablissement: IEtablissement | null = null;

  structuresantesSharedCollection: IStructuresante[] = [];

  editForm: EtablissementFormGroup = this.etablissementFormService.createEtablissementFormGroup();

  constructor(
    protected etablissementService: EtablissementService,
    protected etablissementFormService: EtablissementFormService,
    protected structuresanteService: StructuresanteService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareStructuresante = (o1: IStructuresante | null, o2: IStructuresante | null): boolean =>
    this.structuresanteService.compareStructuresante(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etablissement }) => {
      this.etablissement = etablissement;
      if (etablissement) {
        this.updateForm(etablissement);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const etablissement = this.etablissementFormService.getEtablissement(this.editForm);
    if (etablissement.id !== null) {
      this.subscribeToSaveResponse(this.etablissementService.update(etablissement));
    } else {
      this.subscribeToSaveResponse(this.etablissementService.create(etablissement));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtablissement>>): void {
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

  protected updateForm(etablissement: IEtablissement): void {
    this.etablissement = etablissement;
    this.etablissementFormService.resetForm(this.editForm, etablissement);

    this.structuresantesSharedCollection = this.structuresanteService.addStructuresanteToCollectionIfMissing<IStructuresante>(
      this.structuresantesSharedCollection,
      etablissement.structuresante
    );
  }

  protected loadRelationshipsOptions(): void {
    this.structuresanteService
      .query()
      .pipe(map((res: HttpResponse<IStructuresante[]>) => res.body ?? []))
      .pipe(
        map((structuresantes: IStructuresante[]) =>
          this.structuresanteService.addStructuresanteToCollectionIfMissing<IStructuresante>(
            structuresantes,
            this.etablissement?.structuresante
          )
        )
      )
      .subscribe((structuresantes: IStructuresante[]) => (this.structuresantesSharedCollection = structuresantes));
  }
}
