package com.jhinno.sdk.openapi.test;

import com.jhinno.sdk.openapi.AuthType;
import com.jhinno.sdk.openapi.JHApiExecutionManage;
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
     * 创建一个API执行器管理器
     */
    public static final JHApiExecutionManage API_EXECUTRON_MANAGE = new JHApiExecutionManage(
            "https://172.20.0.200");

    public static final String ACCESS_KEY = "8147c7470bfd4a27952fe750c6bc7cef";

    public static final String ACCESS_KEY_SECRET = "899b13f590394c3daafc6468fed4b1df";

    static {
        API_EXECUTRON_MANAGE.configureApiExecution(t -> {
            // 默认为使用Token模式，如何使用的Token模式，则不需要配置ACCESS_KEY和ACCESS_KEY SECRET
            t.setAuthType(AuthType.ACCESS_SECRET_MODE);
            t.setAccessKey(ACCESS_KEY);
            t.setAccessKeySecret(ACCESS_KEY_SECRET);
        });
    }

}
