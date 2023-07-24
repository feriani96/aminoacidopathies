package tn.mdweb.aminoacidopathies.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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

/**
 * A Fiche.
 */
@Entity
@Table(name = "fiche")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Fiche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "datemaj", nullable = false)
    private LocalDate datemaj;

    @Column(name = "type_observation")
    private String type_observation;

    @Column(name = "identifiant_registre")
    private String identifiant_registre;

    @Column(name = "date_enregistrement")
    private LocalDate date_enregistrement;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private esexe sexe;

    @Column(name = "date_naissance")
    private LocalDate date_naissance;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private estatut statut;

    @Column(name = "date_deces")
    private LocalDate date_deces;

    @Enumerated(EnumType.STRING)
    @Column(name = "circonstance_deces")
    private ecirconstance circonstance_deces;

    @Column(name = "autre_circonstance_deces")
    private String autre_circonstance_deces;

    @Enumerated(EnumType.STRING)
    @Column(name = "lieu_deces")
    private elieudeces lieu_deces;

    @Enumerated(EnumType.STRING)
    @Column(name = "consanguinite")
    private econsanguinite consanguinite;

    @Enumerated(EnumType.STRING)
    @Column(name = "origine_geo_pere_gouvernorat")
    private egouvernorat origine_geo_pere_gouvernorat;

    @Enumerated(EnumType.STRING)
    @Column(name = "origine_geo_mere_gouvernorat")
    private egouvernoratmere origine_geo_mere_gouvernorat;

    @Column(name = "origine_geo_pere_deleguation")
    private String origine_geo_pere_deleguation;

    @Column(name = "origine_geo_mere_deleguation")
    private String origine_geo_mere_deleguation;

    @Enumerated(EnumType.STRING)
    @Column(name = "couverture_sociale")
    private ecouverture couverture_sociale;

    @Column(name = "autre_couverture_sociale")
    private String autre_couverture_sociale;

    @Enumerated(EnumType.STRING)
    @Column(name = "activite")
    private eactivite activite;

    @Column(name = "btravail")
    private Boolean btravail;

    @Column(name = "travail")
    private String travail;

    @Column(name = "scolarise")
    private Boolean scolarise;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_scolarise")
    private escolarisetype type_scolarise;

    @Enumerated(EnumType.STRING)
    @Column(name = "niveau_scolarisation")
    private eniveauscolarisation niveau_scolarisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_familiaux")
    private ecasfamiliaux cas_familiaux;

    @Column(name = "nbcasconfirme")
    private Integer nbcasconfirme;

    @Column(name = "nbcassuspectes")
    private Integer nbcassuspectes;

    @Column(name = "nbcasdecedes")
    private Integer nbcasdecedes;

    @Enumerated(EnumType.STRING)
    @Column(name = "deces_en_bas_age")
    private edecesbasage deces_en_bas_age;

    @Column(name = "nbcas_deces_age_bas")
    private Integer nbcas_deces_age_bas;

    @Enumerated(EnumType.STRING)
    @Column(name = "circonstances_decouverte")
    private ecircondecouverte circonstances_decouverte;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_aux_premiers_symptomes")
    private eage_premier_symptome age_aux_premiers_symptomes;

    @Column(name = "an_age_premiers_symptomes")
    private Integer an_age_premiers_symptomes;

    @Column(name = "mois_age_premiers_symptomes")
    private Integer mois_age_premiers_symptomes;

    @Column(name = "jours_premiers_symptomes")
    private Integer jours_premiers_symptomes;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_premiere_consultation")
    private eagepremiereconsultation age_premiere_consultation;

    @Column(name = "an_age_premiere_consultation")
    private Integer an_age_premiere_consultation;

    @Column(name = "mois_age_premiere_consultation")
    private Integer mois_age_premiere_consultation;

    @Column(name = "jours_premiere_consultation")
    private Integer jours_premiere_consultation;

    @Column(name = "date_derniere_evaluation")
    private LocalDate date_derniere_evaluation;

    @Column(name = "date_diagnostic")
    private LocalDate date_diagnostic;

    @Enumerated(EnumType.STRING)
    @Column(name = "handicap_mental")
    private ehandicapmental handicap_mental;

    @Enumerated(EnumType.STRING)
    @Column(name = "qi")
    private eQI qi;

    @Enumerated(EnumType.STRING)
    @Column(name = "handicap_moteur")
    private eMoteur handicap_moteur;

    @Enumerated(EnumType.STRING)
    @Column(name = "hadicap_moteur_grade")
    private egrade hadicap_moteur_grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "deficit_neurosensoriel")
    private edeficitneuro deficit_neurosensoriel;

    @Enumerated(EnumType.STRING)
    @Column(name = "deficit_neurosensoriel_val")
    private edeficitneurosensorielval deficit_neurosensoriel_val;

    @Enumerated(EnumType.STRING)
    @Column(name = "deficience_psychique")
    private edeficiencepsychique deficience_psychique;

    @Enumerated(EnumType.STRING)
    @Column(name = "deficience_psychique_val")
    private edeficiencepsychiqueval deficience_psychique_val;

    @Column(name = "autre_deficience_psychique")
    private String autre_deficience_psychique;

    @Enumerated(EnumType.STRING)
    @Column(name = "regime")
    private eregime regime;

    @Enumerated(EnumType.STRING)
    @Column(name = "regime_val")
    private eregimeval regime_val;

    @Enumerated(EnumType.STRING)
    @Column(name = "medicament_specifique")
    private emedicamentspecifique medicament_specifique;

    @Enumerated(EnumType.STRING)
    @Column(name = "medicament_specifique_val")
    private emedicamentspecifiqueval medicament_specifique_val;

    @Column(name = "autre_medicament_specifique")
    private String autre_medicament_specifique;

    @Enumerated(EnumType.STRING)
    @Column(name = "vitamines")
    private evitamines vitamines;

    @Enumerated(EnumType.STRING)
    @Column(name = "vitamines_val")
    private evitamineval vitamines_val;

    @Enumerated(EnumType.STRING)
    @Column(name = "greffehepatique")
    private egreffehepatique greffehepatique;

    @Enumerated(EnumType.STRING)
    @Column(name = "reeducation_fonctionnelle")
    private erededucationfonctionnelle reeducation_fonctionnelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "appareillage")
    private eappareillage appareillage;

    @Enumerated(EnumType.STRING)
    @Column(name = "psuchologie")
    private epsychologie psuchologie;

    @Enumerated(EnumType.STRING)
    @Column(name = "ergotherapie")
    private eergotherapie ergotherapie;

    @Enumerated(EnumType.STRING)
    @Column(name = "depistage_post_natal_oriente")
    private edepistage_post_natal_oriente depistage_post_natal_oriente;

    @Enumerated(EnumType.STRING)
    @Column(name = "echelle_depistage")
    private eechelledepistage echelle_depistage;

    @Column(name = "nombre_individus_depistes")
    private Integer nombre_individus_depistes;

    @Enumerated(EnumType.STRING)
    @Column(name = "nouveaux_cas_depistes")
    private enouveaux_cas_depistes nouveaux_cas_depistes;

    @Column(name = "nombre_nouveaux_cas_depistes")
    private Integer nombre_nouveaux_cas_depistes;

    @Column(name = "code_registre_1_cas_depistes")
    private String code_registre1_cas_depistes;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_parente_1_cas_depistes")
    private elienparente1 lien_parente1_cas_depistes;

    @Column(name = "autre_lien_parente_1")
    private String autre_lien_parente1;

    @Column(name = "code_registre_2_cas_depistes")
    private String code_registre2_cas_depistes;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_parente_2_cas_depistes")
    private elienparente2 lien_parente2_cas_depistes;

    @Column(name = "autre_lien_parente_2")
    private String autre_lien_parente2;

    @Column(name = "nombre_de_grossesse_ulterieures")
    private Integer nombre_de_grossesse_ulterieures;

    @Column(name = "nomre_dpn")
    private Integer nomre_DPN;

    @Column(name = "nombre_nouveaux_cas_diagnostiques")
    private Integer nombre_nouveaux_cas_diagnostiques;

    @Column(name = "nombre_itg")
    private Integer nombre_ITG;

    @Column(name = "nomre_de_grossesses_poursuivies")
    private Integer nomre_de_grossesses_poursuivies;

    @Column(name = "nombre_de_foetus_sains")
    private Integer nombre_de_foetus_sains;

    @ManyToOne(optional = false)
    @NotNull
    private Pathologie pathologie;

    @OneToMany(mappedBy = "fiche")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "fiche" }, allowSetters = true)
    private Set<Casconfirme> casconfirmes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Fiche id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatemaj() {
        return this.datemaj;
    }

    public Fiche datemaj(LocalDate datemaj) {
        this.setDatemaj(datemaj);
        return this;
    }

    public void setDatemaj(LocalDate datemaj) {
        this.datemaj = datemaj;
    }

    public String getType_observation() {
        return this.type_observation;
    }

    public Fiche type_observation(String type_observation) {
        this.setType_observation(type_observation);
        return this;
    }

    public void setType_observation(String type_observation) {
        this.type_observation = type_observation;
    }

    public String getIdentifiant_registre() {
        return this.identifiant_registre;
    }

    public Fiche identifiant_registre(String identifiant_registre) {
        this.setIdentifiant_registre(identifiant_registre);
        return this;
    }

    public void setIdentifiant_registre(String identifiant_registre) {
        this.identifiant_registre = identifiant_registre;
    }

    public LocalDate getDate_enregistrement() {
        return this.date_enregistrement;
    }

    public Fiche date_enregistrement(LocalDate date_enregistrement) {
        this.setDate_enregistrement(date_enregistrement);
        return this;
    }

    public void setDate_enregistrement(LocalDate date_enregistrement) {
        this.date_enregistrement = date_enregistrement;
    }

    public esexe getSexe() {
        return this.sexe;
    }

    public Fiche sexe(esexe sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(esexe sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDate_naissance() {
        return this.date_naissance;
    }

    public Fiche date_naissance(LocalDate date_naissance) {
        this.setDate_naissance(date_naissance);
        return this;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public estatut getStatut() {
        return this.statut;
    }

    public Fiche statut(estatut statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(estatut statut) {
        this.statut = statut;
    }

    public LocalDate getDate_deces() {
        return this.date_deces;
    }

    public Fiche date_deces(LocalDate date_deces) {
        this.setDate_deces(date_deces);
        return this;
    }

    public void setDate_deces(LocalDate date_deces) {
        this.date_deces = date_deces;
    }

    public ecirconstance getCirconstance_deces() {
        return this.circonstance_deces;
    }

    public Fiche circonstance_deces(ecirconstance circonstance_deces) {
        this.setCirconstance_deces(circonstance_deces);
        return this;
    }

    public void setCirconstance_deces(ecirconstance circonstance_deces) {
        this.circonstance_deces = circonstance_deces;
    }

    public String getAutre_circonstance_deces() {
        return this.autre_circonstance_deces;
    }

    public Fiche autre_circonstance_deces(String autre_circonstance_deces) {
        this.setAutre_circonstance_deces(autre_circonstance_deces);
        return this;
    }

    public void setAutre_circonstance_deces(String autre_circonstance_deces) {
        this.autre_circonstance_deces = autre_circonstance_deces;
    }

    public elieudeces getLieu_deces() {
        return this.lieu_deces;
    }

    public Fiche lieu_deces(elieudeces lieu_deces) {
        this.setLieu_deces(lieu_deces);
        return this;
    }

    public void setLieu_deces(elieudeces lieu_deces) {
        this.lieu_deces = lieu_deces;
    }

    public econsanguinite getConsanguinite() {
        return this.consanguinite;
    }

    public Fiche consanguinite(econsanguinite consanguinite) {
        this.setConsanguinite(consanguinite);
        return this;
    }

    public void setConsanguinite(econsanguinite consanguinite) {
        this.consanguinite = consanguinite;
    }

    public egouvernorat getOrigine_geo_pere_gouvernorat() {
        return this.origine_geo_pere_gouvernorat;
    }

    public Fiche origine_geo_pere_gouvernorat(egouvernorat origine_geo_pere_gouvernorat) {
        this.setOrigine_geo_pere_gouvernorat(origine_geo_pere_gouvernorat);
        return this;
    }

    public void setOrigine_geo_pere_gouvernorat(egouvernorat origine_geo_pere_gouvernorat) {
        this.origine_geo_pere_gouvernorat = origine_geo_pere_gouvernorat;
    }

    public egouvernoratmere getOrigine_geo_mere_gouvernorat() {
        return this.origine_geo_mere_gouvernorat;
    }

    public Fiche origine_geo_mere_gouvernorat(egouvernoratmere origine_geo_mere_gouvernorat) {
        this.setOrigine_geo_mere_gouvernorat(origine_geo_mere_gouvernorat);
        return this;
    }

    public void setOrigine_geo_mere_gouvernorat(egouvernoratmere origine_geo_mere_gouvernorat) {
        this.origine_geo_mere_gouvernorat = origine_geo_mere_gouvernorat;
    }

    public String getOrigine_geo_pere_deleguation() {
        return this.origine_geo_pere_deleguation;
    }

    public Fiche origine_geo_pere_deleguation(String origine_geo_pere_deleguation) {
        this.setOrigine_geo_pere_deleguation(origine_geo_pere_deleguation);
        return this;
    }

    public void setOrigine_geo_pere_deleguation(String origine_geo_pere_deleguation) {
        this.origine_geo_pere_deleguation = origine_geo_pere_deleguation;
    }

    public String getOrigine_geo_mere_deleguation() {
        return this.origine_geo_mere_deleguation;
    }

    public Fiche origine_geo_mere_deleguation(String origine_geo_mere_deleguation) {
        this.setOrigine_geo_mere_deleguation(origine_geo_mere_deleguation);
        return this;
    }

    public void setOrigine_geo_mere_deleguation(String origine_geo_mere_deleguation) {
        this.origine_geo_mere_deleguation = origine_geo_mere_deleguation;
    }

    public ecouverture getCouverture_sociale() {
        return this.couverture_sociale;
    }

    public Fiche couverture_sociale(ecouverture couverture_sociale) {
        this.setCouverture_sociale(couverture_sociale);
        return this;
    }

    public void setCouverture_sociale(ecouverture couverture_sociale) {
        this.couverture_sociale = couverture_sociale;
    }

    public String getAutre_couverture_sociale() {
        return this.autre_couverture_sociale;
    }

    public Fiche autre_couverture_sociale(String autre_couverture_sociale) {
        this.setAutre_couverture_sociale(autre_couverture_sociale);
        return this;
    }

    public void setAutre_couverture_sociale(String autre_couverture_sociale) {
        this.autre_couverture_sociale = autre_couverture_sociale;
    }

    public eactivite getActivite() {
        return this.activite;
    }

    public Fiche activite(eactivite activite) {
        this.setActivite(activite);
        return this;
    }

    public void setActivite(eactivite activite) {
        this.activite = activite;
    }

    public Boolean getBtravail() {
        return this.btravail;
    }

    public Fiche btravail(Boolean btravail) {
        this.setBtravail(btravail);
        return this;
    }

    public void setBtravail(Boolean btravail) {
        this.btravail = btravail;
    }

    public String getTravail() {
        return this.travail;
    }

    public Fiche travail(String travail) {
        this.setTravail(travail);
        return this;
    }

    public void setTravail(String travail) {
        this.travail = travail;
    }

    public Boolean getScolarise() {
        return this.scolarise;
    }

    public Fiche scolarise(Boolean scolarise) {
        this.setScolarise(scolarise);
        return this;
    }

    public void setScolarise(Boolean scolarise) {
        this.scolarise = scolarise;
    }

    public escolarisetype getType_scolarise() {
        return this.type_scolarise;
    }

    public Fiche type_scolarise(escolarisetype type_scolarise) {
        this.setType_scolarise(type_scolarise);
        return this;
    }

    public void setType_scolarise(escolarisetype type_scolarise) {
        this.type_scolarise = type_scolarise;
    }

    public eniveauscolarisation getNiveau_scolarisation() {
        return this.niveau_scolarisation;
    }

    public Fiche niveau_scolarisation(eniveauscolarisation niveau_scolarisation) {
        this.setNiveau_scolarisation(niveau_scolarisation);
        return this;
    }

    public void setNiveau_scolarisation(eniveauscolarisation niveau_scolarisation) {
        this.niveau_scolarisation = niveau_scolarisation;
    }

    public ecasfamiliaux getCas_familiaux() {
        return this.cas_familiaux;
    }

    public Fiche cas_familiaux(ecasfamiliaux cas_familiaux) {
        this.setCas_familiaux(cas_familiaux);
        return this;
    }

    public void setCas_familiaux(ecasfamiliaux cas_familiaux) {
        this.cas_familiaux = cas_familiaux;
    }

    public Integer getNbcasconfirme() {
        return this.nbcasconfirme;
    }

    public Fiche nbcasconfirme(Integer nbcasconfirme) {
        this.setNbcasconfirme(nbcasconfirme);
        return this;
    }

    public void setNbcasconfirme(Integer nbcasconfirme) {
        this.nbcasconfirme = nbcasconfirme;
    }

    public Integer getNbcassuspectes() {
        return this.nbcassuspectes;
    }

    public Fiche nbcassuspectes(Integer nbcassuspectes) {
        this.setNbcassuspectes(nbcassuspectes);
        return this;
    }

    public void setNbcassuspectes(Integer nbcassuspectes) {
        this.nbcassuspectes = nbcassuspectes;
    }

    public Integer getNbcasdecedes() {
        return this.nbcasdecedes;
    }

    public Fiche nbcasdecedes(Integer nbcasdecedes) {
        this.setNbcasdecedes(nbcasdecedes);
        return this;
    }

    public void setNbcasdecedes(Integer nbcasdecedes) {
        this.nbcasdecedes = nbcasdecedes;
    }

    public edecesbasage getDeces_en_bas_age() {
        return this.deces_en_bas_age;
    }

    public Fiche deces_en_bas_age(edecesbasage deces_en_bas_age) {
        this.setDeces_en_bas_age(deces_en_bas_age);
        return this;
    }

    public void setDeces_en_bas_age(edecesbasage deces_en_bas_age) {
        this.deces_en_bas_age = deces_en_bas_age;
    }

    public Integer getNbcas_deces_age_bas() {
        return this.nbcas_deces_age_bas;
    }

    public Fiche nbcas_deces_age_bas(Integer nbcas_deces_age_bas) {
        this.setNbcas_deces_age_bas(nbcas_deces_age_bas);
        return this;
    }

    public void setNbcas_deces_age_bas(Integer nbcas_deces_age_bas) {
        this.nbcas_deces_age_bas = nbcas_deces_age_bas;
    }

    public ecircondecouverte getCirconstances_decouverte() {
        return this.circonstances_decouverte;
    }

    public Fiche circonstances_decouverte(ecircondecouverte circonstances_decouverte) {
        this.setCirconstances_decouverte(circonstances_decouverte);
        return this;
    }

    public void setCirconstances_decouverte(ecircondecouverte circonstances_decouverte) {
        this.circonstances_decouverte = circonstances_decouverte;
    }

    public eage_premier_symptome getAge_aux_premiers_symptomes() {
        return this.age_aux_premiers_symptomes;
    }

    public Fiche age_aux_premiers_symptomes(eage_premier_symptome age_aux_premiers_symptomes) {
        this.setAge_aux_premiers_symptomes(age_aux_premiers_symptomes);
        return this;
    }

    public void setAge_aux_premiers_symptomes(eage_premier_symptome age_aux_premiers_symptomes) {
        this.age_aux_premiers_symptomes = age_aux_premiers_symptomes;
    }

    public Integer getAn_age_premiers_symptomes() {
        return this.an_age_premiers_symptomes;
    }

    public Fiche an_age_premiers_symptomes(Integer an_age_premiers_symptomes) {
        this.setAn_age_premiers_symptomes(an_age_premiers_symptomes);
        return this;
    }

    public void setAn_age_premiers_symptomes(Integer an_age_premiers_symptomes) {
        this.an_age_premiers_symptomes = an_age_premiers_symptomes;
    }

    public Integer getMois_age_premiers_symptomes() {
        return this.mois_age_premiers_symptomes;
    }

    public Fiche mois_age_premiers_symptomes(Integer mois_age_premiers_symptomes) {
        this.setMois_age_premiers_symptomes(mois_age_premiers_symptomes);
        return this;
    }

    public void setMois_age_premiers_symptomes(Integer mois_age_premiers_symptomes) {
        this.mois_age_premiers_symptomes = mois_age_premiers_symptomes;
    }

    public Integer getJours_premiers_symptomes() {
        return this.jours_premiers_symptomes;
    }

    public Fiche jours_premiers_symptomes(Integer jours_premiers_symptomes) {
        this.setJours_premiers_symptomes(jours_premiers_symptomes);
        return this;
    }

    public void setJours_premiers_symptomes(Integer jours_premiers_symptomes) {
        this.jours_premiers_symptomes = jours_premiers_symptomes;
    }

    public eagepremiereconsultation getAge_premiere_consultation() {
        return this.age_premiere_consultation;
    }

    public Fiche age_premiere_consultation(eagepremiereconsultation age_premiere_consultation) {
        this.setAge_premiere_consultation(age_premiere_consultation);
        return this;
    }

    public void setAge_premiere_consultation(eagepremiereconsultation age_premiere_consultation) {
        this.age_premiere_consultation = age_premiere_consultation;
    }

    public Integer getAn_age_premiere_consultation() {
        return this.an_age_premiere_consultation;
    }

    public Fiche an_age_premiere_consultation(Integer an_age_premiere_consultation) {
        this.setAn_age_premiere_consultation(an_age_premiere_consultation);
        return this;
    }

    public void setAn_age_premiere_consultation(Integer an_age_premiere_consultation) {
        this.an_age_premiere_consultation = an_age_premiere_consultation;
    }

    public Integer getMois_age_premiere_consultation() {
        return this.mois_age_premiere_consultation;
    }

    public Fiche mois_age_premiere_consultation(Integer mois_age_premiere_consultation) {
        this.setMois_age_premiere_consultation(mois_age_premiere_consultation);
        return this;
    }

    public void setMois_age_premiere_consultation(Integer mois_age_premiere_consultation) {
        this.mois_age_premiere_consultation = mois_age_premiere_consultation;
    }

    public Integer getJours_premiere_consultation() {
        return this.jours_premiere_consultation;
    }

    public Fiche jours_premiere_consultation(Integer jours_premiere_consultation) {
        this.setJours_premiere_consultation(jours_premiere_consultation);
        return this;
    }

    public void setJours_premiere_consultation(Integer jours_premiere_consultation) {
        this.jours_premiere_consultation = jours_premiere_consultation;
    }

    public LocalDate getDate_derniere_evaluation() {
        return this.date_derniere_evaluation;
    }

    public Fiche date_derniere_evaluation(LocalDate date_derniere_evaluation) {
        this.setDate_derniere_evaluation(date_derniere_evaluation);
        return this;
    }

    public void setDate_derniere_evaluation(LocalDate date_derniere_evaluation) {
        this.date_derniere_evaluation = date_derniere_evaluation;
    }

    public LocalDate getDate_diagnostic() {
        return this.date_diagnostic;
    }

    public Fiche date_diagnostic(LocalDate date_diagnostic) {
        this.setDate_diagnostic(date_diagnostic);
        return this;
    }

    public void setDate_diagnostic(LocalDate date_diagnostic) {
        this.date_diagnostic = date_diagnostic;
    }

    public ehandicapmental getHandicap_mental() {
        return this.handicap_mental;
    }

    public Fiche handicap_mental(ehandicapmental handicap_mental) {
        this.setHandicap_mental(handicap_mental);
        return this;
    }

    public void setHandicap_mental(ehandicapmental handicap_mental) {
        this.handicap_mental = handicap_mental;
    }

    public eQI getQi() {
        return this.qi;
    }

    public Fiche qi(eQI qi) {
        this.setQi(qi);
        return this;
    }

    public void setQi(eQI qi) {
        this.qi = qi;
    }

    public eMoteur getHandicap_moteur() {
        return this.handicap_moteur;
    }

    public Fiche handicap_moteur(eMoteur handicap_moteur) {
        this.setHandicap_moteur(handicap_moteur);
        return this;
    }

    public void setHandicap_moteur(eMoteur handicap_moteur) {
        this.handicap_moteur = handicap_moteur;
    }

    public egrade getHadicap_moteur_grade() {
        return this.hadicap_moteur_grade;
    }

    public Fiche hadicap_moteur_grade(egrade hadicap_moteur_grade) {
        this.setHadicap_moteur_grade(hadicap_moteur_grade);
        return this;
    }

    public void setHadicap_moteur_grade(egrade hadicap_moteur_grade) {
        this.hadicap_moteur_grade = hadicap_moteur_grade;
    }

    public edeficitneuro getDeficit_neurosensoriel() {
        return this.deficit_neurosensoriel;
    }

    public Fiche deficit_neurosensoriel(edeficitneuro deficit_neurosensoriel) {
        this.setDeficit_neurosensoriel(deficit_neurosensoriel);
        return this;
    }

    public void setDeficit_neurosensoriel(edeficitneuro deficit_neurosensoriel) {
        this.deficit_neurosensoriel = deficit_neurosensoriel;
    }

    public edeficitneurosensorielval getDeficit_neurosensoriel_val() {
        return this.deficit_neurosensoriel_val;
    }

    public Fiche deficit_neurosensoriel_val(edeficitneurosensorielval deficit_neurosensoriel_val) {
        this.setDeficit_neurosensoriel_val(deficit_neurosensoriel_val);
        return this;
    }

    public void setDeficit_neurosensoriel_val(edeficitneurosensorielval deficit_neurosensoriel_val) {
        this.deficit_neurosensoriel_val = deficit_neurosensoriel_val;
    }

    public edeficiencepsychique getDeficience_psychique() {
        return this.deficience_psychique;
    }

    public Fiche deficience_psychique(edeficiencepsychique deficience_psychique) {
        this.setDeficience_psychique(deficience_psychique);
        return this;
    }

    public void setDeficience_psychique(edeficiencepsychique deficience_psychique) {
        this.deficience_psychique = deficience_psychique;
    }

    public edeficiencepsychiqueval getDeficience_psychique_val() {
        return this.deficience_psychique_val;
    }

    public Fiche deficience_psychique_val(edeficiencepsychiqueval deficience_psychique_val) {
        this.setDeficience_psychique_val(deficience_psychique_val);
        return this;
    }

    public void setDeficience_psychique_val(edeficiencepsychiqueval deficience_psychique_val) {
        this.deficience_psychique_val = deficience_psychique_val;
    }

    public String getAutre_deficience_psychique() {
        return this.autre_deficience_psychique;
    }

    public Fiche autre_deficience_psychique(String autre_deficience_psychique) {
        this.setAutre_deficience_psychique(autre_deficience_psychique);
        return this;
    }

    public void setAutre_deficience_psychique(String autre_deficience_psychique) {
        this.autre_deficience_psychique = autre_deficience_psychique;
    }

    public eregime getRegime() {
        return this.regime;
    }

    public Fiche regime(eregime regime) {
        this.setRegime(regime);
        return this;
    }

    public void setRegime(eregime regime) {
        this.regime = regime;
    }

    public eregimeval getRegime_val() {
        return this.regime_val;
    }

    public Fiche regime_val(eregimeval regime_val) {
        this.setRegime_val(regime_val);
        return this;
    }

    public void setRegime_val(eregimeval regime_val) {
        this.regime_val = regime_val;
    }

    public emedicamentspecifique getMedicament_specifique() {
        return this.medicament_specifique;
    }

    public Fiche medicament_specifique(emedicamentspecifique medicament_specifique) {
        this.setMedicament_specifique(medicament_specifique);
        return this;
    }

    public void setMedicament_specifique(emedicamentspecifique medicament_specifique) {
        this.medicament_specifique = medicament_specifique;
    }

    public emedicamentspecifiqueval getMedicament_specifique_val() {
        return this.medicament_specifique_val;
    }

    public Fiche medicament_specifique_val(emedicamentspecifiqueval medicament_specifique_val) {
        this.setMedicament_specifique_val(medicament_specifique_val);
        return this;
    }

    public void setMedicament_specifique_val(emedicamentspecifiqueval medicament_specifique_val) {
        this.medicament_specifique_val = medicament_specifique_val;
    }

    public String getAutre_medicament_specifique() {
        return this.autre_medicament_specifique;
    }

    public Fiche autre_medicament_specifique(String autre_medicament_specifique) {
        this.setAutre_medicament_specifique(autre_medicament_specifique);
        return this;
    }

    public void setAutre_medicament_specifique(String autre_medicament_specifique) {
        this.autre_medicament_specifique = autre_medicament_specifique;
    }

    public evitamines getVitamines() {
        return this.vitamines;
    }

    public Fiche vitamines(evitamines vitamines) {
        this.setVitamines(vitamines);
        return this;
    }

    public void setVitamines(evitamines vitamines) {
        this.vitamines = vitamines;
    }

    public evitamineval getVitamines_val() {
        return this.vitamines_val;
    }

    public Fiche vitamines_val(evitamineval vitamines_val) {
        this.setVitamines_val(vitamines_val);
        return this;
    }

    public void setVitamines_val(evitamineval vitamines_val) {
        this.vitamines_val = vitamines_val;
    }

    public egreffehepatique getGreffehepatique() {
        return this.greffehepatique;
    }

    public Fiche greffehepatique(egreffehepatique greffehepatique) {
        this.setGreffehepatique(greffehepatique);
        return this;
    }

    public void setGreffehepatique(egreffehepatique greffehepatique) {
        this.greffehepatique = greffehepatique;
    }

    public erededucationfonctionnelle getReeducation_fonctionnelle() {
        return this.reeducation_fonctionnelle;
    }

    public Fiche reeducation_fonctionnelle(erededucationfonctionnelle reeducation_fonctionnelle) {
        this.setReeducation_fonctionnelle(reeducation_fonctionnelle);
        return this;
    }

    public void setReeducation_fonctionnelle(erededucationfonctionnelle reeducation_fonctionnelle) {
        this.reeducation_fonctionnelle = reeducation_fonctionnelle;
    }

    public eappareillage getAppareillage() {
        return this.appareillage;
    }

    public Fiche appareillage(eappareillage appareillage) {
        this.setAppareillage(appareillage);
        return this;
    }

    public void setAppareillage(eappareillage appareillage) {
        this.appareillage = appareillage;
    }

    public epsychologie getPsuchologie() {
        return this.psuchologie;
    }

    public Fiche psuchologie(epsychologie psuchologie) {
        this.setPsuchologie(psuchologie);
        return this;
    }

    public void setPsuchologie(epsychologie psuchologie) {
        this.psuchologie = psuchologie;
    }

    public eergotherapie getErgotherapie() {
        return this.ergotherapie;
    }

    public Fiche ergotherapie(eergotherapie ergotherapie) {
        this.setErgotherapie(ergotherapie);
        return this;
    }

    public void setErgotherapie(eergotherapie ergotherapie) {
        this.ergotherapie = ergotherapie;
    }

    public edepistage_post_natal_oriente getDepistage_post_natal_oriente() {
        return this.depistage_post_natal_oriente;
    }

    public Fiche depistage_post_natal_oriente(edepistage_post_natal_oriente depistage_post_natal_oriente) {
        this.setDepistage_post_natal_oriente(depistage_post_natal_oriente);
        return this;
    }

    public void setDepistage_post_natal_oriente(edepistage_post_natal_oriente depistage_post_natal_oriente) {
        this.depistage_post_natal_oriente = depistage_post_natal_oriente;
    }

    public eechelledepistage getEchelle_depistage() {
        return this.echelle_depistage;
    }

    public Fiche echelle_depistage(eechelledepistage echelle_depistage) {
        this.setEchelle_depistage(echelle_depistage);
        return this;
    }

    public void setEchelle_depistage(eechelledepistage echelle_depistage) {
        this.echelle_depistage = echelle_depistage;
    }

    public Integer getNombre_individus_depistes() {
        return this.nombre_individus_depistes;
    }

    public Fiche nombre_individus_depistes(Integer nombre_individus_depistes) {
        this.setNombre_individus_depistes(nombre_individus_depistes);
        return this;
    }

    public void setNombre_individus_depistes(Integer nombre_individus_depistes) {
        this.nombre_individus_depistes = nombre_individus_depistes;
    }

    public enouveaux_cas_depistes getNouveaux_cas_depistes() {
        return this.nouveaux_cas_depistes;
    }

    public Fiche nouveaux_cas_depistes(enouveaux_cas_depistes nouveaux_cas_depistes) {
        this.setNouveaux_cas_depistes(nouveaux_cas_depistes);
        return this;
    }

    public void setNouveaux_cas_depistes(enouveaux_cas_depistes nouveaux_cas_depistes) {
        this.nouveaux_cas_depistes = nouveaux_cas_depistes;
    }

    public Integer getNombre_nouveaux_cas_depistes() {
        return this.nombre_nouveaux_cas_depistes;
    }

    public Fiche nombre_nouveaux_cas_depistes(Integer nombre_nouveaux_cas_depistes) {
        this.setNombre_nouveaux_cas_depistes(nombre_nouveaux_cas_depistes);
        return this;
    }

    public void setNombre_nouveaux_cas_depistes(Integer nombre_nouveaux_cas_depistes) {
        this.nombre_nouveaux_cas_depistes = nombre_nouveaux_cas_depistes;
    }

    public String getCode_registre1_cas_depistes() {
        return this.code_registre1_cas_depistes;
    }

    public Fiche code_registre1_cas_depistes(String code_registre1_cas_depistes) {
        this.setCode_registre1_cas_depistes(code_registre1_cas_depistes);
        return this;
    }

    public void setCode_registre1_cas_depistes(String code_registre1_cas_depistes) {
        this.code_registre1_cas_depistes = code_registre1_cas_depistes;
    }

    public elienparente1 getLien_parente1_cas_depistes() {
        return this.lien_parente1_cas_depistes;
    }

    public Fiche lien_parente1_cas_depistes(elienparente1 lien_parente1_cas_depistes) {
        this.setLien_parente1_cas_depistes(lien_parente1_cas_depistes);
        return this;
    }

    public void setLien_parente1_cas_depistes(elienparente1 lien_parente1_cas_depistes) {
        this.lien_parente1_cas_depistes = lien_parente1_cas_depistes;
    }

    public String getAutre_lien_parente1() {
        return this.autre_lien_parente1;
    }

    public Fiche autre_lien_parente1(String autre_lien_parente1) {
        this.setAutre_lien_parente1(autre_lien_parente1);
        return this;
    }

    public void setAutre_lien_parente1(String autre_lien_parente1) {
        this.autre_lien_parente1 = autre_lien_parente1;
    }

    public String getCode_registre2_cas_depistes() {
        return this.code_registre2_cas_depistes;
    }

    public Fiche code_registre2_cas_depistes(String code_registre2_cas_depistes) {
        this.setCode_registre2_cas_depistes(code_registre2_cas_depistes);
        return this;
    }

    public void setCode_registre2_cas_depistes(String code_registre2_cas_depistes) {
        this.code_registre2_cas_depistes = code_registre2_cas_depistes;
    }

    public elienparente2 getLien_parente2_cas_depistes() {
        return this.lien_parente2_cas_depistes;
    }

    public Fiche lien_parente2_cas_depistes(elienparente2 lien_parente2_cas_depistes) {
        this.setLien_parente2_cas_depistes(lien_parente2_cas_depistes);
        return this;
    }

    public void setLien_parente2_cas_depistes(elienparente2 lien_parente2_cas_depistes) {
        this.lien_parente2_cas_depistes = lien_parente2_cas_depistes;
    }

    public String getAutre_lien_parente2() {
        return this.autre_lien_parente2;
    }

    public Fiche autre_lien_parente2(String autre_lien_parente2) {
        this.setAutre_lien_parente2(autre_lien_parente2);
        return this;
    }

    public void setAutre_lien_parente2(String autre_lien_parente2) {
        this.autre_lien_parente2 = autre_lien_parente2;
    }

    public Integer getNombre_de_grossesse_ulterieures() {
        return this.nombre_de_grossesse_ulterieures;
    }

    public Fiche nombre_de_grossesse_ulterieures(Integer nombre_de_grossesse_ulterieures) {
        this.setNombre_de_grossesse_ulterieures(nombre_de_grossesse_ulterieures);
        return this;
    }

    public void setNombre_de_grossesse_ulterieures(Integer nombre_de_grossesse_ulterieures) {
        this.nombre_de_grossesse_ulterieures = nombre_de_grossesse_ulterieures;
    }

    public Integer getNomre_DPN() {
        return this.nomre_DPN;
    }

    public Fiche nomre_DPN(Integer nomre_DPN) {
        this.setNomre_DPN(nomre_DPN);
        return this;
    }

    public void setNomre_DPN(Integer nomre_DPN) {
        this.nomre_DPN = nomre_DPN;
    }

    public Integer getNombre_nouveaux_cas_diagnostiques() {
        return this.nombre_nouveaux_cas_diagnostiques;
    }

    public Fiche nombre_nouveaux_cas_diagnostiques(Integer nombre_nouveaux_cas_diagnostiques) {
        this.setNombre_nouveaux_cas_diagnostiques(nombre_nouveaux_cas_diagnostiques);
        return this;
    }

    public void setNombre_nouveaux_cas_diagnostiques(Integer nombre_nouveaux_cas_diagnostiques) {
        this.nombre_nouveaux_cas_diagnostiques = nombre_nouveaux_cas_diagnostiques;
    }

    public Integer getNombre_ITG() {
        return this.nombre_ITG;
    }

    public Fiche nombre_ITG(Integer nombre_ITG) {
        this.setNombre_ITG(nombre_ITG);
        return this;
    }

    public void setNombre_ITG(Integer nombre_ITG) {
        this.nombre_ITG = nombre_ITG;
    }

    public Integer getNomre_de_grossesses_poursuivies() {
        return this.nomre_de_grossesses_poursuivies;
    }

    public Fiche nomre_de_grossesses_poursuivies(Integer nomre_de_grossesses_poursuivies) {
        this.setNomre_de_grossesses_poursuivies(nomre_de_grossesses_poursuivies);
        return this;
    }

    public void setNomre_de_grossesses_poursuivies(Integer nomre_de_grossesses_poursuivies) {
        this.nomre_de_grossesses_poursuivies = nomre_de_grossesses_poursuivies;
    }

    public Integer getNombre_de_foetus_sains() {
        return this.nombre_de_foetus_sains;
    }

    public Fiche nombre_de_foetus_sains(Integer nombre_de_foetus_sains) {
        this.setNombre_de_foetus_sains(nombre_de_foetus_sains);
        return this;
    }

    public void setNombre_de_foetus_sains(Integer nombre_de_foetus_sains) {
        this.nombre_de_foetus_sains = nombre_de_foetus_sains;
    }

    public Pathologie getPathologie() {
        return this.pathologie;
    }

    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
    }

    public Fiche pathologie(Pathologie pathologie) {
        this.setPathologie(pathologie);
        return this;
    }

    public Set<Casconfirme> getCasconfirmes() {
        return this.casconfirmes;
    }

    public void setCasconfirmes(Set<Casconfirme> casconfirmes) {
        if (this.casconfirmes != null) {
            this.casconfirmes.forEach(i -> i.setFiche(null));
        }
        if (casconfirmes != null) {
            casconfirmes.forEach(i -> i.setFiche(this));
        }
        this.casconfirmes = casconfirmes;
    }

    public Fiche casconfirmes(Set<Casconfirme> casconfirmes) {
        this.setCasconfirmes(casconfirmes);
        return this;
    }

    public Fiche addCasconfirme(Casconfirme casconfirme) {
        this.casconfirmes.add(casconfirme);
        casconfirme.setFiche(this);
        return this;
    }

    public Fiche removeCasconfirme(Casconfirme casconfirme) {
        this.casconfirmes.remove(casconfirme);
        casconfirme.setFiche(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fiche)) {
            return false;
        }
        return id != null && id.equals(((Fiche) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fiche{" +
            "id=" + getId() +
            ", datemaj='" + getDatemaj() + "'" +
            ", type_observation='" + getType_observation() + "'" +
            ", identifiant_registre='" + getIdentifiant_registre() + "'" +
            ", date_enregistrement='" + getDate_enregistrement() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", date_naissance='" + getDate_naissance() + "'" +
            ", statut='" + getStatut() + "'" +
            ", date_deces='" + getDate_deces() + "'" +
            ", circonstance_deces='" + getCirconstance_deces() + "'" +
            ", autre_circonstance_deces='" + getAutre_circonstance_deces() + "'" +
            ", lieu_deces='" + getLieu_deces() + "'" +
            ", consanguinite='" + getConsanguinite() + "'" +
            ", origine_geo_pere_gouvernorat='" + getOrigine_geo_pere_gouvernorat() + "'" +
            ", origine_geo_mere_gouvernorat='" + getOrigine_geo_mere_gouvernorat() + "'" +
            ", origine_geo_pere_deleguation='" + getOrigine_geo_pere_deleguation() + "'" +
            ", origine_geo_mere_deleguation='" + getOrigine_geo_mere_deleguation() + "'" +
            ", couverture_sociale='" + getCouverture_sociale() + "'" +
            ", autre_couverture_sociale='" + getAutre_couverture_sociale() + "'" +
            ", activite='" + getActivite() + "'" +
            ", btravail='" + getBtravail() + "'" +
            ", travail='" + getTravail() + "'" +
            ", scolarise='" + getScolarise() + "'" +
            ", type_scolarise='" + getType_scolarise() + "'" +
            ", niveau_scolarisation='" + getNiveau_scolarisation() + "'" +
            ", cas_familiaux='" + getCas_familiaux() + "'" +
            ", nbcasconfirme=" + getNbcasconfirme() +
            ", nbcassuspectes=" + getNbcassuspectes() +
            ", nbcasdecedes=" + getNbcasdecedes() +
            ", deces_en_bas_age='" + getDeces_en_bas_age() + "'" +
            ", nbcas_deces_age_bas=" + getNbcas_deces_age_bas() +
            ", circonstances_decouverte='" + getCirconstances_decouverte() + "'" +
            ", age_aux_premiers_symptomes='" + getAge_aux_premiers_symptomes() + "'" +
            ", an_age_premiers_symptomes=" + getAn_age_premiers_symptomes() +
            ", mois_age_premiers_symptomes=" + getMois_age_premiers_symptomes() +
            ", jours_premiers_symptomes=" + getJours_premiers_symptomes() +
            ", age_premiere_consultation='" + getAge_premiere_consultation() + "'" +
            ", an_age_premiere_consultation=" + getAn_age_premiere_consultation() +
            ", mois_age_premiere_consultation=" + getMois_age_premiere_consultation() +
            ", jours_premiere_consultation=" + getJours_premiere_consultation() +
            ", date_derniere_evaluation='" + getDate_derniere_evaluation() + "'" +
            ", date_diagnostic='" + getDate_diagnostic() + "'" +
            ", handicap_mental='" + getHandicap_mental() + "'" +
            ", qi='" + getQi() + "'" +
            ", handicap_moteur='" + getHandicap_moteur() + "'" +
            ", hadicap_moteur_grade='" + getHadicap_moteur_grade() + "'" +
            ", deficit_neurosensoriel='" + getDeficit_neurosensoriel() + "'" +
            ", deficit_neurosensoriel_val='" + getDeficit_neurosensoriel_val() + "'" +
            ", deficience_psychique='" + getDeficience_psychique() + "'" +
            ", deficience_psychique_val='" + getDeficience_psychique_val() + "'" +
            ", autre_deficience_psychique='" + getAutre_deficience_psychique() + "'" +
            ", regime='" + getRegime() + "'" +
            ", regime_val='" + getRegime_val() + "'" +
            ", medicament_specifique='" + getMedicament_specifique() + "'" +
            ", medicament_specifique_val='" + getMedicament_specifique_val() + "'" +
            ", autre_medicament_specifique='" + getAutre_medicament_specifique() + "'" +
            ", vitamines='" + getVitamines() + "'" +
            ", vitamines_val='" + getVitamines_val() + "'" +
            ", greffehepatique='" + getGreffehepatique() + "'" +
            ", reeducation_fonctionnelle='" + getReeducation_fonctionnelle() + "'" +
            ", appareillage='" + getAppareillage() + "'" +
            ", psuchologie='" + getPsuchologie() + "'" +
            ", ergotherapie='" + getErgotherapie() + "'" +
            ", depistage_post_natal_oriente='" + getDepistage_post_natal_oriente() + "'" +
            ", echelle_depistage='" + getEchelle_depistage() + "'" +
            ", nombre_individus_depistes=" + getNombre_individus_depistes() +
            ", nouveaux_cas_depistes='" + getNouveaux_cas_depistes() + "'" +
            ", nombre_nouveaux_cas_depistes=" + getNombre_nouveaux_cas_depistes() +
            ", code_registre1_cas_depistes='" + getCode_registre1_cas_depistes() + "'" +
            ", lien_parente1_cas_depistes='" + getLien_parente1_cas_depistes() + "'" +
            ", autre_lien_parente1='" + getAutre_lien_parente1() + "'" +
            ", code_registre2_cas_depistes='" + getCode_registre2_cas_depistes() + "'" +
            ", lien_parente2_cas_depistes='" + getLien_parente2_cas_depistes() + "'" +
            ", autre_lien_parente2='" + getAutre_lien_parente2() + "'" +
            ", nombre_de_grossesse_ulterieures=" + getNombre_de_grossesse_ulterieures() +
            ", nomre_DPN=" + getNomre_DPN() +
            ", nombre_nouveaux_cas_diagnostiques=" + getNombre_nouveaux_cas_diagnostiques() +
            ", nombre_ITG=" + getNombre_ITG() +
            ", nomre_de_grossesses_poursuivies=" + getNomre_de_grossesses_poursuivies() +
            ", nombre_de_foetus_sains=" + getNombre_de_foetus_sains() +
            "}";
    }
}
