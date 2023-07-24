import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { StructureficheFormService } from './structurefiche-form.service';
import { StructureficheService } from '../service/structurefiche.service';
import { IStructurefiche } from '../structurefiche.model';
import { IEtablissement } from 'app/entities/etablissement/etablissement.model';
import { EtablissementService } from 'app/entities/etablissement/service/etablissement.service';
import { IServicesante } from 'app/entities/servicesante/servicesante.model';
import { ServicesanteService } from 'app/entities/servicesante/service/servicesante.service';
import { IMedecin } from 'app/entities/medecin/medecin.model';
import { MedecinService } from 'app/entities/medecin/service/medecin.service';

import { StructureficheUpdateComponent } from './structurefiche-update.component';

describe('Structurefiche Management Update Component', () => {
  let comp: StructureficheUpdateComponent;
  let fixture: ComponentFixture<StructureficheUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let structureficheFormService: StructureficheFormService;
  let structureficheService: StructureficheService;
  let etablissementService: EtablissementService;
  let servicesanteService: ServicesanteService;
  let medecinService: MedecinService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [StructureficheUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(StructureficheUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StructureficheUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    structureficheFormService = TestBed.inject(StructureficheFormService);
    structureficheService = TestBed.inject(StructureficheService);
    etablissementService = TestBed.inject(EtablissementService);
    servicesanteService = TestBed.inject(ServicesanteService);
    medecinService = TestBed.inject(MedecinService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Etablissement query and add missing value', () => {
      const structurefiche: IStructurefiche = { id: 456 };
      const etablissement: IEtablissement = { id: 39068 };
      structurefiche.etablissement = etablissement;

      const etablissementCollection: IEtablissement[] = [{ id: 89530 }];
      jest.spyOn(etablissementService, 'query').mockReturnValue(of(new HttpResponse({ body: etablissementCollection })));
      const additionalEtablissements = [etablissement];
      const expectedCollection: IEtablissement[] = [...additionalEtablissements, ...etablissementCollection];
      jest.spyOn(etablissementService, 'addEtablissementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ structurefiche });
      comp.ngOnInit();

      expect(etablissementService.query).toHaveBeenCalled();
      expect(etablissementService.addEtablissementToCollectionIfMissing).toHaveBeenCalledWith(
        etablissementCollection,
        ...additionalEtablissements.map(expect.objectContaining)
      );
      expect(comp.etablissementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Servicesante query and add missing value', () => {
      const structurefiche: IStructurefiche = { id: 456 };
      const servicesante: IServicesante = { id: 90900 };
      structurefiche.servicesante = servicesante;

      const servicesanteCollection: IServicesante[] = [{ id: 97032 }];
      jest.spyOn(servicesanteService, 'query').mockReturnValue(of(new HttpResponse({ body: servicesanteCollection })));
      const additionalServicesantes = [servicesante];
      const expectedCollection: IServicesante[] = [...additionalServicesantes, ...servicesanteCollection];
      jest.spyOn(servicesanteService, 'addServicesanteToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ structurefiche });
      comp.ngOnInit();

      expect(servicesanteService.query).toHaveBeenCalled();
      expect(servicesanteService.addServicesanteToCollectionIfMissing).toHaveBeenCalledWith(
        servicesanteCollection,
        ...additionalServicesantes.map(expect.objectContaining)
      );
      expect(comp.servicesantesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Medecin query and add missing value', () => {
      const structurefiche: IStructurefiche = { id: 456 };
      const medecin: IMedecin = { id: 7381 };
      structurefiche.medecin = medecin;

      const medecinCollection: IMedecin[] = [{ id: 51948 }];
      jest.spyOn(medecinService, 'query').mockReturnValue(of(new HttpResponse({ body: medecinCollection })));
      const additionalMedecins = [medecin];
      const expectedCollection: IMedecin[] = [...additionalMedecins, ...medecinCollection];
      jest.spyOn(medecinService, 'addMedecinToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ structurefiche });
      comp.ngOnInit();

      expect(medecinService.query).toHaveBeenCalled();
      expect(medecinService.addMedecinToCollectionIfMissing).toHaveBeenCalledWith(
        medecinCollection,
        ...additionalMedecins.map(expect.objectContaining)
      );
      expect(comp.medecinsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const structurefiche: IStructurefiche = { id: 456 };
      const etablissement: IEtablissement = { id: 40565 };
      structurefiche.etablissement = etablissement;
      const servicesante: IServicesante = { id: 21747 };
      structurefiche.servicesante = servicesante;
      const medecin: IMedecin = { id: 38100 };
      structurefiche.medecin = medecin;

      activatedRoute.data = of({ structurefiche });
      comp.ngOnInit();

      expect(comp.etablissementsSharedCollection).toContain(etablissement);
      expect(comp.servicesantesSharedCollection).toContain(servicesante);
      expect(comp.medecinsSharedCollection).toContain(medecin);
      expect(comp.structurefiche).toEqual(structurefiche);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStructurefiche>>();
      const structurefiche = { id: 123 };
      jest.spyOn(structureficheFormService, 'getStructurefiche').mockReturnValue(structurefiche);
      jest.spyOn(structureficheService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ structurefiche });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: structurefiche }));
      saveSubject.complete();

      // THEN
      expect(structureficheFormService.getStructurefiche).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(structureficheService.update).toHaveBeenCalledWith(expect.objectContaining(structurefiche));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStructurefiche>>();
      const structurefiche = { id: 123 };
      jest.spyOn(structureficheFormService, 'getStructurefiche').mockReturnValue({ id: null });
      jest.spyOn(structureficheService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ structurefiche: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: structurefiche }));
      saveSubject.complete();

      // THEN
      expect(structureficheFormService.getStructurefiche).toHaveBeenCalled();
      expect(structureficheService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStructurefiche>>();
      const structurefiche = { id: 123 };
      jest.spyOn(structureficheService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ structurefiche });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(structureficheService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareEtablissement', () => {
      it('Should forward to etablissementService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(etablissementService, 'compareEtablissement');
        comp.compareEtablissement(entity, entity2);
        expect(etablissementService.compareEtablissement).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareServicesante', () => {
      it('Should forward to servicesanteService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(servicesanteService, 'compareServicesante');
        comp.compareServicesante(entity, entity2);
        expect(servicesanteService.compareServicesante).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareMedecin', () => {
      it('Should forward to medecinService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(medecinService, 'compareMedecin');
        comp.compareMedecin(entity, entity2);
        expect(medecinService.compareMedecin).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
