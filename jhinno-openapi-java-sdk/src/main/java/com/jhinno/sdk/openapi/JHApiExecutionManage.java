package com.jhinno.sdk.openapi;

import java.util.HashMap;
import java.util.Map;

import com.jhinno.sdk.openapi.api.JHRequestExecution;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.api.data.JHDataApiExecution;
import com.jhinno.sdk.openapi.api.file.JHFileApiExecution;
import com.jhinno.sdk.openapi.api.job.JHJobApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHDepartmentApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHUserApiExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;
import com.jhinno.sdk.openapi.client.JHApiHttpClient;

public class JHApiExecutionManage {

    public static final Map<Class<? extends JHApiExecutionAbstract>, JHApiExecutionAbstract> API_CLIENT_MAP = new HashMap<>();
    public final JHRequestExecution EXECUTION;

    /**
     * 创建一个带有默认HTTP客户端的API执行管理器
     *
     * @param appformBaseUrl 景行API的URL
     */
    public JHApiExecutionManage(String appformBaseUrl) {
        JHApiClient client = new JHApiClient(appformBaseUrl);
        client.initDefaultApiClient();
        EXECUTION = new JHRequestExecution(client);
        initApiExecution();
    }

    /**
     * 创建一个带有自定义HTTP客户端的API执行管理器
     *
     * @param httpClient     自定义HTTP客户端
     * @param appformBaseUrl 景行API的URL
     */
    public JHApiExecutionManage(JHApiHttpClient httpClient, String appformBaseUrl) {
        JHApiClient client = new JHApiClient(appformBaseUrl);
        client.setApiHttpClient(httpClient);
        EXECUTION = new JHRequestExecution(client);
        EXECUTION.setAuthType(AuthType.TOKEN_MODE);
        initApiExecution();
    }

    /**
     * 初始化默认的执行器
     */
    private void initApiExecution() {
        registerApiExecution(new JHAppApiExecution());
        registerApiExecution(new JHDataApiExecution());
        registerApiExecution(new JHFileApiExecution());
        registerApiExecution(new JHJobApiExecution());
        registerApiExecution(new JHDepartmentApiExecution());
        registerApiExecution(new JHUserApiExecution());
    }

    /**
     * 配置API执行器
     *
     * @param configurator API执行器配置器
     */
    public void configureApiExecution(ApiExecutionConfigurator configurator) {
        configurator.configure(EXECUTION);
    }

    /**
     * 注册自定义的执行器
     *
     * @param execution 自定义的执行器实例
     */
    public void registerApiExecution(JHApiExecutionAbstract execution) {
        execution.init(EXECUTION);
        API_CLIENT_MAP.put(execution.getClass(), execution);
    }

    /**
     * 获取一个特定的执行器用于调用接口
     *
     * @param <T>   执行器的类型
     * @param clazz 执行器的类
     * @return 执行器实例
     */
    public <T extends JHApiExecutionAbstract> T getApiExecution(Class<? extends T> clazz) {
        return (T) API_CLIENT_MAP.get(clazz);
    }

    /**
     * API执行器配置器接口，用于配置API执行器的参数
     */
    public interface ApiExecutionConfigurator {

        /**
         * 配置API执行器的参数
         *
         * @param execution API执行器实例
         */
        void configure(JHRequestExecution execution);

    }
}
