import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../structurefiche.test-samples';

import { StructureficheFormService } from './structurefiche-form.service';

describe('Structurefiche Form Service', () => {
  let service: StructureficheFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StructureficheFormService);
  });

  describe('Service methods', () => {
    describe('createStructureficheFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createStructureficheFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            typestructure: expect.any(Object),
            ordre: expect.any(Object),
            etablissement: expect.any(Object),
            servicesante: expect.any(Object),
            medecin: expect.any(Object),
          })
        );
      });

      it('passing IStructurefiche should create a new form with FormGroup', () => {
        const formGroup = service.createStructureficheFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            typestructure: expect.any(Object),
            ordre: expect.any(Object),
            etablissement: expect.any(Object),
            servicesante: expect.any(Object),
            medecin: expect.any(Object),
          })
        );
      });
    });

    describe('getStructurefiche', () => {
      it('should return NewStructurefiche for default Structurefiche initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createStructureficheFormGroup(sampleWithNewData);

        const structurefiche = service.getStructurefiche(formGroup) as any;

        expect(structurefiche).toMatchObject(sampleWithNewData);
      });

      it('should return NewStructurefiche for empty Structurefiche initial value', () => {
        const formGroup = service.createStructureficheFormGroup();

        const structurefiche = service.getStructurefiche(formGroup) as any;

        expect(structurefiche).toMatchObject({});
      });

      it('should return IStructurefiche', () => {
        const formGroup = service.createStructureficheFormGroup(sampleWithRequiredData);

        const structurefiche = service.getStructurefiche(formGroup) as any;

        expect(structurefiche).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IStructurefiche should not enable id FormControl', () => {
        const formGroup = service.createStructureficheFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewStructurefiche should disable id FormControl', () => {
        const formGroup = service.createStructureficheFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
