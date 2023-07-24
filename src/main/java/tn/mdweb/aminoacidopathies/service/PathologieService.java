package tn.mdweb.aminoacidopathies.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.domain.Pathologie;
import tn.mdweb.aminoacidopathies.repository.PathologieRepository;
import tn.mdweb.aminoacidopathies.service.dto.PathologieDTO;
import tn.mdweb.aminoacidopathies.service.mapper.PathologieMapper;

/**
 * Service Implementation for managing {@link Pathologie}.
 */
@Service
@Transactional
public class PathologieService {

    private final Logger log = LoggerFactory.getLogger(PathologieService.class);

    private final PathologieRepository pathologieRepository;

    private final PathologieMapper pathologieMapper;

    public PathologieService(PathologieRepository pathologieRepository, PathologieMapper pathologieMapper) {
        this.pathologieRepository = pathologieRepository;
        this.pathologieMapper = pathologieMapper;
    }

    /**
     * Save a pathologie.
     *
     * @param pathologieDTO the entity to save.
     * @return the persisted entity.
     */
    public PathologieDTO save(PathologieDTO pathologieDTO) {
        log.debug("Request to save Pathologie : {}", pathologieDTO);
        Pathologie pathologie = pathologieMapper.toEntity(pathologieDTO);
        pathologie = pathologieRepository.save(pathologie);
        return pathologieMapper.toDto(pathologie);
    }

    /**
     * Update a pathologie.
     *
     * @param pathologieDTO the entity to save.
     * @return the persisted entity.
     */
    public PathologieDTO update(PathologieDTO pathologieDTO) {
        log.debug("Request to update Pathologie : {}", pathologieDTO);
        Pathologie pathologie = pathologieMapper.toEntity(pathologieDTO);
        pathologie = pathologieRepository.save(pathologie);
        return pathologieMapper.toDto(pathologie);
    }

    /**
     * Partially update a pathologie.
     *
     * @param pathologieDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PathologieDTO> partialUpdate(PathologieDTO pathologieDTO) {
        log.debug("Request to partially update Pathologie : {}", pathologieDTO);

        return pathologieRepository
            .findById(pathologieDTO.getId())
            .map(existingPathologie -> {
                pathologieMapper.partialUpdate(existingPathologie, pathologieDTO);

                return existingPathologie;
            })
            .map(pathologieRepository::save)
            .map(pathologieMapper::toDto);
    }

    /**
     * Get all the pathologies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PathologieDTO> findAll() {
        log.debug("Request to get all Pathologies");
        return pathologieRepository.findAll().stream().map(pathologieMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pathologie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PathologieDTO> findOne(Long id) {
        log.debug("Request to get Pathologie : {}", id);
        return pathologieRepository.findById(id).map(pathologieMapper::toDto);
    }

    /**
     * Delete the pathologie by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Pathologie : {}", id);
        pathologieRepository.deleteById(id);
    }
}
