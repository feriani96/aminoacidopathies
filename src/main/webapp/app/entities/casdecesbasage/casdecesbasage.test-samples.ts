import { elienparente } from 'app/entities/enumerations/elienparente.model';
import { elieudeces } from 'app/entities/enumerations/elieudeces.model';

import { ICasdecesbasage, NewCasdecesbasage } from './casdecesbasage.model';

export const sampleWithRequiredData: ICasdecesbasage = {
  id: 51541,
};

export const sampleWithPartialData: ICasdecesbasage = {
  id: 50661,
  confirme: true,
  code_registre: 'Regional Shoes orchid',
  autre_lien_parente: 'grey SMS',
  an_age_de_deces: 85231,
  mois_age_de_deces: 35202,
  jours_age_de_deces: 41077,
  tbl_neuro: true,
  tbl_hemorragique: false,
  autre_circonstances_deces: 'Dynamic mint',
};

export const sampleWithFullData: ICasdecesbasage = {
  id: 54425,
  confirme: true,
  code_registre: 'Jewelery Rest',
  suspecte: false,
  lien_de_parente: elienparente['ONCLE_TANTE'],
  autre_lien_parente: 'Refined reboot mesh',
  an_age_de_deces: 46408,
  mois_age_de_deces: 46247,
  jours_age_de_deces: 36898,
  tbl_neuro: true,
  tbl_hemorragique: true,
  tbl_infx: true,
  autre_circonstances_deces: 'Bedfordshire',
  bautre_circonstance_deces: true,
  np_circonstances_deces: true,
  lieu_deces: elieudeces['DOMICILE'],
};

export const sampleWithNewData: NewCasdecesbasage = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
