package com.hrabbit.admin.core.common.constant.dictmap;

import com.hrabbit.admin.core.common.constant.dictmap.basemap.AbstractDictMap;

/**
 * 系统角色字典表
 *
 * @Auther: hrabbit
 * @Date: 2018-11-22 1:33 PM
 * @Description:
 */
public class SysRoleDict extends AbstractDictMap {
    @Override
    public void init() {
        put("name", "角色名称");
        put("code", "角色编码");
        put("deptName", "所属部门");
        put("parentName", "上级名称");

    }

    @Override
    protected void initBeWrapped() {
    }
}
