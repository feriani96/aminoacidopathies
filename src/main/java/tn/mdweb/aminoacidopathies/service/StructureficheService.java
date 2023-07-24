package tn.mdweb.aminoacidopathies.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.domain.Structurefiche;
import tn.mdweb.aminoacidopathies.repository.StructureficheRepository;
import tn.mdweb.aminoacidopathies.service.dto.StructureficheDTO;
import tn.mdweb.aminoacidopathies.service.mapper.StructureficheMapper;

/**
 * Service Implementation for managing {@link Structurefiche}.
 */
@Service
@Transactional
public class StructureficheService {

    private final Logger log = LoggerFactory.getLogger(StructureficheService.class);

    private final StructureficheRepository structureficheRepository;

    private final StructureficheMapper structureficheMapper;

    public StructureficheService(StructureficheRepository structureficheRepository, StructureficheMapper structureficheMapper) {
        this.structureficheRepository = structureficheRepository;
        this.structureficheMapper = structureficheMapper;
    }

    /**
     * Save a structurefiche.
     *
     * @param structureficheDTO the entity to save.
     * @return the persisted entity.
     */
    public StructureficheDTO save(StructureficheDTO structureficheDTO) {
        log.debug("Request to save Structurefiche : {}", structureficheDTO);
        Structurefiche structurefiche = structureficheMapper.toEntity(structureficheDTO);
        structurefiche = structureficheRepository.save(structurefiche);
        return structureficheMapper.toDto(structurefiche);
    }

    /**
     * Update a structurefiche.
     *
     * @param structureficheDTO the entity to save.
     * @return the persisted entity.
     */
    public StructureficheDTO update(StructureficheDTO structureficheDTO) {
        log.debug("Request to update Structurefiche : {}", structureficheDTO);
        Structurefiche structurefiche = structureficheMapper.toEntity(structureficheDTO);
        structurefiche = structureficheRepository.save(structurefiche);
        return structureficheMapper.toDto(structurefiche);
    }

    /**
     * Partially update a structurefiche.
     *
     * @param structureficheDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StructureficheDTO> partialUpdate(StructureficheDTO structureficheDTO) {
        log.debug("Request to partially update Structurefiche : {}", structureficheDTO);

        return structureficheRepository
            .findById(structureficheDTO.getId())
            .map(existingStructurefiche -> {
                structureficheMapper.partialUpdate(existingStructurefiche, structureficheDTO);

                return existingStructurefiche;
            })
            .map(structureficheRepository::save)
            .map(structureficheMapper::toDto);
    }

    /**
     * Get all the structurefiches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<StructureficheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Structurefiches");
        return structureficheRepository.findAll(pageable).map(structureficheMapper::toDto);
    }

    /**
     * Get one structurefiche by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StructureficheDTO> findOne(Long id) {
        log.debug("Request to get Structurefiche : {}", id);
        return structureficheRepository.findById(id).map(structureficheMapper::toDto);
    }

    /**
     * Delete the structurefiche by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Structurefiche : {}", id);
        structureficheRepository.deleteById(id);
    }
}
