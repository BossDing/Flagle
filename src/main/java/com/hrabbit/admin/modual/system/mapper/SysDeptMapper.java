package com.hrabbit.admin.modual.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hrabbit.admin.modual.system.bean.SysDept;

import java.util.List;

/**
 * 部门
 *
 * @Auther: hrabbit
 * @Date: 2018-12-31 7:08 PM
 * @Description:
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 获取角色信息
     *
     * @return
     */
    List<SysDept> findDeptList();
}