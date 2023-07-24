import { IStructuresante } from 'app/entities/structuresante/structuresante.model';

export interface IEtablissement {
  id: number;
  name?: string | null;
  structuresante?: Pick<IStructuresante, 'id'> | null;
}

export type NewEtablissement = Omit<IEtablissement, 'id'> & { id: null };
