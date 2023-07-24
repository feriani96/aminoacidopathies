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
import tn.mdweb.aminoacidopathies.repository.ServicesanteRepository;
import tn.mdweb.aminoacidopathies.service.ServicesanteService;
import tn.mdweb.aminoacidopathies.service.dto.ServicesanteDTO;
import tn.mdweb.aminoacidopathies.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.mdweb.aminoacidopathies.domain.Servicesante}.
 */
@RestController
@RequestMapping("/api")
public class ServicesanteResource {

    private final Logger log = LoggerFactory.getLogger(ServicesanteResource.class);

    private static final String ENTITY_NAME = "servicesante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServicesanteService servicesanteService;

    private final ServicesanteRepository servicesanteRepository;

    public ServicesanteResource(ServicesanteService servicesanteService, ServicesanteRepository servicesanteRepository) {
        this.servicesanteService = servicesanteService;
        this.servicesanteRepository = servicesanteRepository;
    }

    /**
     * {@code POST  /servicesantes} : Create a new servicesante.
     *
     * @param servicesanteDTO the servicesanteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new servicesanteDTO, or with status {@code 400 (Bad Request)} if the servicesante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/servicesantes")
    public ResponseEntity<ServicesanteDTO> createServicesante(@Valid @RequestBody ServicesanteDTO servicesanteDTO)
        throws URISyntaxException {
        log.debug("REST request to save Servicesante : {}", servicesanteDTO);
        if (servicesanteDTO.getId() != null) {
            throw new BadRequestAlertException("A new servicesante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServicesanteDTO result = servicesanteService.save(servicesanteDTO);
        return ResponseEntity
            .created(new URI("/api/servicesantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /servicesantes/:id} : Updates an existing servicesante.
     *
     * @param id the id of the servicesanteDTO to save.
     * @param servicesanteDTO the servicesanteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servicesanteDTO,
     * or with status {@code 400 (Bad Request)} if the servicesanteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the servicesanteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/servicesantes/{id}")
    public ResponseEntity<ServicesanteDTO> updateServicesante(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ServicesanteDTO servicesanteDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Servicesante : {}, {}", id, servicesanteDTO);
        if (servicesanteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, servicesanteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!servicesanteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ServicesanteDTO result = servicesanteService.update(servicesanteDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, servicesanteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /servicesantes/:id} : Partial updates given fields of an existing servicesante, field will ignore if it is null
     *
     * @param id the id of the servicesanteDTO to save.
     * @param servicesanteDTO the servicesanteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servicesanteDTO,
     * or with status {@code 400 (Bad Request)} if the servicesanteDTO is not valid,
     * or with status {@code 404 (Not Found)} if the servicesanteDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the servicesanteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/servicesantes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ServicesanteDTO> partialUpdateServicesante(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ServicesanteDTO servicesanteDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Servicesante partially : {}, {}", id, servicesanteDTO);
        if (servicesanteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, servicesanteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!servicesanteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ServicesanteDTO> result = servicesanteService.partialUpdate(servicesanteDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, servicesanteDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /servicesantes} : get all the servicesantes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servicesantes in body.
     */
    @GetMapping("/servicesantes")
    public ResponseEntity<List<ServicesanteDTO>> getAllServicesantes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Servicesantes");
        Page<ServicesanteDTO> page = servicesanteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /servicesantes/:id} : get the "id" servicesante.
     *
     * @param id the id of the servicesanteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the servicesanteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/servicesantes/{id}")
    public ResponseEntity<ServicesanteDTO> getServicesante(@PathVariable Long id) {
        log.debug("REST request to get Servicesante : {}", id);
        Optional<ServicesanteDTO> servicesanteDTO = servicesanteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(servicesanteDTO);
    }

    /**
     * {@code DELETE  /servicesantes/:id} : delete the "id" servicesante.
     *
     * @param id the id of the servicesanteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/servicesantes/{id}")
    public ResponseEntity<Void> deleteServicesante(@PathVariable Long id) {
        log.debug("REST request to delete Servicesante : {}", id);
        servicesanteService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
