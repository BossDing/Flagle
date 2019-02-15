package com.hrabbit.admin.modual.system.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * 系统用户
 *
 * @Auther: hrabbit
 * @Date: 2018-12-19 12:02 PM
 * @Description:
 */
@Data
@TableName(value = "sys_user")
public class SysUser {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 登录名称
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer enabled;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色ids
     */
    private String roleIds;
}
