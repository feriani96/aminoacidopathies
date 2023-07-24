package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Etablissement;
import tn.mdweb.aminoacidopathies.domain.Medecin;
import tn.mdweb.aminoacidopathies.domain.Servicesante;
import tn.mdweb.aminoacidopathies.domain.Structurefiche;
import tn.mdweb.aminoacidopathies.service.dto.EtablissementDTO;
import tn.mdweb.aminoacidopathies.service.dto.MedecinDTO;
import tn.mdweb.aminoacidopathies.service.dto.ServicesanteDTO;
import tn.mdweb.aminoacidopathies.service.dto.StructureficheDTO;

/**
 * Mapper for the entity {@link Structurefiche} and its DTO {@link StructureficheDTO}.
 */
@Mapper(componentModel = "spring")
public interface StructureficheMapper extends EntityMapper<StructureficheDTO, Structurefiche> {
    @Mapping(target = "etablissement", source = "etablissement", qualifiedByName = "etablissementId")
    @Mapping(target = "servicesante", source = "servicesante", qualifiedByName = "servicesanteId")
    @Mapping(target = "medecin", source = "medecin", qualifiedByName = "medecinId")
    StructureficheDTO toDto(Structurefiche s);

    @Named("etablissementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EtablissementDTO toDtoEtablissementId(Etablissement etablissement);

    @Named("servicesanteId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServicesanteDTO toDtoServicesanteId(Servicesante servicesante);

    @Named("medecinId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MedecinDTO toDtoMedecinId(Medecin medecin);
}
