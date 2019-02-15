package com.hrabbit.admin.modual.system.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 角色
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 4:47 PM
 * @Description:
 */
@TableName(value = "sys_role")
@Data
public class SysRole {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编码
     */
    private String code;

    /**
     * 组名称
     */
    private String name;

    /**
     * 上级
     */
    private Integer parentId;

    /**
     * 上级名称
     */
    private String parentName;

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
    private Integer createBy;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 部门id
     */
    private Integer deptId;
    
    /**
     * 部门名称
     */
    private String deptName;
}
