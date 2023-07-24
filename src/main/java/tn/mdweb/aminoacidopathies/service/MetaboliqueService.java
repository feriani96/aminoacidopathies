package tn.mdweb.aminoacidopathies.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.domain.Metabolique;
import tn.mdweb.aminoacidopathies.repository.MetaboliqueRepository;
import tn.mdweb.aminoacidopathies.service.dto.MetaboliqueDTO;
import tn.mdweb.aminoacidopathies.service.mapper.MetaboliqueMapper;

/**
 * Service Implementation for managing {@link Metabolique}.
 */
@Service
@Transactional
public class MetaboliqueService {

    private final Logger log = LoggerFactory.getLogger(MetaboliqueService.class);

    private final MetaboliqueRepository metaboliqueRepository;

    private final MetaboliqueMapper metaboliqueMapper;

    public MetaboliqueService(MetaboliqueRepository metaboliqueRepository, MetaboliqueMapper metaboliqueMapper) {
        this.metaboliqueRepository = metaboliqueRepository;
        this.metaboliqueMapper = metaboliqueMapper;
    }

    /**
     * Save a metabolique.
     *
     * @param metaboliqueDTO the entity to save.
     * @return the persisted entity.
     */
    public MetaboliqueDTO save(MetaboliqueDTO metaboliqueDTO) {
        log.debug("Request to save Metabolique : {}", metaboliqueDTO);
        Metabolique metabolique = metaboliqueMapper.toEntity(metaboliqueDTO);
        metabolique = metaboliqueRepository.save(metabolique);
        return metaboliqueMapper.toDto(metabolique);
    }

    /**
     * Update a metabolique.
     *
     * @param metaboliqueDTO the entity to save.
     * @return the persisted entity.
     */
    public MetaboliqueDTO update(MetaboliqueDTO metaboliqueDTO) {
        log.debug("Request to update Metabolique : {}", metaboliqueDTO);
        Metabolique metabolique = metaboliqueMapper.toEntity(metaboliqueDTO);
        metabolique = metaboliqueRepository.save(metabolique);
        return metaboliqueMapper.toDto(metabolique);
    }

    /**
     * Partially update a metabolique.
     *
     * @param metaboliqueDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MetaboliqueDTO> partialUpdate(MetaboliqueDTO metaboliqueDTO) {
        log.debug("Request to partially update Metabolique : {}", metaboliqueDTO);

        return metaboliqueRepository
            .findById(metaboliqueDTO.getId())
            .map(existingMetabolique -> {
                metaboliqueMapper.partialUpdate(existingMetabolique, metaboliqueDTO);

                return existingMetabolique;
            })
            .map(metaboliqueRepository::save)
            .map(metaboliqueMapper::toDto);
    }

    /**
     * Get all the metaboliques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MetaboliqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Metaboliques");
        return metaboliqueRepository.findAll(pageable).map(metaboliqueMapper::toDto);
    }

    /**
     * Get one metabolique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MetaboliqueDTO> findOne(Long id) {
        log.debug("Request to get Metabolique : {}", id);
        return metaboliqueRepository.findById(id).map(metaboliqueMapper::toDto);
    }

    /**
     * Delete the metabolique by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Metabolique : {}", id);
        metaboliqueRepository.deleteById(id);
    }
}
