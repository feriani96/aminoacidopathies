import { IStructuresante, NewStructuresante } from './structuresante.model';

export const sampleWithRequiredData: IStructuresante = {
  id: 12089,
};

export const sampleWithPartialData: IStructuresante = {
  id: 98148,
  name: 'back-end Plastic Account',
};

export const sampleWithFullData: IStructuresante = {
  id: 8418,
  name: 'Czech withdrawal input',
};

export const sampleWithNewData: NewStructuresante = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
