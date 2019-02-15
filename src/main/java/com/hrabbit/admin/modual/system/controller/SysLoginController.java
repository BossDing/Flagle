package com.hrabbit.admin.modual.system.controller;

import com.hrabbit.admin.core.common.http.HttpUtils;
import com.hrabbit.admin.core.log.LogManager;
import com.hrabbit.admin.core.log.factory.LogTaskFactory;
import com.hrabbit.admin.core.node.MenuNode;
import com.hrabbit.admin.core.shiro.bean.ShiroUser;
import com.hrabbit.admin.core.shiro.util.ShiroUtils;
import com.hrabbit.admin.modual.system.bean.SysUser;
import com.hrabbit.admin.modual.system.controller.base.BaseController;
import com.hrabbit.admin.modual.system.service.SysLoginLogService;
import com.hrabbit.admin.modual.system.service.SysMenuService;
import com.hrabbit.admin.modual.system.service.SysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 登录界面
 *
 * @Auther: hrabbit
 * @Date: 2018-12-24 12:59 PM
 * @Description:
 */
@Controller
@Slf4j
public class SysLoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 进入到主页
     *
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        //获取用户角色idf
        List<Integer> roleList = ShiroUtils.getUser().getRoleList();
        //如果用户不存在角色，跳转到登录界面
        if (roleList == null || roleList.size() == 0) {
            ShiroUtils.getSubject().logout();
            model.addAttribute("msg", "该用户没有角色，无法登陆");
            return "/login.html";
        }
        //根据角色id查询按钮资源
        List<MenuNode> menuNodes = sysMenuService.findPermissionByRoleIds(roleList);
        //返回用户资料信息
        ShiroUser shiroUser = ShiroUtils.getUser();
        //将Shiro用户信息返回到前端页面
        model.addAttribute("user", shiroUser);
        model.addAttribute("titles", menuNodes);
        return "/index.html";
    }

    /**
     * 登录界面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroUtils.isAuthenticated() || ShiroUtils.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }

    /**
     * 页面提交登录
     *
     * @param username 登录名称
     * @param password 用户密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "表单验证", notes = "提交登录信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String")
    })
    public String login(String username, String password, Model model) {
        Subject subject = ShiroUtils.getSubject();
        //检验用户是否存在
        SysUser sysUser = sysUserService.findByLoginName(username);
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        ShiroUser shiroUser = ShiroUtils.getUser();
        //添加登录日志
        LogManager.me().executeLog(LogTaskFactory.loginLog(sysUser.getId(),sysUser.getLoginName(),HttpUtils.getIp()));
        //将ShiroUser对象存储到session中
        HttpUtils.getRequest().getSession().setAttribute("shiroUser", shiroUser);
        //保存Session状态
        ShiroUtils.getSession().setAttribute("sessionFlag", true);
        return REDIRECT + "/";
    }


    /**
     * 退出登录
     *
     * @author hrabbit
     * @Date: 2019/1/12 1:59 PM
     */
    @RequestMapping(value = "logOut", method = RequestMethod.GET)
    public String logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroUtils.getUser().getId(),ShiroUtils.getUser().getLoginName(),HttpUtils.getIp()));
        ShiroUtils.getSubject().logout();
        return REDIRECT + "/login.html";
    }


    /**
     * 进入到主页
     *
     * @return
     */
    @RequestMapping("/home")
    public String home(Model model) {
        ShiroUser shiroUser = ShiroUtils.getUser();
        //获取当前登录人的日志信息
        Integer loginCount = sysLoginLogService.findLoginCountByUserId(shiroUser.getId());
        //获取当前登录人的最新登录时间
        String newLoginTime = sysLoginLogService.findNewLoginTimeByUserId(shiroUser.getId());

        model.addAttribute("user",shiroUser);
        model.addAttribute("loginCount",loginCount);
        model.addAttribute("newLoginTime",newLoginTime);
        return "/system/home/home.html";
    }

}