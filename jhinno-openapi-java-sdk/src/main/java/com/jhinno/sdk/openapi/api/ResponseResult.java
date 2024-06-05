package com.jhinno.sdk.openapi.api;

import lombok.Data;

/**
 * @author yanlongqi
 * @date 2024/1/31 10:16
 */
@Data
public class ResponseResult<T>  {


    /**
     * 状态码
     */
    private int code;

    /**
     * 状态
     */
    private String result;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

}
