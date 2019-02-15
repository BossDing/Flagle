package com.hrabbit.admin.core.aop;

import com.hrabbit.admin.core.common.annotion.BussinessLog;
import com.hrabbit.admin.core.common.constant.dictmap.basemap.AbstractDictMap;
import com.hrabbit.admin.core.common.http.HttpUtils;
import com.hrabbit.admin.core.log.LogManager;
import com.hrabbit.admin.core.log.LogObjectHolder;
import com.hrabbit.admin.core.log.factory.LogTaskFactory;
import com.hrabbit.admin.core.shiro.bean.ShiroUser;
import com.hrabbit.admin.core.shiro.util.ShiroUtils;
import com.hrabbit.admin.core.utils.Contrast;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 日志记录
 *
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/12 2:03 PM
 */
@Aspect
@Component
@Slf4j
public class LogAop {

    @Pointcut(value = "@annotation(com.hrabbit.admin.core.common.annotion.BussinessLog)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point) throws Exception {

        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //如果当前用户未登录，不做日志
        ShiroUser shiroUser = ShiroUtils.getUser();
        if (null == shiroUser) {
            return;
        }

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        String bussinessName = annotation.value();
        String key = annotation.key();
        Class dictClass = annotation.dict();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

        //如果涉及到修改,比对变化
        String msg;
        if (bussinessName.contains("修改") || bussinessName.contains("编辑")) {
            Object obj1 = LogObjectHolder.me().get();
            Map<String, String> obj2 = HttpUtils.getRequestParameters();
            msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
        } else {
            Map<String, String> parameters = HttpUtils.getRequestParameters();
            AbstractDictMap dictMap = (AbstractDictMap) dictClass.newInstance();
            msg = Contrast.parseMutiKey(dictMap, key, parameters);
        }

        LogManager.me().executeLog(LogTaskFactory.bussinessLog(shiroUser.getId(),shiroUser.getName(), bussinessName, className, methodName, msg));
    }
}
