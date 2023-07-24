package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Medecin} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MedecinDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    private String cin;

    private String email;

    private String tel;

    private String adresse;

    private EtablissementDTO etablissement;

    private ServicesanteDTO servicesante;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedecinDTO)) {
            return false;
        }

        MedecinDTO medecinDTO = (MedecinDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, medecinDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedecinDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", cin='" + getCin() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel='" + getTel() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", etablissement=" + getEtablissement() +
            ", servicesante=" + getServicesante() +
            "}";
    }
}
