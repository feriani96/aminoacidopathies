import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../cassuspecte.test-samples';

import { CassuspecteFormService } from './cassuspecte-form.service';

describe('Cassuspecte Form Service', () => {
  let service: CassuspecteFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CassuspecteFormService);
  });

  describe('Service methods', () => {
    describe('createCassuspecteFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCassuspecteFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            lienparente: expect.any(Object),
            lienparenteautre: expect.any(Object),
            signes_neurologiques: expect.any(Object),
            troubles_de_la_conscience: expect.any(Object),
            retard_psychomoteur: expect.any(Object),
            retard_mental: expect.any(Object),
            signes_du_spectre_autistique: expect.any(Object),
            epilepsie: expect.any(Object),
            crise_pseudoporphyrique: expect.any(Object),
            autres_signes_neurologiques: expect.any(Object),
            signes_hepatiques: expect.any(Object),
            ictere: expect.any(Object),
            ballonnement: expect.any(Object),
            syndrome_hemorragique: expect.any(Object),
            autres_signes_hepatiques: expect.any(Object),
            signes_osseux: expect.any(Object),
            signes_de_rachitisme: expect.any(Object),
            autre_signes_osseux: expect.any(Object),
            manifestations_thrombotiques: expect.any(Object),
            cerebrale: expect.any(Object),
            autre_manifestations_thrombotiques: expect.any(Object),
            manifestations_ophtalmologiques: expect.any(Object),
            luxation: expect.any(Object),
            ectopie_cristalinienne: expect.any(Object),
            cataracte: expect.any(Object),
            glaucome: expect.any(Object),
            myopie: expect.any(Object),
            manifestations_ophtalmologiques_autre: expect.any(Object),
            autre_criteres: expect.any(Object),
            str_autres_criteres: expect.any(Object),
            critere_non_precise: expect.any(Object),
          })
        );
      });

      it('passing ICassuspecte should create a new form with FormGroup', () => {
        const formGroup = service.createCassuspecteFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            lienparente: expect.any(Object),
            lienparenteautre: expect.any(Object),
            signes_neurologiques: expect.any(Object),
            troubles_de_la_conscience: expect.any(Object),
            retard_psychomoteur: expect.any(Object),
            retard_mental: expect.any(Object),
            signes_du_spectre_autistique: expect.any(Object),
            epilepsie: expect.any(Object),
            crise_pseudoporphyrique: expect.any(Object),
            autres_signes_neurologiques: expect.any(Object),
            signes_hepatiques: expect.any(Object),
            ictere: expect.any(Object),
            ballonnement: expect.any(Object),
            syndrome_hemorragique: expect.any(Object),
            autres_signes_hepatiques: expect.any(Object),
            signes_osseux: expect.any(Object),
            signes_de_rachitisme: expect.any(Object),
            autre_signes_osseux: expect.any(Object),
            manifestations_thrombotiques: expect.any(Object),
            cerebrale: expect.any(Object),
            autre_manifestations_thrombotiques: expect.any(Object),
            manifestations_ophtalmologiques: expect.any(Object),
            luxation: expect.any(Object),
            ectopie_cristalinienne: expect.any(Object),
            cataracte: expect.any(Object),
            glaucome: expect.any(Object),
            myopie: expect.any(Object),
            manifestations_ophtalmologiques_autre: expect.any(Object),
            autre_criteres: expect.any(Object),
            str_autres_criteres: expect.any(Object),
            critere_non_precise: expect.any(Object),
          })
        );
      });
    });

    describe('getCassuspecte', () => {
      it('should return NewCassuspecte for default Cassuspecte initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCassuspecteFormGroup(sampleWithNewData);

        const cassuspecte = service.getCassuspecte(formGroup) as any;

        expect(cassuspecte).toMatchObject(sampleWithNewData);
      });

      it('should return NewCassuspecte for empty Cassuspecte initial value', () => {
        const formGroup = service.createCassuspecteFormGroup();

        const cassuspecte = service.getCassuspecte(formGroup) as any;

        expect(cassuspecte).toMatchObject({});
      });

      it('should return ICassuspecte', () => {
        const formGroup = service.createCassuspecteFormGroup(sampleWithRequiredData);

        const cassuspecte = service.getCassuspecte(formGroup) as any;

        expect(cassuspecte).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICassuspecte should not enable id FormControl', () => {
        const formGroup = service.createCassuspecteFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCassuspecte should disable id FormControl', () => {
        const formGroup = service.createCassuspecteFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
