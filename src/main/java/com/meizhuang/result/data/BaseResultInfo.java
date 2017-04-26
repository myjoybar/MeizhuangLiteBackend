package com.meizhuang.result.data;

/**
 * Created by joybar on 2017/4/26.
 */
public  class BaseResultInfo<T> {

    private Integer code;
    private String message;
    private Long responseTime;
    private T data;

    public BaseResultInfo() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
