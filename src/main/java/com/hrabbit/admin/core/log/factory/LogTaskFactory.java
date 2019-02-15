package com.hrabbit.admin.core.log.factory;

import com.hrabbit.admin.core.common.tool.ToolUtil;
import com.hrabbit.admin.core.constant.state.LogSucceed;
import com.hrabbit.admin.core.constant.state.LogType;
import com.hrabbit.admin.core.handle.SpringContextHolder;
import com.hrabbit.admin.modual.system.bean.SysLog;
import com.hrabbit.admin.modual.system.bean.SysLoginLog;
import com.hrabbit.admin.modual.system.mapper.SysLogMapper;
import com.hrabbit.admin.modual.system.mapper.SysLoginLogMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 日志操作任务创建工厂
 *
 * @Auther: hrabbit
 * @Date: 2019-01-12 2:33 PM
 * @Description:
 */
@Slf4j
public class LogTaskFactory {

    private static SysLogMapper logMapper = SpringContextHolder.getBean(SysLogMapper.class);

    private static SysLoginLogMapper loginLogMapper = SpringContextHolder.getBean(SysLoginLogMapper.class);

    public static TimerTask loginLog(final Integer userId, final String loginName,final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    SysLoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, userId, loginName,null, ip);
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    log.error("创建登录日志异常!", e);
                }
            }
        };
    }

    public static TimerTask loginLog(final String username, final String msg, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                SysLoginLog loginLog = LogFactory.createLoginLog(
                        LogType.LOGIN_FAIL, null, null,"账号:" + username + "," + msg, ip);
                try {
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    log.error("创建登录失败异常!", e);
                }
            }
        };
    }

    public static TimerTask exitLog(final Integer userId,final String loginName, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                SysLoginLog loginLog = LogFactory.createLoginLog(LogType.EXIT, userId, loginName,null, ip);
                try {
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    log.error("创建退出日志异常!", e);
                }
            }
        };
    }

    public static TimerTask bussinessLog(final Integer userId,final String userName, final String bussinessName, final String clazzName, final String methodName, final String msg) {
        return new TimerTask() {
            @Override
            public void run() {
                SysLog sysLog = LogFactory.createOperationLog(
                        LogType.BUSSINESS, userId,userName, bussinessName, clazzName, methodName, msg, LogSucceed.SUCCESS);
                try {
                    logMapper.insert(sysLog);
                } catch (Exception e) {
                    log.error("创建业务日志异常!", e);
                }
            }
        };
    }

    public static TimerTask exceptionLog(final Integer userId, final Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = ToolUtil.getExceptionMsg(exception);
                SysLog sysLog = LogFactory.createOperationLog(
                        LogType.EXCEPTION, userId,"错误日志", "", null, null, msg, LogSucceed.FAIL);
                try {
                    logMapper.insert(sysLog);
                } catch (Exception e) {
                    log.error("创建异常日志异常!", e);
                }
            }
        };
    }
}

