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
import tn.mdweb.aminoacidopathies.repository.StructureficheRepository;
import tn.mdweb.aminoacidopathies.service.StructureficheService;
import tn.mdweb.aminoacidopathies.service.dto.StructureficheDTO;
import tn.mdweb.aminoacidopathies.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.aminoacidopathies.domain.Structurefiche}.
 */
@RestController
@RequestMapping("/api")
public class StructureficheResource {

    private final Logger log = LoggerFactory.getLogger(StructureficheResource.class);

    private static final String ENTITY_NAME = "structurefiche";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StructureficheService structureficheService;

    private final StructureficheRepository structureficheRepository;

    public StructureficheResource(StructureficheService structureficheService, StructureficheRepository structureficheRepository) {
        this.structureficheService = structureficheService;
        this.structureficheRepository = structureficheRepository;
    }

    /**
     * {@code POST  /structurefiches} : Create a new structurefiche.
     *
     * @param structureficheDTO the structureficheDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new structureficheDTO, or with status {@code 400 (Bad Request)} if the structurefiche has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/structurefiches")
    public ResponseEntity<StructureficheDTO> createStructurefiche(@Valid @RequestBody StructureficheDTO structureficheDTO)
        throws URISyntaxException {
        log.debug("REST request to save Structurefiche : {}", structureficheDTO);
        if (structureficheDTO.getId() != null) {
            throw new BadRequestAlertException("A new structurefiche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StructureficheDTO result = structureficheService.save(structureficheDTO);
        return ResponseEntity
            .created(new URI("/api/structurefiches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /structurefiches/:id} : Updates an existing structurefiche.
     *
     * @param id the id of the structureficheDTO to save.
     * @param structureficheDTO the structureficheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structureficheDTO,
     * or with status {@code 400 (Bad Request)} if the structureficheDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the structureficheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/structurefiches/{id}")
    public ResponseEntity<StructureficheDTO> updateStructurefiche(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody StructureficheDTO structureficheDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Structurefiche : {}, {}", id, structureficheDTO);
        if (structureficheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, structureficheDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!structureficheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StructureficheDTO result = structureficheService.update(structureficheDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structureficheDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /structurefiches/:id} : Partial updates given fields of an existing structurefiche, field will ignore if it is null
     *
     * @param id the id of the structureficheDTO to save.
     * @param structureficheDTO the structureficheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structureficheDTO,
     * or with status {@code 400 (Bad Request)} if the structureficheDTO is not valid,
     * or with status {@code 404 (Not Found)} if the structureficheDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the structureficheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/structurefiches/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StructureficheDTO> partialUpdateStructurefiche(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody StructureficheDTO structureficheDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Structurefiche partially : {}, {}", id, structureficheDTO);
        if (structureficheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, structureficheDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!structureficheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StructureficheDTO> result = structureficheService.partialUpdate(structureficheDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structureficheDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /structurefiches} : get all the structurefiches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of structurefiches in body.
     */
    @GetMapping("/structurefiches")
    public ResponseEntity<List<StructureficheDTO>> getAllStructurefiches(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Structurefiches");
        Page<StructureficheDTO> page = structureficheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /structurefiches/:id} : get the "id" structurefiche.
     *
     * @param id the id of the structureficheDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the structureficheDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/structurefiches/{id}")
    public ResponseEntity<StructureficheDTO> getStructurefiche(@PathVariable Long id) {
        log.debug("REST request to get Structurefiche : {}", id);
        Optional<StructureficheDTO> structureficheDTO = structureficheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(structureficheDTO);
    }

    /**
     * {@code DELETE  /structurefiches/:id} : delete the "id" structurefiche.
     *
     * @param id the id of the structureficheDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/structurefiches/{id}")
    public ResponseEntity<Void> deleteStructurefiche(@PathVariable Long id) {
        log.debug("REST request to delete Structurefiche : {}", id);
        structureficheService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
