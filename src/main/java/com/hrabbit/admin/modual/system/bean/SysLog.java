package com.hrabbit.admin.modual.system.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 系统业务日志
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 5:06 PM
 * @Description:
 */
@TableName(value = "sys_log")
@Data
public class SysLog {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 日志名称
     */
    private String logName;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 类名称
     */
    private String className;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 是否成功
     */
    private String succeed;

    /**
     * 日志信息
     */
    private String message;

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
