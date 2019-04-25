package com.ybb.mall.service;

import com.ybb.mall.domain.SysProduct;

import com.ybb.mall.service.dto.product.ProductDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.product.InsertProductVM;
import com.ybb.mall.web.rest.vm.product.UpdateProductVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysProduct.
 */
public interface SysProductService {

    /**
     * Save a sysProduct.
     *
     * @param sysProduct the entity to save
     * @return the persisted entity
     */
    SysProduct save(SysProduct sysProduct);

    /**
     * Get all the sysProducts.
     *
     * @return the list of entities
     */
    List<SysProduct> findAll();

    /**
     * Get all the SysProduct with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<SysProduct> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" sysProduct.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysProduct> findOne(Long id);

    /**
     * Delete the "id" sysProduct.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 分页模糊查询商品列表（根据创建时间倒序）
     * 条件：名称
     */
    Page<ProductDTO> findProductList(String name, Integer pageNum, Integer pageSize);

    /**
     * 新增商品
     */
    ResultObj insertProduct(InsertProductVM productVM);

    /**
     * 修改商品
     */
    ResultObj updateProduct(UpdateProductVM productVM);

    /**
     * 删除商品
     */
    ResultObj deleteProduct(Long id);
}
