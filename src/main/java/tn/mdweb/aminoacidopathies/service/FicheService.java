package tn.mdweb.aminoacidopathies.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.domain.Fiche;
import tn.mdweb.aminoacidopathies.repository.FicheRepository;
import tn.mdweb.aminoacidopathies.service.dto.FicheDTO;
import tn.mdweb.aminoacidopathies.service.mapper.FicheMapper;
import tn.mdweb.aminoacidopathies.repository.CasconfirmeRepository;
import tn.mdweb.aminoacidopathies.service.mapper.CasconfirmeMapper;
import tn.mdweb.aminoacidopathies.service.dto.CasconfirmeDTO;
import tn.mdweb.aminoacidopathies.domain.Casconfirme;
import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedList;


/**
 * Service Implementation for managing {@link Fiche}.
 */
@Service
@Transactional
public class FicheService {

    private final Logger log = LoggerFactory.getLogger(FicheService.class);

    private final FicheRepository ficheRepository;

    private final FicheMapper ficheMapper;

    private final CasconfirmeRepository casconfirmeRepository;

    private final CasconfirmeMapper casconfirmeMapper;

    public FicheService(FicheRepository ficheRepository, FicheMapper ficheMapper,
                        CasconfirmeRepository casconfirmeRepository, CasconfirmeMapper casconfirmeMapper) {
        this.ficheRepository = ficheRepository;
        this.ficheMapper = ficheMapper;
        this.casconfirmeRepository = casconfirmeRepository;
        this.casconfirmeMapper = casconfirmeMapper;
        }


    /**
     * Save a fiche.
     *
     * @param ficheDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheDTO save(FicheDTO ficheDTO) {
        log.debug("Request to save Fiche : {}", ficheDTO);
        Fiche fiche = ficheMapper.toEntity(ficheDTO);
        fiche = ficheRepository.save(fiche);

        for(int i=0; i<ficheDTO.getCasConfirmes().size(); i++) {

            CasconfirmeDTO casconfirmeDTO = ficheDTO.getCasConfirmes().get(i);
            casconfirmeDTO.setFiche(ficheMapper.toDto(fiche));
            log.debug("Request to save Pfield : {}", casconfirmeDTO);
            Casconfirme casconfirme = casconfirmeMapper.toEntity(casconfirmeDTO);
            casconfirme = casconfirmeRepository.save(casconfirme);


        }

        FicheDTO iMtoDTO = ficheMapper.toDto(fiche) ;



        return iMtoDTO;
    }

    /**
     * Update a fiche.
     *
     * @param ficheDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheDTO update(FicheDTO ficheDTO) {
        log.debug("Request to update Fiche : {}", ficheDTO);
        Fiche fiche = ficheMapper.toEntity(ficheDTO);
        fiche = ficheRepository.save(fiche);
        return ficheMapper.toDto(fiche);
    }

    /**
     * Partially update a fiche.
     *
     * @param ficheDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FicheDTO> partialUpdate(FicheDTO ficheDTO) {
        log.debug("Request to partially update Fiche : {}", ficheDTO);

        return ficheRepository
            .findById(ficheDTO.getId())
            .map(existingFiche -> {
                ficheMapper.partialUpdate(existingFiche, ficheDTO);

                return existingFiche;
            })
            .map(ficheRepository::save)
            .map(ficheMapper::toDto);
    }

/**
     * Get all the fiches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FicheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fiches");

        System.out.print("fiche-----------------"+ficheRepository.findAll());

        List<FicheDTO> lidto = ficheRepository.findAll().stream().map(ficheMapper::toDto).collect(Collectors.toCollection(LinkedList::new));


        for (int i=0; i < lidto.size(); i++)
        {
            lidto.get(i).setCasConfirmes(casconfirmeRepository.findByFiche(ficheMapper.toEntity(lidto.get(i))));
        }
        return lidto;
    }

    /**
     * Get one fiche by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public FicheDTO findOne(Long id) {
        log.debug("Request to get Fiche : {}", id);
        FicheDTO idto = ficheRepository.findById(id).map(ficheMapper::toDto).get();
        Fiche f = ficheRepository.findById(id).get();
        idto.setCasConfirmes(casconfirmeRepository.findByFiche(f));

        return idto;

    }


    /**
     * Delete the fiche by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Fiche : {}", id);
        ficheRepository.deleteById(id);
    }
}
