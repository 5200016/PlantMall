package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysReviewService;
import com.ybb.mall.domain.SysReview;
import com.ybb.mall.repository.SysReviewRepository;
import com.ybb.mall.service.dto.product.ReviewDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysReview.
 */
@Service
@Transactional
public class SysReviewServiceImpl implements SysReviewService {

    private final Logger log = LoggerFactory.getLogger(SysReviewServiceImpl.class);

    private final SysReviewRepository sysReviewRepository;

    public SysReviewServiceImpl(SysReviewRepository sysReviewRepository) {
        this.sysReviewRepository = sysReviewRepository;
    }

    /**
     * Save a sysReview.
     *
     * @param sysReview the entity to save
     * @return the persisted entity
     */
    @Override
    public SysReview save(SysReview sysReview) {
        log.debug("Request to save SysReview : {}", sysReview);
        return sysReviewRepository.save(sysReview);
    }

    /**
     * Get all the sysReviews.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysReview> findAll() {
        log.debug("Request to get all SysReviews");
        return sysReviewRepository.findAll();
    }


    /**
     * Get one sysReview by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysReview> findOne(Long id) {
        log.debug("Request to get SysReview : {}", id);
        return sysReviewRepository.findById(id);
    }

    /**
     * Delete the sysReview by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysReview : {}", id);
        sysReviewRepository.deleteById(id);
    }

    @Override
    public Page<ReviewDTO> findByProductId(Long id, String nickname, Integer pageNum, Integer pageSize) {
        return sysReviewRepository.findByProductId(id, nickname, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public ResultObj deleteProductReview(Long id) {
        if(TypeUtils.isEmpty(id)){
            return ResultObj.backCRUDError("删除失败（id不能为空）");
        }
        sysReviewRepository.deleteById(id);
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
        sysReviewRepository.deleteInBatch(reviews);
        return ResultObj.backCRUDSuccess("删除成功");
    }
}
