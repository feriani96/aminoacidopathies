package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Etablissement;
import tn.mdweb.aminoacidopathies.domain.Structuresante;
import tn.mdweb.aminoacidopathies.service.dto.EtablissementDTO;
import tn.mdweb.aminoacidopathies.service.dto.StructuresanteDTO;

/**
 * Mapper for the entity {@link Etablissement} and its DTO {@link EtablissementDTO}.
 */
@Mapper(componentModel = "spring")
public interface EtablissementMapper extends EntityMapper<EtablissementDTO, Etablissement> {
    @Mapping(target = "structuresante", source = "structuresante", qualifiedByName = "structuresanteId")
    EtablissementDTO toDto(Etablissement s);

    @Named("structuresanteId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StructuresanteDTO toDtoStructuresanteId(Structuresante structuresante);
}
