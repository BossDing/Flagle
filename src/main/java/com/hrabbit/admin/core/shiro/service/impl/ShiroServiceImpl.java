package com.hrabbit.admin.core.shiro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hrabbit.admin.core.handle.SpringContextHolder;
import com.hrabbit.admin.core.shiro.bean.ShiroUser;
import com.hrabbit.admin.core.shiro.service.ShiroService;
import com.hrabbit.admin.modual.system.bean.SysRole;
import com.hrabbit.admin.modual.system.bean.SysUser;
import com.hrabbit.admin.modual.system.mapper.SysUserMapper;
import com.hrabbit.admin.modual.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Shiro认证
 *
 * @Auther: hrabbit
 * @Date: 2018-12-27 10:39 AM
 * @Description:
 */
@Service
@Slf4j
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
@SuppressWarnings("all")
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取自己的对象
     *
     * @return
     */
    public static ShiroService me() {
        return SpringContextHolder.getBean(ShiroService.class);
    }

    /**
     * 根据登录名称获取用户信息
     *
     * @param account 账号
     * @return
     */
    @Override
    public SysUser user(String account) {
        SysUser user = sysUserMapper.findByLoginName(account);
        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        return user;
    }

    /**
     * 生成MD5加密盐，并将信息传递到SimpleAuthenticationInfo对象中
     *
     * @param shiroUser
     * @param user
     * @param realmName
     * @return
     */
    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, SysUser user, String realmName) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

    /**
     * 获取ShiroUser
     *
     * @param sysUser
     * @return
     */
    @Override
    public ShiroUser shiroUser(SysUser sysUser) {
        ShiroUser shiroUser = new ShiroUser();
        //设置ShiroUses的信息
        BeanUtil.copyProperties(sysUser, shiroUser);
        //获取用户的部门信息和角色信息
        List<SysRole> sysGroups = sysRoleService.findRoleByUserId(sysUser.getId());
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (SysRole role : sysGroups) {
            roleList.add(role.getId());
            roleNameList.add(role.getName());
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
        return shiroUser;
    }


}