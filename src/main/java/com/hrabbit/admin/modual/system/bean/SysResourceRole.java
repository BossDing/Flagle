package com.hrabbit.admin.modual.system.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 资源<=>角色中间表
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 5:11 PM
 * @Description:
 */
@TableName(value = "sys_resource_role")
@Data
public class SysResourceRole {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 资源id
     */
    private Integer resourceId;

    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 上级id
     */
    private Integer parentId;

    /**
     * 路径
     */
    private String path;

    /**
     * 备注
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建人名称
     */
    private String createName;

}