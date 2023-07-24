import { IPathologie, NewPathologie } from './pathologie.model';

export const sampleWithRequiredData: IPathologie = {
  id: 65517,
  nom: 'Liaison',
};

export const sampleWithPartialData: IPathologie = {
  id: 21254,
  nom: 'salmon Coordinator',
};

export const sampleWithFullData: IPathologie = {
  id: 19139,
  nom: 'Nakfa Account',
};

export const sampleWithNewData: NewPathologie = {
  nom: 'copying Jewelery',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
