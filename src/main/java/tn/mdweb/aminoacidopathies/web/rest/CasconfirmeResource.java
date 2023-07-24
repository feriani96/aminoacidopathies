package tn.mdweb.aminoacidopathies.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
import tn.mdweb.aminoacidopathies.repository.CasconfirmeRepository;
import tn.mdweb.aminoacidopathies.service.CasconfirmeService;
import tn.mdweb.aminoacidopathies.service.dto.CasconfirmeDTO;
import tn.mdweb.aminoacidopathies.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.aminoacidopathies.domain.Casconfirme}.
 */
@RestController
@RequestMapping("/api")
public class CasconfirmeResource {

    private final Logger log = LoggerFactory.getLogger(CasconfirmeResource.class);

    private static final String ENTITY_NAME = "casconfirme";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CasconfirmeService casconfirmeService;

    private final CasconfirmeRepository casconfirmeRepository;

    public CasconfirmeResource(CasconfirmeService casconfirmeService, CasconfirmeRepository casconfirmeRepository) {
        this.casconfirmeService = casconfirmeService;
        this.casconfirmeRepository = casconfirmeRepository;
    }

    /**
     * {@code POST  /casconfirmes} : Create a new casconfirme.
     *
     * @param casconfirmeDTO the casconfirmeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new casconfirmeDTO, or with status {@code 400 (Bad Request)} if the casconfirme has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/casconfirmes")
    public ResponseEntity<CasconfirmeDTO> createCasconfirme(@Valid @RequestBody CasconfirmeDTO casconfirmeDTO) throws URISyntaxException {
        log.debug("REST request to save Casconfirme : {}", casconfirmeDTO);
        if (casconfirmeDTO.getId() != null) {
            throw new BadRequestAlertException("A new casconfirme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CasconfirmeDTO result = casconfirmeService.save(casconfirmeDTO);
        return ResponseEntity
            .created(new URI("/api/casconfirmes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /casconfirmes/:id} : Updates an existing casconfirme.
     *
     * @param id the id of the casconfirmeDTO to save.
     * @param casconfirmeDTO the casconfirmeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated casconfirmeDTO,
     * or with status {@code 400 (Bad Request)} if the casconfirmeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the casconfirmeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/casconfirmes/{id}")
    public ResponseEntity<CasconfirmeDTO> updateCasconfirme(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CasconfirmeDTO casconfirmeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Casconfirme : {}, {}", id, casconfirmeDTO);
        if (casconfirmeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, casconfirmeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!casconfirmeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CasconfirmeDTO result = casconfirmeService.update(casconfirmeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, casconfirmeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /casconfirmes/:id} : Partial updates given fields of an existing casconfirme, field will ignore if it is null
     *
     * @param id the id of the casconfirmeDTO to save.
     * @param casconfirmeDTO the casconfirmeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated casconfirmeDTO,
     * or with status {@code 400 (Bad Request)} if the casconfirmeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the casconfirmeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the casconfirmeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/casconfirmes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CasconfirmeDTO> partialUpdateCasconfirme(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CasconfirmeDTO casconfirmeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Casconfirme partially : {}, {}", id, casconfirmeDTO);
        if (casconfirmeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, casconfirmeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!casconfirmeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CasconfirmeDTO> result = casconfirmeService.partialUpdate(casconfirmeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, casconfirmeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /casconfirmes} : get all the casconfirmes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of casconfirmes in body.
     */
    @GetMapping("/casconfirmes")
    public ResponseEntity<List<CasconfirmeDTO>> getAllCasconfirmes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Casconfirmes");
        Page<CasconfirmeDTO> page = casconfirmeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /casconfirmes/:id} : get the "id" casconfirme.
     *
     * @param id the id of the casconfirmeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the casconfirmeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/casconfirmes/{id}")
    public ResponseEntity<CasconfirmeDTO> getCasconfirme(@PathVariable Long id) {
        log.debug("REST request to get Casconfirme : {}", id);
        Optional<CasconfirmeDTO> casconfirmeDTO = casconfirmeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(casconfirmeDTO);
    }

    /**
     * {@code DELETE  /casconfirmes/:id} : delete the "id" casconfirme.
     *
     * @param id the id of the casconfirmeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/casconfirmes/{id}")
    public ResponseEntity<Void> deleteCasconfirme(@PathVariable Long id) {
        log.debug("REST request to delete Casconfirme : {}", id);
        casconfirmeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
