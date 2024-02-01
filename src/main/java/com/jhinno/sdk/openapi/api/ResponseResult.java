package com.jhinno.sdk.openapi.api;

/**
 * @author yanlongqi
 * @date 2024/1/31 10:16
 */
public class ResponseResult<T> {


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


    /**
     * 获取请求编号
     *
     * @return 请求编号
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置请求编号
     *
     * @param code 请求编号
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取请求状态
     *
     * @return 请求状态
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置请求状态
     *
     * @param result 请求状态
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取请求信息
     *
     * @return 请求信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置请求信息
     *
     * @param message 请求信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取请求数据
     *
     * @return 请求数据
     */
    public T getData() {
        return data;
    }

    /**
     * 设置去就去数据
     *
     * @param data 请求数据
     */
    public void setData(T data) {
        this.data = data;
    }
}
