package com.hrabbit.admin.modual.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hrabbit.admin.modual.system.bean.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户数据库层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-19 12:02 PM
 * @Description:
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据登录名称查询用户信息
     *
     * @param loginName
     * @return
     */
    SysUser findByLoginName(@Param("loginName") String loginName);


    /**
     * 查询所有的用户
     *
     * @param loginName
     * @param roleId
     * @return
     */
    List<SysUser> findListUser(@Param("loginName") String loginName,@Param("roleId")Integer roleId,@Param("createTime")String createTime);

}
