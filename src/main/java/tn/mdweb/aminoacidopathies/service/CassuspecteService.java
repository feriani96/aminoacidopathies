package tn.mdweb.aminoacidopathies.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.domain.Cassuspecte;
import tn.mdweb.aminoacidopathies.repository.CassuspecteRepository;
import tn.mdweb.aminoacidopathies.service.dto.CassuspecteDTO;
import tn.mdweb.aminoacidopathies.service.mapper.CassuspecteMapper;

/**
 * Service Implementation for managing {@link Cassuspecte}.
 */
@Service
@Transactional
public class CassuspecteService {

    private final Logger log = LoggerFactory.getLogger(CassuspecteService.class);

    private final CassuspecteRepository cassuspecteRepository;

    private final CassuspecteMapper cassuspecteMapper;

    public CassuspecteService(CassuspecteRepository cassuspecteRepository, CassuspecteMapper cassuspecteMapper) {
        this.cassuspecteRepository = cassuspecteRepository;
        this.cassuspecteMapper = cassuspecteMapper;
    }

    /**
     * Save a cassuspecte.
     *
     * @param cassuspecteDTO the entity to save.
     * @return the persisted entity.
     */
    public CassuspecteDTO save(CassuspecteDTO cassuspecteDTO) {
        log.debug("Request to save Cassuspecte : {}", cassuspecteDTO);
        Cassuspecte cassuspecte = cassuspecteMapper.toEntity(cassuspecteDTO);
        cassuspecte = cassuspecteRepository.save(cassuspecte);
        return cassuspecteMapper.toDto(cassuspecte);
    }

    /**
     * Update a cassuspecte.
     *
     * @param cassuspecteDTO the entity to save.
     * @return the persisted entity.
     */
    public CassuspecteDTO update(CassuspecteDTO cassuspecteDTO) {
        log.debug("Request to update Cassuspecte : {}", cassuspecteDTO);
        Cassuspecte cassuspecte = cassuspecteMapper.toEntity(cassuspecteDTO);
        cassuspecte = cassuspecteRepository.save(cassuspecte);
        return cassuspecteMapper.toDto(cassuspecte);
    }

    /**
     * Partially update a cassuspecte.
     *
     * @param cassuspecteDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CassuspecteDTO> partialUpdate(CassuspecteDTO cassuspecteDTO) {
        log.debug("Request to partially update Cassuspecte : {}", cassuspecteDTO);

        return cassuspecteRepository
            .findById(cassuspecteDTO.getId())
            .map(existingCassuspecte -> {
                cassuspecteMapper.partialUpdate(existingCassuspecte, cassuspecteDTO);

                return existingCassuspecte;
            })
            .map(cassuspecteRepository::save)
            .map(cassuspecteMapper::toDto);
    }

    /**
     * Get all the cassuspectes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CassuspecteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cassuspectes");
        return cassuspecteRepository.findAll(pageable).map(cassuspecteMapper::toDto);
    }

    /**
     * Get one cassuspecte by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CassuspecteDTO> findOne(Long id) {
        log.debug("Request to get Cassuspecte : {}", id);
        return cassuspecteRepository.findById(id).map(cassuspecteMapper::toDto);
    }

    /**
     * Delete the cassuspecte by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cassuspecte : {}", id);
        cassuspecteRepository.deleteById(id);
    }
}
