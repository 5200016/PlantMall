package com.ybb.mall.repository;

import com.ybb.mall.domain.SysBanner;
import com.ybb.mall.service.dto.home.BannerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description : 广告图片
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

@Repository
public interface BannerRepository extends JpaRepository<SysBanner, Long> {

    /**
     * 根据type查询广告图片并根据sort排序
     * @param type（0：轮播图 1：图文信息）
     * @return
     */
    @Query("select new com.ybb.mall.service.dto.home.BannerDTO(sb.id, sb.path, sb.type, sb.image, sb.sort, sb.createTime, sp.id, sp.name,  sc.id, sc.name)" +
        " from SysBanner sb" +
        " left join SysClassify sc on sb.classify.id = sc.id" +
        " left join SysProduct sp on sb.product.id = sp.id" +
        " where sb.type = ?1" +
        " order by sb.sort asc")
    List<BannerDTO> findBannerByType(Integer type);
}
