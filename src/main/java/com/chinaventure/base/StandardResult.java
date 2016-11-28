package com.chinaventure.base;

/**
 * Created by huaxiujun on 2016/11/24.
 * 标准返回
 */
public class StandardResult {

    private int status;
    private String message;
    private Object result;

    /**
     * 发生错误的基础构建,返回值result默认为null
     *
     * @param status  错误码
     * @param message 错误消息
     */
    public StandardResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 成功的基础构建 status 默认为0 msg为"success"
     *
     * @param result 返回值
     */
    public StandardResult(Object result) {
        status = 0;
        message = "success";
        this.result = result;
    }

    //===================

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
