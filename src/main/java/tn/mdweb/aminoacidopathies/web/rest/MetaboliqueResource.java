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
import tn.mdweb.aminoacidopathies.repository.MetaboliqueRepository;
import tn.mdweb.aminoacidopathies.service.MetaboliqueService;
import tn.mdweb.aminoacidopathies.service.dto.MetaboliqueDTO;
import tn.mdweb.aminoacidopathies.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.aminoacidopathies.domain.Metabolique}.
 */
@RestController
@RequestMapping("/api")
public class MetaboliqueResource {

    private final Logger log = LoggerFactory.getLogger(MetaboliqueResource.class);

    private static final String ENTITY_NAME = "metabolique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MetaboliqueService metaboliqueService;

    private final MetaboliqueRepository metaboliqueRepository;

    public MetaboliqueResource(MetaboliqueService metaboliqueService, MetaboliqueRepository metaboliqueRepository) {
        this.metaboliqueService = metaboliqueService;
        this.metaboliqueRepository = metaboliqueRepository;
    }

    /**
     * {@code POST  /metaboliques} : Create a new metabolique.
     *
     * @param metaboliqueDTO the metaboliqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new metaboliqueDTO, or with status {@code 400 (Bad Request)} if the metabolique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/metaboliques")
    public ResponseEntity<MetaboliqueDTO> createMetabolique(@RequestBody MetaboliqueDTO metaboliqueDTO) throws URISyntaxException {
        log.debug("REST request to save Metabolique : {}", metaboliqueDTO);
        if (metaboliqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new metabolique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MetaboliqueDTO result = metaboliqueService.save(metaboliqueDTO);
        return ResponseEntity
            .created(new URI("/api/metaboliques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /metaboliques/:id} : Updates an existing metabolique.
     *
     * @param id the id of the metaboliqueDTO to save.
     * @param metaboliqueDTO the metaboliqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated metaboliqueDTO,
     * or with status {@code 400 (Bad Request)} if the metaboliqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the metaboliqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/metaboliques/{id}")
    public ResponseEntity<MetaboliqueDTO> updateMetabolique(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MetaboliqueDTO metaboliqueDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Metabolique : {}, {}", id, metaboliqueDTO);
        if (metaboliqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, metaboliqueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!metaboliqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MetaboliqueDTO result = metaboliqueService.update(metaboliqueDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, metaboliqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /metaboliques/:id} : Partial updates given fields of an existing metabolique, field will ignore if it is null
     *
     * @param id the id of the metaboliqueDTO to save.
     * @param metaboliqueDTO the metaboliqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated metaboliqueDTO,
     * or with status {@code 400 (Bad Request)} if the metaboliqueDTO is not valid,
     * or with status {@code 404 (Not Found)} if the metaboliqueDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the metaboliqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/metaboliques/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MetaboliqueDTO> partialUpdateMetabolique(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MetaboliqueDTO metaboliqueDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Metabolique partially : {}, {}", id, metaboliqueDTO);
        if (metaboliqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, metaboliqueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!metaboliqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MetaboliqueDTO> result = metaboliqueService.partialUpdate(metaboliqueDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, metaboliqueDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /metaboliques} : get all the metaboliques.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of metaboliques in body.
     */
    @GetMapping("/metaboliques")
    public ResponseEntity<List<MetaboliqueDTO>> getAllMetaboliques(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Metaboliques");
        Page<MetaboliqueDTO> page = metaboliqueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /metaboliques/:id} : get the "id" metabolique.
     *
     * @param id the id of the metaboliqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the metaboliqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/metaboliques/{id}")
    public ResponseEntity<MetaboliqueDTO> getMetabolique(@PathVariable Long id) {
        log.debug("REST request to get Metabolique : {}", id);
        Optional<MetaboliqueDTO> metaboliqueDTO = metaboliqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(metaboliqueDTO);
    }

    /**
     * {@code DELETE  /metaboliques/:id} : delete the "id" metabolique.
     *
     * @param id the id of the metaboliqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/metaboliques/{id}")
    public ResponseEntity<Void> deleteMetabolique(@PathVariable Long id) {
        log.debug("REST request to delete Metabolique : {}", id);
        metaboliqueService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
