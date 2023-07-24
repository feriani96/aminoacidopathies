export interface IStructuresante {
  id: number;
  name?: string | null;
}

export type NewStructuresante = Omit<IStructuresante, 'id'> & { id: null };
