package com.hrabbit.admin.core.shiro.realm;

import com.hrabbit.admin.core.shiro.bean.ShiroUser;
import com.hrabbit.admin.core.shiro.service.ShiroService;
import com.hrabbit.admin.core.shiro.service.impl.ShiroServiceImpl;
import com.hrabbit.admin.core.shiro.util.ShiroUtils;
import com.hrabbit.admin.modual.system.bean.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Shiro认证
 *
 * @Auther: hrabbit
 * @Date: 2018-12-24 12:30 PM
 * @Description:
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authcToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        ShiroService shiroService = ShiroServiceImpl.me();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        SysUser user = shiroService.user(token.getUsername());
        ShiroUser shiroUser = shiroService.shiroUser(user);
        return shiroService.info(shiroUser, user, super.getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        //String role = sysUserService.getRole(username);
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add("");
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }

}