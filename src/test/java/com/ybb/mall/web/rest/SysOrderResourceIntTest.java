package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.repository.SysOrderRepository;
import com.ybb.mall.service.SysOrderService;
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
 * Test class for the SysOrderResource REST controller.
 *
 * @see SysOrderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysOrderResourceIntTest {

    private static final String DEFAULT_TRADE_NO = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_NO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysOrderRepository sysOrderRepository;

    @Autowired
    private SysOrderService sysOrderService;

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

    private MockMvc restSysOrderMockMvc;

    private SysOrder sysOrder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysOrderResource sysOrderResource = new SysOrderResource(sysOrderService);
        this.restSysOrderMockMvc = MockMvcBuilders.standaloneSetup(sysOrderResource)
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
    public static SysOrder createEntity(EntityManager em) {
        SysOrder sysOrder = new SysOrder()
            .tradeNo(DEFAULT_TRADE_NO)
            .price(DEFAULT_PRICE)
            .type(DEFAULT_TYPE)
            .number(DEFAULT_NUMBER)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysOrder;
    }

    @Before
    public void initTest() {
        sysOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysOrder() throws Exception {
        int databaseSizeBeforeCreate = sysOrderRepository.findAll().size();

        // Create the SysOrder
        restSysOrderMockMvc.perform(post("/api/sys-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOrder)))
            .andExpect(status().isCreated());

        // Validate the SysOrder in the database
        List<SysOrder> sysOrderList = sysOrderRepository.findAll();
        assertThat(sysOrderList).hasSize(databaseSizeBeforeCreate + 1);
        SysOrder testSysOrder = sysOrderList.get(sysOrderList.size() - 1);
        assertThat(testSysOrder.getTradeNo()).isEqualTo(DEFAULT_TRADE_NO);
        assertThat(testSysOrder.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testSysOrder.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSysOrder.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testSysOrder.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysOrder.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysOrderRepository.findAll().size();

        // Create the SysOrder with an existing ID
        sysOrder.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysOrderMockMvc.perform(post("/api/sys-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOrder)))
            .andExpect(status().isBadRequest());

        // Validate the SysOrder in the database
        List<SysOrder> sysOrderList = sysOrderRepository.findAll();
        assertThat(sysOrderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysOrders() throws Exception {
        // Initialize the database
        sysOrderRepository.saveAndFlush(sysOrder);

        // Get all the sysOrderList
        restSysOrderMockMvc.perform(get("/api/sys-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].tradeNo").value(hasItem(DEFAULT_TRADE_NO.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }

    @Test
    @Transactional
    public void getSysOrder() throws Exception {
        // Initialize the database
        sysOrderRepository.saveAndFlush(sysOrder);

        // Get the sysOrder
        restSysOrderMockMvc.perform(get("/api/sys-orders/{id}", sysOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysOrder.getId().intValue()))
            .andExpect(jsonPath("$.tradeNo").value(DEFAULT_TRADE_NO.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysOrder() throws Exception {
        // Get the sysOrder
        restSysOrderMockMvc.perform(get("/api/sys-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysOrder() throws Exception {
        // Initialize the database
        sysOrderService.save(sysOrder);

        int databaseSizeBeforeUpdate = sysOrderRepository.findAll().size();

        // Update the sysOrder
        SysOrder updatedSysOrder = sysOrderRepository.findById(sysOrder.getId()).get();
        // Disconnect from session so that the updates on updatedSysOrder are not directly saved in db
        em.detach(updatedSysOrder);
        updatedSysOrder
            .tradeNo(UPDATED_TRADE_NO)
            .price(UPDATED_PRICE)
            .type(UPDATED_TYPE)
            .number(UPDATED_NUMBER)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysOrderMockMvc.perform(put("/api/sys-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysOrder)))
            .andExpect(status().isOk());

        // Validate the SysOrder in the database
        List<SysOrder> sysOrderList = sysOrderRepository.findAll();
        assertThat(sysOrderList).hasSize(databaseSizeBeforeUpdate);
        SysOrder testSysOrder = sysOrderList.get(sysOrderList.size() - 1);
        assertThat(testSysOrder.getTradeNo()).isEqualTo(UPDATED_TRADE_NO);
        assertThat(testSysOrder.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testSysOrder.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSysOrder.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testSysOrder.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysOrder.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysOrder() throws Exception {
        int databaseSizeBeforeUpdate = sysOrderRepository.findAll().size();

        // Create the SysOrder

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysOrderMockMvc.perform(put("/api/sys-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOrder)))
            .andExpect(status().isBadRequest());

        // Validate the SysOrder in the database
        List<SysOrder> sysOrderList = sysOrderRepository.findAll();
        assertThat(sysOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysOrder() throws Exception {
        // Initialize the database
        sysOrderService.save(sysOrder);

        int databaseSizeBeforeDelete = sysOrderRepository.findAll().size();

        // Get the sysOrder
        restSysOrderMockMvc.perform(delete("/api/sys-orders/{id}", sysOrder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysOrder> sysOrderList = sysOrderRepository.findAll();
        assertThat(sysOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysOrder.class);
        SysOrder sysOrder1 = new SysOrder();
        sysOrder1.setId(1L);
        SysOrder sysOrder2 = new SysOrder();
        sysOrder2.setId(sysOrder1.getId());
        assertThat(sysOrder1).isEqualTo(sysOrder2);
        sysOrder2.setId(2L);
        assertThat(sysOrder1).isNotEqualTo(sysOrder2);
        sysOrder1.setId(null);
        assertThat(sysOrder1).isNotEqualTo(sysOrder2);
    }
}
