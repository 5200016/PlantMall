package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysCouponProduct;
import com.ybb.mall.repository.SysCouponProductRepository;
import com.ybb.mall.service.SysCouponProductService;
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
 * Test class for the SysCouponProductResource REST controller.
 *
 * @see SysCouponProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysCouponProductResourceIntTest {

    private static final Integer DEFAULT_RESIDUE = 1;
    private static final Integer UPDATED_RESIDUE = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysCouponProductRepository sysCouponProductRepository;

    @Autowired
    private SysCouponProductService sysCouponProductService;

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

    private MockMvc restSysCouponProductMockMvc;

    private SysCouponProduct sysCouponProduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysCouponProductResource sysCouponProductResource = new SysCouponProductResource(sysCouponProductService);
        this.restSysCouponProductMockMvc = MockMvcBuilders.standaloneSetup(sysCouponProductResource)
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
    public static SysCouponProduct createEntity(EntityManager em) {
        SysCouponProduct sysCouponProduct = new SysCouponProduct()
            .residue(DEFAULT_RESIDUE)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysCouponProduct;
    }

    @Before
    public void initTest() {
        sysCouponProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysCouponProduct() throws Exception {
        int databaseSizeBeforeCreate = sysCouponProductRepository.findAll().size();

        // Create the SysCouponProduct
        restSysCouponProductMockMvc.perform(post("/api/sys-coupon-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCouponProduct)))
            .andExpect(status().isCreated());

        // Validate the SysCouponProduct in the database
        List<SysCouponProduct> sysCouponProductList = sysCouponProductRepository.findAll();
        assertThat(sysCouponProductList).hasSize(databaseSizeBeforeCreate + 1);
        SysCouponProduct testSysCouponProduct = sysCouponProductList.get(sysCouponProductList.size() - 1);
        assertThat(testSysCouponProduct.getResidue()).isEqualTo(DEFAULT_RESIDUE);
        assertThat(testSysCouponProduct.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysCouponProduct.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysCouponProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysCouponProductRepository.findAll().size();

        // Create the SysCouponProduct with an existing ID
        sysCouponProduct.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysCouponProductMockMvc.perform(post("/api/sys-coupon-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCouponProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SysCouponProduct in the database
        List<SysCouponProduct> sysCouponProductList = sysCouponProductRepository.findAll();
        assertThat(sysCouponProductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysCouponProducts() throws Exception {
        // Initialize the database
        sysCouponProductRepository.saveAndFlush(sysCouponProduct);

        // Get all the sysCouponProductList
        restSysCouponProductMockMvc.perform(get("/api/sys-coupon-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysCouponProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].residue").value(hasItem(DEFAULT_RESIDUE)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @Test
    @Transactional
    public void getSysCouponProduct() throws Exception {
        // Initialize the database
        sysCouponProductRepository.saveAndFlush(sysCouponProduct);

        // Get the sysCouponProduct
        restSysCouponProductMockMvc.perform(get("/api/sys-coupon-products/{id}", sysCouponProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysCouponProduct.getId().intValue()))
            .andExpect(jsonPath("$.residue").value(DEFAULT_RESIDUE))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysCouponProduct() throws Exception {
        // Get the sysCouponProduct
        restSysCouponProductMockMvc.perform(get("/api/sys-coupon-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysCouponProduct() throws Exception {
        // Initialize the database
        sysCouponProductService.save(sysCouponProduct);

        int databaseSizeBeforeUpdate = sysCouponProductRepository.findAll().size();

        // Update the sysCouponProduct
        SysCouponProduct updatedSysCouponProduct = sysCouponProductRepository.findById(sysCouponProduct.getId()).get();
        // Disconnect from session so that the updates on updatedSysCouponProduct are not directly saved in db
        em.detach(updatedSysCouponProduct);
        updatedSysCouponProduct
            .residue(UPDATED_RESIDUE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysCouponProductMockMvc.perform(put("/api/sys-coupon-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysCouponProduct)))
            .andExpect(status().isOk());

        // Validate the SysCouponProduct in the database
        List<SysCouponProduct> sysCouponProductList = sysCouponProductRepository.findAll();
        assertThat(sysCouponProductList).hasSize(databaseSizeBeforeUpdate);
        SysCouponProduct testSysCouponProduct = sysCouponProductList.get(sysCouponProductList.size() - 1);
        assertThat(testSysCouponProduct.getResidue()).isEqualTo(UPDATED_RESIDUE);
        assertThat(testSysCouponProduct.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysCouponProduct.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysCouponProduct() throws Exception {
        int databaseSizeBeforeUpdate = sysCouponProductRepository.findAll().size();

        // Create the SysCouponProduct

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysCouponProductMockMvc.perform(put("/api/sys-coupon-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysCouponProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SysCouponProduct in the database
        List<SysCouponProduct> sysCouponProductList = sysCouponProductRepository.findAll();
        assertThat(sysCouponProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysCouponProduct() throws Exception {
        // Initialize the database
        sysCouponProductService.save(sysCouponProduct);

        int databaseSizeBeforeDelete = sysCouponProductRepository.findAll().size();

        // Get the sysCouponProduct
        restSysCouponProductMockMvc.perform(delete("/api/sys-coupon-products/{id}", sysCouponProduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysCouponProduct> sysCouponProductList = sysCouponProductRepository.findAll();
        assertThat(sysCouponProductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysCouponProduct.class);
        SysCouponProduct sysCouponProduct1 = new SysCouponProduct();
        sysCouponProduct1.setId(1L);
        SysCouponProduct sysCouponProduct2 = new SysCouponProduct();
        sysCouponProduct2.setId(sysCouponProduct1.getId());
        assertThat(sysCouponProduct1).isEqualTo(sysCouponProduct2);
        sysCouponProduct2.setId(2L);
        assertThat(sysCouponProduct1).isNotEqualTo(sysCouponProduct2);
        sysCouponProduct1.setId(null);
        assertThat(sysCouponProduct1).isNotEqualTo(sysCouponProduct2);
    }
}
