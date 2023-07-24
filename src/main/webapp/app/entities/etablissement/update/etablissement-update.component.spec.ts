import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { EtablissementFormService } from './etablissement-form.service';
import { EtablissementService } from '../service/etablissement.service';
import { IEtablissement } from '../etablissement.model';
import { IStructuresante } from 'app/entities/structuresante/structuresante.model';
import { StructuresanteService } from 'app/entities/structuresante/service/structuresante.service';

import { EtablissementUpdateComponent } from './etablissement-update.component';

describe('Etablissement Management Update Component', () => {
  let comp: EtablissementUpdateComponent;
  let fixture: ComponentFixture<EtablissementUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let etablissementFormService: EtablissementFormService;
  let etablissementService: EtablissementService;
  let structuresanteService: StructuresanteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [EtablissementUpdateComponent],
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
      .overrideTemplate(EtablissementUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EtablissementUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    etablissementFormService = TestBed.inject(EtablissementFormService);
    etablissementService = TestBed.inject(EtablissementService);
    structuresanteService = TestBed.inject(StructuresanteService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Structuresante query and add missing value', () => {
      const etablissement: IEtablissement = { id: 456 };
      const structuresante: IStructuresante = { id: 80046 };
      etablissement.structuresante = structuresante;

      const structuresanteCollection: IStructuresante[] = [{ id: 87118 }];
      jest.spyOn(structuresanteService, 'query').mockReturnValue(of(new HttpResponse({ body: structuresanteCollection })));
      const additionalStructuresantes = [structuresante];
      const expectedCollection: IStructuresante[] = [...additionalStructuresantes, ...structuresanteCollection];
      jest.spyOn(structuresanteService, 'addStructuresanteToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ etablissement });
      comp.ngOnInit();

      expect(structuresanteService.query).toHaveBeenCalled();
      expect(structuresanteService.addStructuresanteToCollectionIfMissing).toHaveBeenCalledWith(
        structuresanteCollection,
        ...additionalStructuresantes.map(expect.objectContaining)
      );
      expect(comp.structuresantesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const etablissement: IEtablissement = { id: 456 };
      const structuresante: IStructuresante = { id: 30570 };
      etablissement.structuresante = structuresante;

      activatedRoute.data = of({ etablissement });
      comp.ngOnInit();

      expect(comp.structuresantesSharedCollection).toContain(structuresante);
      expect(comp.etablissement).toEqual(etablissement);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEtablissement>>();
      const etablissement = { id: 123 };
      jest.spyOn(etablissementFormService, 'getEtablissement').mockReturnValue(etablissement);
      jest.spyOn(etablissementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ etablissement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: etablissement }));
      saveSubject.complete();

      // THEN
      expect(etablissementFormService.getEtablissement).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(etablissementService.update).toHaveBeenCalledWith(expect.objectContaining(etablissement));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEtablissement>>();
      const etablissement = { id: 123 };
      jest.spyOn(etablissementFormService, 'getEtablissement').mockReturnValue({ id: null });
      jest.spyOn(etablissementService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ etablissement: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: etablissement }));
      saveSubject.complete();

      // THEN
      expect(etablissementFormService.getEtablissement).toHaveBeenCalled();
      expect(etablissementService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEtablissement>>();
      const etablissement = { id: 123 };
      jest.spyOn(etablissementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ etablissement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(etablissementService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareStructuresante', () => {
      it('Should forward to structuresanteService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(structuresanteService, 'compareStructuresante');
        comp.compareStructuresante(entity, entity2);
        expect(structuresanteService.compareStructuresante).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
