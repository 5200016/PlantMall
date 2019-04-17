package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysMemberSettingService;
import com.ybb.mall.domain.SysMemberSetting;
import com.ybb.mall.repository.SysMemberSettingRepository;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.member.MemberSettingVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysMemberSetting.
 */
@Service
@Transactional
public class SysMemberSettingServiceImpl implements SysMemberSettingService {

    private final Logger log = LoggerFactory.getLogger(SysMemberSettingServiceImpl.class);

    private final SysMemberSettingRepository sysMemberSettingRepository;

    public SysMemberSettingServiceImpl(SysMemberSettingRepository sysMemberSettingRepository) {
        this.sysMemberSettingRepository = sysMemberSettingRepository;
    }

    /**
     * Save a sysMemberSetting.
     *
     * @param sysMemberSetting the entity to save
     * @return the persisted entity
     */
    @Override
    public SysMemberSetting save(SysMemberSetting sysMemberSetting) {
        log.debug("Request to save SysMemberSetting : {}", sysMemberSetting);
        return sysMemberSettingRepository.save(sysMemberSetting);
    }

    /**
     * Get all the sysMemberSettings.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysMemberSetting> findAll() {
        log.debug("Request to get all SysMemberSettings");
        return sysMemberSettingRepository.findAll();
    }


    /**
     * Get one sysMemberSetting by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysMemberSetting> findOne(Long id) {
        log.debug("Request to get SysMemberSetting : {}", id);
        return sysMemberSettingRepository.findById(id);
    }

    /**
     * Delete the sysMemberSetting by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysMemberSetting : {}", id);
        sysMemberSettingRepository.deleteById(id);
    }

    @Override
    public SysMemberSetting findSysMemberSetting() {
        return sysMemberSettingRepository.findAll().get(0);
    }

    @Override
    public ResultObj updateSysMemberSetting(MemberSettingVM memberSetting) {
        if(TypeUtils.isEmpty(memberSetting.getId())){
            return ResultObj.backInfo(false, 200, "修改失败（id不能为空）", null);
        }
        SysMemberSetting sysMemberSetting = new SysMemberSetting();
        sysMemberSetting.setId(memberSetting.getId());
        sysMemberSetting.setCheckGrowthValue(memberSetting.getCheckGrowthValue());
        sysMemberSetting.setCheckIntegralValue(memberSetting.getCheckIntegralValue());
        sysMemberSetting.setCreateTime(memberSetting.getCreateTime());
        sysMemberSetting.setGrowthProportion(memberSetting.getGrowthProportion());
        sysMemberSetting.setIntegralProportion(memberSetting.getIntegralProportion());
        sysMemberSetting.setIntegralProportionCash(memberSetting.getIntegralProportionCash());
        sysMemberSetting.setReviewGrowthValue(memberSetting.getReviewGrowthValue());
        sysMemberSetting.setReviewIntegralValue(memberSetting.getReviewIntegralValue());
        sysMemberSetting.setUpdateTime(DateUtil.getZoneDateTime());
        sysMemberSettingRepository.save(sysMemberSetting);
        return ResultObj.backInfo(true, 200, "修改成功", null);
    }
}
