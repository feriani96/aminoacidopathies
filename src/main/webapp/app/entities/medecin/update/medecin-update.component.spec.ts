import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MedecinFormService } from './medecin-form.service';
import { MedecinService } from '../service/medecin.service';
import { IMedecin } from '../medecin.model';
import { IEtablissement } from 'app/entities/etablissement/etablissement.model';
import { EtablissementService } from 'app/entities/etablissement/service/etablissement.service';
import { IServicesante } from 'app/entities/servicesante/servicesante.model';
import { ServicesanteService } from 'app/entities/servicesante/service/servicesante.service';

import { MedecinUpdateComponent } from './medecin-update.component';

describe('Medecin Management Update Component', () => {
  let comp: MedecinUpdateComponent;
  let fixture: ComponentFixture<MedecinUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let medecinFormService: MedecinFormService;
  let medecinService: MedecinService;
  let etablissementService: EtablissementService;
  let servicesanteService: ServicesanteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MedecinUpdateComponent],
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
      .overrideTemplate(MedecinUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MedecinUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    medecinFormService = TestBed.inject(MedecinFormService);
    medecinService = TestBed.inject(MedecinService);
    etablissementService = TestBed.inject(EtablissementService);
    servicesanteService = TestBed.inject(ServicesanteService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Etablissement query and add missing value', () => {
      const medecin: IMedecin = { id: 456 };
      const etablissement: IEtablissement = { id: 34871 };
      medecin.etablissement = etablissement;

      const etablissementCollection: IEtablissement[] = [{ id: 46291 }];
      jest.spyOn(etablissementService, 'query').mockReturnValue(of(new HttpResponse({ body: etablissementCollection })));
      const additionalEtablissements = [etablissement];
      const expectedCollection: IEtablissement[] = [...additionalEtablissements, ...etablissementCollection];
      jest.spyOn(etablissementService, 'addEtablissementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      expect(etablissementService.query).toHaveBeenCalled();
      expect(etablissementService.addEtablissementToCollectionIfMissing).toHaveBeenCalledWith(
        etablissementCollection,
        ...additionalEtablissements.map(expect.objectContaining)
      );
      expect(comp.etablissementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Servicesante query and add missing value', () => {
      const medecin: IMedecin = { id: 456 };
      const servicesante: IServicesante = { id: 80743 };
      medecin.servicesante = servicesante;

      const servicesanteCollection: IServicesante[] = [{ id: 90782 }];
      jest.spyOn(servicesanteService, 'query').mockReturnValue(of(new HttpResponse({ body: servicesanteCollection })));
      const additionalServicesantes = [servicesante];
      const expectedCollection: IServicesante[] = [...additionalServicesantes, ...servicesanteCollection];
      jest.spyOn(servicesanteService, 'addServicesanteToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      expect(servicesanteService.query).toHaveBeenCalled();
      expect(servicesanteService.addServicesanteToCollectionIfMissing).toHaveBeenCalledWith(
        servicesanteCollection,
        ...additionalServicesantes.map(expect.objectContaining)
      );
      expect(comp.servicesantesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const medecin: IMedecin = { id: 456 };
      const etablissement: IEtablissement = { id: 84468 };
      medecin.etablissement = etablissement;
      const servicesante: IServicesante = { id: 14616 };
      medecin.servicesante = servicesante;

      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      expect(comp.etablissementsSharedCollection).toContain(etablissement);
      expect(comp.servicesantesSharedCollection).toContain(servicesante);
      expect(comp.medecin).toEqual(medecin);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMedecin>>();
      const medecin = { id: 123 };
      jest.spyOn(medecinFormService, 'getMedecin').mockReturnValue(medecin);
      jest.spyOn(medecinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: medecin }));
      saveSubject.complete();

      // THEN
      expect(medecinFormService.getMedecin).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(medecinService.update).toHaveBeenCalledWith(expect.objectContaining(medecin));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMedecin>>();
      const medecin = { id: 123 };
      jest.spyOn(medecinFormService, 'getMedecin').mockReturnValue({ id: null });
      jest.spyOn(medecinService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ medecin: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: medecin }));
      saveSubject.complete();

      // THEN
      expect(medecinFormService.getMedecin).toHaveBeenCalled();
      expect(medecinService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMedecin>>();
      const medecin = { id: 123 };
      jest.spyOn(medecinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(medecinService.update).toHaveBeenCalled();
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
  });
});
