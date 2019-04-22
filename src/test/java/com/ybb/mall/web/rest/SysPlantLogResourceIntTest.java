package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysPlantLog;
import com.ybb.mall.repository.SysPlantLogRepository;
import com.ybb.mall.service.SysPlantLogService;
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
import org.springframework.util.Base64Utils;
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
 * Test class for the SysPlantLogResource REST controller.
 *
 * @see SysPlantLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysPlantLogResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysPlantLogRepository sysPlantLogRepository;

    @Autowired
    private SysPlantLogService sysPlantLogService;

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

    private MockMvc restSysPlantLogMockMvc;

    private SysPlantLog sysPlantLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysPlantLogResource sysPlantLogResource = new SysPlantLogResource(sysPlantLogService);
        this.restSysPlantLogMockMvc = MockMvcBuilders.standaloneSetup(sysPlantLogResource)
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
    public static SysPlantLog createEntity(EntityManager em) {
        SysPlantLog sysPlantLog = new SysPlantLog()
            .name(DEFAULT_NAME)
            .image(DEFAULT_IMAGE)
            .description(DEFAULT_DESCRIPTION)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysPlantLog;
    }

    @Before
    public void initTest() {
        sysPlantLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysPlantLog() throws Exception {
        int databaseSizeBeforeCreate = sysPlantLogRepository.findAll().size();

        // Create the SysPlantLog
        restSysPlantLogMockMvc.perform(post("/api/sys-plant-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPlantLog)))
            .andExpect(status().isCreated());

        // Validate the SysPlantLog in the database
        List<SysPlantLog> sysPlantLogList = sysPlantLogRepository.findAll();
        assertThat(sysPlantLogList).hasSize(databaseSizeBeforeCreate + 1);
        SysPlantLog testSysPlantLog = sysPlantLogList.get(sysPlantLogList.size() - 1);
        assertThat(testSysPlantLog.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysPlantLog.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testSysPlantLog.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysPlantLog.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysPlantLog.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysPlantLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysPlantLogRepository.findAll().size();

        // Create the SysPlantLog with an existing ID
        sysPlantLog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysPlantLogMockMvc.perform(post("/api/sys-plant-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPlantLog)))
            .andExpect(status().isBadRequest());

        // Validate the SysPlantLog in the database
        List<SysPlantLog> sysPlantLogList = sysPlantLogRepository.findAll();
        assertThat(sysPlantLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysPlantLogs() throws Exception {
        // Initialize the database
        sysPlantLogRepository.saveAndFlush(sysPlantLog);

        // Get all the sysPlantLogList
        restSysPlantLogMockMvc.perform(get("/api/sys-plant-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysPlantLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysPlantLog() throws Exception {
        // Initialize the database
        sysPlantLogRepository.saveAndFlush(sysPlantLog);

        // Get the sysPlantLog
        restSysPlantLogMockMvc.perform(get("/api/sys-plant-logs/{id}", sysPlantLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysPlantLog.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysPlantLog() throws Exception {
        // Get the sysPlantLog
        restSysPlantLogMockMvc.perform(get("/api/sys-plant-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysPlantLog() throws Exception {
        // Initialize the database
        sysPlantLogService.save(sysPlantLog);

        int databaseSizeBeforeUpdate = sysPlantLogRepository.findAll().size();

        // Update the sysPlantLog
        SysPlantLog updatedSysPlantLog = sysPlantLogRepository.findById(sysPlantLog.getId()).get();
        // Disconnect from session so that the updates on updatedSysPlantLog are not directly saved in db
        em.detach(updatedSysPlantLog);
        updatedSysPlantLog
            .name(UPDATED_NAME)
            .image(UPDATED_IMAGE)
            .description(UPDATED_DESCRIPTION)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysPlantLogMockMvc.perform(put("/api/sys-plant-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysPlantLog)))
            .andExpect(status().isOk());

        // Validate the SysPlantLog in the database
        List<SysPlantLog> sysPlantLogList = sysPlantLogRepository.findAll();
        assertThat(sysPlantLogList).hasSize(databaseSizeBeforeUpdate);
        SysPlantLog testSysPlantLog = sysPlantLogList.get(sysPlantLogList.size() - 1);
        assertThat(testSysPlantLog.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysPlantLog.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testSysPlantLog.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysPlantLog.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysPlantLog.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysPlantLog() throws Exception {
        int databaseSizeBeforeUpdate = sysPlantLogRepository.findAll().size();

        // Create the SysPlantLog

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPlantLogMockMvc.perform(put("/api/sys-plant-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPlantLog)))
            .andExpect(status().isBadRequest());

        // Validate the SysPlantLog in the database
        List<SysPlantLog> sysPlantLogList = sysPlantLogRepository.findAll();
        assertThat(sysPlantLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysPlantLog() throws Exception {
        // Initialize the database
        sysPlantLogService.save(sysPlantLog);

        int databaseSizeBeforeDelete = sysPlantLogRepository.findAll().size();

        // Get the sysPlantLog
        restSysPlantLogMockMvc.perform(delete("/api/sys-plant-logs/{id}", sysPlantLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysPlantLog> sysPlantLogList = sysPlantLogRepository.findAll();
        assertThat(sysPlantLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPlantLog.class);
        SysPlantLog sysPlantLog1 = new SysPlantLog();
        sysPlantLog1.setId(1L);
        SysPlantLog sysPlantLog2 = new SysPlantLog();
        sysPlantLog2.setId(sysPlantLog1.getId());
        assertThat(sysPlantLog1).isEqualTo(sysPlantLog2);
        sysPlantLog2.setId(2L);
        assertThat(sysPlantLog1).isNotEqualTo(sysPlantLog2);
        sysPlantLog1.setId(null);
        assertThat(sysPlantLog1).isNotEqualTo(sysPlantLog2);
    }
}
