package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.repository.SysReceiverAddressRepository;
import com.ybb.mall.service.SysReceiverAddressService;
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
 * Test class for the SysReceiverAddressResource REST controller.
 *
 * @see SysReceiverAddressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysReceiverAddressResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysReceiverAddressRepository sysReceiverAddressRepository;

    @Autowired
    private SysReceiverAddressService sysReceiverAddressService;

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

    private MockMvc restSysReceiverAddressMockMvc;

    private SysReceiverAddress sysReceiverAddress;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysReceiverAddressResource sysReceiverAddressResource = new SysReceiverAddressResource(sysReceiverAddressService);
        this.restSysReceiverAddressMockMvc = MockMvcBuilders.standaloneSetup(sysReceiverAddressResource)
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
    public static SysReceiverAddress createEntity(EntityManager em) {
        SysReceiverAddress sysReceiverAddress = new SysReceiverAddress()
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .area(DEFAULT_AREA)
            .address(DEFAULT_ADDRESS)
            .status(DEFAULT_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysReceiverAddress;
    }

    @Before
    public void initTest() {
        sysReceiverAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysReceiverAddress() throws Exception {
        int databaseSizeBeforeCreate = sysReceiverAddressRepository.findAll().size();

        // Create the SysReceiverAddress
        restSysReceiverAddressMockMvc.perform(post("/api/sys-receiver-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysReceiverAddress)))
            .andExpect(status().isCreated());

        // Validate the SysReceiverAddress in the database
        List<SysReceiverAddress> sysReceiverAddressList = sysReceiverAddressRepository.findAll();
        assertThat(sysReceiverAddressList).hasSize(databaseSizeBeforeCreate + 1);
        SysReceiverAddress testSysReceiverAddress = sysReceiverAddressList.get(sysReceiverAddressList.size() - 1);
        assertThat(testSysReceiverAddress.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysReceiverAddress.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSysReceiverAddress.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testSysReceiverAddress.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSysReceiverAddress.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysReceiverAddress.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysReceiverAddress.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysReceiverAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysReceiverAddressRepository.findAll().size();

        // Create the SysReceiverAddress with an existing ID
        sysReceiverAddress.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysReceiverAddressMockMvc.perform(post("/api/sys-receiver-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysReceiverAddress)))
            .andExpect(status().isBadRequest());

        // Validate the SysReceiverAddress in the database
        List<SysReceiverAddress> sysReceiverAddressList = sysReceiverAddressRepository.findAll();
        assertThat(sysReceiverAddressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysReceiverAddresses() throws Exception {
        // Initialize the database
        sysReceiverAddressRepository.saveAndFlush(sysReceiverAddress);

        // Get all the sysReceiverAddressList
        restSysReceiverAddressMockMvc.perform(get("/api/sys-receiver-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysReceiverAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysReceiverAddress() throws Exception {
        // Initialize the database
        sysReceiverAddressRepository.saveAndFlush(sysReceiverAddress);

        // Get the sysReceiverAddress
        restSysReceiverAddressMockMvc.perform(get("/api/sys-receiver-addresses/{id}", sysReceiverAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysReceiverAddress.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysReceiverAddress() throws Exception {
        // Get the sysReceiverAddress
        restSysReceiverAddressMockMvc.perform(get("/api/sys-receiver-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysReceiverAddress() throws Exception {
        // Initialize the database
        sysReceiverAddressService.save(sysReceiverAddress);

        int databaseSizeBeforeUpdate = sysReceiverAddressRepository.findAll().size();

        // Update the sysReceiverAddress
        SysReceiverAddress updatedSysReceiverAddress = sysReceiverAddressRepository.findById(sysReceiverAddress.getId()).get();
        // Disconnect from session so that the updates on updatedSysReceiverAddress are not directly saved in db
        em.detach(updatedSysReceiverAddress);
        updatedSysReceiverAddress
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .area(UPDATED_AREA)
            .address(UPDATED_ADDRESS)
            .status(UPDATED_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysReceiverAddressMockMvc.perform(put("/api/sys-receiver-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysReceiverAddress)))
            .andExpect(status().isOk());

        // Validate the SysReceiverAddress in the database
        List<SysReceiverAddress> sysReceiverAddressList = sysReceiverAddressRepository.findAll();
        assertThat(sysReceiverAddressList).hasSize(databaseSizeBeforeUpdate);
        SysReceiverAddress testSysReceiverAddress = sysReceiverAddressList.get(sysReceiverAddressList.size() - 1);
        assertThat(testSysReceiverAddress.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysReceiverAddress.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSysReceiverAddress.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testSysReceiverAddress.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSysReceiverAddress.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysReceiverAddress.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysReceiverAddress.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysReceiverAddress() throws Exception {
        int databaseSizeBeforeUpdate = sysReceiverAddressRepository.findAll().size();

        // Create the SysReceiverAddress

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysReceiverAddressMockMvc.perform(put("/api/sys-receiver-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysReceiverAddress)))
            .andExpect(status().isBadRequest());

        // Validate the SysReceiverAddress in the database
        List<SysReceiverAddress> sysReceiverAddressList = sysReceiverAddressRepository.findAll();
        assertThat(sysReceiverAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysReceiverAddress() throws Exception {
        // Initialize the database
        sysReceiverAddressService.save(sysReceiverAddress);

        int databaseSizeBeforeDelete = sysReceiverAddressRepository.findAll().size();

        // Get the sysReceiverAddress
        restSysReceiverAddressMockMvc.perform(delete("/api/sys-receiver-addresses/{id}", sysReceiverAddress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysReceiverAddress> sysReceiverAddressList = sysReceiverAddressRepository.findAll();
        assertThat(sysReceiverAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysReceiverAddress.class);
        SysReceiverAddress sysReceiverAddress1 = new SysReceiverAddress();
        sysReceiverAddress1.setId(1L);
        SysReceiverAddress sysReceiverAddress2 = new SysReceiverAddress();
        sysReceiverAddress2.setId(sysReceiverAddress1.getId());
        assertThat(sysReceiverAddress1).isEqualTo(sysReceiverAddress2);
        sysReceiverAddress2.setId(2L);
        assertThat(sysReceiverAddress1).isNotEqualTo(sysReceiverAddress2);
        sysReceiverAddress1.setId(null);
        assertThat(sysReceiverAddress1).isNotEqualTo(sysReceiverAddress2);
    }
}
