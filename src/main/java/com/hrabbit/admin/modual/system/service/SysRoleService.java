package com.hrabbit.admin.modual.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.node.SelectNode;
import com.hrabbit.admin.core.node.TreeNode;
import com.hrabbit.admin.core.response.BaseResponse;
import com.hrabbit.admin.modual.system.bean.SysRole;

import java.util.List;

/**
 * 组业务层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-31 7:14 PM
 * @Description:
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据用户id获取角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> findRoleByUserId(Integer userId);

    /**
     * 获取数据Tree
     *
     * @return
     */
    List<TreeNode> findRoleTreeNode();

    /**
     * @param pageNum
     * @param pageSize
     * @param roleName
     * @param parentRoleId 上级id
     * @author hrabbit
     * @Date: 2019/1/20 11:11 PM
     */
    PageInfo<SysRole> findRolePageInfo(Integer pageNum, Integer pageSize, String roleName,Integer parentRoleId);


    /**
     * 根据部门id获取角色
     *
     * @param deptId
     * @return
     */
    SelectNode findRoleByDeptId(Integer deptId);


    /**
     * 添加系统角色
     *
     * @param sysRole
     * @return
     */
    boolean addRole(SysRole sysRole);


    /**
     * 修改系统角色
     *
     * @param sysRole
     * @return
     */
    boolean updateRole(SysRole sysRole);

    /**
     * 判断角色下是否存在子集
     *
     * @param roleId
     * @return
     */
    boolean isParentRole(Integer roleId);

    /**
     * 删除角色信息
     *
     * @param roleId
     * @return
     */
    BaseResponse delete(Integer roleId);
}
