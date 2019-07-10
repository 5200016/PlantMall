package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysMaintenanceFinish;
import com.ybb.mall.repository.SysMaintenanceFinishRepository;
import com.ybb.mall.service.SysMaintenanceFinishService;
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
 * Test class for the SysMaintenanceFinishResource REST controller.
 *
 * @see SysMaintenanceFinishResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysMaintenanceFinishResourceIntTest {

    private static final String DEFAULT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_FINISH_TIME = "AAAAAAAAAA";
    private static final String UPDATED_FINISH_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysMaintenanceFinishRepository sysMaintenanceFinishRepository;

    @Autowired
    private SysMaintenanceFinishService sysMaintenanceFinishService;

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

    private MockMvc restSysMaintenanceFinishMockMvc;

    private SysMaintenanceFinish sysMaintenanceFinish;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysMaintenanceFinishResource sysMaintenanceFinishResource = new SysMaintenanceFinishResource(sysMaintenanceFinishService);
        this.restSysMaintenanceFinishMockMvc = MockMvcBuilders.standaloneSetup(sysMaintenanceFinishResource)
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
    public static SysMaintenanceFinish createEntity(EntityManager em) {
        SysMaintenanceFinish sysMaintenanceFinish = new SysMaintenanceFinish()
            .time(DEFAULT_TIME)
            .finishTime(DEFAULT_FINISH_TIME)
            .url(DEFAULT_URL)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysMaintenanceFinish;
    }

    @Before
    public void initTest() {
        sysMaintenanceFinish = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysMaintenanceFinish() throws Exception {
        int databaseSizeBeforeCreate = sysMaintenanceFinishRepository.findAll().size();

        // Create the SysMaintenanceFinish
        restSysMaintenanceFinishMockMvc.perform(post("/api/sys-maintenance-finishes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMaintenanceFinish)))
            .andExpect(status().isCreated());

        // Validate the SysMaintenanceFinish in the database
        List<SysMaintenanceFinish> sysMaintenanceFinishList = sysMaintenanceFinishRepository.findAll();
        assertThat(sysMaintenanceFinishList).hasSize(databaseSizeBeforeCreate + 1);
        SysMaintenanceFinish testSysMaintenanceFinish = sysMaintenanceFinishList.get(sysMaintenanceFinishList.size() - 1);
        assertThat(testSysMaintenanceFinish.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testSysMaintenanceFinish.getFinishTime()).isEqualTo(DEFAULT_FINISH_TIME);
        assertThat(testSysMaintenanceFinish.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testSysMaintenanceFinish.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysMaintenanceFinish.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysMaintenanceFinishWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysMaintenanceFinishRepository.findAll().size();

        // Create the SysMaintenanceFinish with an existing ID
        sysMaintenanceFinish.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysMaintenanceFinishMockMvc.perform(post("/api/sys-maintenance-finishes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMaintenanceFinish)))
            .andExpect(status().isBadRequest());

        // Validate the SysMaintenanceFinish in the database
        List<SysMaintenanceFinish> sysMaintenanceFinishList = sysMaintenanceFinishRepository.findAll();
        assertThat(sysMaintenanceFinishList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysMaintenanceFinishes() throws Exception {
        // Initialize the database
        sysMaintenanceFinishRepository.saveAndFlush(sysMaintenanceFinish);

        // Get all the sysMaintenanceFinishList
        restSysMaintenanceFinishMockMvc.perform(get("/api/sys-maintenance-finishes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysMaintenanceFinish.getId().intValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].finishTime").value(hasItem(DEFAULT_FINISH_TIME.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysMaintenanceFinish() throws Exception {
        // Initialize the database
        sysMaintenanceFinishRepository.saveAndFlush(sysMaintenanceFinish);

        // Get the sysMaintenanceFinish
        restSysMaintenanceFinishMockMvc.perform(get("/api/sys-maintenance-finishes/{id}", sysMaintenanceFinish.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysMaintenanceFinish.getId().intValue()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.finishTime").value(DEFAULT_FINISH_TIME.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysMaintenanceFinish() throws Exception {
        // Get the sysMaintenanceFinish
        restSysMaintenanceFinishMockMvc.perform(get("/api/sys-maintenance-finishes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysMaintenanceFinish() throws Exception {
        // Initialize the database
        sysMaintenanceFinishService.save(sysMaintenanceFinish);

        int databaseSizeBeforeUpdate = sysMaintenanceFinishRepository.findAll().size();

        // Update the sysMaintenanceFinish
        SysMaintenanceFinish updatedSysMaintenanceFinish = sysMaintenanceFinishRepository.findById(sysMaintenanceFinish.getId()).get();
        // Disconnect from session so that the updates on updatedSysMaintenanceFinish are not directly saved in db
        em.detach(updatedSysMaintenanceFinish);
        updatedSysMaintenanceFinish
            .time(UPDATED_TIME)
            .finishTime(UPDATED_FINISH_TIME)
            .url(UPDATED_URL)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysMaintenanceFinishMockMvc.perform(put("/api/sys-maintenance-finishes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysMaintenanceFinish)))
            .andExpect(status().isOk());

        // Validate the SysMaintenanceFinish in the database
        List<SysMaintenanceFinish> sysMaintenanceFinishList = sysMaintenanceFinishRepository.findAll();
        assertThat(sysMaintenanceFinishList).hasSize(databaseSizeBeforeUpdate);
        SysMaintenanceFinish testSysMaintenanceFinish = sysMaintenanceFinishList.get(sysMaintenanceFinishList.size() - 1);
        assertThat(testSysMaintenanceFinish.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testSysMaintenanceFinish.getFinishTime()).isEqualTo(UPDATED_FINISH_TIME);
        assertThat(testSysMaintenanceFinish.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testSysMaintenanceFinish.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysMaintenanceFinish.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysMaintenanceFinish() throws Exception {
        int databaseSizeBeforeUpdate = sysMaintenanceFinishRepository.findAll().size();

        // Create the SysMaintenanceFinish

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysMaintenanceFinishMockMvc.perform(put("/api/sys-maintenance-finishes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMaintenanceFinish)))
            .andExpect(status().isBadRequest());

        // Validate the SysMaintenanceFinish in the database
        List<SysMaintenanceFinish> sysMaintenanceFinishList = sysMaintenanceFinishRepository.findAll();
        assertThat(sysMaintenanceFinishList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysMaintenanceFinish() throws Exception {
        // Initialize the database
        sysMaintenanceFinishService.save(sysMaintenanceFinish);

        int databaseSizeBeforeDelete = sysMaintenanceFinishRepository.findAll().size();

        // Get the sysMaintenanceFinish
        restSysMaintenanceFinishMockMvc.perform(delete("/api/sys-maintenance-finishes/{id}", sysMaintenanceFinish.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysMaintenanceFinish> sysMaintenanceFinishList = sysMaintenanceFinishRepository.findAll();
        assertThat(sysMaintenanceFinishList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysMaintenanceFinish.class);
        SysMaintenanceFinish sysMaintenanceFinish1 = new SysMaintenanceFinish();
        sysMaintenanceFinish1.setId(1L);
        SysMaintenanceFinish sysMaintenanceFinish2 = new SysMaintenanceFinish();
        sysMaintenanceFinish2.setId(sysMaintenanceFinish1.getId());
        assertThat(sysMaintenanceFinish1).isEqualTo(sysMaintenanceFinish2);
        sysMaintenanceFinish2.setId(2L);
        assertThat(sysMaintenanceFinish1).isNotEqualTo(sysMaintenanceFinish2);
        sysMaintenanceFinish1.setId(null);
        assertThat(sysMaintenanceFinish1).isNotEqualTo(sysMaintenanceFinish2);
    }
}
