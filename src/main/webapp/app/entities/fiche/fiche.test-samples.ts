import dayjs from 'dayjs/esm';

import { esexe } from 'app/entities/enumerations/esexe.model';
import { estatut } from 'app/entities/enumerations/estatut.model';
import { ecirconstance } from 'app/entities/enumerations/ecirconstance.model';
import { elieudeces } from 'app/entities/enumerations/elieudeces.model';
import { econsanguinite } from 'app/entities/enumerations/econsanguinite.model';
import { egouvernorat } from 'app/entities/enumerations/egouvernorat.model';
import { egouvernoratmere } from 'app/entities/enumerations/egouvernoratmere.model';
import { ecouverture } from 'app/entities/enumerations/ecouverture.model';
import { eactivite } from 'app/entities/enumerations/eactivite.model';
import { escolarisetype } from 'app/entities/enumerations/escolarisetype.model';
import { eniveauscolarisation } from 'app/entities/enumerations/eniveauscolarisation.model';
import { ecasfamiliaux } from 'app/entities/enumerations/ecasfamiliaux.model';
import { edecesbasage } from 'app/entities/enumerations/edecesbasage.model';
import { ecircondecouverte } from 'app/entities/enumerations/ecircondecouverte.model';
import { eage_premier_symptome } from 'app/entities/enumerations/eage-premier-symptome.model';
import { eagepremiereconsultation } from 'app/entities/enumerations/eagepremiereconsultation.model';
import { ehandicapmental } from 'app/entities/enumerations/ehandicapmental.model';
import { eQI } from 'app/entities/enumerations/e-qi.model';
import { eMoteur } from 'app/entities/enumerations/e-moteur.model';
import { egrade } from 'app/entities/enumerations/egrade.model';
import { edeficitneuro } from 'app/entities/enumerations/edeficitneuro.model';
import { edeficitneurosensorielval } from 'app/entities/enumerations/edeficitneurosensorielval.model';
import { edeficiencepsychique } from 'app/entities/enumerations/edeficiencepsychique.model';
import { edeficiencepsychiqueval } from 'app/entities/enumerations/edeficiencepsychiqueval.model';
import { eregime } from 'app/entities/enumerations/eregime.model';
import { eregimeval } from 'app/entities/enumerations/eregimeval.model';
import { emedicamentspecifique } from 'app/entities/enumerations/emedicamentspecifique.model';
import { emedicamentspecifiqueval } from 'app/entities/enumerations/emedicamentspecifiqueval.model';
import { evitamines } from 'app/entities/enumerations/evitamines.model';
import { evitamineval } from 'app/entities/enumerations/evitamineval.model';
import { egreffehepatique } from 'app/entities/enumerations/egreffehepatique.model';
import { erededucationfonctionnelle } from 'app/entities/enumerations/erededucationfonctionnelle.model';
import { eappareillage } from 'app/entities/enumerations/eappareillage.model';
import { epsychologie } from 'app/entities/enumerations/epsychologie.model';
import { eergotherapie } from 'app/entities/enumerations/eergotherapie.model';
import { edepistage_post_natal_oriente } from 'app/entities/enumerations/edepistage-post-natal-oriente.model';
import { eechelledepistage } from 'app/entities/enumerations/eechelledepistage.model';
import { enouveaux_cas_depistes } from 'app/entities/enumerations/enouveaux-cas-depistes.model';
import { elienparente1 } from 'app/entities/enumerations/elienparente-1.model';
import { elienparente2 } from 'app/entities/enumerations/elienparente-2.model';

import { IFiche, NewFiche } from './fiche.model';

export const sampleWithRequiredData: IFiche = {
  id: 52797,
  datemaj: dayjs('2023-07-16'),
};

export const sampleWithPartialData: IFiche = {
  id: 77223,
  datemaj: dayjs('2023-07-16'),
  type_observation: 'CSS Outdoors',
  date_enregistrement: dayjs('2023-07-16'),
  sexe: esexe['M'],
  date_naissance: dayjs('2023-07-15'),
  statut: estatut['ENCORE_SUIVI'],
  date_deces: dayjs('2023-07-16'),
  consanguinite: econsanguinite['OUI'],
  origine_geo_pere_deleguation: 'web-enabled',
  origine_geo_mere_deleguation: 'Pizza Bedfordshire',
  autre_couverture_sociale: 'solutions',
  btravail: true,
  travail: 'platforms Loan synthesize',
  cas_familiaux: ecasfamiliaux['OUI'],
  nbcassuspectes: 56753,
  nbcasdecedes: 33992,
  age_aux_premiers_symptomes: eage_premier_symptome['NON_CONNU'],
  jours_premiers_symptomes: 7995,
  an_age_premiere_consultation: 57054,
  date_derniere_evaluation: dayjs('2023-07-16'),
  handicap_mental: ehandicapmental['NON'],
  qi: eQI['INFERIEUR_20'],
  handicap_moteur: eMoteur['OUI'],
  deficit_neurosensoriel: edeficitneuro['OUI'],
  deficience_psychique: edeficiencepsychique['NP'],
  vitamines: evitamines['NP'],
  greffehepatique: egreffehepatique['NP'],
  appareillage: eappareillage['OUI'],
  nombre_individus_depistes: 87912,
  nouveaux_cas_depistes: enouveaux_cas_depistes['NON'],
  code_registre1_cas_depistes: 'HTTP',
  lien_parente1_cas_depistes: elienparente1['FRATERIE'],
  lien_parente2_cas_depistes: elienparente2['COUSINS'],
  autre_lien_parente2: 'Developer hack Sleek',
  nombre_de_grossesse_ulterieures: 13840,
  nombre_nouveaux_cas_diagnostiques: 17316,
  nombre_de_foetus_sains: 25397,
};

export const sampleWithFullData: IFiche = {
  id: 13400,
  datemaj: dayjs('2023-07-16'),
  type_observation: 'withdrawal e-services',
  identifiant_registre: 'best-of-breed',
  date_enregistrement: dayjs('2023-07-15'),
  sexe: esexe['M'],
  date_naissance: dayjs('2023-07-16'),
  statut: estatut['PERDU_DE_VUE'],
  date_deces: dayjs('2023-07-16'),
  circonstance_deces: ecirconstance['TBL_NEURO'],
  autre_circonstance_deces: 'XML Supervisor',
  lieu_deces: elieudeces['DOMICILE'],
  consanguinite: econsanguinite['OUI'],
  origine_geo_pere_gouvernorat: egouvernorat['NABEUL'],
  origine_geo_mere_gouvernorat: egouvernoratmere['SILIANA'],
  origine_geo_pere_deleguation: 'copying',
  origine_geo_mere_deleguation: 'Rwanda orchestrate',
  couverture_sociale: ecouverture['AUCUNE'],
  autre_couverture_sociale: 'multi-byte Garden',
  activite: eactivite['NP'],
  btravail: false,
  travail: 'Lebanon Account',
  scolarise: false,
  type_scolarise: escolarisetype['ECOLE_INTEGRATION'],
  niveau_scolarisation: eniveauscolarisation['SUPERIEUR'],
  cas_familiaux: ecasfamiliaux['NON'],
  nbcasconfirme: 38172,
  nbcassuspectes: 26684,
  nbcasdecedes: 42419,
  deces_en_bas_age: edecesbasage['NP'],
  nbcas_deces_age_bas: 52890,
  circonstances_decouverte: ecircondecouverte['SYMPTOMATIQUE'],
  age_aux_premiers_symptomes: eage_premier_symptome['CONNU'],
  an_age_premiers_symptomes: 2235,
  mois_age_premiers_symptomes: 99169,
  jours_premiers_symptomes: 65553,
  age_premiere_consultation: eagepremiereconsultation['CONNU'],
  an_age_premiere_consultation: 14796,
  mois_age_premiere_consultation: 57056,
  jours_premiere_consultation: 13494,
  date_derniere_evaluation: dayjs('2023-07-16'),
  date_diagnostic: dayjs('2023-07-16'),
  handicap_mental: ehandicapmental['NON'],
  qi: eQI['INFERIEUR_20'],
  handicap_moteur: eMoteur['OUI'],
  hadicap_moteur_grade: egrade['MOYEN'],
  deficit_neurosensoriel: edeficitneuro['OUI'],
  deficit_neurosensoriel_val: edeficitneurosensorielval['AUDITIF'],
  deficience_psychique: edeficiencepsychique['NON'],
  deficience_psychique_val: edeficiencepsychiqueval['TROUBLES_COMMUNICATION_LANGUAGE'],
  autre_deficience_psychique: 'Tactics Quality haptic',
  regime: eregime['NP'],
  regime_val: eregimeval['CONTROLE_EN_ACIDE_AMINE'],
  medicament_specifique: emedicamentspecifique['NP'],
  medicament_specifique_val: emedicamentspecifiqueval['NTBC_NITISIN_ORFADIN'],
  autre_medicament_specifique: 'internet Computers',
  vitamines: evitamines['OUI'],
  vitamines_val: evitamineval['VITAMINEC_ACIDEASCORBIQUE'],
  greffehepatique: egreffehepatique['NON'],
  reeducation_fonctionnelle: erededucationfonctionnelle['OUI'],
  appareillage: eappareillage['NP'],
  psuchologie: epsychologie['OUI'],
  ergotherapie: eergotherapie['OUI'],
  depistage_post_natal_oriente: edepistage_post_natal_oriente['OUI'],
  echelle_depistage: eechelledepistage['NP'],
  nombre_individus_depistes: 59005,
  nouveaux_cas_depistes: enouveaux_cas_depistes['EN_COURS'],
  nombre_nouveaux_cas_depistes: 95362,
  code_registre1_cas_depistes: '24/365 Intelligent',
  lien_parente1_cas_depistes: elienparente1['FRATERIE'],
  autre_lien_parente1: 'Market Wells',
  code_registre2_cas_depistes: 'withdrawal Wisconsin',
  lien_parente2_cas_depistes: elienparente2['FRATERIE'],
  autre_lien_parente2: 'online Front-line',
  nombre_de_grossesse_ulterieures: 41502,
  nomre_DPN: 81682,
  nombre_nouveaux_cas_diagnostiques: 58380,
  nombre_ITG: 38540,
  nomre_de_grossesses_poursuivies: 48805,
  nombre_de_foetus_sains: 40940,
};

export const sampleWithNewData: NewFiche = {
  datemaj: dayjs('2023-07-15'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
