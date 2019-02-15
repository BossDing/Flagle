package com.hrabbit.admin.modual.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hrabbit.admin.core.node.MenuNode;
import com.hrabbit.admin.modual.system.bean.SysMenu;
import com.hrabbit.admin.modual.system.mapper.SysMenuMapper;
import com.hrabbit.admin.modual.system.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源业务层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 3:40 PM
 * @Description:
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 获取角色ids下的资源信息
     *
     * @param roleIds
     * @return
     */
    @Override
    public List<MenuNode> findPermissionByRoleIds(List<Integer> roleIds) {
        //获取到资源
        List<MenuNode> permissionByRoleIds = sysMenuMapper.findPermissionByRoleIds(roleIds);
        //组装资源
        permissionByRoleIds = MenuNode.buildTitle(permissionByRoleIds);
        log.info("资源=>{}", permissionByRoleIds);
        return permissionByRoleIds;
    }


    /**
     * 根据parentId获取按钮信息
     *
     * @param parentId
     * @return
     */
    @Override
    public List<SysMenu> findMenuByParentId(Integer parentId) {
        return sysMenuMapper.findMenuByParentId(parentId);
    }

}
