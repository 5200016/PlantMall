package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysOrderProduct;
import com.ybb.mall.repository.SysOrderProductRepository;
import com.ybb.mall.service.SysOrderProductService;
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
 * Test class for the SysOrderProductResource REST controller.
 *
 * @see SysOrderProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysOrderProductResourceIntTest {

    private static final Integer DEFAULT_PRODUCT_STATUS = 1;
    private static final Integer UPDATED_PRODUCT_STATUS = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysOrderProductRepository sysOrderProductRepository;

    @Autowired
    private SysOrderProductService sysOrderProductService;

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

    private MockMvc restSysOrderProductMockMvc;

    private SysOrderProduct sysOrderProduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysOrderProductResource sysOrderProductResource = new SysOrderProductResource(sysOrderProductService);
        this.restSysOrderProductMockMvc = MockMvcBuilders.standaloneSetup(sysOrderProductResource)
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
    public static SysOrderProduct createEntity(EntityManager em) {
        SysOrderProduct sysOrderProduct = new SysOrderProduct()
            .productStatus(DEFAULT_PRODUCT_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysOrderProduct;
    }

    @Before
    public void initTest() {
        sysOrderProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysOrderProduct() throws Exception {
        int databaseSizeBeforeCreate = sysOrderProductRepository.findAll().size();

        // Create the SysOrderProduct
        restSysOrderProductMockMvc.perform(post("/api/sys-order-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOrderProduct)))
            .andExpect(status().isCreated());

        // Validate the SysOrderProduct in the database
        List<SysOrderProduct> sysOrderProductList = sysOrderProductRepository.findAll();
        assertThat(sysOrderProductList).hasSize(databaseSizeBeforeCreate + 1);
        SysOrderProduct testSysOrderProduct = sysOrderProductList.get(sysOrderProductList.size() - 1);
        assertThat(testSysOrderProduct.getProductStatus()).isEqualTo(DEFAULT_PRODUCT_STATUS);
        assertThat(testSysOrderProduct.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysOrderProduct.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysOrderProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysOrderProductRepository.findAll().size();

        // Create the SysOrderProduct with an existing ID
        sysOrderProduct.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysOrderProductMockMvc.perform(post("/api/sys-order-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOrderProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SysOrderProduct in the database
        List<SysOrderProduct> sysOrderProductList = sysOrderProductRepository.findAll();
        assertThat(sysOrderProductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysOrderProducts() throws Exception {
        // Initialize the database
        sysOrderProductRepository.saveAndFlush(sysOrderProduct);

        // Get all the sysOrderProductList
        restSysOrderProductMockMvc.perform(get("/api/sys-order-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysOrderProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].productStatus").value(hasItem(DEFAULT_PRODUCT_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysOrderProduct() throws Exception {
        // Initialize the database
        sysOrderProductRepository.saveAndFlush(sysOrderProduct);

        // Get the sysOrderProduct
        restSysOrderProductMockMvc.perform(get("/api/sys-order-products/{id}", sysOrderProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysOrderProduct.getId().intValue()))
            .andExpect(jsonPath("$.productStatus").value(DEFAULT_PRODUCT_STATUS))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysOrderProduct() throws Exception {
        // Get the sysOrderProduct
        restSysOrderProductMockMvc.perform(get("/api/sys-order-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysOrderProduct() throws Exception {
        // Initialize the database
        sysOrderProductService.save(sysOrderProduct);

        int databaseSizeBeforeUpdate = sysOrderProductRepository.findAll().size();

        // Update the sysOrderProduct
        SysOrderProduct updatedSysOrderProduct = sysOrderProductRepository.findById(sysOrderProduct.getId()).get();
        // Disconnect from session so that the updates on updatedSysOrderProduct are not directly saved in db
        em.detach(updatedSysOrderProduct);
        updatedSysOrderProduct
            .productStatus(UPDATED_PRODUCT_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysOrderProductMockMvc.perform(put("/api/sys-order-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysOrderProduct)))
            .andExpect(status().isOk());

        // Validate the SysOrderProduct in the database
        List<SysOrderProduct> sysOrderProductList = sysOrderProductRepository.findAll();
        assertThat(sysOrderProductList).hasSize(databaseSizeBeforeUpdate);
        SysOrderProduct testSysOrderProduct = sysOrderProductList.get(sysOrderProductList.size() - 1);
        assertThat(testSysOrderProduct.getProductStatus()).isEqualTo(UPDATED_PRODUCT_STATUS);
        assertThat(testSysOrderProduct.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysOrderProduct.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysOrderProduct() throws Exception {
        int databaseSizeBeforeUpdate = sysOrderProductRepository.findAll().size();

        // Create the SysOrderProduct

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysOrderProductMockMvc.perform(put("/api/sys-order-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysOrderProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SysOrderProduct in the database
        List<SysOrderProduct> sysOrderProductList = sysOrderProductRepository.findAll();
        assertThat(sysOrderProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysOrderProduct() throws Exception {
        // Initialize the database
        sysOrderProductService.save(sysOrderProduct);

        int databaseSizeBeforeDelete = sysOrderProductRepository.findAll().size();

        // Get the sysOrderProduct
        restSysOrderProductMockMvc.perform(delete("/api/sys-order-products/{id}", sysOrderProduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysOrderProduct> sysOrderProductList = sysOrderProductRepository.findAll();
        assertThat(sysOrderProductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysOrderProduct.class);
        SysOrderProduct sysOrderProduct1 = new SysOrderProduct();
        sysOrderProduct1.setId(1L);
        SysOrderProduct sysOrderProduct2 = new SysOrderProduct();
        sysOrderProduct2.setId(sysOrderProduct1.getId());
        assertThat(sysOrderProduct1).isEqualTo(sysOrderProduct2);
        sysOrderProduct2.setId(2L);
        assertThat(sysOrderProduct1).isNotEqualTo(sysOrderProduct2);
        sysOrderProduct1.setId(null);
        assertThat(sysOrderProduct1).isNotEqualTo(sysOrderProduct2);
    }
}
