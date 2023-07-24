package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import tn.mdweb.aminoacidopathies.domain.enumeration.elien_parente;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Casconfirme} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CasconfirmeDTO implements Serializable {

    private Long id;

    @NotNull
    private String code_registre;

    private elien_parente lien_parente;

    private FicheDTO fiche;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode_registre() {
        return code_registre;
    }

    public void setCode_registre(String code_registre) {
        this.code_registre = code_registre;
    }

    public elien_parente getLien_parente() {
        return lien_parente;
    }

    public void setLien_parente(elien_parente lien_parente) {
        this.lien_parente = lien_parente;
    }

    public FicheDTO getFiche() {
        return fiche;
    }

    public void setFiche(FicheDTO fiche) {
        this.fiche = fiche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CasconfirmeDTO)) {
            return false;
        }

        CasconfirmeDTO casconfirmeDTO = (CasconfirmeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, casconfirmeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CasconfirmeDTO{" +
            "id=" + getId() +
            ", code_registre='" + getCode_registre() + "'" +
            ", lien_parente='" + getLien_parente() + "'" +
            ", fiche=" + getFiche() +
            "}";
    }
}
