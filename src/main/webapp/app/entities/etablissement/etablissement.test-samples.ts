import { IEtablissement, NewEtablissement } from './etablissement.model';

export const sampleWithRequiredData: IEtablissement = {
  id: 16156,
};

export const sampleWithPartialData: IEtablissement = {
  id: 55793,
};

export const sampleWithFullData: IEtablissement = {
  id: 47080,
  name: 'Books even-keeled',
};

export const sampleWithNewData: NewEtablissement = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
