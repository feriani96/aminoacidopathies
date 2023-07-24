import { IServicesante, NewServicesante } from './servicesante.model';

export const sampleWithRequiredData: IServicesante = {
  id: 98817,
  nom: 'encoding',
};

export const sampleWithPartialData: IServicesante = {
  id: 67947,
  nom: 'Auto',
};

export const sampleWithFullData: IServicesante = {
  id: 63757,
  nom: 'South backing',
};

export const sampleWithNewData: NewServicesante = {
  nom: 'Maine program Gloves',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
