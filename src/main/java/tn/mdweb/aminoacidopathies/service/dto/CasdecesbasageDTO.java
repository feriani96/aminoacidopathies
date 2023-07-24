package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import tn.mdweb.aminoacidopathies.domain.enumeration.elienparente;
import tn.mdweb.aminoacidopathies.domain.enumeration.elieudeces;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Casdecesbasage} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CasdecesbasageDTO implements Serializable {

    private Long id;

    private Boolean confirme;

    private String code_registre;

    private Boolean suspecte;

    private elienparente lien_de_parente;

    private String autre_lien_parente;

    private Integer an_age_de_deces;

    private Integer mois_age_de_deces;

    private Integer jours_age_de_deces;

    private Boolean tbl_neuro;

    private Boolean tbl_hemorragique;

    private Boolean tbl_infx;

    private String autre_circonstances_deces;

    private Boolean bautre_circonstance_deces;

    private Boolean np_circonstances_deces;

    private elieudeces lieu_deces;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getConfirme() {
        return confirme;
    }

    public void setConfirme(Boolean confirme) {
        this.confirme = confirme;
    }

    public String getCode_registre() {
        return code_registre;
    }

    public void setCode_registre(String code_registre) {
        this.code_registre = code_registre;
    }

    public Boolean getSuspecte() {
        return suspecte;
    }

    public void setSuspecte(Boolean suspecte) {
        this.suspecte = suspecte;
    }

    public elienparente getLien_de_parente() {
        return lien_de_parente;
    }

    public void setLien_de_parente(elienparente lien_de_parente) {
        this.lien_de_parente = lien_de_parente;
    }

    public String getAutre_lien_parente() {
        return autre_lien_parente;
    }

    public void setAutre_lien_parente(String autre_lien_parente) {
        this.autre_lien_parente = autre_lien_parente;
    }

    public Integer getAn_age_de_deces() {
        return an_age_de_deces;
    }

    public void setAn_age_de_deces(Integer an_age_de_deces) {
        this.an_age_de_deces = an_age_de_deces;
    }

    public Integer getMois_age_de_deces() {
        return mois_age_de_deces;
    }

    public void setMois_age_de_deces(Integer mois_age_de_deces) {
        this.mois_age_de_deces = mois_age_de_deces;
    }

    public Integer getJours_age_de_deces() {
        return jours_age_de_deces;
    }

    public void setJours_age_de_deces(Integer jours_age_de_deces) {
        this.jours_age_de_deces = jours_age_de_deces;
    }

    public Boolean getTbl_neuro() {
        return tbl_neuro;
    }

    public void setTbl_neuro(Boolean tbl_neuro) {
        this.tbl_neuro = tbl_neuro;
    }

    public Boolean getTbl_hemorragique() {
        return tbl_hemorragique;
    }

    public void setTbl_hemorragique(Boolean tbl_hemorragique) {
        this.tbl_hemorragique = tbl_hemorragique;
    }

    public Boolean getTbl_infx() {
        return tbl_infx;
    }

    public void setTbl_infx(Boolean tbl_infx) {
        this.tbl_infx = tbl_infx;
    }

    public String getAutre_circonstances_deces() {
        return autre_circonstances_deces;
    }

    public void setAutre_circonstances_deces(String autre_circonstances_deces) {
        this.autre_circonstances_deces = autre_circonstances_deces;
    }

    public Boolean getBautre_circonstance_deces() {
        return bautre_circonstance_deces;
    }

    public void setBautre_circonstance_deces(Boolean bautre_circonstance_deces) {
        this.bautre_circonstance_deces = bautre_circonstance_deces;
    }

    public Boolean getNp_circonstances_deces() {
        return np_circonstances_deces;
    }

    public void setNp_circonstances_deces(Boolean np_circonstances_deces) {
        this.np_circonstances_deces = np_circonstances_deces;
    }

    public elieudeces getLieu_deces() {
        return lieu_deces;
    }

    public void setLieu_deces(elieudeces lieu_deces) {
        this.lieu_deces = lieu_deces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CasdecesbasageDTO)) {
            return false;
        }

        CasdecesbasageDTO casdecesbasageDTO = (CasdecesbasageDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, casdecesbasageDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CasdecesbasageDTO{" +
            "id=" + getId() +
            ", confirme='" + getConfirme() + "'" +
            ", code_registre='" + getCode_registre() + "'" +
            ", suspecte='" + getSuspecte() + "'" +
            ", lien_de_parente='" + getLien_de_parente() + "'" +
            ", autre_lien_parente='" + getAutre_lien_parente() + "'" +
            ", an_age_de_deces=" + getAn_age_de_deces() +
            ", mois_age_de_deces=" + getMois_age_de_deces() +
            ", jours_age_de_deces=" + getJours_age_de_deces() +
            ", tbl_neuro='" + getTbl_neuro() + "'" +
            ", tbl_hemorragique='" + getTbl_hemorragique() + "'" +
            ", tbl_infx='" + getTbl_infx() + "'" +
            ", autre_circonstances_deces='" + getAutre_circonstances_deces() + "'" +
            ", bautre_circonstance_deces='" + getBautre_circonstance_deces() + "'" +
            ", np_circonstances_deces='" + getNp_circonstances_deces() + "'" +
            ", lieu_deces='" + getLieu_deces() + "'" +
            "}";
    }
}
