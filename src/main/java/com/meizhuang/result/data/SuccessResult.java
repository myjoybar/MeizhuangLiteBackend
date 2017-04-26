package com.meizhuang.result.data;

import java.util.stream.BaseStream;

/**
 * Created by joybar on 2017/4/26.
 */
public class SuccessResult<T extends Object> extends BaseResultInfo {

    public static final Integer SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "OK";

    public SuccessResult(T resultData) {
        this.setCode(SUCCESS_CODE);
        this.setMessage(SUCCESS_MSG);
        this.setResponseTime(System.currentTimeMillis());
        this.setData(resultData);
    }
}
