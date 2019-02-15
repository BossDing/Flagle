package com.hrabbit.admin.modual.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.modual.system.bean.SysLoginLog;

/**
 * 系统日志
 *
 * @Auther: hrabbit
 * @Date: 2019-01-13 1:01 PM
 * @Description:
 */
public interface SysLoginLogService extends IService<SysLoginLog> {

    /**
     * 分页查询数据
     *
     * @author hrabbit
     * @Date: 2019/1/13 1:05 PM
     */
    PageInfo<SysLoginLog> pageInfo(Integer pageNum, Integer pageSize, String logName);


    /**
     * 根据用户id获取用户登录次数
     *
     * @author hrabbit
     * @Param: [userId]
     * @Date: 2019/1/19 5:12 PM
     */
    Integer findLoginCountByUserId(Integer userId);


    /**
     * 根据用户id获取用户登录次数
     *
     * @author hrabbit
     * @Param: [userId]
     * @Date: 2019/1/19 5:12 PM
     */
    String findNewLoginTimeByUserId(Integer userId);
}
