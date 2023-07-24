import { IMedecin, NewMedecin } from './medecin.model';

export const sampleWithRequiredData: IMedecin = {
  id: 43266,
  nom: 'Dalasi',
  prenom: 'Mauritius Rubber',
};

export const sampleWithPartialData: IMedecin = {
  id: 70502,
  nom: 'Road Bacon users',
  prenom: 'azure Response',
  tel: 'Cheese',
  adresse: 'Shores',
};

export const sampleWithFullData: IMedecin = {
  id: 5448,
  nom: 'card',
  prenom: 'value-added Account withdrawal',
  cin: 'encoding Trace Buckinghamshire',
  email: 'Shania96@yahoo.com',
  tel: 'tertiary',
  adresse: 'visionary bypass',
};

export const sampleWithNewData: NewMedecin = {
  nom: 'reserved bypassing area',
  prenom: 'Berkshire',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
