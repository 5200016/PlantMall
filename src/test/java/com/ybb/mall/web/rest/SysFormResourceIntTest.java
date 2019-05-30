package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysForm;
import com.ybb.mall.repository.SysFormRepository;
import com.ybb.mall.service.SysFormService;
import com.ybb.mall.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.ybb.mall.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SysFormResource REST controller.
 *
 * @see SysFormResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysFormResourceIntTest {

    private static final String DEFAULT_FORM_ID = "AAAAAAAAAA";
    private static final String UPDATED_FORM_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_VALIDITY = 1;
    private static final Integer UPDATED_VALIDITY = 2;

    @Autowired
    private SysFormRepository sysFormRepository;

    @Autowired
    private SysFormService sysFormService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSysFormMockMvc;

    private SysForm sysForm;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysFormResource sysFormResource = new SysFormResource(sysFormService);
        this.restSysFormMockMvc = MockMvcBuilders.standaloneSetup(sysFormResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysForm createEntity(EntityManager em) {
        SysForm sysForm = new SysForm()
            .formId(DEFAULT_FORM_ID)
            .validity(DEFAULT_VALIDITY);
        return sysForm;
    }

    @Before
    public void initTest() {
        sysForm = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysForm() throws Exception {
        int databaseSizeBeforeCreate = sysFormRepository.findAll().size();

        // Create the SysForm
        restSysFormMockMvc.perform(post("/api/sys-forms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysForm)))
            .andExpect(status().isCreated());

        // Validate the SysForm in the database
        List<SysForm> sysFormList = sysFormRepository.findAll();
        assertThat(sysFormList).hasSize(databaseSizeBeforeCreate + 1);
        SysForm testSysForm = sysFormList.get(sysFormList.size() - 1);
        assertThat(testSysForm.getFormId()).isEqualTo(DEFAULT_FORM_ID);
        assertThat(testSysForm.getValidity()).isEqualTo(DEFAULT_VALIDITY);
    }

    @Test
    @Transactional
    public void createSysFormWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysFormRepository.findAll().size();

        // Create the SysForm with an existing ID
        sysForm.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysFormMockMvc.perform(post("/api/sys-forms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysForm)))
            .andExpect(status().isBadRequest());

        // Validate the SysForm in the database
        List<SysForm> sysFormList = sysFormRepository.findAll();
        assertThat(sysFormList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysForms() throws Exception {
        // Initialize the database
        sysFormRepository.saveAndFlush(sysForm);

        // Get all the sysFormList
        restSysFormMockMvc.perform(get("/api/sys-forms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysForm.getId().intValue())))
            .andExpect(jsonPath("$.[*].formId").value(hasItem(DEFAULT_FORM_ID.toString())))
            .andExpect(jsonPath("$.[*].validity").value(hasItem(DEFAULT_VALIDITY)));
    }
    
    @Test
    @Transactional
    public void getSysForm() throws Exception {
        // Initialize the database
        sysFormRepository.saveAndFlush(sysForm);

        // Get the sysForm
        restSysFormMockMvc.perform(get("/api/sys-forms/{id}", sysForm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysForm.getId().intValue()))
            .andExpect(jsonPath("$.formId").value(DEFAULT_FORM_ID.toString()))
            .andExpect(jsonPath("$.validity").value(DEFAULT_VALIDITY));
    }

    @Test
    @Transactional
    public void getNonExistingSysForm() throws Exception {
        // Get the sysForm
        restSysFormMockMvc.perform(get("/api/sys-forms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysForm() throws Exception {
        // Initialize the database
        sysFormService.save(sysForm);

        int databaseSizeBeforeUpdate = sysFormRepository.findAll().size();

        // Update the sysForm
        SysForm updatedSysForm = sysFormRepository.findById(sysForm.getId()).get();
        // Disconnect from session so that the updates on updatedSysForm are not directly saved in db
        em.detach(updatedSysForm);
        updatedSysForm
            .formId(UPDATED_FORM_ID)
            .validity(UPDATED_VALIDITY);

        restSysFormMockMvc.perform(put("/api/sys-forms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysForm)))
            .andExpect(status().isOk());

        // Validate the SysForm in the database
        List<SysForm> sysFormList = sysFormRepository.findAll();
        assertThat(sysFormList).hasSize(databaseSizeBeforeUpdate);
        SysForm testSysForm = sysFormList.get(sysFormList.size() - 1);
        assertThat(testSysForm.getFormId()).isEqualTo(UPDATED_FORM_ID);
        assertThat(testSysForm.getValidity()).isEqualTo(UPDATED_VALIDITY);
    }

    @Test
    @Transactional
    public void updateNonExistingSysForm() throws Exception {
        int databaseSizeBeforeUpdate = sysFormRepository.findAll().size();

        // Create the SysForm

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysFormMockMvc.perform(put("/api/sys-forms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysForm)))
            .andExpect(status().isBadRequest());

        // Validate the SysForm in the database
        List<SysForm> sysFormList = sysFormRepository.findAll();
        assertThat(sysFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysForm() throws Exception {
        // Initialize the database
        sysFormService.save(sysForm);

        int databaseSizeBeforeDelete = sysFormRepository.findAll().size();

        // Get the sysForm
        restSysFormMockMvc.perform(delete("/api/sys-forms/{id}", sysForm.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysForm> sysFormList = sysFormRepository.findAll();
        assertThat(sysFormList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysForm.class);
        SysForm sysForm1 = new SysForm();
        sysForm1.setId(1L);
        SysForm sysForm2 = new SysForm();
        sysForm2.setId(sysForm1.getId());
        assertThat(sysForm1).isEqualTo(sysForm2);
        sysForm2.setId(2L);
        assertThat(sysForm1).isNotEqualTo(sysForm2);
        sysForm1.setId(null);
        assertThat(sysForm1).isNotEqualTo(sysForm2);
    }
}
