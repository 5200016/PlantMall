package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.repository.SysProductRepository;
import com.ybb.mall.service.SysProductService;
import com.ybb.mall.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import static com.ybb.mall.web.rest.TestUtil.sameInstant;
import static com.ybb.mall.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SysProductResource REST controller.
 *
 * @see SysProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysProductResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LEASE_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_LEASE_PRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_INVENTORY = 1;
    private static final Integer UPDATED_INVENTORY = 2;

    private static final Integer DEFAULT_SALE = 1;
    private static final Integer UPDATED_SALE = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysProductRepository sysProductRepository;

    @Mock
    private SysProductRepository sysProductRepositoryMock;

    @Mock
    private SysProductService sysProductServiceMock;

    @Autowired
    private SysProductService sysProductService;

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

    private MockMvc restSysProductMockMvc;

    private SysProduct sysProduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysProductResource sysProductResource = new SysProductResource(sysProductService);
        this.restSysProductMockMvc = MockMvcBuilders.standaloneSetup(sysProductResource)
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
    public static SysProduct createEntity(EntityManager em) {
        SysProduct sysProduct = new SysProduct()
            .name(DEFAULT_NAME)
            .leasePrice(DEFAULT_LEASE_PRICE)
            .price(DEFAULT_PRICE)
            .image(DEFAULT_IMAGE)
            .inventory(DEFAULT_INVENTORY)
            .sale(DEFAULT_SALE)
            .description(DEFAULT_DESCRIPTION)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysProduct;
    }

    @Before
    public void initTest() {
        sysProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysProduct() throws Exception {
        int databaseSizeBeforeCreate = sysProductRepository.findAll().size();

        // Create the SysProduct
        restSysProductMockMvc.perform(post("/api/sys-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysProduct)))
            .andExpect(status().isCreated());

        // Validate the SysProduct in the database
        List<SysProduct> sysProductList = sysProductRepository.findAll();
        assertThat(sysProductList).hasSize(databaseSizeBeforeCreate + 1);
        SysProduct testSysProduct = sysProductList.get(sysProductList.size() - 1);
        assertThat(testSysProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysProduct.getLeasePrice()).isEqualTo(DEFAULT_LEASE_PRICE);
        assertThat(testSysProduct.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testSysProduct.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testSysProduct.getInventory()).isEqualTo(DEFAULT_INVENTORY);
        assertThat(testSysProduct.getSale()).isEqualTo(DEFAULT_SALE);
        assertThat(testSysProduct.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSysProduct.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysProduct.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysProductRepository.findAll().size();

        // Create the SysProduct with an existing ID
        sysProduct.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysProductMockMvc.perform(post("/api/sys-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SysProduct in the database
        List<SysProduct> sysProductList = sysProductRepository.findAll();
        assertThat(sysProductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysProducts() throws Exception {
        // Initialize the database
        sysProductRepository.saveAndFlush(sysProduct);

        // Get all the sysProductList
        restSysProductMockMvc.perform(get("/api/sys-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].leasePrice").value(hasItem(DEFAULT_LEASE_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].inventory").value(hasItem(DEFAULT_INVENTORY)))
            .andExpect(jsonPath("$.[*].sale").value(hasItem(DEFAULT_SALE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllSysProductsWithEagerRelationshipsIsEnabled() throws Exception {
        SysProductResource sysProductResource = new SysProductResource(sysProductServiceMock);
        when(sysProductServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restSysProductMockMvc = MockMvcBuilders.standaloneSetup(sysProductResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSysProductMockMvc.perform(get("/api/sys-products?eagerload=true"))
        .andExpect(status().isOk());

        verify(sysProductServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllSysProductsWithEagerRelationshipsIsNotEnabled() throws Exception {
        SysProductResource sysProductResource = new SysProductResource(sysProductServiceMock);
            when(sysProductServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restSysProductMockMvc = MockMvcBuilders.standaloneSetup(sysProductResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSysProductMockMvc.perform(get("/api/sys-products?eagerload=true"))
        .andExpect(status().isOk());

            verify(sysProductServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getSysProduct() throws Exception {
        // Initialize the database
        sysProductRepository.saveAndFlush(sysProduct);

        // Get the sysProduct
        restSysProductMockMvc.perform(get("/api/sys-products/{id}", sysProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysProduct.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.leasePrice").value(DEFAULT_LEASE_PRICE.intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.inventory").value(DEFAULT_INVENTORY))
            .andExpect(jsonPath("$.sale").value(DEFAULT_SALE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysProduct() throws Exception {
        // Get the sysProduct
        restSysProductMockMvc.perform(get("/api/sys-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysProduct() throws Exception {
        // Initialize the database
        sysProductService.save(sysProduct);

        int databaseSizeBeforeUpdate = sysProductRepository.findAll().size();

        // Update the sysProduct
        SysProduct updatedSysProduct = sysProductRepository.findById(sysProduct.getId()).get();
        // Disconnect from session so that the updates on updatedSysProduct are not directly saved in db
        em.detach(updatedSysProduct);
        updatedSysProduct
            .name(UPDATED_NAME)
            .leasePrice(UPDATED_LEASE_PRICE)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .inventory(UPDATED_INVENTORY)
            .sale(UPDATED_SALE)
            .description(UPDATED_DESCRIPTION)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysProductMockMvc.perform(put("/api/sys-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysProduct)))
            .andExpect(status().isOk());

        // Validate the SysProduct in the database
        List<SysProduct> sysProductList = sysProductRepository.findAll();
        assertThat(sysProductList).hasSize(databaseSizeBeforeUpdate);
        SysProduct testSysProduct = sysProductList.get(sysProductList.size() - 1);
        assertThat(testSysProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysProduct.getLeasePrice()).isEqualTo(UPDATED_LEASE_PRICE);
        assertThat(testSysProduct.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testSysProduct.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testSysProduct.getInventory()).isEqualTo(UPDATED_INVENTORY);
        assertThat(testSysProduct.getSale()).isEqualTo(UPDATED_SALE);
        assertThat(testSysProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSysProduct.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysProduct.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysProduct() throws Exception {
        int databaseSizeBeforeUpdate = sysProductRepository.findAll().size();

        // Create the SysProduct

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysProductMockMvc.perform(put("/api/sys-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysProduct)))
            .andExpect(status().isBadRequest());

        // Validate the SysProduct in the database
        List<SysProduct> sysProductList = sysProductRepository.findAll();
        assertThat(sysProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysProduct() throws Exception {
        // Initialize the database
        sysProductService.save(sysProduct);

        int databaseSizeBeforeDelete = sysProductRepository.findAll().size();

        // Get the sysProduct
        restSysProductMockMvc.perform(delete("/api/sys-products/{id}", sysProduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysProduct> sysProductList = sysProductRepository.findAll();
        assertThat(sysProductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysProduct.class);
        SysProduct sysProduct1 = new SysProduct();
        sysProduct1.setId(1L);
        SysProduct sysProduct2 = new SysProduct();
        sysProduct2.setId(sysProduct1.getId());
        assertThat(sysProduct1).isEqualTo(sysProduct2);
        sysProduct2.setId(2L);
        assertThat(sysProduct1).isNotEqualTo(sysProduct2);
        sysProduct1.setId(null);
        assertThat(sysProduct1).isNotEqualTo(sysProduct2);
    }
}
