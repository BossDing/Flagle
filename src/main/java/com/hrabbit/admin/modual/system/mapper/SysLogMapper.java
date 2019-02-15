package com.hrabbit.admin.modual.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hrabbit.admin.modual.system.bean.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统日志数据库层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-31 7:11 PM
 * @Description:
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 查询所有的业务日志
     *
     * @author hrabbit
     * @Date: 2019/1/13 1:06 PM
     */
    List<SysLog> listLog(@Param("logName") String logName);
}
