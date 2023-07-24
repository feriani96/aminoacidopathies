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
import tn.mdweb.aminoacidopathies.domain.Structuresante;
import tn.mdweb.aminoacidopathies.repository.StructuresanteRepository;
import tn.mdweb.aminoacidopathies.service.dto.StructuresanteDTO;
import tn.mdweb.aminoacidopathies.service.mapper.StructuresanteMapper;

/**
 * Integration tests for the {@link StructuresanteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StructuresanteResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/structuresantes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StructuresanteRepository structuresanteRepository;

    @Autowired
    private StructuresanteMapper structuresanteMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStructuresanteMockMvc;

    private Structuresante structuresante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Structuresante createEntity(EntityManager em) {
        Structuresante structuresante = new Structuresante().name(DEFAULT_NAME);
        return structuresante;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Structuresante createUpdatedEntity(EntityManager em) {
        Structuresante structuresante = new Structuresante().name(UPDATED_NAME);
        return structuresante;
    }

    @BeforeEach
    public void initTest() {
        structuresante = createEntity(em);
    }

    @Test
    @Transactional
    void createStructuresante() throws Exception {
        int databaseSizeBeforeCreate = structuresanteRepository.findAll().size();
        // Create the Structuresante
        StructuresanteDTO structuresanteDTO = structuresanteMapper.toDto(structuresante);
        restStructuresanteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structuresanteDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeCreate + 1);
        Structuresante testStructuresante = structuresanteList.get(structuresanteList.size() - 1);
        assertThat(testStructuresante.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void createStructuresanteWithExistingId() throws Exception {
        // Create the Structuresante with an existing ID
        structuresante.setId(1L);
        StructuresanteDTO structuresanteDTO = structuresanteMapper.toDto(structuresante);

        int databaseSizeBeforeCreate = structuresanteRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStructuresanteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structuresanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStructuresantes() throws Exception {
        // Initialize the database
        structuresanteRepository.saveAndFlush(structuresante);

        // Get all the structuresanteList
        restStructuresanteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(structuresante.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getStructuresante() throws Exception {
        // Initialize the database
        structuresanteRepository.saveAndFlush(structuresante);

        // Get the structuresante
        restStructuresanteMockMvc
            .perform(get(ENTITY_API_URL_ID, structuresante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(structuresante.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingStructuresante() throws Exception {
        // Get the structuresante
        restStructuresanteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStructuresante() throws Exception {
        // Initialize the database
        structuresanteRepository.saveAndFlush(structuresante);

        int databaseSizeBeforeUpdate = structuresanteRepository.findAll().size();

        // Update the structuresante
        Structuresante updatedStructuresante = structuresanteRepository.findById(structuresante.getId()).get();
        // Disconnect from session so that the updates on updatedStructuresante are not directly saved in db
        em.detach(updatedStructuresante);
        updatedStructuresante.name(UPDATED_NAME);
        StructuresanteDTO structuresanteDTO = structuresanteMapper.toDto(updatedStructuresante);

        restStructuresanteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, structuresanteDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(structuresanteDTO))
            )
            .andExpect(status().isOk());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeUpdate);
        Structuresante testStructuresante = structuresanteList.get(structuresanteList.size() - 1);
        assertThat(testStructuresante.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void putNonExistingStructuresante() throws Exception {
        int databaseSizeBeforeUpdate = structuresanteRepository.findAll().size();
        structuresante.setId(count.incrementAndGet());

        // Create the Structuresante
        StructuresanteDTO structuresanteDTO = structuresanteMapper.toDto(structuresante);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStructuresanteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, structuresanteDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(structuresanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStructuresante() throws Exception {
        int databaseSizeBeforeUpdate = structuresanteRepository.findAll().size();
        structuresante.setId(count.incrementAndGet());

        // Create the Structuresante
        StructuresanteDTO structuresanteDTO = structuresanteMapper.toDto(structuresante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructuresanteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(structuresanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStructuresante() throws Exception {
        int databaseSizeBeforeUpdate = structuresanteRepository.findAll().size();
        structuresante.setId(count.incrementAndGet());

        // Create the Structuresante
        StructuresanteDTO structuresanteDTO = structuresanteMapper.toDto(structuresante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructuresanteMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structuresanteDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStructuresanteWithPatch() throws Exception {
        // Initialize the database
        structuresanteRepository.saveAndFlush(structuresante);

        int databaseSizeBeforeUpdate = structuresanteRepository.findAll().size();

        // Update the structuresante using partial update
        Structuresante partialUpdatedStructuresante = new Structuresante();
        partialUpdatedStructuresante.setId(structuresante.getId());

        partialUpdatedStructuresante.name(UPDATED_NAME);

        restStructuresanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStructuresante.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStructuresante))
            )
            .andExpect(status().isOk());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeUpdate);
        Structuresante testStructuresante = structuresanteList.get(structuresanteList.size() - 1);
        assertThat(testStructuresante.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void fullUpdateStructuresanteWithPatch() throws Exception {
        // Initialize the database
        structuresanteRepository.saveAndFlush(structuresante);

        int databaseSizeBeforeUpdate = structuresanteRepository.findAll().size();

        // Update the structuresante using partial update
        Structuresante partialUpdatedStructuresante = new Structuresante();
        partialUpdatedStructuresante.setId(structuresante.getId());

        partialUpdatedStructuresante.name(UPDATED_NAME);

        restStructuresanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStructuresante.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStructuresante))
            )
            .andExpect(status().isOk());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeUpdate);
        Structuresante testStructuresante = structuresanteList.get(structuresanteList.size() - 1);
        assertThat(testStructuresante.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingStructuresante() throws Exception {
        int databaseSizeBeforeUpdate = structuresanteRepository.findAll().size();
        structuresante.setId(count.incrementAndGet());

        // Create the Structuresante
        StructuresanteDTO structuresanteDTO = structuresanteMapper.toDto(structuresante);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStructuresanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, structuresanteDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(structuresanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStructuresante() throws Exception {
        int databaseSizeBeforeUpdate = structuresanteRepository.findAll().size();
        structuresante.setId(count.incrementAndGet());

        // Create the Structuresante
        StructuresanteDTO structuresanteDTO = structuresanteMapper.toDto(structuresante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructuresanteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(structuresanteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStructuresante() throws Exception {
        int databaseSizeBeforeUpdate = structuresanteRepository.findAll().size();
        structuresante.setId(count.incrementAndGet());

        // Create the Structuresante
        StructuresanteDTO structuresanteDTO = structuresanteMapper.toDto(structuresante);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructuresanteMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(structuresanteDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Structuresante in the database
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStructuresante() throws Exception {
        // Initialize the database
        structuresanteRepository.saveAndFlush(structuresante);

        int databaseSizeBeforeDelete = structuresanteRepository.findAll().size();

        // Delete the structuresante
        restStructuresanteMockMvc
            .perform(delete(ENTITY_API_URL_ID, structuresante.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Structuresante> structuresanteList = structuresanteRepository.findAll();
        assertThat(structuresanteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
