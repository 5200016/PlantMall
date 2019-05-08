package com.ybb.mall.service.impl;

import com.ybb.mall.domain.*;
import com.ybb.mall.repository.ClassifyRepository;
import com.ybb.mall.repository.CouponClassifyRepository;
import com.ybb.mall.repository.CouponProductRepository;
import com.ybb.mall.repository.CouponRepository;
import com.ybb.mall.service.CouponService;
import com.ybb.mall.service.dto.coupon.BriefDTO;
import com.ybb.mall.service.dto.coupon.BriefListDTO;
import com.ybb.mall.service.dto.product.classify.ClassifyDTO;
import com.ybb.mall.service.mapper.SysClassifyMapper;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.coupon.BriefVM;
import com.ybb.mall.web.rest.vm.coupon.InsertCouponVM;
import com.ybb.mall.web.rest.vm.coupon.UpdateCouponVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 优惠券管理
 */
@Service
@Transactional
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    private final CouponProductRepository couponProductRepository;

    private final CouponClassifyRepository couponClassifyRepository;

    private final ClassifyRepository classifyRepository;

    private final SysClassifyMapper classifyMapper;

    public CouponServiceImpl(CouponRepository couponRepository, CouponProductRepository couponProductRepository, CouponClassifyRepository couponClassifyRepository, ClassifyRepository classifyRepository, SysClassifyMapper classifyMapper) {
        this.couponRepository = couponRepository;
        this.couponProductRepository = couponProductRepository;
        this.couponClassifyRepository = couponClassifyRepository;
        this.classifyRepository = classifyRepository;
        this.classifyMapper = classifyMapper;
    }

    @Override
    public Page<SysCoupon> findCouponList(String name, Integer type, Integer pageNum, Integer pageSize) {
        Integer typeFlag = 0;
        if(!TypeUtils.isEmpty(type) && !type.equals(-1)){
            typeFlag = 1;
        }
        return couponRepository.findCouponList(name, type, typeFlag, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public ResultObj insertCoupon(InsertCouponVM couponVM) {

        // 优惠券主表入库
        SysCoupon coupon = new SysCoupon();
        coupon.setName(couponVM.getName());
        coupon.setType(couponVM.getType());
        coupon.setValue(couponVM.getValue());
        coupon.setQuantity(couponVM.getQuantity());
        coupon.setLimit(couponVM.getLimit());
        coupon.setGetNumber(0);
        coupon.setStartTime(couponVM.getStartTime());
        coupon.setEndTime(couponVM.getEndTime());
        coupon.setDescription(couponVM.getDescription());
        coupon.setMoneyOff(couponVM.getMoneyOff());
        coupon.setRange(couponVM.getRange());
        coupon.setCreateTime(DateUtil.getZoneDateTime());
        coupon.setUpdateTime(DateUtil.getZoneDateTime());
        SysCoupon sysCoupon = couponRepository.save(coupon);

        // 优惠券商品从表入库
        if(!TypeUtils.isEmpty(couponVM.getProduct())){
            insertCouponProduct(couponVM.getProduct(), sysCoupon);
        }

        // 优惠券商品及分类从表入库
        if(!TypeUtils.isEmpty(couponVM.getClassify())){
            insertCouponClassify(couponVM.getClassify(), sysCoupon);
        }
        return ResultObj.backCRUDSuccess("新增成功");
    }

    public void insertCouponProduct(List<BriefVM> productList, SysCoupon sysCoupon){
        List<SysCouponProduct> couponProducts = new ArrayList<>();
        for(BriefVM data : productList){
            SysCouponProduct info = new SysCouponProduct();
            SysProduct product = new SysProduct();
            product.setId(data.getId());

            info.setCoupon(sysCoupon);
            info.setProduct(product);
            info.setCreateTime(DateUtil.getZoneDateTime());
            info.setUpdateTime(DateUtil.getZoneDateTime());

            couponProducts.add(info);
        }
        couponProductRepository.saveAll(couponProducts);
    }

    public void insertCouponClassify(List<BriefVM> classifyList, SysCoupon sysCoupon){

        List<ClassifyDTO> classifyDTOList = classifyRepository.findSysClassifyAndProduct().stream().map(classifyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        List<BriefVM> productList = filterClassify(classifyList, classifyDTOList);
        insertCouponProduct(productList, sysCoupon);

        List<SysCouponClassify> couponClassifies = new ArrayList<>();
        for(BriefVM data : classifyList){
            SysCouponClassify info = new SysCouponClassify();
            SysClassify classify = new SysClassify();
            classify.setId(data.getId());

            info.setCoupon(sysCoupon);
            info.setClassify(classify);
            info.setCreateTime(DateUtil.getZoneDateTime());
            info.setUpdateTime(DateUtil.getZoneDateTime());

            couponClassifies.add(info);
        }
        couponClassifyRepository.saveAll(couponClassifies);
    }

    // 筛选符合条件的商品分类并生成商品集合
    public List<BriefVM> filterClassify(List<BriefVM> classifyList, List<ClassifyDTO> classifyDTOList){
        List<BriefVM> productList = new ArrayList<>();
        for(BriefVM brief : classifyList){
            for(ClassifyDTO classifyDTO : classifyDTOList){
                if(brief.getId().equals(classifyDTO.getId())){
                    List<SysProduct> sysProducts = classifyDTO.getProducts();
                    for(SysProduct item : sysProducts){
                        BriefVM product = new BriefVM();
                        product.setId(item.getId());
                        productList.add(product);
                    }
                    break;
                }
            }
        }
        return productList.parallelStream().distinct().collect(Collectors.toList());
    }

    @Override
    public ResultObj updateCoupon(UpdateCouponVM couponVM) {
        if(TypeUtils.isEmpty(couponVM.getId())){
            return ResultObj.backCRUDError("编辑失败（id不能为空）");
        }
        SysCoupon coupon = new SysCoupon();
        coupon.setId(couponVM.getId());
        coupon.setType(couponVM.getType());
        coupon.setName(couponVM.getName());
        coupon.setValue(couponVM.getValue());
        coupon.setQuantity(couponVM.getQuantity());
        coupon.setLimit(couponVM.getLimit());
        coupon.setStartTime(couponVM.getStartTime());
        coupon.setGetNumber(couponVM.getGetNumber());
        coupon.setEndTime(couponVM.getEndTime());
        coupon.setDescription(couponVM.getDescription());
        coupon.setMoneyOff(couponVM.getMoneyOff());
        coupon.setCreateTime(couponVM.getCreateTime());
        coupon.setRange(couponVM.getRange());
        coupon.setUpdateTime(DateUtil.getZoneDateTime());
        couponRepository.save(coupon);
        return ResultObj.backCRUDSuccess("编辑成功");
    }

    @Override
    public ResultObj deleteCoupon(Long id) {
        if (TypeUtils.isEmpty(id)) {
            return ResultObj.backCRUDError("删除失败（id不能为空）");
        }
        couponRepository.deleteById(id);
        return ResultObj.backCRUDSuccess("删除成功");
    }

    @Override
    public BriefListDTO findCouponProductAndClassify(Long id) {
        List<BriefDTO> product = couponProductRepository.findProductBrief(id);
        List<BriefDTO> classify = couponClassifyRepository.findClassifyBrief(id);
        return new BriefListDTO(product, classify);
    }
}
