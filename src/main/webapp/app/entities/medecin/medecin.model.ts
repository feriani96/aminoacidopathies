import { IEtablissement } from 'app/entities/etablissement/etablissement.model';
import { IServicesante } from 'app/entities/servicesante/servicesante.model';

export interface IMedecin {
  id: number;
  nom?: string | null;
  prenom?: string | null;
  cin?: string | null;
  email?: string | null;
  tel?: string | null;
  adresse?: string | null;
  etablissement?: Pick<IEtablissement, 'id'> | null;
  servicesante?: Pick<IServicesante, 'id'> | null;
}

export type NewMedecin = Omit<IMedecin, 'id'> & { id: null };
