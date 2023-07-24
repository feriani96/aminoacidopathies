package tn.mdweb.aminoacidopathies.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.IntegrationTest;
import tn.mdweb.aminoacidopathies.domain.Fiche;
import tn.mdweb.aminoacidopathies.domain.Pathologie;
import tn.mdweb.aminoacidopathies.domain.enumeration.eMoteur;
import tn.mdweb.aminoacidopathies.domain.enumeration.eQI;
import tn.mdweb.aminoacidopathies.domain.enumeration.eactivite;
import tn.mdweb.aminoacidopathies.domain.enumeration.eage_premier_symptome;
import tn.mdweb.aminoacidopathies.domain.enumeration.eagepremiereconsultation;
import tn.mdweb.aminoacidopathies.domain.enumeration.eappareillage;
import tn.mdweb.aminoacidopathies.domain.enumeration.ecasfamiliaux;
import tn.mdweb.aminoacidopathies.domain.enumeration.ecircondecouverte;
import tn.mdweb.aminoacidopathies.domain.enumeration.ecirconstance;
import tn.mdweb.aminoacidopathies.domain.enumeration.econsanguinite;
import tn.mdweb.aminoacidopathies.domain.enumeration.ecouverture;
import tn.mdweb.aminoacidopathies.domain.enumeration.edecesbasage;
import tn.mdweb.aminoacidopathies.domain.enumeration.edeficiencepsychique;
import tn.mdweb.aminoacidopathies.domain.enumeration.edeficiencepsychiqueval;
import tn.mdweb.aminoacidopathies.domain.enumeration.edeficitneuro;
import tn.mdweb.aminoacidopathies.domain.enumeration.edeficitneurosensorielval;
import tn.mdweb.aminoacidopathies.domain.enumeration.edepistage_post_natal_oriente;
import tn.mdweb.aminoacidopathies.domain.enumeration.eechelledepistage;
import tn.mdweb.aminoacidopathies.domain.enumeration.eergotherapie;
import tn.mdweb.aminoacidopathies.domain.enumeration.egouvernorat;
import tn.mdweb.aminoacidopathies.domain.enumeration.egouvernoratmere;
import tn.mdweb.aminoacidopathies.domain.enumeration.egrade;
import tn.mdweb.aminoacidopathies.domain.enumeration.egreffehepatique;
import tn.mdweb.aminoacidopathies.domain.enumeration.ehandicapmental;
import tn.mdweb.aminoacidopathies.domain.enumeration.elienparente1;
import tn.mdweb.aminoacidopathies.domain.enumeration.elienparente2;
import tn.mdweb.aminoacidopathies.domain.enumeration.elieudeces;
import tn.mdweb.aminoacidopathies.domain.enumeration.emedicamentspecifique;
import tn.mdweb.aminoacidopathies.domain.enumeration.emedicamentspecifiqueval;
import tn.mdweb.aminoacidopathies.domain.enumeration.eniveauscolarisation;
import tn.mdweb.aminoacidopathies.domain.enumeration.enouveaux_cas_depistes;
import tn.mdweb.aminoacidopathies.domain.enumeration.epsychologie;
import tn.mdweb.aminoacidopathies.domain.enumeration.erededucationfonctionnelle;
import tn.mdweb.aminoacidopathies.domain.enumeration.eregime;
import tn.mdweb.aminoacidopathies.domain.enumeration.eregimeval;
import tn.mdweb.aminoacidopathies.domain.enumeration.escolarisetype;
import tn.mdweb.aminoacidopathies.domain.enumeration.esexe;
import tn.mdweb.aminoacidopathies.domain.enumeration.estatut;
import tn.mdweb.aminoacidopathies.domain.enumeration.evitamines;
import tn.mdweb.aminoacidopathies.domain.enumeration.evitamineval;
import tn.mdweb.aminoacidopathies.repository.FicheRepository;
import tn.mdweb.aminoacidopathies.service.dto.FicheDTO;
import tn.mdweb.aminoacidopathies.service.mapper.FicheMapper;

/**
 * Integration tests for the {@link FicheResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheResourceIT {

    private static final LocalDate DEFAULT_DATEMAJ = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEMAJ = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TYPE_OBSERVATION = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OBSERVATION = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_REGISTRE = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_REGISTRE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ENREGISTREMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ENREGISTREMENT = LocalDate.now(ZoneId.systemDefault());

    private static final esexe DEFAULT_SEXE = esexe.M;
    private static final esexe UPDATED_SEXE = esexe.F;

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final estatut DEFAULT_STATUT = estatut.ENCORE_SUIVI;
    private static final estatut UPDATED_STATUT = estatut.DECEDE;

    private static final LocalDate DEFAULT_DATE_DECES = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DECES = LocalDate.now(ZoneId.systemDefault());

    private static final ecirconstance DEFAULT_CIRCONSTANCE_DECES = ecirconstance.TBL_NEURO;
    private static final ecirconstance UPDATED_CIRCONSTANCE_DECES = ecirconstance.TBL_HEMORRAGIQUE;

    private static final String DEFAULT_AUTRE_CIRCONSTANCE_DECES = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_CIRCONSTANCE_DECES = "BBBBBBBBBB";

    private static final elieudeces DEFAULT_LIEU_DECES = elieudeces.DOMICILE;
    private static final elieudeces UPDATED_LIEU_DECES = elieudeces.DOMICILE;

    private static final econsanguinite DEFAULT_CONSANGUINITE = econsanguinite.OUI;
    private static final econsanguinite UPDATED_CONSANGUINITE = econsanguinite.NON;

    private static final egouvernorat DEFAULT_ORIGINE_GEO_PERE_GOUVERNORAT = egouvernorat.ARIANA;
    private static final egouvernorat UPDATED_ORIGINE_GEO_PERE_GOUVERNORAT = egouvernorat.BEJA;

    private static final egouvernoratmere DEFAULT_ORIGINE_GEO_MERE_GOUVERNORAT = egouvernoratmere.ARIANA;
    private static final egouvernoratmere UPDATED_ORIGINE_GEO_MERE_GOUVERNORAT = egouvernoratmere.BEJA;

    private static final String DEFAULT_ORIGINE_GEO_PERE_DELEGUATION = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINE_GEO_PERE_DELEGUATION = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINE_GEO_MERE_DELEGUATION = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINE_GEO_MERE_DELEGUATION = "BBBBBBBBBB";

    private static final ecouverture DEFAULT_COUVERTURE_SOCIALE = ecouverture.AUCUNE;
    private static final ecouverture UPDATED_COUVERTURE_SOCIALE = ecouverture.CNAM;

    private static final String DEFAULT_AUTRE_COUVERTURE_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_COUVERTURE_SOCIALE = "BBBBBBBBBB";

    private static final eactivite DEFAULT_ACTIVITE = eactivite.NP;
    private static final eactivite UPDATED_ACTIVITE = eactivite.NON;

    private static final Boolean DEFAULT_BTRAVAIL = false;
    private static final Boolean UPDATED_BTRAVAIL = true;

    private static final String DEFAULT_TRAVAIL = "AAAAAAAAAA";
    private static final String UPDATED_TRAVAIL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SCOLARISE = false;
    private static final Boolean UPDATED_SCOLARISE = true;

    private static final escolarisetype DEFAULT_TYPE_SCOLARISE = escolarisetype.ECOLE_NORMALE;
    private static final escolarisetype UPDATED_TYPE_SCOLARISE = escolarisetype.ECOLE_INTEGRATION;

    private static final eniveauscolarisation DEFAULT_NIVEAU_SCOLARISATION = eniveauscolarisation.PRIMAIRE;
    private static final eniveauscolarisation UPDATED_NIVEAU_SCOLARISATION = eniveauscolarisation.COLLEGE;

    private static final ecasfamiliaux DEFAULT_CAS_FAMILIAUX = ecasfamiliaux.OUI;
    private static final ecasfamiliaux UPDATED_CAS_FAMILIAUX = ecasfamiliaux.NON;

    private static final Integer DEFAULT_NBCASCONFIRME = 1;
    private static final Integer UPDATED_NBCASCONFIRME = 2;

    private static final Integer DEFAULT_NBCASSUSPECTES = 1;
    private static final Integer UPDATED_NBCASSUSPECTES = 2;

    private static final Integer DEFAULT_NBCASDECEDES = 1;
    private static final Integer UPDATED_NBCASDECEDES = 2;

    private static final edecesbasage DEFAULT_DECES_EN_BAS_AGE = edecesbasage.OUI;
    private static final edecesbasage UPDATED_DECES_EN_BAS_AGE = edecesbasage.NON;

    private static final Integer DEFAULT_NBCAS_DECES_AGE_BAS = 1;
    private static final Integer UPDATED_NBCAS_DECES_AGE_BAS = 2;

    private static final ecircondecouverte DEFAULT_CIRCONSTANCES_DECOUVERTE = ecircondecouverte.SYMPTOMATIQUE;
    private static final ecircondecouverte UPDATED_CIRCONSTANCES_DECOUVERTE = ecircondecouverte.DIAGNOSTIC_PRENATAL;

    private static final eage_premier_symptome DEFAULT_AGE_AUX_PREMIERS_SYMPTOMES = eage_premier_symptome.NON_CONNU;
    private static final eage_premier_symptome UPDATED_AGE_AUX_PREMIERS_SYMPTOMES = eage_premier_symptome.CONNU;

    private static final Integer DEFAULT_AN_AGE_PREMIERS_SYMPTOMES = 1;
    private static final Integer UPDATED_AN_AGE_PREMIERS_SYMPTOMES = 2;

    private static final Integer DEFAULT_MOIS_AGE_PREMIERS_SYMPTOMES = 1;
    private static final Integer UPDATED_MOIS_AGE_PREMIERS_SYMPTOMES = 2;

    private static final Integer DEFAULT_JOURS_PREMIERS_SYMPTOMES = 1;
    private static final Integer UPDATED_JOURS_PREMIERS_SYMPTOMES = 2;

    private static final eagepremiereconsultation DEFAULT_AGE_PREMIERE_CONSULTATION = eagepremiereconsultation.NON_CONNU;
    private static final eagepremiereconsultation UPDATED_AGE_PREMIERE_CONSULTATION = eagepremiereconsultation.CONNU;

    private static final Integer DEFAULT_AN_AGE_PREMIERE_CONSULTATION = 1;
    private static final Integer UPDATED_AN_AGE_PREMIERE_CONSULTATION = 2;

    private static final Integer DEFAULT_MOIS_AGE_PREMIERE_CONSULTATION = 1;
    private static final Integer UPDATED_MOIS_AGE_PREMIERE_CONSULTATION = 2;

    private static final Integer DEFAULT_JOURS_PREMIERE_CONSULTATION = 1;
    private static final Integer UPDATED_JOURS_PREMIERE_CONSULTATION = 2;

    private static final LocalDate DEFAULT_DATE_DERNIERE_EVALUATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DERNIERE_EVALUATION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_DIAGNOSTIC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DIAGNOSTIC = LocalDate.now(ZoneId.systemDefault());

    private static final ehandicapmental DEFAULT_HANDICAP_MENTAL = ehandicapmental.NP;
    private static final ehandicapmental UPDATED_HANDICAP_MENTAL = ehandicapmental.OUI;

    private static final eQI DEFAULT_QI = eQI.NON_EVALUE;
    private static final eQI UPDATED_QI = eQI.QI_50_69;

    private static final eMoteur DEFAULT_HANDICAP_MOTEUR = eMoteur.NP;
    private static final eMoteur UPDATED_HANDICAP_MOTEUR = eMoteur.NON;

    private static final egrade DEFAULT_HADICAP_MOTEUR_GRADE = egrade.LEGER;
    private static final egrade UPDATED_HADICAP_MOTEUR_GRADE = egrade.MOYEN;

    private static final edeficitneuro DEFAULT_DEFICIT_NEUROSENSORIEL = edeficitneuro.NON;
    private static final edeficitneuro UPDATED_DEFICIT_NEUROSENSORIEL = edeficitneuro.OUI;

    private static final edeficitneurosensorielval DEFAULT_DEFICIT_NEUROSENSORIEL_VAL = edeficitneurosensorielval.AUDITIF;
    private static final edeficitneurosensorielval UPDATED_DEFICIT_NEUROSENSORIEL_VAL = edeficitneurosensorielval.VISUEL;

    private static final edeficiencepsychique DEFAULT_DEFICIENCE_PSYCHIQUE = edeficiencepsychique.OUI;
    private static final edeficiencepsychique UPDATED_DEFICIENCE_PSYCHIQUE = edeficiencepsychique.NON;

    private static final edeficiencepsychiqueval DEFAULT_DEFICIENCE_PSYCHIQUE_VAL = edeficiencepsychiqueval.TROUBLES_COMMUNICATION_LANGUAGE;
    private static final edeficiencepsychiqueval UPDATED_DEFICIENCE_PSYCHIQUE_VAL = edeficiencepsychiqueval.TROUBLES_COMPORTEMENT;

    private static final String DEFAULT_AUTRE_DEFICIENCE_PSYCHIQUE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_DEFICIENCE_PSYCHIQUE = "BBBBBBBBBB";

    private static final eregime DEFAULT_REGIME = eregime.OUI;
    private static final eregime UPDATED_REGIME = eregime.NON;

    private static final eregimeval DEFAULT_REGIME_VAL = eregimeval.HYPOPROTIDIQUE;
    private static final eregimeval UPDATED_REGIME_VAL = eregimeval.CONTROLE_EN_ACIDE_AMINE;

    private static final emedicamentspecifique DEFAULT_MEDICAMENT_SPECIFIQUE = emedicamentspecifique.OUI;
    private static final emedicamentspecifique UPDATED_MEDICAMENT_SPECIFIQUE = emedicamentspecifique.NON;

    private static final emedicamentspecifiqueval DEFAULT_MEDICAMENT_SPECIFIQUE_VAL = emedicamentspecifiqueval.NTBC_NITISIN_ORFADIN;
    private static final emedicamentspecifiqueval UPDATED_MEDICAMENT_SPECIFIQUE_VAL = emedicamentspecifiqueval.CITRATEDEBETAINE_CYSTADANE;

    private static final String DEFAULT_AUTRE_MEDICAMENT_SPECIFIQUE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_MEDICAMENT_SPECIFIQUE = "BBBBBBBBBB";

    private static final evitamines DEFAULT_VITAMINES = evitamines.OUI;
    private static final evitamines UPDATED_VITAMINES = evitamines.NON;

    private static final evitamineval DEFAULT_VITAMINES_VAL = evitamineval.VITAMINEB1_THIAMINE;
    private static final evitamineval UPDATED_VITAMINES_VAL = evitamineval.VITAMINEB6_PYRIDOXINE;

    private static final egreffehepatique DEFAULT_GREFFEHEPATIQUE = egreffehepatique.OUI;
    private static final egreffehepatique UPDATED_GREFFEHEPATIQUE = egreffehepatique.NON;

    private static final erededucationfonctionnelle DEFAULT_REEDUCATION_FONCTIONNELLE = erededucationfonctionnelle.OUI;
    private static final erededucationfonctionnelle UPDATED_REEDUCATION_FONCTIONNELLE = erededucationfonctionnelle.NON;

    private static final eappareillage DEFAULT_APPAREILLAGE = eappareillage.OUI;
    private static final eappareillage UPDATED_APPAREILLAGE = eappareillage.NON;

    private static final epsychologie DEFAULT_PSUCHOLOGIE = epsychologie.OUI;
    private static final epsychologie UPDATED_PSUCHOLOGIE = epsychologie.NON;

    private static final eergotherapie DEFAULT_ERGOTHERAPIE = eergotherapie.OUI;
    private static final eergotherapie UPDATED_ERGOTHERAPIE = eergotherapie.NON;

    private static final edepistage_post_natal_oriente DEFAULT_DEPISTAGE_POST_NATAL_ORIENTE = edepistage_post_natal_oriente.OUI;
    private static final edepistage_post_natal_oriente UPDATED_DEPISTAGE_POST_NATAL_ORIENTE = edepistage_post_natal_oriente.NON;

    private static final eechelledepistage DEFAULT_ECHELLE_DEPISTAGE = eechelledepistage.FAMILLE_NUCLEAIRE;
    private static final eechelledepistage UPDATED_ECHELLE_DEPISTAGE = eechelledepistage.AU_DELA;

    private static final Integer DEFAULT_NOMBRE_INDIVIDUS_DEPISTES = 1;
    private static final Integer UPDATED_NOMBRE_INDIVIDUS_DEPISTES = 2;

    private static final enouveaux_cas_depistes DEFAULT_NOUVEAUX_CAS_DEPISTES = enouveaux_cas_depistes.OUI;
    private static final enouveaux_cas_depistes UPDATED_NOUVEAUX_CAS_DEPISTES = enouveaux_cas_depistes.NON;

    private static final Integer DEFAULT_NOMBRE_NOUVEAUX_CAS_DEPISTES = 1;
    private static final Integer UPDATED_NOMBRE_NOUVEAUX_CAS_DEPISTES = 2;

    private static final String DEFAULT_CODE_REGISTRE_1_CAS_DEPISTES = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REGISTRE_1_CAS_DEPISTES = "BBBBBBBBBB";

    private static final elienparente1 DEFAULT_LIEN_PARENTE_1_CAS_DEPISTES = elienparente1.FRATERIE;
    private static final elienparente1 UPDATED_LIEN_PARENTE_1_CAS_DEPISTES = elienparente1.COUSINS;

    private static final String DEFAULT_AUTRE_LIEN_PARENTE_1 = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_LIEN_PARENTE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_REGISTRE_2_CAS_DEPISTES = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REGISTRE_2_CAS_DEPISTES = "BBBBBBBBBB";

    private static final elienparente2 DEFAULT_LIEN_PARENTE_2_CAS_DEPISTES = elienparente2.FRATERIE;
    private static final elienparente2 UPDATED_LIEN_PARENTE_2_CAS_DEPISTES = elienparente2.COUSINS;

    private static final String DEFAULT_AUTRE_LIEN_PARENTE_2 = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_LIEN_PARENTE_2 = "BBBBBBBBBB";

    private static final Integer DEFAULT_NOMBRE_DE_GROSSESSE_ULTERIEURES = 1;
    private static final Integer UPDATED_NOMBRE_DE_GROSSESSE_ULTERIEURES = 2;

    private static final Integer DEFAULT_NOMRE_DPN = 1;
    private static final Integer UPDATED_NOMRE_DPN = 2;

    private static final Integer DEFAULT_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES = 1;
    private static final Integer UPDATED_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES = 2;

    private static final Integer DEFAULT_NOMBRE_ITG = 1;
    private static final Integer UPDATED_NOMBRE_ITG = 2;

    private static final Integer DEFAULT_NOMRE_DE_GROSSESSES_POURSUIVIES = 1;
    private static final Integer UPDATED_NOMRE_DE_GROSSESSES_POURSUIVIES = 2;

    private static final Integer DEFAULT_NOMBRE_DE_FOETUS_SAINS = 1;
    private static final Integer UPDATED_NOMBRE_DE_FOETUS_SAINS = 2;

    private static final String ENTITY_API_URL = "/api/fiches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FicheRepository ficheRepository;

    @Autowired
    private FicheMapper ficheMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheMockMvc;

    private Fiche fiche;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fiche createEntity(EntityManager em) {
        Fiche fiche = new Fiche()
            .datemaj(DEFAULT_DATEMAJ)
            .type_observation(DEFAULT_TYPE_OBSERVATION)
            .identifiant_registre(DEFAULT_IDENTIFIANT_REGISTRE)
            .date_enregistrement(DEFAULT_DATE_ENREGISTREMENT)
            .sexe(DEFAULT_SEXE)
            .date_naissance(DEFAULT_DATE_NAISSANCE)
            .statut(DEFAULT_STATUT)
            .date_deces(DEFAULT_DATE_DECES)
            .circonstance_deces(DEFAULT_CIRCONSTANCE_DECES)
            .autre_circonstance_deces(DEFAULT_AUTRE_CIRCONSTANCE_DECES)
            .lieu_deces(DEFAULT_LIEU_DECES)
            .consanguinite(DEFAULT_CONSANGUINITE)
            .origine_geo_pere_gouvernorat(DEFAULT_ORIGINE_GEO_PERE_GOUVERNORAT)
            .origine_geo_mere_gouvernorat(DEFAULT_ORIGINE_GEO_MERE_GOUVERNORAT)
            .origine_geo_pere_deleguation(DEFAULT_ORIGINE_GEO_PERE_DELEGUATION)
            .origine_geo_mere_deleguation(DEFAULT_ORIGINE_GEO_MERE_DELEGUATION)
            .couverture_sociale(DEFAULT_COUVERTURE_SOCIALE)
            .autre_couverture_sociale(DEFAULT_AUTRE_COUVERTURE_SOCIALE)
            .activite(DEFAULT_ACTIVITE)
            .btravail(DEFAULT_BTRAVAIL)
            .travail(DEFAULT_TRAVAIL)
            .scolarise(DEFAULT_SCOLARISE)
            .type_scolarise(DEFAULT_TYPE_SCOLARISE)
            .niveau_scolarisation(DEFAULT_NIVEAU_SCOLARISATION)
            .cas_familiaux(DEFAULT_CAS_FAMILIAUX)
            .nbcasconfirme(DEFAULT_NBCASCONFIRME)
            .nbcassuspectes(DEFAULT_NBCASSUSPECTES)
            .nbcasdecedes(DEFAULT_NBCASDECEDES)
            .deces_en_bas_age(DEFAULT_DECES_EN_BAS_AGE)
            .nbcas_deces_age_bas(DEFAULT_NBCAS_DECES_AGE_BAS)
            .circonstances_decouverte(DEFAULT_CIRCONSTANCES_DECOUVERTE)
            .age_aux_premiers_symptomes(DEFAULT_AGE_AUX_PREMIERS_SYMPTOMES)
            .an_age_premiers_symptomes(DEFAULT_AN_AGE_PREMIERS_SYMPTOMES)
            .mois_age_premiers_symptomes(DEFAULT_MOIS_AGE_PREMIERS_SYMPTOMES)
            .jours_premiers_symptomes(DEFAULT_JOURS_PREMIERS_SYMPTOMES)
            .age_premiere_consultation(DEFAULT_AGE_PREMIERE_CONSULTATION)
            .an_age_premiere_consultation(DEFAULT_AN_AGE_PREMIERE_CONSULTATION)
            .mois_age_premiere_consultation(DEFAULT_MOIS_AGE_PREMIERE_CONSULTATION)
            .jours_premiere_consultation(DEFAULT_JOURS_PREMIERE_CONSULTATION)
            .date_derniere_evaluation(DEFAULT_DATE_DERNIERE_EVALUATION)
            .date_diagnostic(DEFAULT_DATE_DIAGNOSTIC)
            .handicap_mental(DEFAULT_HANDICAP_MENTAL)
            .qi(DEFAULT_QI)
            .handicap_moteur(DEFAULT_HANDICAP_MOTEUR)
            .hadicap_moteur_grade(DEFAULT_HADICAP_MOTEUR_GRADE)
            .deficit_neurosensoriel(DEFAULT_DEFICIT_NEUROSENSORIEL)
            .deficit_neurosensoriel_val(DEFAULT_DEFICIT_NEUROSENSORIEL_VAL)
            .deficience_psychique(DEFAULT_DEFICIENCE_PSYCHIQUE)
            .deficience_psychique_val(DEFAULT_DEFICIENCE_PSYCHIQUE_VAL)
            .autre_deficience_psychique(DEFAULT_AUTRE_DEFICIENCE_PSYCHIQUE)
            .regime(DEFAULT_REGIME)
            .regime_val(DEFAULT_REGIME_VAL)
            .medicament_specifique(DEFAULT_MEDICAMENT_SPECIFIQUE)
            .medicament_specifique_val(DEFAULT_MEDICAMENT_SPECIFIQUE_VAL)
            .autre_medicament_specifique(DEFAULT_AUTRE_MEDICAMENT_SPECIFIQUE)
            .vitamines(DEFAULT_VITAMINES)
            .vitamines_val(DEFAULT_VITAMINES_VAL)
            .greffehepatique(DEFAULT_GREFFEHEPATIQUE)
            .reeducation_fonctionnelle(DEFAULT_REEDUCATION_FONCTIONNELLE)
            .appareillage(DEFAULT_APPAREILLAGE)
            .psuchologie(DEFAULT_PSUCHOLOGIE)
            .ergotherapie(DEFAULT_ERGOTHERAPIE)
            .depistage_post_natal_oriente(DEFAULT_DEPISTAGE_POST_NATAL_ORIENTE)
            .echelle_depistage(DEFAULT_ECHELLE_DEPISTAGE)
            .nombre_individus_depistes(DEFAULT_NOMBRE_INDIVIDUS_DEPISTES)
            .nouveaux_cas_depistes(DEFAULT_NOUVEAUX_CAS_DEPISTES)
            .nombre_nouveaux_cas_depistes(DEFAULT_NOMBRE_NOUVEAUX_CAS_DEPISTES)
            .code_registre1_cas_depistes(DEFAULT_CODE_REGISTRE_1_CAS_DEPISTES)
            .lien_parente1_cas_depistes(DEFAULT_LIEN_PARENTE_1_CAS_DEPISTES)
            .autre_lien_parente1(DEFAULT_AUTRE_LIEN_PARENTE_1)
            .code_registre2_cas_depistes(DEFAULT_CODE_REGISTRE_2_CAS_DEPISTES)
            .lien_parente2_cas_depistes(DEFAULT_LIEN_PARENTE_2_CAS_DEPISTES)
            .autre_lien_parente2(DEFAULT_AUTRE_LIEN_PARENTE_2)
            .nombre_de_grossesse_ulterieures(DEFAULT_NOMBRE_DE_GROSSESSE_ULTERIEURES)
            .nomre_DPN(DEFAULT_NOMRE_DPN)
            .nombre_nouveaux_cas_diagnostiques(DEFAULT_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES)
            .nombre_ITG(DEFAULT_NOMBRE_ITG)
            .nomre_de_grossesses_poursuivies(DEFAULT_NOMRE_DE_GROSSESSES_POURSUIVIES)
            .nombre_de_foetus_sains(DEFAULT_NOMBRE_DE_FOETUS_SAINS);
        // Add required entity
        Pathologie pathologie;
        if (TestUtil.findAll(em, Pathologie.class).isEmpty()) {
            pathologie = PathologieResourceIT.createEntity(em);
            em.persist(pathologie);
            em.flush();
        } else {
            pathologie = TestUtil.findAll(em, Pathologie.class).get(0);
        }
        fiche.setPathologie(pathologie);
        return fiche;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fiche createUpdatedEntity(EntityManager em) {
        Fiche fiche = new Fiche()
            .datemaj(UPDATED_DATEMAJ)
            .type_observation(UPDATED_TYPE_OBSERVATION)
            .identifiant_registre(UPDATED_IDENTIFIANT_REGISTRE)
            .date_enregistrement(UPDATED_DATE_ENREGISTREMENT)
            .sexe(UPDATED_SEXE)
            .date_naissance(UPDATED_DATE_NAISSANCE)
            .statut(UPDATED_STATUT)
            .date_deces(UPDATED_DATE_DECES)
            .circonstance_deces(UPDATED_CIRCONSTANCE_DECES)
            .autre_circonstance_deces(UPDATED_AUTRE_CIRCONSTANCE_DECES)
            .lieu_deces(UPDATED_LIEU_DECES)
            .consanguinite(UPDATED_CONSANGUINITE)
            .origine_geo_pere_gouvernorat(UPDATED_ORIGINE_GEO_PERE_GOUVERNORAT)
            .origine_geo_mere_gouvernorat(UPDATED_ORIGINE_GEO_MERE_GOUVERNORAT)
            .origine_geo_pere_deleguation(UPDATED_ORIGINE_GEO_PERE_DELEGUATION)
            .origine_geo_mere_deleguation(UPDATED_ORIGINE_GEO_MERE_DELEGUATION)
            .couverture_sociale(UPDATED_COUVERTURE_SOCIALE)
            .autre_couverture_sociale(UPDATED_AUTRE_COUVERTURE_SOCIALE)
            .activite(UPDATED_ACTIVITE)
            .btravail(UPDATED_BTRAVAIL)
            .travail(UPDATED_TRAVAIL)
            .scolarise(UPDATED_SCOLARISE)
            .type_scolarise(UPDATED_TYPE_SCOLARISE)
            .niveau_scolarisation(UPDATED_NIVEAU_SCOLARISATION)
            .cas_familiaux(UPDATED_CAS_FAMILIAUX)
            .nbcasconfirme(UPDATED_NBCASCONFIRME)
            .nbcassuspectes(UPDATED_NBCASSUSPECTES)
            .nbcasdecedes(UPDATED_NBCASDECEDES)
            .deces_en_bas_age(UPDATED_DECES_EN_BAS_AGE)
            .nbcas_deces_age_bas(UPDATED_NBCAS_DECES_AGE_BAS)
            .circonstances_decouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .age_aux_premiers_symptomes(UPDATED_AGE_AUX_PREMIERS_SYMPTOMES)
            .an_age_premiers_symptomes(UPDATED_AN_AGE_PREMIERS_SYMPTOMES)
            .mois_age_premiers_symptomes(UPDATED_MOIS_AGE_PREMIERS_SYMPTOMES)
            .jours_premiers_symptomes(UPDATED_JOURS_PREMIERS_SYMPTOMES)
            .age_premiere_consultation(UPDATED_AGE_PREMIERE_CONSULTATION)
            .an_age_premiere_consultation(UPDATED_AN_AGE_PREMIERE_CONSULTATION)
            .mois_age_premiere_consultation(UPDATED_MOIS_AGE_PREMIERE_CONSULTATION)
            .jours_premiere_consultation(UPDATED_JOURS_PREMIERE_CONSULTATION)
            .date_derniere_evaluation(UPDATED_DATE_DERNIERE_EVALUATION)
            .date_diagnostic(UPDATED_DATE_DIAGNOSTIC)
            .handicap_mental(UPDATED_HANDICAP_MENTAL)
            .qi(UPDATED_QI)
            .handicap_moteur(UPDATED_HANDICAP_MOTEUR)
            .hadicap_moteur_grade(UPDATED_HADICAP_MOTEUR_GRADE)
            .deficit_neurosensoriel(UPDATED_DEFICIT_NEUROSENSORIEL)
            .deficit_neurosensoriel_val(UPDATED_DEFICIT_NEUROSENSORIEL_VAL)
            .deficience_psychique(UPDATED_DEFICIENCE_PSYCHIQUE)
            .deficience_psychique_val(UPDATED_DEFICIENCE_PSYCHIQUE_VAL)
            .autre_deficience_psychique(UPDATED_AUTRE_DEFICIENCE_PSYCHIQUE)
            .regime(UPDATED_REGIME)
            .regime_val(UPDATED_REGIME_VAL)
            .medicament_specifique(UPDATED_MEDICAMENT_SPECIFIQUE)
            .medicament_specifique_val(UPDATED_MEDICAMENT_SPECIFIQUE_VAL)
            .autre_medicament_specifique(UPDATED_AUTRE_MEDICAMENT_SPECIFIQUE)
            .vitamines(UPDATED_VITAMINES)
            .vitamines_val(UPDATED_VITAMINES_VAL)
            .greffehepatique(UPDATED_GREFFEHEPATIQUE)
            .reeducation_fonctionnelle(UPDATED_REEDUCATION_FONCTIONNELLE)
            .appareillage(UPDATED_APPAREILLAGE)
            .psuchologie(UPDATED_PSUCHOLOGIE)
            .ergotherapie(UPDATED_ERGOTHERAPIE)
            .depistage_post_natal_oriente(UPDATED_DEPISTAGE_POST_NATAL_ORIENTE)
            .echelle_depistage(UPDATED_ECHELLE_DEPISTAGE)
            .nombre_individus_depistes(UPDATED_NOMBRE_INDIVIDUS_DEPISTES)
            .nouveaux_cas_depistes(UPDATED_NOUVEAUX_CAS_DEPISTES)
            .nombre_nouveaux_cas_depistes(UPDATED_NOMBRE_NOUVEAUX_CAS_DEPISTES)
            .code_registre1_cas_depistes(UPDATED_CODE_REGISTRE_1_CAS_DEPISTES)
            .lien_parente1_cas_depistes(UPDATED_LIEN_PARENTE_1_CAS_DEPISTES)
            .autre_lien_parente1(UPDATED_AUTRE_LIEN_PARENTE_1)
            .code_registre2_cas_depistes(UPDATED_CODE_REGISTRE_2_CAS_DEPISTES)
            .lien_parente2_cas_depistes(UPDATED_LIEN_PARENTE_2_CAS_DEPISTES)
            .autre_lien_parente2(UPDATED_AUTRE_LIEN_PARENTE_2)
            .nombre_de_grossesse_ulterieures(UPDATED_NOMBRE_DE_GROSSESSE_ULTERIEURES)
            .nomre_DPN(UPDATED_NOMRE_DPN)
            .nombre_nouveaux_cas_diagnostiques(UPDATED_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES)
            .nombre_ITG(UPDATED_NOMBRE_ITG)
            .nomre_de_grossesses_poursuivies(UPDATED_NOMRE_DE_GROSSESSES_POURSUIVIES)
            .nombre_de_foetus_sains(UPDATED_NOMBRE_DE_FOETUS_SAINS);
        // Add required entity
        Pathologie pathologie;
        if (TestUtil.findAll(em, Pathologie.class).isEmpty()) {
            pathologie = PathologieResourceIT.createUpdatedEntity(em);
            em.persist(pathologie);
            em.flush();
        } else {
            pathologie = TestUtil.findAll(em, Pathologie.class).get(0);
        }
        fiche.setPathologie(pathologie);
        return fiche;
    }

    @BeforeEach
    public void initTest() {
        fiche = createEntity(em);
    }

    @Test
    @Transactional
    void createFiche() throws Exception {
        int databaseSizeBeforeCreate = ficheRepository.findAll().size();
        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);
        restFicheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ficheDTO)))
            .andExpect(status().isCreated());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeCreate + 1);
        Fiche testFiche = ficheList.get(ficheList.size() - 1);
        assertThat(testFiche.getDatemaj()).isEqualTo(DEFAULT_DATEMAJ);
        assertThat(testFiche.getType_observation()).isEqualTo(DEFAULT_TYPE_OBSERVATION);
        assertThat(testFiche.getIdentifiant_registre()).isEqualTo(DEFAULT_IDENTIFIANT_REGISTRE);
        assertThat(testFiche.getDate_enregistrement()).isEqualTo(DEFAULT_DATE_ENREGISTREMENT);
        assertThat(testFiche.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testFiche.getDate_naissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testFiche.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testFiche.getDate_deces()).isEqualTo(DEFAULT_DATE_DECES);
        assertThat(testFiche.getCirconstance_deces()).isEqualTo(DEFAULT_CIRCONSTANCE_DECES);
        assertThat(testFiche.getAutre_circonstance_deces()).isEqualTo(DEFAULT_AUTRE_CIRCONSTANCE_DECES);
        assertThat(testFiche.getLieu_deces()).isEqualTo(DEFAULT_LIEU_DECES);
        assertThat(testFiche.getConsanguinite()).isEqualTo(DEFAULT_CONSANGUINITE);
        assertThat(testFiche.getOrigine_geo_pere_gouvernorat()).isEqualTo(DEFAULT_ORIGINE_GEO_PERE_GOUVERNORAT);
        assertThat(testFiche.getOrigine_geo_mere_gouvernorat()).isEqualTo(DEFAULT_ORIGINE_GEO_MERE_GOUVERNORAT);
        assertThat(testFiche.getOrigine_geo_pere_deleguation()).isEqualTo(DEFAULT_ORIGINE_GEO_PERE_DELEGUATION);
        assertThat(testFiche.getOrigine_geo_mere_deleguation()).isEqualTo(DEFAULT_ORIGINE_GEO_MERE_DELEGUATION);
        assertThat(testFiche.getCouverture_sociale()).isEqualTo(DEFAULT_COUVERTURE_SOCIALE);
        assertThat(testFiche.getAutre_couverture_sociale()).isEqualTo(DEFAULT_AUTRE_COUVERTURE_SOCIALE);
        assertThat(testFiche.getActivite()).isEqualTo(DEFAULT_ACTIVITE);
        assertThat(testFiche.getBtravail()).isEqualTo(DEFAULT_BTRAVAIL);
        assertThat(testFiche.getTravail()).isEqualTo(DEFAULT_TRAVAIL);
        assertThat(testFiche.getScolarise()).isEqualTo(DEFAULT_SCOLARISE);
        assertThat(testFiche.getType_scolarise()).isEqualTo(DEFAULT_TYPE_SCOLARISE);
        assertThat(testFiche.getNiveau_scolarisation()).isEqualTo(DEFAULT_NIVEAU_SCOLARISATION);
        assertThat(testFiche.getCas_familiaux()).isEqualTo(DEFAULT_CAS_FAMILIAUX);
        assertThat(testFiche.getNbcasconfirme()).isEqualTo(DEFAULT_NBCASCONFIRME);
        assertThat(testFiche.getNbcassuspectes()).isEqualTo(DEFAULT_NBCASSUSPECTES);
        assertThat(testFiche.getNbcasdecedes()).isEqualTo(DEFAULT_NBCASDECEDES);
        assertThat(testFiche.getDeces_en_bas_age()).isEqualTo(DEFAULT_DECES_EN_BAS_AGE);
        assertThat(testFiche.getNbcas_deces_age_bas()).isEqualTo(DEFAULT_NBCAS_DECES_AGE_BAS);
        assertThat(testFiche.getCirconstances_decouverte()).isEqualTo(DEFAULT_CIRCONSTANCES_DECOUVERTE);
        assertThat(testFiche.getAge_aux_premiers_symptomes()).isEqualTo(DEFAULT_AGE_AUX_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getAn_age_premiers_symptomes()).isEqualTo(DEFAULT_AN_AGE_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getMois_age_premiers_symptomes()).isEqualTo(DEFAULT_MOIS_AGE_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getJours_premiers_symptomes()).isEqualTo(DEFAULT_JOURS_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getAge_premiere_consultation()).isEqualTo(DEFAULT_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getAn_age_premiere_consultation()).isEqualTo(DEFAULT_AN_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getMois_age_premiere_consultation()).isEqualTo(DEFAULT_MOIS_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getJours_premiere_consultation()).isEqualTo(DEFAULT_JOURS_PREMIERE_CONSULTATION);
        assertThat(testFiche.getDate_derniere_evaluation()).isEqualTo(DEFAULT_DATE_DERNIERE_EVALUATION);
        assertThat(testFiche.getDate_diagnostic()).isEqualTo(DEFAULT_DATE_DIAGNOSTIC);
        assertThat(testFiche.getHandicap_mental()).isEqualTo(DEFAULT_HANDICAP_MENTAL);
        assertThat(testFiche.getQi()).isEqualTo(DEFAULT_QI);
        assertThat(testFiche.getHandicap_moteur()).isEqualTo(DEFAULT_HANDICAP_MOTEUR);
        assertThat(testFiche.getHadicap_moteur_grade()).isEqualTo(DEFAULT_HADICAP_MOTEUR_GRADE);
        assertThat(testFiche.getDeficit_neurosensoriel()).isEqualTo(DEFAULT_DEFICIT_NEUROSENSORIEL);
        assertThat(testFiche.getDeficit_neurosensoriel_val()).isEqualTo(DEFAULT_DEFICIT_NEUROSENSORIEL_VAL);
        assertThat(testFiche.getDeficience_psychique()).isEqualTo(DEFAULT_DEFICIENCE_PSYCHIQUE);
        assertThat(testFiche.getDeficience_psychique_val()).isEqualTo(DEFAULT_DEFICIENCE_PSYCHIQUE_VAL);
        assertThat(testFiche.getAutre_deficience_psychique()).isEqualTo(DEFAULT_AUTRE_DEFICIENCE_PSYCHIQUE);
        assertThat(testFiche.getRegime()).isEqualTo(DEFAULT_REGIME);
        assertThat(testFiche.getRegime_val()).isEqualTo(DEFAULT_REGIME_VAL);
        assertThat(testFiche.getMedicament_specifique()).isEqualTo(DEFAULT_MEDICAMENT_SPECIFIQUE);
        assertThat(testFiche.getMedicament_specifique_val()).isEqualTo(DEFAULT_MEDICAMENT_SPECIFIQUE_VAL);
        assertThat(testFiche.getAutre_medicament_specifique()).isEqualTo(DEFAULT_AUTRE_MEDICAMENT_SPECIFIQUE);
        assertThat(testFiche.getVitamines()).isEqualTo(DEFAULT_VITAMINES);
        assertThat(testFiche.getVitamines_val()).isEqualTo(DEFAULT_VITAMINES_VAL);
        assertThat(testFiche.getGreffehepatique()).isEqualTo(DEFAULT_GREFFEHEPATIQUE);
        assertThat(testFiche.getReeducation_fonctionnelle()).isEqualTo(DEFAULT_REEDUCATION_FONCTIONNELLE);
        assertThat(testFiche.getAppareillage()).isEqualTo(DEFAULT_APPAREILLAGE);
        assertThat(testFiche.getPsuchologie()).isEqualTo(DEFAULT_PSUCHOLOGIE);
        assertThat(testFiche.getErgotherapie()).isEqualTo(DEFAULT_ERGOTHERAPIE);
        assertThat(testFiche.getDepistage_post_natal_oriente()).isEqualTo(DEFAULT_DEPISTAGE_POST_NATAL_ORIENTE);
        assertThat(testFiche.getEchelle_depistage()).isEqualTo(DEFAULT_ECHELLE_DEPISTAGE);
        assertThat(testFiche.getNombre_individus_depistes()).isEqualTo(DEFAULT_NOMBRE_INDIVIDUS_DEPISTES);
        assertThat(testFiche.getNouveaux_cas_depistes()).isEqualTo(DEFAULT_NOUVEAUX_CAS_DEPISTES);
        assertThat(testFiche.getNombre_nouveaux_cas_depistes()).isEqualTo(DEFAULT_NOMBRE_NOUVEAUX_CAS_DEPISTES);
        assertThat(testFiche.getCode_registre1_cas_depistes()).isEqualTo(DEFAULT_CODE_REGISTRE_1_CAS_DEPISTES);
        assertThat(testFiche.getLien_parente1_cas_depistes()).isEqualTo(DEFAULT_LIEN_PARENTE_1_CAS_DEPISTES);
        assertThat(testFiche.getAutre_lien_parente1()).isEqualTo(DEFAULT_AUTRE_LIEN_PARENTE_1);
        assertThat(testFiche.getCode_registre2_cas_depistes()).isEqualTo(DEFAULT_CODE_REGISTRE_2_CAS_DEPISTES);
        assertThat(testFiche.getLien_parente2_cas_depistes()).isEqualTo(DEFAULT_LIEN_PARENTE_2_CAS_DEPISTES);
        assertThat(testFiche.getAutre_lien_parente2()).isEqualTo(DEFAULT_AUTRE_LIEN_PARENTE_2);
        assertThat(testFiche.getNombre_de_grossesse_ulterieures()).isEqualTo(DEFAULT_NOMBRE_DE_GROSSESSE_ULTERIEURES);
        assertThat(testFiche.getNomre_DPN()).isEqualTo(DEFAULT_NOMRE_DPN);
        assertThat(testFiche.getNombre_nouveaux_cas_diagnostiques()).isEqualTo(DEFAULT_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES);
        assertThat(testFiche.getNombre_ITG()).isEqualTo(DEFAULT_NOMBRE_ITG);
        assertThat(testFiche.getNomre_de_grossesses_poursuivies()).isEqualTo(DEFAULT_NOMRE_DE_GROSSESSES_POURSUIVIES);
        assertThat(testFiche.getNombre_de_foetus_sains()).isEqualTo(DEFAULT_NOMBRE_DE_FOETUS_SAINS);
    }

    @Test
    @Transactional
    void createFicheWithExistingId() throws Exception {
        // Create the Fiche with an existing ID
        fiche.setId(1L);
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        int databaseSizeBeforeCreate = ficheRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ficheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDatemajIsRequired() throws Exception {
        int databaseSizeBeforeTest = ficheRepository.findAll().size();
        // set the field null
        fiche.setDatemaj(null);

        // Create the Fiche, which fails.
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        restFicheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ficheDTO)))
            .andExpect(status().isBadRequest());

        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFiches() throws Exception {
        // Initialize the database
        ficheRepository.saveAndFlush(fiche);

        // Get all the ficheList
        restFicheMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fiche.getId().intValue())))
            .andExpect(jsonPath("$.[*].datemaj").value(hasItem(DEFAULT_DATEMAJ.toString())))
            .andExpect(jsonPath("$.[*].type_observation").value(hasItem(DEFAULT_TYPE_OBSERVATION)))
            .andExpect(jsonPath("$.[*].identifiant_registre").value(hasItem(DEFAULT_IDENTIFIANT_REGISTRE)))
            .andExpect(jsonPath("$.[*].date_enregistrement").value(hasItem(DEFAULT_DATE_ENREGISTREMENT.toString())))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].date_naissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].date_deces").value(hasItem(DEFAULT_DATE_DECES.toString())))
            .andExpect(jsonPath("$.[*].circonstance_deces").value(hasItem(DEFAULT_CIRCONSTANCE_DECES.toString())))
            .andExpect(jsonPath("$.[*].autre_circonstance_deces").value(hasItem(DEFAULT_AUTRE_CIRCONSTANCE_DECES)))
            .andExpect(jsonPath("$.[*].lieu_deces").value(hasItem(DEFAULT_LIEU_DECES.toString())))
            .andExpect(jsonPath("$.[*].consanguinite").value(hasItem(DEFAULT_CONSANGUINITE.toString())))
            .andExpect(jsonPath("$.[*].origine_geo_pere_gouvernorat").value(hasItem(DEFAULT_ORIGINE_GEO_PERE_GOUVERNORAT.toString())))
            .andExpect(jsonPath("$.[*].origine_geo_mere_gouvernorat").value(hasItem(DEFAULT_ORIGINE_GEO_MERE_GOUVERNORAT.toString())))
            .andExpect(jsonPath("$.[*].origine_geo_pere_deleguation").value(hasItem(DEFAULT_ORIGINE_GEO_PERE_DELEGUATION)))
            .andExpect(jsonPath("$.[*].origine_geo_mere_deleguation").value(hasItem(DEFAULT_ORIGINE_GEO_MERE_DELEGUATION)))
            .andExpect(jsonPath("$.[*].couverture_sociale").value(hasItem(DEFAULT_COUVERTURE_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].autre_couverture_sociale").value(hasItem(DEFAULT_AUTRE_COUVERTURE_SOCIALE)))
            .andExpect(jsonPath("$.[*].activite").value(hasItem(DEFAULT_ACTIVITE.toString())))
            .andExpect(jsonPath("$.[*].btravail").value(hasItem(DEFAULT_BTRAVAIL.booleanValue())))
            .andExpect(jsonPath("$.[*].travail").value(hasItem(DEFAULT_TRAVAIL)))
            .andExpect(jsonPath("$.[*].scolarise").value(hasItem(DEFAULT_SCOLARISE.booleanValue())))
            .andExpect(jsonPath("$.[*].type_scolarise").value(hasItem(DEFAULT_TYPE_SCOLARISE.toString())))
            .andExpect(jsonPath("$.[*].niveau_scolarisation").value(hasItem(DEFAULT_NIVEAU_SCOLARISATION.toString())))
            .andExpect(jsonPath("$.[*].cas_familiaux").value(hasItem(DEFAULT_CAS_FAMILIAUX.toString())))
            .andExpect(jsonPath("$.[*].nbcasconfirme").value(hasItem(DEFAULT_NBCASCONFIRME)))
            .andExpect(jsonPath("$.[*].nbcassuspectes").value(hasItem(DEFAULT_NBCASSUSPECTES)))
            .andExpect(jsonPath("$.[*].nbcasdecedes").value(hasItem(DEFAULT_NBCASDECEDES)))
            .andExpect(jsonPath("$.[*].deces_en_bas_age").value(hasItem(DEFAULT_DECES_EN_BAS_AGE.toString())))
            .andExpect(jsonPath("$.[*].nbcas_deces_age_bas").value(hasItem(DEFAULT_NBCAS_DECES_AGE_BAS)))
            .andExpect(jsonPath("$.[*].circonstances_decouverte").value(hasItem(DEFAULT_CIRCONSTANCES_DECOUVERTE.toString())))
            .andExpect(jsonPath("$.[*].age_aux_premiers_symptomes").value(hasItem(DEFAULT_AGE_AUX_PREMIERS_SYMPTOMES.toString())))
            .andExpect(jsonPath("$.[*].an_age_premiers_symptomes").value(hasItem(DEFAULT_AN_AGE_PREMIERS_SYMPTOMES)))
            .andExpect(jsonPath("$.[*].mois_age_premiers_symptomes").value(hasItem(DEFAULT_MOIS_AGE_PREMIERS_SYMPTOMES)))
            .andExpect(jsonPath("$.[*].jours_premiers_symptomes").value(hasItem(DEFAULT_JOURS_PREMIERS_SYMPTOMES)))
            .andExpect(jsonPath("$.[*].age_premiere_consultation").value(hasItem(DEFAULT_AGE_PREMIERE_CONSULTATION.toString())))
            .andExpect(jsonPath("$.[*].an_age_premiere_consultation").value(hasItem(DEFAULT_AN_AGE_PREMIERE_CONSULTATION)))
            .andExpect(jsonPath("$.[*].mois_age_premiere_consultation").value(hasItem(DEFAULT_MOIS_AGE_PREMIERE_CONSULTATION)))
            .andExpect(jsonPath("$.[*].jours_premiere_consultation").value(hasItem(DEFAULT_JOURS_PREMIERE_CONSULTATION)))
            .andExpect(jsonPath("$.[*].date_derniere_evaluation").value(hasItem(DEFAULT_DATE_DERNIERE_EVALUATION.toString())))
            .andExpect(jsonPath("$.[*].date_diagnostic").value(hasItem(DEFAULT_DATE_DIAGNOSTIC.toString())))
            .andExpect(jsonPath("$.[*].handicap_mental").value(hasItem(DEFAULT_HANDICAP_MENTAL.toString())))
            .andExpect(jsonPath("$.[*].qi").value(hasItem(DEFAULT_QI.toString())))
            .andExpect(jsonPath("$.[*].handicap_moteur").value(hasItem(DEFAULT_HANDICAP_MOTEUR.toString())))
            .andExpect(jsonPath("$.[*].hadicap_moteur_grade").value(hasItem(DEFAULT_HADICAP_MOTEUR_GRADE.toString())))
            .andExpect(jsonPath("$.[*].deficit_neurosensoriel").value(hasItem(DEFAULT_DEFICIT_NEUROSENSORIEL.toString())))
            .andExpect(jsonPath("$.[*].deficit_neurosensoriel_val").value(hasItem(DEFAULT_DEFICIT_NEUROSENSORIEL_VAL.toString())))
            .andExpect(jsonPath("$.[*].deficience_psychique").value(hasItem(DEFAULT_DEFICIENCE_PSYCHIQUE.toString())))
            .andExpect(jsonPath("$.[*].deficience_psychique_val").value(hasItem(DEFAULT_DEFICIENCE_PSYCHIQUE_VAL.toString())))
            .andExpect(jsonPath("$.[*].autre_deficience_psychique").value(hasItem(DEFAULT_AUTRE_DEFICIENCE_PSYCHIQUE)))
            .andExpect(jsonPath("$.[*].regime").value(hasItem(DEFAULT_REGIME.toString())))
            .andExpect(jsonPath("$.[*].regime_val").value(hasItem(DEFAULT_REGIME_VAL.toString())))
            .andExpect(jsonPath("$.[*].medicament_specifique").value(hasItem(DEFAULT_MEDICAMENT_SPECIFIQUE.toString())))
            .andExpect(jsonPath("$.[*].medicament_specifique_val").value(hasItem(DEFAULT_MEDICAMENT_SPECIFIQUE_VAL.toString())))
            .andExpect(jsonPath("$.[*].autre_medicament_specifique").value(hasItem(DEFAULT_AUTRE_MEDICAMENT_SPECIFIQUE)))
            .andExpect(jsonPath("$.[*].vitamines").value(hasItem(DEFAULT_VITAMINES.toString())))
            .andExpect(jsonPath("$.[*].vitamines_val").value(hasItem(DEFAULT_VITAMINES_VAL.toString())))
            .andExpect(jsonPath("$.[*].greffehepatique").value(hasItem(DEFAULT_GREFFEHEPATIQUE.toString())))
            .andExpect(jsonPath("$.[*].reeducation_fonctionnelle").value(hasItem(DEFAULT_REEDUCATION_FONCTIONNELLE.toString())))
            .andExpect(jsonPath("$.[*].appareillage").value(hasItem(DEFAULT_APPAREILLAGE.toString())))
            .andExpect(jsonPath("$.[*].psuchologie").value(hasItem(DEFAULT_PSUCHOLOGIE.toString())))
            .andExpect(jsonPath("$.[*].ergotherapie").value(hasItem(DEFAULT_ERGOTHERAPIE.toString())))
            .andExpect(jsonPath("$.[*].depistage_post_natal_oriente").value(hasItem(DEFAULT_DEPISTAGE_POST_NATAL_ORIENTE.toString())))
            .andExpect(jsonPath("$.[*].echelle_depistage").value(hasItem(DEFAULT_ECHELLE_DEPISTAGE.toString())))
            .andExpect(jsonPath("$.[*].nombre_individus_depistes").value(hasItem(DEFAULT_NOMBRE_INDIVIDUS_DEPISTES)))
            .andExpect(jsonPath("$.[*].nouveaux_cas_depistes").value(hasItem(DEFAULT_NOUVEAUX_CAS_DEPISTES.toString())))
            .andExpect(jsonPath("$.[*].nombre_nouveaux_cas_depistes").value(hasItem(DEFAULT_NOMBRE_NOUVEAUX_CAS_DEPISTES)))
            .andExpect(jsonPath("$.[*].code_registre1_cas_depistes").value(hasItem(DEFAULT_CODE_REGISTRE_1_CAS_DEPISTES)))
            .andExpect(jsonPath("$.[*].lien_parente1_cas_depistes").value(hasItem(DEFAULT_LIEN_PARENTE_1_CAS_DEPISTES.toString())))
            .andExpect(jsonPath("$.[*].autre_lien_parente1").value(hasItem(DEFAULT_AUTRE_LIEN_PARENTE_1)))
            .andExpect(jsonPath("$.[*].code_registre2_cas_depistes").value(hasItem(DEFAULT_CODE_REGISTRE_2_CAS_DEPISTES)))
            .andExpect(jsonPath("$.[*].lien_parente2_cas_depistes").value(hasItem(DEFAULT_LIEN_PARENTE_2_CAS_DEPISTES.toString())))
            .andExpect(jsonPath("$.[*].autre_lien_parente2").value(hasItem(DEFAULT_AUTRE_LIEN_PARENTE_2)))
            .andExpect(jsonPath("$.[*].nombre_de_grossesse_ulterieures").value(hasItem(DEFAULT_NOMBRE_DE_GROSSESSE_ULTERIEURES)))
            .andExpect(jsonPath("$.[*].nomre_DPN").value(hasItem(DEFAULT_NOMRE_DPN)))
            .andExpect(jsonPath("$.[*].nombre_nouveaux_cas_diagnostiques").value(hasItem(DEFAULT_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES)))
            .andExpect(jsonPath("$.[*].nombre_ITG").value(hasItem(DEFAULT_NOMBRE_ITG)))
            .andExpect(jsonPath("$.[*].nomre_de_grossesses_poursuivies").value(hasItem(DEFAULT_NOMRE_DE_GROSSESSES_POURSUIVIES)))
            .andExpect(jsonPath("$.[*].nombre_de_foetus_sains").value(hasItem(DEFAULT_NOMBRE_DE_FOETUS_SAINS)));
    }

    @Test
    @Transactional
    void getFiche() throws Exception {
        // Initialize the database
        ficheRepository.saveAndFlush(fiche);

        // Get the fiche
        restFicheMockMvc
            .perform(get(ENTITY_API_URL_ID, fiche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fiche.getId().intValue()))
            .andExpect(jsonPath("$.datemaj").value(DEFAULT_DATEMAJ.toString()))
            .andExpect(jsonPath("$.type_observation").value(DEFAULT_TYPE_OBSERVATION))
            .andExpect(jsonPath("$.identifiant_registre").value(DEFAULT_IDENTIFIANT_REGISTRE))
            .andExpect(jsonPath("$.date_enregistrement").value(DEFAULT_DATE_ENREGISTREMENT.toString()))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.date_naissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
            .andExpect(jsonPath("$.date_deces").value(DEFAULT_DATE_DECES.toString()))
            .andExpect(jsonPath("$.circonstance_deces").value(DEFAULT_CIRCONSTANCE_DECES.toString()))
            .andExpect(jsonPath("$.autre_circonstance_deces").value(DEFAULT_AUTRE_CIRCONSTANCE_DECES))
            .andExpect(jsonPath("$.lieu_deces").value(DEFAULT_LIEU_DECES.toString()))
            .andExpect(jsonPath("$.consanguinite").value(DEFAULT_CONSANGUINITE.toString()))
            .andExpect(jsonPath("$.origine_geo_pere_gouvernorat").value(DEFAULT_ORIGINE_GEO_PERE_GOUVERNORAT.toString()))
            .andExpect(jsonPath("$.origine_geo_mere_gouvernorat").value(DEFAULT_ORIGINE_GEO_MERE_GOUVERNORAT.toString()))
            .andExpect(jsonPath("$.origine_geo_pere_deleguation").value(DEFAULT_ORIGINE_GEO_PERE_DELEGUATION))
            .andExpect(jsonPath("$.origine_geo_mere_deleguation").value(DEFAULT_ORIGINE_GEO_MERE_DELEGUATION))
            .andExpect(jsonPath("$.couverture_sociale").value(DEFAULT_COUVERTURE_SOCIALE.toString()))
            .andExpect(jsonPath("$.autre_couverture_sociale").value(DEFAULT_AUTRE_COUVERTURE_SOCIALE))
            .andExpect(jsonPath("$.activite").value(DEFAULT_ACTIVITE.toString()))
            .andExpect(jsonPath("$.btravail").value(DEFAULT_BTRAVAIL.booleanValue()))
            .andExpect(jsonPath("$.travail").value(DEFAULT_TRAVAIL))
            .andExpect(jsonPath("$.scolarise").value(DEFAULT_SCOLARISE.booleanValue()))
            .andExpect(jsonPath("$.type_scolarise").value(DEFAULT_TYPE_SCOLARISE.toString()))
            .andExpect(jsonPath("$.niveau_scolarisation").value(DEFAULT_NIVEAU_SCOLARISATION.toString()))
            .andExpect(jsonPath("$.cas_familiaux").value(DEFAULT_CAS_FAMILIAUX.toString()))
            .andExpect(jsonPath("$.nbcasconfirme").value(DEFAULT_NBCASCONFIRME))
            .andExpect(jsonPath("$.nbcassuspectes").value(DEFAULT_NBCASSUSPECTES))
            .andExpect(jsonPath("$.nbcasdecedes").value(DEFAULT_NBCASDECEDES))
            .andExpect(jsonPath("$.deces_en_bas_age").value(DEFAULT_DECES_EN_BAS_AGE.toString()))
            .andExpect(jsonPath("$.nbcas_deces_age_bas").value(DEFAULT_NBCAS_DECES_AGE_BAS))
            .andExpect(jsonPath("$.circonstances_decouverte").value(DEFAULT_CIRCONSTANCES_DECOUVERTE.toString()))
            .andExpect(jsonPath("$.age_aux_premiers_symptomes").value(DEFAULT_AGE_AUX_PREMIERS_SYMPTOMES.toString()))
            .andExpect(jsonPath("$.an_age_premiers_symptomes").value(DEFAULT_AN_AGE_PREMIERS_SYMPTOMES))
            .andExpect(jsonPath("$.mois_age_premiers_symptomes").value(DEFAULT_MOIS_AGE_PREMIERS_SYMPTOMES))
            .andExpect(jsonPath("$.jours_premiers_symptomes").value(DEFAULT_JOURS_PREMIERS_SYMPTOMES))
            .andExpect(jsonPath("$.age_premiere_consultation").value(DEFAULT_AGE_PREMIERE_CONSULTATION.toString()))
            .andExpect(jsonPath("$.an_age_premiere_consultation").value(DEFAULT_AN_AGE_PREMIERE_CONSULTATION))
            .andExpect(jsonPath("$.mois_age_premiere_consultation").value(DEFAULT_MOIS_AGE_PREMIERE_CONSULTATION))
            .andExpect(jsonPath("$.jours_premiere_consultation").value(DEFAULT_JOURS_PREMIERE_CONSULTATION))
            .andExpect(jsonPath("$.date_derniere_evaluation").value(DEFAULT_DATE_DERNIERE_EVALUATION.toString()))
            .andExpect(jsonPath("$.date_diagnostic").value(DEFAULT_DATE_DIAGNOSTIC.toString()))
            .andExpect(jsonPath("$.handicap_mental").value(DEFAULT_HANDICAP_MENTAL.toString()))
            .andExpect(jsonPath("$.qi").value(DEFAULT_QI.toString()))
            .andExpect(jsonPath("$.handicap_moteur").value(DEFAULT_HANDICAP_MOTEUR.toString()))
            .andExpect(jsonPath("$.hadicap_moteur_grade").value(DEFAULT_HADICAP_MOTEUR_GRADE.toString()))
            .andExpect(jsonPath("$.deficit_neurosensoriel").value(DEFAULT_DEFICIT_NEUROSENSORIEL.toString()))
            .andExpect(jsonPath("$.deficit_neurosensoriel_val").value(DEFAULT_DEFICIT_NEUROSENSORIEL_VAL.toString()))
            .andExpect(jsonPath("$.deficience_psychique").value(DEFAULT_DEFICIENCE_PSYCHIQUE.toString()))
            .andExpect(jsonPath("$.deficience_psychique_val").value(DEFAULT_DEFICIENCE_PSYCHIQUE_VAL.toString()))
            .andExpect(jsonPath("$.autre_deficience_psychique").value(DEFAULT_AUTRE_DEFICIENCE_PSYCHIQUE))
            .andExpect(jsonPath("$.regime").value(DEFAULT_REGIME.toString()))
            .andExpect(jsonPath("$.regime_val").value(DEFAULT_REGIME_VAL.toString()))
            .andExpect(jsonPath("$.medicament_specifique").value(DEFAULT_MEDICAMENT_SPECIFIQUE.toString()))
            .andExpect(jsonPath("$.medicament_specifique_val").value(DEFAULT_MEDICAMENT_SPECIFIQUE_VAL.toString()))
            .andExpect(jsonPath("$.autre_medicament_specifique").value(DEFAULT_AUTRE_MEDICAMENT_SPECIFIQUE))
            .andExpect(jsonPath("$.vitamines").value(DEFAULT_VITAMINES.toString()))
            .andExpect(jsonPath("$.vitamines_val").value(DEFAULT_VITAMINES_VAL.toString()))
            .andExpect(jsonPath("$.greffehepatique").value(DEFAULT_GREFFEHEPATIQUE.toString()))
            .andExpect(jsonPath("$.reeducation_fonctionnelle").value(DEFAULT_REEDUCATION_FONCTIONNELLE.toString()))
            .andExpect(jsonPath("$.appareillage").value(DEFAULT_APPAREILLAGE.toString()))
            .andExpect(jsonPath("$.psuchologie").value(DEFAULT_PSUCHOLOGIE.toString()))
            .andExpect(jsonPath("$.ergotherapie").value(DEFAULT_ERGOTHERAPIE.toString()))
            .andExpect(jsonPath("$.depistage_post_natal_oriente").value(DEFAULT_DEPISTAGE_POST_NATAL_ORIENTE.toString()))
            .andExpect(jsonPath("$.echelle_depistage").value(DEFAULT_ECHELLE_DEPISTAGE.toString()))
            .andExpect(jsonPath("$.nombre_individus_depistes").value(DEFAULT_NOMBRE_INDIVIDUS_DEPISTES))
            .andExpect(jsonPath("$.nouveaux_cas_depistes").value(DEFAULT_NOUVEAUX_CAS_DEPISTES.toString()))
            .andExpect(jsonPath("$.nombre_nouveaux_cas_depistes").value(DEFAULT_NOMBRE_NOUVEAUX_CAS_DEPISTES))
            .andExpect(jsonPath("$.code_registre1_cas_depistes").value(DEFAULT_CODE_REGISTRE_1_CAS_DEPISTES))
            .andExpect(jsonPath("$.lien_parente1_cas_depistes").value(DEFAULT_LIEN_PARENTE_1_CAS_DEPISTES.toString()))
            .andExpect(jsonPath("$.autre_lien_parente1").value(DEFAULT_AUTRE_LIEN_PARENTE_1))
            .andExpect(jsonPath("$.code_registre2_cas_depistes").value(DEFAULT_CODE_REGISTRE_2_CAS_DEPISTES))
            .andExpect(jsonPath("$.lien_parente2_cas_depistes").value(DEFAULT_LIEN_PARENTE_2_CAS_DEPISTES.toString()))
            .andExpect(jsonPath("$.autre_lien_parente2").value(DEFAULT_AUTRE_LIEN_PARENTE_2))
            .andExpect(jsonPath("$.nombre_de_grossesse_ulterieures").value(DEFAULT_NOMBRE_DE_GROSSESSE_ULTERIEURES))
            .andExpect(jsonPath("$.nomre_DPN").value(DEFAULT_NOMRE_DPN))
            .andExpect(jsonPath("$.nombre_nouveaux_cas_diagnostiques").value(DEFAULT_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES))
            .andExpect(jsonPath("$.nombre_ITG").value(DEFAULT_NOMBRE_ITG))
            .andExpect(jsonPath("$.nomre_de_grossesses_poursuivies").value(DEFAULT_NOMRE_DE_GROSSESSES_POURSUIVIES))
            .andExpect(jsonPath("$.nombre_de_foetus_sains").value(DEFAULT_NOMBRE_DE_FOETUS_SAINS));
    }

    @Test
    @Transactional
    void getNonExistingFiche() throws Exception {
        // Get the fiche
        restFicheMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFiche() throws Exception {
        // Initialize the database
        ficheRepository.saveAndFlush(fiche);

        int databaseSizeBeforeUpdate = ficheRepository.findAll().size();

        // Update the fiche
        Fiche updatedFiche = ficheRepository.findById(fiche.getId()).get();
        // Disconnect from session so that the updates on updatedFiche are not directly saved in db
        em.detach(updatedFiche);
        updatedFiche
            .datemaj(UPDATED_DATEMAJ)
            .type_observation(UPDATED_TYPE_OBSERVATION)
            .identifiant_registre(UPDATED_IDENTIFIANT_REGISTRE)
            .date_enregistrement(UPDATED_DATE_ENREGISTREMENT)
            .sexe(UPDATED_SEXE)
            .date_naissance(UPDATED_DATE_NAISSANCE)
            .statut(UPDATED_STATUT)
            .date_deces(UPDATED_DATE_DECES)
            .circonstance_deces(UPDATED_CIRCONSTANCE_DECES)
            .autre_circonstance_deces(UPDATED_AUTRE_CIRCONSTANCE_DECES)
            .lieu_deces(UPDATED_LIEU_DECES)
            .consanguinite(UPDATED_CONSANGUINITE)
            .origine_geo_pere_gouvernorat(UPDATED_ORIGINE_GEO_PERE_GOUVERNORAT)
            .origine_geo_mere_gouvernorat(UPDATED_ORIGINE_GEO_MERE_GOUVERNORAT)
            .origine_geo_pere_deleguation(UPDATED_ORIGINE_GEO_PERE_DELEGUATION)
            .origine_geo_mere_deleguation(UPDATED_ORIGINE_GEO_MERE_DELEGUATION)
            .couverture_sociale(UPDATED_COUVERTURE_SOCIALE)
            .autre_couverture_sociale(UPDATED_AUTRE_COUVERTURE_SOCIALE)
            .activite(UPDATED_ACTIVITE)
            .btravail(UPDATED_BTRAVAIL)
            .travail(UPDATED_TRAVAIL)
            .scolarise(UPDATED_SCOLARISE)
            .type_scolarise(UPDATED_TYPE_SCOLARISE)
            .niveau_scolarisation(UPDATED_NIVEAU_SCOLARISATION)
            .cas_familiaux(UPDATED_CAS_FAMILIAUX)
            .nbcasconfirme(UPDATED_NBCASCONFIRME)
            .nbcassuspectes(UPDATED_NBCASSUSPECTES)
            .nbcasdecedes(UPDATED_NBCASDECEDES)
            .deces_en_bas_age(UPDATED_DECES_EN_BAS_AGE)
            .nbcas_deces_age_bas(UPDATED_NBCAS_DECES_AGE_BAS)
            .circonstances_decouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .age_aux_premiers_symptomes(UPDATED_AGE_AUX_PREMIERS_SYMPTOMES)
            .an_age_premiers_symptomes(UPDATED_AN_AGE_PREMIERS_SYMPTOMES)
            .mois_age_premiers_symptomes(UPDATED_MOIS_AGE_PREMIERS_SYMPTOMES)
            .jours_premiers_symptomes(UPDATED_JOURS_PREMIERS_SYMPTOMES)
            .age_premiere_consultation(UPDATED_AGE_PREMIERE_CONSULTATION)
            .an_age_premiere_consultation(UPDATED_AN_AGE_PREMIERE_CONSULTATION)
            .mois_age_premiere_consultation(UPDATED_MOIS_AGE_PREMIERE_CONSULTATION)
            .jours_premiere_consultation(UPDATED_JOURS_PREMIERE_CONSULTATION)
            .date_derniere_evaluation(UPDATED_DATE_DERNIERE_EVALUATION)
            .date_diagnostic(UPDATED_DATE_DIAGNOSTIC)
            .handicap_mental(UPDATED_HANDICAP_MENTAL)
            .qi(UPDATED_QI)
            .handicap_moteur(UPDATED_HANDICAP_MOTEUR)
            .hadicap_moteur_grade(UPDATED_HADICAP_MOTEUR_GRADE)
            .deficit_neurosensoriel(UPDATED_DEFICIT_NEUROSENSORIEL)
            .deficit_neurosensoriel_val(UPDATED_DEFICIT_NEUROSENSORIEL_VAL)
            .deficience_psychique(UPDATED_DEFICIENCE_PSYCHIQUE)
            .deficience_psychique_val(UPDATED_DEFICIENCE_PSYCHIQUE_VAL)
            .autre_deficience_psychique(UPDATED_AUTRE_DEFICIENCE_PSYCHIQUE)
            .regime(UPDATED_REGIME)
            .regime_val(UPDATED_REGIME_VAL)
            .medicament_specifique(UPDATED_MEDICAMENT_SPECIFIQUE)
            .medicament_specifique_val(UPDATED_MEDICAMENT_SPECIFIQUE_VAL)
            .autre_medicament_specifique(UPDATED_AUTRE_MEDICAMENT_SPECIFIQUE)
            .vitamines(UPDATED_VITAMINES)
            .vitamines_val(UPDATED_VITAMINES_VAL)
            .greffehepatique(UPDATED_GREFFEHEPATIQUE)
            .reeducation_fonctionnelle(UPDATED_REEDUCATION_FONCTIONNELLE)
            .appareillage(UPDATED_APPAREILLAGE)
            .psuchologie(UPDATED_PSUCHOLOGIE)
            .ergotherapie(UPDATED_ERGOTHERAPIE)
            .depistage_post_natal_oriente(UPDATED_DEPISTAGE_POST_NATAL_ORIENTE)
            .echelle_depistage(UPDATED_ECHELLE_DEPISTAGE)
            .nombre_individus_depistes(UPDATED_NOMBRE_INDIVIDUS_DEPISTES)
            .nouveaux_cas_depistes(UPDATED_NOUVEAUX_CAS_DEPISTES)
            .nombre_nouveaux_cas_depistes(UPDATED_NOMBRE_NOUVEAUX_CAS_DEPISTES)
            .code_registre1_cas_depistes(UPDATED_CODE_REGISTRE_1_CAS_DEPISTES)
            .lien_parente1_cas_depistes(UPDATED_LIEN_PARENTE_1_CAS_DEPISTES)
            .autre_lien_parente1(UPDATED_AUTRE_LIEN_PARENTE_1)
            .code_registre2_cas_depistes(UPDATED_CODE_REGISTRE_2_CAS_DEPISTES)
            .lien_parente2_cas_depistes(UPDATED_LIEN_PARENTE_2_CAS_DEPISTES)
            .autre_lien_parente2(UPDATED_AUTRE_LIEN_PARENTE_2)
            .nombre_de_grossesse_ulterieures(UPDATED_NOMBRE_DE_GROSSESSE_ULTERIEURES)
            .nomre_DPN(UPDATED_NOMRE_DPN)
            .nombre_nouveaux_cas_diagnostiques(UPDATED_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES)
            .nombre_ITG(UPDATED_NOMBRE_ITG)
            .nomre_de_grossesses_poursuivies(UPDATED_NOMRE_DE_GROSSESSES_POURSUIVIES)
            .nombre_de_foetus_sains(UPDATED_NOMBRE_DE_FOETUS_SAINS);
        FicheDTO ficheDTO = ficheMapper.toDto(updatedFiche);

        restFicheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ficheDTO))
            )
            .andExpect(status().isOk());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeUpdate);
        Fiche testFiche = ficheList.get(ficheList.size() - 1);
        assertThat(testFiche.getDatemaj()).isEqualTo(UPDATED_DATEMAJ);
        assertThat(testFiche.getType_observation()).isEqualTo(UPDATED_TYPE_OBSERVATION);
        assertThat(testFiche.getIdentifiant_registre()).isEqualTo(UPDATED_IDENTIFIANT_REGISTRE);
        assertThat(testFiche.getDate_enregistrement()).isEqualTo(UPDATED_DATE_ENREGISTREMENT);
        assertThat(testFiche.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testFiche.getDate_naissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testFiche.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testFiche.getDate_deces()).isEqualTo(UPDATED_DATE_DECES);
        assertThat(testFiche.getCirconstance_deces()).isEqualTo(UPDATED_CIRCONSTANCE_DECES);
        assertThat(testFiche.getAutre_circonstance_deces()).isEqualTo(UPDATED_AUTRE_CIRCONSTANCE_DECES);
        assertThat(testFiche.getLieu_deces()).isEqualTo(UPDATED_LIEU_DECES);
        assertThat(testFiche.getConsanguinite()).isEqualTo(UPDATED_CONSANGUINITE);
        assertThat(testFiche.getOrigine_geo_pere_gouvernorat()).isEqualTo(UPDATED_ORIGINE_GEO_PERE_GOUVERNORAT);
        assertThat(testFiche.getOrigine_geo_mere_gouvernorat()).isEqualTo(UPDATED_ORIGINE_GEO_MERE_GOUVERNORAT);
        assertThat(testFiche.getOrigine_geo_pere_deleguation()).isEqualTo(UPDATED_ORIGINE_GEO_PERE_DELEGUATION);
        assertThat(testFiche.getOrigine_geo_mere_deleguation()).isEqualTo(UPDATED_ORIGINE_GEO_MERE_DELEGUATION);
        assertThat(testFiche.getCouverture_sociale()).isEqualTo(UPDATED_COUVERTURE_SOCIALE);
        assertThat(testFiche.getAutre_couverture_sociale()).isEqualTo(UPDATED_AUTRE_COUVERTURE_SOCIALE);
        assertThat(testFiche.getActivite()).isEqualTo(UPDATED_ACTIVITE);
        assertThat(testFiche.getBtravail()).isEqualTo(UPDATED_BTRAVAIL);
        assertThat(testFiche.getTravail()).isEqualTo(UPDATED_TRAVAIL);
        assertThat(testFiche.getScolarise()).isEqualTo(UPDATED_SCOLARISE);
        assertThat(testFiche.getType_scolarise()).isEqualTo(UPDATED_TYPE_SCOLARISE);
        assertThat(testFiche.getNiveau_scolarisation()).isEqualTo(UPDATED_NIVEAU_SCOLARISATION);
        assertThat(testFiche.getCas_familiaux()).isEqualTo(UPDATED_CAS_FAMILIAUX);
        assertThat(testFiche.getNbcasconfirme()).isEqualTo(UPDATED_NBCASCONFIRME);
        assertThat(testFiche.getNbcassuspectes()).isEqualTo(UPDATED_NBCASSUSPECTES);
        assertThat(testFiche.getNbcasdecedes()).isEqualTo(UPDATED_NBCASDECEDES);
        assertThat(testFiche.getDeces_en_bas_age()).isEqualTo(UPDATED_DECES_EN_BAS_AGE);
        assertThat(testFiche.getNbcas_deces_age_bas()).isEqualTo(UPDATED_NBCAS_DECES_AGE_BAS);
        assertThat(testFiche.getCirconstances_decouverte()).isEqualTo(UPDATED_CIRCONSTANCES_DECOUVERTE);
        assertThat(testFiche.getAge_aux_premiers_symptomes()).isEqualTo(UPDATED_AGE_AUX_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getAn_age_premiers_symptomes()).isEqualTo(UPDATED_AN_AGE_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getMois_age_premiers_symptomes()).isEqualTo(UPDATED_MOIS_AGE_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getJours_premiers_symptomes()).isEqualTo(UPDATED_JOURS_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getAge_premiere_consultation()).isEqualTo(UPDATED_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getAn_age_premiere_consultation()).isEqualTo(UPDATED_AN_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getMois_age_premiere_consultation()).isEqualTo(UPDATED_MOIS_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getJours_premiere_consultation()).isEqualTo(UPDATED_JOURS_PREMIERE_CONSULTATION);
        assertThat(testFiche.getDate_derniere_evaluation()).isEqualTo(UPDATED_DATE_DERNIERE_EVALUATION);
        assertThat(testFiche.getDate_diagnostic()).isEqualTo(UPDATED_DATE_DIAGNOSTIC);
        assertThat(testFiche.getHandicap_mental()).isEqualTo(UPDATED_HANDICAP_MENTAL);
        assertThat(testFiche.getQi()).isEqualTo(UPDATED_QI);
        assertThat(testFiche.getHandicap_moteur()).isEqualTo(UPDATED_HANDICAP_MOTEUR);
        assertThat(testFiche.getHadicap_moteur_grade()).isEqualTo(UPDATED_HADICAP_MOTEUR_GRADE);
        assertThat(testFiche.getDeficit_neurosensoriel()).isEqualTo(UPDATED_DEFICIT_NEUROSENSORIEL);
        assertThat(testFiche.getDeficit_neurosensoriel_val()).isEqualTo(UPDATED_DEFICIT_NEUROSENSORIEL_VAL);
        assertThat(testFiche.getDeficience_psychique()).isEqualTo(UPDATED_DEFICIENCE_PSYCHIQUE);
        assertThat(testFiche.getDeficience_psychique_val()).isEqualTo(UPDATED_DEFICIENCE_PSYCHIQUE_VAL);
        assertThat(testFiche.getAutre_deficience_psychique()).isEqualTo(UPDATED_AUTRE_DEFICIENCE_PSYCHIQUE);
        assertThat(testFiche.getRegime()).isEqualTo(UPDATED_REGIME);
        assertThat(testFiche.getRegime_val()).isEqualTo(UPDATED_REGIME_VAL);
        assertThat(testFiche.getMedicament_specifique()).isEqualTo(UPDATED_MEDICAMENT_SPECIFIQUE);
        assertThat(testFiche.getMedicament_specifique_val()).isEqualTo(UPDATED_MEDICAMENT_SPECIFIQUE_VAL);
        assertThat(testFiche.getAutre_medicament_specifique()).isEqualTo(UPDATED_AUTRE_MEDICAMENT_SPECIFIQUE);
        assertThat(testFiche.getVitamines()).isEqualTo(UPDATED_VITAMINES);
        assertThat(testFiche.getVitamines_val()).isEqualTo(UPDATED_VITAMINES_VAL);
        assertThat(testFiche.getGreffehepatique()).isEqualTo(UPDATED_GREFFEHEPATIQUE);
        assertThat(testFiche.getReeducation_fonctionnelle()).isEqualTo(UPDATED_REEDUCATION_FONCTIONNELLE);
        assertThat(testFiche.getAppareillage()).isEqualTo(UPDATED_APPAREILLAGE);
        assertThat(testFiche.getPsuchologie()).isEqualTo(UPDATED_PSUCHOLOGIE);
        assertThat(testFiche.getErgotherapie()).isEqualTo(UPDATED_ERGOTHERAPIE);
        assertThat(testFiche.getDepistage_post_natal_oriente()).isEqualTo(UPDATED_DEPISTAGE_POST_NATAL_ORIENTE);
        assertThat(testFiche.getEchelle_depistage()).isEqualTo(UPDATED_ECHELLE_DEPISTAGE);
        assertThat(testFiche.getNombre_individus_depistes()).isEqualTo(UPDATED_NOMBRE_INDIVIDUS_DEPISTES);
        assertThat(testFiche.getNouveaux_cas_depistes()).isEqualTo(UPDATED_NOUVEAUX_CAS_DEPISTES);
        assertThat(testFiche.getNombre_nouveaux_cas_depistes()).isEqualTo(UPDATED_NOMBRE_NOUVEAUX_CAS_DEPISTES);
        assertThat(testFiche.getCode_registre1_cas_depistes()).isEqualTo(UPDATED_CODE_REGISTRE_1_CAS_DEPISTES);
        assertThat(testFiche.getLien_parente1_cas_depistes()).isEqualTo(UPDATED_LIEN_PARENTE_1_CAS_DEPISTES);
        assertThat(testFiche.getAutre_lien_parente1()).isEqualTo(UPDATED_AUTRE_LIEN_PARENTE_1);
        assertThat(testFiche.getCode_registre2_cas_depistes()).isEqualTo(UPDATED_CODE_REGISTRE_2_CAS_DEPISTES);
        assertThat(testFiche.getLien_parente2_cas_depistes()).isEqualTo(UPDATED_LIEN_PARENTE_2_CAS_DEPISTES);
        assertThat(testFiche.getAutre_lien_parente2()).isEqualTo(UPDATED_AUTRE_LIEN_PARENTE_2);
        assertThat(testFiche.getNombre_de_grossesse_ulterieures()).isEqualTo(UPDATED_NOMBRE_DE_GROSSESSE_ULTERIEURES);
        assertThat(testFiche.getNomre_DPN()).isEqualTo(UPDATED_NOMRE_DPN);
        assertThat(testFiche.getNombre_nouveaux_cas_diagnostiques()).isEqualTo(UPDATED_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES);
        assertThat(testFiche.getNombre_ITG()).isEqualTo(UPDATED_NOMBRE_ITG);
        assertThat(testFiche.getNomre_de_grossesses_poursuivies()).isEqualTo(UPDATED_NOMRE_DE_GROSSESSES_POURSUIVIES);
        assertThat(testFiche.getNombre_de_foetus_sains()).isEqualTo(UPDATED_NOMBRE_DE_FOETUS_SAINS);
    }

    @Test
    @Transactional
    void putNonExistingFiche() throws Exception {
        int databaseSizeBeforeUpdate = ficheRepository.findAll().size();
        fiche.setId(count.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFiche() throws Exception {
        int databaseSizeBeforeUpdate = ficheRepository.findAll().size();
        fiche.setId(count.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFiche() throws Exception {
        int databaseSizeBeforeUpdate = ficheRepository.findAll().size();
        fiche.setId(count.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ficheDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFicheWithPatch() throws Exception {
        // Initialize the database
        ficheRepository.saveAndFlush(fiche);

        int databaseSizeBeforeUpdate = ficheRepository.findAll().size();

        // Update the fiche using partial update
        Fiche partialUpdatedFiche = new Fiche();
        partialUpdatedFiche.setId(fiche.getId());

        partialUpdatedFiche
            .identifiant_registre(UPDATED_IDENTIFIANT_REGISTRE)
            .date_enregistrement(UPDATED_DATE_ENREGISTREMENT)
            .sexe(UPDATED_SEXE)
            .date_naissance(UPDATED_DATE_NAISSANCE)
            .statut(UPDATED_STATUT)
            .circonstance_deces(UPDATED_CIRCONSTANCE_DECES)
            .autre_circonstance_deces(UPDATED_AUTRE_CIRCONSTANCE_DECES)
            .consanguinite(UPDATED_CONSANGUINITE)
            .origine_geo_pere_gouvernorat(UPDATED_ORIGINE_GEO_PERE_GOUVERNORAT)
            .origine_geo_mere_gouvernorat(UPDATED_ORIGINE_GEO_MERE_GOUVERNORAT)
            .btravail(UPDATED_BTRAVAIL)
            .travail(UPDATED_TRAVAIL)
            .type_scolarise(UPDATED_TYPE_SCOLARISE)
            .niveau_scolarisation(UPDATED_NIVEAU_SCOLARISATION)
            .cas_familiaux(UPDATED_CAS_FAMILIAUX)
            .nbcassuspectes(UPDATED_NBCASSUSPECTES)
            .nbcasdecedes(UPDATED_NBCASDECEDES)
            .age_aux_premiers_symptomes(UPDATED_AGE_AUX_PREMIERS_SYMPTOMES)
            .mois_age_premiers_symptomes(UPDATED_MOIS_AGE_PREMIERS_SYMPTOMES)
            .jours_premiers_symptomes(UPDATED_JOURS_PREMIERS_SYMPTOMES)
            .age_premiere_consultation(UPDATED_AGE_PREMIERE_CONSULTATION)
            .date_diagnostic(UPDATED_DATE_DIAGNOSTIC)
            .qi(UPDATED_QI)
            .handicap_moteur(UPDATED_HANDICAP_MOTEUR)
            .hadicap_moteur_grade(UPDATED_HADICAP_MOTEUR_GRADE)
            .deficit_neurosensoriel(UPDATED_DEFICIT_NEUROSENSORIEL)
            .deficit_neurosensoriel_val(UPDATED_DEFICIT_NEUROSENSORIEL_VAL)
            .deficience_psychique(UPDATED_DEFICIENCE_PSYCHIQUE)
            .autre_deficience_psychique(UPDATED_AUTRE_DEFICIENCE_PSYCHIQUE)
            .regime_val(UPDATED_REGIME_VAL)
            .autre_medicament_specifique(UPDATED_AUTRE_MEDICAMENT_SPECIFIQUE)
            .vitamines_val(UPDATED_VITAMINES_VAL)
            .greffehepatique(UPDATED_GREFFEHEPATIQUE)
            .reeducation_fonctionnelle(UPDATED_REEDUCATION_FONCTIONNELLE)
            .psuchologie(UPDATED_PSUCHOLOGIE)
            .ergotherapie(UPDATED_ERGOTHERAPIE)
            .nombre_individus_depistes(UPDATED_NOMBRE_INDIVIDUS_DEPISTES)
            .nouveaux_cas_depistes(UPDATED_NOUVEAUX_CAS_DEPISTES)
            .code_registre1_cas_depistes(UPDATED_CODE_REGISTRE_1_CAS_DEPISTES)
            .lien_parente1_cas_depistes(UPDATED_LIEN_PARENTE_1_CAS_DEPISTES)
            .code_registre2_cas_depistes(UPDATED_CODE_REGISTRE_2_CAS_DEPISTES)
            .lien_parente2_cas_depistes(UPDATED_LIEN_PARENTE_2_CAS_DEPISTES)
            .autre_lien_parente2(UPDATED_AUTRE_LIEN_PARENTE_2)
            .nombre_de_grossesse_ulterieures(UPDATED_NOMBRE_DE_GROSSESSE_ULTERIEURES)
            .nombre_nouveaux_cas_diagnostiques(UPDATED_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES);

        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiche.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFiche))
            )
            .andExpect(status().isOk());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeUpdate);
        Fiche testFiche = ficheList.get(ficheList.size() - 1);
        assertThat(testFiche.getDatemaj()).isEqualTo(DEFAULT_DATEMAJ);
        assertThat(testFiche.getType_observation()).isEqualTo(DEFAULT_TYPE_OBSERVATION);
        assertThat(testFiche.getIdentifiant_registre()).isEqualTo(UPDATED_IDENTIFIANT_REGISTRE);
        assertThat(testFiche.getDate_enregistrement()).isEqualTo(UPDATED_DATE_ENREGISTREMENT);
        assertThat(testFiche.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testFiche.getDate_naissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testFiche.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testFiche.getDate_deces()).isEqualTo(DEFAULT_DATE_DECES);
        assertThat(testFiche.getCirconstance_deces()).isEqualTo(UPDATED_CIRCONSTANCE_DECES);
        assertThat(testFiche.getAutre_circonstance_deces()).isEqualTo(UPDATED_AUTRE_CIRCONSTANCE_DECES);
        assertThat(testFiche.getLieu_deces()).isEqualTo(DEFAULT_LIEU_DECES);
        assertThat(testFiche.getConsanguinite()).isEqualTo(UPDATED_CONSANGUINITE);
        assertThat(testFiche.getOrigine_geo_pere_gouvernorat()).isEqualTo(UPDATED_ORIGINE_GEO_PERE_GOUVERNORAT);
        assertThat(testFiche.getOrigine_geo_mere_gouvernorat()).isEqualTo(UPDATED_ORIGINE_GEO_MERE_GOUVERNORAT);
        assertThat(testFiche.getOrigine_geo_pere_deleguation()).isEqualTo(DEFAULT_ORIGINE_GEO_PERE_DELEGUATION);
        assertThat(testFiche.getOrigine_geo_mere_deleguation()).isEqualTo(DEFAULT_ORIGINE_GEO_MERE_DELEGUATION);
        assertThat(testFiche.getCouverture_sociale()).isEqualTo(DEFAULT_COUVERTURE_SOCIALE);
        assertThat(testFiche.getAutre_couverture_sociale()).isEqualTo(DEFAULT_AUTRE_COUVERTURE_SOCIALE);
        assertThat(testFiche.getActivite()).isEqualTo(DEFAULT_ACTIVITE);
        assertThat(testFiche.getBtravail()).isEqualTo(UPDATED_BTRAVAIL);
        assertThat(testFiche.getTravail()).isEqualTo(UPDATED_TRAVAIL);
        assertThat(testFiche.getScolarise()).isEqualTo(DEFAULT_SCOLARISE);
        assertThat(testFiche.getType_scolarise()).isEqualTo(UPDATED_TYPE_SCOLARISE);
        assertThat(testFiche.getNiveau_scolarisation()).isEqualTo(UPDATED_NIVEAU_SCOLARISATION);
        assertThat(testFiche.getCas_familiaux()).isEqualTo(UPDATED_CAS_FAMILIAUX);
        assertThat(testFiche.getNbcasconfirme()).isEqualTo(DEFAULT_NBCASCONFIRME);
        assertThat(testFiche.getNbcassuspectes()).isEqualTo(UPDATED_NBCASSUSPECTES);
        assertThat(testFiche.getNbcasdecedes()).isEqualTo(UPDATED_NBCASDECEDES);
        assertThat(testFiche.getDeces_en_bas_age()).isEqualTo(DEFAULT_DECES_EN_BAS_AGE);
        assertThat(testFiche.getNbcas_deces_age_bas()).isEqualTo(DEFAULT_NBCAS_DECES_AGE_BAS);
        assertThat(testFiche.getCirconstances_decouverte()).isEqualTo(DEFAULT_CIRCONSTANCES_DECOUVERTE);
        assertThat(testFiche.getAge_aux_premiers_symptomes()).isEqualTo(UPDATED_AGE_AUX_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getAn_age_premiers_symptomes()).isEqualTo(DEFAULT_AN_AGE_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getMois_age_premiers_symptomes()).isEqualTo(UPDATED_MOIS_AGE_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getJours_premiers_symptomes()).isEqualTo(UPDATED_JOURS_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getAge_premiere_consultation()).isEqualTo(UPDATED_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getAn_age_premiere_consultation()).isEqualTo(DEFAULT_AN_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getMois_age_premiere_consultation()).isEqualTo(DEFAULT_MOIS_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getJours_premiere_consultation()).isEqualTo(DEFAULT_JOURS_PREMIERE_CONSULTATION);
        assertThat(testFiche.getDate_derniere_evaluation()).isEqualTo(DEFAULT_DATE_DERNIERE_EVALUATION);
        assertThat(testFiche.getDate_diagnostic()).isEqualTo(UPDATED_DATE_DIAGNOSTIC);
        assertThat(testFiche.getHandicap_mental()).isEqualTo(DEFAULT_HANDICAP_MENTAL);
        assertThat(testFiche.getQi()).isEqualTo(UPDATED_QI);
        assertThat(testFiche.getHandicap_moteur()).isEqualTo(UPDATED_HANDICAP_MOTEUR);
        assertThat(testFiche.getHadicap_moteur_grade()).isEqualTo(UPDATED_HADICAP_MOTEUR_GRADE);
        assertThat(testFiche.getDeficit_neurosensoriel()).isEqualTo(UPDATED_DEFICIT_NEUROSENSORIEL);
        assertThat(testFiche.getDeficit_neurosensoriel_val()).isEqualTo(UPDATED_DEFICIT_NEUROSENSORIEL_VAL);
        assertThat(testFiche.getDeficience_psychique()).isEqualTo(UPDATED_DEFICIENCE_PSYCHIQUE);
        assertThat(testFiche.getDeficience_psychique_val()).isEqualTo(DEFAULT_DEFICIENCE_PSYCHIQUE_VAL);
        assertThat(testFiche.getAutre_deficience_psychique()).isEqualTo(UPDATED_AUTRE_DEFICIENCE_PSYCHIQUE);
        assertThat(testFiche.getRegime()).isEqualTo(DEFAULT_REGIME);
        assertThat(testFiche.getRegime_val()).isEqualTo(UPDATED_REGIME_VAL);
        assertThat(testFiche.getMedicament_specifique()).isEqualTo(DEFAULT_MEDICAMENT_SPECIFIQUE);
        assertThat(testFiche.getMedicament_specifique_val()).isEqualTo(DEFAULT_MEDICAMENT_SPECIFIQUE_VAL);
        assertThat(testFiche.getAutre_medicament_specifique()).isEqualTo(UPDATED_AUTRE_MEDICAMENT_SPECIFIQUE);
        assertThat(testFiche.getVitamines()).isEqualTo(DEFAULT_VITAMINES);
        assertThat(testFiche.getVitamines_val()).isEqualTo(UPDATED_VITAMINES_VAL);
        assertThat(testFiche.getGreffehepatique()).isEqualTo(UPDATED_GREFFEHEPATIQUE);
        assertThat(testFiche.getReeducation_fonctionnelle()).isEqualTo(UPDATED_REEDUCATION_FONCTIONNELLE);
        assertThat(testFiche.getAppareillage()).isEqualTo(DEFAULT_APPAREILLAGE);
        assertThat(testFiche.getPsuchologie()).isEqualTo(UPDATED_PSUCHOLOGIE);
        assertThat(testFiche.getErgotherapie()).isEqualTo(UPDATED_ERGOTHERAPIE);
        assertThat(testFiche.getDepistage_post_natal_oriente()).isEqualTo(DEFAULT_DEPISTAGE_POST_NATAL_ORIENTE);
        assertThat(testFiche.getEchelle_depistage()).isEqualTo(DEFAULT_ECHELLE_DEPISTAGE);
        assertThat(testFiche.getNombre_individus_depistes()).isEqualTo(UPDATED_NOMBRE_INDIVIDUS_DEPISTES);
        assertThat(testFiche.getNouveaux_cas_depistes()).isEqualTo(UPDATED_NOUVEAUX_CAS_DEPISTES);
        assertThat(testFiche.getNombre_nouveaux_cas_depistes()).isEqualTo(DEFAULT_NOMBRE_NOUVEAUX_CAS_DEPISTES);
        assertThat(testFiche.getCode_registre1_cas_depistes()).isEqualTo(UPDATED_CODE_REGISTRE_1_CAS_DEPISTES);
        assertThat(testFiche.getLien_parente1_cas_depistes()).isEqualTo(UPDATED_LIEN_PARENTE_1_CAS_DEPISTES);
        assertThat(testFiche.getAutre_lien_parente1()).isEqualTo(DEFAULT_AUTRE_LIEN_PARENTE_1);
        assertThat(testFiche.getCode_registre2_cas_depistes()).isEqualTo(UPDATED_CODE_REGISTRE_2_CAS_DEPISTES);
        assertThat(testFiche.getLien_parente2_cas_depistes()).isEqualTo(UPDATED_LIEN_PARENTE_2_CAS_DEPISTES);
        assertThat(testFiche.getAutre_lien_parente2()).isEqualTo(UPDATED_AUTRE_LIEN_PARENTE_2);
        assertThat(testFiche.getNombre_de_grossesse_ulterieures()).isEqualTo(UPDATED_NOMBRE_DE_GROSSESSE_ULTERIEURES);
        assertThat(testFiche.getNomre_DPN()).isEqualTo(DEFAULT_NOMRE_DPN);
        assertThat(testFiche.getNombre_nouveaux_cas_diagnostiques()).isEqualTo(UPDATED_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES);
        assertThat(testFiche.getNombre_ITG()).isEqualTo(DEFAULT_NOMBRE_ITG);
        assertThat(testFiche.getNomre_de_grossesses_poursuivies()).isEqualTo(DEFAULT_NOMRE_DE_GROSSESSES_POURSUIVIES);
        assertThat(testFiche.getNombre_de_foetus_sains()).isEqualTo(DEFAULT_NOMBRE_DE_FOETUS_SAINS);
    }

    @Test
    @Transactional
    void fullUpdateFicheWithPatch() throws Exception {
        // Initialize the database
        ficheRepository.saveAndFlush(fiche);

        int databaseSizeBeforeUpdate = ficheRepository.findAll().size();

        // Update the fiche using partial update
        Fiche partialUpdatedFiche = new Fiche();
        partialUpdatedFiche.setId(fiche.getId());

        partialUpdatedFiche
            .datemaj(UPDATED_DATEMAJ)
            .type_observation(UPDATED_TYPE_OBSERVATION)
            .identifiant_registre(UPDATED_IDENTIFIANT_REGISTRE)
            .date_enregistrement(UPDATED_DATE_ENREGISTREMENT)
            .sexe(UPDATED_SEXE)
            .date_naissance(UPDATED_DATE_NAISSANCE)
            .statut(UPDATED_STATUT)
            .date_deces(UPDATED_DATE_DECES)
            .circonstance_deces(UPDATED_CIRCONSTANCE_DECES)
            .autre_circonstance_deces(UPDATED_AUTRE_CIRCONSTANCE_DECES)
            .lieu_deces(UPDATED_LIEU_DECES)
            .consanguinite(UPDATED_CONSANGUINITE)
            .origine_geo_pere_gouvernorat(UPDATED_ORIGINE_GEO_PERE_GOUVERNORAT)
            .origine_geo_mere_gouvernorat(UPDATED_ORIGINE_GEO_MERE_GOUVERNORAT)
            .origine_geo_pere_deleguation(UPDATED_ORIGINE_GEO_PERE_DELEGUATION)
            .origine_geo_mere_deleguation(UPDATED_ORIGINE_GEO_MERE_DELEGUATION)
            .couverture_sociale(UPDATED_COUVERTURE_SOCIALE)
            .autre_couverture_sociale(UPDATED_AUTRE_COUVERTURE_SOCIALE)
            .activite(UPDATED_ACTIVITE)
            .btravail(UPDATED_BTRAVAIL)
            .travail(UPDATED_TRAVAIL)
            .scolarise(UPDATED_SCOLARISE)
            .type_scolarise(UPDATED_TYPE_SCOLARISE)
            .niveau_scolarisation(UPDATED_NIVEAU_SCOLARISATION)
            .cas_familiaux(UPDATED_CAS_FAMILIAUX)
            .nbcasconfirme(UPDATED_NBCASCONFIRME)
            .nbcassuspectes(UPDATED_NBCASSUSPECTES)
            .nbcasdecedes(UPDATED_NBCASDECEDES)
            .deces_en_bas_age(UPDATED_DECES_EN_BAS_AGE)
            .nbcas_deces_age_bas(UPDATED_NBCAS_DECES_AGE_BAS)
            .circonstances_decouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .age_aux_premiers_symptomes(UPDATED_AGE_AUX_PREMIERS_SYMPTOMES)
            .an_age_premiers_symptomes(UPDATED_AN_AGE_PREMIERS_SYMPTOMES)
            .mois_age_premiers_symptomes(UPDATED_MOIS_AGE_PREMIERS_SYMPTOMES)
            .jours_premiers_symptomes(UPDATED_JOURS_PREMIERS_SYMPTOMES)
            .age_premiere_consultation(UPDATED_AGE_PREMIERE_CONSULTATION)
            .an_age_premiere_consultation(UPDATED_AN_AGE_PREMIERE_CONSULTATION)
            .mois_age_premiere_consultation(UPDATED_MOIS_AGE_PREMIERE_CONSULTATION)
            .jours_premiere_consultation(UPDATED_JOURS_PREMIERE_CONSULTATION)
            .date_derniere_evaluation(UPDATED_DATE_DERNIERE_EVALUATION)
            .date_diagnostic(UPDATED_DATE_DIAGNOSTIC)
            .handicap_mental(UPDATED_HANDICAP_MENTAL)
            .qi(UPDATED_QI)
            .handicap_moteur(UPDATED_HANDICAP_MOTEUR)
            .hadicap_moteur_grade(UPDATED_HADICAP_MOTEUR_GRADE)
            .deficit_neurosensoriel(UPDATED_DEFICIT_NEUROSENSORIEL)
            .deficit_neurosensoriel_val(UPDATED_DEFICIT_NEUROSENSORIEL_VAL)
            .deficience_psychique(UPDATED_DEFICIENCE_PSYCHIQUE)
            .deficience_psychique_val(UPDATED_DEFICIENCE_PSYCHIQUE_VAL)
            .autre_deficience_psychique(UPDATED_AUTRE_DEFICIENCE_PSYCHIQUE)
            .regime(UPDATED_REGIME)
            .regime_val(UPDATED_REGIME_VAL)
            .medicament_specifique(UPDATED_MEDICAMENT_SPECIFIQUE)
            .medicament_specifique_val(UPDATED_MEDICAMENT_SPECIFIQUE_VAL)
            .autre_medicament_specifique(UPDATED_AUTRE_MEDICAMENT_SPECIFIQUE)
            .vitamines(UPDATED_VITAMINES)
            .vitamines_val(UPDATED_VITAMINES_VAL)
            .greffehepatique(UPDATED_GREFFEHEPATIQUE)
            .reeducation_fonctionnelle(UPDATED_REEDUCATION_FONCTIONNELLE)
            .appareillage(UPDATED_APPAREILLAGE)
            .psuchologie(UPDATED_PSUCHOLOGIE)
            .ergotherapie(UPDATED_ERGOTHERAPIE)
            .depistage_post_natal_oriente(UPDATED_DEPISTAGE_POST_NATAL_ORIENTE)
            .echelle_depistage(UPDATED_ECHELLE_DEPISTAGE)
            .nombre_individus_depistes(UPDATED_NOMBRE_INDIVIDUS_DEPISTES)
            .nouveaux_cas_depistes(UPDATED_NOUVEAUX_CAS_DEPISTES)
            .nombre_nouveaux_cas_depistes(UPDATED_NOMBRE_NOUVEAUX_CAS_DEPISTES)
            .code_registre1_cas_depistes(UPDATED_CODE_REGISTRE_1_CAS_DEPISTES)
            .lien_parente1_cas_depistes(UPDATED_LIEN_PARENTE_1_CAS_DEPISTES)
            .autre_lien_parente1(UPDATED_AUTRE_LIEN_PARENTE_1)
            .code_registre2_cas_depistes(UPDATED_CODE_REGISTRE_2_CAS_DEPISTES)
            .lien_parente2_cas_depistes(UPDATED_LIEN_PARENTE_2_CAS_DEPISTES)
            .autre_lien_parente2(UPDATED_AUTRE_LIEN_PARENTE_2)
            .nombre_de_grossesse_ulterieures(UPDATED_NOMBRE_DE_GROSSESSE_ULTERIEURES)
            .nomre_DPN(UPDATED_NOMRE_DPN)
            .nombre_nouveaux_cas_diagnostiques(UPDATED_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES)
            .nombre_ITG(UPDATED_NOMBRE_ITG)
            .nomre_de_grossesses_poursuivies(UPDATED_NOMRE_DE_GROSSESSES_POURSUIVIES)
            .nombre_de_foetus_sains(UPDATED_NOMBRE_DE_FOETUS_SAINS);

        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiche.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFiche))
            )
            .andExpect(status().isOk());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeUpdate);
        Fiche testFiche = ficheList.get(ficheList.size() - 1);
        assertThat(testFiche.getDatemaj()).isEqualTo(UPDATED_DATEMAJ);
        assertThat(testFiche.getType_observation()).isEqualTo(UPDATED_TYPE_OBSERVATION);
        assertThat(testFiche.getIdentifiant_registre()).isEqualTo(UPDATED_IDENTIFIANT_REGISTRE);
        assertThat(testFiche.getDate_enregistrement()).isEqualTo(UPDATED_DATE_ENREGISTREMENT);
        assertThat(testFiche.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testFiche.getDate_naissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testFiche.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testFiche.getDate_deces()).isEqualTo(UPDATED_DATE_DECES);
        assertThat(testFiche.getCirconstance_deces()).isEqualTo(UPDATED_CIRCONSTANCE_DECES);
        assertThat(testFiche.getAutre_circonstance_deces()).isEqualTo(UPDATED_AUTRE_CIRCONSTANCE_DECES);
        assertThat(testFiche.getLieu_deces()).isEqualTo(UPDATED_LIEU_DECES);
        assertThat(testFiche.getConsanguinite()).isEqualTo(UPDATED_CONSANGUINITE);
        assertThat(testFiche.getOrigine_geo_pere_gouvernorat()).isEqualTo(UPDATED_ORIGINE_GEO_PERE_GOUVERNORAT);
        assertThat(testFiche.getOrigine_geo_mere_gouvernorat()).isEqualTo(UPDATED_ORIGINE_GEO_MERE_GOUVERNORAT);
        assertThat(testFiche.getOrigine_geo_pere_deleguation()).isEqualTo(UPDATED_ORIGINE_GEO_PERE_DELEGUATION);
        assertThat(testFiche.getOrigine_geo_mere_deleguation()).isEqualTo(UPDATED_ORIGINE_GEO_MERE_DELEGUATION);
        assertThat(testFiche.getCouverture_sociale()).isEqualTo(UPDATED_COUVERTURE_SOCIALE);
        assertThat(testFiche.getAutre_couverture_sociale()).isEqualTo(UPDATED_AUTRE_COUVERTURE_SOCIALE);
        assertThat(testFiche.getActivite()).isEqualTo(UPDATED_ACTIVITE);
        assertThat(testFiche.getBtravail()).isEqualTo(UPDATED_BTRAVAIL);
        assertThat(testFiche.getTravail()).isEqualTo(UPDATED_TRAVAIL);
        assertThat(testFiche.getScolarise()).isEqualTo(UPDATED_SCOLARISE);
        assertThat(testFiche.getType_scolarise()).isEqualTo(UPDATED_TYPE_SCOLARISE);
        assertThat(testFiche.getNiveau_scolarisation()).isEqualTo(UPDATED_NIVEAU_SCOLARISATION);
        assertThat(testFiche.getCas_familiaux()).isEqualTo(UPDATED_CAS_FAMILIAUX);
        assertThat(testFiche.getNbcasconfirme()).isEqualTo(UPDATED_NBCASCONFIRME);
        assertThat(testFiche.getNbcassuspectes()).isEqualTo(UPDATED_NBCASSUSPECTES);
        assertThat(testFiche.getNbcasdecedes()).isEqualTo(UPDATED_NBCASDECEDES);
        assertThat(testFiche.getDeces_en_bas_age()).isEqualTo(UPDATED_DECES_EN_BAS_AGE);
        assertThat(testFiche.getNbcas_deces_age_bas()).isEqualTo(UPDATED_NBCAS_DECES_AGE_BAS);
        assertThat(testFiche.getCirconstances_decouverte()).isEqualTo(UPDATED_CIRCONSTANCES_DECOUVERTE);
        assertThat(testFiche.getAge_aux_premiers_symptomes()).isEqualTo(UPDATED_AGE_AUX_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getAn_age_premiers_symptomes()).isEqualTo(UPDATED_AN_AGE_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getMois_age_premiers_symptomes()).isEqualTo(UPDATED_MOIS_AGE_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getJours_premiers_symptomes()).isEqualTo(UPDATED_JOURS_PREMIERS_SYMPTOMES);
        assertThat(testFiche.getAge_premiere_consultation()).isEqualTo(UPDATED_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getAn_age_premiere_consultation()).isEqualTo(UPDATED_AN_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getMois_age_premiere_consultation()).isEqualTo(UPDATED_MOIS_AGE_PREMIERE_CONSULTATION);
        assertThat(testFiche.getJours_premiere_consultation()).isEqualTo(UPDATED_JOURS_PREMIERE_CONSULTATION);
        assertThat(testFiche.getDate_derniere_evaluation()).isEqualTo(UPDATED_DATE_DERNIERE_EVALUATION);
        assertThat(testFiche.getDate_diagnostic()).isEqualTo(UPDATED_DATE_DIAGNOSTIC);
        assertThat(testFiche.getHandicap_mental()).isEqualTo(UPDATED_HANDICAP_MENTAL);
        assertThat(testFiche.getQi()).isEqualTo(UPDATED_QI);
        assertThat(testFiche.getHandicap_moteur()).isEqualTo(UPDATED_HANDICAP_MOTEUR);
        assertThat(testFiche.getHadicap_moteur_grade()).isEqualTo(UPDATED_HADICAP_MOTEUR_GRADE);
        assertThat(testFiche.getDeficit_neurosensoriel()).isEqualTo(UPDATED_DEFICIT_NEUROSENSORIEL);
        assertThat(testFiche.getDeficit_neurosensoriel_val()).isEqualTo(UPDATED_DEFICIT_NEUROSENSORIEL_VAL);
        assertThat(testFiche.getDeficience_psychique()).isEqualTo(UPDATED_DEFICIENCE_PSYCHIQUE);
        assertThat(testFiche.getDeficience_psychique_val()).isEqualTo(UPDATED_DEFICIENCE_PSYCHIQUE_VAL);
        assertThat(testFiche.getAutre_deficience_psychique()).isEqualTo(UPDATED_AUTRE_DEFICIENCE_PSYCHIQUE);
        assertThat(testFiche.getRegime()).isEqualTo(UPDATED_REGIME);
        assertThat(testFiche.getRegime_val()).isEqualTo(UPDATED_REGIME_VAL);
        assertThat(testFiche.getMedicament_specifique()).isEqualTo(UPDATED_MEDICAMENT_SPECIFIQUE);
        assertThat(testFiche.getMedicament_specifique_val()).isEqualTo(UPDATED_MEDICAMENT_SPECIFIQUE_VAL);
        assertThat(testFiche.getAutre_medicament_specifique()).isEqualTo(UPDATED_AUTRE_MEDICAMENT_SPECIFIQUE);
        assertThat(testFiche.getVitamines()).isEqualTo(UPDATED_VITAMINES);
        assertThat(testFiche.getVitamines_val()).isEqualTo(UPDATED_VITAMINES_VAL);
        assertThat(testFiche.getGreffehepatique()).isEqualTo(UPDATED_GREFFEHEPATIQUE);
        assertThat(testFiche.getReeducation_fonctionnelle()).isEqualTo(UPDATED_REEDUCATION_FONCTIONNELLE);
        assertThat(testFiche.getAppareillage()).isEqualTo(UPDATED_APPAREILLAGE);
        assertThat(testFiche.getPsuchologie()).isEqualTo(UPDATED_PSUCHOLOGIE);
        assertThat(testFiche.getErgotherapie()).isEqualTo(UPDATED_ERGOTHERAPIE);
        assertThat(testFiche.getDepistage_post_natal_oriente()).isEqualTo(UPDATED_DEPISTAGE_POST_NATAL_ORIENTE);
        assertThat(testFiche.getEchelle_depistage()).isEqualTo(UPDATED_ECHELLE_DEPISTAGE);
        assertThat(testFiche.getNombre_individus_depistes()).isEqualTo(UPDATED_NOMBRE_INDIVIDUS_DEPISTES);
        assertThat(testFiche.getNouveaux_cas_depistes()).isEqualTo(UPDATED_NOUVEAUX_CAS_DEPISTES);
        assertThat(testFiche.getNombre_nouveaux_cas_depistes()).isEqualTo(UPDATED_NOMBRE_NOUVEAUX_CAS_DEPISTES);
        assertThat(testFiche.getCode_registre1_cas_depistes()).isEqualTo(UPDATED_CODE_REGISTRE_1_CAS_DEPISTES);
        assertThat(testFiche.getLien_parente1_cas_depistes()).isEqualTo(UPDATED_LIEN_PARENTE_1_CAS_DEPISTES);
        assertThat(testFiche.getAutre_lien_parente1()).isEqualTo(UPDATED_AUTRE_LIEN_PARENTE_1);
        assertThat(testFiche.getCode_registre2_cas_depistes()).isEqualTo(UPDATED_CODE_REGISTRE_2_CAS_DEPISTES);
        assertThat(testFiche.getLien_parente2_cas_depistes()).isEqualTo(UPDATED_LIEN_PARENTE_2_CAS_DEPISTES);
        assertThat(testFiche.getAutre_lien_parente2()).isEqualTo(UPDATED_AUTRE_LIEN_PARENTE_2);
        assertThat(testFiche.getNombre_de_grossesse_ulterieures()).isEqualTo(UPDATED_NOMBRE_DE_GROSSESSE_ULTERIEURES);
        assertThat(testFiche.getNomre_DPN()).isEqualTo(UPDATED_NOMRE_DPN);
        assertThat(testFiche.getNombre_nouveaux_cas_diagnostiques()).isEqualTo(UPDATED_NOMBRE_NOUVEAUX_CAS_DIAGNOSTIQUES);
        assertThat(testFiche.getNombre_ITG()).isEqualTo(UPDATED_NOMBRE_ITG);
        assertThat(testFiche.getNomre_de_grossesses_poursuivies()).isEqualTo(UPDATED_NOMRE_DE_GROSSESSES_POURSUIVIES);
        assertThat(testFiche.getNombre_de_foetus_sains()).isEqualTo(UPDATED_NOMBRE_DE_FOETUS_SAINS);
    }

    @Test
    @Transactional
    void patchNonExistingFiche() throws Exception {
        int databaseSizeBeforeUpdate = ficheRepository.findAll().size();
        fiche.setId(count.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ficheDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFiche() throws Exception {
        int databaseSizeBeforeUpdate = ficheRepository.findAll().size();
        fiche.setId(count.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFiche() throws Exception {
        int databaseSizeBeforeUpdate = ficheRepository.findAll().size();
        fiche.setId(count.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ficheDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fiche in the database
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFiche() throws Exception {
        // Initialize the database
        ficheRepository.saveAndFlush(fiche);

        int databaseSizeBeforeDelete = ficheRepository.findAll().size();

        // Delete the fiche
        restFicheMockMvc
            .perform(delete(ENTITY_API_URL_ID, fiche.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fiche> ficheList = ficheRepository.findAll();
        assertThat(ficheList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
