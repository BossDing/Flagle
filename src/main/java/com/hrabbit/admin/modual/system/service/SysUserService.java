package com.hrabbit.admin.modual.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.modual.system.bean.SysUser;

/**
 * 用户业务层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-19 11:59 AM
 * @Description:
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据登录名称查询用户信息
     *
     * @param loginName
     * @return
     */
    SysUser findByLoginName(String loginName);


    /**
     * 分页查询数据
     *
     * @param pageNum
     * @param pageSize
     * @param loginName 用户名称
     * @param roleId    角色id
     * @return
     */
    PageInfo<SysUser> pageInfo(Integer pageNum, Integer pageSize, String loginName, Integer roleId, String createTime);


    /**
     * 添加系统用户
     *
     * @author hrabbit
     * @Param: [sysUser]
     * @Date: 2019/1/12 4:19 PM
     */
    boolean addUser(SysUser sysUser, Integer[] roleId);


    /**
     * 修改系统用户
     *
     * @param sysUser
     * @param roleId
     * @return
     */
    boolean updateUser(SysUser sysUser, Integer[] roleId);


    /**
     * 删除系统用户
     *
     * @param userId
     * @return
     */
    boolean deleteUser(Integer userId);
}
