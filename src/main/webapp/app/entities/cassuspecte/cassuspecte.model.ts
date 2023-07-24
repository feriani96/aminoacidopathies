import { elienparente } from 'app/entities/enumerations/elienparente.model';

export interface ICassuspecte {
  id: number;
  lienparente?: elienparente | null;
  lienparenteautre?: string | null;
  signes_neurologiques?: boolean | null;
  troubles_de_la_conscience?: boolean | null;
  retard_psychomoteur?: boolean | null;
  retard_mental?: boolean | null;
  signes_du_spectre_autistique?: boolean | null;
  epilepsie?: boolean | null;
  crise_pseudoporphyrique?: boolean | null;
  autres_signes_neurologiques?: string | null;
  signes_hepatiques?: boolean | null;
  ictere?: boolean | null;
  ballonnement?: boolean | null;
  syndrome_hemorragique?: boolean | null;
  autres_signes_hepatiques?: string | null;
  signes_osseux?: boolean | null;
  signes_de_rachitisme?: boolean | null;
  autre_signes_osseux?: string | null;
  manifestations_thrombotiques?: boolean | null;
  cerebrale?: boolean | null;
  autre_manifestations_thrombotiques?: string | null;
  manifestations_ophtalmologiques?: boolean | null;
  luxation?: boolean | null;
  ectopie_cristalinienne?: boolean | null;
  cataracte?: boolean | null;
  glaucome?: boolean | null;
  myopie?: boolean | null;
  manifestations_ophtalmologiques_autre?: string | null;
  autre_criteres?: boolean | null;
  str_autres_criteres?: string | null;
  critere_non_precise?: boolean | null;
}

export type NewCassuspecte = Omit<ICassuspecte, 'id'> & { id: null };
