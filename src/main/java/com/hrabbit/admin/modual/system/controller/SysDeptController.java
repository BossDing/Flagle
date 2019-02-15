package com.hrabbit.admin.modual.system.controller;

import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.node.TreeNode;
import com.hrabbit.admin.core.response.ObjectRestResponse;
import com.hrabbit.admin.core.response.TableResultResponse;
import com.hrabbit.admin.modual.system.bean.SysDept;
import com.hrabbit.admin.modual.system.controller.base.BaseController;
import com.hrabbit.admin.modual.system.service.SysDeptService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 部门控制层
 *
 * @Auther: hrabbit
 * @Date: 2019-01-20 10:59 PM
 * @Description:
 */
@RequestMapping("dept")
@Controller
@SuppressWarnings("all")
public class SysDeptController extends BaseController {


    private static final String BASEURL = "/system/dept/";

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 进入到部门主页
     *
     * @author hrabbit
     * @Param: []
     * @Date: 2019/1/20 11:01 PM
     */
    @RequestMapping("/")
    @ApiOperation("进入到部门主页")
    public String index() {
        return BASEURL + "dept_info.html";
    }


    /**
     * 获取部门机构树
     *
     * @return
     */
    @RequestMapping(value = "findDeptTreeNode", method = RequestMethod.GET)
    @ApiOperation(value = "获取组织机构树")
    @ResponseBody
    public Object findGroupTreeNode() {
        List<TreeNode> groupTreeNode = sysDeptService.findDeptTreeNode();
        return new ObjectRestResponse<List<TreeNode>>().data(groupTreeNode);
    }


    /**
     * 获取部门下拉Node
     *
     * @return
     */
    @RequestMapping(value = "findDeptSelectNode", method = RequestMethod.GET)
    @ApiOperation(value = "获取部门组织树")
    @ResponseBody
    public Object findDeptSelectNode() {
        return sysDeptService.findDeptSelectNode();
    }

    /**
     * 列表数据
     *
     * @param pageNum      第几页
     * @param pageSize     请求数据
     * @param groupType    组类型 "角色类型" "部门类型"
     * @param parentRoleId 上级角色id
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "系统用户列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "roleId", value = "roleId", dataType = "Integer")
    })
    public Object list(Integer pageNum, Integer pageSize, Integer parentDeptId) {
        PageInfo<SysDept> pageInfo = sysDeptService.findDeptPageInfo(pageNum, pageSize, parentDeptId);
        return new TableResultResponse<SysDept>(pageInfo);
    }

}
