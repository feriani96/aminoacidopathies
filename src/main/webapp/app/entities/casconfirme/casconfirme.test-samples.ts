import { elien_parente } from 'app/entities/enumerations/elien-parente.model';

import { ICasconfirme, NewCasconfirme } from './casconfirme.model';

export const sampleWithRequiredData: ICasconfirme = {
  id: 93980,
  code_registre: 'Developer neural Dynamic',
};

export const sampleWithPartialData: ICasconfirme = {
  id: 60431,
  code_registre: 'frictionless payment JBOD',
  lien_parente: elien_parente['NP'],
};

export const sampleWithFullData: ICasconfirme = {
  id: 10591,
  code_registre: 'Berkshire sensor Roads',
  lien_parente: elien_parente['FRERE_SOEUR'],
};

export const sampleWithNewData: NewCasconfirme = {
  code_registre: 'Human plum engineer',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
