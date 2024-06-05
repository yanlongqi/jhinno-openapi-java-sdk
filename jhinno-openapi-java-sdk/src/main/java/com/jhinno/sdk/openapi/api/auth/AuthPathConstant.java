package com.jhinno.sdk.openapi.api.auth;

/**
 * 鉴权相关接口路径
 *
 * @author yanlongqi
 * @date 2024/1/29 10:32
 */
public class AuthPathConstant {

    /**
     * 获取用户token
     */
    public static final String AUTH_TOKEN_PATH = "/ws/api/auth/token";

    /**
     * 注销token
     */
    public static final String AUTH_LOGOUT = "/ws/api/auth/logout";


    /**
     * 用户注册
     */
    public static final String AUTH_REGISTER = "/ws/api/auth/register";

    /**
     * 测试服务器是否可用
     */
    public static final String PING = "/ws/api/ping";

}
