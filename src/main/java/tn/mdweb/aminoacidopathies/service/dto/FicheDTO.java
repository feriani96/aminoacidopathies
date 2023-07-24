package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;
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
import java.util.List;


/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Fiche} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate datemaj;

    private String type_observation;

    private String identifiant_registre;

    private LocalDate date_enregistrement;

    private esexe sexe;

    private LocalDate date_naissance;

    private estatut statut;

    private LocalDate date_deces;

    private ecirconstance circonstance_deces;

    private String autre_circonstance_deces;

    private elieudeces lieu_deces;

    private econsanguinite consanguinite;

    private egouvernorat origine_geo_pere_gouvernorat;

    private egouvernoratmere origine_geo_mere_gouvernorat;

    private String origine_geo_pere_deleguation;

    private String origine_geo_mere_deleguation;

    private ecouverture couverture_sociale;

    private String autre_couverture_sociale;

    private eactivite activite;

    private Boolean btravail;

    private String travail;

    private Boolean scolarise;

    private escolarisetype type_scolarise;

    private eniveauscolarisation niveau_scolarisation;

    private ecasfamiliaux cas_familiaux;

    private Integer nbcasconfirme;

    private Integer nbcassuspectes;

    private Integer nbcasdecedes;

    private edecesbasage deces_en_bas_age;

    private Integer nbcas_deces_age_bas;

    private ecircondecouverte circonstances_decouverte;

    private eage_premier_symptome age_aux_premiers_symptomes;

    private Integer an_age_premiers_symptomes;

    private Integer mois_age_premiers_symptomes;

    private Integer jours_premiers_symptomes;

    private eagepremiereconsultation age_premiere_consultation;

    private Integer an_age_premiere_consultation;

    private Integer mois_age_premiere_consultation;

    private Integer jours_premiere_consultation;

    private LocalDate date_derniere_evaluation;

    private LocalDate date_diagnostic;

    private ehandicapmental handicap_mental;

    private eQI qi;

    private eMoteur handicap_moteur;

    private egrade hadicap_moteur_grade;

    private edeficitneuro deficit_neurosensoriel;

    private edeficitneurosensorielval deficit_neurosensoriel_val;

    private edeficiencepsychique deficience_psychique;

    private edeficiencepsychiqueval deficience_psychique_val;

    private String autre_deficience_psychique;

    private eregime regime;

    private eregimeval regime_val;

    private emedicamentspecifique medicament_specifique;

    private emedicamentspecifiqueval medicament_specifique_val;

    private String autre_medicament_specifique;

    private evitamines vitamines;

    private evitamineval vitamines_val;

    private egreffehepatique greffehepatique;

    private erededucationfonctionnelle reeducation_fonctionnelle;

    private eappareillage appareillage;

    private epsychologie psuchologie;

    private eergotherapie ergotherapie;

    private edepistage_post_natal_oriente depistage_post_natal_oriente;

    private eechelledepistage echelle_depistage;

    private Integer nombre_individus_depistes;

    private enouveaux_cas_depistes nouveaux_cas_depistes;

    private Integer nombre_nouveaux_cas_depistes;

    private String code_registre1_cas_depistes;

    private elienparente1 lien_parente1_cas_depistes;

    private String autre_lien_parente1;

    private String code_registre2_cas_depistes;

    private elienparente2 lien_parente2_cas_depistes;

    private String autre_lien_parente2;

    private Integer nombre_de_grossesse_ulterieures;

    private Integer nomre_DPN;

    private Integer nombre_nouveaux_cas_diagnostiques;

    private Integer nombre_ITG;

    private Integer nomre_de_grossesses_poursuivies;

    private Integer nombre_de_foetus_sains;

    private PathologieDTO pathologie;

    private List<CasconfirmeDTO> casConfirmes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatemaj() {
        return datemaj;
    }

    public void setDatemaj(LocalDate datemaj) {
        this.datemaj = datemaj;
    }

    public String getType_observation() {
        return type_observation;
    }

    public void setType_observation(String type_observation) {
        this.type_observation = type_observation;
    }

    public String getIdentifiant_registre() {
        return identifiant_registre;
    }

    public void setIdentifiant_registre(String identifiant_registre) {
        this.identifiant_registre = identifiant_registre;
    }

    public LocalDate getDate_enregistrement() {
        return date_enregistrement;
    }

    public void setDate_enregistrement(LocalDate date_enregistrement) {
        this.date_enregistrement = date_enregistrement;
    }

    public esexe getSexe() {
        return sexe;
    }

    public void setSexe(esexe sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public estatut getStatut() {
        return statut;
    }

    public void setStatut(estatut statut) {
        this.statut = statut;
    }

    public LocalDate getDate_deces() {
        return date_deces;
    }

    public void setDate_deces(LocalDate date_deces) {
        this.date_deces = date_deces;
    }

    public ecirconstance getCirconstance_deces() {
        return circonstance_deces;
    }

    public void setCirconstance_deces(ecirconstance circonstance_deces) {
        this.circonstance_deces = circonstance_deces;
    }

    public String getAutre_circonstance_deces() {
        return autre_circonstance_deces;
    }

    public void setAutre_circonstance_deces(String autre_circonstance_deces) {
        this.autre_circonstance_deces = autre_circonstance_deces;
    }

    public elieudeces getLieu_deces() {
        return lieu_deces;
    }

    public void setLieu_deces(elieudeces lieu_deces) {
        this.lieu_deces = lieu_deces;
    }

    public econsanguinite getConsanguinite() {
        return consanguinite;
    }

    public void setConsanguinite(econsanguinite consanguinite) {
        this.consanguinite = consanguinite;
    }

    public egouvernorat getOrigine_geo_pere_gouvernorat() {
        return origine_geo_pere_gouvernorat;
    }

    public void setOrigine_geo_pere_gouvernorat(egouvernorat origine_geo_pere_gouvernorat) {
        this.origine_geo_pere_gouvernorat = origine_geo_pere_gouvernorat;
    }

    public egouvernoratmere getOrigine_geo_mere_gouvernorat() {
        return origine_geo_mere_gouvernorat;
    }

    public void setOrigine_geo_mere_gouvernorat(egouvernoratmere origine_geo_mere_gouvernorat) {
        this.origine_geo_mere_gouvernorat = origine_geo_mere_gouvernorat;
    }

    public String getOrigine_geo_pere_deleguation() {
        return origine_geo_pere_deleguation;
    }

    public void setOrigine_geo_pere_deleguation(String origine_geo_pere_deleguation) {
        this.origine_geo_pere_deleguation = origine_geo_pere_deleguation;
    }

    public String getOrigine_geo_mere_deleguation() {
        return origine_geo_mere_deleguation;
    }

    public void setOrigine_geo_mere_deleguation(String origine_geo_mere_deleguation) {
        this.origine_geo_mere_deleguation = origine_geo_mere_deleguation;
    }

    public ecouverture getCouverture_sociale() {
        return couverture_sociale;
    }

    public void setCouverture_sociale(ecouverture couverture_sociale) {
        this.couverture_sociale = couverture_sociale;
    }

    public String getAutre_couverture_sociale() {
        return autre_couverture_sociale;
    }

    public void setAutre_couverture_sociale(String autre_couverture_sociale) {
        this.autre_couverture_sociale = autre_couverture_sociale;
    }

    public eactivite getActivite() {
        return activite;
    }

    public void setActivite(eactivite activite) {
        this.activite = activite;
    }

    public Boolean getBtravail() {
        return btravail;
    }

    public void setBtravail(Boolean btravail) {
        this.btravail = btravail;
    }

    public String getTravail() {
        return travail;
    }

    public void setTravail(String travail) {
        this.travail = travail;
    }

    public Boolean getScolarise() {
        return scolarise;
    }

    public void setScolarise(Boolean scolarise) {
        this.scolarise = scolarise;
    }

    public escolarisetype getType_scolarise() {
        return type_scolarise;
    }

    public void setType_scolarise(escolarisetype type_scolarise) {
        this.type_scolarise = type_scolarise;
    }

    public eniveauscolarisation getNiveau_scolarisation() {
        return niveau_scolarisation;
    }

    public void setNiveau_scolarisation(eniveauscolarisation niveau_scolarisation) {
        this.niveau_scolarisation = niveau_scolarisation;
    }

    public ecasfamiliaux getCas_familiaux() {
        return cas_familiaux;
    }

    public void setCas_familiaux(ecasfamiliaux cas_familiaux) {
        this.cas_familiaux = cas_familiaux;
    }

    public Integer getNbcasconfirme() {
        return nbcasconfirme;
    }

    public void setNbcasconfirme(Integer nbcasconfirme) {
        this.nbcasconfirme = nbcasconfirme;
    }

    public Integer getNbcassuspectes() {
        return nbcassuspectes;
    }

    public void setNbcassuspectes(Integer nbcassuspectes) {
        this.nbcassuspectes = nbcassuspectes;
    }

    public Integer getNbcasdecedes() {
        return nbcasdecedes;
    }

    public void setNbcasdecedes(Integer nbcasdecedes) {
        this.nbcasdecedes = nbcasdecedes;
    }

    public edecesbasage getDeces_en_bas_age() {
        return deces_en_bas_age;
    }

    public void setDeces_en_bas_age(edecesbasage deces_en_bas_age) {
        this.deces_en_bas_age = deces_en_bas_age;
    }

    public Integer getNbcas_deces_age_bas() {
        return nbcas_deces_age_bas;
    }

    public void setNbcas_deces_age_bas(Integer nbcas_deces_age_bas) {
        this.nbcas_deces_age_bas = nbcas_deces_age_bas;
    }

    public ecircondecouverte getCirconstances_decouverte() {
        return circonstances_decouverte;
    }

    public void setCirconstances_decouverte(ecircondecouverte circonstances_decouverte) {
        this.circonstances_decouverte = circonstances_decouverte;
    }

    public eage_premier_symptome getAge_aux_premiers_symptomes() {
        return age_aux_premiers_symptomes;
    }

    public void setAge_aux_premiers_symptomes(eage_premier_symptome age_aux_premiers_symptomes) {
        this.age_aux_premiers_symptomes = age_aux_premiers_symptomes;
    }

    public Integer getAn_age_premiers_symptomes() {
        return an_age_premiers_symptomes;
    }

    public void setAn_age_premiers_symptomes(Integer an_age_premiers_symptomes) {
        this.an_age_premiers_symptomes = an_age_premiers_symptomes;
    }

    public Integer getMois_age_premiers_symptomes() {
        return mois_age_premiers_symptomes;
    }

    public void setMois_age_premiers_symptomes(Integer mois_age_premiers_symptomes) {
        this.mois_age_premiers_symptomes = mois_age_premiers_symptomes;
    }

    public Integer getJours_premiers_symptomes() {
        return jours_premiers_symptomes;
    }

    public void setJours_premiers_symptomes(Integer jours_premiers_symptomes) {
        this.jours_premiers_symptomes = jours_premiers_symptomes;
    }

    public eagepremiereconsultation getAge_premiere_consultation() {
        return age_premiere_consultation;
    }

    public void setAge_premiere_consultation(eagepremiereconsultation age_premiere_consultation) {
        this.age_premiere_consultation = age_premiere_consultation;
    }

    public Integer getAn_age_premiere_consultation() {
        return an_age_premiere_consultation;
    }

    public void setAn_age_premiere_consultation(Integer an_age_premiere_consultation) {
        this.an_age_premiere_consultation = an_age_premiere_consultation;
    }

    public Integer getMois_age_premiere_consultation() {
        return mois_age_premiere_consultation;
    }

    public void setMois_age_premiere_consultation(Integer mois_age_premiere_consultation) {
        this.mois_age_premiere_consultation = mois_age_premiere_consultation;
    }

    public Integer getJours_premiere_consultation() {
        return jours_premiere_consultation;
    }

    public void setJours_premiere_consultation(Integer jours_premiere_consultation) {
        this.jours_premiere_consultation = jours_premiere_consultation;
    }

    public LocalDate getDate_derniere_evaluation() {
        return date_derniere_evaluation;
    }

    public void setDate_derniere_evaluation(LocalDate date_derniere_evaluation) {
        this.date_derniere_evaluation = date_derniere_evaluation;
    }

    public LocalDate getDate_diagnostic() {
        return date_diagnostic;
    }

    public void setDate_diagnostic(LocalDate date_diagnostic) {
        this.date_diagnostic = date_diagnostic;
    }

    public ehandicapmental getHandicap_mental() {
        return handicap_mental;
    }

    public void setHandicap_mental(ehandicapmental handicap_mental) {
        this.handicap_mental = handicap_mental;
    }

    public eQI getQi() {
        return qi;
    }

    public void setQi(eQI qi) {
        this.qi = qi;
    }

    public eMoteur getHandicap_moteur() {
        return handicap_moteur;
    }

    public void setHandicap_moteur(eMoteur handicap_moteur) {
        this.handicap_moteur = handicap_moteur;
    }

    public egrade getHadicap_moteur_grade() {
        return hadicap_moteur_grade;
    }

    public void setHadicap_moteur_grade(egrade hadicap_moteur_grade) {
        this.hadicap_moteur_grade = hadicap_moteur_grade;
    }

    public edeficitneuro getDeficit_neurosensoriel() {
        return deficit_neurosensoriel;
    }

    public void setDeficit_neurosensoriel(edeficitneuro deficit_neurosensoriel) {
        this.deficit_neurosensoriel = deficit_neurosensoriel;
    }

    public edeficitneurosensorielval getDeficit_neurosensoriel_val() {
        return deficit_neurosensoriel_val;
    }

    public void setDeficit_neurosensoriel_val(edeficitneurosensorielval deficit_neurosensoriel_val) {
        this.deficit_neurosensoriel_val = deficit_neurosensoriel_val;
    }

    public edeficiencepsychique getDeficience_psychique() {
        return deficience_psychique;
    }

    public void setDeficience_psychique(edeficiencepsychique deficience_psychique) {
        this.deficience_psychique = deficience_psychique;
    }

    public edeficiencepsychiqueval getDeficience_psychique_val() {
        return deficience_psychique_val;
    }

    public void setDeficience_psychique_val(edeficiencepsychiqueval deficience_psychique_val) {
        this.deficience_psychique_val = deficience_psychique_val;
    }

    public String getAutre_deficience_psychique() {
        return autre_deficience_psychique;
    }

    public void setAutre_deficience_psychique(String autre_deficience_psychique) {
        this.autre_deficience_psychique = autre_deficience_psychique;
    }

    public eregime getRegime() {
        return regime;
    }

    public void setRegime(eregime regime) {
        this.regime = regime;
    }

    public eregimeval getRegime_val() {
        return regime_val;
    }

    public void setRegime_val(eregimeval regime_val) {
        this.regime_val = regime_val;
    }

    public emedicamentspecifique getMedicament_specifique() {
        return medicament_specifique;
    }

    public void setMedicament_specifique(emedicamentspecifique medicament_specifique) {
        this.medicament_specifique = medicament_specifique;
    }

    public emedicamentspecifiqueval getMedicament_specifique_val() {
        return medicament_specifique_val;
    }

    public void setMedicament_specifique_val(emedicamentspecifiqueval medicament_specifique_val) {
        this.medicament_specifique_val = medicament_specifique_val;
    }

    public String getAutre_medicament_specifique() {
        return autre_medicament_specifique;
    }

    public void setAutre_medicament_specifique(String autre_medicament_specifique) {
        this.autre_medicament_specifique = autre_medicament_specifique;
    }

    public evitamines getVitamines() {
        return vitamines;
    }

    public void setVitamines(evitamines vitamines) {
        this.vitamines = vitamines;
    }

    public evitamineval getVitamines_val() {
        return vitamines_val;
    }

    public void setVitamines_val(evitamineval vitamines_val) {
        this.vitamines_val = vitamines_val;
    }

    public egreffehepatique getGreffehepatique() {
        return greffehepatique;
    }

    public void setGreffehepatique(egreffehepatique greffehepatique) {
        this.greffehepatique = greffehepatique;
    }

    public erededucationfonctionnelle getReeducation_fonctionnelle() {
        return reeducation_fonctionnelle;
    }

    public void setReeducation_fonctionnelle(erededucationfonctionnelle reeducation_fonctionnelle) {
        this.reeducation_fonctionnelle = reeducation_fonctionnelle;
    }

    public eappareillage getAppareillage() {
        return appareillage;
    }

    public void setAppareillage(eappareillage appareillage) {
        this.appareillage = appareillage;
    }

    public epsychologie getPsuchologie() {
        return psuchologie;
    }

    public void setPsuchologie(epsychologie psuchologie) {
        this.psuchologie = psuchologie;
    }

    public eergotherapie getErgotherapie() {
        return ergotherapie;
    }

    public void setErgotherapie(eergotherapie ergotherapie) {
        this.ergotherapie = ergotherapie;
    }

    public edepistage_post_natal_oriente getDepistage_post_natal_oriente() {
        return depistage_post_natal_oriente;
    }

    public void setDepistage_post_natal_oriente(edepistage_post_natal_oriente depistage_post_natal_oriente) {
        this.depistage_post_natal_oriente = depistage_post_natal_oriente;
    }

    public eechelledepistage getEchelle_depistage() {
        return echelle_depistage;
    }

    public void setEchelle_depistage(eechelledepistage echelle_depistage) {
        this.echelle_depistage = echelle_depistage;
    }

    public Integer getNombre_individus_depistes() {
        return nombre_individus_depistes;
    }

    public void setNombre_individus_depistes(Integer nombre_individus_depistes) {
        this.nombre_individus_depistes = nombre_individus_depistes;
    }

    public enouveaux_cas_depistes getNouveaux_cas_depistes() {
        return nouveaux_cas_depistes;
    }

    public void setNouveaux_cas_depistes(enouveaux_cas_depistes nouveaux_cas_depistes) {
        this.nouveaux_cas_depistes = nouveaux_cas_depistes;
    }

    public Integer getNombre_nouveaux_cas_depistes() {
        return nombre_nouveaux_cas_depistes;
    }

    public void setNombre_nouveaux_cas_depistes(Integer nombre_nouveaux_cas_depistes) {
        this.nombre_nouveaux_cas_depistes = nombre_nouveaux_cas_depistes;
    }

    public String getCode_registre1_cas_depistes() {
        return code_registre1_cas_depistes;
    }

    public void setCode_registre1_cas_depistes(String code_registre1_cas_depistes) {
        this.code_registre1_cas_depistes = code_registre1_cas_depistes;
    }

    public elienparente1 getLien_parente1_cas_depistes() {
        return lien_parente1_cas_depistes;
    }

    public void setLien_parente1_cas_depistes(elienparente1 lien_parente1_cas_depistes) {
        this.lien_parente1_cas_depistes = lien_parente1_cas_depistes;
    }

    public String getAutre_lien_parente1() {
        return autre_lien_parente1;
    }

    public void setAutre_lien_parente1(String autre_lien_parente1) {
        this.autre_lien_parente1 = autre_lien_parente1;
    }

    public String getCode_registre2_cas_depistes() {
        return code_registre2_cas_depistes;
    }

    public void setCode_registre2_cas_depistes(String code_registre2_cas_depistes) {
        this.code_registre2_cas_depistes = code_registre2_cas_depistes;
    }

    public elienparente2 getLien_parente2_cas_depistes() {
        return lien_parente2_cas_depistes;
    }

    public void setLien_parente2_cas_depistes(elienparente2 lien_parente2_cas_depistes) {
        this.lien_parente2_cas_depistes = lien_parente2_cas_depistes;
    }

    public String getAutre_lien_parente2() {
        return autre_lien_parente2;
    }

    public void setAutre_lien_parente2(String autre_lien_parente2) {
        this.autre_lien_parente2 = autre_lien_parente2;
    }

    public Integer getNombre_de_grossesse_ulterieures() {
        return nombre_de_grossesse_ulterieures;
    }

    public void setNombre_de_grossesse_ulterieures(Integer nombre_de_grossesse_ulterieures) {
        this.nombre_de_grossesse_ulterieures = nombre_de_grossesse_ulterieures;
    }

    public Integer getNomre_DPN() {
        return nomre_DPN;
    }

    public void setNomre_DPN(Integer nomre_DPN) {
        this.nomre_DPN = nomre_DPN;
    }

    public Integer getNombre_nouveaux_cas_diagnostiques() {
        return nombre_nouveaux_cas_diagnostiques;
    }

    public void setNombre_nouveaux_cas_diagnostiques(Integer nombre_nouveaux_cas_diagnostiques) {
        this.nombre_nouveaux_cas_diagnostiques = nombre_nouveaux_cas_diagnostiques;
    }

    public Integer getNombre_ITG() {
        return nombre_ITG;
    }

    public void setNombre_ITG(Integer nombre_ITG) {
        this.nombre_ITG = nombre_ITG;
    }

    public Integer getNomre_de_grossesses_poursuivies() {
        return nomre_de_grossesses_poursuivies;
    }

    public void setNomre_de_grossesses_poursuivies(Integer nomre_de_grossesses_poursuivies) {
        this.nomre_de_grossesses_poursuivies = nomre_de_grossesses_poursuivies;
    }

    public Integer getNombre_de_foetus_sains() {
        return nombre_de_foetus_sains;
    }

    public void setNombre_de_foetus_sains(Integer nombre_de_foetus_sains) {
        this.nombre_de_foetus_sains = nombre_de_foetus_sains;
    }

    public PathologieDTO getPathologie() {
        return pathologie;
    }

    public void setPathologie(PathologieDTO pathologie) {
        this.pathologie = pathologie;
    }

    public java.util.List<CasconfirmeDTO> getCasConfirmes() {
        return casConfirmes;
    }

    public void setCasConfirmes(List<CasconfirmeDTO> casConfirmes) {
        this.casConfirmes = casConfirmes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheDTO)) {
            return false;
        }

        FicheDTO ficheDTO = (FicheDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ficheDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

@Override
public String toString() {
    return "FicheDTO{" +
               "id=" + id +
               ", datemaj=" + datemaj +
               ", type_observation='" + type_observation + '\'' +
               ", identifiant_registre='" + identifiant_registre + '\'' +
               ", date_enregistrement=" + date_enregistrement +
               ", sexe=" + sexe +
               ", date_naissance=" + date_naissance +
               ", statut=" + statut +
               ", date_deces=" + date_deces +
               ", circonstance_deces=" + circonstance_deces +
               ", autre_circonstance_deces='" + autre_circonstance_deces + '\'' +
               ", lieu_deces=" + lieu_deces +
               ", consanguinite=" + consanguinite +
               ", origine_geo_pere_gouvernorat=" + origine_geo_pere_gouvernorat +
               ", origine_geo_mere_gouvernorat=" + origine_geo_mere_gouvernorat +
               ", origine_geo_pere_deleguation='" + origine_geo_pere_deleguation + '\'' +
               ", origine_geo_mere_deleguation='" + origine_geo_mere_deleguation + '\'' +
               ", couverture_sociale=" + couverture_sociale +
               ", autre_couverture_sociale='" + autre_couverture_sociale + '\'' +
               ", activite=" + activite +
               ", btravail=" + btravail +
               ", travail='" + travail + '\'' +
               ", scolarise=" + scolarise +
               ", type_scolarise=" + type_scolarise +
               ", niveau_scolarisation=" + niveau_scolarisation +
               ", cas_familiaux=" + cas_familiaux +
               ", nbcasconfirme=" + nbcasconfirme +
               ", nbcassuspectes=" + nbcassuspectes +
               ", nbcasdecedes=" + nbcasdecedes +
               ", deces_en_bas_age=" + deces_en_bas_age +
               ", nbcas_deces_age_bas=" + nbcas_deces_age_bas +
               ", circonstances_decouverte=" + circonstances_decouverte +
               ", age_aux_premiers_symptomes=" + age_aux_premiers_symptomes +
               ", an_age_premiers_symptomes=" + an_age_premiers_symptomes +
               ", mois_age_premiers_symptomes=" + mois_age_premiers_symptomes +
               ", jours_premiers_symptomes=" + jours_premiers_symptomes +
               ", age_premiere_consultation=" + age_premiere_consultation +
               ", an_age_premiere_consultation=" + an_age_premiere_consultation +
               ", mois_age_premiere_consultation=" + mois_age_premiere_consultation +
               ", jours_premiere_consultation=" + jours_premiere_consultation +
               ", date_derniere_evaluation=" + date_derniere_evaluation +
               ", date_diagnostic=" + date_diagnostic +
               ", handicap_mental=" + handicap_mental +
               ", qi=" + qi +
               ", handicap_moteur=" + handicap_moteur +
               ", hadicap_moteur_grade=" + hadicap_moteur_grade +
               ", deficit_neurosensoriel=" + deficit_neurosensoriel +
               ", deficit_neurosensoriel_val=" + deficit_neurosensoriel_val +
               ", deficience_psychique=" + deficience_psychique +
               ", deficience_psychique_val=" + deficience_psychique_val +
               ", autre_deficience_psychique='" + autre_deficience_psychique + '\'' +
               ", regime=" + regime +
               ", regime_val=" + regime_val +
               ", medicament_specifique=" + medicament_specifique +
               ", medicament_specifique_val=" + medicament_specifique_val +
               ", autre_medicament_specifique='" + autre_medicament_specifique + '\'' +
               ", vitamines=" + vitamines +
               ", vitamines_val=" + vitamines_val +
               ", greffehepatique=" + greffehepatique +
               ", reeducation_fonctionnelle=" + reeducation_fonctionnelle +
               ", appareillage=" + appareillage +
               ", psuchologie=" + psuchologie +
               ", ergotherapie=" + ergotherapie +
               ", depistage_post_natal_oriente=" + depistage_post_natal_oriente +
               ", echelle_depistage=" + echelle_depistage +
               ", nombre_individus_depistes=" + nombre_individus_depistes +
               ", nouveaux_cas_depistes=" + nouveaux_cas_depistes +
               ", nombre_nouveaux_cas_depistes=" + nombre_nouveaux_cas_depistes +
               ", code_registre1_cas_depistes='" + code_registre1_cas_depistes + '\'' +
               ", lien_parente1_cas_depistes=" + lien_parente1_cas_depistes +
               ", autre_lien_parente1='" + autre_lien_parente1 + '\'' +
               ", code_registre2_cas_depistes='" + code_registre2_cas_depistes + '\'' +
               ", lien_parente2_cas_depistes=" + lien_parente2_cas_depistes +
               ", autre_lien_parente2='" + autre_lien_parente2 + '\'' +
               ", nombre_de_grossesse_ulterieures=" + nombre_de_grossesse_ulterieures +
               ", nomre_DPN=" + nomre_DPN +
               ", nombre_nouveaux_cas_diagnostiques=" + nombre_nouveaux_cas_diagnostiques +
               ", nombre_ITG=" + nombre_ITG +
               ", nomre_de_grossesses_poursuivies=" + nomre_de_grossesses_poursuivies +
               ", nombre_de_foetus_sains=" + nombre_de_foetus_sains +
               ", pathologie=" + pathologie +
               ", casConfirmes=" + casConfirmes +
               '}';
}

// prettier-ignore

}
