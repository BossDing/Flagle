package com.hrabbit.admin.modual.system.controller;

import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.response.TableResultResponse;
import com.hrabbit.admin.modual.system.bean.SysLoginLog;
import com.hrabbit.admin.modual.system.controller.base.BaseController;
import com.hrabbit.admin.modual.system.service.SysLoginLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统登录日志
 *
 * @Auther: hrabbit
 * @Date: 2019-01-17 5:45 PM
 * @Description:
 */
@RequestMapping(value = "loginLog")
@Controller
public class SysLoginLogController extends BaseController {

    private static final String BASEURL = "/system/loginLog/";

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 登录日志主页
     *
     * @author hrabbit
     * @Date: 2019/1/17 5:46 PM
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return BASEURL + "/sys_login_log.html";
    }


    /**
     * 列表数据
     *
     * @param pageNum  第几页
     * @param pageSize 请求数据
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "系统登录日志列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "logName", value = "logName", dataType = "String")
    })
    public Object list(Integer pageNum, Integer pageSize, String logName) {
        PageInfo<SysLoginLog> pageInfo = sysLoginLogService.pageInfo(pageNum, pageSize, logName);
        return new TableResultResponse<SysLoginLog>(pageInfo);
    }

}