import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../casconfirme.test-samples';

import { CasconfirmeFormService } from './casconfirme-form.service';

describe('Casconfirme Form Service', () => {
  let service: CasconfirmeFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CasconfirmeFormService);
  });

  describe('Service methods', () => {
    describe('createCasconfirmeFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCasconfirmeFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code_registre: expect.any(Object),
            lien_parente: expect.any(Object),
            fiche: expect.any(Object),
          })
        );
      });

      it('passing ICasconfirme should create a new form with FormGroup', () => {
        const formGroup = service.createCasconfirmeFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code_registre: expect.any(Object),
            lien_parente: expect.any(Object),
            fiche: expect.any(Object),
          })
        );
      });
    });

    describe('getCasconfirme', () => {
      it('should return NewCasconfirme for default Casconfirme initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCasconfirmeFormGroup(sampleWithNewData);

        const casconfirme = service.getCasconfirme(formGroup) as any;

        expect(casconfirme).toMatchObject(sampleWithNewData);
      });

      it('should return NewCasconfirme for empty Casconfirme initial value', () => {
        const formGroup = service.createCasconfirmeFormGroup();

        const casconfirme = service.getCasconfirme(formGroup) as any;

        expect(casconfirme).toMatchObject({});
      });

      it('should return ICasconfirme', () => {
        const formGroup = service.createCasconfirmeFormGroup(sampleWithRequiredData);

        const casconfirme = service.getCasconfirme(formGroup) as any;

        expect(casconfirme).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICasconfirme should not enable id FormControl', () => {
        const formGroup = service.createCasconfirmeFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCasconfirme should disable id FormControl', () => {
        const formGroup = service.createCasconfirmeFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
