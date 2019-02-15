package com.hrabbit.admin.core.response;

import com.hrabbit.admin.core.exception.BussinessExceptionEnum;

/**
 * 基础返回消息
 *
 * @Auther: hrabbit
 * @Date: 2018-11-15 3:35 PM
 * @Description:
 */
public class BaseResponse {
    private int status = 200;
    private String message = "操作成功!";

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse() {
    }
    public void setErrorEnum(BussinessExceptionEnum bussinessExceptionEnum){
        this.status = bussinessExceptionEnum.getCode();
        this.message = bussinessExceptionEnum.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}