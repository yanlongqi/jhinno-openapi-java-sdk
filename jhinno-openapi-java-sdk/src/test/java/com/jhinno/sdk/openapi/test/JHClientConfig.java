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
            "https://192.168.87.24");

    public static final String ACCESS_KEY = "e2544957e53b4377bb4f8203a094e50b";

    public static final String ACCESS_KEY_SECRET = "52d18cf7163047b78ea48756b8b40d28";

    static {
        API_EXECUTRON_MANAGE.configureApiExecution(t -> {
            // 默认为使用Token模式，如何使用的Token模式，则不需要配置ACCESS_KEY和ACCESS_KEY SECRET
             t.setAuthType(AuthType.ACCESS_SECRET_MODE);
            t.setAccessKey(ACCESS_KEY);
            t.setAccessKeySecret(ACCESS_KEY_SECRET);
        });
    }

}
