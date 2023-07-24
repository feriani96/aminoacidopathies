package tn.mdweb.aminoacidopathies.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.domain.Servicesante;
import tn.mdweb.aminoacidopathies.repository.ServicesanteRepository;
import tn.mdweb.aminoacidopathies.service.dto.ServicesanteDTO;
import tn.mdweb.aminoacidopathies.service.mapper.ServicesanteMapper;

/**
 * Service Implementation for managing {@link Servicesante}.
 */
@Service
@Transactional
public class ServicesanteService {

    private final Logger log = LoggerFactory.getLogger(ServicesanteService.class);

    private final ServicesanteRepository servicesanteRepository;

    private final ServicesanteMapper servicesanteMapper;

    public ServicesanteService(ServicesanteRepository servicesanteRepository, ServicesanteMapper servicesanteMapper) {
        this.servicesanteRepository = servicesanteRepository;
        this.servicesanteMapper = servicesanteMapper;
    }

    /**
     * Save a servicesante.
     *
     * @param servicesanteDTO the entity to save.
     * @return the persisted entity.
     */
    public ServicesanteDTO save(ServicesanteDTO servicesanteDTO) {
        log.debug("Request to save Servicesante : {}", servicesanteDTO);
        Servicesante servicesante = servicesanteMapper.toEntity(servicesanteDTO);
        servicesante = servicesanteRepository.save(servicesante);
        return servicesanteMapper.toDto(servicesante);
    }

    /**
     * Update a servicesante.
     *
     * @param servicesanteDTO the entity to save.
     * @return the persisted entity.
     */
    public ServicesanteDTO update(ServicesanteDTO servicesanteDTO) {
        log.debug("Request to update Servicesante : {}", servicesanteDTO);
        Servicesante servicesante = servicesanteMapper.toEntity(servicesanteDTO);
        servicesante = servicesanteRepository.save(servicesante);
        return servicesanteMapper.toDto(servicesante);
    }

    /**
     * Partially update a servicesante.
     *
     * @param servicesanteDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ServicesanteDTO> partialUpdate(ServicesanteDTO servicesanteDTO) {
        log.debug("Request to partially update Servicesante : {}", servicesanteDTO);

        return servicesanteRepository
            .findById(servicesanteDTO.getId())
            .map(existingServicesante -> {
                servicesanteMapper.partialUpdate(existingServicesante, servicesanteDTO);

                return existingServicesante;
            })
            .map(servicesanteRepository::save)
            .map(servicesanteMapper::toDto);
    }

    /**
     * Get all the servicesantes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ServicesanteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Servicesantes");
        return servicesanteRepository.findAll(pageable).map(servicesanteMapper::toDto);
    }

    /**
     * Get one servicesante by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ServicesanteDTO> findOne(Long id) {
        log.debug("Request to get Servicesante : {}", id);
        return servicesanteRepository.findById(id).map(servicesanteMapper::toDto);
    }

    /**
     * Delete the servicesante by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Servicesante : {}", id);
        servicesanteRepository.deleteById(id);
    }
}
