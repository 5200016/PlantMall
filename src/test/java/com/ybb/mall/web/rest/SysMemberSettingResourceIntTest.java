package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysMemberSetting;
import com.ybb.mall.repository.SysMemberSettingRepository;
import com.ybb.mall.service.SysMemberSettingService;
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
 * Test class for the SysMemberSettingResource REST controller.
 *
 * @see SysMemberSettingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysMemberSettingResourceIntTest {

    private static final Integer DEFAULT_GROWTH_PROPORTION = 1;
    private static final Integer UPDATED_GROWTH_PROPORTION = 2;

    private static final Integer DEFAULT_REVIEW_GROWTH_VALUE = 1;
    private static final Integer UPDATED_REVIEW_GROWTH_VALUE = 2;

    private static final Integer DEFAULT_CHECK_GROWTH_VALUE = 1;
    private static final Integer UPDATED_CHECK_GROWTH_VALUE = 2;

    private static final Integer DEFAULT_INTEGRAL_PROPORTION = 1;
    private static final Integer UPDATED_INTEGRAL_PROPORTION = 2;

    private static final Integer DEFAULT_REVIEW_INTEGRAL_VALUE = 1;
    private static final Integer UPDATED_REVIEW_INTEGRAL_VALUE = 2;

    private static final Integer DEFAULT_CHECK_INTEGRAL_VALUE = 1;
    private static final Integer UPDATED_CHECK_INTEGRAL_VALUE = 2;

    private static final Integer DEFAULT_INTEGRAL_PROPORTION_CASH = 1;
    private static final Integer UPDATED_INTEGRAL_PROPORTION_CASH = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysMemberSettingRepository sysMemberSettingRepository;

    @Autowired
    private SysMemberSettingService sysMemberSettingService;

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

    private MockMvc restSysMemberSettingMockMvc;

    private SysMemberSetting sysMemberSetting;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysMemberSettingResource sysMemberSettingResource = new SysMemberSettingResource(sysMemberSettingService);
        this.restSysMemberSettingMockMvc = MockMvcBuilders.standaloneSetup(sysMemberSettingResource)
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
    public static SysMemberSetting createEntity(EntityManager em) {
        SysMemberSetting sysMemberSetting = new SysMemberSetting()
            .growthProportion(DEFAULT_GROWTH_PROPORTION)
            .reviewGrowthValue(DEFAULT_REVIEW_GROWTH_VALUE)
            .checkGrowthValue(DEFAULT_CHECK_GROWTH_VALUE)
            .integralProportion(DEFAULT_INTEGRAL_PROPORTION)
            .reviewIntegralValue(DEFAULT_REVIEW_INTEGRAL_VALUE)
            .checkIntegralValue(DEFAULT_CHECK_INTEGRAL_VALUE)
            .integralProportionCash(DEFAULT_INTEGRAL_PROPORTION_CASH)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysMemberSetting;
    }

    @Before
    public void initTest() {
        sysMemberSetting = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysMemberSetting() throws Exception {
        int databaseSizeBeforeCreate = sysMemberSettingRepository.findAll().size();

        // Create the SysMemberSetting
        restSysMemberSettingMockMvc.perform(post("/api/sys-member-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMemberSetting)))
            .andExpect(status().isCreated());

        // Validate the SysMemberSetting in the database
        List<SysMemberSetting> sysMemberSettingList = sysMemberSettingRepository.findAll();
        assertThat(sysMemberSettingList).hasSize(databaseSizeBeforeCreate + 1);
        SysMemberSetting testSysMemberSetting = sysMemberSettingList.get(sysMemberSettingList.size() - 1);
        assertThat(testSysMemberSetting.getGrowthProportion()).isEqualTo(DEFAULT_GROWTH_PROPORTION);
        assertThat(testSysMemberSetting.getReviewGrowthValue()).isEqualTo(DEFAULT_REVIEW_GROWTH_VALUE);
        assertThat(testSysMemberSetting.getCheckGrowthValue()).isEqualTo(DEFAULT_CHECK_GROWTH_VALUE);
        assertThat(testSysMemberSetting.getIntegralProportion()).isEqualTo(DEFAULT_INTEGRAL_PROPORTION);
        assertThat(testSysMemberSetting.getReviewIntegralValue()).isEqualTo(DEFAULT_REVIEW_INTEGRAL_VALUE);
        assertThat(testSysMemberSetting.getCheckIntegralValue()).isEqualTo(DEFAULT_CHECK_INTEGRAL_VALUE);
        assertThat(testSysMemberSetting.getIntegralProportionCash()).isEqualTo(DEFAULT_INTEGRAL_PROPORTION_CASH);
        assertThat(testSysMemberSetting.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysMemberSetting.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysMemberSettingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysMemberSettingRepository.findAll().size();

        // Create the SysMemberSetting with an existing ID
        sysMemberSetting.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysMemberSettingMockMvc.perform(post("/api/sys-member-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMemberSetting)))
            .andExpect(status().isBadRequest());

        // Validate the SysMemberSetting in the database
        List<SysMemberSetting> sysMemberSettingList = sysMemberSettingRepository.findAll();
        assertThat(sysMemberSettingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysMemberSettings() throws Exception {
        // Initialize the database
        sysMemberSettingRepository.saveAndFlush(sysMemberSetting);

        // Get all the sysMemberSettingList
        restSysMemberSettingMockMvc.perform(get("/api/sys-member-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysMemberSetting.getId().intValue())))
            .andExpect(jsonPath("$.[*].growthProportion").value(hasItem(DEFAULT_GROWTH_PROPORTION)))
            .andExpect(jsonPath("$.[*].reviewGrowthValue").value(hasItem(DEFAULT_REVIEW_GROWTH_VALUE)))
            .andExpect(jsonPath("$.[*].checkGrowthValue").value(hasItem(DEFAULT_CHECK_GROWTH_VALUE)))
            .andExpect(jsonPath("$.[*].integralProportion").value(hasItem(DEFAULT_INTEGRAL_PROPORTION)))
            .andExpect(jsonPath("$.[*].reviewIntegralValue").value(hasItem(DEFAULT_REVIEW_INTEGRAL_VALUE)))
            .andExpect(jsonPath("$.[*].checkIntegralValue").value(hasItem(DEFAULT_CHECK_INTEGRAL_VALUE)))
            .andExpect(jsonPath("$.[*].integralProportionCash").value(hasItem(DEFAULT_INTEGRAL_PROPORTION_CASH)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysMemberSetting() throws Exception {
        // Initialize the database
        sysMemberSettingRepository.saveAndFlush(sysMemberSetting);

        // Get the sysMemberSetting
        restSysMemberSettingMockMvc.perform(get("/api/sys-member-settings/{id}", sysMemberSetting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysMemberSetting.getId().intValue()))
            .andExpect(jsonPath("$.growthProportion").value(DEFAULT_GROWTH_PROPORTION))
            .andExpect(jsonPath("$.reviewGrowthValue").value(DEFAULT_REVIEW_GROWTH_VALUE))
            .andExpect(jsonPath("$.checkGrowthValue").value(DEFAULT_CHECK_GROWTH_VALUE))
            .andExpect(jsonPath("$.integralProportion").value(DEFAULT_INTEGRAL_PROPORTION))
            .andExpect(jsonPath("$.reviewIntegralValue").value(DEFAULT_REVIEW_INTEGRAL_VALUE))
            .andExpect(jsonPath("$.checkIntegralValue").value(DEFAULT_CHECK_INTEGRAL_VALUE))
            .andExpect(jsonPath("$.integralProportionCash").value(DEFAULT_INTEGRAL_PROPORTION_CASH))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysMemberSetting() throws Exception {
        // Get the sysMemberSetting
        restSysMemberSettingMockMvc.perform(get("/api/sys-member-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysMemberSetting() throws Exception {
        // Initialize the database
        sysMemberSettingService.save(sysMemberSetting);

        int databaseSizeBeforeUpdate = sysMemberSettingRepository.findAll().size();

        // Update the sysMemberSetting
        SysMemberSetting updatedSysMemberSetting = sysMemberSettingRepository.findById(sysMemberSetting.getId()).get();
        // Disconnect from session so that the updates on updatedSysMemberSetting are not directly saved in db
        em.detach(updatedSysMemberSetting);
        updatedSysMemberSetting
            .growthProportion(UPDATED_GROWTH_PROPORTION)
            .reviewGrowthValue(UPDATED_REVIEW_GROWTH_VALUE)
            .checkGrowthValue(UPDATED_CHECK_GROWTH_VALUE)
            .integralProportion(UPDATED_INTEGRAL_PROPORTION)
            .reviewIntegralValue(UPDATED_REVIEW_INTEGRAL_VALUE)
            .checkIntegralValue(UPDATED_CHECK_INTEGRAL_VALUE)
            .integralProportionCash(UPDATED_INTEGRAL_PROPORTION_CASH)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysMemberSettingMockMvc.perform(put("/api/sys-member-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysMemberSetting)))
            .andExpect(status().isOk());

        // Validate the SysMemberSetting in the database
        List<SysMemberSetting> sysMemberSettingList = sysMemberSettingRepository.findAll();
        assertThat(sysMemberSettingList).hasSize(databaseSizeBeforeUpdate);
        SysMemberSetting testSysMemberSetting = sysMemberSettingList.get(sysMemberSettingList.size() - 1);
        assertThat(testSysMemberSetting.getGrowthProportion()).isEqualTo(UPDATED_GROWTH_PROPORTION);
        assertThat(testSysMemberSetting.getReviewGrowthValue()).isEqualTo(UPDATED_REVIEW_GROWTH_VALUE);
        assertThat(testSysMemberSetting.getCheckGrowthValue()).isEqualTo(UPDATED_CHECK_GROWTH_VALUE);
        assertThat(testSysMemberSetting.getIntegralProportion()).isEqualTo(UPDATED_INTEGRAL_PROPORTION);
        assertThat(testSysMemberSetting.getReviewIntegralValue()).isEqualTo(UPDATED_REVIEW_INTEGRAL_VALUE);
        assertThat(testSysMemberSetting.getCheckIntegralValue()).isEqualTo(UPDATED_CHECK_INTEGRAL_VALUE);
        assertThat(testSysMemberSetting.getIntegralProportionCash()).isEqualTo(UPDATED_INTEGRAL_PROPORTION_CASH);
        assertThat(testSysMemberSetting.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysMemberSetting.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysMemberSetting() throws Exception {
        int databaseSizeBeforeUpdate = sysMemberSettingRepository.findAll().size();

        // Create the SysMemberSetting

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysMemberSettingMockMvc.perform(put("/api/sys-member-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysMemberSetting)))
            .andExpect(status().isBadRequest());

        // Validate the SysMemberSetting in the database
        List<SysMemberSetting> sysMemberSettingList = sysMemberSettingRepository.findAll();
        assertThat(sysMemberSettingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysMemberSetting() throws Exception {
        // Initialize the database
        sysMemberSettingService.save(sysMemberSetting);

        int databaseSizeBeforeDelete = sysMemberSettingRepository.findAll().size();

        // Get the sysMemberSetting
        restSysMemberSettingMockMvc.perform(delete("/api/sys-member-settings/{id}", sysMemberSetting.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysMemberSetting> sysMemberSettingList = sysMemberSettingRepository.findAll();
        assertThat(sysMemberSettingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysMemberSetting.class);
        SysMemberSetting sysMemberSetting1 = new SysMemberSetting();
        sysMemberSetting1.setId(1L);
        SysMemberSetting sysMemberSetting2 = new SysMemberSetting();
        sysMemberSetting2.setId(sysMemberSetting1.getId());
        assertThat(sysMemberSetting1).isEqualTo(sysMemberSetting2);
        sysMemberSetting2.setId(2L);
        assertThat(sysMemberSetting1).isNotEqualTo(sysMemberSetting2);
        sysMemberSetting1.setId(null);
        assertThat(sysMemberSetting1).isNotEqualTo(sysMemberSetting2);
    }
}
