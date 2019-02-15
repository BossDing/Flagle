package com.hrabbit.admin.modual.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hrabbit.admin.core.node.MenuNode;
import com.hrabbit.admin.modual.system.bean.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源数据库层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 3:40 PM
 * @Description:
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色Ids获取资源信息
     *
     * @param roleIds
     * @return
     */
    List<MenuNode> findPermissionByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据parentId获取按钮信息
     *
     * @param parentId
     * @return
     */
    List<SysMenu> findMenuByParentId(@Param("parentId") Integer parentId);
}