package com.jhinno.sdk.openapi.test;

import com.jhinno.sdk.openapi.client.JHApiClient;

/**
 * SDK Client 的配置
 *
 * @author yanlongqi
 * @date 2024/2/4 17:11
 */
public class JHClientConfig {

    /**
     * 初始化JHApi客户端
     */
    public static final JHApiClient client = JHApiClient.build("https://172.17.0.5/appform");

}
