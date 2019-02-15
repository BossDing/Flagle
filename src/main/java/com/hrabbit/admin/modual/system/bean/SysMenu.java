package com.hrabbit.admin.modual.system.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 菜单
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 3:41 PM
 * @Description:
 */
@Data
@TableName(value = "sys_menu")
public class SysMenu {

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
     * 资源名称
     */
    private String title;

    /**
     * 父级资源
     */
    private Integer parentId;

    /**
     * 资源路径
     */
    private String href;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 资源类型 menu|button
     */
    private String type;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 备注
     */
    private String description;

    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建人
     */
    private String createName;
}
