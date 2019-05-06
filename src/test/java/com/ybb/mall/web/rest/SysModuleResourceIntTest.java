package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysModule;
import com.ybb.mall.repository.SysModuleRepository;
import com.ybb.mall.service.SysModuleService;
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
 * Test class for the SysModuleResource REST controller.
 *
 * @see SysModuleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysModuleResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_IMAGE_DISABLE = 1;
    private static final Integer UPDATED_IMAGE_DISABLE = 2;

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_STYLE_TYPE = 1;
    private static final Integer UPDATED_STYLE_TYPE = 2;

    private static final Integer DEFAULT_HOME_MENU = 1;
    private static final Integer UPDATED_HOME_MENU = 2;

    private static final Integer DEFAULT_HOME_BOTTOM = 1;
    private static final Integer UPDATED_HOME_BOTTOM = 2;

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT = 1;
    private static final Integer UPDATED_SORT = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysModuleRepository sysModuleRepository;

    @Autowired
    private SysModuleService sysModuleService;

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

    private MockMvc restSysModuleMockMvc;

    private SysModule sysModule;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysModuleResource sysModuleResource = new SysModuleResource(sysModuleService);
        this.restSysModuleMockMvc = MockMvcBuilders.standaloneSetup(sysModuleResource)
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
    public static SysModule createEntity(EntityManager em) {
        SysModule sysModule = new SysModule()
            .name(DEFAULT_NAME)
            .icon(DEFAULT_ICON)
            .image(DEFAULT_IMAGE)
            .imageDisable(DEFAULT_IMAGE_DISABLE)
            .type(DEFAULT_TYPE)
            .styleType(DEFAULT_STYLE_TYPE)
            .homeMenu(DEFAULT_HOME_MENU)
            .homeBottom(DEFAULT_HOME_BOTTOM)
            .path(DEFAULT_PATH)
            .sort(DEFAULT_SORT)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysModule;
    }

    @Before
    public void initTest() {
        sysModule = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysModule() throws Exception {
        int databaseSizeBeforeCreate = sysModuleRepository.findAll().size();

        // Create the SysModule
        restSysModuleMockMvc.perform(post("/api/sys-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysModule)))
            .andExpect(status().isCreated());

        // Validate the SysModule in the database
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeCreate + 1);
        SysModule testSysModule = sysModuleList.get(sysModuleList.size() - 1);
        assertThat(testSysModule.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysModule.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testSysModule.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testSysModule.getImageDisable()).isEqualTo(DEFAULT_IMAGE_DISABLE);
        assertThat(testSysModule.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSysModule.getStyleType()).isEqualTo(DEFAULT_STYLE_TYPE);
        assertThat(testSysModule.getHomeMenu()).isEqualTo(DEFAULT_HOME_MENU);
        assertThat(testSysModule.getHomeBottom()).isEqualTo(DEFAULT_HOME_BOTTOM);
        assertThat(testSysModule.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testSysModule.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testSysModule.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysModule.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysModuleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysModuleRepository.findAll().size();

        // Create the SysModule with an existing ID
        sysModule.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysModuleMockMvc.perform(post("/api/sys-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysModule)))
            .andExpect(status().isBadRequest());

        // Validate the SysModule in the database
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysModules() throws Exception {
        // Initialize the database
        sysModuleRepository.saveAndFlush(sysModule);

        // Get all the sysModuleList
        restSysModuleMockMvc.perform(get("/api/sys-modules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysModule.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].imageDisable").value(hasItem(DEFAULT_IMAGE_DISABLE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].styleType").value(hasItem(DEFAULT_STYLE_TYPE)))
            .andExpect(jsonPath("$.[*].homeMenu").value(hasItem(DEFAULT_HOME_MENU)))
            .andExpect(jsonPath("$.[*].homeBottom").value(hasItem(DEFAULT_HOME_BOTTOM)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysModule() throws Exception {
        // Initialize the database
        sysModuleRepository.saveAndFlush(sysModule);

        // Get the sysModule
        restSysModuleMockMvc.perform(get("/api/sys-modules/{id}", sysModule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysModule.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON.toString()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.imageDisable").value(DEFAULT_IMAGE_DISABLE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.styleType").value(DEFAULT_STYLE_TYPE))
            .andExpect(jsonPath("$.homeMenu").value(DEFAULT_HOME_MENU))
            .andExpect(jsonPath("$.homeBottom").value(DEFAULT_HOME_BOTTOM))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH.toString()))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysModule() throws Exception {
        // Get the sysModule
        restSysModuleMockMvc.perform(get("/api/sys-modules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysModule() throws Exception {
        // Initialize the database
        sysModuleService.save(sysModule);

        int databaseSizeBeforeUpdate = sysModuleRepository.findAll().size();

        // Update the sysModule
        SysModule updatedSysModule = sysModuleRepository.findById(sysModule.getId()).get();
        // Disconnect from session so that the updates on updatedSysModule are not directly saved in db
        em.detach(updatedSysModule);
        updatedSysModule
            .name(UPDATED_NAME)
            .icon(UPDATED_ICON)
            .image(UPDATED_IMAGE)
            .imageDisable(UPDATED_IMAGE_DISABLE)
            .type(UPDATED_TYPE)
            .styleType(UPDATED_STYLE_TYPE)
            .homeMenu(UPDATED_HOME_MENU)
            .homeBottom(UPDATED_HOME_BOTTOM)
            .path(UPDATED_PATH)
            .sort(UPDATED_SORT)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysModuleMockMvc.perform(put("/api/sys-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysModule)))
            .andExpect(status().isOk());

        // Validate the SysModule in the database
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeUpdate);
        SysModule testSysModule = sysModuleList.get(sysModuleList.size() - 1);
        assertThat(testSysModule.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysModule.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testSysModule.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testSysModule.getImageDisable()).isEqualTo(UPDATED_IMAGE_DISABLE);
        assertThat(testSysModule.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSysModule.getStyleType()).isEqualTo(UPDATED_STYLE_TYPE);
        assertThat(testSysModule.getHomeMenu()).isEqualTo(UPDATED_HOME_MENU);
        assertThat(testSysModule.getHomeBottom()).isEqualTo(UPDATED_HOME_BOTTOM);
        assertThat(testSysModule.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testSysModule.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testSysModule.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysModule.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysModule() throws Exception {
        int databaseSizeBeforeUpdate = sysModuleRepository.findAll().size();

        // Create the SysModule

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysModuleMockMvc.perform(put("/api/sys-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysModule)))
            .andExpect(status().isBadRequest());

        // Validate the SysModule in the database
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysModule() throws Exception {
        // Initialize the database
        sysModuleService.save(sysModule);

        int databaseSizeBeforeDelete = sysModuleRepository.findAll().size();

        // Get the sysModule
        restSysModuleMockMvc.perform(delete("/api/sys-modules/{id}", sysModule.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysModule.class);
        SysModule sysModule1 = new SysModule();
        sysModule1.setId(1L);
        SysModule sysModule2 = new SysModule();
        sysModule2.setId(sysModule1.getId());
        assertThat(sysModule1).isEqualTo(sysModule2);
        sysModule2.setId(2L);
        assertThat(sysModule1).isNotEqualTo(sysModule2);
        sysModule1.setId(null);
        assertThat(sysModule1).isNotEqualTo(sysModule2);
    }
}
