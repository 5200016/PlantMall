package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysBanner;
import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.repository.BannerRepository;
import com.ybb.mall.repository.ClassifyRepository;
import com.ybb.mall.service.BannerService;
import com.ybb.mall.service.dto.home.BannerDTO;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.home.banner.InsertBannerVM;
import com.ybb.mall.web.rest.vm.home.banner.UpdateBannerVM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 轮播管理
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    private final ClassifyRepository classifyRepository;

    public BannerServiceImpl(BannerRepository bannerRepository, ClassifyRepository classifyRepository) {
        this.bannerRepository = bannerRepository;
        this.classifyRepository = classifyRepository;
    }

    @Override
    public List<BannerDTO> findAllByTypeOrderBySortAsc(Integer type) {
        List<BannerDTO> result = bannerRepository.findBannerByType(type);
        if(!TypeUtils.isEmpty(result)){
            for(BannerDTO data : result){
                List<Long> cascadeId = new ArrayList<>();
                if(!TypeUtils.isEmpty(data.getClassifyId())){
                    cascadeId.add(data.getClassifyId());
                }
                if(!TypeUtils.isEmpty(data.getProductId())){
                    cascadeId.add(data.getProductId());
                }
                data.setCascadeId(cascadeId);
            }
        }
        return result;
    }

    @Override
    public ResultObj insertBanner(InsertBannerVM bannerVM) {
        SysBanner banner = new SysBanner();
        banner.setImage(bannerVM.getImage());
        banner.setSort(bannerVM.getSort());
        banner.setType(bannerVM.getType());

        banner.setCreateTime(DateUtil.getZoneDateTime());
        banner.setUpdateTime(DateUtil.getZoneDateTime());

        if(!TypeUtils.isEmpty(bannerVM.getCascadeId())){
            SysClassify sysClassify = classifyRepository.findSysClassifyById(bannerVM.getCascadeId().get(0));
            SysProduct sysProduct = new SysProduct();
            Integer size = bannerVM.getCascadeId().size();
            if(size.equals(1)){
                banner.setPath("pages/product/list/index?id=" + sysClassify.getId() + "&classifyType=" + sysClassify.getType() + "&classifyId=" + sysClassify.getId());
                banner.setClassify(sysClassify);
            }
            if(size.equals(2)){
                Long productId = bannerVM.getCascadeId().get(1);
                banner.setPath("pages/product/detail/index?id=" + productId + "&classifyType=" + sysClassify.getType());
                banner.setClassify(sysClassify);
                sysProduct.setId(productId);
                banner.setProduct(sysProduct);
            }
        }
        bannerRepository.save(banner);
        return ResultObj.backCRUDSuccess("新增成功");
    }

    @Override
    public ResultObj updateBanner(UpdateBannerVM bannerVM) {
        if(TypeUtils.isEmpty(bannerVM.getId())){
            return ResultObj.backCRUDError("编辑失败（id不能为空）");
        }
        SysBanner banner = new SysBanner();
        banner.setId(bannerVM.getId());
        banner.setImage(bannerVM.getImage());
        banner.setSort(bannerVM.getSort());
        banner.setPath(bannerVM.getPath());
        banner.setType(bannerVM.getType());
        banner.setCreateTime(bannerVM.getCreateTime());
        banner.setUpdateTime(DateUtil.getZoneDateTime());

        if(!TypeUtils.isEmpty(bannerVM.getCascadeId())){

            SysProduct sysProduct = new SysProduct();
            SysClassify sysClassify = classifyRepository.findSysClassifyById(bannerVM.getCascadeId().get(0));
            Integer size = bannerVM.getCascadeId().size();
            if(size.equals(2)){
                Long productId = bannerVM.getCascadeId().get(1);
                sysProduct.setId(productId);
                banner.setPath("pages/product/detail/index?id=" + productId + "&classifyType=" + sysClassify.getType());
                banner.setClassify(sysClassify);
                banner.setProduct(sysProduct);
            }
            if(size.equals(1)){
                banner.setPath("pages/product/list/index?id=" + sysClassify.getId() + "&classifyType=" + sysClassify.getType() + "&classifyId=" + sysClassify.getId());
                banner.setClassify(sysClassify);
            }

        }

        bannerRepository.save(banner);
        return ResultObj.backCRUDSuccess("编辑成功");
    }

    @Override
    public ResultObj deleteBanner(Long id) {
        if(TypeUtils.isEmpty(id)){
            return ResultObj.backCRUDError("删除失败（id不能为空）");
        }
        bannerRepository.deleteById(id);
        return ResultObj.backCRUDSuccess("删除成功");
    }
}
