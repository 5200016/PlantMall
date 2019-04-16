package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.repository.SysClassifyRepository;
import com.ybb.mall.service.SysClassifyService;
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
 * Test class for the SysClassifyResource REST controller.
 *
 * @see SysClassifyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysClassifyResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysClassifyRepository sysClassifyRepository;

    @Autowired
    private SysClassifyService sysClassifyService;

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

    private MockMvc restSysClassifyMockMvc;

    private SysClassify sysClassify;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysClassifyResource sysClassifyResource = new SysClassifyResource(sysClassifyService);
        this.restSysClassifyMockMvc = MockMvcBuilders.standaloneSetup(sysClassifyResource)
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
    public static SysClassify createEntity(EntityManager em) {
        SysClassify sysClassify = new SysClassify()
            .name(DEFAULT_NAME)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysClassify;
    }

    @Before
    public void initTest() {
        sysClassify = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysClassify() throws Exception {
        int databaseSizeBeforeCreate = sysClassifyRepository.findAll().size();

        // Create the SysClassify
        restSysClassifyMockMvc.perform(post("/api/sys-classifies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysClassify)))
            .andExpect(status().isCreated());

        // Validate the SysClassify in the database
        List<SysClassify> sysClassifyList = sysClassifyRepository.findAll();
        assertThat(sysClassifyList).hasSize(databaseSizeBeforeCreate + 1);
        SysClassify testSysClassify = sysClassifyList.get(sysClassifyList.size() - 1);
        assertThat(testSysClassify.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysClassify.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysClassify.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysClassifyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysClassifyRepository.findAll().size();

        // Create the SysClassify with an existing ID
        sysClassify.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysClassifyMockMvc.perform(post("/api/sys-classifies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysClassify)))
            .andExpect(status().isBadRequest());

        // Validate the SysClassify in the database
        List<SysClassify> sysClassifyList = sysClassifyRepository.findAll();
        assertThat(sysClassifyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysClassifies() throws Exception {
        // Initialize the database
        sysClassifyRepository.saveAndFlush(sysClassify);

        // Get all the sysClassifyList
        restSysClassifyMockMvc.perform(get("/api/sys-classifies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysClassify.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysClassify() throws Exception {
        // Initialize the database
        sysClassifyRepository.saveAndFlush(sysClassify);

        // Get the sysClassify
        restSysClassifyMockMvc.perform(get("/api/sys-classifies/{id}", sysClassify.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysClassify.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysClassify() throws Exception {
        // Get the sysClassify
        restSysClassifyMockMvc.perform(get("/api/sys-classifies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysClassify() throws Exception {
        // Initialize the database
        sysClassifyService.save(sysClassify);

        int databaseSizeBeforeUpdate = sysClassifyRepository.findAll().size();

        // Update the sysClassify
        SysClassify updatedSysClassify = sysClassifyRepository.findById(sysClassify.getId()).get();
        // Disconnect from session so that the updates on updatedSysClassify are not directly saved in db
        em.detach(updatedSysClassify);
        updatedSysClassify
            .name(UPDATED_NAME)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysClassifyMockMvc.perform(put("/api/sys-classifies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysClassify)))
            .andExpect(status().isOk());

        // Validate the SysClassify in the database
        List<SysClassify> sysClassifyList = sysClassifyRepository.findAll();
        assertThat(sysClassifyList).hasSize(databaseSizeBeforeUpdate);
        SysClassify testSysClassify = sysClassifyList.get(sysClassifyList.size() - 1);
        assertThat(testSysClassify.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysClassify.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysClassify.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysClassify() throws Exception {
        int databaseSizeBeforeUpdate = sysClassifyRepository.findAll().size();

        // Create the SysClassify

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysClassifyMockMvc.perform(put("/api/sys-classifies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysClassify)))
            .andExpect(status().isBadRequest());

        // Validate the SysClassify in the database
        List<SysClassify> sysClassifyList = sysClassifyRepository.findAll();
        assertThat(sysClassifyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysClassify() throws Exception {
        // Initialize the database
        sysClassifyService.save(sysClassify);

        int databaseSizeBeforeDelete = sysClassifyRepository.findAll().size();

        // Get the sysClassify
        restSysClassifyMockMvc.perform(delete("/api/sys-classifies/{id}", sysClassify.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysClassify> sysClassifyList = sysClassifyRepository.findAll();
        assertThat(sysClassifyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysClassify.class);
        SysClassify sysClassify1 = new SysClassify();
        sysClassify1.setId(1L);
        SysClassify sysClassify2 = new SysClassify();
        sysClassify2.setId(sysClassify1.getId());
        assertThat(sysClassify1).isEqualTo(sysClassify2);
        sysClassify2.setId(2L);
        assertThat(sysClassify1).isNotEqualTo(sysClassify2);
        sysClassify1.setId(null);
        assertThat(sysClassify1).isNotEqualTo(sysClassify2);
    }
}
