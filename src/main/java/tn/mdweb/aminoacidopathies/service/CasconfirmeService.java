package tn.mdweb.aminoacidopathies.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.domain.Casconfirme;
import tn.mdweb.aminoacidopathies.repository.CasconfirmeRepository;
import tn.mdweb.aminoacidopathies.service.dto.CasconfirmeDTO;
import tn.mdweb.aminoacidopathies.service.mapper.CasconfirmeMapper;
import java.util.List;
import tn.mdweb.aminoacidopathies.domain.Fiche;

/**
 * Service Implementation for managing {@link Casconfirme}.
 */
@Service
@Transactional
public class CasconfirmeService {

    private final Logger log = LoggerFactory.getLogger(CasconfirmeService.class);

    private final CasconfirmeRepository casconfirmeRepository;

    private final CasconfirmeMapper casconfirmeMapper;

    public CasconfirmeService(CasconfirmeRepository casconfirmeRepository, CasconfirmeMapper casconfirmeMapper) {
        this.casconfirmeRepository = casconfirmeRepository;
        this.casconfirmeMapper = casconfirmeMapper;
    }

    /**
     * Save a casconfirme.
     *
     * @param casconfirmeDTO the entity to save.
     * @return the persisted entity.
     */
    public CasconfirmeDTO save(CasconfirmeDTO casconfirmeDTO) {
        log.debug("Request to save Casconfirme : {}", casconfirmeDTO);
        Casconfirme casconfirme = casconfirmeMapper.toEntity(casconfirmeDTO);
        casconfirme = casconfirmeRepository.save(casconfirme);
        return casconfirmeMapper.toDto(casconfirme);
    }

    /**
     * Update a casconfirme.
     *
     * @param casconfirmeDTO the entity to save.
     * @return the persisted entity.
     */
    public CasconfirmeDTO update(CasconfirmeDTO casconfirmeDTO) {
        log.debug("Request to update Casconfirme : {}", casconfirmeDTO);
        Casconfirme casconfirme = casconfirmeMapper.toEntity(casconfirmeDTO);
        casconfirme = casconfirmeRepository.save(casconfirme);
        return casconfirmeMapper.toDto(casconfirme);
    }

    /**
     * Partially update a casconfirme.
     *
     * @param casconfirmeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CasconfirmeDTO> partialUpdate(CasconfirmeDTO casconfirmeDTO) {
        log.debug("Request to partially update Casconfirme : {}", casconfirmeDTO);

        return casconfirmeRepository
            .findById(casconfirmeDTO.getId())
            .map(existingCasconfirme -> {
                casconfirmeMapper.partialUpdate(existingCasconfirme, casconfirmeDTO);

                return existingCasconfirme;
            })
            .map(casconfirmeRepository::save)
            .map(casconfirmeMapper::toDto);
    }

    /**
     * Get all the casconfirmes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CasconfirmeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Casconfirmes");
        return casconfirmeRepository.findAll(pageable).map(casconfirmeMapper::toDto);
    }


    /**GetAllCasConfirmeByFiche***/

    /**
     * Get all the casConfirme.
     *
     * @return the list of entities.
     */
    public List<CasconfirmeDTO> findAllByFiche(Fiche fiche) {
        log.debug("Request to get all CasConfirme");
        log.debug("findAllByFiche-----------------"+fiche);
        log.debug("casconfirmeRepository.findAllByFiche-----------------"+ casconfirmeRepository.findByFiche(fiche));
        return casconfirmeRepository.findByFiche(fiche);


}



/**
     * Get one casconfirme by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CasconfirmeDTO> findOne(Long id) {
        log.debug("Request to get Casconfirme : {}", id);
        return casconfirmeRepository.findById(id).map(casconfirmeMapper::toDto);
    }

    /**
     * Delete the casconfirme by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Casconfirme : {}", id);
        casconfirmeRepository.deleteById(id);
    }
}
