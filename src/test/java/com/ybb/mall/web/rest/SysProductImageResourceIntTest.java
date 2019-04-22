package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysProductImage;
import com.ybb.mall.repository.SysProductImageRepository;
import com.ybb.mall.service.SysProductImageService;
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
 * Test class for the SysProductImageResource REST controller.
 *
 * @see SysProductImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysProductImageResourceIntTest {

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysProductImageRepository sysProductImageRepository;

    @Autowired
    private SysProductImageService sysProductImageService;

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

    private MockMvc restSysProductImageMockMvc;

    private SysProductImage sysProductImage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysProductImageResource sysProductImageResource = new SysProductImageResource(sysProductImageService);
        this.restSysProductImageMockMvc = MockMvcBuilders.standaloneSetup(sysProductImageResource)
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
    public static SysProductImage createEntity(EntityManager em) {
        SysProductImage sysProductImage = new SysProductImage()
            .url(DEFAULT_URL)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysProductImage;
    }

    @Before
    public void initTest() {
        sysProductImage = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysProductImage() throws Exception {
        int databaseSizeBeforeCreate = sysProductImageRepository.findAll().size();

        // Create the SysProductImage
        restSysProductImageMockMvc.perform(post("/api/sys-product-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysProductImage)))
            .andExpect(status().isCreated());

        // Validate the SysProductImage in the database
        List<SysProductImage> sysProductImageList = sysProductImageRepository.findAll();
        assertThat(sysProductImageList).hasSize(databaseSizeBeforeCreate + 1);
        SysProductImage testSysProductImage = sysProductImageList.get(sysProductImageList.size() - 1);
        assertThat(testSysProductImage.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testSysProductImage.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysProductImage.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysProductImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysProductImageRepository.findAll().size();

        // Create the SysProductImage with an existing ID
        sysProductImage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysProductImageMockMvc.perform(post("/api/sys-product-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysProductImage)))
            .andExpect(status().isBadRequest());

        // Validate the SysProductImage in the database
        List<SysProductImage> sysProductImageList = sysProductImageRepository.findAll();
        assertThat(sysProductImageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysProductImages() throws Exception {
        // Initialize the database
        sysProductImageRepository.saveAndFlush(sysProductImage);

        // Get all the sysProductImageList
        restSysProductImageMockMvc.perform(get("/api/sys-product-images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysProductImage.getId().intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysProductImage() throws Exception {
        // Initialize the database
        sysProductImageRepository.saveAndFlush(sysProductImage);

        // Get the sysProductImage
        restSysProductImageMockMvc.perform(get("/api/sys-product-images/{id}", sysProductImage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysProductImage.getId().intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysProductImage() throws Exception {
        // Get the sysProductImage
        restSysProductImageMockMvc.perform(get("/api/sys-product-images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysProductImage() throws Exception {
        // Initialize the database
        sysProductImageService.save(sysProductImage);

        int databaseSizeBeforeUpdate = sysProductImageRepository.findAll().size();

        // Update the sysProductImage
        SysProductImage updatedSysProductImage = sysProductImageRepository.findById(sysProductImage.getId()).get();
        // Disconnect from session so that the updates on updatedSysProductImage are not directly saved in db
        em.detach(updatedSysProductImage);
        updatedSysProductImage
            .url(UPDATED_URL)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysProductImageMockMvc.perform(put("/api/sys-product-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysProductImage)))
            .andExpect(status().isOk());

        // Validate the SysProductImage in the database
        List<SysProductImage> sysProductImageList = sysProductImageRepository.findAll();
        assertThat(sysProductImageList).hasSize(databaseSizeBeforeUpdate);
        SysProductImage testSysProductImage = sysProductImageList.get(sysProductImageList.size() - 1);
        assertThat(testSysProductImage.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testSysProductImage.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysProductImage.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysProductImage() throws Exception {
        int databaseSizeBeforeUpdate = sysProductImageRepository.findAll().size();

        // Create the SysProductImage

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysProductImageMockMvc.perform(put("/api/sys-product-images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysProductImage)))
            .andExpect(status().isBadRequest());

        // Validate the SysProductImage in the database
        List<SysProductImage> sysProductImageList = sysProductImageRepository.findAll();
        assertThat(sysProductImageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysProductImage() throws Exception {
        // Initialize the database
        sysProductImageService.save(sysProductImage);

        int databaseSizeBeforeDelete = sysProductImageRepository.findAll().size();

        // Get the sysProductImage
        restSysProductImageMockMvc.perform(delete("/api/sys-product-images/{id}", sysProductImage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysProductImage> sysProductImageList = sysProductImageRepository.findAll();
        assertThat(sysProductImageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysProductImage.class);
        SysProductImage sysProductImage1 = new SysProductImage();
        sysProductImage1.setId(1L);
        SysProductImage sysProductImage2 = new SysProductImage();
        sysProductImage2.setId(sysProductImage1.getId());
        assertThat(sysProductImage1).isEqualTo(sysProductImage2);
        sysProductImage2.setId(2L);
        assertThat(sysProductImage1).isNotEqualTo(sysProductImage2);
        sysProductImage1.setId(null);
        assertThat(sysProductImage1).isNotEqualTo(sysProductImage2);
    }
}
