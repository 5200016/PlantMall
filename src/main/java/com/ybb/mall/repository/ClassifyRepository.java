package com.ybb.mall.repository;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.service.dto.product.classify.ClassifyWebDTO;
import com.ybb.mall.service.dto.wx.ClassifyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description : 商品分类
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

@Repository
public interface ClassifyRepository extends JpaRepository<SysClassify, Long> {

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

    /**
     * 查询商品分类列表（根据排序字段正序）
     */
    @Query("select new com.ybb.mall.service.dto.product.classify.ClassifyWebDTO(sc.id, sc.name) from SysClassify sc" +
        " order by sc.sort asc")
    List<ClassifyWebDTO> findSysClassify();

    @Query("select sc from SysClassify sc order by sc.sort asc")
    List<SysClassify> findSysClassifyAndProduct();

    /**
     * 查询商品分类列表（根据排序字段正序）
     * 条件：分类类型
     */
    @Query("select new com.ybb.mall.service.dto.product.classify.ClassifyWebDTO(sc.id, sc.name)" +
        " from SysClassify sc" +
        " where sc.type = ?1" +
        " and sc.name like concat('%', ?2, '%')" +
        " order by sc.sort asc")
    Page<ClassifyWebDTO> findClassifyByTypePage(Integer type, String name, Pageable pageable);

    /**
     * 查询商品分类列表（微信小程序）
     */
    @Query("select new com.ybb.mall.service.dto.wx.ClassifyDTO(sc.id, sc.name, sc.type) from SysClassify sc" +
        " order by sc.type asc")
    List<ClassifyDTO> findWXClassify();

    /**
     * 根据商品分类id查询商品
     */
    @Query("select sc from SysClassify sc where sc.id = ?1")
    SysClassify findSysClassifyById(Long id);
}
