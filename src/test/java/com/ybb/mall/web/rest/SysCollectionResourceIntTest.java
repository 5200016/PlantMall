package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysCollection;
import com.ybb.mall.repository.SysCollectionRepository;
import com.ybb.mall.service.SysCollectionService;
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
 * Test class for the SysCollectionResource REST controller.
 *
 * @see SysCollectionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysCollectionResourceIntTest {

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysCollectionRepository sysCollectionRepository;

    @Autowired
    private SysCollectionService sysCollectionService;

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

    private MockMvc restSysCollectionMockMvc;

    private SysCollection sysCollection;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysCollectionResource sysCollectionResource = new SysCollectionResource(sysCollectionService);
        this.restSysCollectionMockMvc = MockMvcBuilders.standaloneSetup(sysCollectionResource)
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
    public static SysCollection createEntity(EntityManager em) {
        SysCollection sysCollection = new SysCollection()
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysCollection;
    }

    @Before
    public void initTest() {
        sysCollection = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysCollection() throws Exception {
        int databaseSizeBeforeCreate = sysCollectionRepository.findAll().size();

        // Create the SysCollection
        restSysCollectionMockMvc.perform(post("/api/sys-collections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCollection)))
            .andExpect(status().isCreated());

        // Validate the SysCollection in the database
        List<SysCollection> sysCollectionList = sysCollectionRepository.findAll();
        assertThat(sysCollectionList).hasSize(databaseSizeBeforeCreate + 1);
        SysCollection testSysCollection = sysCollectionList.get(sysCollectionList.size() - 1);
        assertThat(testSysCollection.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysCollection.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysCollectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysCollectionRepository.findAll().size();

        // Create the SysCollection with an existing ID
        sysCollection.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCollectionMockMvc.perform(post("/api/sys-collections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCollection)))
            .andExpect(status().isBadRequest());

        // Validate the SysCollection in the database
        List<SysCollection> sysCollectionList = sysCollectionRepository.findAll();
        assertThat(sysCollectionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysCollections() throws Exception {
        // Initialize the database
        sysCollectionRepository.saveAndFlush(sysCollection);

        // Get all the sysCollectionList
        restSysCollectionMockMvc.perform(get("/api/sys-collections?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCollection.getId().intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysCollection() throws Exception {
        // Initialize the database
        sysCollectionRepository.saveAndFlush(sysCollection);

        // Get the sysCollection
        restSysCollectionMockMvc.perform(get("/api/sys-collections/{id}", sysCollection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysCollection.getId().intValue()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysCollection() throws Exception {
        // Get the sysCollection
        restSysCollectionMockMvc.perform(get("/api/sys-collections/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysCollection() throws Exception {
        // Initialize the database
        sysCollectionService.save(sysCollection);

        int databaseSizeBeforeUpdate = sysCollectionRepository.findAll().size();

        // Update the sysCollection
        SysCollection updatedSysCollection = sysCollectionRepository.findById(sysCollection.getId()).get();
        // Disconnect from session so that the updates on updatedSysCollection are not directly saved in db
        em.detach(updatedSysCollection);
        updatedSysCollection
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysCollectionMockMvc.perform(put("/api/sys-collections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysCollection)))
            .andExpect(status().isOk());

        // Validate the SysCollection in the database
        List<SysCollection> sysCollectionList = sysCollectionRepository.findAll();
        assertThat(sysCollectionList).hasSize(databaseSizeBeforeUpdate);
        SysCollection testSysCollection = sysCollectionList.get(sysCollectionList.size() - 1);
        assertThat(testSysCollection.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysCollection.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysCollection() throws Exception {
        int databaseSizeBeforeUpdate = sysCollectionRepository.findAll().size();

        // Create the SysCollection

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCollectionMockMvc.perform(put("/api/sys-collections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCollection)))
            .andExpect(status().isBadRequest());

        // Validate the SysCollection in the database
        List<SysCollection> sysCollectionList = sysCollectionRepository.findAll();
        assertThat(sysCollectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysCollection() throws Exception {
        // Initialize the database
        sysCollectionService.save(sysCollection);

        int databaseSizeBeforeDelete = sysCollectionRepository.findAll().size();

        // Get the sysCollection
        restSysCollectionMockMvc.perform(delete("/api/sys-collections/{id}", sysCollection.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysCollection> sysCollectionList = sysCollectionRepository.findAll();
        assertThat(sysCollectionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCollection.class);
        SysCollection sysCollection1 = new SysCollection();
        sysCollection1.setId(1L);
        SysCollection sysCollection2 = new SysCollection();
        sysCollection2.setId(sysCollection1.getId());
        assertThat(sysCollection1).isEqualTo(sysCollection2);
        sysCollection2.setId(2L);
        assertThat(sysCollection1).isNotEqualTo(sysCollection2);
        sysCollection1.setId(null);
        assertThat(sysCollection1).isNotEqualTo(sysCollection2);
    }
}
