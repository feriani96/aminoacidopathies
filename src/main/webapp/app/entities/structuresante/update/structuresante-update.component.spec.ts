import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { StructuresanteFormService } from './structuresante-form.service';
import { StructuresanteService } from '../service/structuresante.service';
import { IStructuresante } from '../structuresante.model';

import { StructuresanteUpdateComponent } from './structuresante-update.component';

describe('Structuresante Management Update Component', () => {
  let comp: StructuresanteUpdateComponent;
  let fixture: ComponentFixture<StructuresanteUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let structuresanteFormService: StructuresanteFormService;
  let structuresanteService: StructuresanteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [StructuresanteUpdateComponent],
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
      .overrideTemplate(StructuresanteUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StructuresanteUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    structuresanteFormService = TestBed.inject(StructuresanteFormService);
    structuresanteService = TestBed.inject(StructuresanteService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const structuresante: IStructuresante = { id: 456 };

      activatedRoute.data = of({ structuresante });
      comp.ngOnInit();

      expect(comp.structuresante).toEqual(structuresante);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStructuresante>>();
      const structuresante = { id: 123 };
      jest.spyOn(structuresanteFormService, 'getStructuresante').mockReturnValue(structuresante);
      jest.spyOn(structuresanteService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ structuresante });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: structuresante }));
      saveSubject.complete();

      // THEN
      expect(structuresanteFormService.getStructuresante).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(structuresanteService.update).toHaveBeenCalledWith(expect.objectContaining(structuresante));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStructuresante>>();
      const structuresante = { id: 123 };
      jest.spyOn(structuresanteFormService, 'getStructuresante').mockReturnValue({ id: null });
      jest.spyOn(structuresanteService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ structuresante: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: structuresante }));
      saveSubject.complete();

      // THEN
      expect(structuresanteFormService.getStructuresante).toHaveBeenCalled();
      expect(structuresanteService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStructuresante>>();
      const structuresante = { id: 123 };
      jest.spyOn(structuresanteService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ structuresante });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(structuresanteService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
