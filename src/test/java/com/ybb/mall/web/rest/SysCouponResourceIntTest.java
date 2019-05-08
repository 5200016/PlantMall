package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysCoupon;
import com.ybb.mall.repository.SysCouponRepository;
import com.ybb.mall.service.SysCouponService;
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
import java.math.BigDecimal;
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
 * Test class for the SysCouponResource REST controller.
 *
 * @see SysCouponResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysCouponResourceIntTest {

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALUE = new BigDecimal(2);

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Integer DEFAULT_GET_NUMBER = 1;
    private static final Integer UPDATED_GET_NUMBER = 2;

    private static final Integer DEFAULT_LIMIT = 1;
    private static final Integer UPDATED_LIMIT = 2;

    private static final ZonedDateTime DEFAULT_START_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_END_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MONEY_OFF = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONEY_OFF = new BigDecimal(2);

    private static final Integer DEFAULT_RANGE = 1;
    private static final Integer UPDATED_RANGE = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysCouponRepository sysCouponRepository;

    @Autowired
    private SysCouponService sysCouponService;

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

    private MockMvc restSysCouponMockMvc;

    private SysCoupon sysCoupon;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysCouponResource sysCouponResource = new SysCouponResource(sysCouponService);
        this.restSysCouponMockMvc = MockMvcBuilders.standaloneSetup(sysCouponResource)
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
    public static SysCoupon createEntity(EntityManager em) {
        SysCoupon sysCoupon = new SysCoupon()
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .quantity(DEFAULT_QUANTITY)
            .getNumber(DEFAULT_GET_NUMBER)
            .limit(DEFAULT_LIMIT)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .description(DEFAULT_DESCRIPTION)
            .moneyOff(DEFAULT_MONEY_OFF)
            .range(DEFAULT_RANGE)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysCoupon;
    }

    @Before
    public void initTest() {
        sysCoupon = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysCoupon() throws Exception {
        int databaseSizeBeforeCreate = sysCouponRepository.findAll().size();

        // Create the SysCoupon
        restSysCouponMockMvc.perform(post("/api/sys-coupons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCoupon)))
            .andExpect(status().isCreated());

        // Validate the SysCoupon in the database
        List<SysCoupon> sysCouponList = sysCouponRepository.findAll();
        assertThat(sysCouponList).hasSize(databaseSizeBeforeCreate + 1);
        SysCoupon testSysCoupon = sysCouponList.get(sysCouponList.size() - 1);
        assertThat(testSysCoupon.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSysCoupon.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysCoupon.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testSysCoupon.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testSysCoupon.getGetNumber()).isEqualTo(DEFAULT_GET_NUMBER);
        assertThat(testSysCoupon.getLimit()).isEqualTo(DEFAULT_LIMIT);
        assertThat(testSysCoupon.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testSysCoupon.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testSysCoupon.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysCoupon.getMoneyOff()).isEqualTo(DEFAULT_MONEY_OFF);
        assertThat(testSysCoupon.getRange()).isEqualTo(DEFAULT_RANGE);
        assertThat(testSysCoupon.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysCoupon.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysCouponWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysCouponRepository.findAll().size();

        // Create the SysCoupon with an existing ID
        sysCoupon.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCouponMockMvc.perform(post("/api/sys-coupons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCoupon)))
            .andExpect(status().isBadRequest());

        // Validate the SysCoupon in the database
        List<SysCoupon> sysCouponList = sysCouponRepository.findAll();
        assertThat(sysCouponList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysCoupons() throws Exception {
        // Initialize the database
        sysCouponRepository.saveAndFlush(sysCoupon);

        // Get all the sysCouponList
        restSysCouponMockMvc.perform(get("/api/sys-coupons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCoupon.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].getNumber").value(hasItem(DEFAULT_GET_NUMBER)))
            .andExpect(jsonPath("$.[*].limit").value(hasItem(DEFAULT_LIMIT)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(sameInstant(DEFAULT_START_TIME))))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(sameInstant(DEFAULT_END_TIME))))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].moneyOff").value(hasItem(DEFAULT_MONEY_OFF.intValue())))
            .andExpect(jsonPath("$.[*].range").value(hasItem(DEFAULT_RANGE)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysCoupon() throws Exception {
        // Initialize the database
        sysCouponRepository.saveAndFlush(sysCoupon);

        // Get the sysCoupon
        restSysCouponMockMvc.perform(get("/api/sys-coupons/{id}", sysCoupon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysCoupon.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.getNumber").value(DEFAULT_GET_NUMBER))
            .andExpect(jsonPath("$.limit").value(DEFAULT_LIMIT))
            .andExpect(jsonPath("$.startTime").value(sameInstant(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.endTime").value(sameInstant(DEFAULT_END_TIME)))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.moneyOff").value(DEFAULT_MONEY_OFF.intValue()))
            .andExpect(jsonPath("$.range").value(DEFAULT_RANGE))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysCoupon() throws Exception {
        // Get the sysCoupon
        restSysCouponMockMvc.perform(get("/api/sys-coupons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysCoupon() throws Exception {
        // Initialize the database
        sysCouponService.save(sysCoupon);

        int databaseSizeBeforeUpdate = sysCouponRepository.findAll().size();

        // Update the sysCoupon
        SysCoupon updatedSysCoupon = sysCouponRepository.findById(sysCoupon.getId()).get();
        // Disconnect from session so that the updates on updatedSysCoupon are not directly saved in db
        em.detach(updatedSysCoupon);
        updatedSysCoupon
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .quantity(UPDATED_QUANTITY)
            .getNumber(UPDATED_GET_NUMBER)
            .limit(UPDATED_LIMIT)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .description(UPDATED_DESCRIPTION)
            .moneyOff(UPDATED_MONEY_OFF)
            .range(UPDATED_RANGE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysCouponMockMvc.perform(put("/api/sys-coupons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysCoupon)))
            .andExpect(status().isOk());

        // Validate the SysCoupon in the database
        List<SysCoupon> sysCouponList = sysCouponRepository.findAll();
        assertThat(sysCouponList).hasSize(databaseSizeBeforeUpdate);
        SysCoupon testSysCoupon = sysCouponList.get(sysCouponList.size() - 1);
        assertThat(testSysCoupon.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSysCoupon.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysCoupon.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSysCoupon.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testSysCoupon.getGetNumber()).isEqualTo(UPDATED_GET_NUMBER);
        assertThat(testSysCoupon.getLimit()).isEqualTo(UPDATED_LIMIT);
        assertThat(testSysCoupon.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testSysCoupon.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testSysCoupon.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysCoupon.getMoneyOff()).isEqualTo(UPDATED_MONEY_OFF);
        assertThat(testSysCoupon.getRange()).isEqualTo(UPDATED_RANGE);
        assertThat(testSysCoupon.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysCoupon.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysCoupon() throws Exception {
        int databaseSizeBeforeUpdate = sysCouponRepository.findAll().size();

        // Create the SysCoupon

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCouponMockMvc.perform(put("/api/sys-coupons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCoupon)))
            .andExpect(status().isBadRequest());

        // Validate the SysCoupon in the database
        List<SysCoupon> sysCouponList = sysCouponRepository.findAll();
        assertThat(sysCouponList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysCoupon() throws Exception {
        // Initialize the database
        sysCouponService.save(sysCoupon);

        int databaseSizeBeforeDelete = sysCouponRepository.findAll().size();

        // Get the sysCoupon
        restSysCouponMockMvc.perform(delete("/api/sys-coupons/{id}", sysCoupon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysCoupon> sysCouponList = sysCouponRepository.findAll();
        assertThat(sysCouponList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCoupon.class);
        SysCoupon sysCoupon1 = new SysCoupon();
        sysCoupon1.setId(1L);
        SysCoupon sysCoupon2 = new SysCoupon();
        sysCoupon2.setId(sysCoupon1.getId());
        assertThat(sysCoupon1).isEqualTo(sysCoupon2);
        sysCoupon2.setId(2L);
        assertThat(sysCoupon1).isNotEqualTo(sysCoupon2);
        sysCoupon1.setId(null);
        assertThat(sysCoupon1).isNotEqualTo(sysCoupon2);
    }
}
