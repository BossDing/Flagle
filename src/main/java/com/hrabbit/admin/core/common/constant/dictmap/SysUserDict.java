package com.hrabbit.admin.core.common.constant.dictmap;

import com.hrabbit.admin.core.common.constant.dictmap.basemap.AbstractDictMap;

/**
 * 系统用户字典表
 * @Auther: hrabbit
 * @Date: 2018-11-22 1:33 PM
 * @Description:
 */
public class SysUserDict extends AbstractDictMap {
    @Override
    public void init() {
        put("name","用户名称");
        put("phone","电话");
        put("id","用户id");
        put("loginName","登录名称");

    }

    @Override
    protected void initBeWrapped() {
    }
}
