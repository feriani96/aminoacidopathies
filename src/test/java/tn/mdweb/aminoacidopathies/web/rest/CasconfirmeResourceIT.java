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
import tn.mdweb.aminoacidopathies.domain.Casconfirme;
import tn.mdweb.aminoacidopathies.domain.Fiche;
import tn.mdweb.aminoacidopathies.domain.enumeration.elien_parente;
import tn.mdweb.aminoacidopathies.repository.CasconfirmeRepository;
import tn.mdweb.aminoacidopathies.service.dto.CasconfirmeDTO;
import tn.mdweb.aminoacidopathies.service.mapper.CasconfirmeMapper;

/**
 * Integration tests for the {@link CasconfirmeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CasconfirmeResourceIT {

    private static final String DEFAULT_CODE_REGISTRE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REGISTRE = "BBBBBBBBBB";

    private static final elien_parente DEFAULT_LIEN_PARENTE = elien_parente.FRERE_SOEUR;
    private static final elien_parente UPDATED_LIEN_PARENTE = elien_parente.PERE_MERE;

    private static final String ENTITY_API_URL = "/api/casconfirmes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CasconfirmeRepository casconfirmeRepository;

    @Autowired
    private CasconfirmeMapper casconfirmeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCasconfirmeMockMvc;

    private Casconfirme casconfirme;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Casconfirme createEntity(EntityManager em) {
        Casconfirme casconfirme = new Casconfirme().code_registre(DEFAULT_CODE_REGISTRE).lien_parente(DEFAULT_LIEN_PARENTE);
        // Add required entity
        Fiche fiche;
        if (TestUtil.findAll(em, Fiche.class).isEmpty()) {
            fiche = FicheResourceIT.createEntity(em);
            em.persist(fiche);
            em.flush();
        } else {
            fiche = TestUtil.findAll(em, Fiche.class).get(0);
        }
        casconfirme.setFiche(fiche);
        return casconfirme;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Casconfirme createUpdatedEntity(EntityManager em) {
        Casconfirme casconfirme = new Casconfirme().code_registre(UPDATED_CODE_REGISTRE).lien_parente(UPDATED_LIEN_PARENTE);
        // Add required entity
        Fiche fiche;
        if (TestUtil.findAll(em, Fiche.class).isEmpty()) {
            fiche = FicheResourceIT.createUpdatedEntity(em);
            em.persist(fiche);
            em.flush();
        } else {
            fiche = TestUtil.findAll(em, Fiche.class).get(0);
        }
        casconfirme.setFiche(fiche);
        return casconfirme;
    }

    @BeforeEach
    public void initTest() {
        casconfirme = createEntity(em);
    }

    @Test
    @Transactional
    void createCasconfirme() throws Exception {
        int databaseSizeBeforeCreate = casconfirmeRepository.findAll().size();
        // Create the Casconfirme
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(casconfirme);
        restCasconfirmeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeCreate + 1);
        Casconfirme testCasconfirme = casconfirmeList.get(casconfirmeList.size() - 1);
        assertThat(testCasconfirme.getCode_registre()).isEqualTo(DEFAULT_CODE_REGISTRE);
        assertThat(testCasconfirme.getLien_parente()).isEqualTo(DEFAULT_LIEN_PARENTE);
    }

    @Test
    @Transactional
    void createCasconfirmeWithExistingId() throws Exception {
        // Create the Casconfirme with an existing ID
        casconfirme.setId(1L);
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(casconfirme);

        int databaseSizeBeforeCreate = casconfirmeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCasconfirmeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCode_registreIsRequired() throws Exception {
        int databaseSizeBeforeTest = casconfirmeRepository.findAll().size();
        // set the field null
        casconfirme.setCode_registre(null);

        // Create the Casconfirme, which fails.
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(casconfirme);

        restCasconfirmeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO))
            )
            .andExpect(status().isBadRequest());

        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCasconfirmes() throws Exception {
        // Initialize the database
        casconfirmeRepository.saveAndFlush(casconfirme);

        // Get all the casconfirmeList
        restCasconfirmeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casconfirme.getId().intValue())))
            .andExpect(jsonPath("$.[*].code_registre").value(hasItem(DEFAULT_CODE_REGISTRE)))
            .andExpect(jsonPath("$.[*].lien_parente").value(hasItem(DEFAULT_LIEN_PARENTE.toString())));
    }

    @Test
    @Transactional
    void getCasconfirme() throws Exception {
        // Initialize the database
        casconfirmeRepository.saveAndFlush(casconfirme);

        // Get the casconfirme
        restCasconfirmeMockMvc
            .perform(get(ENTITY_API_URL_ID, casconfirme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(casconfirme.getId().intValue()))
            .andExpect(jsonPath("$.code_registre").value(DEFAULT_CODE_REGISTRE))
            .andExpect(jsonPath("$.lien_parente").value(DEFAULT_LIEN_PARENTE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCasconfirme() throws Exception {
        // Get the casconfirme
        restCasconfirmeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCasconfirme() throws Exception {
        // Initialize the database
        casconfirmeRepository.saveAndFlush(casconfirme);

        int databaseSizeBeforeUpdate = casconfirmeRepository.findAll().size();

        // Update the casconfirme
        Casconfirme updatedCasconfirme = casconfirmeRepository.findById(casconfirme.getId()).get();
        // Disconnect from session so that the updates on updatedCasconfirme are not directly saved in db
        em.detach(updatedCasconfirme);
        updatedCasconfirme.code_registre(UPDATED_CODE_REGISTRE).lien_parente(UPDATED_LIEN_PARENTE);
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(updatedCasconfirme);

        restCasconfirmeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, casconfirmeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeUpdate);
        Casconfirme testCasconfirme = casconfirmeList.get(casconfirmeList.size() - 1);
        assertThat(testCasconfirme.getCode_registre()).isEqualTo(UPDATED_CODE_REGISTRE);
        assertThat(testCasconfirme.getLien_parente()).isEqualTo(UPDATED_LIEN_PARENTE);
    }

    @Test
    @Transactional
    void putNonExistingCasconfirme() throws Exception {
        int databaseSizeBeforeUpdate = casconfirmeRepository.findAll().size();
        casconfirme.setId(count.incrementAndGet());

        // Create the Casconfirme
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(casconfirme);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCasconfirmeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, casconfirmeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCasconfirme() throws Exception {
        int databaseSizeBeforeUpdate = casconfirmeRepository.findAll().size();
        casconfirme.setId(count.incrementAndGet());

        // Create the Casconfirme
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(casconfirme);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCasconfirmeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCasconfirme() throws Exception {
        int databaseSizeBeforeUpdate = casconfirmeRepository.findAll().size();
        casconfirme.setId(count.incrementAndGet());

        // Create the Casconfirme
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(casconfirme);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCasconfirmeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCasconfirmeWithPatch() throws Exception {
        // Initialize the database
        casconfirmeRepository.saveAndFlush(casconfirme);

        int databaseSizeBeforeUpdate = casconfirmeRepository.findAll().size();

        // Update the casconfirme using partial update
        Casconfirme partialUpdatedCasconfirme = new Casconfirme();
        partialUpdatedCasconfirme.setId(casconfirme.getId());

        partialUpdatedCasconfirme.code_registre(UPDATED_CODE_REGISTRE).lien_parente(UPDATED_LIEN_PARENTE);

        restCasconfirmeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCasconfirme.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCasconfirme))
            )
            .andExpect(status().isOk());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeUpdate);
        Casconfirme testCasconfirme = casconfirmeList.get(casconfirmeList.size() - 1);
        assertThat(testCasconfirme.getCode_registre()).isEqualTo(UPDATED_CODE_REGISTRE);
        assertThat(testCasconfirme.getLien_parente()).isEqualTo(UPDATED_LIEN_PARENTE);
    }

    @Test
    @Transactional
    void fullUpdateCasconfirmeWithPatch() throws Exception {
        // Initialize the database
        casconfirmeRepository.saveAndFlush(casconfirme);

        int databaseSizeBeforeUpdate = casconfirmeRepository.findAll().size();

        // Update the casconfirme using partial update
        Casconfirme partialUpdatedCasconfirme = new Casconfirme();
        partialUpdatedCasconfirme.setId(casconfirme.getId());

        partialUpdatedCasconfirme.code_registre(UPDATED_CODE_REGISTRE).lien_parente(UPDATED_LIEN_PARENTE);

        restCasconfirmeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCasconfirme.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCasconfirme))
            )
            .andExpect(status().isOk());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeUpdate);
        Casconfirme testCasconfirme = casconfirmeList.get(casconfirmeList.size() - 1);
        assertThat(testCasconfirme.getCode_registre()).isEqualTo(UPDATED_CODE_REGISTRE);
        assertThat(testCasconfirme.getLien_parente()).isEqualTo(UPDATED_LIEN_PARENTE);
    }

    @Test
    @Transactional
    void patchNonExistingCasconfirme() throws Exception {
        int databaseSizeBeforeUpdate = casconfirmeRepository.findAll().size();
        casconfirme.setId(count.incrementAndGet());

        // Create the Casconfirme
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(casconfirme);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCasconfirmeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, casconfirmeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCasconfirme() throws Exception {
        int databaseSizeBeforeUpdate = casconfirmeRepository.findAll().size();
        casconfirme.setId(count.incrementAndGet());

        // Create the Casconfirme
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(casconfirme);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCasconfirmeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCasconfirme() throws Exception {
        int databaseSizeBeforeUpdate = casconfirmeRepository.findAll().size();
        casconfirme.setId(count.incrementAndGet());

        // Create the Casconfirme
        CasconfirmeDTO casconfirmeDTO = casconfirmeMapper.toDto(casconfirme);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCasconfirmeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(casconfirmeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Casconfirme in the database
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCasconfirme() throws Exception {
        // Initialize the database
        casconfirmeRepository.saveAndFlush(casconfirme);

        int databaseSizeBeforeDelete = casconfirmeRepository.findAll().size();

        // Delete the casconfirme
        restCasconfirmeMockMvc
            .perform(delete(ENTITY_API_URL_ID, casconfirme.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Casconfirme> casconfirmeList = casconfirmeRepository.findAll();
        assertThat(casconfirmeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
