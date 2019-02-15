package com.hrabbit.admin.core.shiro.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Shiro包装类
 * 用户信息
 * @Auther: hrabbit
 * @Date: 2018-12-24 12:31 PM
 * @Description:
 */
@Data
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public Integer id;          // 主键ID
    public String loginName;      // 账号
    public String name;         // 姓名
    public Integer deptId;      // 部门id
    public String password;     // 用户密码
    public String avatar;      //用户头像
    public String salt;         //盐
    public List<Integer> roleList; // 角色集
    public String deptName;        // 部门名称
    public List<String> roleNames; // 角色名称集
}
