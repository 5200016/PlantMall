package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysMaintenancePersonnel;
import com.ybb.mall.repository.SysMaintenancePersonnelRepository;
import com.ybb.mall.service.SysMaintenancePersonnelService;
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
 * Test class for the SysMaintenancePersonnelResource REST controller.
 *
 * @see SysMaintenancePersonnelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysMaintenancePersonnelResourceIntTest {

    private static final String DEFAULT_OPENID = "AAAAAAAAAA";
    private static final String UPDATED_OPENID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysMaintenancePersonnelRepository sysMaintenancePersonnelRepository;

    @Autowired
    private SysMaintenancePersonnelService sysMaintenancePersonnelService;

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

    private MockMvc restSysMaintenancePersonnelMockMvc;

    private SysMaintenancePersonnel sysMaintenancePersonnel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysMaintenancePersonnelResource sysMaintenancePersonnelResource = new SysMaintenancePersonnelResource(sysMaintenancePersonnelService);
        this.restSysMaintenancePersonnelMockMvc = MockMvcBuilders.standaloneSetup(sysMaintenancePersonnelResource)
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
    public static SysMaintenancePersonnel createEntity(EntityManager em) {
        SysMaintenancePersonnel sysMaintenancePersonnel = new SysMaintenancePersonnel()
            .openid(DEFAULT_OPENID)
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysMaintenancePersonnel;
    }

    @Before
    public void initTest() {
        sysMaintenancePersonnel = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysMaintenancePersonnel() throws Exception {
        int databaseSizeBeforeCreate = sysMaintenancePersonnelRepository.findAll().size();

        // Create the SysMaintenancePersonnel
        restSysMaintenancePersonnelMockMvc.perform(post("/api/sys-maintenance-personnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMaintenancePersonnel)))
            .andExpect(status().isCreated());

        // Validate the SysMaintenancePersonnel in the database
        List<SysMaintenancePersonnel> sysMaintenancePersonnelList = sysMaintenancePersonnelRepository.findAll();
        assertThat(sysMaintenancePersonnelList).hasSize(databaseSizeBeforeCreate + 1);
        SysMaintenancePersonnel testSysMaintenancePersonnel = sysMaintenancePersonnelList.get(sysMaintenancePersonnelList.size() - 1);
        assertThat(testSysMaintenancePersonnel.getOpenid()).isEqualTo(DEFAULT_OPENID);
        assertThat(testSysMaintenancePersonnel.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysMaintenancePersonnel.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSysMaintenancePersonnel.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysMaintenancePersonnel.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysMaintenancePersonnelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysMaintenancePersonnelRepository.findAll().size();

        // Create the SysMaintenancePersonnel with an existing ID
        sysMaintenancePersonnel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysMaintenancePersonnelMockMvc.perform(post("/api/sys-maintenance-personnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMaintenancePersonnel)))
            .andExpect(status().isBadRequest());

        // Validate the SysMaintenancePersonnel in the database
        List<SysMaintenancePersonnel> sysMaintenancePersonnelList = sysMaintenancePersonnelRepository.findAll();
        assertThat(sysMaintenancePersonnelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysMaintenancePersonnels() throws Exception {
        // Initialize the database
        sysMaintenancePersonnelRepository.saveAndFlush(sysMaintenancePersonnel);

        // Get all the sysMaintenancePersonnelList
        restSysMaintenancePersonnelMockMvc.perform(get("/api/sys-maintenance-personnels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysMaintenancePersonnel.getId().intValue())))
            .andExpect(jsonPath("$.[*].openid").value(hasItem(DEFAULT_OPENID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysMaintenancePersonnel() throws Exception {
        // Initialize the database
        sysMaintenancePersonnelRepository.saveAndFlush(sysMaintenancePersonnel);

        // Get the sysMaintenancePersonnel
        restSysMaintenancePersonnelMockMvc.perform(get("/api/sys-maintenance-personnels/{id}", sysMaintenancePersonnel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysMaintenancePersonnel.getId().intValue()))
            .andExpect(jsonPath("$.openid").value(DEFAULT_OPENID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysMaintenancePersonnel() throws Exception {
        // Get the sysMaintenancePersonnel
        restSysMaintenancePersonnelMockMvc.perform(get("/api/sys-maintenance-personnels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysMaintenancePersonnel() throws Exception {
        // Initialize the database
        sysMaintenancePersonnelService.save(sysMaintenancePersonnel);

        int databaseSizeBeforeUpdate = sysMaintenancePersonnelRepository.findAll().size();

        // Update the sysMaintenancePersonnel
        SysMaintenancePersonnel updatedSysMaintenancePersonnel = sysMaintenancePersonnelRepository.findById(sysMaintenancePersonnel.getId()).get();
        // Disconnect from session so that the updates on updatedSysMaintenancePersonnel are not directly saved in db
        em.detach(updatedSysMaintenancePersonnel);
        updatedSysMaintenancePersonnel
            .openid(UPDATED_OPENID)
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysMaintenancePersonnelMockMvc.perform(put("/api/sys-maintenance-personnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysMaintenancePersonnel)))
            .andExpect(status().isOk());

        // Validate the SysMaintenancePersonnel in the database
        List<SysMaintenancePersonnel> sysMaintenancePersonnelList = sysMaintenancePersonnelRepository.findAll();
        assertThat(sysMaintenancePersonnelList).hasSize(databaseSizeBeforeUpdate);
        SysMaintenancePersonnel testSysMaintenancePersonnel = sysMaintenancePersonnelList.get(sysMaintenancePersonnelList.size() - 1);
        assertThat(testSysMaintenancePersonnel.getOpenid()).isEqualTo(UPDATED_OPENID);
        assertThat(testSysMaintenancePersonnel.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysMaintenancePersonnel.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSysMaintenancePersonnel.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysMaintenancePersonnel.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysMaintenancePersonnel() throws Exception {
        int databaseSizeBeforeUpdate = sysMaintenancePersonnelRepository.findAll().size();

        // Create the SysMaintenancePersonnel

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysMaintenancePersonnelMockMvc.perform(put("/api/sys-maintenance-personnels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMaintenancePersonnel)))
            .andExpect(status().isBadRequest());

        // Validate the SysMaintenancePersonnel in the database
        List<SysMaintenancePersonnel> sysMaintenancePersonnelList = sysMaintenancePersonnelRepository.findAll();
        assertThat(sysMaintenancePersonnelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysMaintenancePersonnel() throws Exception {
        // Initialize the database
        sysMaintenancePersonnelService.save(sysMaintenancePersonnel);

        int databaseSizeBeforeDelete = sysMaintenancePersonnelRepository.findAll().size();

        // Get the sysMaintenancePersonnel
        restSysMaintenancePersonnelMockMvc.perform(delete("/api/sys-maintenance-personnels/{id}", sysMaintenancePersonnel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysMaintenancePersonnel> sysMaintenancePersonnelList = sysMaintenancePersonnelRepository.findAll();
        assertThat(sysMaintenancePersonnelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysMaintenancePersonnel.class);
        SysMaintenancePersonnel sysMaintenancePersonnel1 = new SysMaintenancePersonnel();
        sysMaintenancePersonnel1.setId(1L);
        SysMaintenancePersonnel sysMaintenancePersonnel2 = new SysMaintenancePersonnel();
        sysMaintenancePersonnel2.setId(sysMaintenancePersonnel1.getId());
        assertThat(sysMaintenancePersonnel1).isEqualTo(sysMaintenancePersonnel2);
        sysMaintenancePersonnel2.setId(2L);
        assertThat(sysMaintenancePersonnel1).isNotEqualTo(sysMaintenancePersonnel2);
        sysMaintenancePersonnel1.setId(null);
        assertThat(sysMaintenancePersonnel1).isNotEqualTo(sysMaintenancePersonnel2);
    }
}
