package com.hrabbit.admin.core.exception;

/**
 * 基础异常
 *
 * @Auther: hrabbit
 * @Date: 2018-12-17 6:34 PM
 * @Description:
 */
public class BaseException extends RuntimeException {

    private Integer code;

    private String message;

    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public BaseException(BussinessExceptionEnum bussinessExceptionEnum){
        this.code = bussinessExceptionEnum.getCode();
        this.message = bussinessExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
