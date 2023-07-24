package tn.mdweb.aminoacidopathies.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.domain.Structuresante;
import tn.mdweb.aminoacidopathies.repository.StructuresanteRepository;
import tn.mdweb.aminoacidopathies.service.dto.StructuresanteDTO;
import tn.mdweb.aminoacidopathies.service.mapper.StructuresanteMapper;

/**
 * Service Implementation for managing {@link Structuresante}.
 */
@Service
@Transactional
public class StructuresanteService {

    private final Logger log = LoggerFactory.getLogger(StructuresanteService.class);

    private final StructuresanteRepository structuresanteRepository;

    private final StructuresanteMapper structuresanteMapper;

    public StructuresanteService(StructuresanteRepository structuresanteRepository, StructuresanteMapper structuresanteMapper) {
        this.structuresanteRepository = structuresanteRepository;
        this.structuresanteMapper = structuresanteMapper;
    }

    /**
     * Save a structuresante.
     *
     * @param structuresanteDTO the entity to save.
     * @return the persisted entity.
     */
    public StructuresanteDTO save(StructuresanteDTO structuresanteDTO) {
        log.debug("Request to save Structuresante : {}", structuresanteDTO);
        Structuresante structuresante = structuresanteMapper.toEntity(structuresanteDTO);
        structuresante = structuresanteRepository.save(structuresante);
        return structuresanteMapper.toDto(structuresante);
    }

    /**
     * Update a structuresante.
     *
     * @param structuresanteDTO the entity to save.
     * @return the persisted entity.
     */
    public StructuresanteDTO update(StructuresanteDTO structuresanteDTO) {
        log.debug("Request to update Structuresante : {}", structuresanteDTO);
        Structuresante structuresante = structuresanteMapper.toEntity(structuresanteDTO);
        structuresante = structuresanteRepository.save(structuresante);
        return structuresanteMapper.toDto(structuresante);
    }

    /**
     * Partially update a structuresante.
     *
     * @param structuresanteDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StructuresanteDTO> partialUpdate(StructuresanteDTO structuresanteDTO) {
        log.debug("Request to partially update Structuresante : {}", structuresanteDTO);

        return structuresanteRepository
            .findById(structuresanteDTO.getId())
            .map(existingStructuresante -> {
                structuresanteMapper.partialUpdate(existingStructuresante, structuresanteDTO);

                return existingStructuresante;
            })
            .map(structuresanteRepository::save)
            .map(structuresanteMapper::toDto);
    }

    /**
     * Get all the structuresantes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StructuresanteDTO> findAll() {
        log.debug("Request to get all Structuresantes");
        return structuresanteRepository
            .findAll()
            .stream()
            .map(structuresanteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one structuresante by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StructuresanteDTO> findOne(Long id) {
        log.debug("Request to get Structuresante : {}", id);
        return structuresanteRepository.findById(id).map(structuresanteMapper::toDto);
    }

    /**
     * Delete the structuresante by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Structuresante : {}", id);
        structuresanteRepository.deleteById(id);
    }
}
