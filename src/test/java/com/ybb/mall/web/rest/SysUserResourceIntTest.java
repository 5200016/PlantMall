package com.ybb.mall.web.rest;

import com.ybb.mall.PlantMallApp;

import com.ybb.mall.domain.SysUser;
import com.ybb.mall.repository.SysUserRepository;
import com.ybb.mall.service.SysUserService;
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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
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
 * Test class for the SysUserResource REST controller.
 *
 * @see SysUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlantMallApp.class)
public class SysUserResourceIntTest {

    private static final String DEFAULT_OPENID = "AAAAAAAAAA";
    private static final String UPDATED_OPENID = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_SESSION_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final String DEFAULT_NICKNAME = "AAAAAAAAAA";
    private static final String UPDATED_NICKNAME = "BBBBBBBBBB";

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGRAL = 1;
    private static final Integer UPDATED_INTEGRAL = 2;

    private static final Integer DEFAULT_GROWTH_VALUE = 1;
    private static final Integer UPDATED_GROWTH_VALUE = 2;

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SysUserRepository sysUserRepository;

    @Mock
    private SysUserRepository sysUserRepositoryMock;

    @Mock
    private SysUserService sysUserServiceMock;

    @Autowired
    private SysUserService sysUserService;

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

    private MockMvc restSysUserMockMvc;

    private SysUser sysUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysUserResource sysUserResource = new SysUserResource(sysUserService);
        this.restSysUserMockMvc = MockMvcBuilders.standaloneSetup(sysUserResource)
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
    public static SysUser createEntity(EntityManager em) {
        SysUser sysUser = new SysUser()
            .openid(DEFAULT_OPENID)
            .phone(DEFAULT_PHONE)
            .sessionKey(DEFAULT_SESSION_KEY)
            .username(DEFAULT_USERNAME)
            .avatar(DEFAULT_AVATAR)
            .nickname(DEFAULT_NICKNAME)
            .sex(DEFAULT_SEX)
            .integral(DEFAULT_INTEGRAL)
            .growthValue(DEFAULT_GROWTH_VALUE)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return sysUser;
    }

    @Before
    public void initTest() {
        sysUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysUser() throws Exception {
        int databaseSizeBeforeCreate = sysUserRepository.findAll().size();

        // Create the SysUser
        restSysUserMockMvc.perform(post("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUser)))
            .andExpect(status().isCreated());

        // Validate the SysUser in the database
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeCreate + 1);
        SysUser testSysUser = sysUserList.get(sysUserList.size() - 1);
        assertThat(testSysUser.getOpenid()).isEqualTo(DEFAULT_OPENID);
        assertThat(testSysUser.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSysUser.getSessionKey()).isEqualTo(DEFAULT_SESSION_KEY);
        assertThat(testSysUser.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testSysUser.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testSysUser.getNickname()).isEqualTo(DEFAULT_NICKNAME);
        assertThat(testSysUser.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testSysUser.getIntegral()).isEqualTo(DEFAULT_INTEGRAL);
        assertThat(testSysUser.getGrowthValue()).isEqualTo(DEFAULT_GROWTH_VALUE);
        assertThat(testSysUser.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSysUser.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createSysUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysUserRepository.findAll().size();

        // Create the SysUser with an existing ID
        sysUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysUserMockMvc.perform(post("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUser)))
            .andExpect(status().isBadRequest());

        // Validate the SysUser in the database
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSysUsers() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList
        restSysUserMockMvc.perform(get("/api/sys-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].openid").value(hasItem(DEFAULT_OPENID.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].sessionKey").value(hasItem(DEFAULT_SESSION_KEY.toString())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
            .andExpect(jsonPath("$.[*].nickname").value(hasItem(DEFAULT_NICKNAME.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].integral").value(hasItem(DEFAULT_INTEGRAL)))
            .andExpect(jsonPath("$.[*].growthValue").value(hasItem(DEFAULT_GROWTH_VALUE)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllSysUsersWithEagerRelationshipsIsEnabled() throws Exception {
        SysUserResource sysUserResource = new SysUserResource(sysUserServiceMock);
        when(sysUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restSysUserMockMvc = MockMvcBuilders.standaloneSetup(sysUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSysUserMockMvc.perform(get("/api/sys-users?eagerload=true"))
        .andExpect(status().isOk());

        verify(sysUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllSysUsersWithEagerRelationshipsIsNotEnabled() throws Exception {
        SysUserResource sysUserResource = new SysUserResource(sysUserServiceMock);
            when(sysUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restSysUserMockMvc = MockMvcBuilders.standaloneSetup(sysUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSysUserMockMvc.perform(get("/api/sys-users?eagerload=true"))
        .andExpect(status().isOk());

            verify(sysUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getSysUser() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get the sysUser
        restSysUserMockMvc.perform(get("/api/sys-users/{id}", sysUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysUser.getId().intValue()))
            .andExpect(jsonPath("$.openid").value(DEFAULT_OPENID.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.sessionKey").value(DEFAULT_SESSION_KEY.toString()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()))
            .andExpect(jsonPath("$.nickname").value(DEFAULT_NICKNAME.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.integral").value(DEFAULT_INTEGRAL))
            .andExpect(jsonPath("$.growthValue").value(DEFAULT_GROWTH_VALUE))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingSysUser() throws Exception {
        // Get the sysUser
        restSysUserMockMvc.perform(get("/api/sys-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysUser() throws Exception {
        // Initialize the database
        sysUserService.save(sysUser);

        int databaseSizeBeforeUpdate = sysUserRepository.findAll().size();

        // Update the sysUser
        SysUser updatedSysUser = sysUserRepository.findById(sysUser.getId()).get();
        // Disconnect from session so that the updates on updatedSysUser are not directly saved in db
        em.detach(updatedSysUser);
        updatedSysUser
            .openid(UPDATED_OPENID)
            .phone(UPDATED_PHONE)
            .sessionKey(UPDATED_SESSION_KEY)
            .username(UPDATED_USERNAME)
            .avatar(UPDATED_AVATAR)
            .nickname(UPDATED_NICKNAME)
            .sex(UPDATED_SEX)
            .integral(UPDATED_INTEGRAL)
            .growthValue(UPDATED_GROWTH_VALUE)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restSysUserMockMvc.perform(put("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysUser)))
            .andExpect(status().isOk());

        // Validate the SysUser in the database
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeUpdate);
        SysUser testSysUser = sysUserList.get(sysUserList.size() - 1);
        assertThat(testSysUser.getOpenid()).isEqualTo(UPDATED_OPENID);
        assertThat(testSysUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSysUser.getSessionKey()).isEqualTo(UPDATED_SESSION_KEY);
        assertThat(testSysUser.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testSysUser.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testSysUser.getNickname()).isEqualTo(UPDATED_NICKNAME);
        assertThat(testSysUser.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testSysUser.getIntegral()).isEqualTo(UPDATED_INTEGRAL);
        assertThat(testSysUser.getGrowthValue()).isEqualTo(UPDATED_GROWTH_VALUE);
        assertThat(testSysUser.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSysUser.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSysUser() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRepository.findAll().size();

        // Create the SysUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserMockMvc.perform(put("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUser)))
            .andExpect(status().isBadRequest());

        // Validate the SysUser in the database
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysUser() throws Exception {
        // Initialize the database
        sysUserService.save(sysUser);

        int databaseSizeBeforeDelete = sysUserRepository.findAll().size();

        // Get the sysUser
        restSysUserMockMvc.perform(delete("/api/sys-users/{id}", sysUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUser.class);
        SysUser sysUser1 = new SysUser();
        sysUser1.setId(1L);
        SysUser sysUser2 = new SysUser();
        sysUser2.setId(sysUser1.getId());
        assertThat(sysUser1).isEqualTo(sysUser2);
        sysUser2.setId(2L);
        assertThat(sysUser1).isNotEqualTo(sysUser2);
        sysUser1.setId(null);
        assertThat(sysUser1).isNotEqualTo(sysUser2);
    }
}
