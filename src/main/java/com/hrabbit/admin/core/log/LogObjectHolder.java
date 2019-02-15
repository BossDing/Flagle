package com.hrabbit.admin.core.log;

import com.hrabbit.admin.core.handle.SpringContextHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

/**
 * 两个Bean进行对比的时候，临时Bean存放地址
 *
 * @Auther: hrabbit
 * @Date: 2019-01-12 2:26 PM
 * @Description:
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class LogObjectHolder implements Serializable {

    private Object object = null;

    public void set(Object obj) {
        this.object = obj;
    }

    public Object get() {
        return object;
    }

    public static LogObjectHolder me() {
        return SpringContextHolder.getBean(LogObjectHolder.class);
    }
}
