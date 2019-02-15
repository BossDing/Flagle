package com.hrabbit.admin.modual.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.node.SelectNode;
import com.hrabbit.admin.core.node.TreeNode;
import com.hrabbit.admin.modual.system.bean.SysDept;
import com.hrabbit.admin.modual.system.bean.SysRole;

import java.util.List;

/**
 * 获取部门信息
 *
 * @Auther: hrabbit
 * @Date: 2019-01-28 8:13 PM
 * @Description:
 */
public interface SysDeptService extends IService<SysDept> {


    /**
     * 获取部门Tree
     *
     * @return
     */
    List<TreeNode> findDeptTreeNode();

    /**
     * 查询部门信息列表
     *
     * @param pageNum
     * @param pageSize
     * @param parentRoleId
     * @return
     */
    PageInfo<SysDept> findDeptPageInfo(Integer pageNum, Integer pageSize, Integer parentRoleId);


    /**
     * 获取部门下拉node
     *
     * @return
     */
    List<SelectNode> findDeptSelectNode();
}
