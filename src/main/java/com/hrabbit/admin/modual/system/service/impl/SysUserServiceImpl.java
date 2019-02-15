package com.hrabbit.admin.modual.system.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.common.tool.ToolUtil;
import com.hrabbit.admin.core.shiro.bean.ShiroUser;
import com.hrabbit.admin.core.shiro.util.ShiroUtils;
import com.hrabbit.admin.modual.system.bean.SysRoleUser;
import com.hrabbit.admin.modual.system.bean.SysUser;
import com.hrabbit.admin.modual.system.mapper.SysUserMapper;
import com.hrabbit.admin.modual.system.service.SysRoleUserService;
import com.hrabbit.admin.modual.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统用户业务层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-19 12:01 PM
 * @Description:
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    /**
     * 根据登录名称查询用户信息
     *
     * @param loginName
     * @return
     */
    @Override
    public SysUser findByLoginName(String loginName) {
        return sysUserMapper.findByLoginName(loginName);
    }

    /**
     * 分页查询数据
     *
     * @param pageNum
     * @param pageSize
     * @param loginName
     * @param roleId
     * @return
     */
    @Override
    public PageInfo<SysUser> pageInfo(Integer pageNum, Integer pageSize, String loginName, Integer roleId, String createTime) {
        //设置分页
        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        List<SysUser> listUser = sysUserMapper.findListUser(loginName, roleId, createTime);
        PageInfo<SysUser> pageInfo = new PageInfo<>(listUser);
        return pageInfo;
    }


    /**
     * 添加系统用户
     *
     * @author hrabbit
     * @Param: [sysUser]
     * @Date: 2019/1/12 4:19 PM
     */
    @Override
    public boolean addUser(SysUser sysUser, Integer[] roleId) {
        //获取当前登录人信息
        ShiroUser shiroUser = ShiroUtils.getUser();
        //完善用户信息
        sysUser.setSalt(ShiroUtils.getRandomSalt(5));
        sysUser.setEnabled(0);
        sysUser.setCreateTime(new Date());
        if (ToolUtil.isNotEmpty(roleId)) {
            String roleIds = StringUtils.join(roleId, ",");
            sysUser.setRoleIds(roleIds);
        }
        sysUser.setPassword(ShiroUtils.md5(sysUser.getPassword(), sysUser.getSalt()));
        Integer count = sysUserMapper.insert(sysUser);
        //保存用户成功之后，添加角色信息
        if (count > 0) {
            List<SysRoleUser> sysRoleUsers = Arrays.stream(roleId).collect(() -> new ArrayList<SysRoleUser>(),
                    (list, role) -> list.add(new SysRoleUser(role, sysUser.getId())),
                    (list1, list2) -> list1.addAll(list2));
            return sysRoleUserService.insertOrUpdateBatch(sysRoleUsers);
        }
        return false;
    }

    /**
     * 修改系统用户
     *
     * @param sysUser
     * @param roleId
     * @return
     */
    @Transactional
    @Override
    public boolean updateUser(SysUser sysUser, Integer[] roleId) {
        //获取当前登录人信息
        ShiroUser shiroUser = ShiroUtils.getUser();
        //删除原角色中间表数据
        Integer count = sysRoleUserService.deleteByUserId(sysUser.getId());
        if (ToolUtil.isNotEmpty(roleId)) {
            String roleIds = StringUtils.join(roleId, ",");
            sysUser.setRoleIds(roleIds);
        }
        //修改用户数据
        boolean flag = this.updateById(sysUser);
        //保存用户成功之后，添加角色信息
        if (flag) {
            List<SysRoleUser> sysRoleUsers = Arrays.stream(roleId).collect(() -> new ArrayList<SysRoleUser>(),
                    (list, role) -> list.add(new SysRoleUser(role, sysUser.getId())),
                    (list1, list2) -> list1.addAll(list2));
            return sysRoleUserService.insertOrUpdateBatch(sysRoleUsers);
        }
        return false;
    }

    /**
     * 删除系统用户
     *
     * @param sysUser
     * @return
     */
    @Override
    public boolean deleteUser(Integer userId) {
        //查询系统用户完整信息
        SysUser sysUser = this.selectById(userId);
        if (ToolUtil.isNotEmpty(sysUser)) {
            //删除原角色中间表数据
            Integer count = sysRoleUserService.deleteByUserId(sysUser.getId());
            //删除系统用户
            return sysUserMapper.deleteById(userId)>0?true:false;
        }
        return false;
    }

}
