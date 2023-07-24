package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Structuresante} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StructuresanteDTO implements Serializable {

    private Long id;

    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StructuresanteDTO)) {
            return false;
        }

        StructuresanteDTO structuresanteDTO = (StructuresanteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, structuresanteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StructuresanteDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
