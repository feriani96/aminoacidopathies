import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { StructureficheFormService, StructureficheFormGroup } from './structurefiche-form.service';
import { IStructurefiche } from '../structurefiche.model';
import { StructureficheService } from '../service/structurefiche.service';
import { IEtablissement } from 'app/entities/etablissement/etablissement.model';
import { EtablissementService } from 'app/entities/etablissement/service/etablissement.service';
import { IServicesante } from 'app/entities/servicesante/servicesante.model';
import { ServicesanteService } from 'app/entities/servicesante/service/servicesante.service';
import { IMedecin } from 'app/entities/medecin/medecin.model';
import { MedecinService } from 'app/entities/medecin/service/medecin.service';
import { etypestructure } from 'app/entities/enumerations/etypestructure.model';

@Component({
  selector: 'jhi-structurefiche-update',
  templateUrl: './structurefiche-update.component.html',
})
export class StructureficheUpdateComponent implements OnInit {
  isSaving = false;
  structurefiche: IStructurefiche | null = null;
  etypestructureValues = Object.keys(etypestructure);

  etablissementsSharedCollection: IEtablissement[] = [];
  servicesantesSharedCollection: IServicesante[] = [];
  medecinsSharedCollection: IMedecin[] = [];

  editForm: StructureficheFormGroup = this.structureficheFormService.createStructureficheFormGroup();

  constructor(
    protected structureficheService: StructureficheService,
    protected structureficheFormService: StructureficheFormService,
    protected etablissementService: EtablissementService,
    protected servicesanteService: ServicesanteService,
    protected medecinService: MedecinService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEtablissement = (o1: IEtablissement | null, o2: IEtablissement | null): boolean =>
    this.etablissementService.compareEtablissement(o1, o2);

  compareServicesante = (o1: IServicesante | null, o2: IServicesante | null): boolean =>
    this.servicesanteService.compareServicesante(o1, o2);

  compareMedecin = (o1: IMedecin | null, o2: IMedecin | null): boolean => this.medecinService.compareMedecin(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ structurefiche }) => {
      this.structurefiche = structurefiche;
      if (structurefiche) {
        this.updateForm(structurefiche);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const structurefiche = this.structureficheFormService.getStructurefiche(this.editForm);
    if (structurefiche.id !== null) {
      this.subscribeToSaveResponse(this.structureficheService.update(structurefiche));
    } else {
      this.subscribeToSaveResponse(this.structureficheService.create(structurefiche));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStructurefiche>>): void {
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

  protected updateForm(structurefiche: IStructurefiche): void {
    this.structurefiche = structurefiche;
    this.structureficheFormService.resetForm(this.editForm, structurefiche);

    this.etablissementsSharedCollection = this.etablissementService.addEtablissementToCollectionIfMissing<IEtablissement>(
      this.etablissementsSharedCollection,
      structurefiche.etablissement
    );
    this.servicesantesSharedCollection = this.servicesanteService.addServicesanteToCollectionIfMissing<IServicesante>(
      this.servicesantesSharedCollection,
      structurefiche.servicesante
    );
    this.medecinsSharedCollection = this.medecinService.addMedecinToCollectionIfMissing<IMedecin>(
      this.medecinsSharedCollection,
      structurefiche.medecin
    );
  }

  protected loadRelationshipsOptions(): void {
    this.etablissementService
      .query()
      .pipe(map((res: HttpResponse<IEtablissement[]>) => res.body ?? []))
      .pipe(
        map((etablissements: IEtablissement[]) =>
          this.etablissementService.addEtablissementToCollectionIfMissing<IEtablissement>(
            etablissements,
            this.structurefiche?.etablissement
          )
        )
      )
      .subscribe((etablissements: IEtablissement[]) => (this.etablissementsSharedCollection = etablissements));

    this.servicesanteService
      .query()
      .pipe(map((res: HttpResponse<IServicesante[]>) => res.body ?? []))
      .pipe(
        map((servicesantes: IServicesante[]) =>
          this.servicesanteService.addServicesanteToCollectionIfMissing<IServicesante>(servicesantes, this.structurefiche?.servicesante)
        )
      )
      .subscribe((servicesantes: IServicesante[]) => (this.servicesantesSharedCollection = servicesantes));

    this.medecinService
      .query()
      .pipe(map((res: HttpResponse<IMedecin[]>) => res.body ?? []))
      .pipe(
        map((medecins: IMedecin[]) => this.medecinService.addMedecinToCollectionIfMissing<IMedecin>(medecins, this.structurefiche?.medecin))
      )
      .subscribe((medecins: IMedecin[]) => (this.medecinsSharedCollection = medecins));
  }
}
