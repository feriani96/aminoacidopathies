import { ename } from 'app/entities/enumerations/ename.model';
import { efait } from 'app/entities/enumerations/efait.model';
import { elaboratoire } from 'app/entities/enumerations/elaboratoire.model';
import { eResultat } from 'app/entities/enumerations/e-resultat.model';

import { IMetabolique, NewMetabolique } from './metabolique.model';

export const sampleWithRequiredData: IMetabolique = {
  id: 95920,
};

export const sampleWithPartialData: IMetabolique = {
  id: 63729,
  name: ename['DOSAGEPHESURPAPIER'],
  resultat: eResultat['EN_COURS'],
};

export const sampleWithFullData: IMetabolique = {
  id: 28337,
  name: ename['SUCCINYLACETONEURINAIRE'],
  fait: efait['NP'],
  laboratoire: elaboratoire['LABORATOIREETRANGER'],
  resultat: eResultat['EN_COURS'],
};

export const sampleWithNewData: NewMetabolique = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
