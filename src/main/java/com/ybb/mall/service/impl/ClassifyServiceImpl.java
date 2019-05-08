package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.repository.ClassifyRepository;
import com.ybb.mall.service.ClassifyService;
import com.ybb.mall.service.dto.product.cascade.ClassifyCascadeDTO;
import com.ybb.mall.service.dto.product.cascade.ProductCascadeDTO;
import com.ybb.mall.service.dto.product.classify.ClassifyDTO;
import com.ybb.mall.service.dto.product.classify.ClassifyGroupDTO;
import com.ybb.mall.service.dto.product.classify.ClassifyWebDTO;
import com.ybb.mall.service.mapper.SysClassifyMapper;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.product.classify.InsertClassifyVM;
import com.ybb.mall.web.rest.vm.product.classify.UpdateClassifyVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

@Service
@Transactional
public class ClassifyServiceImpl implements ClassifyService {

    private final ClassifyRepository classifyRepository;

    private final SysClassifyMapper classifyMapper;

    public ClassifyServiceImpl(ClassifyRepository classifyRepository, SysClassifyMapper classifyMapper) {
        this.classifyRepository = classifyRepository;
        this.classifyMapper = classifyMapper;
    }

    @Override
    public Page<ClassifyDTO> findClassifyList(String name, Integer type, Integer pageNum, Integer pageSize) {
        Integer typeFlag = 0;
        if (!TypeUtils.isEmpty(type) && !type.equals(-1)) {
            typeFlag = 1;
        }
        return classifyRepository.findSysClassifyList(name, type, typeFlag, PageRequest.of(pageNum, pageSize)).map(classifyMapper::toDto);
    }

    @Override
    public ResultObj insertClassify(InsertClassifyVM classifyVM) {
        SysClassify classify = new SysClassify();
        classify.setName(classifyVM.getName());
        classify.setType(classifyVM.getType());
        classify.setCreateTime(DateUtil.getZoneDateTime());
        classify.setSort(classifyVM.getSort());
        classify.setUpdateTime(DateUtil.getZoneDateTime());
        classifyRepository.save(classify);
        return ResultObj.backInfo(true, 200, "新增成功", null);
    }

    @Override
    public ResultObj updateClassify(UpdateClassifyVM classifyVM) {
        if (TypeUtils.isEmpty(classifyVM.getId())) {
            return ResultObj.backInfo(false, 200, "编辑失败（id不能为空）", null);
        }
        SysClassify classify = new SysClassify();
        classify.setId(classifyVM.getId());
        classify.setName(classifyVM.getName());
        classify.setType(classifyVM.getType());
        classify.setCreateTime(classifyVM.getCreateTime());
        classify.setSort(classifyVM.getSort());
        classify.setUpdateTime(DateUtil.getZoneDateTime());
        classifyRepository.save(classify);
        return ResultObj.backInfo(true, 200, "编辑成功", null);
    }

    @Override
    public ResultObj deleteClassify(Long id) {
        if (TypeUtils.isEmpty(id)) {
            return ResultObj.backInfo(false, 200, "删除失败（id不能为空）", null);
        }
        classifyRepository.deleteById(id);
        return ResultObj.backInfo(true, 200, "删除成功", null);
    }

    @Override
    public ClassifyGroupDTO findSysClassifyGroup() {
        return new ClassifyGroupDTO(
            classifyRepository.findSysClassifyByType(0),
            classifyRepository.findSysClassifyByType(1)
        );
    }

    @Override
    public List<ClassifyCascadeDTO> findClassifyAndProduct() {
        List<ClassifyDTO> classifyDTOList = classifyRepository.findSysClassifyAndProduct().stream().map(classifyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        List<ClassifyCascadeDTO> result = new ArrayList<>();
        for(ClassifyDTO data : classifyDTOList){
            ClassifyCascadeDTO cascadeDTO = new ClassifyCascadeDTO();
            cascadeDTO.setLabel(data.getName());
            cascadeDTO.setValue(data.getId());
            List<ProductCascadeDTO> resultCascade = new ArrayList<>();
            if(!TypeUtils.isEmpty(data.getProducts())){
                for(SysProduct product : data.getProducts()){
                    ProductCascadeDTO productCascadeDTO = new ProductCascadeDTO();
                    productCascadeDTO.setValue(product.getId());
                    productCascadeDTO.setLabel(product.getName());
                    resultCascade.add(productCascadeDTO);
                }
            }
            cascadeDTO.setChildren(resultCascade);
            result.add(cascadeDTO);
        }
        return result;
    }

    @Override
    public List<ClassifyCascadeDTO> findClassifyCascade() {
        List<ClassifyWebDTO> classifyWebDTO = classifyRepository.findSysClassify();
        List<ClassifyCascadeDTO> result = new ArrayList<>();

        for(ClassifyWebDTO data : classifyWebDTO){
            ClassifyCascadeDTO classifyCascade = new ClassifyCascadeDTO();
            classifyCascade.setValue(data.getId());
            classifyCascade.setLabel(data.getName());
            result.add(classifyCascade);
        }
        return result;
    }

    @Override
    public Page<ClassifyWebDTO> findSellClassifyPage(Integer type, String name, Integer pageNum, Integer pageSize) {
        return classifyRepository.findClassifyByTypePage(type, name, PageRequest.of(pageNum, pageSize));
    }

}
