package com.hrabbit.admin.core.common.constant.dictmap.factory;

import com.hrabbit.admin.core.common.constant.factory.ConstantFactoryService;
import com.hrabbit.admin.core.common.constant.factory.impl.ConstantFactoryServiceImpl;
import com.hrabbit.admin.core.exception.BaseException;
import com.hrabbit.admin.core.exception.BussinessExceptionEnum;

import java.lang.reflect.Method;

/**
 * 字典字段的包装器(从ConstantFactory中获取包装值)
 *
 * @Auther: hrabbit
 * @Date: 2019-01-12 2:47 PM
 * @Description:
 */
public class DictFieldWarpperFactory {

    public static Object createFieldWarpper(Object parameter, String methodName) {
        ConstantFactoryService constantFactory = ConstantFactoryServiceImpl.me();
        try {
            Method method = ConstantFactoryServiceImpl.class.getMethod(methodName, parameter.getClass());
            return method.invoke(constantFactory, parameter);
        } catch (Exception e) {
            try {
                Method method = ConstantFactoryServiceImpl.class.getMethod(methodName, Integer.class);
                return method.invoke(constantFactory, Integer.parseInt(parameter.toString()));
            } catch (Exception e1) {
                throw new BaseException(BussinessExceptionEnum.ERROR_WRAPPER_FIELD);
            }
        }
    }

}
