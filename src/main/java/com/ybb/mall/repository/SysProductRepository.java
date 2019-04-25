package com.ybb.mall.repository;

import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.service.dto.product.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SysProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysProductRepository extends JpaRepository<SysProduct, Long> {

    @Query(value = "select distinct sys_product from SysProduct sys_product left join fetch sys_product.classifies",
        countQuery = "select count(distinct sys_product) from SysProduct sys_product")
    Page<SysProduct> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct sys_product from SysProduct sys_product left join fetch sys_product.classifies")
    List<SysProduct> findAllWithEagerRelationships();

    @Query("select sys_product from SysProduct sys_product left join fetch sys_product.classifies where sys_product.id =:id")
    Optional<SysProduct> findOneWithEagerRelationships(@Param("id") Long id);

    /**
     * 分页模糊查询商品列表（根据创建时间倒序）
     * 条件：名称
     */
    @Query(value = "select sp from SysProduct sp" +
        " left join fetch sp.classifies" +
        " left join fetch sp.images" +
        " where sp.name like concat('%', ?1, '%')" +
        " order by sp.createTime desc",
        countQuery = "select count(sp) from SysProduct sp" +
            " where sp.name like concat('%', ?1, '%')" +
            " order by sp.createTime desc")
    Page<SysProduct> findProductList(String name, Pageable pageable);
}
