import { etypestructure } from 'app/entities/enumerations/etypestructure.model';

import { IStructurefiche, NewStructurefiche } from './structurefiche.model';

export const sampleWithRequiredData: IStructurefiche = {
  id: 84613,
  typestructure: etypestructure['SUIVI'],
  ordre: 49871,
};

export const sampleWithPartialData: IStructurefiche = {
  id: 77475,
  typestructure: etypestructure['ORIGINE'],
  ordre: 30597,
};

export const sampleWithFullData: IStructurefiche = {
  id: 69288,
  typestructure: etypestructure['ORIGINE'],
  ordre: 2959,
};

export const sampleWithNewData: NewStructurefiche = {
  typestructure: etypestructure['SUIVI'],
  ordre: 1742,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
