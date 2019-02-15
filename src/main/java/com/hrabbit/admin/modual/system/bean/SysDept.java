package com.hrabbit.admin.modual.system.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 分组类型
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 4:54 PM
 * @Description:
 */
@TableName(value = "sys_dept")
@Data
public class SysDept {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer num;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 父级ids
     */
    private String parentIds;

    /**
     * 是否作废
     */
    private Integer enabled;

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
}
