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
import tn.mdweb.aminoacidopathies.domain.Casdecesbasage;
import tn.mdweb.aminoacidopathies.domain.enumeration.elienparente;
import tn.mdweb.aminoacidopathies.domain.enumeration.elieudeces;
import tn.mdweb.aminoacidopathies.repository.CasdecesbasageRepository;
import tn.mdweb.aminoacidopathies.service.dto.CasdecesbasageDTO;
import tn.mdweb.aminoacidopathies.service.mapper.CasdecesbasageMapper;

/**
 * Integration tests for the {@link CasdecesbasageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CasdecesbasageResourceIT {

    private static final Boolean DEFAULT_CONFIRME = false;
    private static final Boolean UPDATED_CONFIRME = true;

    private static final String DEFAULT_CODE_REGISTRE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_REGISTRE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SUSPECTE = false;
    private static final Boolean UPDATED_SUSPECTE = true;

    private static final elienparente DEFAULT_LIEN_DE_PARENTE = elienparente.FRERE_SOEUR;
    private static final elienparente UPDATED_LIEN_DE_PARENTE = elienparente.PERE_MERE;

    private static final String DEFAULT_AUTRE_LIEN_PARENTE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_LIEN_PARENTE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AN_AGE_DE_DECES = 1;
    private static final Integer UPDATED_AN_AGE_DE_DECES = 2;

    private static final Integer DEFAULT_MOIS_AGE_DE_DECES = 1;
    private static final Integer UPDATED_MOIS_AGE_DE_DECES = 2;

    private static final Integer DEFAULT_JOURS_AGE_DE_DECES = 1;
    private static final Integer UPDATED_JOURS_AGE_DE_DECES = 2;

    private static final Boolean DEFAULT_TBL_NEURO = false;
    private static final Boolean UPDATED_TBL_NEURO = true;

    private static final Boolean DEFAULT_TBL_HEMORRAGIQUE = false;
    private static final Boolean UPDATED_TBL_HEMORRAGIQUE = true;

    private static final Boolean DEFAULT_TBL_INFX = false;
    private static final Boolean UPDATED_TBL_INFX = true;

    private static final String DEFAULT_AUTRE_CIRCONSTANCES_DECES = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_CIRCONSTANCES_DECES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BAUTRE_CIRCONSTANCE_DECES = false;
    private static final Boolean UPDATED_BAUTRE_CIRCONSTANCE_DECES = true;

    private static final Boolean DEFAULT_NP_CIRCONSTANCES_DECES = false;
    private static final Boolean UPDATED_NP_CIRCONSTANCES_DECES = true;

    private static final elieudeces DEFAULT_LIEU_DECES = elieudeces.DOMICILE;
    private static final elieudeces UPDATED_LIEU_DECES = elieudeces.STRUCTURE_SANTE_PUBLIQUE;

    private static final String ENTITY_API_URL = "/api/casdecesbasages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CasdecesbasageRepository casdecesbasageRepository;

    @Autowired
    private CasdecesbasageMapper casdecesbasageMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCasdecesbasageMockMvc;

    private Casdecesbasage casdecesbasage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Casdecesbasage createEntity(EntityManager em) {
        Casdecesbasage casdecesbasage = new Casdecesbasage()
            .confirme(DEFAULT_CONFIRME)
            .code_registre(DEFAULT_CODE_REGISTRE)
            .suspecte(DEFAULT_SUSPECTE)
            .lien_de_parente(DEFAULT_LIEN_DE_PARENTE)
            .autre_lien_parente(DEFAULT_AUTRE_LIEN_PARENTE)
            .an_age_de_deces(DEFAULT_AN_AGE_DE_DECES)
            .mois_age_de_deces(DEFAULT_MOIS_AGE_DE_DECES)
            .jours_age_de_deces(DEFAULT_JOURS_AGE_DE_DECES)
            .tbl_neuro(DEFAULT_TBL_NEURO)
            .tbl_hemorragique(DEFAULT_TBL_HEMORRAGIQUE)
            .tbl_infx(DEFAULT_TBL_INFX)
            .autre_circonstances_deces(DEFAULT_AUTRE_CIRCONSTANCES_DECES)
            .bautre_circonstance_deces(DEFAULT_BAUTRE_CIRCONSTANCE_DECES)
            .np_circonstances_deces(DEFAULT_NP_CIRCONSTANCES_DECES)
            .lieu_deces(DEFAULT_LIEU_DECES);
        return casdecesbasage;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Casdecesbasage createUpdatedEntity(EntityManager em) {
        Casdecesbasage casdecesbasage = new Casdecesbasage()
            .confirme(UPDATED_CONFIRME)
            .code_registre(UPDATED_CODE_REGISTRE)
            .suspecte(UPDATED_SUSPECTE)
            .lien_de_parente(UPDATED_LIEN_DE_PARENTE)
            .autre_lien_parente(UPDATED_AUTRE_LIEN_PARENTE)
            .an_age_de_deces(UPDATED_AN_AGE_DE_DECES)
            .mois_age_de_deces(UPDATED_MOIS_AGE_DE_DECES)
            .jours_age_de_deces(UPDATED_JOURS_AGE_DE_DECES)
            .tbl_neuro(UPDATED_TBL_NEURO)
            .tbl_hemorragique(UPDATED_TBL_HEMORRAGIQUE)
            .tbl_infx(UPDATED_TBL_INFX)
            .autre_circonstances_deces(UPDATED_AUTRE_CIRCONSTANCES_DECES)
            .bautre_circonstance_deces(UPDATED_BAUTRE_CIRCONSTANCE_DECES)
            .np_circonstances_deces(UPDATED_NP_CIRCONSTANCES_DECES)
            .lieu_deces(UPDATED_LIEU_DECES);
        return casdecesbasage;
    }

    @BeforeEach
    public void initTest() {
        casdecesbasage = createEntity(em);
    }

    @Test
    @Transactional
    void createCasdecesbasage() throws Exception {
        int databaseSizeBeforeCreate = casdecesbasageRepository.findAll().size();
        // Create the Casdecesbasage
        CasdecesbasageDTO casdecesbasageDTO = casdecesbasageMapper.toDto(casdecesbasage);
        restCasdecesbasageMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(casdecesbasageDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeCreate + 1);
        Casdecesbasage testCasdecesbasage = casdecesbasageList.get(casdecesbasageList.size() - 1);
        assertThat(testCasdecesbasage.getConfirme()).isEqualTo(DEFAULT_CONFIRME);
        assertThat(testCasdecesbasage.getCode_registre()).isEqualTo(DEFAULT_CODE_REGISTRE);
        assertThat(testCasdecesbasage.getSuspecte()).isEqualTo(DEFAULT_SUSPECTE);
        assertThat(testCasdecesbasage.getLien_de_parente()).isEqualTo(DEFAULT_LIEN_DE_PARENTE);
        assertThat(testCasdecesbasage.getAutre_lien_parente()).isEqualTo(DEFAULT_AUTRE_LIEN_PARENTE);
        assertThat(testCasdecesbasage.getAn_age_de_deces()).isEqualTo(DEFAULT_AN_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getMois_age_de_deces()).isEqualTo(DEFAULT_MOIS_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getJours_age_de_deces()).isEqualTo(DEFAULT_JOURS_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getTbl_neuro()).isEqualTo(DEFAULT_TBL_NEURO);
        assertThat(testCasdecesbasage.getTbl_hemorragique()).isEqualTo(DEFAULT_TBL_HEMORRAGIQUE);
        assertThat(testCasdecesbasage.getTbl_infx()).isEqualTo(DEFAULT_TBL_INFX);
        assertThat(testCasdecesbasage.getAutre_circonstances_deces()).isEqualTo(DEFAULT_AUTRE_CIRCONSTANCES_DECES);
        assertThat(testCasdecesbasage.getBautre_circonstance_deces()).isEqualTo(DEFAULT_BAUTRE_CIRCONSTANCE_DECES);
        assertThat(testCasdecesbasage.getNp_circonstances_deces()).isEqualTo(DEFAULT_NP_CIRCONSTANCES_DECES);
        assertThat(testCasdecesbasage.getLieu_deces()).isEqualTo(DEFAULT_LIEU_DECES);
    }

    @Test
    @Transactional
    void createCasdecesbasageWithExistingId() throws Exception {
        // Create the Casdecesbasage with an existing ID
        casdecesbasage.setId(1L);
        CasdecesbasageDTO casdecesbasageDTO = casdecesbasageMapper.toDto(casdecesbasage);

        int databaseSizeBeforeCreate = casdecesbasageRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCasdecesbasageMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(casdecesbasageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCasdecesbasages() throws Exception {
        // Initialize the database
        casdecesbasageRepository.saveAndFlush(casdecesbasage);

        // Get all the casdecesbasageList
        restCasdecesbasageMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casdecesbasage.getId().intValue())))
            .andExpect(jsonPath("$.[*].confirme").value(hasItem(DEFAULT_CONFIRME.booleanValue())))
            .andExpect(jsonPath("$.[*].code_registre").value(hasItem(DEFAULT_CODE_REGISTRE)))
            .andExpect(jsonPath("$.[*].suspecte").value(hasItem(DEFAULT_SUSPECTE.booleanValue())))
            .andExpect(jsonPath("$.[*].lien_de_parente").value(hasItem(DEFAULT_LIEN_DE_PARENTE.toString())))
            .andExpect(jsonPath("$.[*].autre_lien_parente").value(hasItem(DEFAULT_AUTRE_LIEN_PARENTE)))
            .andExpect(jsonPath("$.[*].an_age_de_deces").value(hasItem(DEFAULT_AN_AGE_DE_DECES)))
            .andExpect(jsonPath("$.[*].mois_age_de_deces").value(hasItem(DEFAULT_MOIS_AGE_DE_DECES)))
            .andExpect(jsonPath("$.[*].jours_age_de_deces").value(hasItem(DEFAULT_JOURS_AGE_DE_DECES)))
            .andExpect(jsonPath("$.[*].tbl_neuro").value(hasItem(DEFAULT_TBL_NEURO.booleanValue())))
            .andExpect(jsonPath("$.[*].tbl_hemorragique").value(hasItem(DEFAULT_TBL_HEMORRAGIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].tbl_infx").value(hasItem(DEFAULT_TBL_INFX.booleanValue())))
            .andExpect(jsonPath("$.[*].autre_circonstances_deces").value(hasItem(DEFAULT_AUTRE_CIRCONSTANCES_DECES)))
            .andExpect(jsonPath("$.[*].bautre_circonstance_deces").value(hasItem(DEFAULT_BAUTRE_CIRCONSTANCE_DECES.booleanValue())))
            .andExpect(jsonPath("$.[*].np_circonstances_deces").value(hasItem(DEFAULT_NP_CIRCONSTANCES_DECES.booleanValue())))
            .andExpect(jsonPath("$.[*].lieu_deces").value(hasItem(DEFAULT_LIEU_DECES.toString())));
    }

    @Test
    @Transactional
    void getCasdecesbasage() throws Exception {
        // Initialize the database
        casdecesbasageRepository.saveAndFlush(casdecesbasage);

        // Get the casdecesbasage
        restCasdecesbasageMockMvc
            .perform(get(ENTITY_API_URL_ID, casdecesbasage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(casdecesbasage.getId().intValue()))
            .andExpect(jsonPath("$.confirme").value(DEFAULT_CONFIRME.booleanValue()))
            .andExpect(jsonPath("$.code_registre").value(DEFAULT_CODE_REGISTRE))
            .andExpect(jsonPath("$.suspecte").value(DEFAULT_SUSPECTE.booleanValue()))
            .andExpect(jsonPath("$.lien_de_parente").value(DEFAULT_LIEN_DE_PARENTE.toString()))
            .andExpect(jsonPath("$.autre_lien_parente").value(DEFAULT_AUTRE_LIEN_PARENTE))
            .andExpect(jsonPath("$.an_age_de_deces").value(DEFAULT_AN_AGE_DE_DECES))
            .andExpect(jsonPath("$.mois_age_de_deces").value(DEFAULT_MOIS_AGE_DE_DECES))
            .andExpect(jsonPath("$.jours_age_de_deces").value(DEFAULT_JOURS_AGE_DE_DECES))
            .andExpect(jsonPath("$.tbl_neuro").value(DEFAULT_TBL_NEURO.booleanValue()))
            .andExpect(jsonPath("$.tbl_hemorragique").value(DEFAULT_TBL_HEMORRAGIQUE.booleanValue()))
            .andExpect(jsonPath("$.tbl_infx").value(DEFAULT_TBL_INFX.booleanValue()))
            .andExpect(jsonPath("$.autre_circonstances_deces").value(DEFAULT_AUTRE_CIRCONSTANCES_DECES))
            .andExpect(jsonPath("$.bautre_circonstance_deces").value(DEFAULT_BAUTRE_CIRCONSTANCE_DECES.booleanValue()))
            .andExpect(jsonPath("$.np_circonstances_deces").value(DEFAULT_NP_CIRCONSTANCES_DECES.booleanValue()))
            .andExpect(jsonPath("$.lieu_deces").value(DEFAULT_LIEU_DECES.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCasdecesbasage() throws Exception {
        // Get the casdecesbasage
        restCasdecesbasageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCasdecesbasage() throws Exception {
        // Initialize the database
        casdecesbasageRepository.saveAndFlush(casdecesbasage);

        int databaseSizeBeforeUpdate = casdecesbasageRepository.findAll().size();

        // Update the casdecesbasage
        Casdecesbasage updatedCasdecesbasage = casdecesbasageRepository.findById(casdecesbasage.getId()).get();
        // Disconnect from session so that the updates on updatedCasdecesbasage are not directly saved in db
        em.detach(updatedCasdecesbasage);
        updatedCasdecesbasage
            .confirme(UPDATED_CONFIRME)
            .code_registre(UPDATED_CODE_REGISTRE)
            .suspecte(UPDATED_SUSPECTE)
            .lien_de_parente(UPDATED_LIEN_DE_PARENTE)
            .autre_lien_parente(UPDATED_AUTRE_LIEN_PARENTE)
            .an_age_de_deces(UPDATED_AN_AGE_DE_DECES)
            .mois_age_de_deces(UPDATED_MOIS_AGE_DE_DECES)
            .jours_age_de_deces(UPDATED_JOURS_AGE_DE_DECES)
            .tbl_neuro(UPDATED_TBL_NEURO)
            .tbl_hemorragique(UPDATED_TBL_HEMORRAGIQUE)
            .tbl_infx(UPDATED_TBL_INFX)
            .autre_circonstances_deces(UPDATED_AUTRE_CIRCONSTANCES_DECES)
            .bautre_circonstance_deces(UPDATED_BAUTRE_CIRCONSTANCE_DECES)
            .np_circonstances_deces(UPDATED_NP_CIRCONSTANCES_DECES)
            .lieu_deces(UPDATED_LIEU_DECES);
        CasdecesbasageDTO casdecesbasageDTO = casdecesbasageMapper.toDto(updatedCasdecesbasage);

        restCasdecesbasageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, casdecesbasageDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(casdecesbasageDTO))
            )
            .andExpect(status().isOk());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeUpdate);
        Casdecesbasage testCasdecesbasage = casdecesbasageList.get(casdecesbasageList.size() - 1);
        assertThat(testCasdecesbasage.getConfirme()).isEqualTo(UPDATED_CONFIRME);
        assertThat(testCasdecesbasage.getCode_registre()).isEqualTo(UPDATED_CODE_REGISTRE);
        assertThat(testCasdecesbasage.getSuspecte()).isEqualTo(UPDATED_SUSPECTE);
        assertThat(testCasdecesbasage.getLien_de_parente()).isEqualTo(UPDATED_LIEN_DE_PARENTE);
        assertThat(testCasdecesbasage.getAutre_lien_parente()).isEqualTo(UPDATED_AUTRE_LIEN_PARENTE);
        assertThat(testCasdecesbasage.getAn_age_de_deces()).isEqualTo(UPDATED_AN_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getMois_age_de_deces()).isEqualTo(UPDATED_MOIS_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getJours_age_de_deces()).isEqualTo(UPDATED_JOURS_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getTbl_neuro()).isEqualTo(UPDATED_TBL_NEURO);
        assertThat(testCasdecesbasage.getTbl_hemorragique()).isEqualTo(UPDATED_TBL_HEMORRAGIQUE);
        assertThat(testCasdecesbasage.getTbl_infx()).isEqualTo(UPDATED_TBL_INFX);
        assertThat(testCasdecesbasage.getAutre_circonstances_deces()).isEqualTo(UPDATED_AUTRE_CIRCONSTANCES_DECES);
        assertThat(testCasdecesbasage.getBautre_circonstance_deces()).isEqualTo(UPDATED_BAUTRE_CIRCONSTANCE_DECES);
        assertThat(testCasdecesbasage.getNp_circonstances_deces()).isEqualTo(UPDATED_NP_CIRCONSTANCES_DECES);
        assertThat(testCasdecesbasage.getLieu_deces()).isEqualTo(UPDATED_LIEU_DECES);
    }

    @Test
    @Transactional
    void putNonExistingCasdecesbasage() throws Exception {
        int databaseSizeBeforeUpdate = casdecesbasageRepository.findAll().size();
        casdecesbasage.setId(count.incrementAndGet());

        // Create the Casdecesbasage
        CasdecesbasageDTO casdecesbasageDTO = casdecesbasageMapper.toDto(casdecesbasage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCasdecesbasageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, casdecesbasageDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(casdecesbasageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCasdecesbasage() throws Exception {
        int databaseSizeBeforeUpdate = casdecesbasageRepository.findAll().size();
        casdecesbasage.setId(count.incrementAndGet());

        // Create the Casdecesbasage
        CasdecesbasageDTO casdecesbasageDTO = casdecesbasageMapper.toDto(casdecesbasage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCasdecesbasageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(casdecesbasageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCasdecesbasage() throws Exception {
        int databaseSizeBeforeUpdate = casdecesbasageRepository.findAll().size();
        casdecesbasage.setId(count.incrementAndGet());

        // Create the Casdecesbasage
        CasdecesbasageDTO casdecesbasageDTO = casdecesbasageMapper.toDto(casdecesbasage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCasdecesbasageMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(casdecesbasageDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCasdecesbasageWithPatch() throws Exception {
        // Initialize the database
        casdecesbasageRepository.saveAndFlush(casdecesbasage);

        int databaseSizeBeforeUpdate = casdecesbasageRepository.findAll().size();

        // Update the casdecesbasage using partial update
        Casdecesbasage partialUpdatedCasdecesbasage = new Casdecesbasage();
        partialUpdatedCasdecesbasage.setId(casdecesbasage.getId());

        partialUpdatedCasdecesbasage
            .confirme(UPDATED_CONFIRME)
            .lien_de_parente(UPDATED_LIEN_DE_PARENTE)
            .an_age_de_deces(UPDATED_AN_AGE_DE_DECES)
            .mois_age_de_deces(UPDATED_MOIS_AGE_DE_DECES)
            .jours_age_de_deces(UPDATED_JOURS_AGE_DE_DECES)
            .bautre_circonstance_deces(UPDATED_BAUTRE_CIRCONSTANCE_DECES)
            .np_circonstances_deces(UPDATED_NP_CIRCONSTANCES_DECES)
            .lieu_deces(UPDATED_LIEU_DECES);

        restCasdecesbasageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCasdecesbasage.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCasdecesbasage))
            )
            .andExpect(status().isOk());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeUpdate);
        Casdecesbasage testCasdecesbasage = casdecesbasageList.get(casdecesbasageList.size() - 1);
        assertThat(testCasdecesbasage.getConfirme()).isEqualTo(UPDATED_CONFIRME);
        assertThat(testCasdecesbasage.getCode_registre()).isEqualTo(DEFAULT_CODE_REGISTRE);
        assertThat(testCasdecesbasage.getSuspecte()).isEqualTo(DEFAULT_SUSPECTE);
        assertThat(testCasdecesbasage.getLien_de_parente()).isEqualTo(UPDATED_LIEN_DE_PARENTE);
        assertThat(testCasdecesbasage.getAutre_lien_parente()).isEqualTo(DEFAULT_AUTRE_LIEN_PARENTE);
        assertThat(testCasdecesbasage.getAn_age_de_deces()).isEqualTo(UPDATED_AN_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getMois_age_de_deces()).isEqualTo(UPDATED_MOIS_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getJours_age_de_deces()).isEqualTo(UPDATED_JOURS_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getTbl_neuro()).isEqualTo(DEFAULT_TBL_NEURO);
        assertThat(testCasdecesbasage.getTbl_hemorragique()).isEqualTo(DEFAULT_TBL_HEMORRAGIQUE);
        assertThat(testCasdecesbasage.getTbl_infx()).isEqualTo(DEFAULT_TBL_INFX);
        assertThat(testCasdecesbasage.getAutre_circonstances_deces()).isEqualTo(DEFAULT_AUTRE_CIRCONSTANCES_DECES);
        assertThat(testCasdecesbasage.getBautre_circonstance_deces()).isEqualTo(UPDATED_BAUTRE_CIRCONSTANCE_DECES);
        assertThat(testCasdecesbasage.getNp_circonstances_deces()).isEqualTo(UPDATED_NP_CIRCONSTANCES_DECES);
        assertThat(testCasdecesbasage.getLieu_deces()).isEqualTo(UPDATED_LIEU_DECES);
    }

    @Test
    @Transactional
    void fullUpdateCasdecesbasageWithPatch() throws Exception {
        // Initialize the database
        casdecesbasageRepository.saveAndFlush(casdecesbasage);

        int databaseSizeBeforeUpdate = casdecesbasageRepository.findAll().size();

        // Update the casdecesbasage using partial update
        Casdecesbasage partialUpdatedCasdecesbasage = new Casdecesbasage();
        partialUpdatedCasdecesbasage.setId(casdecesbasage.getId());

        partialUpdatedCasdecesbasage
            .confirme(UPDATED_CONFIRME)
            .code_registre(UPDATED_CODE_REGISTRE)
            .suspecte(UPDATED_SUSPECTE)
            .lien_de_parente(UPDATED_LIEN_DE_PARENTE)
            .autre_lien_parente(UPDATED_AUTRE_LIEN_PARENTE)
            .an_age_de_deces(UPDATED_AN_AGE_DE_DECES)
            .mois_age_de_deces(UPDATED_MOIS_AGE_DE_DECES)
            .jours_age_de_deces(UPDATED_JOURS_AGE_DE_DECES)
            .tbl_neuro(UPDATED_TBL_NEURO)
            .tbl_hemorragique(UPDATED_TBL_HEMORRAGIQUE)
            .tbl_infx(UPDATED_TBL_INFX)
            .autre_circonstances_deces(UPDATED_AUTRE_CIRCONSTANCES_DECES)
            .bautre_circonstance_deces(UPDATED_BAUTRE_CIRCONSTANCE_DECES)
            .np_circonstances_deces(UPDATED_NP_CIRCONSTANCES_DECES)
            .lieu_deces(UPDATED_LIEU_DECES);

        restCasdecesbasageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCasdecesbasage.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCasdecesbasage))
            )
            .andExpect(status().isOk());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeUpdate);
        Casdecesbasage testCasdecesbasage = casdecesbasageList.get(casdecesbasageList.size() - 1);
        assertThat(testCasdecesbasage.getConfirme()).isEqualTo(UPDATED_CONFIRME);
        assertThat(testCasdecesbasage.getCode_registre()).isEqualTo(UPDATED_CODE_REGISTRE);
        assertThat(testCasdecesbasage.getSuspecte()).isEqualTo(UPDATED_SUSPECTE);
        assertThat(testCasdecesbasage.getLien_de_parente()).isEqualTo(UPDATED_LIEN_DE_PARENTE);
        assertThat(testCasdecesbasage.getAutre_lien_parente()).isEqualTo(UPDATED_AUTRE_LIEN_PARENTE);
        assertThat(testCasdecesbasage.getAn_age_de_deces()).isEqualTo(UPDATED_AN_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getMois_age_de_deces()).isEqualTo(UPDATED_MOIS_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getJours_age_de_deces()).isEqualTo(UPDATED_JOURS_AGE_DE_DECES);
        assertThat(testCasdecesbasage.getTbl_neuro()).isEqualTo(UPDATED_TBL_NEURO);
        assertThat(testCasdecesbasage.getTbl_hemorragique()).isEqualTo(UPDATED_TBL_HEMORRAGIQUE);
        assertThat(testCasdecesbasage.getTbl_infx()).isEqualTo(UPDATED_TBL_INFX);
        assertThat(testCasdecesbasage.getAutre_circonstances_deces()).isEqualTo(UPDATED_AUTRE_CIRCONSTANCES_DECES);
        assertThat(testCasdecesbasage.getBautre_circonstance_deces()).isEqualTo(UPDATED_BAUTRE_CIRCONSTANCE_DECES);
        assertThat(testCasdecesbasage.getNp_circonstances_deces()).isEqualTo(UPDATED_NP_CIRCONSTANCES_DECES);
        assertThat(testCasdecesbasage.getLieu_deces()).isEqualTo(UPDATED_LIEU_DECES);
    }

    @Test
    @Transactional
    void patchNonExistingCasdecesbasage() throws Exception {
        int databaseSizeBeforeUpdate = casdecesbasageRepository.findAll().size();
        casdecesbasage.setId(count.incrementAndGet());

        // Create the Casdecesbasage
        CasdecesbasageDTO casdecesbasageDTO = casdecesbasageMapper.toDto(casdecesbasage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCasdecesbasageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, casdecesbasageDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(casdecesbasageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCasdecesbasage() throws Exception {
        int databaseSizeBeforeUpdate = casdecesbasageRepository.findAll().size();
        casdecesbasage.setId(count.incrementAndGet());

        // Create the Casdecesbasage
        CasdecesbasageDTO casdecesbasageDTO = casdecesbasageMapper.toDto(casdecesbasage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCasdecesbasageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(casdecesbasageDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCasdecesbasage() throws Exception {
        int databaseSizeBeforeUpdate = casdecesbasageRepository.findAll().size();
        casdecesbasage.setId(count.incrementAndGet());

        // Create the Casdecesbasage
        CasdecesbasageDTO casdecesbasageDTO = casdecesbasageMapper.toDto(casdecesbasage);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCasdecesbasageMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(casdecesbasageDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Casdecesbasage in the database
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCasdecesbasage() throws Exception {
        // Initialize the database
        casdecesbasageRepository.saveAndFlush(casdecesbasage);

        int databaseSizeBeforeDelete = casdecesbasageRepository.findAll().size();

        // Delete the casdecesbasage
        restCasdecesbasageMockMvc
            .perform(delete(ENTITY_API_URL_ID, casdecesbasage.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Casdecesbasage> casdecesbasageList = casdecesbasageRepository.findAll();
        assertThat(casdecesbasageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
