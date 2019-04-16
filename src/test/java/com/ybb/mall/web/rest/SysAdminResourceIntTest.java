package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysAdmin;
import com.ybb.mall.repository.SysAdminRepository;
import com.ybb.mall.service.SysAdminService;
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
 * Test class for the SysAdminResource REST controller.
 *
 * @see SysAdminResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysAdminResourceIntTest {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final Integer DEFAULT_ROLE = 1;
    private static final Integer UPDATED_ROLE = 2;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysAdminRepository sysAdminRepository;

    @Autowired
    private SysAdminService sysAdminService;

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

    private MockMvc restSysAdminMockMvc;

    private SysAdmin sysAdmin;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysAdminResource sysAdminResource = new SysAdminResource(sysAdminService);
        this.restSysAdminMockMvc = MockMvcBuilders.standaloneSetup(sysAdminResource)
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
    public static SysAdmin createEntity(EntityManager em) {
        SysAdmin sysAdmin = new SysAdmin()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .avatar(DEFAULT_AVATAR)
            .sex(DEFAULT_SEX)
            .role(DEFAULT_ROLE)
            .phone(DEFAULT_PHONE)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysAdmin;
    }

    @Before
    public void initTest() {
        sysAdmin = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysAdmin() throws Exception {
        int databaseSizeBeforeCreate = sysAdminRepository.findAll().size();

        // Create the SysAdmin
        restSysAdminMockMvc.perform(post("/api/sys-admins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysAdmin)))
            .andExpect(status().isCreated());

        // Validate the SysAdmin in the database
        List<SysAdmin> sysAdminList = sysAdminRepository.findAll();
        assertThat(sysAdminList).hasSize(databaseSizeBeforeCreate + 1);
        SysAdmin testSysAdmin = sysAdminList.get(sysAdminList.size() - 1);
        assertThat(testSysAdmin.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testSysAdmin.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testSysAdmin.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testSysAdmin.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testSysAdmin.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testSysAdmin.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSysAdmin.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysAdmin.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysAdminWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysAdminRepository.findAll().size();

        // Create the SysAdmin with an existing ID
        sysAdmin.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysAdminMockMvc.perform(post("/api/sys-admins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysAdmin)))
            .andExpect(status().isBadRequest());

        // Validate the SysAdmin in the database
        List<SysAdmin> sysAdminList = sysAdminRepository.findAll();
        assertThat(sysAdminList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysAdmins() throws Exception {
        // Initialize the database
        sysAdminRepository.saveAndFlush(sysAdmin);

        // Get all the sysAdminList
        restSysAdminMockMvc.perform(get("/api/sys-admins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysAdmin.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysAdmin() throws Exception {
        // Initialize the database
        sysAdminRepository.saveAndFlush(sysAdmin);

        // Get the sysAdmin
        restSysAdminMockMvc.perform(get("/api/sys-admins/{id}", sysAdmin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysAdmin.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysAdmin() throws Exception {
        // Get the sysAdmin
        restSysAdminMockMvc.perform(get("/api/sys-admins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysAdmin() throws Exception {
        // Initialize the database
        sysAdminService.save(sysAdmin);

        int databaseSizeBeforeUpdate = sysAdminRepository.findAll().size();

        // Update the sysAdmin
        SysAdmin updatedSysAdmin = sysAdminRepository.findById(sysAdmin.getId()).get();
        // Disconnect from session so that the updates on updatedSysAdmin are not directly saved in db
        em.detach(updatedSysAdmin);
        updatedSysAdmin
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .avatar(UPDATED_AVATAR)
            .sex(UPDATED_SEX)
            .role(UPDATED_ROLE)
            .phone(UPDATED_PHONE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysAdminMockMvc.perform(put("/api/sys-admins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysAdmin)))
            .andExpect(status().isOk());

        // Validate the SysAdmin in the database
        List<SysAdmin> sysAdminList = sysAdminRepository.findAll();
        assertThat(sysAdminList).hasSize(databaseSizeBeforeUpdate);
        SysAdmin testSysAdmin = sysAdminList.get(sysAdminList.size() - 1);
        assertThat(testSysAdmin.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testSysAdmin.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testSysAdmin.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testSysAdmin.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testSysAdmin.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testSysAdmin.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSysAdmin.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysAdmin.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysAdmin() throws Exception {
        int databaseSizeBeforeUpdate = sysAdminRepository.findAll().size();

        // Create the SysAdmin

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysAdminMockMvc.perform(put("/api/sys-admins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysAdmin)))
            .andExpect(status().isBadRequest());

        // Validate the SysAdmin in the database
        List<SysAdmin> sysAdminList = sysAdminRepository.findAll();
        assertThat(sysAdminList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysAdmin() throws Exception {
        // Initialize the database
        sysAdminService.save(sysAdmin);

        int databaseSizeBeforeDelete = sysAdminRepository.findAll().size();

        // Get the sysAdmin
        restSysAdminMockMvc.perform(delete("/api/sys-admins/{id}", sysAdmin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysAdmin> sysAdminList = sysAdminRepository.findAll();
        assertThat(sysAdminList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysAdmin.class);
        SysAdmin sysAdmin1 = new SysAdmin();
        sysAdmin1.setId(1L);
        SysAdmin sysAdmin2 = new SysAdmin();
        sysAdmin2.setId(sysAdmin1.getId());
        assertThat(sysAdmin1).isEqualTo(sysAdmin2);
        sysAdmin2.setId(2L);
        assertThat(sysAdmin1).isNotEqualTo(sysAdmin2);
        sysAdmin1.setId(null);
        assertThat(sysAdmin1).isNotEqualTo(sysAdmin2);
    }
}
