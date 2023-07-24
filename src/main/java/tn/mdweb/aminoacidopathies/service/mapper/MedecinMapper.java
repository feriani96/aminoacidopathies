package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Etablissement;
import tn.mdweb.aminoacidopathies.domain.Medecin;
import tn.mdweb.aminoacidopathies.domain.Servicesante;
import tn.mdweb.aminoacidopathies.service.dto.EtablissementDTO;
import tn.mdweb.aminoacidopathies.service.dto.MedecinDTO;
import tn.mdweb.aminoacidopathies.service.dto.ServicesanteDTO;

/**
 * Mapper for the entity {@link Medecin} and its DTO {@link MedecinDTO}.
 */
@Mapper(componentModel = "spring")
public interface MedecinMapper extends EntityMapper<MedecinDTO, Medecin> {
    @Mapping(target = "etablissement", source = "etablissement", qualifiedByName = "etablissementId")
    @Mapping(target = "servicesante", source = "servicesante", qualifiedByName = "servicesanteId")
    MedecinDTO toDto(Medecin s);

    @Named("etablissementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EtablissementDTO toDtoEtablissementId(Etablissement etablissement);

    @Named("servicesanteId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServicesanteDTO toDtoServicesanteId(Servicesante servicesante);
}
