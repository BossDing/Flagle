package com.hrabbit.admin.modual.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hrabbit.admin.modual.system.bean.SysLog;
import com.hrabbit.admin.modual.system.bean.SysLoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录日志
 *
 * @Auther: hrabbit
 * @Date: 2018-12-31 7:12 PM
 * @Description:
 */
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

    /**
     * 查询所有的业务日志
     *
     * @author hrabbit
     * @Date: 2019/1/13 1:06 PM
     */
    List<SysLoginLog> listLog(@Param("logName") String logName);


    /**
     * 根据用户id获取用户登录次数
     *
     * @author hrabbit
     * @Param: [userId]
     * @Date: 2019/1/19 5:12 PM
     */
    Integer findLoginCountByUserId(@Param("userId") Integer userId);


    /**
     * 查询用户的最新登录信息
     *
     * @author hrabbit
     * @Param: [userId]
     * @Date: 2019/1/19 5:23 PM
     */
    String findNewLoginTimeByUserId(@Param("userId") Integer userId);
}
