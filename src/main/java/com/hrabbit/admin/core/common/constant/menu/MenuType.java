package com.hrabbit.admin.core.common.constant.menu;

/**
 * 是否是菜单的枚举
 * @Auther: hrabbit
 * @Date: 2018-11-27 11:36 AM
 * @Description:
 */
public enum MenuType {

    YES(1,"是"),
    NO(0,"不是");

    int code;

    String message;

    MenuType(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
