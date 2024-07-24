package com.jhinno.sdk.openapi.autoconfigure;

import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.api.data.JHDataApiExecution;
import com.jhinno.sdk.openapi.api.file.JHFileApiExecution;
import com.jhinno.sdk.openapi.api.job.JHJobApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHDepartmentApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHUserApiExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * openapi执行器自动配置
 *
 * @author yanlongqi
 * @date 2024/6/4 17:27
 */
@Configuration
@RequiredArgsConstructor
public class JHOpenapiExecutionAutoconfigure {


    private final JHOpenapiProperties properties;
    private final JHApiClient client;

    @Bean
    public List<JHApiExecution> ApiExecution() {
        List<JHApiExecution> executions = new ArrayList<>();
        executions.add(new JHAppApiExecution());
        executions.add(new JHDataApiExecution());
        executions.add(new JHFileApiExecution());
        executions.add(new JHJobApiExecution());
        executions.add(new JHDepartmentApiExecution());
        executions.add(new JHUserApiExecution());
        executions.forEach(t -> {
            t.setJhApiClient(client);
            t.setForceGetToken(properties.isForceGetToken());
            t.setAuthType(properties.getAuthType());
            t.setAccessKey(properties.getAccessKey());
            t.setAccessKeySecret(properties.getAccessKeySecret());
            t.setTokenTimeout(properties.getTokenTimeout());
            t.setTokenResidueTime(properties.getTokenResidueTime());
            t.setUsedServerTime(properties.isUsedServerTime());
        });
        return executions;
    }
}
