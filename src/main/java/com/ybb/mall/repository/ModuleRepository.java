package com.ybb.mall.repository;

import com.ybb.mall.domain.SysModule;
import com.ybb.mall.service.dto.wx.ModuleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 首页模块
 */
@Repository
public interface ModuleRepository extends JpaRepository<SysModule, Long> {

    @Query("select sm from SysModule sm order by sm.sort asc")
    List<SysModule> findHomeModuleList();

    /**
     * 查询首页菜单列表
     */
    @Query("select sm from SysModule sm where sm.homeMenu = 1 order by sm.sort asc")
    List<SysModule> findHomeMenuList();

    /**
     * 查询首页底部菜单列表
     */
    @Query("select sm from SysModule sm where sm.homeBottom = 1 order by sm.sort asc")
    List<SysModule> findHomeBottomList();

    /**
     * 查询首页底部菜单列表
     */
    @Query("select new com.ybb.mall.service.dto.wx.ModuleDTO(sm.name, sm.icon, sm.image, sm.imageDisable, sm.type, sm.styleType, sm.homeMenu, sm.homeBottom, sm.path, sm.classify)" +
        " from SysModule sm where sm.homeBottom = 1 order by sm.sort asc")
    List<ModuleDTO> findMenuList();
}
