package tn.mdweb.aminoacidopathies.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tn.mdweb.aminoacidopathies.domain.enumeration.elienparente;
import tn.mdweb.aminoacidopathies.domain.enumeration.elieudeces;

/**
 * A Casdecesbasage.
 */
@Entity
@Table(name = "casdecesbasage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Casdecesbasage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "confirme")
    private Boolean confirme;

    @Column(name = "code_registre")
    private String code_registre;

    @Column(name = "suspecte")
    private Boolean suspecte;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_de_parente")
    private elienparente lien_de_parente;

    @Column(name = "autre_lien_parente")
    private String autre_lien_parente;

    @Column(name = "an_age_de_deces")
    private Integer an_age_de_deces;

    @Column(name = "mois_age_de_deces")
    private Integer mois_age_de_deces;

    @Column(name = "jours_age_de_deces")
    private Integer jours_age_de_deces;

    @Column(name = "tbl_neuro")
    private Boolean tbl_neuro;

    @Column(name = "tbl_hemorragique")
    private Boolean tbl_hemorragique;

    @Column(name = "tbl_infx")
    private Boolean tbl_infx;

    @Column(name = "autre_circonstances_deces")
    private String autre_circonstances_deces;

    @Column(name = "bautre_circonstance_deces")
    private Boolean bautre_circonstance_deces;

    @Column(name = "np_circonstances_deces")
    private Boolean np_circonstances_deces;

    @Enumerated(EnumType.STRING)
    @Column(name = "lieu_deces")
    private elieudeces lieu_deces;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Casdecesbasage id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getConfirme() {
        return this.confirme;
    }

    public Casdecesbasage confirme(Boolean confirme) {
        this.setConfirme(confirme);
        return this;
    }

    public void setConfirme(Boolean confirme) {
        this.confirme = confirme;
    }

    public String getCode_registre() {
        return this.code_registre;
    }

    public Casdecesbasage code_registre(String code_registre) {
        this.setCode_registre(code_registre);
        return this;
    }

    public void setCode_registre(String code_registre) {
        this.code_registre = code_registre;
    }

    public Boolean getSuspecte() {
        return this.suspecte;
    }

    public Casdecesbasage suspecte(Boolean suspecte) {
        this.setSuspecte(suspecte);
        return this;
    }

    public void setSuspecte(Boolean suspecte) {
        this.suspecte = suspecte;
    }

    public elienparente getLien_de_parente() {
        return this.lien_de_parente;
    }

    public Casdecesbasage lien_de_parente(elienparente lien_de_parente) {
        this.setLien_de_parente(lien_de_parente);
        return this;
    }

    public void setLien_de_parente(elienparente lien_de_parente) {
        this.lien_de_parente = lien_de_parente;
    }

    public String getAutre_lien_parente() {
        return this.autre_lien_parente;
    }

    public Casdecesbasage autre_lien_parente(String autre_lien_parente) {
        this.setAutre_lien_parente(autre_lien_parente);
        return this;
    }

    public void setAutre_lien_parente(String autre_lien_parente) {
        this.autre_lien_parente = autre_lien_parente;
    }

    public Integer getAn_age_de_deces() {
        return this.an_age_de_deces;
    }

    public Casdecesbasage an_age_de_deces(Integer an_age_de_deces) {
        this.setAn_age_de_deces(an_age_de_deces);
        return this;
    }

    public void setAn_age_de_deces(Integer an_age_de_deces) {
        this.an_age_de_deces = an_age_de_deces;
    }

    public Integer getMois_age_de_deces() {
        return this.mois_age_de_deces;
    }

    public Casdecesbasage mois_age_de_deces(Integer mois_age_de_deces) {
        this.setMois_age_de_deces(mois_age_de_deces);
        return this;
    }

    public void setMois_age_de_deces(Integer mois_age_de_deces) {
        this.mois_age_de_deces = mois_age_de_deces;
    }

    public Integer getJours_age_de_deces() {
        return this.jours_age_de_deces;
    }

    public Casdecesbasage jours_age_de_deces(Integer jours_age_de_deces) {
        this.setJours_age_de_deces(jours_age_de_deces);
        return this;
    }

    public void setJours_age_de_deces(Integer jours_age_de_deces) {
        this.jours_age_de_deces = jours_age_de_deces;
    }

    public Boolean getTbl_neuro() {
        return this.tbl_neuro;
    }

    public Casdecesbasage tbl_neuro(Boolean tbl_neuro) {
        this.setTbl_neuro(tbl_neuro);
        return this;
    }

    public void setTbl_neuro(Boolean tbl_neuro) {
        this.tbl_neuro = tbl_neuro;
    }

    public Boolean getTbl_hemorragique() {
        return this.tbl_hemorragique;
    }

    public Casdecesbasage tbl_hemorragique(Boolean tbl_hemorragique) {
        this.setTbl_hemorragique(tbl_hemorragique);
        return this;
    }

    public void setTbl_hemorragique(Boolean tbl_hemorragique) {
        this.tbl_hemorragique = tbl_hemorragique;
    }

    public Boolean getTbl_infx() {
        return this.tbl_infx;
    }

    public Casdecesbasage tbl_infx(Boolean tbl_infx) {
        this.setTbl_infx(tbl_infx);
        return this;
    }

    public void setTbl_infx(Boolean tbl_infx) {
        this.tbl_infx = tbl_infx;
    }

    public String getAutre_circonstances_deces() {
        return this.autre_circonstances_deces;
    }

    public Casdecesbasage autre_circonstances_deces(String autre_circonstances_deces) {
        this.setAutre_circonstances_deces(autre_circonstances_deces);
        return this;
    }

    public void setAutre_circonstances_deces(String autre_circonstances_deces) {
        this.autre_circonstances_deces = autre_circonstances_deces;
    }

    public Boolean getBautre_circonstance_deces() {
        return this.bautre_circonstance_deces;
    }

    public Casdecesbasage bautre_circonstance_deces(Boolean bautre_circonstance_deces) {
        this.setBautre_circonstance_deces(bautre_circonstance_deces);
        return this;
    }

    public void setBautre_circonstance_deces(Boolean bautre_circonstance_deces) {
        this.bautre_circonstance_deces = bautre_circonstance_deces;
    }

    public Boolean getNp_circonstances_deces() {
        return this.np_circonstances_deces;
    }

    public Casdecesbasage np_circonstances_deces(Boolean np_circonstances_deces) {
        this.setNp_circonstances_deces(np_circonstances_deces);
        return this;
    }

    public void setNp_circonstances_deces(Boolean np_circonstances_deces) {
        this.np_circonstances_deces = np_circonstances_deces;
    }

    public elieudeces getLieu_deces() {
        return this.lieu_deces;
    }

    public Casdecesbasage lieu_deces(elieudeces lieu_deces) {
        this.setLieu_deces(lieu_deces);
        return this;
    }

    public void setLieu_deces(elieudeces lieu_deces) {
        this.lieu_deces = lieu_deces;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Casdecesbasage)) {
            return false;
        }
        return id != null && id.equals(((Casdecesbasage) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Casdecesbasage{" +
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
