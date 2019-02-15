package com.hrabbit.admin.core.response;

/**
 * 返回List类型数据
 * @Auther: hrabbit
 * @Date: 2018-11-15 3:37 PM
 * @Description:
 */
public class ListRestResponse<T> {
    /**
     * 返回的内容
     */
    String msg;
    /**
     * 返回的消息
     */
    T result;

    /**
     * 消息个数
     */
    int count;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ListRestResponse count(int count) {
        this.setCount(count);
        return this;
    }

    public ListRestResponse count(Long count) {
        this.setCount(count.intValue());
        return this;
    }

    public ListRestResponse msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ListRestResponse result(T result) {
        this.setResult(result);
        return this;
    }

}