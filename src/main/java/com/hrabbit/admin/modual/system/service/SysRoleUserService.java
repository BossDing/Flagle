package com.hrabbit.admin.modual.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.hrabbit.admin.modual.system.bean.SysRoleUser;

/**
 * 角色和用户的中间业务
 *
 * @Auther: hrabbit
 * @Date: 2019-01-29 9:07 PM
 * @Description:
 */
public interface SysRoleUserService extends IService<SysRoleUser> {


    /**
     * 根据用户id删除中间表信息
     *
     * @param userId
     * @return
     */
    Integer deleteByUserId(Integer userId);

}
