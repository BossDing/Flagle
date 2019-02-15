package com.hrabbit.admin.modual.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.hrabbit.admin.core.node.MenuNode;
import com.hrabbit.admin.modual.system.bean.SysMenu;

import java.util.List;

/**
 * 资源业务层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 3:40 PM
 * @Description:
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取角色ids下的资源信息
     *
     * @param roleIds
     * @return
     */
    List<MenuNode> findPermissionByRoleIds(List<Integer> roleIds);

    /**
     * 根据parentId获取按钮信息
     *
     * @param parentId
     * @return
     */
    List<SysMenu> findMenuByParentId(Integer parentId);
}
