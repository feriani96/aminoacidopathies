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
import tn.mdweb.aminoacidopathies.repository.StructuresanteRepository;
import tn.mdweb.aminoacidopathies.service.StructuresanteService;
import tn.mdweb.aminoacidopathies.service.dto.StructuresanteDTO;
import tn.mdweb.aminoacidopathies.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.aminoacidopathies.domain.Structuresante}.
 */
@RestController
@RequestMapping("/api")
public class StructuresanteResource {

    private final Logger log = LoggerFactory.getLogger(StructuresanteResource.class);

    private static final String ENTITY_NAME = "structuresante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StructuresanteService structuresanteService;

    private final StructuresanteRepository structuresanteRepository;

    public StructuresanteResource(StructuresanteService structuresanteService, StructuresanteRepository structuresanteRepository) {
        this.structuresanteService = structuresanteService;
        this.structuresanteRepository = structuresanteRepository;
    }

    /**
     * {@code POST  /structuresantes} : Create a new structuresante.
     *
     * @param structuresanteDTO the structuresanteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new structuresanteDTO, or with status {@code 400 (Bad Request)} if the structuresante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/structuresantes")
    public ResponseEntity<StructuresanteDTO> createStructuresante(@Valid @RequestBody StructuresanteDTO structuresanteDTO)
        throws URISyntaxException {
        log.debug("REST request to save Structuresante : {}", structuresanteDTO);
        if (structuresanteDTO.getId() != null) {
            throw new BadRequestAlertException("A new structuresante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StructuresanteDTO result = structuresanteService.save(structuresanteDTO);
        return ResponseEntity
            .created(new URI("/api/structuresantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /structuresantes/:id} : Updates an existing structuresante.
     *
     * @param id the id of the structuresanteDTO to save.
     * @param structuresanteDTO the structuresanteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structuresanteDTO,
     * or with status {@code 400 (Bad Request)} if the structuresanteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the structuresanteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/structuresantes/{id}")
    public ResponseEntity<StructuresanteDTO> updateStructuresante(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody StructuresanteDTO structuresanteDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Structuresante : {}, {}", id, structuresanteDTO);
        if (structuresanteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, structuresanteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!structuresanteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StructuresanteDTO result = structuresanteService.update(structuresanteDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structuresanteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /structuresantes/:id} : Partial updates given fields of an existing structuresante, field will ignore if it is null
     *
     * @param id the id of the structuresanteDTO to save.
     * @param structuresanteDTO the structuresanteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structuresanteDTO,
     * or with status {@code 400 (Bad Request)} if the structuresanteDTO is not valid,
     * or with status {@code 404 (Not Found)} if the structuresanteDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the structuresanteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/structuresantes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StructuresanteDTO> partialUpdateStructuresante(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody StructuresanteDTO structuresanteDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Structuresante partially : {}, {}", id, structuresanteDTO);
        if (structuresanteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, structuresanteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!structuresanteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StructuresanteDTO> result = structuresanteService.partialUpdate(structuresanteDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structuresanteDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /structuresantes} : get all the structuresantes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of structuresantes in body.
     */
    @GetMapping("/structuresantes")
    public List<StructuresanteDTO> getAllStructuresantes() {
        log.debug("REST request to get all Structuresantes");
        return structuresanteService.findAll();
    }

    /**
     * {@code GET  /structuresantes/:id} : get the "id" structuresante.
     *
     * @param id the id of the structuresanteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the structuresanteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/structuresantes/{id}")
    public ResponseEntity<StructuresanteDTO> getStructuresante(@PathVariable Long id) {
        log.debug("REST request to get Structuresante : {}", id);
        Optional<StructuresanteDTO> structuresanteDTO = structuresanteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(structuresanteDTO);
    }

    /**
     * {@code DELETE  /structuresantes/:id} : delete the "id" structuresante.
     *
     * @param id the id of the structuresanteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/structuresantes/{id}")
    public ResponseEntity<Void> deleteStructuresante(@PathVariable Long id) {
        log.debug("REST request to delete Structuresante : {}", id);
        structuresanteService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
