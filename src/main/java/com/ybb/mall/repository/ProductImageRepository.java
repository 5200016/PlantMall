package com.ybb.mall.repository;

import com.ybb.mall.domain.SysProductImage;
import com.ybb.mall.service.dto.product.ProductImageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description : 商品图片
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

@Repository
public interface ProductImageRepository extends JpaRepository<SysProductImage, Long> {
    /**
     * 根据商品id删除图片
     * @param id
     */
    @Transactional
    @Modifying
    @Query("delete from SysProductImage spi where spi.product.id = ?1")
    void deleteByProductId(Long id);

    /**
     * 根据商品id查询图片
     * @param id
     */
    @Query("select new com.ybb.mall.service.dto.product.ProductImageDTO(spi.url) from SysProductImage spi" +
        " where spi.product.id = ?1" +
        " order by spi.createTime asc")
    List<ProductImageDTO> findByProductId(Long id);
}
