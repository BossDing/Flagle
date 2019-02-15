package com.hrabbit.admin.core.common.constant.factory.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.hrabbit.admin.core.common.constant.factory.ConstantFactoryService;
import com.hrabbit.admin.core.common.tool.ToolUtil;
import com.hrabbit.admin.core.handle.SpringContextHolder;
import com.hrabbit.admin.modual.system.bean.SysRole;
import com.hrabbit.admin.modual.system.bean.SysUser;
import com.hrabbit.admin.modual.system.mapper.SysRoleMapper;
import com.hrabbit.admin.modual.system.mapper.SysUserMapper;
import com.hrabbit.admin.modual.system.service.SysMenuService;

/**
 * 常量的生产工厂
 *
 * @Auther: hrabbit
 * @Date: 2019-01-12 2:58 PM
 * @Description:
 */
public class ConstantFactoryServiceImpl implements ConstantFactoryService {

    private SysUserMapper userMapper = SpringContextHolder.getBean(SysUserMapper.class);
    private SysMenuService menuMapper = SpringContextHolder.getBean(SysMenuService.class);
    private SysRoleMapper groupMapper = SpringContextHolder.getBean(SysRoleMapper.class);

    public static ConstantFactoryService me() {
        return SpringContextHolder.getBean("constantFactoryService");
    }

    /**
     * 根据用户id获取用户名称
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:03 PM
     */
    @Override
    public String getUserNameById(Long userId) {
        SysUser user = userMapper.selectById(userId);
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }

    /**
     * 根据用户id获取用户账号
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:04 PM
     */
    @Override
    public String getLoginNameById(Long userId) {
        SysUser user = userMapper.selectById(userId);
        if (user != null) {
            return user.getLoginName();
        } else {
            return "--";
        }
    }

    /**
     * 通过角色ids获取角色名称
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:04 PM
     */
    @Override
    public String getRoleName(String roleIds) {
        if (ToolUtil.isEmpty(roleIds)) {
            return "";
        }
        Long[] roles = Convert.toLongArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (Long role : roles) {
            SysRole roleObj = groupMapper.selectById(role);
            if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(sb.toString(), ",");
    }

    /**
     * 通过角色id获取角色名称
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:04 PM
     */
    @Override
    public String getSingleRoleName(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        SysRole roleObj = groupMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    /**
     * 通过角色id获取角色英文名称
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:04 PM
     */
    @Override
    public String getSingleRoleTip(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        SysRole roleObj = groupMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getDescription();
        }
        return "";
    }
}
