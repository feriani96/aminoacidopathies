package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Fiche;
import tn.mdweb.aminoacidopathies.domain.Pathologie;
import tn.mdweb.aminoacidopathies.service.dto.FicheDTO;
import tn.mdweb.aminoacidopathies.service.dto.PathologieDTO;

/**
 * Mapper for the entity {@link Fiche} and its DTO {@link FicheDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheMapper extends EntityMapper<FicheDTO, Fiche> {
    @Mapping(target = "pathologie", source = "pathologie", qualifiedByName = "pathologieId")
    FicheDTO toDto(Fiche s);

    @Named("pathologieId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PathologieDTO toDtoPathologieId(Pathologie pathologie);
}
