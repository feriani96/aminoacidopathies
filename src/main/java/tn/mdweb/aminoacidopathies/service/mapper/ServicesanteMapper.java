package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Servicesante;
import tn.mdweb.aminoacidopathies.service.dto.ServicesanteDTO;

/**
 * Mapper for the entity {@link Servicesante} and its DTO {@link ServicesanteDTO}.
 */
@Mapper(componentModel = "spring")
public interface ServicesanteMapper extends EntityMapper<ServicesanteDTO, Servicesante> {}
