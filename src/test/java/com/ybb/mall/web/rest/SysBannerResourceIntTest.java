package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysBanner;
import com.ybb.mall.repository.SysBannerRepository;
import com.ybb.mall.service.SysBannerService;
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
 * Test class for the SysBannerResource REST controller.
 *
 * @see SysBannerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysBannerResourceIntTest {

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT = 1;
    private static final Integer UPDATED_SORT = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysBannerRepository sysBannerRepository;

    @Autowired
    private SysBannerService sysBannerService;

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

    private MockMvc restSysBannerMockMvc;

    private SysBanner sysBanner;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysBannerResource sysBannerResource = new SysBannerResource(sysBannerService);
        this.restSysBannerMockMvc = MockMvcBuilders.standaloneSetup(sysBannerResource)
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
    public static SysBanner createEntity(EntityManager em) {
        SysBanner sysBanner = new SysBanner()
            .image(DEFAULT_IMAGE)
            .link(DEFAULT_LINK)
            .sort(DEFAULT_SORT)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysBanner;
    }

    @Before
    public void initTest() {
        sysBanner = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysBanner() throws Exception {
        int databaseSizeBeforeCreate = sysBannerRepository.findAll().size();

        // Create the SysBanner
        restSysBannerMockMvc.perform(post("/api/sys-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysBanner)))
            .andExpect(status().isCreated());

        // Validate the SysBanner in the database
        List<SysBanner> sysBannerList = sysBannerRepository.findAll();
        assertThat(sysBannerList).hasSize(databaseSizeBeforeCreate + 1);
        SysBanner testSysBanner = sysBannerList.get(sysBannerList.size() - 1);
        assertThat(testSysBanner.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testSysBanner.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testSysBanner.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testSysBanner.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysBanner.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysBannerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysBannerRepository.findAll().size();

        // Create the SysBanner with an existing ID
        sysBanner.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysBannerMockMvc.perform(post("/api/sys-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysBanner)))
            .andExpect(status().isBadRequest());

        // Validate the SysBanner in the database
        List<SysBanner> sysBannerList = sysBannerRepository.findAll();
        assertThat(sysBannerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysBanners() throws Exception {
        // Initialize the database
        sysBannerRepository.saveAndFlush(sysBanner);

        // Get all the sysBannerList
        restSysBannerMockMvc.perform(get("/api/sys-banners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysBanner.getId().intValue())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK.toString())))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysBanner() throws Exception {
        // Initialize the database
        sysBannerRepository.saveAndFlush(sysBanner);

        // Get the sysBanner
        restSysBannerMockMvc.perform(get("/api/sys-banners/{id}", sysBanner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysBanner.getId().intValue()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK.toString()))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysBanner() throws Exception {
        // Get the sysBanner
        restSysBannerMockMvc.perform(get("/api/sys-banners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysBanner() throws Exception {
        // Initialize the database
        sysBannerService.save(sysBanner);

        int databaseSizeBeforeUpdate = sysBannerRepository.findAll().size();

        // Update the sysBanner
        SysBanner updatedSysBanner = sysBannerRepository.findById(sysBanner.getId()).get();
        // Disconnect from session so that the updates on updatedSysBanner are not directly saved in db
        em.detach(updatedSysBanner);
        updatedSysBanner
            .image(UPDATED_IMAGE)
            .link(UPDATED_LINK)
            .sort(UPDATED_SORT)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysBannerMockMvc.perform(put("/api/sys-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysBanner)))
            .andExpect(status().isOk());

        // Validate the SysBanner in the database
        List<SysBanner> sysBannerList = sysBannerRepository.findAll();
        assertThat(sysBannerList).hasSize(databaseSizeBeforeUpdate);
        SysBanner testSysBanner = sysBannerList.get(sysBannerList.size() - 1);
        assertThat(testSysBanner.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testSysBanner.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testSysBanner.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testSysBanner.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysBanner.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysBanner() throws Exception {
        int databaseSizeBeforeUpdate = sysBannerRepository.findAll().size();

        // Create the SysBanner

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysBannerMockMvc.perform(put("/api/sys-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysBanner)))
            .andExpect(status().isBadRequest());

        // Validate the SysBanner in the database
        List<SysBanner> sysBannerList = sysBannerRepository.findAll();
        assertThat(sysBannerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysBanner() throws Exception {
        // Initialize the database
        sysBannerService.save(sysBanner);

        int databaseSizeBeforeDelete = sysBannerRepository.findAll().size();

        // Get the sysBanner
        restSysBannerMockMvc.perform(delete("/api/sys-banners/{id}", sysBanner.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysBanner> sysBannerList = sysBannerRepository.findAll();
        assertThat(sysBannerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysBanner.class);
        SysBanner sysBanner1 = new SysBanner();
        sysBanner1.setId(1L);
        SysBanner sysBanner2 = new SysBanner();
        sysBanner2.setId(sysBanner1.getId());
        assertThat(sysBanner1).isEqualTo(sysBanner2);
        sysBanner2.setId(2L);
        assertThat(sysBanner1).isNotEqualTo(sysBanner2);
        sysBanner1.setId(null);
        assertThat(sysBanner1).isNotEqualTo(sysBanner2);
    }
}
