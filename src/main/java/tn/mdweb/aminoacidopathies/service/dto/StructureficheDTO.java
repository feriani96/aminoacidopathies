package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import tn.mdweb.aminoacidopathies.domain.enumeration.etypestructure;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Structurefiche} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StructureficheDTO implements Serializable {

    private Long id;

    @NotNull
    private etypestructure typestructure;

    @NotNull
    private Integer ordre;

    private EtablissementDTO etablissement;

    private ServicesanteDTO servicesante;

    private MedecinDTO medecin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public etypestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(etypestructure typestructure) {
        this.typestructure = typestructure;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public EtablissementDTO getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(EtablissementDTO etablissement) {
        this.etablissement = etablissement;
    }

    public ServicesanteDTO getServicesante() {
        return servicesante;
    }

    public void setServicesante(ServicesanteDTO servicesante) {
        this.servicesante = servicesante;
    }

    public MedecinDTO getMedecin() {
        return medecin;
    }

    public void setMedecin(MedecinDTO medecin) {
        this.medecin = medecin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StructureficheDTO)) {
            return false;
        }

        StructureficheDTO structureficheDTO = (StructureficheDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, structureficheDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StructureficheDTO{" +
            "id=" + getId() +
            ", typestructure='" + getTypestructure() + "'" +
            ", ordre=" + getOrdre() +
            ", etablissement=" + getEtablissement() +
            ", servicesante=" + getServicesante() +
            ", medecin=" + getMedecin() +
            "}";
    }
}
