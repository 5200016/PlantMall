package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysShoppingCar;
import com.ybb.mall.repository.SysShoppingCarRepository;
import com.ybb.mall.service.SysShoppingCarService;
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
 * Test class for the SysShoppingCarResource REST controller.
 *
 * @see SysShoppingCarResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysShoppingCarResourceIntTest {

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysShoppingCarRepository sysShoppingCarRepository;

    @Autowired
    private SysShoppingCarService sysShoppingCarService;

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

    private MockMvc restSysShoppingCarMockMvc;

    private SysShoppingCar sysShoppingCar;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysShoppingCarResource sysShoppingCarResource = new SysShoppingCarResource(sysShoppingCarService);
        this.restSysShoppingCarMockMvc = MockMvcBuilders.standaloneSetup(sysShoppingCarResource)
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
    public static SysShoppingCar createEntity(EntityManager em) {
        SysShoppingCar sysShoppingCar = new SysShoppingCar()
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysShoppingCar;
    }

    @Before
    public void initTest() {
        sysShoppingCar = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysShoppingCar() throws Exception {
        int databaseSizeBeforeCreate = sysShoppingCarRepository.findAll().size();

        // Create the SysShoppingCar
        restSysShoppingCarMockMvc.perform(post("/api/sys-shopping-cars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysShoppingCar)))
            .andExpect(status().isCreated());

        // Validate the SysShoppingCar in the database
        List<SysShoppingCar> sysShoppingCarList = sysShoppingCarRepository.findAll();
        assertThat(sysShoppingCarList).hasSize(databaseSizeBeforeCreate + 1);
        SysShoppingCar testSysShoppingCar = sysShoppingCarList.get(sysShoppingCarList.size() - 1);
        assertThat(testSysShoppingCar.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysShoppingCar.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysShoppingCarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysShoppingCarRepository.findAll().size();

        // Create the SysShoppingCar with an existing ID
        sysShoppingCar.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysShoppingCarMockMvc.perform(post("/api/sys-shopping-cars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysShoppingCar)))
            .andExpect(status().isBadRequest());

        // Validate the SysShoppingCar in the database
        List<SysShoppingCar> sysShoppingCarList = sysShoppingCarRepository.findAll();
        assertThat(sysShoppingCarList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysShoppingCars() throws Exception {
        // Initialize the database
        sysShoppingCarRepository.saveAndFlush(sysShoppingCar);

        // Get all the sysShoppingCarList
        restSysShoppingCarMockMvc.perform(get("/api/sys-shopping-cars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysShoppingCar.getId().intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysShoppingCar() throws Exception {
        // Initialize the database
        sysShoppingCarRepository.saveAndFlush(sysShoppingCar);

        // Get the sysShoppingCar
        restSysShoppingCarMockMvc.perform(get("/api/sys-shopping-cars/{id}", sysShoppingCar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysShoppingCar.getId().intValue()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysShoppingCar() throws Exception {
        // Get the sysShoppingCar
        restSysShoppingCarMockMvc.perform(get("/api/sys-shopping-cars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysShoppingCar() throws Exception {
        // Initialize the database
        sysShoppingCarService.save(sysShoppingCar);

        int databaseSizeBeforeUpdate = sysShoppingCarRepository.findAll().size();

        // Update the sysShoppingCar
        SysShoppingCar updatedSysShoppingCar = sysShoppingCarRepository.findById(sysShoppingCar.getId()).get();
        // Disconnect from session so that the updates on updatedSysShoppingCar are not directly saved in db
        em.detach(updatedSysShoppingCar);
        updatedSysShoppingCar
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysShoppingCarMockMvc.perform(put("/api/sys-shopping-cars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysShoppingCar)))
            .andExpect(status().isOk());

        // Validate the SysShoppingCar in the database
        List<SysShoppingCar> sysShoppingCarList = sysShoppingCarRepository.findAll();
        assertThat(sysShoppingCarList).hasSize(databaseSizeBeforeUpdate);
        SysShoppingCar testSysShoppingCar = sysShoppingCarList.get(sysShoppingCarList.size() - 1);
        assertThat(testSysShoppingCar.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysShoppingCar.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysShoppingCar() throws Exception {
        int databaseSizeBeforeUpdate = sysShoppingCarRepository.findAll().size();

        // Create the SysShoppingCar

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysShoppingCarMockMvc.perform(put("/api/sys-shopping-cars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysShoppingCar)))
            .andExpect(status().isBadRequest());

        // Validate the SysShoppingCar in the database
        List<SysShoppingCar> sysShoppingCarList = sysShoppingCarRepository.findAll();
        assertThat(sysShoppingCarList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysShoppingCar() throws Exception {
        // Initialize the database
        sysShoppingCarService.save(sysShoppingCar);

        int databaseSizeBeforeDelete = sysShoppingCarRepository.findAll().size();

        // Get the sysShoppingCar
        restSysShoppingCarMockMvc.perform(delete("/api/sys-shopping-cars/{id}", sysShoppingCar.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysShoppingCar> sysShoppingCarList = sysShoppingCarRepository.findAll();
        assertThat(sysShoppingCarList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysShoppingCar.class);
        SysShoppingCar sysShoppingCar1 = new SysShoppingCar();
        sysShoppingCar1.setId(1L);
        SysShoppingCar sysShoppingCar2 = new SysShoppingCar();
        sysShoppingCar2.setId(sysShoppingCar1.getId());
        assertThat(sysShoppingCar1).isEqualTo(sysShoppingCar2);
        sysShoppingCar2.setId(2L);
        assertThat(sysShoppingCar1).isNotEqualTo(sysShoppingCar2);
        sysShoppingCar1.setId(null);
        assertThat(sysShoppingCar1).isNotEqualTo(sysShoppingCar2);
    }
}
