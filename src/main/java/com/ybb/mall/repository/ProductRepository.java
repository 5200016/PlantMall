package com.ybb.mall.repository;

import com.ybb.mall.domain.SysProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
        " order by sp.createTime desc",
        countQuery = "select count(sp) from SysProduct sp" +
            " where sp.name like concat('%', ?1, '%')" +
            " order by sp.createTime desc")
    Page<SysProduct> findProductList(String name, Pageable pageable);
}
