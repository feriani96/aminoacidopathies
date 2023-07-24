import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { MedecinFormService, MedecinFormGroup } from './medecin-form.service';
import { IMedecin } from '../medecin.model';
import { MedecinService } from '../service/medecin.service';
import { IEtablissement } from 'app/entities/etablissement/etablissement.model';
import { EtablissementService } from 'app/entities/etablissement/service/etablissement.service';
import { IServicesante } from 'app/entities/servicesante/servicesante.model';
import { ServicesanteService } from 'app/entities/servicesante/service/servicesante.service';

@Component({
  selector: 'jhi-medecin-update',
  templateUrl: './medecin-update.component.html',
})
export class MedecinUpdateComponent implements OnInit {
  isSaving = false;
  medecin: IMedecin | null = null;

  etablissementsSharedCollection: IEtablissement[] = [];
  servicesantesSharedCollection: IServicesante[] = [];

  editForm: MedecinFormGroup = this.medecinFormService.createMedecinFormGroup();

  constructor(
    protected medecinService: MedecinService,
    protected medecinFormService: MedecinFormService,
    protected etablissementService: EtablissementService,
    protected servicesanteService: ServicesanteService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEtablissement = (o1: IEtablissement | null, o2: IEtablissement | null): boolean =>
    this.etablissementService.compareEtablissement(o1, o2);

  compareServicesante = (o1: IServicesante | null, o2: IServicesante | null): boolean =>
    this.servicesanteService.compareServicesante(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecin }) => {
      this.medecin = medecin;
      if (medecin) {
        this.updateForm(medecin);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medecin = this.medecinFormService.getMedecin(this.editForm);
    if (medecin.id !== null) {
      this.subscribeToSaveResponse(this.medecinService.update(medecin));
    } else {
      this.subscribeToSaveResponse(this.medecinService.create(medecin));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedecin>>): void {
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

  protected updateForm(medecin: IMedecin): void {
    this.medecin = medecin;
    this.medecinFormService.resetForm(this.editForm, medecin);

    this.etablissementsSharedCollection = this.etablissementService.addEtablissementToCollectionIfMissing<IEtablissement>(
      this.etablissementsSharedCollection,
      medecin.etablissement
    );
    this.servicesantesSharedCollection = this.servicesanteService.addServicesanteToCollectionIfMissing<IServicesante>(
      this.servicesantesSharedCollection,
      medecin.servicesante
    );
  }

  protected loadRelationshipsOptions(): void {
    this.etablissementService
      .query()
      .pipe(map((res: HttpResponse<IEtablissement[]>) => res.body ?? []))
      .pipe(
        map((etablissements: IEtablissement[]) =>
          this.etablissementService.addEtablissementToCollectionIfMissing<IEtablissement>(etablissements, this.medecin?.etablissement)
        )
      )
      .subscribe((etablissements: IEtablissement[]) => (this.etablissementsSharedCollection = etablissements));

    this.servicesanteService
      .query()
      .pipe(map((res: HttpResponse<IServicesante[]>) => res.body ?? []))
      .pipe(
        map((servicesantes: IServicesante[]) =>
          this.servicesanteService.addServicesanteToCollectionIfMissing<IServicesante>(servicesantes, this.medecin?.servicesante)
        )
      )
      .subscribe((servicesantes: IServicesante[]) => (this.servicesantesSharedCollection = servicesantes));
  }
}
