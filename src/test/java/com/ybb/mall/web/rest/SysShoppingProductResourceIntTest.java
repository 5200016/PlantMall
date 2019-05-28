package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysShoppingProduct;
import com.ybb.mall.repository.SysShoppingProductRepository;
import com.ybb.mall.service.SysShoppingProductService;
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
 * Test class for the SysShoppingProductResource REST controller.
 *
 * @see SysShoppingProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysShoppingProductResourceIntTest {

    private static final Integer DEFAULT_PRODUCT_NUMBER = 1;
    private static final Integer UPDATED_PRODUCT_NUMBER = 2;

    private static final Integer DEFAULT_PRODUCT_TYPE = 1;
    private static final Integer UPDATED_PRODUCT_TYPE = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysShoppingProductRepository sysShoppingProductRepository;

    @Autowired
    private SysShoppingProductService sysShoppingProductService;

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

    private MockMvc restSysShoppingProductMockMvc;

    private SysShoppingProduct sysShoppingProduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysShoppingProductResource sysShoppingProductResource = new SysShoppingProductResource(sysShoppingProductService);
        this.restSysShoppingProductMockMvc = MockMvcBuilders.standaloneSetup(sysShoppingProductResource)
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
    public static SysShoppingProduct createEntity(EntityManager em) {
        SysShoppingProduct sysShoppingProduct = new SysShoppingProduct()
            .productNumber(DEFAULT_PRODUCT_NUMBER)
            .productType(DEFAULT_PRODUCT_TYPE)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysShoppingProduct;
    }

    @Before
    public void initTest() {
        sysShoppingProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysShoppingProduct() throws Exception {
        int databaseSizeBeforeCreate = sysShoppingProductRepository.findAll().size();

        // Create the SysShoppingProduct
        restSysShoppingProductMockMvc.perform(post("/api/sys-shopping-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysShoppingProduct)))
            .andExpect(status().isCreated());

        // Validate the SysShoppingProduct in the database
        List<SysShoppingProduct> sysShoppingProductList = sysShoppingProductRepository.findAll();
        assertThat(sysShoppingProductList).hasSize(databaseSizeBeforeCreate + 1);
        SysShoppingProduct testSysShoppingProduct = sysShoppingProductList.get(sysShoppingProductList.size() - 1);
        assertThat(testSysShoppingProduct.getProductNumber()).isEqualTo(DEFAULT_PRODUCT_NUMBER);
        assertThat(testSysShoppingProduct.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testSysShoppingProduct.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysShoppingProduct.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysShoppingProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysShoppingProductRepository.findAll().size();

        // Create the SysShoppingProduct with an existing ID
        sysShoppingProduct.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysShoppingProductMockMvc.perform(post("/api/sys-shopping-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysShoppingProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SysShoppingProduct in the database
        List<SysShoppingProduct> sysShoppingProductList = sysShoppingProductRepository.findAll();
        assertThat(sysShoppingProductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysShoppingProducts() throws Exception {
        // Initialize the database
        sysShoppingProductRepository.saveAndFlush(sysShoppingProduct);

        // Get all the sysShoppingProductList
        restSysShoppingProductMockMvc.perform(get("/api/sys-shopping-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysShoppingProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].productNumber").value(hasItem(DEFAULT_PRODUCT_NUMBER)))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysShoppingProduct() throws Exception {
        // Initialize the database
        sysShoppingProductRepository.saveAndFlush(sysShoppingProduct);

        // Get the sysShoppingProduct
        restSysShoppingProductMockMvc.perform(get("/api/sys-shopping-products/{id}", sysShoppingProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysShoppingProduct.getId().intValue()))
            .andExpect(jsonPath("$.productNumber").value(DEFAULT_PRODUCT_NUMBER))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysShoppingProduct() throws Exception {
        // Get the sysShoppingProduct
        restSysShoppingProductMockMvc.perform(get("/api/sys-shopping-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysShoppingProduct() throws Exception {
        // Initialize the database
        sysShoppingProductService.save(sysShoppingProduct);

        int databaseSizeBeforeUpdate = sysShoppingProductRepository.findAll().size();

        // Update the sysShoppingProduct
        SysShoppingProduct updatedSysShoppingProduct = sysShoppingProductRepository.findById(sysShoppingProduct.getId()).get();
        // Disconnect from session so that the updates on updatedSysShoppingProduct are not directly saved in db
        em.detach(updatedSysShoppingProduct);
        updatedSysShoppingProduct
            .productNumber(UPDATED_PRODUCT_NUMBER)
            .productType(UPDATED_PRODUCT_TYPE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysShoppingProductMockMvc.perform(put("/api/sys-shopping-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysShoppingProduct)))
            .andExpect(status().isOk());

        // Validate the SysShoppingProduct in the database
        List<SysShoppingProduct> sysShoppingProductList = sysShoppingProductRepository.findAll();
        assertThat(sysShoppingProductList).hasSize(databaseSizeBeforeUpdate);
        SysShoppingProduct testSysShoppingProduct = sysShoppingProductList.get(sysShoppingProductList.size() - 1);
        assertThat(testSysShoppingProduct.getProductNumber()).isEqualTo(UPDATED_PRODUCT_NUMBER);
        assertThat(testSysShoppingProduct.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testSysShoppingProduct.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysShoppingProduct.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysShoppingProduct() throws Exception {
        int databaseSizeBeforeUpdate = sysShoppingProductRepository.findAll().size();

        // Create the SysShoppingProduct

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysShoppingProductMockMvc.perform(put("/api/sys-shopping-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysShoppingProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SysShoppingProduct in the database
        List<SysShoppingProduct> sysShoppingProductList = sysShoppingProductRepository.findAll();
        assertThat(sysShoppingProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysShoppingProduct() throws Exception {
        // Initialize the database
        sysShoppingProductService.save(sysShoppingProduct);

        int databaseSizeBeforeDelete = sysShoppingProductRepository.findAll().size();

        // Get the sysShoppingProduct
        restSysShoppingProductMockMvc.perform(delete("/api/sys-shopping-products/{id}", sysShoppingProduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysShoppingProduct> sysShoppingProductList = sysShoppingProductRepository.findAll();
        assertThat(sysShoppingProductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysShoppingProduct.class);
        SysShoppingProduct sysShoppingProduct1 = new SysShoppingProduct();
        sysShoppingProduct1.setId(1L);
        SysShoppingProduct sysShoppingProduct2 = new SysShoppingProduct();
        sysShoppingProduct2.setId(sysShoppingProduct1.getId());
        assertThat(sysShoppingProduct1).isEqualTo(sysShoppingProduct2);
        sysShoppingProduct2.setId(2L);
        assertThat(sysShoppingProduct1).isNotEqualTo(sysShoppingProduct2);
        sysShoppingProduct1.setId(null);
        assertThat(sysShoppingProduct1).isNotEqualTo(sysShoppingProduct2);
    }
}
