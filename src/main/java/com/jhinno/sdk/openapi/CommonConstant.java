package com.jhinno.sdk.openapi;

/**
 * @author yanlongqi
 * @date 2024/1/31 10:17
 */
public class CommonConstant {

    /**
     * 请求成功时的标识
     */
    public static final String SUCCESS = "success";

    /**
     * 请求失败时的标识
     */
    public static final String FAILED = "failed";


    /**
     * 默认的token有效时间（单位：分钟）
     */
    public static final int DEFAULT_TOKEN_EFFECTIVE_TIME = 30;


    /**
     * token 默认剩余时间
     */
    public static final int DEFAULT_TOKEN_RESIDUE_TIME = 5;

    /**
     * 获取token时AES加密的默认key
     */
    public static final String DEFAULT_AES_KEY = "jin5no@aqec8gtw6";
}
