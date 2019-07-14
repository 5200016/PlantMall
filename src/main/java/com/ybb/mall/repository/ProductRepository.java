package com.ybb.mall.repository;

import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.service.dto.product.ProductBriefDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Description : 商品
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

@Repository
public interface ProductRepository extends JpaRepository<SysProduct, Long> {

    /**
     * 分页模糊查询商品列表（根据创建时间倒序）
     * 条件：名称
     */
    @Query(value = "select sp from SysProduct sp" +
        " left join fetch sp.classifies" +
        " left join fetch sp.images" +
        " where sp.name like concat('%', ?1, '%')" +
        " and sp.active = true" +
        " order by sp.createTime desc",
        countQuery = "select count(sp) from SysProduct sp" +
            " where sp.name like concat('%', ?1, '%')" +
            " order by sp.createTime desc")
    Page<SysProduct> findProductList(String name, Pageable pageable);

    /**
     * 查询商品列表（简略信息：id，name，inventory）
     */
    @Query("select new com.ybb.mall.service.dto.product.ProductBriefDTO(sp.id, sp.name, sp.inventory, sp.leasePrice)" +
        " from SysProduct sp" +
        " where sp.active = true")
    List<ProductBriefDTO> findProductBrief();

    /**
     * 分页查询商品列表（简略信息：id，name，inventory）
     * 条件：名称
     */
    @Query("select new com.ybb.mall.service.dto.product.ProductBriefDTO(sp.id, sp.name, sp.inventory)" +
        " from SysProduct sp" +
        " where sp.name like concat('%', ?1, '%')" +
        " and sp.active = true")
    Page<ProductBriefDTO> findProductBriefByName(String name, Pageable pageable);

    /**
     * 根据id查询商品
     */
    SysProduct findSysProductById(Long id);

    /**
     * 分页模糊查询商品列表（微信小程序）
     * 条件：名称
     */
    @Query(value = "select distinct sp from SysProduct sp" +
        " left join fetch sp.classifies" +
        " where sp.name like concat('%', ?1, '%')" +
        " and sp.active = true")
    List<SysProduct> findWXProductList(String name);

    /**
     * 根据id查询商品详情（微信小程序）
     */
    @Query(value = "select distinct sp from SysProduct sp" +
        " left join fetch sp.images" +
        " where sp.id = ?1")
    Optional<SysProduct> findWXProductById(Long id);
}
