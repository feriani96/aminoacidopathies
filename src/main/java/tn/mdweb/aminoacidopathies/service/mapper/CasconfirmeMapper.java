package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Casconfirme;
import tn.mdweb.aminoacidopathies.domain.Fiche;
import tn.mdweb.aminoacidopathies.service.dto.CasconfirmeDTO;
import tn.mdweb.aminoacidopathies.service.dto.FicheDTO;

/**
 * Mapper for the entity {@link Casconfirme} and its DTO {@link CasconfirmeDTO}.
 */
@Mapper(componentModel = "spring")
public interface CasconfirmeMapper extends EntityMapper<CasconfirmeDTO, Casconfirme> {
    @Mapping(target = "fiche", source = "fiche", qualifiedByName = "ficheId")
    CasconfirmeDTO toDto(Casconfirme s);

    @Named("ficheId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FicheDTO toDtoFicheId(Fiche fiche);
}
