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
import tn.mdweb.aminoacidopathies.domain.Metabolique;
import tn.mdweb.aminoacidopathies.domain.enumeration.eResultat;
import tn.mdweb.aminoacidopathies.domain.enumeration.efait;
import tn.mdweb.aminoacidopathies.domain.enumeration.elaboratoire;
import tn.mdweb.aminoacidopathies.domain.enumeration.ename;
import tn.mdweb.aminoacidopathies.repository.MetaboliqueRepository;
import tn.mdweb.aminoacidopathies.service.dto.MetaboliqueDTO;
import tn.mdweb.aminoacidopathies.service.mapper.MetaboliqueMapper;

/**
 * Integration tests for the {@link MetaboliqueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MetaboliqueResourceIT {

    private static final ename DEFAULT_NAME = ename.BRANDT;
    private static final ename UPDATED_NAME = ename.BARBER;

    private static final efait DEFAULT_FAIT = efait.NF;
    private static final efait UPDATED_FAIT = efait.NP;

    private static final elaboratoire DEFAULT_LABORATOIRE = elaboratoire.BIOCHIMIERABTA;
    private static final elaboratoire UPDATED_LABORATOIRE = elaboratoire.BIOCHIMIESOUSSE_FHACHED;

    private static final eResultat DEFAULT_RESULTAT = eResultat.PATHOLOGIQUE;
    private static final eResultat UPDATED_RESULTAT = eResultat.NORMAL;

    private static final String ENTITY_API_URL = "/api/metaboliques";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MetaboliqueRepository metaboliqueRepository;

    @Autowired
    private MetaboliqueMapper metaboliqueMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMetaboliqueMockMvc;

    private Metabolique metabolique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Metabolique createEntity(EntityManager em) {
        Metabolique metabolique = new Metabolique()
            .name(DEFAULT_NAME)
            .fait(DEFAULT_FAIT)
            .laboratoire(DEFAULT_LABORATOIRE)
            .resultat(DEFAULT_RESULTAT);
        return metabolique;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Metabolique createUpdatedEntity(EntityManager em) {
        Metabolique metabolique = new Metabolique()
            .name(UPDATED_NAME)
            .fait(UPDATED_FAIT)
            .laboratoire(UPDATED_LABORATOIRE)
            .resultat(UPDATED_RESULTAT);
        return metabolique;
    }

    @BeforeEach
    public void initTest() {
        metabolique = createEntity(em);
    }

    @Test
    @Transactional
    void createMetabolique() throws Exception {
        int databaseSizeBeforeCreate = metaboliqueRepository.findAll().size();
        // Create the Metabolique
        MetaboliqueDTO metaboliqueDTO = metaboliqueMapper.toDto(metabolique);
        restMetaboliqueMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(metaboliqueDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeCreate + 1);
        Metabolique testMetabolique = metaboliqueList.get(metaboliqueList.size() - 1);
        assertThat(testMetabolique.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMetabolique.getFait()).isEqualTo(DEFAULT_FAIT);
        assertThat(testMetabolique.getLaboratoire()).isEqualTo(DEFAULT_LABORATOIRE);
        assertThat(testMetabolique.getResultat()).isEqualTo(DEFAULT_RESULTAT);
    }

    @Test
    @Transactional
    void createMetaboliqueWithExistingId() throws Exception {
        // Create the Metabolique with an existing ID
        metabolique.setId(1L);
        MetaboliqueDTO metaboliqueDTO = metaboliqueMapper.toDto(metabolique);

        int databaseSizeBeforeCreate = metaboliqueRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMetaboliqueMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(metaboliqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMetaboliques() throws Exception {
        // Initialize the database
        metaboliqueRepository.saveAndFlush(metabolique);

        // Get all the metaboliqueList
        restMetaboliqueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(metabolique.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fait").value(hasItem(DEFAULT_FAIT.toString())))
            .andExpect(jsonPath("$.[*].laboratoire").value(hasItem(DEFAULT_LABORATOIRE.toString())))
            .andExpect(jsonPath("$.[*].resultat").value(hasItem(DEFAULT_RESULTAT.toString())));
    }

    @Test
    @Transactional
    void getMetabolique() throws Exception {
        // Initialize the database
        metaboliqueRepository.saveAndFlush(metabolique);

        // Get the metabolique
        restMetaboliqueMockMvc
            .perform(get(ENTITY_API_URL_ID, metabolique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(metabolique.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fait").value(DEFAULT_FAIT.toString()))
            .andExpect(jsonPath("$.laboratoire").value(DEFAULT_LABORATOIRE.toString()))
            .andExpect(jsonPath("$.resultat").value(DEFAULT_RESULTAT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingMetabolique() throws Exception {
        // Get the metabolique
        restMetaboliqueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMetabolique() throws Exception {
        // Initialize the database
        metaboliqueRepository.saveAndFlush(metabolique);

        int databaseSizeBeforeUpdate = metaboliqueRepository.findAll().size();

        // Update the metabolique
        Metabolique updatedMetabolique = metaboliqueRepository.findById(metabolique.getId()).get();
        // Disconnect from session so that the updates on updatedMetabolique are not directly saved in db
        em.detach(updatedMetabolique);
        updatedMetabolique.name(UPDATED_NAME).fait(UPDATED_FAIT).laboratoire(UPDATED_LABORATOIRE).resultat(UPDATED_RESULTAT);
        MetaboliqueDTO metaboliqueDTO = metaboliqueMapper.toDto(updatedMetabolique);

        restMetaboliqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, metaboliqueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(metaboliqueDTO))
            )
            .andExpect(status().isOk());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeUpdate);
        Metabolique testMetabolique = metaboliqueList.get(metaboliqueList.size() - 1);
        assertThat(testMetabolique.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMetabolique.getFait()).isEqualTo(UPDATED_FAIT);
        assertThat(testMetabolique.getLaboratoire()).isEqualTo(UPDATED_LABORATOIRE);
        assertThat(testMetabolique.getResultat()).isEqualTo(UPDATED_RESULTAT);
    }

    @Test
    @Transactional
    void putNonExistingMetabolique() throws Exception {
        int databaseSizeBeforeUpdate = metaboliqueRepository.findAll().size();
        metabolique.setId(count.incrementAndGet());

        // Create the Metabolique
        MetaboliqueDTO metaboliqueDTO = metaboliqueMapper.toDto(metabolique);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMetaboliqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, metaboliqueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(metaboliqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMetabolique() throws Exception {
        int databaseSizeBeforeUpdate = metaboliqueRepository.findAll().size();
        metabolique.setId(count.incrementAndGet());

        // Create the Metabolique
        MetaboliqueDTO metaboliqueDTO = metaboliqueMapper.toDto(metabolique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMetaboliqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(metaboliqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMetabolique() throws Exception {
        int databaseSizeBeforeUpdate = metaboliqueRepository.findAll().size();
        metabolique.setId(count.incrementAndGet());

        // Create the Metabolique
        MetaboliqueDTO metaboliqueDTO = metaboliqueMapper.toDto(metabolique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMetaboliqueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(metaboliqueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMetaboliqueWithPatch() throws Exception {
        // Initialize the database
        metaboliqueRepository.saveAndFlush(metabolique);

        int databaseSizeBeforeUpdate = metaboliqueRepository.findAll().size();

        // Update the metabolique using partial update
        Metabolique partialUpdatedMetabolique = new Metabolique();
        partialUpdatedMetabolique.setId(metabolique.getId());

        partialUpdatedMetabolique.resultat(UPDATED_RESULTAT);

        restMetaboliqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMetabolique.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMetabolique))
            )
            .andExpect(status().isOk());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeUpdate);
        Metabolique testMetabolique = metaboliqueList.get(metaboliqueList.size() - 1);
        assertThat(testMetabolique.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMetabolique.getFait()).isEqualTo(DEFAULT_FAIT);
        assertThat(testMetabolique.getLaboratoire()).isEqualTo(DEFAULT_LABORATOIRE);
        assertThat(testMetabolique.getResultat()).isEqualTo(UPDATED_RESULTAT);
    }

    @Test
    @Transactional
    void fullUpdateMetaboliqueWithPatch() throws Exception {
        // Initialize the database
        metaboliqueRepository.saveAndFlush(metabolique);

        int databaseSizeBeforeUpdate = metaboliqueRepository.findAll().size();

        // Update the metabolique using partial update
        Metabolique partialUpdatedMetabolique = new Metabolique();
        partialUpdatedMetabolique.setId(metabolique.getId());

        partialUpdatedMetabolique.name(UPDATED_NAME).fait(UPDATED_FAIT).laboratoire(UPDATED_LABORATOIRE).resultat(UPDATED_RESULTAT);

        restMetaboliqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMetabolique.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMetabolique))
            )
            .andExpect(status().isOk());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeUpdate);
        Metabolique testMetabolique = metaboliqueList.get(metaboliqueList.size() - 1);
        assertThat(testMetabolique.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMetabolique.getFait()).isEqualTo(UPDATED_FAIT);
        assertThat(testMetabolique.getLaboratoire()).isEqualTo(UPDATED_LABORATOIRE);
        assertThat(testMetabolique.getResultat()).isEqualTo(UPDATED_RESULTAT);
    }

    @Test
    @Transactional
    void patchNonExistingMetabolique() throws Exception {
        int databaseSizeBeforeUpdate = metaboliqueRepository.findAll().size();
        metabolique.setId(count.incrementAndGet());

        // Create the Metabolique
        MetaboliqueDTO metaboliqueDTO = metaboliqueMapper.toDto(metabolique);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMetaboliqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, metaboliqueDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(metaboliqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMetabolique() throws Exception {
        int databaseSizeBeforeUpdate = metaboliqueRepository.findAll().size();
        metabolique.setId(count.incrementAndGet());

        // Create the Metabolique
        MetaboliqueDTO metaboliqueDTO = metaboliqueMapper.toDto(metabolique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMetaboliqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(metaboliqueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMetabolique() throws Exception {
        int databaseSizeBeforeUpdate = metaboliqueRepository.findAll().size();
        metabolique.setId(count.incrementAndGet());

        // Create the Metabolique
        MetaboliqueDTO metaboliqueDTO = metaboliqueMapper.toDto(metabolique);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMetaboliqueMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(metaboliqueDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Metabolique in the database
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMetabolique() throws Exception {
        // Initialize the database
        metaboliqueRepository.saveAndFlush(metabolique);

        int databaseSizeBeforeDelete = metaboliqueRepository.findAll().size();

        // Delete the metabolique
        restMetaboliqueMockMvc
            .perform(delete(ENTITY_API_URL_ID, metabolique.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Metabolique> metaboliqueList = metaboliqueRepository.findAll();
        assertThat(metaboliqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
