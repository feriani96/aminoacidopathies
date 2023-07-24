import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PathologieFormService } from './pathologie-form.service';
import { PathologieService } from '../service/pathologie.service';
import { IPathologie } from '../pathologie.model';

import { PathologieUpdateComponent } from './pathologie-update.component';

describe('Pathologie Management Update Component', () => {
  let comp: PathologieUpdateComponent;
  let fixture: ComponentFixture<PathologieUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let pathologieFormService: PathologieFormService;
  let pathologieService: PathologieService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PathologieUpdateComponent],
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
      .overrideTemplate(PathologieUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PathologieUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    pathologieFormService = TestBed.inject(PathologieFormService);
    pathologieService = TestBed.inject(PathologieService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const pathologie: IPathologie = { id: 456 };

      activatedRoute.data = of({ pathologie });
      comp.ngOnInit();

      expect(comp.pathologie).toEqual(pathologie);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPathologie>>();
      const pathologie = { id: 123 };
      jest.spyOn(pathologieFormService, 'getPathologie').mockReturnValue(pathologie);
      jest.spyOn(pathologieService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ pathologie });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: pathologie }));
      saveSubject.complete();

      // THEN
      expect(pathologieFormService.getPathologie).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(pathologieService.update).toHaveBeenCalledWith(expect.objectContaining(pathologie));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPathologie>>();
      const pathologie = { id: 123 };
      jest.spyOn(pathologieFormService, 'getPathologie').mockReturnValue({ id: null });
      jest.spyOn(pathologieService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ pathologie: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: pathologie }));
      saveSubject.complete();

      // THEN
      expect(pathologieFormService.getPathologie).toHaveBeenCalled();
      expect(pathologieService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPathologie>>();
      const pathologie = { id: 123 };
      jest.spyOn(pathologieService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ pathologie });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(pathologieService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
