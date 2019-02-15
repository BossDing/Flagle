package com.hrabbit.admin.core.log.factory;

import com.hrabbit.admin.core.constant.state.LogSucceed;
import com.hrabbit.admin.core.constant.state.LogType;
import com.hrabbit.admin.modual.system.bean.SysLog;
import com.hrabbit.admin.modual.system.bean.SysLoginLog;

import java.util.Date;

/**
 * 日志对象创建工厂
 *
 * @Auther: hrabbit
 * @Date: 2019-01-12 2:27 PM
 * @Description:
 */
public class LogFactory {
    /**
     * 创建操作日志
     */
    public static SysLog createOperationLog(LogType logType, Integer userId, String userName, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        SysLog operationLog = new SysLog();
        operationLog.setLogType(logType.getMessage());
        operationLog.setLogName(bussinessName);
        operationLog.setUserId(userId);
        operationLog.setClassName(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreateTime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        operationLog.setCreateName(userName);
        operationLog.setCreateBy(userId);
        return operationLog;
    }

    /**
     * 创建登录日志
     */
    public static SysLoginLog createLoginLog(LogType logType, Integer userId, String loginName, String msg, String ip) {
        SysLoginLog loginLog = new SysLoginLog();
        loginLog.setLogName(logType.getMessage());
        loginLog.setUserId(userId);
        loginLog.setLoginName(loginName);
        loginLog.setCreateTime(new Date());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }
}
