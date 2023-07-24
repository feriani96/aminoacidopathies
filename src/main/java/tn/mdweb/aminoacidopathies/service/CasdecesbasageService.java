package tn.mdweb.aminoacidopathies.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.domain.Casdecesbasage;
import tn.mdweb.aminoacidopathies.repository.CasdecesbasageRepository;
import tn.mdweb.aminoacidopathies.service.dto.CasdecesbasageDTO;
import tn.mdweb.aminoacidopathies.service.mapper.CasdecesbasageMapper;

/**
 * Service Implementation for managing {@link Casdecesbasage}.
 */
@Service
@Transactional
public class CasdecesbasageService {

    private final Logger log = LoggerFactory.getLogger(CasdecesbasageService.class);

    private final CasdecesbasageRepository casdecesbasageRepository;

    private final CasdecesbasageMapper casdecesbasageMapper;

    public CasdecesbasageService(CasdecesbasageRepository casdecesbasageRepository, CasdecesbasageMapper casdecesbasageMapper) {
        this.casdecesbasageRepository = casdecesbasageRepository;
        this.casdecesbasageMapper = casdecesbasageMapper;
    }

    /**
     * Save a casdecesbasage.
     *
     * @param casdecesbasageDTO the entity to save.
     * @return the persisted entity.
     */
    public CasdecesbasageDTO save(CasdecesbasageDTO casdecesbasageDTO) {
        log.debug("Request to save Casdecesbasage : {}", casdecesbasageDTO);
        Casdecesbasage casdecesbasage = casdecesbasageMapper.toEntity(casdecesbasageDTO);
        casdecesbasage = casdecesbasageRepository.save(casdecesbasage);
        return casdecesbasageMapper.toDto(casdecesbasage);
    }

    /**
     * Update a casdecesbasage.
     *
     * @param casdecesbasageDTO the entity to save.
     * @return the persisted entity.
     */
    public CasdecesbasageDTO update(CasdecesbasageDTO casdecesbasageDTO) {
        log.debug("Request to update Casdecesbasage : {}", casdecesbasageDTO);
        Casdecesbasage casdecesbasage = casdecesbasageMapper.toEntity(casdecesbasageDTO);
        casdecesbasage = casdecesbasageRepository.save(casdecesbasage);
        return casdecesbasageMapper.toDto(casdecesbasage);
    }

    /**
     * Partially update a casdecesbasage.
     *
     * @param casdecesbasageDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CasdecesbasageDTO> partialUpdate(CasdecesbasageDTO casdecesbasageDTO) {
        log.debug("Request to partially update Casdecesbasage : {}", casdecesbasageDTO);

        return casdecesbasageRepository
            .findById(casdecesbasageDTO.getId())
            .map(existingCasdecesbasage -> {
                casdecesbasageMapper.partialUpdate(existingCasdecesbasage, casdecesbasageDTO);

                return existingCasdecesbasage;
            })
            .map(casdecesbasageRepository::save)
            .map(casdecesbasageMapper::toDto);
    }

    /**
     * Get all the casdecesbasages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CasdecesbasageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Casdecesbasages");
        return casdecesbasageRepository.findAll(pageable).map(casdecesbasageMapper::toDto);
    }

    /**
     * Get one casdecesbasage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CasdecesbasageDTO> findOne(Long id) {
        log.debug("Request to get Casdecesbasage : {}", id);
        return casdecesbasageRepository.findById(id).map(casdecesbasageMapper::toDto);
    }

    /**
     * Delete the casdecesbasage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Casdecesbasage : {}", id);
        casdecesbasageRepository.deleteById(id);
    }
}
