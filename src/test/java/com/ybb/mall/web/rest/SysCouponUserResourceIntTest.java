package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysCouponUser;
import com.ybb.mall.repository.SysCouponUserRepository;
import com.ybb.mall.service.SysCouponUserService;
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
 * Test class for the SysCouponUserResource REST controller.
 *
 * @see SysCouponUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysCouponUserResourceIntTest {

    private static final Integer DEFAULT_USE_STATUS = 1;
    private static final Integer UPDATED_USE_STATUS = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysCouponUserRepository sysCouponUserRepository;

    @Autowired
    private SysCouponUserService sysCouponUserService;

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

    private MockMvc restSysCouponUserMockMvc;

    private SysCouponUser sysCouponUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysCouponUserResource sysCouponUserResource = new SysCouponUserResource(sysCouponUserService);
        this.restSysCouponUserMockMvc = MockMvcBuilders.standaloneSetup(sysCouponUserResource)
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
    public static SysCouponUser createEntity(EntityManager em) {
        SysCouponUser sysCouponUser = new SysCouponUser()
            .useStatus(DEFAULT_USE_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysCouponUser;
    }

    @Before
    public void initTest() {
        sysCouponUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysCouponUser() throws Exception {
        int databaseSizeBeforeCreate = sysCouponUserRepository.findAll().size();

        // Create the SysCouponUser
        restSysCouponUserMockMvc.perform(post("/api/sys-coupon-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCouponUser)))
            .andExpect(status().isCreated());

        // Validate the SysCouponUser in the database
        List<SysCouponUser> sysCouponUserList = sysCouponUserRepository.findAll();
        assertThat(sysCouponUserList).hasSize(databaseSizeBeforeCreate + 1);
        SysCouponUser testSysCouponUser = sysCouponUserList.get(sysCouponUserList.size() - 1);
        assertThat(testSysCouponUser.getUseStatus()).isEqualTo(DEFAULT_USE_STATUS);
        assertThat(testSysCouponUser.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysCouponUser.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysCouponUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysCouponUserRepository.findAll().size();

        // Create the SysCouponUser with an existing ID
        sysCouponUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCouponUserMockMvc.perform(post("/api/sys-coupon-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCouponUser)))
            .andExpect(status().isBadRequest());

        // Validate the SysCouponUser in the database
        List<SysCouponUser> sysCouponUserList = sysCouponUserRepository.findAll();
        assertThat(sysCouponUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysCouponUsers() throws Exception {
        // Initialize the database
        sysCouponUserRepository.saveAndFlush(sysCouponUser);

        // Get all the sysCouponUserList
        restSysCouponUserMockMvc.perform(get("/api/sys-coupon-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCouponUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].useStatus").value(hasItem(DEFAULT_USE_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysCouponUser() throws Exception {
        // Initialize the database
        sysCouponUserRepository.saveAndFlush(sysCouponUser);

        // Get the sysCouponUser
        restSysCouponUserMockMvc.perform(get("/api/sys-coupon-users/{id}", sysCouponUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysCouponUser.getId().intValue()))
            .andExpect(jsonPath("$.useStatus").value(DEFAULT_USE_STATUS))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysCouponUser() throws Exception {
        // Get the sysCouponUser
        restSysCouponUserMockMvc.perform(get("/api/sys-coupon-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysCouponUser() throws Exception {
        // Initialize the database
        sysCouponUserService.save(sysCouponUser);

        int databaseSizeBeforeUpdate = sysCouponUserRepository.findAll().size();

        // Update the sysCouponUser
        SysCouponUser updatedSysCouponUser = sysCouponUserRepository.findById(sysCouponUser.getId()).get();
        // Disconnect from session so that the updates on updatedSysCouponUser are not directly saved in db
        em.detach(updatedSysCouponUser);
        updatedSysCouponUser
            .useStatus(UPDATED_USE_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysCouponUserMockMvc.perform(put("/api/sys-coupon-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysCouponUser)))
            .andExpect(status().isOk());

        // Validate the SysCouponUser in the database
        List<SysCouponUser> sysCouponUserList = sysCouponUserRepository.findAll();
        assertThat(sysCouponUserList).hasSize(databaseSizeBeforeUpdate);
        SysCouponUser testSysCouponUser = sysCouponUserList.get(sysCouponUserList.size() - 1);
        assertThat(testSysCouponUser.getUseStatus()).isEqualTo(UPDATED_USE_STATUS);
        assertThat(testSysCouponUser.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysCouponUser.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysCouponUser() throws Exception {
        int databaseSizeBeforeUpdate = sysCouponUserRepository.findAll().size();

        // Create the SysCouponUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCouponUserMockMvc.perform(put("/api/sys-coupon-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCouponUser)))
            .andExpect(status().isBadRequest());

        // Validate the SysCouponUser in the database
        List<SysCouponUser> sysCouponUserList = sysCouponUserRepository.findAll();
        assertThat(sysCouponUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysCouponUser() throws Exception {
        // Initialize the database
        sysCouponUserService.save(sysCouponUser);

        int databaseSizeBeforeDelete = sysCouponUserRepository.findAll().size();

        // Get the sysCouponUser
        restSysCouponUserMockMvc.perform(delete("/api/sys-coupon-users/{id}", sysCouponUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysCouponUser> sysCouponUserList = sysCouponUserRepository.findAll();
        assertThat(sysCouponUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCouponUser.class);
        SysCouponUser sysCouponUser1 = new SysCouponUser();
        sysCouponUser1.setId(1L);
        SysCouponUser sysCouponUser2 = new SysCouponUser();
        sysCouponUser2.setId(sysCouponUser1.getId());
        assertThat(sysCouponUser1).isEqualTo(sysCouponUser2);
        sysCouponUser2.setId(2L);
        assertThat(sysCouponUser1).isNotEqualTo(sysCouponUser2);
        sysCouponUser1.setId(null);
        assertThat(sysCouponUser1).isNotEqualTo(sysCouponUser2);
    }
}
