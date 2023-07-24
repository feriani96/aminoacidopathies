import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../casdecesbasage.test-samples';

import { CasdecesbasageFormService } from './casdecesbasage-form.service';

describe('Casdecesbasage Form Service', () => {
  let service: CasdecesbasageFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CasdecesbasageFormService);
  });

  describe('Service methods', () => {
    describe('createCasdecesbasageFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCasdecesbasageFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            confirme: expect.any(Object),
            code_registre: expect.any(Object),
            suspecte: expect.any(Object),
            lien_de_parente: expect.any(Object),
            autre_lien_parente: expect.any(Object),
            an_age_de_deces: expect.any(Object),
            mois_age_de_deces: expect.any(Object),
            jours_age_de_deces: expect.any(Object),
            tbl_neuro: expect.any(Object),
            tbl_hemorragique: expect.any(Object),
            tbl_infx: expect.any(Object),
            autre_circonstances_deces: expect.any(Object),
            bautre_circonstance_deces: expect.any(Object),
            np_circonstances_deces: expect.any(Object),
            lieu_deces: expect.any(Object),
          })
        );
      });

      it('passing ICasdecesbasage should create a new form with FormGroup', () => {
        const formGroup = service.createCasdecesbasageFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            confirme: expect.any(Object),
            code_registre: expect.any(Object),
            suspecte: expect.any(Object),
            lien_de_parente: expect.any(Object),
            autre_lien_parente: expect.any(Object),
            an_age_de_deces: expect.any(Object),
            mois_age_de_deces: expect.any(Object),
            jours_age_de_deces: expect.any(Object),
            tbl_neuro: expect.any(Object),
            tbl_hemorragique: expect.any(Object),
            tbl_infx: expect.any(Object),
            autre_circonstances_deces: expect.any(Object),
            bautre_circonstance_deces: expect.any(Object),
            np_circonstances_deces: expect.any(Object),
            lieu_deces: expect.any(Object),
          })
        );
      });
    });

    describe('getCasdecesbasage', () => {
      it('should return NewCasdecesbasage for default Casdecesbasage initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCasdecesbasageFormGroup(sampleWithNewData);

        const casdecesbasage = service.getCasdecesbasage(formGroup) as any;

        expect(casdecesbasage).toMatchObject(sampleWithNewData);
      });

      it('should return NewCasdecesbasage for empty Casdecesbasage initial value', () => {
        const formGroup = service.createCasdecesbasageFormGroup();

        const casdecesbasage = service.getCasdecesbasage(formGroup) as any;

        expect(casdecesbasage).toMatchObject({});
      });

      it('should return ICasdecesbasage', () => {
        const formGroup = service.createCasdecesbasageFormGroup(sampleWithRequiredData);

        const casdecesbasage = service.getCasdecesbasage(formGroup) as any;

        expect(casdecesbasage).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICasdecesbasage should not enable id FormControl', () => {
        const formGroup = service.createCasdecesbasageFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCasdecesbasage should disable id FormControl', () => {
        const formGroup = service.createCasdecesbasageFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
