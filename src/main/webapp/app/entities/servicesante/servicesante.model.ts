export interface IServicesante {
  id: number;
  nom?: string | null;
}

export type NewServicesante = Omit<IServicesante, 'id'> & { id: null };
