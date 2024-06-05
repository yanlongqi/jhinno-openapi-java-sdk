package com.jhinno.sdk.openapi.client;

/**
 * 默认的HTTP客户端请求配置
 *
 * @author yanlongqi
 * @date 2024/1/30 18:12
 */
public class DefaultHttpClientConfig {

    /**
     * 默认设置最大连接数
     */
    public static int MAX_TOTAL = 200;

    /**
     * 默认服务每次能并行接收的请求数量
     */
    public static int MAX_PER_ROUT = 20;


    /**
     * 默认socket连接超时的时间(单位：毫秒)
     */
    public static final int SOCKET_TIMEOUT = 5000;

    /**
     * 默认连接超时的时间(单位：毫秒)
     */
    public static final int CONNECT_TIMEOUT = 5000;

    /**
     * 默认请求超时的时间(单位：毫秒)
     */
    public static final int CONNECTION_REQUEST_TIMEOUT = 5000;
}
