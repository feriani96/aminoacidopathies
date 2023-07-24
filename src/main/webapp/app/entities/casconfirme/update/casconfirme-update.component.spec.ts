import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CasconfirmeFormService } from './casconfirme-form.service';
import { CasconfirmeService } from '../service/casconfirme.service';
import { ICasconfirme } from '../casconfirme.model';
import { IFiche } from 'app/entities/fiche/fiche.model';
import { FicheService } from 'app/entities/fiche/service/fiche.service';

import { CasconfirmeUpdateComponent } from './casconfirme-update.component';

describe('Casconfirme Management Update Component', () => {
  let comp: CasconfirmeUpdateComponent;
  let fixture: ComponentFixture<CasconfirmeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let casconfirmeFormService: CasconfirmeFormService;
  let casconfirmeService: CasconfirmeService;
  let ficheService: FicheService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CasconfirmeUpdateComponent],
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
      .overrideTemplate(CasconfirmeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CasconfirmeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    casconfirmeFormService = TestBed.inject(CasconfirmeFormService);
    casconfirmeService = TestBed.inject(CasconfirmeService);
    ficheService = TestBed.inject(FicheService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Fiche query and add missing value', () => {
      const casconfirme: ICasconfirme = { id: 456 };
      const fiche: IFiche = { id: 60457 };
      casconfirme.fiche = fiche;

      const ficheCollection: IFiche[] = [{ id: 28225 }];
      jest.spyOn(ficheService, 'query').mockReturnValue(of(new HttpResponse({ body: ficheCollection })));
      const additionalFiches = [fiche];
      const expectedCollection: IFiche[] = [...additionalFiches, ...ficheCollection];
      jest.spyOn(ficheService, 'addFicheToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ casconfirme });
      comp.ngOnInit();

      expect(ficheService.query).toHaveBeenCalled();
      expect(ficheService.addFicheToCollectionIfMissing).toHaveBeenCalledWith(
        ficheCollection,
        ...additionalFiches.map(expect.objectContaining)
      );
      expect(comp.fichesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const casconfirme: ICasconfirme = { id: 456 };
      const fiche: IFiche = { id: 79870 };
      casconfirme.fiche = fiche;

      activatedRoute.data = of({ casconfirme });
      comp.ngOnInit();

      expect(comp.fichesSharedCollection).toContain(fiche);
      expect(comp.casconfirme).toEqual(casconfirme);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICasconfirme>>();
      const casconfirme = { id: 123 };
      jest.spyOn(casconfirmeFormService, 'getCasconfirme').mockReturnValue(casconfirme);
      jest.spyOn(casconfirmeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ casconfirme });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: casconfirme }));
      saveSubject.complete();

      // THEN
      expect(casconfirmeFormService.getCasconfirme).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(casconfirmeService.update).toHaveBeenCalledWith(expect.objectContaining(casconfirme));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICasconfirme>>();
      const casconfirme = { id: 123 };
      jest.spyOn(casconfirmeFormService, 'getCasconfirme').mockReturnValue({ id: null });
      jest.spyOn(casconfirmeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ casconfirme: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: casconfirme }));
      saveSubject.complete();

      // THEN
      expect(casconfirmeFormService.getCasconfirme).toHaveBeenCalled();
      expect(casconfirmeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICasconfirme>>();
      const casconfirme = { id: 123 };
      jest.spyOn(casconfirmeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ casconfirme });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(casconfirmeService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareFiche', () => {
      it('Should forward to ficheService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(ficheService, 'compareFiche');
        comp.compareFiche(entity, entity2);
        expect(ficheService.compareFiche).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
