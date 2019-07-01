package com.ybb.mall.repository;

import com.ybb.mall.domain.SysReview;
import com.ybb.mall.service.dto.product.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 商品评论
 */
@Repository
public interface ReviewRepository extends JpaRepository<SysReview, Long> {
    /**
     * 根据商品id分页查询商品评论
     */
    @Query("select new com.ybb.mall.service.dto.product.ReviewDTO(sr.id, su.nickname, su.avatar, sr.content, sr.level, sr.createTime)" +
        " from SysReview sr" +
        " left join SysUser su on sr.user.id = su.id" +
        " where sr.product.id = ?1" +
        " and su.nickname like concat('%', ?2, '%') " +
        " order by sr.createTime desc")
    Page<ReviewDTO> findByProductId(Long id, String nickname, Pageable pageable);

    /**
     * 根据商品id分页查询商品评论
     */
    @Query("select new com.ybb.mall.service.dto.product.ReviewDTO(sr.id, su.nickname, su.avatar, sr.content, sr.level, sr.createTime)" +
        " from SysReview sr" +
        " left join SysUser su on sr.user.id = su.id" +
        " where sr.product.id = ?1" +
        " order by sr.createTime desc")
    List<ReviewDTO> findAllByProductId(Long id);

    /**
     * 根据商品id分页查询商品评论
     */
    @Query("select new com.ybb.mall.service.dto.product.ReviewDTO(sr.id, su.nickname, su.avatar, sr.content, sr.level, sr.createTime)" +
        " from SysReview sr" +
        " left join SysUser su on sr.user.id = su.id" +
        " where sr.product.id = ?1" +
        " order by sr.createTime desc")
    Page<ReviewDTO> findAllByProductIdPage(Long id, Pageable pageable);
}
