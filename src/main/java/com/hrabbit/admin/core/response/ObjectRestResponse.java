package com.hrabbit.admin.core.response;

/**
 * Object类型数据
 * @Auther: hrabbit
 * @Date: 2018-11-15 3:38 PM
 * @Description:
 */
public class ObjectRestResponse<T> extends BaseResponse {

    T data;

    boolean rel;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }


    public ObjectRestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public ObjectRestResponse data(T data) {
        this.setData(data);
        return this;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}