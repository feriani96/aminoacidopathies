package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Pathologie;
import tn.mdweb.aminoacidopathies.service.dto.PathologieDTO;

/**
 * Mapper for the entity {@link Pathologie} and its DTO {@link PathologieDTO}.
 */
@Mapper(componentModel = "spring")
public interface PathologieMapper extends EntityMapper<PathologieDTO, Pathologie> {}
