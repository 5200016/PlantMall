package com.ybb.mall.service;

import com.ybb.mall.domain.SysReview;
import com.ybb.mall.service.dto.product.ReviewDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品评论
 */
public interface ReviewService {

    /**
     * 根据商品id分页查询商品评论
     */
    Page<ReviewDTO> findByProductId(Long id, String nickname, Integer pageNum, Integer pageSize);

    /**
     * 根据id删除商品评论
     */
    ResultObj deleteProductReview(Long id);

    /**
     * 批量删除商品评论
     */
    ResultObj deleteProductReviewBatch(List<Long> id);

    /**
     * 根据商品id查询评论列表
     */
    ResultObj findProductReviewList(Long id);

    /**
     * 根据商品id查询评论列表
     */
    ResultObj findProductReviewListPage(Long id, Integer pageNum, Integer pageSize);

}
