package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Structuresante;
import tn.mdweb.aminoacidopathies.service.dto.StructuresanteDTO;

/**
 * Mapper for the entity {@link Structuresante} and its DTO {@link StructuresanteDTO}.
 */
@Mapper(componentModel = "spring")
public interface StructuresanteMapper extends EntityMapper<StructuresanteDTO, Structuresante> {}
