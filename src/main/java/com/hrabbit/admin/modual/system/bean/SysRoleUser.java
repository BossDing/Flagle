package com.hrabbit.admin.modual.system.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 用户所拥有的组权限
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 4:58 PM
 * @Description:
 */
@TableName(value = "sys_role_user")
@Data
public class SysRoleUser {


    public SysRoleUser(Integer roleId,Integer userId){
        this.roleId = roleId;
        this.userId = userId;
    }

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分组id
     */
    private Integer roleId;

    /**
     * 用户id
     */
    private Integer userId;
}
