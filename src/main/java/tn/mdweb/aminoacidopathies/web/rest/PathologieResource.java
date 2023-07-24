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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.mdweb.aminoacidopathies.repository.PathologieRepository;
import tn.mdweb.aminoacidopathies.service.PathologieService;
import tn.mdweb.aminoacidopathies.service.dto.PathologieDTO;
import tn.mdweb.aminoacidopathies.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.aminoacidopathies.domain.Pathologie}.
 */
@RestController
@RequestMapping("/api")
public class PathologieResource {

    private final Logger log = LoggerFactory.getLogger(PathologieResource.class);

    private static final String ENTITY_NAME = "pathologie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PathologieService pathologieService;

    private final PathologieRepository pathologieRepository;

    public PathologieResource(PathologieService pathologieService, PathologieRepository pathologieRepository) {
        this.pathologieService = pathologieService;
        this.pathologieRepository = pathologieRepository;
    }

    /**
     * {@code POST  /pathologies} : Create a new pathologie.
     *
     * @param pathologieDTO the pathologieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pathologieDTO, or with status {@code 400 (Bad Request)} if the pathologie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pathologies")
    public ResponseEntity<PathologieDTO> createPathologie(@Valid @RequestBody PathologieDTO pathologieDTO) throws URISyntaxException {
        log.debug("REST request to save Pathologie : {}", pathologieDTO);
        if (pathologieDTO.getId() != null) {
            throw new BadRequestAlertException("A new pathologie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PathologieDTO result = pathologieService.save(pathologieDTO);
        return ResponseEntity
            .created(new URI("/api/pathologies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pathologies/:id} : Updates an existing pathologie.
     *
     * @param id the id of the pathologieDTO to save.
     * @param pathologieDTO the pathologieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pathologieDTO,
     * or with status {@code 400 (Bad Request)} if the pathologieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pathologieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pathologies/{id}")
    public ResponseEntity<PathologieDTO> updatePathologie(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PathologieDTO pathologieDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Pathologie : {}, {}", id, pathologieDTO);
        if (pathologieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pathologieDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pathologieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PathologieDTO result = pathologieService.update(pathologieDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pathologieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pathologies/:id} : Partial updates given fields of an existing pathologie, field will ignore if it is null
     *
     * @param id the id of the pathologieDTO to save.
     * @param pathologieDTO the pathologieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pathologieDTO,
     * or with status {@code 400 (Bad Request)} if the pathologieDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pathologieDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pathologieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pathologies/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PathologieDTO> partialUpdatePathologie(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PathologieDTO pathologieDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Pathologie partially : {}, {}", id, pathologieDTO);
        if (pathologieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pathologieDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pathologieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PathologieDTO> result = pathologieService.partialUpdate(pathologieDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pathologieDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pathologies} : get all the pathologies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pathologies in body.
     */
    @GetMapping("/pathologies")
    public List<PathologieDTO> getAllPathologies() {
        log.debug("REST request to get all Pathologies");
        return pathologieService.findAll();
    }

    /**
     * {@code GET  /pathologies/:id} : get the "id" pathologie.
     *
     * @param id the id of the pathologieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pathologieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pathologies/{id}")
    public ResponseEntity<PathologieDTO> getPathologie(@PathVariable Long id) {
        log.debug("REST request to get Pathologie : {}", id);
        Optional<PathologieDTO> pathologieDTO = pathologieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pathologieDTO);
    }

    /**
     * {@code DELETE  /pathologies/:id} : delete the "id" pathologie.
     *
     * @param id the id of the pathologieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pathologies/{id}")
    public ResponseEntity<Void> deletePathologie(@PathVariable Long id) {
        log.debug("REST request to delete Pathologie : {}", id);
        pathologieService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
