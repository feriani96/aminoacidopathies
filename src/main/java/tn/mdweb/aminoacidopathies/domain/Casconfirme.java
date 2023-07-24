package tn.mdweb.aminoacidopathies.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tn.mdweb.aminoacidopathies.domain.enumeration.elien_parente;

/**
 * A Casconfirme.
 */
@Entity
@Table(name = "casconfirme")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Casconfirme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code_registre", nullable = false)
    private String code_registre;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_parente")
    private elien_parente lien_parente;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "pathologie", "casconfirmes" }, allowSetters = true)
    private Fiche fiche;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Casconfirme id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode_registre() {
        return this.code_registre;
    }

    public Casconfirme code_registre(String code_registre) {
        this.setCode_registre(code_registre);
        return this;
    }

    public void setCode_registre(String code_registre) {
        this.code_registre = code_registre;
    }

    public elien_parente getLien_parente() {
        return this.lien_parente;
    }

    public Casconfirme lien_parente(elien_parente lien_parente) {
        this.setLien_parente(lien_parente);
        return this;
    }

    public void setLien_parente(elien_parente lien_parente) {
        this.lien_parente = lien_parente;
    }

    public Fiche getFiche() {
        return this.fiche;
    }

    public void setFiche(Fiche fiche) {
        this.fiche = fiche;
    }

    public Casconfirme fiche(Fiche fiche) {
        this.setFiche(fiche);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Casconfirme)) {
            return false;
        }
        return id != null && id.equals(((Casconfirme) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Casconfirme{" +
            "id=" + getId() +
            ", code_registre='" + getCode_registre() + "'" +
            ", lien_parente='" + getLien_parente() + "'" +
            "}";
    }
}
