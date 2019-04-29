package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysAppointment;
import com.ybb.mall.repository.SysAppointmentRepository;
import com.ybb.mall.service.SysAppointmentService;
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
 * Test class for the SysAppointmentResource REST controller.
 *
 * @see SysAppointmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysAppointmentResourceIntTest {

    private static final ZonedDateTime DEFAULT_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysAppointmentRepository sysAppointmentRepository;

    @Autowired
    private SysAppointmentService sysAppointmentService;

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

    private MockMvc restSysAppointmentMockMvc;

    private SysAppointment sysAppointment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysAppointmentResource sysAppointmentResource = new SysAppointmentResource(sysAppointmentService);
        this.restSysAppointmentMockMvc = MockMvcBuilders.standaloneSetup(sysAppointmentResource)
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
    public static SysAppointment createEntity(EntityManager em) {
        SysAppointment sysAppointment = new SysAppointment()
            .time(DEFAULT_TIME)
            .remark(DEFAULT_REMARK)
            .status(DEFAULT_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysAppointment;
    }

    @Before
    public void initTest() {
        sysAppointment = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysAppointment() throws Exception {
        int databaseSizeBeforeCreate = sysAppointmentRepository.findAll().size();

        // Create the SysAppointment
        restSysAppointmentMockMvc.perform(post("/api/sys-appointments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysAppointment)))
            .andExpect(status().isCreated());

        // Validate the SysAppointment in the database
        List<SysAppointment> sysAppointmentList = sysAppointmentRepository.findAll();
        assertThat(sysAppointmentList).hasSize(databaseSizeBeforeCreate + 1);
        SysAppointment testSysAppointment = sysAppointmentList.get(sysAppointmentList.size() - 1);
        assertThat(testSysAppointment.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testSysAppointment.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testSysAppointment.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysAppointment.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysAppointment.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysAppointmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysAppointmentRepository.findAll().size();

        // Create the SysAppointment with an existing ID
        sysAppointment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysAppointmentMockMvc.perform(post("/api/sys-appointments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysAppointment)))
            .andExpect(status().isBadRequest());

        // Validate the SysAppointment in the database
        List<SysAppointment> sysAppointmentList = sysAppointmentRepository.findAll();
        assertThat(sysAppointmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysAppointments() throws Exception {
        // Initialize the database
        sysAppointmentRepository.saveAndFlush(sysAppointment);

        // Get all the sysAppointmentList
        restSysAppointmentMockMvc.perform(get("/api/sys-appointments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysAppointment.getId().intValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(sameInstant(DEFAULT_TIME))))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysAppointment() throws Exception {
        // Initialize the database
        sysAppointmentRepository.saveAndFlush(sysAppointment);

        // Get the sysAppointment
        restSysAppointmentMockMvc.perform(get("/api/sys-appointments/{id}", sysAppointment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysAppointment.getId().intValue()))
            .andExpect(jsonPath("$.time").value(sameInstant(DEFAULT_TIME)))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysAppointment() throws Exception {
        // Get the sysAppointment
        restSysAppointmentMockMvc.perform(get("/api/sys-appointments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysAppointment() throws Exception {
        // Initialize the database
        sysAppointmentService.save(sysAppointment);

        int databaseSizeBeforeUpdate = sysAppointmentRepository.findAll().size();

        // Update the sysAppointment
        SysAppointment updatedSysAppointment = sysAppointmentRepository.findById(sysAppointment.getId()).get();
        // Disconnect from session so that the updates on updatedSysAppointment are not directly saved in db
        em.detach(updatedSysAppointment);
        updatedSysAppointment
            .time(UPDATED_TIME)
            .remark(UPDATED_REMARK)
            .status(UPDATED_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysAppointmentMockMvc.perform(put("/api/sys-appointments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysAppointment)))
            .andExpect(status().isOk());

        // Validate the SysAppointment in the database
        List<SysAppointment> sysAppointmentList = sysAppointmentRepository.findAll();
        assertThat(sysAppointmentList).hasSize(databaseSizeBeforeUpdate);
        SysAppointment testSysAppointment = sysAppointmentList.get(sysAppointmentList.size() - 1);
        assertThat(testSysAppointment.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testSysAppointment.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSysAppointment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysAppointment.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysAppointment.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysAppointment() throws Exception {
        int databaseSizeBeforeUpdate = sysAppointmentRepository.findAll().size();

        // Create the SysAppointment

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysAppointmentMockMvc.perform(put("/api/sys-appointments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysAppointment)))
            .andExpect(status().isBadRequest());

        // Validate the SysAppointment in the database
        List<SysAppointment> sysAppointmentList = sysAppointmentRepository.findAll();
        assertThat(sysAppointmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysAppointment() throws Exception {
        // Initialize the database
        sysAppointmentService.save(sysAppointment);

        int databaseSizeBeforeDelete = sysAppointmentRepository.findAll().size();

        // Get the sysAppointment
        restSysAppointmentMockMvc.perform(delete("/api/sys-appointments/{id}", sysAppointment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysAppointment> sysAppointmentList = sysAppointmentRepository.findAll();
        assertThat(sysAppointmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysAppointment.class);
        SysAppointment sysAppointment1 = new SysAppointment();
        sysAppointment1.setId(1L);
        SysAppointment sysAppointment2 = new SysAppointment();
        sysAppointment2.setId(sysAppointment1.getId());
        assertThat(sysAppointment1).isEqualTo(sysAppointment2);
        sysAppointment2.setId(2L);
        assertThat(sysAppointment1).isNotEqualTo(sysAppointment2);
        sysAppointment1.setId(null);
        assertThat(sysAppointment1).isNotEqualTo(sysAppointment2);
    }
}
