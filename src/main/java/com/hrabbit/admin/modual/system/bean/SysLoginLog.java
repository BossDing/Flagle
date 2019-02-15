package com.hrabbit.admin.modual.system.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: hrabbit
 * @Date: 2018-12-30 5:01 PM
 * @Description:
 */
@TableName(value = "sys_login_log")
@Data
public class SysLoginLog {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 日志名称
     */
    private String logName;

    /**
     * 管理员id
     */
    private Integer userId;

    /**
     * 是够成功
     */
    private String succeed;

    /**
     * 日志信息
     */
    private String message;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 登录人
     */
    private String loginName;

}
