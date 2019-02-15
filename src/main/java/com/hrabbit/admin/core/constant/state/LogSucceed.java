package com.hrabbit.admin.core.constant.state;

/**
 * 业务是否成功的日志记录
 *
 * @Auther: hrabbit
 * @Date: 2019-01-12 2:32 PM
 * @Description:
 */
public enum LogSucceed {

    SUCCESS("成功"),
    FAIL("失败");

    String message;

    LogSucceed(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
