package com.jhinno.sdk.openapi.autoconfigure;

import com.jhinno.sdk.openapi.api.JHRequestExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;
import com.jhinno.sdk.openapi.client.JHApiHttpClientImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * openapi客户端自动配置
 *
 * @author yanlongqi
 * @date 2024/6/4 16:01
 */
@Configuration
@EnableConfigurationProperties(JHOpenapiProperties.class)
public class JHOpenapiClientAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    public JHApiClient jhApiClient(JHOpenapiProperties properties) {
        JHApiClient jhApiClient = new JHApiClient(properties.getServerUrl());
        JHApiHttpClientImpl jhApiHttpClient = new JHApiHttpClientImpl();
        jhApiHttpClient.init(properties.getSocketTimeout(), properties.getConnectTimeout(), properties.getConnectRequestTimeout());
        jhApiHttpClient.createHttpClients(properties.getMaxTotal(), properties.getMaxPerRout());
        jhApiClient.setApiHttpClient(jhApiHttpClient);
        return jhApiClient;
    }

    @Bean
    public JHRequestExecution requestExecution(JHApiClient jhApiClient, JHOpenapiProperties properties) {
        JHRequestExecution requestExecution = new JHRequestExecution(jhApiClient);
        requestExecution.setForceGetToken(properties.isForceGetToken());
        requestExecution.setAuthType(properties.getAuthType());
        requestExecution.setAccessKey(properties.getAccessKey());
        requestExecution.setAccessKeySecret(properties.getAccessKeySecret());
        requestExecution.setTokenTimeout(properties.getTokenTimeout());
        requestExecution.setTokenResidueTime(properties.getTokenResidueTime());
        requestExecution.setUsedServerTime(properties.isUsedServerTime());
        return requestExecution;
    }

}
