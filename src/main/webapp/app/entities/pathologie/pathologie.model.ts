export interface IPathologie {
  id: number;
  nom?: string | null;
}

export type NewPathologie = Omit<IPathologie, 'id'> & { id: null };
