package com.jhinno.sdk.openapi;

/**
 * 目前Appform的认证类型有两个，Token模式和AK/SK的模式。
 * 使用AK/SK的模式需要应用门户安装API接口安全插件，并且需要配置AK/SK信息。
 * 推荐使用AK/SK的模式，Token模式后续将被弃用
 */
public enum AuthType {

    /**
     * Token模式，不推荐使用，后续将被弃用
     */
    TOKEN_MODE,

    /**
     * AK/SK模式，推荐使用，但需要服务端安装接口安全插件
     */
    ACCESS_SECRET_MODE,

}
