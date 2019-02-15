package com.hrabbit.admin.modual.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.modual.system.bean.SysLog;
/**
 * 系统日志
 *
 * @Auther: hrabbit
 * @Date: 2019-01-13 1:01 PM
 * @Description:
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 分页查询数据
     *
     * @author hrabbit
     * @Param: [pageNum, pageSize, loginName, roleId,logName]
     * @Date: 2019/1/13 1:05 PM
     */
    PageInfo<SysLog> pageInfo(Integer pageNum, Integer pageSize,String logName);
}
