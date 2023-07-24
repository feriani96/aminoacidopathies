import { IEtablissement } from 'app/entities/etablissement/etablissement.model';
import { IServicesante } from 'app/entities/servicesante/servicesante.model';
import { IMedecin } from 'app/entities/medecin/medecin.model';
import { etypestructure } from 'app/entities/enumerations/etypestructure.model';

export interface IStructurefiche {
  id: number;
  typestructure?: etypestructure | null;
  ordre?: number | null;
  etablissement?: Pick<IEtablissement, 'id'> | null;
  servicesante?: Pick<IServicesante, 'id'> | null;
  medecin?: Pick<IMedecin, 'id'> | null;
}

export type NewStructurefiche = Omit<IStructurefiche, 'id'> & { id: null };
