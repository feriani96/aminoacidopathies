package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Cassuspecte;
import tn.mdweb.aminoacidopathies.service.dto.CassuspecteDTO;

/**
 * Mapper for the entity {@link Cassuspecte} and its DTO {@link CassuspecteDTO}.
 */
@Mapper(componentModel = "spring")
public interface CassuspecteMapper extends EntityMapper<CassuspecteDTO, Cassuspecte> {}
