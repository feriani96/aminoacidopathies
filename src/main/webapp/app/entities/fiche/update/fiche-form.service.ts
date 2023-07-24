import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFiche, NewFiche } from '../fiche.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFiche for edit and NewFicheFormGroupInput for create.
 */
type FicheFormGroupInput = IFiche | PartialWithRequiredKeyOf<NewFiche>;

type FicheFormDefaults = Pick<NewFiche, 'id' | 'btravail' | 'scolarise'>;

type FicheFormGroupContent = {
  id: FormControl<IFiche['id'] | NewFiche['id']>;
  datemaj: FormControl<IFiche['datemaj']>;
  type_observation: FormControl<IFiche['type_observation']>;
  identifiant_registre: FormControl<IFiche['identifiant_registre']>;
  date_enregistrement: FormControl<IFiche['date_enregistrement']>;
  sexe: FormControl<IFiche['sexe']>;
  date_naissance: FormControl<IFiche['date_naissance']>;
  statut: FormControl<IFiche['statut']>;
  date_deces: FormControl<IFiche['date_deces']>;
  circonstance_deces: FormControl<IFiche['circonstance_deces']>;
  autre_circonstance_deces: FormControl<IFiche['autre_circonstance_deces']>;
  lieu_deces: FormControl<IFiche['lieu_deces']>;
  consanguinite: FormControl<IFiche['consanguinite']>;
  origine_geo_pere_gouvernorat: FormControl<IFiche['origine_geo_pere_gouvernorat']>;
  origine_geo_mere_gouvernorat: FormControl<IFiche['origine_geo_mere_gouvernorat']>;
  origine_geo_pere_deleguation: FormControl<IFiche['origine_geo_pere_deleguation']>;
  origine_geo_mere_deleguation: FormControl<IFiche['origine_geo_mere_deleguation']>;
  couverture_sociale: FormControl<IFiche['couverture_sociale']>;
  autre_couverture_sociale: FormControl<IFiche['autre_couverture_sociale']>;
  activite: FormControl<IFiche['activite']>;
  btravail: FormControl<IFiche['btravail']>;
  travail: FormControl<IFiche['travail']>;
  scolarise: FormControl<IFiche['scolarise']>;
  type_scolarise: FormControl<IFiche['type_scolarise']>;
  niveau_scolarisation: FormControl<IFiche['niveau_scolarisation']>;
  cas_familiaux: FormControl<IFiche['cas_familiaux']>;
  nbcasconfirme: FormControl<IFiche['nbcasconfirme']>;
  nbcassuspectes: FormControl<IFiche['nbcassuspectes']>;
  nbcasdecedes: FormControl<IFiche['nbcasdecedes']>;
  deces_en_bas_age: FormControl<IFiche['deces_en_bas_age']>;
  nbcas_deces_age_bas: FormControl<IFiche['nbcas_deces_age_bas']>;
  circonstances_decouverte: FormControl<IFiche['circonstances_decouverte']>;
  age_aux_premiers_symptomes: FormControl<IFiche['age_aux_premiers_symptomes']>;
  an_age_premiers_symptomes: FormControl<IFiche['an_age_premiers_symptomes']>;
  mois_age_premiers_symptomes: FormControl<IFiche['mois_age_premiers_symptomes']>;
  jours_premiers_symptomes: FormControl<IFiche['jours_premiers_symptomes']>;
  age_premiere_consultation: FormControl<IFiche['age_premiere_consultation']>;
  an_age_premiere_consultation: FormControl<IFiche['an_age_premiere_consultation']>;
  mois_age_premiere_consultation: FormControl<IFiche['mois_age_premiere_consultation']>;
  jours_premiere_consultation: FormControl<IFiche['jours_premiere_consultation']>;
  date_derniere_evaluation: FormControl<IFiche['date_derniere_evaluation']>;
  date_diagnostic: FormControl<IFiche['date_diagnostic']>;
  handicap_mental: FormControl<IFiche['handicap_mental']>;
  qi: FormControl<IFiche['qi']>;
  handicap_moteur: FormControl<IFiche['handicap_moteur']>;
  hadicap_moteur_grade: FormControl<IFiche['hadicap_moteur_grade']>;
  deficit_neurosensoriel: FormControl<IFiche['deficit_neurosensoriel']>;
  deficit_neurosensoriel_val: FormControl<IFiche['deficit_neurosensoriel_val']>;
  deficience_psychique: FormControl<IFiche['deficience_psychique']>;
  deficience_psychique_val: FormControl<IFiche['deficience_psychique_val']>;
  autre_deficience_psychique: FormControl<IFiche['autre_deficience_psychique']>;
  regime: FormControl<IFiche['regime']>;
  regime_val: FormControl<IFiche['regime_val']>;
  medicament_specifique: FormControl<IFiche['medicament_specifique']>;
  medicament_specifique_val: FormControl<IFiche['medicament_specifique_val']>;
  autre_medicament_specifique: FormControl<IFiche['autre_medicament_specifique']>;
  vitamines: FormControl<IFiche['vitamines']>;
  vitamines_val: FormControl<IFiche['vitamines_val']>;
  greffehepatique: FormControl<IFiche['greffehepatique']>;
  reeducation_fonctionnelle: FormControl<IFiche['reeducation_fonctionnelle']>;
  appareillage: FormControl<IFiche['appareillage']>;
  psuchologie: FormControl<IFiche['psuchologie']>;
  ergotherapie: FormControl<IFiche['ergotherapie']>;
  depistage_post_natal_oriente: FormControl<IFiche['depistage_post_natal_oriente']>;
  echelle_depistage: FormControl<IFiche['echelle_depistage']>;
  nombre_individus_depistes: FormControl<IFiche['nombre_individus_depistes']>;
  nouveaux_cas_depistes: FormControl<IFiche['nouveaux_cas_depistes']>;
  nombre_nouveaux_cas_depistes: FormControl<IFiche['nombre_nouveaux_cas_depistes']>;
  code_registre1_cas_depistes: FormControl<IFiche['code_registre1_cas_depistes']>;
  lien_parente1_cas_depistes: FormControl<IFiche['lien_parente1_cas_depistes']>;
  autre_lien_parente1: FormControl<IFiche['autre_lien_parente1']>;
  code_registre2_cas_depistes: FormControl<IFiche['code_registre2_cas_depistes']>;
  lien_parente2_cas_depistes: FormControl<IFiche['lien_parente2_cas_depistes']>;
  autre_lien_parente2: FormControl<IFiche['autre_lien_parente2']>;
  nombre_de_grossesse_ulterieures: FormControl<IFiche['nombre_de_grossesse_ulterieures']>;
  nomre_DPN: FormControl<IFiche['nomre_DPN']>;
  nombre_nouveaux_cas_diagnostiques: FormControl<IFiche['nombre_nouveaux_cas_diagnostiques']>;
  nombre_ITG: FormControl<IFiche['nombre_ITG']>;
  nomre_de_grossesses_poursuivies: FormControl<IFiche['nomre_de_grossesses_poursuivies']>;
  nombre_de_foetus_sains: FormControl<IFiche['nombre_de_foetus_sains']>;
  pathologie: FormControl<IFiche['pathologie']>;
};

export type FicheFormGroup = FormGroup<FicheFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheFormService {
  createFicheFormGroup(fiche: FicheFormGroupInput = { id: null }): FicheFormGroup {
    const ficheRawValue = {
      ...this.getFormDefaults(),
      ...fiche,
    };
    return new FormGroup<FicheFormGroupContent>({
      id: new FormControl(
        { value: ficheRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      datemaj: new FormControl(ficheRawValue.datemaj, {
        validators: [Validators.required],
      }),
      type_observation: new FormControl(ficheRawValue.type_observation),
      identifiant_registre: new FormControl(ficheRawValue.identifiant_registre),
      date_enregistrement: new FormControl(ficheRawValue.date_enregistrement),
      sexe: new FormControl(ficheRawValue.sexe),
      date_naissance: new FormControl(ficheRawValue.date_naissance),
      statut: new FormControl(ficheRawValue.statut),
      date_deces: new FormControl(ficheRawValue.date_deces),
      circonstance_deces: new FormControl(ficheRawValue.circonstance_deces),
      autre_circonstance_deces: new FormControl(ficheRawValue.autre_circonstance_deces),
      lieu_deces: new FormControl(ficheRawValue.lieu_deces),
      consanguinite: new FormControl(ficheRawValue.consanguinite),
      origine_geo_pere_gouvernorat: new FormControl(ficheRawValue.origine_geo_pere_gouvernorat),
      origine_geo_mere_gouvernorat: new FormControl(ficheRawValue.origine_geo_mere_gouvernorat),
      origine_geo_pere_deleguation: new FormControl(ficheRawValue.origine_geo_pere_deleguation),
      origine_geo_mere_deleguation: new FormControl(ficheRawValue.origine_geo_mere_deleguation),
      couverture_sociale: new FormControl(ficheRawValue.couverture_sociale),
      autre_couverture_sociale: new FormControl(ficheRawValue.autre_couverture_sociale),
      activite: new FormControl(ficheRawValue.activite),
      btravail: new FormControl(ficheRawValue.btravail),
      travail: new FormControl(ficheRawValue.travail),
      scolarise: new FormControl(ficheRawValue.scolarise),
      type_scolarise: new FormControl(ficheRawValue.type_scolarise),
      niveau_scolarisation: new FormControl(ficheRawValue.niveau_scolarisation),
      cas_familiaux: new FormControl(ficheRawValue.cas_familiaux),
      nbcasconfirme: new FormControl(ficheRawValue.nbcasconfirme),
      nbcassuspectes: new FormControl(ficheRawValue.nbcassuspectes),
      nbcasdecedes: new FormControl(ficheRawValue.nbcasdecedes),
      deces_en_bas_age: new FormControl(ficheRawValue.deces_en_bas_age),
      nbcas_deces_age_bas: new FormControl(ficheRawValue.nbcas_deces_age_bas),
      circonstances_decouverte: new FormControl(ficheRawValue.circonstances_decouverte),
      age_aux_premiers_symptomes: new FormControl(ficheRawValue.age_aux_premiers_symptomes),
      an_age_premiers_symptomes: new FormControl(ficheRawValue.an_age_premiers_symptomes),
      mois_age_premiers_symptomes: new FormControl(ficheRawValue.mois_age_premiers_symptomes),
      jours_premiers_symptomes: new FormControl(ficheRawValue.jours_premiers_symptomes),
      age_premiere_consultation: new FormControl(ficheRawValue.age_premiere_consultation),
      an_age_premiere_consultation: new FormControl(ficheRawValue.an_age_premiere_consultation),
      mois_age_premiere_consultation: new FormControl(ficheRawValue.mois_age_premiere_consultation),
      jours_premiere_consultation: new FormControl(ficheRawValue.jours_premiere_consultation),
      date_derniere_evaluation: new FormControl(ficheRawValue.date_derniere_evaluation),
      date_diagnostic: new FormControl(ficheRawValue.date_diagnostic),
      handicap_mental: new FormControl(ficheRawValue.handicap_mental),
      qi: new FormControl(ficheRawValue.qi),
      handicap_moteur: new FormControl(ficheRawValue.handicap_moteur),
      hadicap_moteur_grade: new FormControl(ficheRawValue.hadicap_moteur_grade),
      deficit_neurosensoriel: new FormControl(ficheRawValue.deficit_neurosensoriel),
      deficit_neurosensoriel_val: new FormControl(ficheRawValue.deficit_neurosensoriel_val),
      deficience_psychique: new FormControl(ficheRawValue.deficience_psychique),
      deficience_psychique_val: new FormControl(ficheRawValue.deficience_psychique_val),
      autre_deficience_psychique: new FormControl(ficheRawValue.autre_deficience_psychique),
      regime: new FormControl(ficheRawValue.regime),
      regime_val: new FormControl(ficheRawValue.regime_val),
      medicament_specifique: new FormControl(ficheRawValue.medicament_specifique),
      medicament_specifique_val: new FormControl(ficheRawValue.medicament_specifique_val),
      autre_medicament_specifique: new FormControl(ficheRawValue.autre_medicament_specifique),
      vitamines: new FormControl(ficheRawValue.vitamines),
      vitamines_val: new FormControl(ficheRawValue.vitamines_val),
      greffehepatique: new FormControl(ficheRawValue.greffehepatique),
      reeducation_fonctionnelle: new FormControl(ficheRawValue.reeducation_fonctionnelle),
      appareillage: new FormControl(ficheRawValue.appareillage),
      psuchologie: new FormControl(ficheRawValue.psuchologie),
      ergotherapie: new FormControl(ficheRawValue.ergotherapie),
      depistage_post_natal_oriente: new FormControl(ficheRawValue.depistage_post_natal_oriente),
      echelle_depistage: new FormControl(ficheRawValue.echelle_depistage),
      nombre_individus_depistes: new FormControl(ficheRawValue.nombre_individus_depistes),
      nouveaux_cas_depistes: new FormControl(ficheRawValue.nouveaux_cas_depistes),
      nombre_nouveaux_cas_depistes: new FormControl(ficheRawValue.nombre_nouveaux_cas_depistes),
      code_registre1_cas_depistes: new FormControl(ficheRawValue.code_registre1_cas_depistes),
      lien_parente1_cas_depistes: new FormControl(ficheRawValue.lien_parente1_cas_depistes),
      autre_lien_parente1: new FormControl(ficheRawValue.autre_lien_parente1),
      code_registre2_cas_depistes: new FormControl(ficheRawValue.code_registre2_cas_depistes),
      lien_parente2_cas_depistes: new FormControl(ficheRawValue.lien_parente2_cas_depistes),
      autre_lien_parente2: new FormControl(ficheRawValue.autre_lien_parente2),
      nombre_de_grossesse_ulterieures: new FormControl(ficheRawValue.nombre_de_grossesse_ulterieures),
      nomre_DPN: new FormControl(ficheRawValue.nomre_DPN),
      nombre_nouveaux_cas_diagnostiques: new FormControl(ficheRawValue.nombre_nouveaux_cas_diagnostiques),
      nombre_ITG: new FormControl(ficheRawValue.nombre_ITG),
      nomre_de_grossesses_poursuivies: new FormControl(ficheRawValue.nomre_de_grossesses_poursuivies),
      nombre_de_foetus_sains: new FormControl(ficheRawValue.nombre_de_foetus_sains),
      pathologie: new FormControl(ficheRawValue.pathologie, {
        validators: [Validators.required],
      }),
    });
  }

  getFiche(form: FicheFormGroup): IFiche | NewFiche {
    return form.getRawValue() as IFiche | NewFiche;
  }

  resetForm(form: FicheFormGroup, fiche: FicheFormGroupInput): void {
    const ficheRawValue = { ...this.getFormDefaults(), ...fiche };
    form.reset(
      {
        ...ficheRawValue,
        id: { value: ficheRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FicheFormDefaults {
    return {
      id: null,
      btravail: false,
      scolarise: false,
    };
  }
}
