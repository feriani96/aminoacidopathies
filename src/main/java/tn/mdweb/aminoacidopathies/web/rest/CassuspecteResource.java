package tn.mdweb.aminoacidopathies.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.mdweb.aminoacidopathies.repository.CassuspecteRepository;
import tn.mdweb.aminoacidopathies.service.CassuspecteService;
import tn.mdweb.aminoacidopathies.service.dto.CassuspecteDTO;
import tn.mdweb.aminoacidopathies.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.aminoacidopathies.domain.Cassuspecte}.
 */
@RestController
@RequestMapping("/api")
public class CassuspecteResource {

    private final Logger log = LoggerFactory.getLogger(CassuspecteResource.class);

    private static final String ENTITY_NAME = "cassuspecte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CassuspecteService cassuspecteService;

    private final CassuspecteRepository cassuspecteRepository;

    public CassuspecteResource(CassuspecteService cassuspecteService, CassuspecteRepository cassuspecteRepository) {
        this.cassuspecteService = cassuspecteService;
        this.cassuspecteRepository = cassuspecteRepository;
    }

    /**
     * {@code POST  /cassuspectes} : Create a new cassuspecte.
     *
     * @param cassuspecteDTO the cassuspecteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cassuspecteDTO, or with status {@code 400 (Bad Request)} if the cassuspecte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cassuspectes")
    public ResponseEntity<CassuspecteDTO> createCassuspecte(@RequestBody CassuspecteDTO cassuspecteDTO) throws URISyntaxException {
        log.debug("REST request to save Cassuspecte : {}", cassuspecteDTO);
        if (cassuspecteDTO.getId() != null) {
            throw new BadRequestAlertException("A new cassuspecte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CassuspecteDTO result = cassuspecteService.save(cassuspecteDTO);
        return ResponseEntity
            .created(new URI("/api/cassuspectes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cassuspectes/:id} : Updates an existing cassuspecte.
     *
     * @param id the id of the cassuspecteDTO to save.
     * @param cassuspecteDTO the cassuspecteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cassuspecteDTO,
     * or with status {@code 400 (Bad Request)} if the cassuspecteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cassuspecteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cassuspectes/{id}")
    public ResponseEntity<CassuspecteDTO> updateCassuspecte(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CassuspecteDTO cassuspecteDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Cassuspecte : {}, {}", id, cassuspecteDTO);
        if (cassuspecteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cassuspecteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cassuspecteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CassuspecteDTO result = cassuspecteService.update(cassuspecteDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cassuspecteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /cassuspectes/:id} : Partial updates given fields of an existing cassuspecte, field will ignore if it is null
     *
     * @param id the id of the cassuspecteDTO to save.
     * @param cassuspecteDTO the cassuspecteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cassuspecteDTO,
     * or with status {@code 400 (Bad Request)} if the cassuspecteDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cassuspecteDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cassuspecteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cassuspectes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CassuspecteDTO> partialUpdateCassuspecte(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CassuspecteDTO cassuspecteDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Cassuspecte partially : {}, {}", id, cassuspecteDTO);
        if (cassuspecteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cassuspecteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cassuspecteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CassuspecteDTO> result = cassuspecteService.partialUpdate(cassuspecteDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cassuspecteDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cassuspectes} : get all the cassuspectes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cassuspectes in body.
     */
    @GetMapping("/cassuspectes")
    public ResponseEntity<List<CassuspecteDTO>> getAllCassuspectes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Cassuspectes");
        Page<CassuspecteDTO> page = cassuspecteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cassuspectes/:id} : get the "id" cassuspecte.
     *
     * @param id the id of the cassuspecteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cassuspecteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cassuspectes/{id}")
    public ResponseEntity<CassuspecteDTO> getCassuspecte(@PathVariable Long id) {
        log.debug("REST request to get Cassuspecte : {}", id);
        Optional<CassuspecteDTO> cassuspecteDTO = cassuspecteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cassuspecteDTO);
    }

    /**
     * {@code DELETE  /cassuspectes/:id} : delete the "id" cassuspecte.
     *
     * @param id the id of the cassuspecteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cassuspectes/{id}")
    public ResponseEntity<Void> deleteCassuspecte(@PathVariable Long id) {
        log.debug("REST request to delete Cassuspecte : {}", id);
        cassuspecteService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
