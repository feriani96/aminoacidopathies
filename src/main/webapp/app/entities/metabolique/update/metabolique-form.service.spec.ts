import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../metabolique.test-samples';

import { MetaboliqueFormService } from './metabolique-form.service';

describe('Metabolique Form Service', () => {
  let service: MetaboliqueFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MetaboliqueFormService);
  });

  describe('Service methods', () => {
    describe('createMetaboliqueFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMetaboliqueFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            fait: expect.any(Object),
            laboratoire: expect.any(Object),
            resultat: expect.any(Object),
          })
        );
      });

      it('passing IMetabolique should create a new form with FormGroup', () => {
        const formGroup = service.createMetaboliqueFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            fait: expect.any(Object),
            laboratoire: expect.any(Object),
            resultat: expect.any(Object),
          })
        );
      });
    });

    describe('getMetabolique', () => {
      it('should return NewMetabolique for default Metabolique initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createMetaboliqueFormGroup(sampleWithNewData);

        const metabolique = service.getMetabolique(formGroup) as any;

        expect(metabolique).toMatchObject(sampleWithNewData);
      });

      it('should return NewMetabolique for empty Metabolique initial value', () => {
        const formGroup = service.createMetaboliqueFormGroup();

        const metabolique = service.getMetabolique(formGroup) as any;

        expect(metabolique).toMatchObject({});
      });

      it('should return IMetabolique', () => {
        const formGroup = service.createMetaboliqueFormGroup(sampleWithRequiredData);

        const metabolique = service.getMetabolique(formGroup) as any;

        expect(metabolique).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMetabolique should not enable id FormControl', () => {
        const formGroup = service.createMetaboliqueFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMetabolique should disable id FormControl', () => {
        const formGroup = service.createMetaboliqueFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
