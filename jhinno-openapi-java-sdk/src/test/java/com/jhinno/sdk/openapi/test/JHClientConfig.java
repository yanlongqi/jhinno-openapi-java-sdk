package com.jhinno.sdk.openapi.test;

import com.jhinno.sdk.openapi.AuthType;
import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.api.data.JHDataApiExecution;
import com.jhinno.sdk.openapi.api.file.JHFileApiExecution;
import com.jhinno.sdk.openapi.api.job.JHJobApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHDepartmentApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHUserApiExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;

import java.util.HashMap;
import java.util.Map;

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
    public static final JHApiClient client = new JHApiClient("https://192.168.38.160/appform");

    public static final Map<Class<? extends JHApiExecution>, JHApiExecution> jhApiClientMap = new HashMap<>();

    public static final String ACCESS_KEY = "35154207caa94be582e75da31ec8e9f2";

    public static final String ACCESS_KEY_SECRET = "2cdb5ecbe51543ad9a08ad21c32e2dd9";

    static {
        client.initDefaultApiClient();
        jhApiClientMap.put(JHAppApiExecution.class, new JHAppApiExecution());
        jhApiClientMap.put(JHDataApiExecution.class, new JHDataApiExecution());
        jhApiClientMap.put(JHFileApiExecution.class, new JHFileApiExecution());
        jhApiClientMap.put(JHJobApiExecution.class, new JHJobApiExecution());
        jhApiClientMap.put(JHDepartmentApiExecution.class, new JHDepartmentApiExecution());
        jhApiClientMap.put(JHUserApiExecution.class, new JHUserApiExecution());

        jhApiClientMap.forEach((k, v) -> {
            v.setJhApiClient(client);
            v.setAuthType(AuthType.ACCESS_SECRET_MODE);
            v.setAccessKey(ACCESS_KEY);
            v.setAccessKeySecret(ACCESS_KEY_SECRET);
            v.setUsedServerTime(true);
        });
    }

}
