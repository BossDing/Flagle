package com.hrabbit.admin.modual.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hrabbit.admin.modual.system.bean.SysRoleUser;
import com.hrabbit.admin.modual.system.mapper.SysRoleUserMapper;
import com.hrabbit.admin.modual.system.service.SysRoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色和用户的中间表
 *
 * @Auther: hrabbit
 * @Date: 2019-01-29 9:08 PM
 * @Description:
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * 根据用户id删除中间表信息
     *
     * @param userId
     * @return
     */
    @Override
    public Integer deleteByUserId(Integer userId) {
        return sysRoleUserMapper.deleteByUserId(userId);
    }

}
