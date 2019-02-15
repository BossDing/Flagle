package com.hrabbit.admin.modual.system.controller;

import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.common.annotion.BussinessLog;
import com.hrabbit.admin.core.common.constant.dictmap.SysUserDict;
import com.hrabbit.admin.core.exception.BaseException;
import com.hrabbit.admin.core.exception.BussinessExceptionEnum;
import com.hrabbit.admin.core.log.LogObjectHolder;
import com.hrabbit.admin.core.node.SelectNode;
import com.hrabbit.admin.core.response.BaseResponse;
import com.hrabbit.admin.core.response.TableResultResponse;
import com.hrabbit.admin.core.shiro.bean.ShiroUser;
import com.hrabbit.admin.core.shiro.util.ShiroUtils;
import com.hrabbit.admin.modual.system.bean.SysUser;
import com.hrabbit.admin.modual.system.controller.base.BaseController;
import com.hrabbit.admin.modual.system.service.SysLoginLogService;
import com.hrabbit.admin.modual.system.service.SysRoleService;
import com.hrabbit.admin.modual.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统用户
 *
 * @Auther: hrabbit
 * @Date: 2018-12-17 6:21 PM
 * @Description:
 */
@Controller
@RequestMapping("user")
@Api(value = "系统用户")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 根据id获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "进入到主页")
    public String index() {
        return "/system/user/user_info.html";
    }


    /**
     * 进入用户详情界面
     *
     * @return
     */
    @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    @ApiOperation(value = "进入到用户详情界面")
    public String userDetail(ModelMap model) {
        //获取当前登录人
        ShiroUser user = ShiroUtils.getUser();
        //获取当前登录人的日志信息
        Integer loginCount = sysLoginLogService.findLoginCountByUserId(user.getId());
        //获取当前登录人的最新登录时间
        String newLoginTime = sysLoginLogService.findNewLoginTimeByUserId(user.getId());

        //添加到域
        model.addAttribute("user", user);
        model.addAttribute("loginCount", loginCount);
        model.addAttribute("newLoginTime", newLoginTime);
        return "/system/user/user_detail.html";
    }

    /**
     * 列表数据
     *
     * @param pageNum   第几页
     * @param pageSize  请求数据
     * @param loginName 登录名称
     * @param roleId    角色id
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "系统用户列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "loginName", value = "loginName", dataType = "String"),
            @ApiImplicitParam(name = "roleId", value = "roleId", dataType = "Integer")
    })
    public Object list(Integer pageNum, Integer pageSize, String loginName, Integer roleId, String createTime) {
        System.out.println(createTime);
        PageInfo<SysUser> pageInfo = sysUserService.pageInfo(pageNum, pageSize, loginName, roleId, createTime);
        return new TableResultResponse<SysUser>(pageInfo);
    }

    /**
     * 打开添加页面
     *
     * @return
     */
    @RequestMapping(value = "openAddUser", method = RequestMethod.GET)
    @ApiOperation(value = "打开添加用户界面")
    public String openAddUser() {
        return "/system/user/user_add.html";
    }


    /**
     * 添加用户
     *
     * @param sysUser 系统用户
     * @author hrabbit
     * @Date: 2019/1/12 1:44 PM
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ApiOperation(value = "添加系统用户")
    @ApiImplicitParam(value = "sysUser", name = "sysUser", dataType = "SysUser")
    @ResponseBody
    public BaseResponse add(@Valid SysUser sysUser, @RequestParam("role[]") Integer[] role, BindingResult result) {
        //验证信息
        if (result.hasErrors()) {
            throw new BaseException(BussinessExceptionEnum.REQUEST_INVALIDATE);
        }
        //验证用户账号是否存在
        SysUser user = sysUserService.findByLoginName(sysUser.getLoginName());
        if (user != null) {
            throw new BaseException(BussinessExceptionEnum.USER_ALREADY_REG);
        }
        boolean flag = sysUserService.addUser(sysUser, role);
        if (flag) {
            SuccessResponse.setErrorEnum(BussinessExceptionEnum.USER_ADD_ERROR);
        }
        return SuccessResponse;
    }

    /**
     * 打开修改页面
     *
     * @return
     */
    @RequestMapping(value = "openUpdateUser/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "打开修改用户界面")
    public String openUpdateUser(@PathVariable("userId") Integer userId, ModelMap map) {
        SysUser sysUser = sysUserService.selectById(userId);
        //加载这个用户的所属部门的角色集合
        SelectNode selectNode = sysRoleService.findRoleByDeptId(sysUser.getDeptId());
        map.addAttribute("user", sysUser);
        map.addAttribute("selectNode", selectNode);
        //设置到log日志临时存放
        LogObjectHolder.me().set(sysUser);
        return "/system/user/user_update.html";
    }


    /**
     * 修改用户信息
     *
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @BussinessLog(value = "修改用户", key = "loginName", dict = SysUserDict.class)
    @ResponseBody
    public Object update(SysUser sysUser, @RequestParam("role[]") Integer[] role, BindingResult result) {
        //验证信息
        if (result.hasErrors()) {
            throw new BaseException(BussinessExceptionEnum.REQUEST_INVALIDATE);
        }
        boolean flag = sysUserService.updateUser(sysUser, role);
        if (flag) {
            SuccessResponse.setErrorEnum(BussinessExceptionEnum.USER_ADD_ERROR);
        }
        return SuccessResponse;
    }

    /**
     * 删除用户信息
     *
     * @return
     */
    @RequestMapping(value = "delete/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("userId") Integer userId) {
        boolean flag = sysUserService.deleteUser(userId);
        if (flag) {
            SuccessResponse.setErrorEnum(BussinessExceptionEnum.USER_ADD_ERROR);
        }
        return SuccessResponse;
    }


}