package com.ybb.mall.service;

import com.ybb.mall.domain.SysBanner;
import com.ybb.mall.service.dto.home.BannerDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.home.banner.InsertBannerVM;
import com.ybb.mall.web.rest.vm.home.banner.UpdateBannerVM;

import java.util.List;

/**
 * @Description : 广告管理
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

public interface BannerService {

    /**
     * 根据type查询广告图片并根据sort排序
     * @param type（0：轮播图 1：图文信息）
     * @return
     */
    List<BannerDTO> findAllByTypeOrderBySortAsc(Integer type);

    /**
     * 新增广告
     * @param bannerVM
     * @return
     */
    ResultObj insertBanner(InsertBannerVM bannerVM);

    /**
     * 修改广告
     * @param bannerVM
     * @return
     */
    ResultObj updateBanner(UpdateBannerVM bannerVM);

    /**
     * 删除广告
     * @param id
     * @return
     */
    ResultObj deleteBanner(Long id);
}
