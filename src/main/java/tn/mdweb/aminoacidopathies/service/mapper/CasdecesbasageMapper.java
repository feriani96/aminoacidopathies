package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Casdecesbasage;
import tn.mdweb.aminoacidopathies.service.dto.CasdecesbasageDTO;

/**
 * Mapper for the entity {@link Casdecesbasage} and its DTO {@link CasdecesbasageDTO}.
 */
@Mapper(componentModel = "spring")
public interface CasdecesbasageMapper extends EntityMapper<CasdecesbasageDTO, Casdecesbasage> {}
