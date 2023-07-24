package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Etablissement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EtablissementDTO implements Serializable {

    private Long id;

    private String name;

    private StructuresanteDTO structuresante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StructuresanteDTO getStructuresante() {
        return structuresante;
    }

    public void setStructuresante(StructuresanteDTO structuresante) {
        this.structuresante = structuresante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtablissementDTO)) {
            return false;
        }

        EtablissementDTO etablissementDTO = (EtablissementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, etablissementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtablissementDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", structuresante=" + getStructuresante() +
            "}";
    }
}
