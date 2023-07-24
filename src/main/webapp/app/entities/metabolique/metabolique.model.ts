import { ename } from 'app/entities/enumerations/ename.model';
import { efait } from 'app/entities/enumerations/efait.model';
import { elaboratoire } from 'app/entities/enumerations/elaboratoire.model';
import { eResultat } from 'app/entities/enumerations/e-resultat.model';

export interface IMetabolique {
  id: number;
  name?: ename | null;
  fait?: efait | null;
  laboratoire?: elaboratoire | null;
  resultat?: eResultat | null;
}

export type NewMetabolique = Omit<IMetabolique, 'id'> & { id: null };
