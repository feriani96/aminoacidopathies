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
import tn.mdweb.aminoacidopathies.repository.CasdecesbasageRepository;
import tn.mdweb.aminoacidopathies.service.CasdecesbasageService;
import tn.mdweb.aminoacidopathies.service.dto.CasdecesbasageDTO;
import tn.mdweb.aminoacidopathies.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.aminoacidopathies.domain.Casdecesbasage}.
 */
@RestController
@RequestMapping("/api")
public class CasdecesbasageResource {

    private final Logger log = LoggerFactory.getLogger(CasdecesbasageResource.class);

    private static final String ENTITY_NAME = "casdecesbasage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CasdecesbasageService casdecesbasageService;

    private final CasdecesbasageRepository casdecesbasageRepository;

    public CasdecesbasageResource(CasdecesbasageService casdecesbasageService, CasdecesbasageRepository casdecesbasageRepository) {
        this.casdecesbasageService = casdecesbasageService;
        this.casdecesbasageRepository = casdecesbasageRepository;
    }

    /**
     * {@code POST  /casdecesbasages} : Create a new casdecesbasage.
     *
     * @param casdecesbasageDTO the casdecesbasageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new casdecesbasageDTO, or with status {@code 400 (Bad Request)} if the casdecesbasage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/casdecesbasages")
    public ResponseEntity<CasdecesbasageDTO> createCasdecesbasage(@RequestBody CasdecesbasageDTO casdecesbasageDTO)
        throws URISyntaxException {
        log.debug("REST request to save Casdecesbasage : {}", casdecesbasageDTO);
        if (casdecesbasageDTO.getId() != null) {
            throw new BadRequestAlertException("A new casdecesbasage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CasdecesbasageDTO result = casdecesbasageService.save(casdecesbasageDTO);
        return ResponseEntity
            .created(new URI("/api/casdecesbasages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /casdecesbasages/:id} : Updates an existing casdecesbasage.
     *
     * @param id the id of the casdecesbasageDTO to save.
     * @param casdecesbasageDTO the casdecesbasageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated casdecesbasageDTO,
     * or with status {@code 400 (Bad Request)} if the casdecesbasageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the casdecesbasageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/casdecesbasages/{id}")
    public ResponseEntity<CasdecesbasageDTO> updateCasdecesbasage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CasdecesbasageDTO casdecesbasageDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Casdecesbasage : {}, {}", id, casdecesbasageDTO);
        if (casdecesbasageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, casdecesbasageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!casdecesbasageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CasdecesbasageDTO result = casdecesbasageService.update(casdecesbasageDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, casdecesbasageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /casdecesbasages/:id} : Partial updates given fields of an existing casdecesbasage, field will ignore if it is null
     *
     * @param id the id of the casdecesbasageDTO to save.
     * @param casdecesbasageDTO the casdecesbasageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated casdecesbasageDTO,
     * or with status {@code 400 (Bad Request)} if the casdecesbasageDTO is not valid,
     * or with status {@code 404 (Not Found)} if the casdecesbasageDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the casdecesbasageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/casdecesbasages/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CasdecesbasageDTO> partialUpdateCasdecesbasage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CasdecesbasageDTO casdecesbasageDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Casdecesbasage partially : {}, {}", id, casdecesbasageDTO);
        if (casdecesbasageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, casdecesbasageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!casdecesbasageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CasdecesbasageDTO> result = casdecesbasageService.partialUpdate(casdecesbasageDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, casdecesbasageDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /casdecesbasages} : get all the casdecesbasages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of casdecesbasages in body.
     */
    @GetMapping("/casdecesbasages")
    public ResponseEntity<List<CasdecesbasageDTO>> getAllCasdecesbasages(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Casdecesbasages");
        Page<CasdecesbasageDTO> page = casdecesbasageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /casdecesbasages/:id} : get the "id" casdecesbasage.
     *
     * @param id the id of the casdecesbasageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the casdecesbasageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/casdecesbasages/{id}")
    public ResponseEntity<CasdecesbasageDTO> getCasdecesbasage(@PathVariable Long id) {
        log.debug("REST request to get Casdecesbasage : {}", id);
        Optional<CasdecesbasageDTO> casdecesbasageDTO = casdecesbasageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(casdecesbasageDTO);
    }

    /**
     * {@code DELETE  /casdecesbasages/:id} : delete the "id" casdecesbasage.
     *
     * @param id the id of the casdecesbasageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/casdecesbasages/{id}")
    public ResponseEntity<Void> deleteCasdecesbasage(@PathVariable Long id) {
        log.debug("REST request to delete Casdecesbasage : {}", id);
        casdecesbasageService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
