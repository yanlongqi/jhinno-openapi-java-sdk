package com.jhinno.sdk.openapi.test;

import com.jhinno.sdk.openapi.AuthType;
import com.jhinno.sdk.openapi.JHApiExecutionManage;
import com.jhinno.sdk.openapi.JHApiRequestHandler;

/**
 * SDK Client 的配置
 *
 * @author yanlongqi
 * @date 2024/2/4 17:11
 */
public class JHClientConfig {

    public static final JHApiRequestHandler REQUEST_HANDLER = new JHApiRequestHandler() {
        @Override
        public String getCurrentUserName() {
            return "lqyan";
        }
    };

    public static final String APPFORM_SERVER_URL = "https://192.168.41.12";
    public static final String ACCESS_KEY = "74e02e60d47343299e4c130ea4a7685d";
    public static final String ACCESS_KEY_SECRET = "91ff101a1f7542dfb00049c39b4e8995";


    /**
     * 创建一个API执行器管理器
     */
    public static final JHApiExecutionManage API_EXECUTION_MANAGE = new JHApiExecutionManage(APPFORM_SERVER_URL, REQUEST_HANDLER);


    static {
        API_EXECUTION_MANAGE.configureApiExecution(t -> {
            // 默认为使用Token模式，如何使用的Token模式，则不需要配置ACCESS_KEY和ACCESS_KEY SECRET
            t.setAuthType(AuthType.ACCESS_SECRET_MODE);
            t.setAccessKey(ACCESS_KEY);
            t.setAccessKeySecret(ACCESS_KEY_SECRET);
        });
    }

}
