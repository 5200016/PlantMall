package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysReview;
import com.ybb.mall.repository.SysReviewRepository;
import com.ybb.mall.service.SysReviewService;
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
 * Test class for the SysReviewResource REST controller.
 *
 * @see SysReviewResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysReviewResourceIntTest {

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysReviewRepository sysReviewRepository;

    @Autowired
    private SysReviewService sysReviewService;

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

    private MockMvc restSysReviewMockMvc;

    private SysReview sysReview;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysReviewResource sysReviewResource = new SysReviewResource(sysReviewService);
        this.restSysReviewMockMvc = MockMvcBuilders.standaloneSetup(sysReviewResource)
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
    public static SysReview createEntity(EntityManager em) {
        SysReview sysReview = new SysReview()
            .content(DEFAULT_CONTENT)
            .level(DEFAULT_LEVEL)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysReview;
    }

    @Before
    public void initTest() {
        sysReview = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysReview() throws Exception {
        int databaseSizeBeforeCreate = sysReviewRepository.findAll().size();

        // Create the SysReview
        restSysReviewMockMvc.perform(post("/api/sys-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysReview)))
            .andExpect(status().isCreated());

        // Validate the SysReview in the database
        List<SysReview> sysReviewList = sysReviewRepository.findAll();
        assertThat(sysReviewList).hasSize(databaseSizeBeforeCreate + 1);
        SysReview testSysReview = sysReviewList.get(sysReviewList.size() - 1);
        assertThat(testSysReview.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testSysReview.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testSysReview.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysReview.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysReviewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysReviewRepository.findAll().size();

        // Create the SysReview with an existing ID
        sysReview.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysReviewMockMvc.perform(post("/api/sys-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysReview)))
            .andExpect(status().isBadRequest());

        // Validate the SysReview in the database
        List<SysReview> sysReviewList = sysReviewRepository.findAll();
        assertThat(sysReviewList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysReviews() throws Exception {
        // Initialize the database
        sysReviewRepository.saveAndFlush(sysReview);

        // Get all the sysReviewList
        restSysReviewMockMvc.perform(get("/api/sys-reviews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysReview.getId().intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysReview() throws Exception {
        // Initialize the database
        sysReviewRepository.saveAndFlush(sysReview);

        // Get the sysReview
        restSysReviewMockMvc.perform(get("/api/sys-reviews/{id}", sysReview.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysReview.getId().intValue()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysReview() throws Exception {
        // Get the sysReview
        restSysReviewMockMvc.perform(get("/api/sys-reviews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysReview() throws Exception {
        // Initialize the database
        sysReviewService.save(sysReview);

        int databaseSizeBeforeUpdate = sysReviewRepository.findAll().size();

        // Update the sysReview
        SysReview updatedSysReview = sysReviewRepository.findById(sysReview.getId()).get();
        // Disconnect from session so that the updates on updatedSysReview are not directly saved in db
        em.detach(updatedSysReview);
        updatedSysReview
            .content(UPDATED_CONTENT)
            .level(UPDATED_LEVEL)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysReviewMockMvc.perform(put("/api/sys-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysReview)))
            .andExpect(status().isOk());

        // Validate the SysReview in the database
        List<SysReview> sysReviewList = sysReviewRepository.findAll();
        assertThat(sysReviewList).hasSize(databaseSizeBeforeUpdate);
        SysReview testSysReview = sysReviewList.get(sysReviewList.size() - 1);
        assertThat(testSysReview.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testSysReview.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testSysReview.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysReview.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysReview() throws Exception {
        int databaseSizeBeforeUpdate = sysReviewRepository.findAll().size();

        // Create the SysReview

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysReviewMockMvc.perform(put("/api/sys-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysReview)))
            .andExpect(status().isBadRequest());

        // Validate the SysReview in the database
        List<SysReview> sysReviewList = sysReviewRepository.findAll();
        assertThat(sysReviewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysReview() throws Exception {
        // Initialize the database
        sysReviewService.save(sysReview);

        int databaseSizeBeforeDelete = sysReviewRepository.findAll().size();

        // Get the sysReview
        restSysReviewMockMvc.perform(delete("/api/sys-reviews/{id}", sysReview.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysReview> sysReviewList = sysReviewRepository.findAll();
        assertThat(sysReviewList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysReview.class);
        SysReview sysReview1 = new SysReview();
        sysReview1.setId(1L);
        SysReview sysReview2 = new SysReview();
        sysReview2.setId(sysReview1.getId());
        assertThat(sysReview1).isEqualTo(sysReview2);
        sysReview2.setId(2L);
        assertThat(sysReview1).isNotEqualTo(sysReview2);
        sysReview1.setId(null);
        assertThat(sysReview1).isNotEqualTo(sysReview2);
    }
}
