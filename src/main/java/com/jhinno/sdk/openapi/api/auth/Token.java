package com.jhinno.sdk.openapi.api.auth;

/**
 * 请求令牌，用户请求其他接口
 *
 * @author yanlongqi
 * @date 2024/1/31 10:30
 */
public class Token {

    /**
     * 令牌
     */
    private String token;

    /**
     * 获取令牌
     *
     * @return 令牌
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置令牌
     *
     * @param token 令牌
     */
    public void setToken(String token) {
        this.token = token;
    }
}
