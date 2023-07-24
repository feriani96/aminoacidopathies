import { elienparente } from 'app/entities/enumerations/elienparente.model';

import { ICassuspecte, NewCassuspecte } from './cassuspecte.model';

export const sampleWithRequiredData: ICassuspecte = {
  id: 90725,
};

export const sampleWithPartialData: ICassuspecte = {
  id: 84963,
  lienparente: elienparente['ONCLE_TANTE'],
  troubles_de_la_conscience: true,
  retard_psychomoteur: false,
  retard_mental: false,
  signes_du_spectre_autistique: false,
  epilepsie: true,
  crise_pseudoporphyrique: true,
  autres_signes_neurologiques: 'invoice Tuna Shoes',
  signes_hepatiques: true,
  ballonnement: true,
  syndrome_hemorragique: true,
  autres_signes_hepatiques: 'Steel Coordinator Buckinghamshire',
  signes_osseux: true,
  cerebrale: false,
  autre_manifestations_thrombotiques: 'Garden matrix',
  luxation: true,
  ectopie_cristalinienne: false,
  cataracte: false,
  manifestations_ophtalmologiques_autre: 'Swedish Tools',
  autre_criteres: false,
  str_autres_criteres: 'paradigms',
};

export const sampleWithFullData: ICassuspecte = {
  id: 66033,
  lienparente: elienparente['AUTRE'],
  lienparenteautre: 'Rand Steel',
  signes_neurologiques: true,
  troubles_de_la_conscience: true,
  retard_psychomoteur: false,
  retard_mental: true,
  signes_du_spectre_autistique: false,
  epilepsie: true,
  crise_pseudoporphyrique: false,
  autres_signes_neurologiques: 'redundant monetize',
  signes_hepatiques: false,
  ictere: false,
  ballonnement: true,
  syndrome_hemorragique: false,
  autres_signes_hepatiques: 'indexing',
  signes_osseux: false,
  signes_de_rachitisme: true,
  autre_signes_osseux: 'Republic Response systems',
  manifestations_thrombotiques: true,
  cerebrale: true,
  autre_manifestations_thrombotiques: 'Dynamic',
  manifestations_ophtalmologiques: false,
  luxation: true,
  ectopie_cristalinienne: true,
  cataracte: true,
  glaucome: true,
  myopie: true,
  manifestations_ophtalmologiques_autre: 'calculating',
  autre_criteres: false,
  str_autres_criteres: 'Investment',
  critere_non_precise: false,
};

export const sampleWithNewData: NewCassuspecte = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
