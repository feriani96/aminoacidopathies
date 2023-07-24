package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import tn.mdweb.aminoacidopathies.domain.enumeration.elienparente;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Cassuspecte} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CassuspecteDTO implements Serializable {

    private Long id;

    private elienparente lienparente;

    private String lienparenteautre;

    private Boolean signes_neurologiques;

    private Boolean troubles_de_la_conscience;

    private Boolean retard_psychomoteur;

    private Boolean retard_mental;

    private Boolean signes_du_spectre_autistique;

    private Boolean epilepsie;

    private Boolean crise_pseudoporphyrique;

    private String autres_signes_neurologiques;

    private Boolean signes_hepatiques;

    private Boolean ictere;

    private Boolean ballonnement;

    private Boolean syndrome_hemorragique;

    private String autres_signes_hepatiques;

    private Boolean signes_osseux;

    private Boolean signes_de_rachitisme;

    private String autre_signes_osseux;

    private Boolean manifestations_thrombotiques;

    private Boolean cerebrale;

    private String autre_manifestations_thrombotiques;

    private Boolean manifestations_ophtalmologiques;

    private Boolean luxation;

    private Boolean ectopie_cristalinienne;

    private Boolean cataracte;

    private Boolean glaucome;

    private Boolean myopie;

    private String manifestations_ophtalmologiques_autre;

    private Boolean autre_criteres;

    private String str_autres_criteres;

    private Boolean critere_non_precise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public elienparente getLienparente() {
        return lienparente;
    }

    public void setLienparente(elienparente lienparente) {
        this.lienparente = lienparente;
    }

    public String getLienparenteautre() {
        return lienparenteautre;
    }

    public void setLienparenteautre(String lienparenteautre) {
        this.lienparenteautre = lienparenteautre;
    }

    public Boolean getSignes_neurologiques() {
        return signes_neurologiques;
    }

    public void setSignes_neurologiques(Boolean signes_neurologiques) {
        this.signes_neurologiques = signes_neurologiques;
    }

    public Boolean getTroubles_de_la_conscience() {
        return troubles_de_la_conscience;
    }

    public void setTroubles_de_la_conscience(Boolean troubles_de_la_conscience) {
        this.troubles_de_la_conscience = troubles_de_la_conscience;
    }

    public Boolean getRetard_psychomoteur() {
        return retard_psychomoteur;
    }

    public void setRetard_psychomoteur(Boolean retard_psychomoteur) {
        this.retard_psychomoteur = retard_psychomoteur;
    }

    public Boolean getRetard_mental() {
        return retard_mental;
    }

    public void setRetard_mental(Boolean retard_mental) {
        this.retard_mental = retard_mental;
    }

    public Boolean getSignes_du_spectre_autistique() {
        return signes_du_spectre_autistique;
    }

    public void setSignes_du_spectre_autistique(Boolean signes_du_spectre_autistique) {
        this.signes_du_spectre_autistique = signes_du_spectre_autistique;
    }

    public Boolean getEpilepsie() {
        return epilepsie;
    }

    public void setEpilepsie(Boolean epilepsie) {
        this.epilepsie = epilepsie;
    }

    public Boolean getCrise_pseudoporphyrique() {
        return crise_pseudoporphyrique;
    }

    public void setCrise_pseudoporphyrique(Boolean crise_pseudoporphyrique) {
        this.crise_pseudoporphyrique = crise_pseudoporphyrique;
    }

    public String getAutres_signes_neurologiques() {
        return autres_signes_neurologiques;
    }

    public void setAutres_signes_neurologiques(String autres_signes_neurologiques) {
        this.autres_signes_neurologiques = autres_signes_neurologiques;
    }

    public Boolean getSignes_hepatiques() {
        return signes_hepatiques;
    }

    public void setSignes_hepatiques(Boolean signes_hepatiques) {
        this.signes_hepatiques = signes_hepatiques;
    }

    public Boolean getIctere() {
        return ictere;
    }

    public void setIctere(Boolean ictere) {
        this.ictere = ictere;
    }

    public Boolean getBallonnement() {
        return ballonnement;
    }

    public void setBallonnement(Boolean ballonnement) {
        this.ballonnement = ballonnement;
    }

    public Boolean getSyndrome_hemorragique() {
        return syndrome_hemorragique;
    }

    public void setSyndrome_hemorragique(Boolean syndrome_hemorragique) {
        this.syndrome_hemorragique = syndrome_hemorragique;
    }

    public String getAutres_signes_hepatiques() {
        return autres_signes_hepatiques;
    }

    public void setAutres_signes_hepatiques(String autres_signes_hepatiques) {
        this.autres_signes_hepatiques = autres_signes_hepatiques;
    }

    public Boolean getSignes_osseux() {
        return signes_osseux;
    }

    public void setSignes_osseux(Boolean signes_osseux) {
        this.signes_osseux = signes_osseux;
    }

    public Boolean getSignes_de_rachitisme() {
        return signes_de_rachitisme;
    }

    public void setSignes_de_rachitisme(Boolean signes_de_rachitisme) {
        this.signes_de_rachitisme = signes_de_rachitisme;
    }

    public String getAutre_signes_osseux() {
        return autre_signes_osseux;
    }

    public void setAutre_signes_osseux(String autre_signes_osseux) {
        this.autre_signes_osseux = autre_signes_osseux;
    }

    public Boolean getManifestations_thrombotiques() {
        return manifestations_thrombotiques;
    }

    public void setManifestations_thrombotiques(Boolean manifestations_thrombotiques) {
        this.manifestations_thrombotiques = manifestations_thrombotiques;
    }

    public Boolean getCerebrale() {
        return cerebrale;
    }

    public void setCerebrale(Boolean cerebrale) {
        this.cerebrale = cerebrale;
    }

    public String getAutre_manifestations_thrombotiques() {
        return autre_manifestations_thrombotiques;
    }

    public void setAutre_manifestations_thrombotiques(String autre_manifestations_thrombotiques) {
        this.autre_manifestations_thrombotiques = autre_manifestations_thrombotiques;
    }

    public Boolean getManifestations_ophtalmologiques() {
        return manifestations_ophtalmologiques;
    }

    public void setManifestations_ophtalmologiques(Boolean manifestations_ophtalmologiques) {
        this.manifestations_ophtalmologiques = manifestations_ophtalmologiques;
    }

    public Boolean getLuxation() {
        return luxation;
    }

    public void setLuxation(Boolean luxation) {
        this.luxation = luxation;
    }

    public Boolean getEctopie_cristalinienne() {
        return ectopie_cristalinienne;
    }

    public void setEctopie_cristalinienne(Boolean ectopie_cristalinienne) {
        this.ectopie_cristalinienne = ectopie_cristalinienne;
    }

    public Boolean getCataracte() {
        return cataracte;
    }

    public void setCataracte(Boolean cataracte) {
        this.cataracte = cataracte;
    }

    public Boolean getGlaucome() {
        return glaucome;
    }

    public void setGlaucome(Boolean glaucome) {
        this.glaucome = glaucome;
    }

    public Boolean getMyopie() {
        return myopie;
    }

    public void setMyopie(Boolean myopie) {
        this.myopie = myopie;
    }

    public String getManifestations_ophtalmologiques_autre() {
        return manifestations_ophtalmologiques_autre;
    }

    public void setManifestations_ophtalmologiques_autre(String manifestations_ophtalmologiques_autre) {
        this.manifestations_ophtalmologiques_autre = manifestations_ophtalmologiques_autre;
    }

    public Boolean getAutre_criteres() {
        return autre_criteres;
    }

    public void setAutre_criteres(Boolean autre_criteres) {
        this.autre_criteres = autre_criteres;
    }

    public String getStr_autres_criteres() {
        return str_autres_criteres;
    }

    public void setStr_autres_criteres(String str_autres_criteres) {
        this.str_autres_criteres = str_autres_criteres;
    }

    public Boolean getCritere_non_precise() {
        return critere_non_precise;
    }

    public void setCritere_non_precise(Boolean critere_non_precise) {
        this.critere_non_precise = critere_non_precise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CassuspecteDTO)) {
            return false;
        }

        CassuspecteDTO cassuspecteDTO = (CassuspecteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cassuspecteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CassuspecteDTO{" +
            "id=" + getId() +
            ", lienparente='" + getLienparente() + "'" +
            ", lienparenteautre='" + getLienparenteautre() + "'" +
            ", signes_neurologiques='" + getSignes_neurologiques() + "'" +
            ", troubles_de_la_conscience='" + getTroubles_de_la_conscience() + "'" +
            ", retard_psychomoteur='" + getRetard_psychomoteur() + "'" +
            ", retard_mental='" + getRetard_mental() + "'" +
            ", signes_du_spectre_autistique='" + getSignes_du_spectre_autistique() + "'" +
            ", epilepsie='" + getEpilepsie() + "'" +
            ", crise_pseudoporphyrique='" + getCrise_pseudoporphyrique() + "'" +
            ", autres_signes_neurologiques='" + getAutres_signes_neurologiques() + "'" +
            ", signes_hepatiques='" + getSignes_hepatiques() + "'" +
            ", ictere='" + getIctere() + "'" +
            ", ballonnement='" + getBallonnement() + "'" +
            ", syndrome_hemorragique='" + getSyndrome_hemorragique() + "'" +
            ", autres_signes_hepatiques='" + getAutres_signes_hepatiques() + "'" +
            ", signes_osseux='" + getSignes_osseux() + "'" +
            ", signes_de_rachitisme='" + getSignes_de_rachitisme() + "'" +
            ", autre_signes_osseux='" + getAutre_signes_osseux() + "'" +
            ", manifestations_thrombotiques='" + getManifestations_thrombotiques() + "'" +
            ", cerebrale='" + getCerebrale() + "'" +
            ", autre_manifestations_thrombotiques='" + getAutre_manifestations_thrombotiques() + "'" +
            ", manifestations_ophtalmologiques='" + getManifestations_ophtalmologiques() + "'" +
            ", luxation='" + getLuxation() + "'" +
            ", ectopie_cristalinienne='" + getEctopie_cristalinienne() + "'" +
            ", cataracte='" + getCataracte() + "'" +
            ", glaucome='" + getGlaucome() + "'" +
            ", myopie='" + getMyopie() + "'" +
            ", manifestations_ophtalmologiques_autre='" + getManifestations_ophtalmologiques_autre() + "'" +
            ", autre_criteres='" + getAutre_criteres() + "'" +
            ", str_autres_criteres='" + getStr_autres_criteres() + "'" +
            ", critere_non_precise='" + getCritere_non_precise() + "'" +
            "}";
    }
}
