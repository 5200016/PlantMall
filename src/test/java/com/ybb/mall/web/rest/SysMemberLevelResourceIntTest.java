package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysMemberLevel;
import com.ybb.mall.repository.SysMemberLevelRepository;
import com.ybb.mall.service.SysMemberLevelService;
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
 * Test class for the SysMemberLevelResource REST controller.
 *
 * @see SysMemberLevelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysMemberLevelResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_LEFT_VALUE = 1;
    private static final Integer UPDATED_LEFT_VALUE = 2;

    private static final Integer DEFAULT_RIGHT_VALUE = 1;
    private static final Integer UPDATED_RIGHT_VALUE = 2;

    private static final Double DEFAULT_DISCOUNT = 1D;
    private static final Double UPDATED_DISCOUNT = 2D;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysMemberLevelRepository sysMemberLevelRepository;

    @Autowired
    private SysMemberLevelService sysMemberLevelService;

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

    private MockMvc restSysMemberLevelMockMvc;

    private SysMemberLevel sysMemberLevel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysMemberLevelResource sysMemberLevelResource = new SysMemberLevelResource(sysMemberLevelService);
        this.restSysMemberLevelMockMvc = MockMvcBuilders.standaloneSetup(sysMemberLevelResource)
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
    public static SysMemberLevel createEntity(EntityManager em) {
        SysMemberLevel sysMemberLevel = new SysMemberLevel()
            .name(DEFAULT_NAME)
            .level(DEFAULT_LEVEL)
            .leftValue(DEFAULT_LEFT_VALUE)
            .rightValue(DEFAULT_RIGHT_VALUE)
            .discount(DEFAULT_DISCOUNT)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysMemberLevel;
    }

    @Before
    public void initTest() {
        sysMemberLevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysMemberLevel() throws Exception {
        int databaseSizeBeforeCreate = sysMemberLevelRepository.findAll().size();

        // Create the SysMemberLevel
        restSysMemberLevelMockMvc.perform(post("/api/sys-member-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMemberLevel)))
            .andExpect(status().isCreated());

        // Validate the SysMemberLevel in the database
        List<SysMemberLevel> sysMemberLevelList = sysMemberLevelRepository.findAll();
        assertThat(sysMemberLevelList).hasSize(databaseSizeBeforeCreate + 1);
        SysMemberLevel testSysMemberLevel = sysMemberLevelList.get(sysMemberLevelList.size() - 1);
        assertThat(testSysMemberLevel.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysMemberLevel.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testSysMemberLevel.getLeftValue()).isEqualTo(DEFAULT_LEFT_VALUE);
        assertThat(testSysMemberLevel.getRightValue()).isEqualTo(DEFAULT_RIGHT_VALUE);
        assertThat(testSysMemberLevel.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testSysMemberLevel.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysMemberLevel.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysMemberLevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysMemberLevelRepository.findAll().size();

        // Create the SysMemberLevel with an existing ID
        sysMemberLevel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysMemberLevelMockMvc.perform(post("/api/sys-member-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMemberLevel)))
            .andExpect(status().isBadRequest());

        // Validate the SysMemberLevel in the database
        List<SysMemberLevel> sysMemberLevelList = sysMemberLevelRepository.findAll();
        assertThat(sysMemberLevelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysMemberLevels() throws Exception {
        // Initialize the database
        sysMemberLevelRepository.saveAndFlush(sysMemberLevel);

        // Get all the sysMemberLevelList
        restSysMemberLevelMockMvc.perform(get("/api/sys-member-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysMemberLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].leftValue").value(hasItem(DEFAULT_LEFT_VALUE)))
            .andExpect(jsonPath("$.[*].rightValue").value(hasItem(DEFAULT_RIGHT_VALUE)))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysMemberLevel() throws Exception {
        // Initialize the database
        sysMemberLevelRepository.saveAndFlush(sysMemberLevel);

        // Get the sysMemberLevel
        restSysMemberLevelMockMvc.perform(get("/api/sys-member-levels/{id}", sysMemberLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysMemberLevel.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.leftValue").value(DEFAULT_LEFT_VALUE))
            .andExpect(jsonPath("$.rightValue").value(DEFAULT_RIGHT_VALUE))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysMemberLevel() throws Exception {
        // Get the sysMemberLevel
        restSysMemberLevelMockMvc.perform(get("/api/sys-member-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysMemberLevel() throws Exception {
        // Initialize the database
        sysMemberLevelService.save(sysMemberLevel);

        int databaseSizeBeforeUpdate = sysMemberLevelRepository.findAll().size();

        // Update the sysMemberLevel
        SysMemberLevel updatedSysMemberLevel = sysMemberLevelRepository.findById(sysMemberLevel.getId()).get();
        // Disconnect from session so that the updates on updatedSysMemberLevel are not directly saved in db
        em.detach(updatedSysMemberLevel);
        updatedSysMemberLevel
            .name(UPDATED_NAME)
            .level(UPDATED_LEVEL)
            .leftValue(UPDATED_LEFT_VALUE)
            .rightValue(UPDATED_RIGHT_VALUE)
            .discount(UPDATED_DISCOUNT)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysMemberLevelMockMvc.perform(put("/api/sys-member-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysMemberLevel)))
            .andExpect(status().isOk());

        // Validate the SysMemberLevel in the database
        List<SysMemberLevel> sysMemberLevelList = sysMemberLevelRepository.findAll();
        assertThat(sysMemberLevelList).hasSize(databaseSizeBeforeUpdate);
        SysMemberLevel testSysMemberLevel = sysMemberLevelList.get(sysMemberLevelList.size() - 1);
        assertThat(testSysMemberLevel.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysMemberLevel.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testSysMemberLevel.getLeftValue()).isEqualTo(UPDATED_LEFT_VALUE);
        assertThat(testSysMemberLevel.getRightValue()).isEqualTo(UPDATED_RIGHT_VALUE);
        assertThat(testSysMemberLevel.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testSysMemberLevel.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysMemberLevel.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysMemberLevel() throws Exception {
        int databaseSizeBeforeUpdate = sysMemberLevelRepository.findAll().size();

        // Create the SysMemberLevel

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysMemberLevelMockMvc.perform(put("/api/sys-member-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMemberLevel)))
            .andExpect(status().isBadRequest());

        // Validate the SysMemberLevel in the database
        List<SysMemberLevel> sysMemberLevelList = sysMemberLevelRepository.findAll();
        assertThat(sysMemberLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysMemberLevel() throws Exception {
        // Initialize the database
        sysMemberLevelService.save(sysMemberLevel);

        int databaseSizeBeforeDelete = sysMemberLevelRepository.findAll().size();

        // Get the sysMemberLevel
        restSysMemberLevelMockMvc.perform(delete("/api/sys-member-levels/{id}", sysMemberLevel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysMemberLevel> sysMemberLevelList = sysMemberLevelRepository.findAll();
        assertThat(sysMemberLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysMemberLevel.class);
        SysMemberLevel sysMemberLevel1 = new SysMemberLevel();
        sysMemberLevel1.setId(1L);
        SysMemberLevel sysMemberLevel2 = new SysMemberLevel();
        sysMemberLevel2.setId(sysMemberLevel1.getId());
        assertThat(sysMemberLevel1).isEqualTo(sysMemberLevel2);
        sysMemberLevel2.setId(2L);
        assertThat(sysMemberLevel1).isNotEqualTo(sysMemberLevel2);
        sysMemberLevel1.setId(null);
        assertThat(sysMemberLevel1).isNotEqualTo(sysMemberLevel2);
    }
}
