package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysCouponClassify;
import com.ybb.mall.repository.SysCouponClassifyRepository;
import com.ybb.mall.service.SysCouponClassifyService;
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
 * Test class for the SysCouponClassifyResource REST controller.
 *
 * @see SysCouponClassifyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysCouponClassifyResourceIntTest {

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysCouponClassifyRepository sysCouponClassifyRepository;

    @Autowired
    private SysCouponClassifyService sysCouponClassifyService;

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

    private MockMvc restSysCouponClassifyMockMvc;

    private SysCouponClassify sysCouponClassify;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysCouponClassifyResource sysCouponClassifyResource = new SysCouponClassifyResource(sysCouponClassifyService);
        this.restSysCouponClassifyMockMvc = MockMvcBuilders.standaloneSetup(sysCouponClassifyResource)
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
    public static SysCouponClassify createEntity(EntityManager em) {
        SysCouponClassify sysCouponClassify = new SysCouponClassify()
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysCouponClassify;
    }

    @Before
    public void initTest() {
        sysCouponClassify = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysCouponClassify() throws Exception {
        int databaseSizeBeforeCreate = sysCouponClassifyRepository.findAll().size();

        // Create the SysCouponClassify
        restSysCouponClassifyMockMvc.perform(post("/api/sys-coupon-classifies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCouponClassify)))
            .andExpect(status().isCreated());

        // Validate the SysCouponClassify in the database
        List<SysCouponClassify> sysCouponClassifyList = sysCouponClassifyRepository.findAll();
        assertThat(sysCouponClassifyList).hasSize(databaseSizeBeforeCreate + 1);
        SysCouponClassify testSysCouponClassify = sysCouponClassifyList.get(sysCouponClassifyList.size() - 1);
        assertThat(testSysCouponClassify.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysCouponClassify.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysCouponClassifyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysCouponClassifyRepository.findAll().size();

        // Create the SysCouponClassify with an existing ID
        sysCouponClassify.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCouponClassifyMockMvc.perform(post("/api/sys-coupon-classifies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCouponClassify)))
            .andExpect(status().isBadRequest());

        // Validate the SysCouponClassify in the database
        List<SysCouponClassify> sysCouponClassifyList = sysCouponClassifyRepository.findAll();
        assertThat(sysCouponClassifyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysCouponClassifies() throws Exception {
        // Initialize the database
        sysCouponClassifyRepository.saveAndFlush(sysCouponClassify);

        // Get all the sysCouponClassifyList
        restSysCouponClassifyMockMvc.perform(get("/api/sys-coupon-classifies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCouponClassify.getId().intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysCouponClassify() throws Exception {
        // Initialize the database
        sysCouponClassifyRepository.saveAndFlush(sysCouponClassify);

        // Get the sysCouponClassify
        restSysCouponClassifyMockMvc.perform(get("/api/sys-coupon-classifies/{id}", sysCouponClassify.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysCouponClassify.getId().intValue()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysCouponClassify() throws Exception {
        // Get the sysCouponClassify
        restSysCouponClassifyMockMvc.perform(get("/api/sys-coupon-classifies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysCouponClassify() throws Exception {
        // Initialize the database
        sysCouponClassifyService.save(sysCouponClassify);

        int databaseSizeBeforeUpdate = sysCouponClassifyRepository.findAll().size();

        // Update the sysCouponClassify
        SysCouponClassify updatedSysCouponClassify = sysCouponClassifyRepository.findById(sysCouponClassify.getId()).get();
        // Disconnect from session so that the updates on updatedSysCouponClassify are not directly saved in db
        em.detach(updatedSysCouponClassify);
        updatedSysCouponClassify
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysCouponClassifyMockMvc.perform(put("/api/sys-coupon-classifies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysCouponClassify)))
            .andExpect(status().isOk());

        // Validate the SysCouponClassify in the database
        List<SysCouponClassify> sysCouponClassifyList = sysCouponClassifyRepository.findAll();
        assertThat(sysCouponClassifyList).hasSize(databaseSizeBeforeUpdate);
        SysCouponClassify testSysCouponClassify = sysCouponClassifyList.get(sysCouponClassifyList.size() - 1);
        assertThat(testSysCouponClassify.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysCouponClassify.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysCouponClassify() throws Exception {
        int databaseSizeBeforeUpdate = sysCouponClassifyRepository.findAll().size();

        // Create the SysCouponClassify

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCouponClassifyMockMvc.perform(put("/api/sys-coupon-classifies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCouponClassify)))
            .andExpect(status().isBadRequest());

        // Validate the SysCouponClassify in the database
        List<SysCouponClassify> sysCouponClassifyList = sysCouponClassifyRepository.findAll();
        assertThat(sysCouponClassifyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysCouponClassify() throws Exception {
        // Initialize the database
        sysCouponClassifyService.save(sysCouponClassify);

        int databaseSizeBeforeDelete = sysCouponClassifyRepository.findAll().size();

        // Get the sysCouponClassify
        restSysCouponClassifyMockMvc.perform(delete("/api/sys-coupon-classifies/{id}", sysCouponClassify.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysCouponClassify> sysCouponClassifyList = sysCouponClassifyRepository.findAll();
        assertThat(sysCouponClassifyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCouponClassify.class);
        SysCouponClassify sysCouponClassify1 = new SysCouponClassify();
        sysCouponClassify1.setId(1L);
        SysCouponClassify sysCouponClassify2 = new SysCouponClassify();
        sysCouponClassify2.setId(sysCouponClassify1.getId());
        assertThat(sysCouponClassify1).isEqualTo(sysCouponClassify2);
        sysCouponClassify2.setId(2L);
        assertThat(sysCouponClassify1).isNotEqualTo(sysCouponClassify2);
        sysCouponClassify1.setId(null);
        assertThat(sysCouponClassify1).isNotEqualTo(sysCouponClassify2);
    }
}
