package tn.mdweb.aminoacidopathies.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tn.mdweb.aminoacidopathies.domain.enumeration.etypestructure;

/**
 * A Structurefiche.
 */
@Entity
@Table(name = "structurefiche")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Structurefiche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "typestructure", nullable = false)
    private etypestructure typestructure;

    @NotNull
    @Column(name = "ordre", nullable = false)
    private Integer ordre;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "structuresante" }, allowSetters = true)
    private Etablissement etablissement;

    @ManyToOne
    private Servicesante servicesante;

    @ManyToOne
    @JsonIgnoreProperties(value = { "etablissement", "servicesante" }, allowSetters = true)
    private Medecin medecin;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Structurefiche id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public etypestructure getTypestructure() {
        return this.typestructure;
    }

    public Structurefiche typestructure(etypestructure typestructure) {
        this.setTypestructure(typestructure);
        return this;
    }

    public void setTypestructure(etypestructure typestructure) {
        this.typestructure = typestructure;
    }

    public Integer getOrdre() {
        return this.ordre;
    }

    public Structurefiche ordre(Integer ordre) {
        this.setOrdre(ordre);
        return this;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public Structurefiche etablissement(Etablissement etablissement) {
        this.setEtablissement(etablissement);
        return this;
    }

    public Servicesante getServicesante() {
        return this.servicesante;
    }

    public void setServicesante(Servicesante servicesante) {
        this.servicesante = servicesante;
    }

    public Structurefiche servicesante(Servicesante servicesante) {
        this.setServicesante(servicesante);
        return this;
    }

    public Medecin getMedecin() {
        return this.medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Structurefiche medecin(Medecin medecin) {
        this.setMedecin(medecin);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Structurefiche)) {
            return false;
        }
        return id != null && id.equals(((Structurefiche) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Structurefiche{" +
            "id=" + getId() +
            ", typestructure='" + getTypestructure() + "'" +
            ", ordre=" + getOrdre() +
            "}";
    }
}
