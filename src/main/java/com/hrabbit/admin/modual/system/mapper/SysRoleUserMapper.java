package com.hrabbit.admin.modual.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hrabbit.admin.modual.system.bean.SysRoleUser;
import org.apache.ibatis.annotations.Param;

/**
 * 组和用户的数据库层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-31 7:09 PM
 * @Description:
 */
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {


    /**
     * 根据用户id删除中间表信息
     *
     * @param userId
     * @return
     */
    Integer deleteByUserId(@Param("userId") Integer userId);
}
