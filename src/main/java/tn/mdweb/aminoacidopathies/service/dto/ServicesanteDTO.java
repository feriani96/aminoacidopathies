package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Servicesante} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ServicesanteDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServicesanteDTO)) {
            return false;
        }

        ServicesanteDTO servicesanteDTO = (ServicesanteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, servicesanteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServicesanteDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
