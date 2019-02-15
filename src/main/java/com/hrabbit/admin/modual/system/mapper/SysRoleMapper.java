package com.hrabbit.admin.modual.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hrabbit.admin.modual.system.bean.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分组数据库层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-31 7:07 PM
 * @Description:
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id获取角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> findRoleByUserId(@Param("userId") Integer userId);


    /**
     * 获取角色信息
     * @param roleName
     * @param parentRoleId
     * @return
     */
    List<SysRole> findRoleList(@Param("roleName") String roleName,@Param("parentRoleId") Integer parentRoleId);

    /**
     * 根据部门id获取角色信息
     *
     * @param deptId
     * @return
     */
    List<SysRole> findRoleByDeptId(@Param("deptId") Integer deptId);


    /**
     * 查询角色下是否存在子集
     *
     * @param roleId
     * @return
     */
    Integer isParentRole(@Param("roleId") Integer roleId);
}
