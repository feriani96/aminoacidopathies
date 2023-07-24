package tn.mdweb.aminoacidopathies.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tn.mdweb.aminoacidopathies.domain.enumeration.eResultat;
import tn.mdweb.aminoacidopathies.domain.enumeration.efait;
import tn.mdweb.aminoacidopathies.domain.enumeration.elaboratoire;
import tn.mdweb.aminoacidopathies.domain.enumeration.ename;

/**
 * A Metabolique.
 */
@Entity
@Table(name = "metabolique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Metabolique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ename name;

    @Enumerated(EnumType.STRING)
    @Column(name = "fait")
    private efait fait;

    @Enumerated(EnumType.STRING)
    @Column(name = "laboratoire")
    private elaboratoire laboratoire;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultat")
    private eResultat resultat;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Metabolique id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ename getName() {
        return this.name;
    }

    public Metabolique name(ename name) {
        this.setName(name);
        return this;
    }

    public void setName(ename name) {
        this.name = name;
    }

    public efait getFait() {
        return this.fait;
    }

    public Metabolique fait(efait fait) {
        this.setFait(fait);
        return this;
    }

    public void setFait(efait fait) {
        this.fait = fait;
    }

    public elaboratoire getLaboratoire() {
        return this.laboratoire;
    }

    public Metabolique laboratoire(elaboratoire laboratoire) {
        this.setLaboratoire(laboratoire);
        return this;
    }

    public void setLaboratoire(elaboratoire laboratoire) {
        this.laboratoire = laboratoire;
    }

    public eResultat getResultat() {
        return this.resultat;
    }

    public Metabolique resultat(eResultat resultat) {
        this.setResultat(resultat);
        return this;
    }

    public void setResultat(eResultat resultat) {
        this.resultat = resultat;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Metabolique)) {
            return false;
        }
        return id != null && id.equals(((Metabolique) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Metabolique{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", fait='" + getFait() + "'" +
            ", laboratoire='" + getLaboratoire() + "'" +
            ", resultat='" + getResultat() + "'" +
            "}";
    }
}
