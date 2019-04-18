package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysRecharge;
import com.ybb.mall.repository.SysRechargeRepository;
import com.ybb.mall.service.SysRechargeService;
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
 * Test class for the SysRechargeResource REST controller.
 *
 * @see SysRechargeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysRechargeResourceIntTest {

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysRechargeRepository sysRechargeRepository;

    @Autowired
    private SysRechargeService sysRechargeService;

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

    private MockMvc restSysRechargeMockMvc;

    private SysRecharge sysRecharge;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysRechargeResource sysRechargeResource = new SysRechargeResource(sysRechargeService);
        this.restSysRechargeMockMvc = MockMvcBuilders.standaloneSetup(sysRechargeResource)
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
    public static SysRecharge createEntity(EntityManager em) {
        SysRecharge sysRecharge = new SysRecharge()
            .price(DEFAULT_PRICE)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysRecharge;
    }

    @Before
    public void initTest() {
        sysRecharge = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysRecharge() throws Exception {
        int databaseSizeBeforeCreate = sysRechargeRepository.findAll().size();

        // Create the SysRecharge
        restSysRechargeMockMvc.perform(post("/api/sys-recharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRecharge)))
            .andExpect(status().isCreated());

        // Validate the SysRecharge in the database
        List<SysRecharge> sysRechargeList = sysRechargeRepository.findAll();
        assertThat(sysRechargeList).hasSize(databaseSizeBeforeCreate + 1);
        SysRecharge testSysRecharge = sysRechargeList.get(sysRechargeList.size() - 1);
        assertThat(testSysRecharge.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testSysRecharge.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysRecharge.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysRechargeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysRechargeRepository.findAll().size();

        // Create the SysRecharge with an existing ID
        sysRecharge.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRechargeMockMvc.perform(post("/api/sys-recharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRecharge)))
            .andExpect(status().isBadRequest());

        // Validate the SysRecharge in the database
        List<SysRecharge> sysRechargeList = sysRechargeRepository.findAll();
        assertThat(sysRechargeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysRecharges() throws Exception {
        // Initialize the database
        sysRechargeRepository.saveAndFlush(sysRecharge);

        // Get all the sysRechargeList
        restSysRechargeMockMvc.perform(get("/api/sys-recharges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRecharge.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysRecharge() throws Exception {
        // Initialize the database
        sysRechargeRepository.saveAndFlush(sysRecharge);

        // Get the sysRecharge
        restSysRechargeMockMvc.perform(get("/api/sys-recharges/{id}", sysRecharge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysRecharge.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysRecharge() throws Exception {
        // Get the sysRecharge
        restSysRechargeMockMvc.perform(get("/api/sys-recharges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysRecharge() throws Exception {
        // Initialize the database
        sysRechargeService.save(sysRecharge);

        int databaseSizeBeforeUpdate = sysRechargeRepository.findAll().size();

        // Update the sysRecharge
        SysRecharge updatedSysRecharge = sysRechargeRepository.findById(sysRecharge.getId()).get();
        // Disconnect from session so that the updates on updatedSysRecharge are not directly saved in db
        em.detach(updatedSysRecharge);
        updatedSysRecharge
            .price(UPDATED_PRICE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysRechargeMockMvc.perform(put("/api/sys-recharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysRecharge)))
            .andExpect(status().isOk());

        // Validate the SysRecharge in the database
        List<SysRecharge> sysRechargeList = sysRechargeRepository.findAll();
        assertThat(sysRechargeList).hasSize(databaseSizeBeforeUpdate);
        SysRecharge testSysRecharge = sysRechargeList.get(sysRechargeList.size() - 1);
        assertThat(testSysRecharge.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testSysRecharge.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysRecharge.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysRecharge() throws Exception {
        int databaseSizeBeforeUpdate = sysRechargeRepository.findAll().size();

        // Create the SysRecharge

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRechargeMockMvc.perform(put("/api/sys-recharges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRecharge)))
            .andExpect(status().isBadRequest());

        // Validate the SysRecharge in the database
        List<SysRecharge> sysRechargeList = sysRechargeRepository.findAll();
        assertThat(sysRechargeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysRecharge() throws Exception {
        // Initialize the database
        sysRechargeService.save(sysRecharge);

        int databaseSizeBeforeDelete = sysRechargeRepository.findAll().size();

        // Get the sysRecharge
        restSysRechargeMockMvc.perform(delete("/api/sys-recharges/{id}", sysRecharge.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysRecharge> sysRechargeList = sysRechargeRepository.findAll();
        assertThat(sysRechargeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRecharge.class);
        SysRecharge sysRecharge1 = new SysRecharge();
        sysRecharge1.setId(1L);
        SysRecharge sysRecharge2 = new SysRecharge();
        sysRecharge2.setId(sysRecharge1.getId());
        assertThat(sysRecharge1).isEqualTo(sysRecharge2);
        sysRecharge2.setId(2L);
        assertThat(sysRecharge1).isNotEqualTo(sysRecharge2);
        sysRecharge1.setId(null);
        assertThat(sysRecharge1).isNotEqualTo(sysRecharge2);
    }
}
