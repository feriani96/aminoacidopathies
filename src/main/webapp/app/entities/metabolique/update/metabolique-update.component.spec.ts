import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MetaboliqueFormService } from './metabolique-form.service';
import { MetaboliqueService } from '../service/metabolique.service';
import { IMetabolique } from '../metabolique.model';

import { MetaboliqueUpdateComponent } from './metabolique-update.component';

describe('Metabolique Management Update Component', () => {
  let comp: MetaboliqueUpdateComponent;
  let fixture: ComponentFixture<MetaboliqueUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let metaboliqueFormService: MetaboliqueFormService;
  let metaboliqueService: MetaboliqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MetaboliqueUpdateComponent],
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
      .overrideTemplate(MetaboliqueUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MetaboliqueUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    metaboliqueFormService = TestBed.inject(MetaboliqueFormService);
    metaboliqueService = TestBed.inject(MetaboliqueService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const metabolique: IMetabolique = { id: 456 };

      activatedRoute.data = of({ metabolique });
      comp.ngOnInit();

      expect(comp.metabolique).toEqual(metabolique);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMetabolique>>();
      const metabolique = { id: 123 };
      jest.spyOn(metaboliqueFormService, 'getMetabolique').mockReturnValue(metabolique);
      jest.spyOn(metaboliqueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ metabolique });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: metabolique }));
      saveSubject.complete();

      // THEN
      expect(metaboliqueFormService.getMetabolique).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(metaboliqueService.update).toHaveBeenCalledWith(expect.objectContaining(metabolique));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMetabolique>>();
      const metabolique = { id: 123 };
      jest.spyOn(metaboliqueFormService, 'getMetabolique').mockReturnValue({ id: null });
      jest.spyOn(metaboliqueService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ metabolique: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: metabolique }));
      saveSubject.complete();

      // THEN
      expect(metaboliqueFormService.getMetabolique).toHaveBeenCalled();
      expect(metaboliqueService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMetabolique>>();
      const metabolique = { id: 123 };
      jest.spyOn(metaboliqueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ metabolique });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(metaboliqueService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
