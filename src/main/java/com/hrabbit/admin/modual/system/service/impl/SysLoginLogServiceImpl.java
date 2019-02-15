package com.hrabbit.admin.modual.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.modual.system.bean.SysLog;
import com.hrabbit.admin.modual.system.bean.SysLoginLog;
import com.hrabbit.admin.modual.system.mapper.SysLogMapper;
import com.hrabbit.admin.modual.system.mapper.SysLoginLogMapper;
import com.hrabbit.admin.modual.system.service.SysLogService;
import com.hrabbit.admin.modual.system.service.SysLoginLogService;
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
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {


    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    /**
     * 分页查询数据
     *
     * @author hrabbit
     * @Date: 2019/1/13 1:05 PM
     */
    @Override
    public PageInfo<SysLoginLog> pageInfo(Integer pageNum, Integer pageSize,String logName) {
        //设置分页
        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        List<SysLoginLog> listLog = sysLoginLogMapper.listLog(logName);
        PageInfo<SysLoginLog> pageInfo = new PageInfo<SysLoginLog>(listLog);
        log.info("pageInfo={}",pageInfo);
        return pageInfo;
    }


    /**
     * 根据用户id获取用户登录次数
     *
     * @author hrabbit
     * @Date: 2019/1/19 5:12 PM
     */
    @Override
    public Integer findLoginCountByUserId(Integer userId){
        return sysLoginLogMapper.findLoginCountByUserId(userId);
    }


    /**
     * 根据用户id获取用户的最新登录时间
     *
     * @author hrabbit
     * @Date: 2019/1/19 5:12 PM
     */
    @Override
    public String findNewLoginTimeByUserId(Integer userId){
        return sysLoginLogMapper.findNewLoginTimeByUserId(userId);
    }

}
