package tn.mdweb.aminoacidopathies.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.IntegrationTest;
import tn.mdweb.aminoacidopathies.domain.Servicesante;
import tn.mdweb.aminoacidopathies.repository.ServicesanteRepository;
import tn.mdweb.aminoacidopathies.service.dto.ServicesanteDTO;
import tn.mdweb.aminoacidopathies.service.mapper.ServicesanteMapper;

/**
 * Integration tests for the {@link ServicesanteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServicesanteResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/servicesantes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ServicesanteRepository servicesanteRepository;

    @Autowired
    private ServicesanteMapper servicesanteMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServicesanteMockMvc;

    private Servicesante servicesante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicesante createEntity(EntityManager em) {
        Servicesante servicesante = new Servicesante().nom(DEFAULT_NOM);
        return servicesante;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicesante createUpdatedEntity(EntityManager em) {
        Servicesante servicesante = new Servicesante().nom(UPDATED_NOM);
        return servicesante;
    }

    @BeforeEach
    public void initTest() {
        servicesante = createEntity(em);
    }

    @Test
    @Transactional
    void createServicesante() throws Exception {
        int databaseSizeBeforeCreate = servicesanteRepository.findAll().size();
        // Create the Servicesante
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(servicesante);
        restServicesanteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeCreate + 1);
        Servicesante testServicesante = servicesanteList.get(servicesanteList.size() - 1);
        assertThat(testServicesante.getNom()).isEqualTo(DEFAULT_NOM);
    }

    @Test
    @Transactional
    void createServicesanteWithExistingId() throws Exception {
        // Create the Servicesante with an existing ID
        servicesante.setId(1L);
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(servicesante);

        int databaseSizeBeforeCreate = servicesanteRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServicesanteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = servicesanteRepository.findAll().size();
        // set the field null
        servicesante.setNom(null);

        // Create the Servicesante, which fails.
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(servicesante);

        restServicesanteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isBadRequest());

        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllServicesantes() throws Exception {
        // Initialize the database
        servicesanteRepository.saveAndFlush(servicesante);

        // Get all the servicesanteList
        restServicesanteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(servicesante.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)));
    }

    @Test
    @Transactional
    void getServicesante() throws Exception {
        // Initialize the database
        servicesanteRepository.saveAndFlush(servicesante);

        // Get the servicesante
        restServicesanteMockMvc
            .perform(get(ENTITY_API_URL_ID, servicesante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(servicesante.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM));
    }

    @Test
    @Transactional
    void getNonExistingServicesante() throws Exception {
        // Get the servicesante
        restServicesanteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingServicesante() throws Exception {
        // Initialize the database
        servicesanteRepository.saveAndFlush(servicesante);

        int databaseSizeBeforeUpdate = servicesanteRepository.findAll().size();

        // Update the servicesante
        Servicesante updatedServicesante = servicesanteRepository.findById(servicesante.getId()).get();
        // Disconnect from session so that the updates on updatedServicesante are not directly saved in db
        em.detach(updatedServicesante);
        updatedServicesante.nom(UPDATED_NOM);
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(updatedServicesante);

        restServicesanteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, servicesanteDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isOk());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeUpdate);
        Servicesante testServicesante = servicesanteList.get(servicesanteList.size() - 1);
        assertThat(testServicesante.getNom()).isEqualTo(UPDATED_NOM);
    }

    @Test
    @Transactional
    void putNonExistingServicesante() throws Exception {
        int databaseSizeBeforeUpdate = servicesanteRepository.findAll().size();
        servicesante.setId(count.incrementAndGet());

        // Create the Servicesante
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(servicesante);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicesanteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, servicesanteDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchServicesante() throws Exception {
        int databaseSizeBeforeUpdate = servicesanteRepository.findAll().size();
        servicesante.setId(count.incrementAndGet());

        // Create the Servicesante
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(servicesante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicesanteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamServicesante() throws Exception {
        int databaseSizeBeforeUpdate = servicesanteRepository.findAll().size();
        servicesante.setId(count.incrementAndGet());

        // Create the Servicesante
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(servicesante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicesanteMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateServicesanteWithPatch() throws Exception {
        // Initialize the database
        servicesanteRepository.saveAndFlush(servicesante);

        int databaseSizeBeforeUpdate = servicesanteRepository.findAll().size();

        // Update the servicesante using partial update
        Servicesante partialUpdatedServicesante = new Servicesante();
        partialUpdatedServicesante.setId(servicesante.getId());

        restServicesanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServicesante.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServicesante))
            )
            .andExpect(status().isOk());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeUpdate);
        Servicesante testServicesante = servicesanteList.get(servicesanteList.size() - 1);
        assertThat(testServicesante.getNom()).isEqualTo(DEFAULT_NOM);
    }

    @Test
    @Transactional
    void fullUpdateServicesanteWithPatch() throws Exception {
        // Initialize the database
        servicesanteRepository.saveAndFlush(servicesante);

        int databaseSizeBeforeUpdate = servicesanteRepository.findAll().size();

        // Update the servicesante using partial update
        Servicesante partialUpdatedServicesante = new Servicesante();
        partialUpdatedServicesante.setId(servicesante.getId());

        partialUpdatedServicesante.nom(UPDATED_NOM);

        restServicesanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServicesante.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServicesante))
            )
            .andExpect(status().isOk());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeUpdate);
        Servicesante testServicesante = servicesanteList.get(servicesanteList.size() - 1);
        assertThat(testServicesante.getNom()).isEqualTo(UPDATED_NOM);
    }

    @Test
    @Transactional
    void patchNonExistingServicesante() throws Exception {
        int databaseSizeBeforeUpdate = servicesanteRepository.findAll().size();
        servicesante.setId(count.incrementAndGet());

        // Create the Servicesante
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(servicesante);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicesanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, servicesanteDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchServicesante() throws Exception {
        int databaseSizeBeforeUpdate = servicesanteRepository.findAll().size();
        servicesante.setId(count.incrementAndGet());

        // Create the Servicesante
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(servicesante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicesanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamServicesante() throws Exception {
        int databaseSizeBeforeUpdate = servicesanteRepository.findAll().size();
        servicesante.setId(count.incrementAndGet());

        // Create the Servicesante
        ServicesanteDTO servicesanteDTO = servicesanteMapper.toDto(servicesante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicesanteMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(servicesanteDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Servicesante in the database
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteServicesante() throws Exception {
        // Initialize the database
        servicesanteRepository.saveAndFlush(servicesante);

        int databaseSizeBeforeDelete = servicesanteRepository.findAll().size();

        // Delete the servicesante
        restServicesanteMockMvc
            .perform(delete(ENTITY_API_URL_ID, servicesante.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Servicesante> servicesanteList = servicesanteRepository.findAll();
        assertThat(servicesanteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
