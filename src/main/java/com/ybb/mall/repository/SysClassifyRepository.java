package com.ybb.mall.repository;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.service.dto.product.classify.ClassifyWebDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SysClassify entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysClassifyRepository extends JpaRepository<SysClassify, Long> {

    /**
     * 分页模糊查询商品分类列表（根据排序字段正序）
     * 条件：名称, 类型
     */
    @Query("select sc from SysClassify sc where" +
        " sc.name like concat('%', ?1, '%')" +
        " and ( 0 = ?3 or sc.type = ?2)" +
        " order by sc.sort asc")
    Page<SysClassify> findSysClassifyList(String name, Integer type, Integer typeFlag, Pageable pageable);

    /**
     * 根据类型查询商品分类列表（根据排序字段正序）
     * 条件：类型
     */
    @Query("select new com.ybb.mall.service.dto.product.classify.ClassifyWebDTO(sc.id, sc.name) from SysClassify sc" +
        " where sc.type = ?1" +
        " order by sc.sort asc")
    List<ClassifyWebDTO> findSysClassifyByType(Integer type);
}
