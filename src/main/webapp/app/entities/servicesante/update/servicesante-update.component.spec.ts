import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ServicesanteFormService } from './servicesante-form.service';
import { ServicesanteService } from '../service/servicesante.service';
import { IServicesante } from '../servicesante.model';

import { ServicesanteUpdateComponent } from './servicesante-update.component';

describe('Servicesante Management Update Component', () => {
  let comp: ServicesanteUpdateComponent;
  let fixture: ComponentFixture<ServicesanteUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let servicesanteFormService: ServicesanteFormService;
  let servicesanteService: ServicesanteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ServicesanteUpdateComponent],
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
      .overrideTemplate(ServicesanteUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ServicesanteUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    servicesanteFormService = TestBed.inject(ServicesanteFormService);
    servicesanteService = TestBed.inject(ServicesanteService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const servicesante: IServicesante = { id: 456 };

      activatedRoute.data = of({ servicesante });
      comp.ngOnInit();

      expect(comp.servicesante).toEqual(servicesante);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicesante>>();
      const servicesante = { id: 123 };
      jest.spyOn(servicesanteFormService, 'getServicesante').mockReturnValue(servicesante);
      jest.spyOn(servicesanteService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicesante });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: servicesante }));
      saveSubject.complete();

      // THEN
      expect(servicesanteFormService.getServicesante).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(servicesanteService.update).toHaveBeenCalledWith(expect.objectContaining(servicesante));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicesante>>();
      const servicesante = { id: 123 };
      jest.spyOn(servicesanteFormService, 'getServicesante').mockReturnValue({ id: null });
      jest.spyOn(servicesanteService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicesante: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: servicesante }));
      saveSubject.complete();

      // THEN
      expect(servicesanteFormService.getServicesante).toHaveBeenCalled();
      expect(servicesanteService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicesante>>();
      const servicesante = { id: 123 };
      jest.spyOn(servicesanteService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicesante });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(servicesanteService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
