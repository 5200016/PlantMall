package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.domain.SysProductImage;
import com.ybb.mall.repository.SysProductImageRepository;
import com.ybb.mall.service.SysProductService;
import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.repository.SysProductRepository;
import com.ybb.mall.service.dto.product.ProductDTO;
import com.ybb.mall.service.dto.product.ProductImageDTO;
import com.ybb.mall.service.dto.product.classify.ClassifyWebDTO;
import com.ybb.mall.service.mapper.SysProductMapper;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.product.ImageList;
import com.ybb.mall.web.rest.vm.product.InsertProductVM;
import com.ybb.mall.web.rest.vm.product.UpdateProductVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service Implementation for managing SysProduct.
 */
@Service
@Transactional
public class SysProductServiceImpl implements SysProductService {

    private final Logger log = LoggerFactory.getLogger(SysProductServiceImpl.class);

    private final SysProductRepository sysProductRepository;

    private final SysProductImageRepository sysProductImageRepository;

    private final SysProductMapper productMapper;

    public SysProductServiceImpl(SysProductRepository sysProductRepository, SysProductImageRepository sysProductImageRepository, SysProductMapper productMapper) {
        this.sysProductRepository = sysProductRepository;
        this.sysProductImageRepository = sysProductImageRepository;
        this.productMapper = productMapper;
    }

    /**
     * Save a sysProduct.
     *
     * @param sysProduct the entity to save
     * @return the persisted entity
     */
    @Override
    public SysProduct save(SysProduct sysProduct) {
        log.debug("Request to save SysProduct : {}", sysProduct);
        return sysProductRepository.save(sysProduct);
    }

    /**
     * Get all the sysProducts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysProduct> findAll() {
        log.debug("Request to get all SysProducts");
        return sysProductRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the SysProduct with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SysProduct> findAllWithEagerRelationships(Pageable pageable) {
        return sysProductRepository.findAllWithEagerRelationships(pageable);
    }


    /**
     * Get one sysProduct by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysProduct> findOne(Long id) {
        log.debug("Request to get SysProduct : {}", id);
        return sysProductRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the sysProduct by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysProduct : {}", id);
        sysProductRepository.deleteById(id);
    }

    @Override
    public Page<ProductDTO> findProductList(String name, Integer pageNum, Integer pageSize) {
        Page<ProductDTO> productPage = sysProductRepository.findProductList(name, PageRequest.of(pageNum, pageSize)).map(productMapper::toDto);
        for ( ProductDTO data : productPage.getContent()) {
            List<Long> classifyId = new ArrayList<>();
            List<ProductImageDTO> images = new ArrayList<>();

            // 查询商品分类
            if(!TypeUtils.isEmpty(data.getClassifies())){
                for (SysClassify classify : data.getClassifies()){
                    classifyId.add(classify.getId());
                }
            }

            // 查询商品图片
            if(!TypeUtils.isEmpty(data.getImages())){
                for (SysProductImage productImage : data.getImages()){
                    images.add(new ProductImageDTO(productImage.getUrl()));
                }
            }

            data.setImageList(images);
            data.setClassifyId(classifyId);
        }
        return productPage;
    }

    @Override
    public ResultObj insertProduct(InsertProductVM productVM) {

        // 入库商品表
        SysProduct product = new SysProduct();
        product.setName(productVM.getName());
        product.setLeasePrice(productVM.getLeasePrice());
        product.setInventory(productVM.getInventory());
        product.setPrice(productVM.getPrice());
        product.setDescription(productVM.getDescription());
        product.setSale(productVM.getSale());
        product.setCreateTime(DateUtil.getZoneDateTime());
        product.setClassifies(productAndClassifyRelation(productVM.getClassifyId()));
        product.setUpdateTime(DateUtil.getZoneDateTime());
        SysProduct sysProduct = sysProductRepository.save(product);

        // 入库商品图片表
        if(!TypeUtils.isEmpty(productVM.getImageList())){
            Set<SysProductImage> productImageList = new HashSet<>();
            for(ImageList imageList : productVM.getImageList()){
                SysProductImage productImage = new SysProductImage();
                productImage.setUrl(imageList.getUrl());
                productImage.setCreateTime(DateUtil.getZoneDateTime());
                productImage.setProduct(sysProduct);
                productImage.setUpdateTime(DateUtil.getZoneDateTime());
                productImageList.add(productImage);
            }
            sysProductImageRepository.saveAll(productImageList);
        }
        return ResultObj.backCRUDSuccess("新增成功");
    }

    @Override
    public ResultObj updateProduct(UpdateProductVM productVM) {
        if(TypeUtils.isEmpty(productVM.getId())){
            return ResultObj.backCRUDError("编辑失败（商品id不能为空）");
        }

        // 入库商品图片表
        Set<SysProductImage> productImageList = new HashSet<>();
        if(!TypeUtils.isEmpty(productVM.getImageList())){

            // 删除商品原图片
            sysProductImageRepository.deleteByProductId(productVM.getId());

            SysProduct product = new SysProduct();
            product.setId(productVM.getId());
            for(ImageList imageList : productVM.getImageList()){
                SysProductImage productImage = new SysProductImage();
                productImage.setUrl(imageList.getUrl());
                productImage.setProduct(product);
                productImage.setCreateTime(DateUtil.getZoneDateTime());
                productImage.setUpdateTime(DateUtil.getZoneDateTime());
                productImageList.add(productImage);
            }
            sysProductImageRepository.saveAll(productImageList);
        }

        // 入库商品表
        SysProduct product = new SysProduct();
        product.setId(productVM.getId());
        product.setName(productVM.getName());
        product.setLeasePrice(productVM.getLeasePrice());
        product.setPrice(productVM.getPrice());
        product.setInventory(productVM.getInventory());
        product.setDescription(productVM.getDescription());
        product.setSale(productVM.getSale());
        product.setCreateTime(productVM.getCreateTime());
        product.setClassifies(productAndClassifyRelation(productVM.getClassifyId()));
        product.setUpdateTime(DateUtil.getZoneDateTime());
        sysProductRepository.save(product);
        return ResultObj.backCRUDSuccess("编辑成功");
    }

    @Override
    public ResultObj deleteProduct(Long id) {
        if(TypeUtils.isEmpty(id)){
            ResultObj.backCRUDSuccess("删除失败（商品id不能为空）");
        }
        sysProductRepository.deleteById(id);
        return ResultObj.backCRUDSuccess("删除成功");
    }

    // 维护商品及其分类关联关系
    public Set<SysClassify> productAndClassifyRelation(List<Long> classifyId){
        Set<SysClassify> classifies = new HashSet<>();
        if(!TypeUtils.isEmpty(classifyId)){
            for(Long id : classifyId){
                SysClassify classify = new SysClassify();
                classify.setId(id);
                classifies.add(classify);
            }
            return classifies;
        }else {
            return null;
        }
    }
}
