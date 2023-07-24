import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../fiche.test-samples';

import { FicheFormService } from './fiche-form.service';

describe('Fiche Form Service', () => {
  let service: FicheFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FicheFormService);
  });

  describe('Service methods', () => {
    describe('createFicheFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFicheFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            datemaj: expect.any(Object),
            type_observation: expect.any(Object),
            identifiant_registre: expect.any(Object),
            date_enregistrement: expect.any(Object),
            sexe: expect.any(Object),
            date_naissance: expect.any(Object),
            statut: expect.any(Object),
            date_deces: expect.any(Object),
            circonstance_deces: expect.any(Object),
            autre_circonstance_deces: expect.any(Object),
            lieu_deces: expect.any(Object),
            consanguinite: expect.any(Object),
            origine_geo_pere_gouvernorat: expect.any(Object),
            origine_geo_mere_gouvernorat: expect.any(Object),
            origine_geo_pere_deleguation: expect.any(Object),
            origine_geo_mere_deleguation: expect.any(Object),
            couverture_sociale: expect.any(Object),
            autre_couverture_sociale: expect.any(Object),
            activite: expect.any(Object),
            btravail: expect.any(Object),
            travail: expect.any(Object),
            scolarise: expect.any(Object),
            type_scolarise: expect.any(Object),
            niveau_scolarisation: expect.any(Object),
            cas_familiaux: expect.any(Object),
            nbcasconfirme: expect.any(Object),
            nbcassuspectes: expect.any(Object),
            nbcasdecedes: expect.any(Object),
            deces_en_bas_age: expect.any(Object),
            nbcas_deces_age_bas: expect.any(Object),
            circonstances_decouverte: expect.any(Object),
            age_aux_premiers_symptomes: expect.any(Object),
            an_age_premiers_symptomes: expect.any(Object),
            mois_age_premiers_symptomes: expect.any(Object),
            jours_premiers_symptomes: expect.any(Object),
            age_premiere_consultation: expect.any(Object),
            an_age_premiere_consultation: expect.any(Object),
            mois_age_premiere_consultation: expect.any(Object),
            jours_premiere_consultation: expect.any(Object),
            date_derniere_evaluation: expect.any(Object),
            date_diagnostic: expect.any(Object),
            handicap_mental: expect.any(Object),
            qi: expect.any(Object),
            handicap_moteur: expect.any(Object),
            hadicap_moteur_grade: expect.any(Object),
            deficit_neurosensoriel: expect.any(Object),
            deficit_neurosensoriel_val: expect.any(Object),
            deficience_psychique: expect.any(Object),
            deficience_psychique_val: expect.any(Object),
            autre_deficience_psychique: expect.any(Object),
            regime: expect.any(Object),
            regime_val: expect.any(Object),
            medicament_specifique: expect.any(Object),
            medicament_specifique_val: expect.any(Object),
            autre_medicament_specifique: expect.any(Object),
            vitamines: expect.any(Object),
            vitamines_val: expect.any(Object),
            greffehepatique: expect.any(Object),
            reeducation_fonctionnelle: expect.any(Object),
            appareillage: expect.any(Object),
            psuchologie: expect.any(Object),
            ergotherapie: expect.any(Object),
            depistage_post_natal_oriente: expect.any(Object),
            echelle_depistage: expect.any(Object),
            nombre_individus_depistes: expect.any(Object),
            nouveaux_cas_depistes: expect.any(Object),
            nombre_nouveaux_cas_depistes: expect.any(Object),
            code_registre1_cas_depistes: expect.any(Object),
            lien_parente1_cas_depistes: expect.any(Object),
            autre_lien_parente1: expect.any(Object),
            code_registre2_cas_depistes: expect.any(Object),
            lien_parente2_cas_depistes: expect.any(Object),
            autre_lien_parente2: expect.any(Object),
            nombre_de_grossesse_ulterieures: expect.any(Object),
            nomre_DPN: expect.any(Object),
            nombre_nouveaux_cas_diagnostiques: expect.any(Object),
            nombre_ITG: expect.any(Object),
            nomre_de_grossesses_poursuivies: expect.any(Object),
            nombre_de_foetus_sains: expect.any(Object),
            pathologie: expect.any(Object),
          })
        );
      });

      it('passing IFiche should create a new form with FormGroup', () => {
        const formGroup = service.createFicheFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            datemaj: expect.any(Object),
            type_observation: expect.any(Object),
            identifiant_registre: expect.any(Object),
            date_enregistrement: expect.any(Object),
            sexe: expect.any(Object),
            date_naissance: expect.any(Object),
            statut: expect.any(Object),
            date_deces: expect.any(Object),
            circonstance_deces: expect.any(Object),
            autre_circonstance_deces: expect.any(Object),
            lieu_deces: expect.any(Object),
            consanguinite: expect.any(Object),
            origine_geo_pere_gouvernorat: expect.any(Object),
            origine_geo_mere_gouvernorat: expect.any(Object),
            origine_geo_pere_deleguation: expect.any(Object),
            origine_geo_mere_deleguation: expect.any(Object),
            couverture_sociale: expect.any(Object),
            autre_couverture_sociale: expect.any(Object),
            activite: expect.any(Object),
            btravail: expect.any(Object),
            travail: expect.any(Object),
            scolarise: expect.any(Object),
            type_scolarise: expect.any(Object),
            niveau_scolarisation: expect.any(Object),
            cas_familiaux: expect.any(Object),
            nbcasconfirme: expect.any(Object),
            nbcassuspectes: expect.any(Object),
            nbcasdecedes: expect.any(Object),
            deces_en_bas_age: expect.any(Object),
            nbcas_deces_age_bas: expect.any(Object),
            circonstances_decouverte: expect.any(Object),
            age_aux_premiers_symptomes: expect.any(Object),
            an_age_premiers_symptomes: expect.any(Object),
            mois_age_premiers_symptomes: expect.any(Object),
            jours_premiers_symptomes: expect.any(Object),
            age_premiere_consultation: expect.any(Object),
            an_age_premiere_consultation: expect.any(Object),
            mois_age_premiere_consultation: expect.any(Object),
            jours_premiere_consultation: expect.any(Object),
            date_derniere_evaluation: expect.any(Object),
            date_diagnostic: expect.any(Object),
            handicap_mental: expect.any(Object),
            qi: expect.any(Object),
            handicap_moteur: expect.any(Object),
            hadicap_moteur_grade: expect.any(Object),
            deficit_neurosensoriel: expect.any(Object),
            deficit_neurosensoriel_val: expect.any(Object),
            deficience_psychique: expect.any(Object),
            deficience_psychique_val: expect.any(Object),
            autre_deficience_psychique: expect.any(Object),
            regime: expect.any(Object),
            regime_val: expect.any(Object),
            medicament_specifique: expect.any(Object),
            medicament_specifique_val: expect.any(Object),
            autre_medicament_specifique: expect.any(Object),
            vitamines: expect.any(Object),
            vitamines_val: expect.any(Object),
            greffehepatique: expect.any(Object),
            reeducation_fonctionnelle: expect.any(Object),
            appareillage: expect.any(Object),
            psuchologie: expect.any(Object),
            ergotherapie: expect.any(Object),
            depistage_post_natal_oriente: expect.any(Object),
            echelle_depistage: expect.any(Object),
            nombre_individus_depistes: expect.any(Object),
            nouveaux_cas_depistes: expect.any(Object),
            nombre_nouveaux_cas_depistes: expect.any(Object),
            code_registre1_cas_depistes: expect.any(Object),
            lien_parente1_cas_depistes: expect.any(Object),
            autre_lien_parente1: expect.any(Object),
            code_registre2_cas_depistes: expect.any(Object),
            lien_parente2_cas_depistes: expect.any(Object),
            autre_lien_parente2: expect.any(Object),
            nombre_de_grossesse_ulterieures: expect.any(Object),
            nomre_DPN: expect.any(Object),
            nombre_nouveaux_cas_diagnostiques: expect.any(Object),
            nombre_ITG: expect.any(Object),
            nomre_de_grossesses_poursuivies: expect.any(Object),
            nombre_de_foetus_sains: expect.any(Object),
            pathologie: expect.any(Object),
          })
        );
      });
    });

    describe('getFiche', () => {
      it('should return NewFiche for default Fiche initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFicheFormGroup(sampleWithNewData);

        const fiche = service.getFiche(formGroup) as any;

        expect(fiche).toMatchObject(sampleWithNewData);
      });

      it('should return NewFiche for empty Fiche initial value', () => {
        const formGroup = service.createFicheFormGroup();

        const fiche = service.getFiche(formGroup) as any;

        expect(fiche).toMatchObject({});
      });

      it('should return IFiche', () => {
        const formGroup = service.createFicheFormGroup(sampleWithRequiredData);

        const fiche = service.getFiche(formGroup) as any;

        expect(fiche).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFiche should not enable id FormControl', () => {
        const formGroup = service.createFicheFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFiche should disable id FormControl', () => {
        const formGroup = service.createFicheFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
