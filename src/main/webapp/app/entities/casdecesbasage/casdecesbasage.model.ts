import { elienparente } from 'app/entities/enumerations/elienparente.model';
import { elieudeces } from 'app/entities/enumerations/elieudeces.model';

export interface ICasdecesbasage {
  id: number;
  confirme?: boolean | null;
  code_registre?: string | null;
  suspecte?: boolean | null;
  lien_de_parente?: elienparente | null;
  autre_lien_parente?: string | null;
  an_age_de_deces?: number | null;
  mois_age_de_deces?: number | null;
  jours_age_de_deces?: number | null;
  tbl_neuro?: boolean | null;
  tbl_hemorragique?: boolean | null;
  tbl_infx?: boolean | null;
  autre_circonstances_deces?: string | null;
  bautre_circonstance_deces?: boolean | null;
  np_circonstances_deces?: boolean | null;
  lieu_deces?: elieudeces | null;
}

export type NewCasdecesbasage = Omit<ICasdecesbasage, 'id'> & { id: null };
