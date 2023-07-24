import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../structuresante.test-samples';

import { StructuresanteFormService } from './structuresante-form.service';

describe('Structuresante Form Service', () => {
  let service: StructuresanteFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StructuresanteFormService);
  });

  describe('Service methods', () => {
    describe('createStructuresanteFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createStructuresanteFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
          })
        );
      });

      it('passing IStructuresante should create a new form with FormGroup', () => {
        const formGroup = service.createStructuresanteFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
          })
        );
      });
    });

    describe('getStructuresante', () => {
      it('should return NewStructuresante for default Structuresante initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createStructuresanteFormGroup(sampleWithNewData);

        const structuresante = service.getStructuresante(formGroup) as any;

        expect(structuresante).toMatchObject(sampleWithNewData);
      });

      it('should return NewStructuresante for empty Structuresante initial value', () => {
        const formGroup = service.createStructuresanteFormGroup();

        const structuresante = service.getStructuresante(formGroup) as any;

        expect(structuresante).toMatchObject({});
      });

      it('should return IStructuresante', () => {
        const formGroup = service.createStructuresanteFormGroup(sampleWithRequiredData);

        const structuresante = service.getStructuresante(formGroup) as any;

        expect(structuresante).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IStructuresante should not enable id FormControl', () => {
        const formGroup = service.createStructuresanteFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewStructuresante should disable id FormControl', () => {
        const formGroup = service.createStructuresanteFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
