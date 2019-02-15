package com.hrabbit.admin.core.common.annotion;

import com.hrabbit.admin.core.common.constant.dictmap.basemap.AbstractDictMap;
import com.hrabbit.admin.core.common.constant.dictmap.basemap.SystemDict;

import java.lang.annotation.*;

/**
 * 业务日志
 * @Auther: hrabbit
 * @Date: 2019-01-12 2:10 PM
 * @Description:
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BussinessLog {

    /**
     * 业务的名称,例如:"修改菜单"
     */
    String value() default "";

    /**
     * 被修改的实体的唯一标识,例如:菜单实体的唯一标识为"id"
     */
    String key() default "id";

    /**
     * 字典(用于查找key的中文名称和字段的中文名称)
     */
    Class<? extends AbstractDictMap> dict() default SystemDict.class;
}