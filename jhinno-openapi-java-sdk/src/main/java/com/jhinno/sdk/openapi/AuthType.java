package com.jhinno.sdk.openapi;

/**
 * 目前Appform的认证类型有两个，Token模式和AK/SK的模式。
 * 推荐使用AK/SK的模式，Token模式后续将被弃用
 */
public enum AuthType {

    /**
     * Token模式
     */
    TOKEN_MODE,

    /**
     * AK/SK模式
     */
    ACCESS_SECRET_MODE,

}
