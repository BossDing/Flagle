package com.hrabbit.admin.modual.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.common.tool.ToolUtil;
import com.hrabbit.admin.core.node.SelectNode;
import com.hrabbit.admin.core.node.TreeNode;
import com.hrabbit.admin.core.shiro.bean.ShiroUser;
import com.hrabbit.admin.core.shiro.util.ShiroUtils;
import com.hrabbit.admin.modual.system.bean.SysDept;
import com.hrabbit.admin.modual.system.bean.SysRole;
import com.hrabbit.admin.modual.system.mapper.SysDeptMapper;
import com.hrabbit.admin.modual.system.mapper.SysRoleMapper;
import com.hrabbit.admin.modual.system.service.SysDeptService;
import com.hrabbit.admin.modual.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 部门业务层
 *
 * @Auther: hrabbit
 * @Date: 2019-01-28 8:14 PM
 * @Description:
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 获取部门Tree
     *
     * @return
     */
    @Override
    public List<TreeNode> findDeptTreeNode() {
        //根据groupType获取数据
        List<SysDept> groupByGroupType = sysDeptMapper.findDeptList();

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
    public PageInfo<SysDept> findDeptPageInfo(Integer pageNum, Integer pageSize, Integer parentRoleId) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<SysDept> sysGroup = sysDeptMapper.findDeptList();
        //组装数据
        PageInfo<SysDept> pageInfo = new PageInfo<>(sysGroup);
        return pageInfo;
    }


    /**
     * 获取部门下拉node
     *
     * @return
     */
    public List<SelectNode> findDeptSelectNode() {
        //根据groupType获取数据
        List<SysDept> groupByGroupType = sysDeptMapper.findDeptList();
        //创建数据仓库
        List<SelectNode> selectNodes = new ArrayList<>();
        //加载数据
        if (ToolUtil.isNotEmpty(groupByGroupType)) {
            for (SysDept sysDept : groupByGroupType) {
                SelectNode selectNode = new SelectNode();
                selectNode.setId(sysDept.getId());
                selectNode.setName(sysDept.getName());
                List<SysRole> sysRoles = sysRoleMapper.findRoleByDeptId(sysDept.getId());
                if (ToolUtil.isNotEmpty(sysRoles)) {
                    selectNode.setLinkedList(sysRoles);
                }
                selectNodes.add(selectNode);
            }
        }
        return selectNodes;
    }
}
