package com.jhinno.sdk.openapi.utils;

import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.autoconfigure.JHOpenapiProperties;
import com.jhinno.sdk.openapi.client.JHApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JHOpenApiConfig {

    private final JHApiClient client;
    private final JHOpenapiProperties properties;

    /**
     * 配置执行器
     *
     * @param execution 执行器
     * @param <T>       执行器类型
     * @return 配置的执行器
     */
    public <T extends JHApiExecution> T configJHApiExecution(T execution) {
        execution.setJhApiClient(client);
        execution.setForceGetToken(properties.isForceGetToken());
        execution.setAuthType(properties.getAuthType());
        execution.setAccessKey(properties.getAccessKey());
        execution.setAccessKeySecret(properties.getAccessKeySecret());
        execution.setTokenTimeout(properties.getTokenTimeout());
        execution.setTokenResidueTime(properties.getTokenResidueTime());
        execution.setUsedServerTime(properties.isUsedServerTime());
        return execution;
    }

}
