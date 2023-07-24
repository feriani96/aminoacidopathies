import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../servicesante.test-samples';

import { ServicesanteFormService } from './servicesante-form.service';

describe('Servicesante Form Service', () => {
  let service: ServicesanteFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicesanteFormService);
  });

  describe('Service methods', () => {
    describe('createServicesanteFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createServicesanteFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nom: expect.any(Object),
          })
        );
      });

      it('passing IServicesante should create a new form with FormGroup', () => {
        const formGroup = service.createServicesanteFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nom: expect.any(Object),
          })
        );
      });
    });

    describe('getServicesante', () => {
      it('should return NewServicesante for default Servicesante initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createServicesanteFormGroup(sampleWithNewData);

        const servicesante = service.getServicesante(formGroup) as any;

        expect(servicesante).toMatchObject(sampleWithNewData);
      });

      it('should return NewServicesante for empty Servicesante initial value', () => {
        const formGroup = service.createServicesanteFormGroup();

        const servicesante = service.getServicesante(formGroup) as any;

        expect(servicesante).toMatchObject({});
      });

      it('should return IServicesante', () => {
        const formGroup = service.createServicesanteFormGroup(sampleWithRequiredData);

        const servicesante = service.getServicesante(formGroup) as any;

        expect(servicesante).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IServicesante should not enable id FormControl', () => {
        const formGroup = service.createServicesanteFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewServicesante should disable id FormControl', () => {
        const formGroup = service.createServicesanteFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
