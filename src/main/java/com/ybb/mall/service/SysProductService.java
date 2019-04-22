package com.ybb.mall.service;

import com.ybb.mall.domain.SysProduct;

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
}
