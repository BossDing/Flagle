package com.hrabbit.admin.modual.system.controller;

import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.response.TableResultResponse;
import com.hrabbit.admin.modual.system.bean.SysLog;
import com.hrabbit.admin.modual.system.controller.base.BaseController;
import com.hrabbit.admin.modual.system.service.SysLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统日志
 *
 * @Auther: hrabbit
 * @Date: 2019-01-13 12:58 PM
 * @Description:
 */
@Controller
@RequestMapping("log")
@Slf4j
@SuppressWarnings("all")
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    private static final String BASEURL = "/system/log/";

    /**
     * 进入到主页
     *
     * @author hrabbit
     * @Param: []
     * @Date: 2019/1/13 1:01 PM
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list() {
        return BASEURL + "sys_log.html";
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
    @ApiOperation(value = "系统日志列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "logName",value = "logName",dataType = "String")
    })
    public Object list(Integer pageNum, Integer pageSize,String logName) {
        PageInfo<SysLog> pageInfo = sysLogService.pageInfo(pageNum, pageSize,logName);
        return new TableResultResponse<SysLog>(pageInfo);
    }

}
