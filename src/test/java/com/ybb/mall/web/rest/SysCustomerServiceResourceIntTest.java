package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysCustomerService;
import com.ybb.mall.repository.SysCustomerServiceRepository;
import com.ybb.mall.service.SysCustomerServiceService;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.ybb.mall.web.rest.TestUtil.sameInstant;
import static com.ybb.mall.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SysCustomerServiceResource REST controller.
 *
 * @see SysCustomerServiceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysCustomerServiceResourceIntTest {

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysCustomerServiceRepository sysCustomerServiceRepository;

    @Autowired
    private SysCustomerServiceService sysCustomerServiceService;

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

    private MockMvc restSysCustomerServiceMockMvc;

    private SysCustomerService sysCustomerService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysCustomerServiceResource sysCustomerServiceResource = new SysCustomerServiceResource(sysCustomerServiceService);
        this.restSysCustomerServiceMockMvc = MockMvcBuilders.standaloneSetup(sysCustomerServiceResource)
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
    public static SysCustomerService createEntity(EntityManager em) {
        SysCustomerService sysCustomerService = new SysCustomerService()
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysCustomerService;
    }

    @Before
    public void initTest() {
        sysCustomerService = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysCustomerService() throws Exception {
        int databaseSizeBeforeCreate = sysCustomerServiceRepository.findAll().size();

        // Create the SysCustomerService
        restSysCustomerServiceMockMvc.perform(post("/api/sys-customer-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCustomerService)))
            .andExpect(status().isCreated());

        // Validate the SysCustomerService in the database
        List<SysCustomerService> sysCustomerServiceList = sysCustomerServiceRepository.findAll();
        assertThat(sysCustomerServiceList).hasSize(databaseSizeBeforeCreate + 1);
        SysCustomerService testSysCustomerService = sysCustomerServiceList.get(sysCustomerServiceList.size() - 1);
        assertThat(testSysCustomerService.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSysCustomerService.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSysCustomerService.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysCustomerService.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysCustomerServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysCustomerServiceRepository.findAll().size();

        // Create the SysCustomerService with an existing ID
        sysCustomerService.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCustomerServiceMockMvc.perform(post("/api/sys-customer-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCustomerService)))
            .andExpect(status().isBadRequest());

        // Validate the SysCustomerService in the database
        List<SysCustomerService> sysCustomerServiceList = sysCustomerServiceRepository.findAll();
        assertThat(sysCustomerServiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysCustomerServices() throws Exception {
        // Initialize the database
        sysCustomerServiceRepository.saveAndFlush(sysCustomerService);

        // Get all the sysCustomerServiceList
        restSysCustomerServiceMockMvc.perform(get("/api/sys-customer-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCustomerService.getId().intValue())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysCustomerService() throws Exception {
        // Initialize the database
        sysCustomerServiceRepository.saveAndFlush(sysCustomerService);

        // Get the sysCustomerService
        restSysCustomerServiceMockMvc.perform(get("/api/sys-customer-services/{id}", sysCustomerService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysCustomerService.getId().intValue()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysCustomerService() throws Exception {
        // Get the sysCustomerService
        restSysCustomerServiceMockMvc.perform(get("/api/sys-customer-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysCustomerService() throws Exception {
        // Initialize the database
        sysCustomerServiceService.save(sysCustomerService);

        int databaseSizeBeforeUpdate = sysCustomerServiceRepository.findAll().size();

        // Update the sysCustomerService
        SysCustomerService updatedSysCustomerService = sysCustomerServiceRepository.findById(sysCustomerService.getId()).get();
        // Disconnect from session so that the updates on updatedSysCustomerService are not directly saved in db
        em.detach(updatedSysCustomerService);
        updatedSysCustomerService
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysCustomerServiceMockMvc.perform(put("/api/sys-customer-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysCustomerService)))
            .andExpect(status().isOk());

        // Validate the SysCustomerService in the database
        List<SysCustomerService> sysCustomerServiceList = sysCustomerServiceRepository.findAll();
        assertThat(sysCustomerServiceList).hasSize(databaseSizeBeforeUpdate);
        SysCustomerService testSysCustomerService = sysCustomerServiceList.get(sysCustomerServiceList.size() - 1);
        assertThat(testSysCustomerService.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSysCustomerService.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSysCustomerService.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysCustomerService.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysCustomerService() throws Exception {
        int databaseSizeBeforeUpdate = sysCustomerServiceRepository.findAll().size();

        // Create the SysCustomerService

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCustomerServiceMockMvc.perform(put("/api/sys-customer-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCustomerService)))
            .andExpect(status().isBadRequest());

        // Validate the SysCustomerService in the database
        List<SysCustomerService> sysCustomerServiceList = sysCustomerServiceRepository.findAll();
        assertThat(sysCustomerServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysCustomerService() throws Exception {
        // Initialize the database
        sysCustomerServiceService.save(sysCustomerService);

        int databaseSizeBeforeDelete = sysCustomerServiceRepository.findAll().size();

        // Get the sysCustomerService
        restSysCustomerServiceMockMvc.perform(delete("/api/sys-customer-services/{id}", sysCustomerService.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysCustomerService> sysCustomerServiceList = sysCustomerServiceRepository.findAll();
        assertThat(sysCustomerServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCustomerService.class);
        SysCustomerService sysCustomerService1 = new SysCustomerService();
        sysCustomerService1.setId(1L);
        SysCustomerService sysCustomerService2 = new SysCustomerService();
        sysCustomerService2.setId(sysCustomerService1.getId());
        assertThat(sysCustomerService1).isEqualTo(sysCustomerService2);
        sysCustomerService2.setId(2L);
        assertThat(sysCustomerService1).isNotEqualTo(sysCustomerService2);
        sysCustomerService1.setId(null);
        assertThat(sysCustomerService1).isNotEqualTo(sysCustomerService2);
    }
}
