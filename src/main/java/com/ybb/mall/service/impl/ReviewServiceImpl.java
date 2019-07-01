package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysReview;
import com.ybb.mall.repository.ReviewRepository;
import com.ybb.mall.service.ReviewService;
import com.ybb.mall.service.dto.product.ReviewDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 商品评论
 * @Author 黄志成
 * @Date 2019-06-10
 * @Version
 */

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Page<ReviewDTO> findByProductId(Long id, String nickname, Integer pageNum, Integer pageSize) {
        return reviewRepository.findByProductId(id, nickname, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public ResultObj deleteProductReview(Long id) {
        if(TypeUtils.isEmpty(id)){
            return ResultObj.backCRUDError("删除失败（id不能为空）");
        }
        reviewRepository.deleteById(id);
        return ResultObj.backCRUDSuccess("删除成功");
    }

    @Override
    public ResultObj deleteProductReviewBatch(List<Long> id) {
        if(TypeUtils.isEmpty(id)){
            return ResultObj.backCRUDError("请选择删除项");
        }
        List<SysReview> reviews = new ArrayList<>();
        for (Long data : id){
            SysReview sysReview = new SysReview();
            sysReview.setId(data);
            reviews.add(sysReview);
        }
        reviewRepository.deleteInBatch(reviews);
        return ResultObj.backCRUDSuccess("删除成功");
    }

    @Override
    public ResultObj findProductReviewList(Long id) {
        List<ReviewDTO> reviewList = reviewRepository.findAllByProductId(id);
        return ResultObj.back(200, reviewList);
    }

    @Override
    public ResultObj findProductReviewListPage(Long id, Integer pageNum, Integer pageSize) {
        Page<ReviewDTO> reviewList = reviewRepository.findAllByProductIdPage(id, PageRequest.of(pageNum, pageSize));
        return ResultObj.back(200, reviewList);
    }
}
