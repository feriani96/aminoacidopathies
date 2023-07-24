package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Metabolique;
import tn.mdweb.aminoacidopathies.service.dto.MetaboliqueDTO;

/**
 * Mapper for the entity {@link Metabolique} and its DTO {@link MetaboliqueDTO}.
 */
@Mapper(componentModel = "spring")
public interface MetaboliqueMapper extends EntityMapper<MetaboliqueDTO, Metabolique> {}
