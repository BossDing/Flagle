package com.hrabbit.admin.modual.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.modual.system.bean.SysLog;
import com.hrabbit.admin.modual.system.mapper.SysLogMapper;
import com.hrabbit.admin.modual.system.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统日志
 *
 * @Auther: hrabbit
 * @Date: 2019-01-13 1:02 PM
 * @Description:
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {


    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * 分页查询数据
     *
     * @author hrabbit
     * @Param: [pageNum, pageSize, loginName, roleId]
     * @Date: 2019/1/13 1:05 PM
     */
    @Override
    public PageInfo<SysLog> pageInfo(Integer pageNum, Integer pageSize,String logName) {
        //设置分页
        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        List<SysLog> listLog = sysLogMapper.listLog(logName);
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(listLog);
        return pageInfo;
    }


}
