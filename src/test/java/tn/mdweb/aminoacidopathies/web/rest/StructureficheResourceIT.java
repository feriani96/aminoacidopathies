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
import tn.mdweb.aminoacidopathies.domain.Etablissement;
import tn.mdweb.aminoacidopathies.domain.Structurefiche;
import tn.mdweb.aminoacidopathies.domain.enumeration.etypestructure;
import tn.mdweb.aminoacidopathies.repository.StructureficheRepository;
import tn.mdweb.aminoacidopathies.service.dto.StructureficheDTO;
import tn.mdweb.aminoacidopathies.service.mapper.StructureficheMapper;

/**
 * Integration tests for the {@link StructureficheResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StructureficheResourceIT {

    private static final etypestructure DEFAULT_TYPESTRUCTURE = etypestructure.ORIGINE;
    private static final etypestructure UPDATED_TYPESTRUCTURE = etypestructure.SUIVI;

    private static final Integer DEFAULT_ORDRE = 1;
    private static final Integer UPDATED_ORDRE = 2;

    private static final String ENTITY_API_URL = "/api/structurefiches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StructureficheRepository structureficheRepository;

    @Autowired
    private StructureficheMapper structureficheMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStructureficheMockMvc;

    private Structurefiche structurefiche;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Structurefiche createEntity(EntityManager em) {
        Structurefiche structurefiche = new Structurefiche().typestructure(DEFAULT_TYPESTRUCTURE).ordre(DEFAULT_ORDRE);
        // Add required entity
        Etablissement etablissement;
        if (TestUtil.findAll(em, Etablissement.class).isEmpty()) {
            etablissement = EtablissementResourceIT.createEntity(em);
            em.persist(etablissement);
            em.flush();
        } else {
            etablissement = TestUtil.findAll(em, Etablissement.class).get(0);
        }
        structurefiche.setEtablissement(etablissement);
        return structurefiche;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Structurefiche createUpdatedEntity(EntityManager em) {
        Structurefiche structurefiche = new Structurefiche().typestructure(UPDATED_TYPESTRUCTURE).ordre(UPDATED_ORDRE);
        // Add required entity
        Etablissement etablissement;
        if (TestUtil.findAll(em, Etablissement.class).isEmpty()) {
            etablissement = EtablissementResourceIT.createUpdatedEntity(em);
            em.persist(etablissement);
            em.flush();
        } else {
            etablissement = TestUtil.findAll(em, Etablissement.class).get(0);
        }
        structurefiche.setEtablissement(etablissement);
        return structurefiche;
    }

    @BeforeEach
    public void initTest() {
        structurefiche = createEntity(em);
    }

    @Test
    @Transactional
    void createStructurefiche() throws Exception {
        int databaseSizeBeforeCreate = structureficheRepository.findAll().size();
        // Create the Structurefiche
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);
        restStructureficheMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeCreate + 1);
        Structurefiche testStructurefiche = structureficheList.get(structureficheList.size() - 1);
        assertThat(testStructurefiche.getTypestructure()).isEqualTo(DEFAULT_TYPESTRUCTURE);
        assertThat(testStructurefiche.getOrdre()).isEqualTo(DEFAULT_ORDRE);
    }

    @Test
    @Transactional
    void createStructureficheWithExistingId() throws Exception {
        // Create the Structurefiche with an existing ID
        structurefiche.setId(1L);
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);

        int databaseSizeBeforeCreate = structureficheRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStructureficheMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTypestructureIsRequired() throws Exception {
        int databaseSizeBeforeTest = structureficheRepository.findAll().size();
        // set the field null
        structurefiche.setTypestructure(null);

        // Create the Structurefiche, which fails.
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);

        restStructureficheMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isBadRequest());

        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrdreIsRequired() throws Exception {
        int databaseSizeBeforeTest = structureficheRepository.findAll().size();
        // set the field null
        structurefiche.setOrdre(null);

        // Create the Structurefiche, which fails.
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);

        restStructureficheMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isBadRequest());

        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllStructurefiches() throws Exception {
        // Initialize the database
        structureficheRepository.saveAndFlush(structurefiche);

        // Get all the structureficheList
        restStructureficheMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(structurefiche.getId().intValue())))
            .andExpect(jsonPath("$.[*].typestructure").value(hasItem(DEFAULT_TYPESTRUCTURE.toString())))
            .andExpect(jsonPath("$.[*].ordre").value(hasItem(DEFAULT_ORDRE)));
    }

    @Test
    @Transactional
    void getStructurefiche() throws Exception {
        // Initialize the database
        structureficheRepository.saveAndFlush(structurefiche);

        // Get the structurefiche
        restStructureficheMockMvc
            .perform(get(ENTITY_API_URL_ID, structurefiche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(structurefiche.getId().intValue()))
            .andExpect(jsonPath("$.typestructure").value(DEFAULT_TYPESTRUCTURE.toString()))
            .andExpect(jsonPath("$.ordre").value(DEFAULT_ORDRE));
    }

    @Test
    @Transactional
    void getNonExistingStructurefiche() throws Exception {
        // Get the structurefiche
        restStructureficheMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStructurefiche() throws Exception {
        // Initialize the database
        structureficheRepository.saveAndFlush(structurefiche);

        int databaseSizeBeforeUpdate = structureficheRepository.findAll().size();

        // Update the structurefiche
        Structurefiche updatedStructurefiche = structureficheRepository.findById(structurefiche.getId()).get();
        // Disconnect from session so that the updates on updatedStructurefiche are not directly saved in db
        em.detach(updatedStructurefiche);
        updatedStructurefiche.typestructure(UPDATED_TYPESTRUCTURE).ordre(UPDATED_ORDRE);
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(updatedStructurefiche);

        restStructureficheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, structureficheDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isOk());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeUpdate);
        Structurefiche testStructurefiche = structureficheList.get(structureficheList.size() - 1);
        assertThat(testStructurefiche.getTypestructure()).isEqualTo(UPDATED_TYPESTRUCTURE);
        assertThat(testStructurefiche.getOrdre()).isEqualTo(UPDATED_ORDRE);
    }

    @Test
    @Transactional
    void putNonExistingStructurefiche() throws Exception {
        int databaseSizeBeforeUpdate = structureficheRepository.findAll().size();
        structurefiche.setId(count.incrementAndGet());

        // Create the Structurefiche
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStructureficheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, structureficheDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStructurefiche() throws Exception {
        int databaseSizeBeforeUpdate = structureficheRepository.findAll().size();
        structurefiche.setId(count.incrementAndGet());

        // Create the Structurefiche
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructureficheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStructurefiche() throws Exception {
        int databaseSizeBeforeUpdate = structureficheRepository.findAll().size();
        structurefiche.setId(count.incrementAndGet());

        // Create the Structurefiche
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructureficheMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStructureficheWithPatch() throws Exception {
        // Initialize the database
        structureficheRepository.saveAndFlush(structurefiche);

        int databaseSizeBeforeUpdate = structureficheRepository.findAll().size();

        // Update the structurefiche using partial update
        Structurefiche partialUpdatedStructurefiche = new Structurefiche();
        partialUpdatedStructurefiche.setId(structurefiche.getId());

        restStructureficheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStructurefiche.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStructurefiche))
            )
            .andExpect(status().isOk());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeUpdate);
        Structurefiche testStructurefiche = structureficheList.get(structureficheList.size() - 1);
        assertThat(testStructurefiche.getTypestructure()).isEqualTo(DEFAULT_TYPESTRUCTURE);
        assertThat(testStructurefiche.getOrdre()).isEqualTo(DEFAULT_ORDRE);
    }

    @Test
    @Transactional
    void fullUpdateStructureficheWithPatch() throws Exception {
        // Initialize the database
        structureficheRepository.saveAndFlush(structurefiche);

        int databaseSizeBeforeUpdate = structureficheRepository.findAll().size();

        // Update the structurefiche using partial update
        Structurefiche partialUpdatedStructurefiche = new Structurefiche();
        partialUpdatedStructurefiche.setId(structurefiche.getId());

        partialUpdatedStructurefiche.typestructure(UPDATED_TYPESTRUCTURE).ordre(UPDATED_ORDRE);

        restStructureficheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStructurefiche.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStructurefiche))
            )
            .andExpect(status().isOk());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeUpdate);
        Structurefiche testStructurefiche = structureficheList.get(structureficheList.size() - 1);
        assertThat(testStructurefiche.getTypestructure()).isEqualTo(UPDATED_TYPESTRUCTURE);
        assertThat(testStructurefiche.getOrdre()).isEqualTo(UPDATED_ORDRE);
    }

    @Test
    @Transactional
    void patchNonExistingStructurefiche() throws Exception {
        int databaseSizeBeforeUpdate = structureficheRepository.findAll().size();
        structurefiche.setId(count.incrementAndGet());

        // Create the Structurefiche
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStructureficheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, structureficheDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStructurefiche() throws Exception {
        int databaseSizeBeforeUpdate = structureficheRepository.findAll().size();
        structurefiche.setId(count.incrementAndGet());

        // Create the Structurefiche
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructureficheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStructurefiche() throws Exception {
        int databaseSizeBeforeUpdate = structureficheRepository.findAll().size();
        structurefiche.setId(count.incrementAndGet());

        // Create the Structurefiche
        StructureficheDTO structureficheDTO = structureficheMapper.toDto(structurefiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStructureficheMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(structureficheDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Structurefiche in the database
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStructurefiche() throws Exception {
        // Initialize the database
        structureficheRepository.saveAndFlush(structurefiche);

        int databaseSizeBeforeDelete = structureficheRepository.findAll().size();

        // Delete the structurefiche
        restStructureficheMockMvc
            .perform(delete(ENTITY_API_URL_ID, structurefiche.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Structurefiche> structureficheList = structureficheRepository.findAll();
        assertThat(structureficheList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
