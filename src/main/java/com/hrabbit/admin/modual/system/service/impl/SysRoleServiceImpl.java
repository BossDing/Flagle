package com.hrabbit.admin.modual.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.exception.BussinessExceptionEnum;
import com.hrabbit.admin.core.node.SelectNode;
import com.hrabbit.admin.core.node.TreeNode;
import com.hrabbit.admin.core.response.BaseResponse;
import com.hrabbit.admin.core.shiro.bean.ShiroUser;
import com.hrabbit.admin.core.shiro.util.ShiroUtils;
import com.hrabbit.admin.modual.system.bean.SysDept;
import com.hrabbit.admin.modual.system.bean.SysRole;
import com.hrabbit.admin.modual.system.mapper.SysDeptMapper;
import com.hrabbit.admin.modual.system.mapper.SysRoleMapper;
import com.hrabbit.admin.modual.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色组业务层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-31 7:16 PM
 * @Description:
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 根据用户id获取角色信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> findRoleByUserId(Integer userId) {
        return sysRoleMapper.findRoleByUserId(userId);
    }

    /**
     * 获取数据Tree
     *
     * @return
     */
    @Override
    public List<TreeNode> findRoleTreeNode() {
        //根据groupType获取数据
        List<SysRole> groupByGroupType = sysRoleMapper.findRoleList(null,0);
        //组装到树状机构中
        ArrayList<TreeNode> groupTree = groupByGroupType.stream().collect(() -> new ArrayList<TreeNode>(),
                (list, organizations) -> list.add(new TreeNode(organizations.getId().toString(), organizations.getParentId() == null || organizations.getParentId() == -1 ? "#" : organizations.getParentId().toString(),
                        organizations.getName(), organizations.getParentId() != null && organizations.getParentId() != -1 ? "file" : null)),
                (list1, list2) -> list1.addAll(list2));
        return groupTree;
    }


    /**
     * 分页查询数据
     *
     * @param pageNum
     * @param pageSize
     * @param parentRoleId 上级id
     * @param groupType    分组类型  "部门"，"角色"
     * @author hrabbit
     * @Date: 2019/1/20 11:11 PM
     */
    public PageInfo<SysRole> findRolePageInfo(Integer pageNum, Integer pageSize, String roleName,Integer parentRoleId) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> sysGroup = sysRoleMapper.findRoleList(roleName,parentRoleId);
        //组装数据
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysGroup);
        return pageInfo;
    }

    /**
     * 根据部门id获取角色
     *
     * @param deptId
     * @return
     */
    @Override
    public SelectNode findRoleByDeptId(Integer deptId) {
        SelectNode selectNode = new SelectNode();
        SysDept sysDept = sysDeptMapper.selectById(deptId);
        BeanUtil.copyProperties(sysDept, selectNode);
        List<SysRole> roleByDeptId = sysRoleMapper.findRoleByDeptId(deptId);
        selectNode.setLinkedList(roleByDeptId);
        return selectNode;
    }


    /**
     * 添加系统角色
     *
     * @param sysRole
     * @return
     */
    @Override
    public boolean addRole(SysRole sysRole) {
        ShiroUser user = ShiroUtils.getUser();
        sysRole.setCreateBy(user.getId());
        sysRole.setCreateName(user.getName());
        sysRole.setCreateTime(new Date());
        return this.insert(sysRole);
    }

    /**
     * 修改系统角色
     *
     * @param sysRole
     * @return
     */
    @Override
    public boolean updateRole(SysRole sysRole) {
        return this.updateById(sysRole);
    }

    /**
     * 判断角色下是否存在子集
     *
     * @param roleId
     * @return
     */
    public boolean isParentRole(Integer roleId) {
        return sysRoleMapper.isParentRole(roleId) > 0 ? true : false;
    }


    /**
     * 删除角色信息
     *
     * @param roleId
     * @return
     */
    @Transactional
    @Override
    public BaseResponse delete(Integer roleId) {
        BaseResponse SuccessResponse = new BaseResponse();
        //判断子集是否存在
        boolean flag = this.isParentRole(roleId);
        //存在子集
        if (flag) {
            SuccessResponse.setErrorEnum(BussinessExceptionEnum.CANT_ROLE_HAVECHILD);
            return SuccessResponse;
        }
        super.deleteById(roleId);
        return SuccessResponse;
    }
}