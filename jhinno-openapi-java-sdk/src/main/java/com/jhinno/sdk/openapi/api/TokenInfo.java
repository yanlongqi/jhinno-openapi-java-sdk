package com.jhinno.sdk.openapi.api;

/**
 * @author yanlongqi
 * @date 2024/1/31 10:06
 */
public class TokenInfo {


    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户令牌
     */
    private String token;


    /**
     * 用户申请令牌时的时间戳，用于校验令牌是否过期
     */
    private long currentTimestamp;

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    /**
     * 获取申请令牌时的时间戳
     *
     * @return 时间戳
     */
    public long getCurrentTimestamp() {
        return currentTimestamp;
    }

    /**
     * 设置时间戳
     *
     * @param currentTimestamp 时间戳
     */
    public void setCurrentTimestamp(long currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }
}
