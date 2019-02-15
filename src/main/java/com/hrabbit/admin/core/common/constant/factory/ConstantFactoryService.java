package com.hrabbit.admin.core.common.constant.factory;

/**
 * 常量生产工厂的接口
 *
 * @Auther: hrabbit
 * @Date: 2019-01-12 2:57 PM
 * @Description:
 */
public interface ConstantFactoryService {


    /**
     * 根据用户id获取用户名称
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:03 PM
     */
    String getUserNameById(Long userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:04 PM
     */
    String getLoginNameById(Long userId);

    /**
     * 通过角色ids获取角色名称
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:04 PM
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:04 PM
     */
    String getSingleRoleName(Long roleId);

    /**
     * 通过角色id获取角色英文名称
     *
     * @author hrabbit
     * @Date: 2019/1/12 3:04 PM
     */
    String getSingleRoleTip(Long roleId);
}
