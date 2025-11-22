package com.jhinno.sdk.openapi;


import java.util.Map;

public interface JHApiRequestHandler {

    /**
     * 获取当前登录的用户名
     *
     * @return 用户名
     */
    default String getCurrentUserName() {
        return null;
    }

    /**
     * 构建一个带token的请求头
     *
     * @param headers 处理器的请求头
     * @return 请求头
     */
    default Map<String, Object> getHeaders(Map<String, Object> headers) {
        return headers;
    }
}
