package com.hrabbit.admin.core.shiro.service;

import com.hrabbit.admin.core.shiro.bean.ShiroUser;
import com.hrabbit.admin.modual.system.bean.SysUser;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

/**
 * Shiro认证
 *
 * @Auther: hrabbit
 * @Date: 2018-12-27 10:39 AM
 * @Description:
 */
public interface ShiroService {

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    SysUser user(String account);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, SysUser user, String realmName);


    /**
     * 获取shiroUser
     *
     * @param sysUser
     * @return
     */
    ShiroUser shiroUser(SysUser sysUser);
}
