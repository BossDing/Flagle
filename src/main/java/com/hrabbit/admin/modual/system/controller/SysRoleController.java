package com.hrabbit.admin.modual.system.controller;

import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.common.annotion.BussinessLog;
import com.hrabbit.admin.core.common.constant.dictmap.SysRoleDict;
import com.hrabbit.admin.core.common.constant.dictmap.SysUserDict;
import com.hrabbit.admin.core.exception.BaseException;
import com.hrabbit.admin.core.exception.BussinessExceptionEnum;
import com.hrabbit.admin.core.log.LogObjectHolder;
import com.hrabbit.admin.core.node.SelectNode;
import com.hrabbit.admin.core.node.TreeNode;
import com.hrabbit.admin.core.response.BaseResponse;
import com.hrabbit.admin.core.response.ObjectRestResponse;
import com.hrabbit.admin.core.response.TableResultResponse;
import com.hrabbit.admin.modual.system.bean.SysRole;
import com.hrabbit.admin.modual.system.bean.SysUser;
import com.hrabbit.admin.modual.system.controller.base.BaseController;
import com.hrabbit.admin.modual.system.service.SysDeptService;
import com.hrabbit.admin.modual.system.service.SysRoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色控制器
 *
 * @Auther: hrabbit
 * @Date: 2019-01-20 11:02 PM
 * @Description:
 */
@RequestMapping("role")
@Controller
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    private static final String BASEURL = "/system/role/";

    /**
     * 进入到角色的主页面
     *
     * @author hrabbit
     * @Date: 2019/1/20 11:03 PM
     */
    @RequestMapping("/")
    @ApiOperation("进入到角色主页面")
    public String index() {
        return BASEURL + "role_info.html";
    }


    /**
     * 获取组织机构树
     *
     * @return
     */
    @RequestMapping(value = "findRoleTreeNode", method = RequestMethod.GET)
    @ApiOperation(value = "获取组织机构树")
    @ResponseBody
    public Object findGroupTreeNode() {
        List<TreeNode> groupTreeNode = sysRoleService.findRoleTreeNode();
        return new ObjectRestResponse<List<TreeNode>>().data(groupTreeNode);
    }


    /**
     * 列表数据
     *
     * @param pageNum      第几页
     * @param pageSize     请求数据
     * @param roleId 上级角色id
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "系统角色列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "roleName", value = "roleName", dataType = "String"),
            @ApiImplicitParam(name = "roleId", value = "roleId", dataType = "Integer")
    })
    public Object list(Integer pageNum, Integer pageSize, String roleName,Integer roleId) {
        PageInfo<SysRole> pageInfo = sysRoleService.findRolePageInfo(pageNum, pageSize, roleName,roleId);
        return new TableResultResponse<SysRole>(pageInfo);
    }


    /**
     * 根据部门id获取角色信息
     *
     * @return
     */
    @RequestMapping(value = "findRoleByDeptId/{deptId}", method = RequestMethod.GET)
    @ResponseBody
    public Object findRoleByDeptId(@PathVariable("deptId") Integer deptId) {
        return sysRoleService.findRoleByDeptId(deptId);
    }


    /**
     * 打开添加角色的界面
     *
     * @return
     */
    @RequestMapping(value = "openAddRole", method = RequestMethod.GET)
    public String openAddRole(ModelMap modelMap) {
        return BASEURL + "/role_add.html";
    }


    /**
     * 添加用户
     *
     * @param sysRole 系统角色
     * @author hrabbit
     * @Date: 2019/1/12 1:44 PM
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ApiOperation(value = "添加系统角色")
    @ApiImplicitParam(value = "sysRole", name = "sysRole", dataType = "SysRole")
    @ResponseBody
    public BaseResponse add(@Valid SysRole sysRole, BindingResult result) {
        //验证信息
        if (result.hasErrors()) {
            throw new BaseException(BussinessExceptionEnum.REQUEST_INVALIDATE);
        }
        boolean flag = sysRoleService.addRole(sysRole);
        if (flag) {
            SuccessResponse.setErrorEnum(BussinessExceptionEnum.DB_RESOURCE_NULL);
        }
        return SuccessResponse;
    }


    /**
     * 打开修改角色的界面
     *
     * @return
     */
    @RequestMapping(value = "openUpdateRole/{roleId}", method = RequestMethod.GET)
    public String openUpdateRole(@PathVariable("roleId") Integer roleId, ModelMap modelMap) {
        SysRole sysRole = sysRoleService.selectById(roleId);
        modelMap.addAttribute("role", sysRole);
        //设置到log日志临时存放
        LogObjectHolder.me().set(sysRole);
        return BASEURL + "/role_update.html";
    }


    /**
     * 修改用户信息
     *
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @BussinessLog(value = "修改角色", key = "name", dict = SysRoleDict.class)
    @ResponseBody
    public Object update(SysRole sysRole, BindingResult result) {
        //验证信息
        if (result.hasErrors()) {
            throw new BaseException(BussinessExceptionEnum.REQUEST_INVALIDATE);
        }
        boolean flag = sysRoleService.updateRole(sysRole);
        if (flag) {
            SuccessResponse.setErrorEnum(BussinessExceptionEnum.REQUEST_INVALIDATE);
        }
        return SuccessResponse;
    }


    /**
     * 删除角色信息
     *
     * @return
     */
    @RequestMapping(value = "delete/{roleId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("roleId") Integer roleId) {
        SuccessResponse = sysRoleService.delete(roleId);
        return SuccessResponse;
    }

}
