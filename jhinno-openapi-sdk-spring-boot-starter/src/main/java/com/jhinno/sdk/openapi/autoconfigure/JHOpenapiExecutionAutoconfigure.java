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


    public void init(JHApiExecution apiExecution) {
        apiExecution.setJhApiClient(client);
        apiExecution.setForceGetToken(properties.isForceGetToken());
        apiExecution.setAuthType(properties.getAuthType());
        apiExecution.setAccessKey(properties.getAccessKey());
        apiExecution.setAccessKeySecret(properties.getAccessKeySecret());
        apiExecution.setTokenTimeout(properties.getTokenTimeout());
        apiExecution.setTokenResidueTime(properties.getTokenResidueTime());
        apiExecution.setUsedServerTime(properties.isUsedServerTime());
    }

    @Bean
    public JHAppApiExecution appApiExecution() {
        JHAppApiExecution jhAppApiExecution = new JHAppApiExecution();
        init(jhAppApiExecution);
        return jhAppApiExecution;
    }

    @Bean
    public JHDataApiExecution dataApiExecution() {
        JHDataApiExecution dataApiExecution = new JHDataApiExecution();
        init(dataApiExecution);
        return dataApiExecution;
    }


    @Bean
    public JHFileApiExecution fileApiExecution() {
        JHFileApiExecution fileApiExecution = new JHFileApiExecution();
        init(fileApiExecution);
        return fileApiExecution;
    }

    @Bean
    public JHJobApiExecution jobApiExecution() {
        JHJobApiExecution jobApiExecution = new JHJobApiExecution();
        init(jobApiExecution);
        return jobApiExecution;
    }

    @Bean
    public JHDepartmentApiExecution departmentApiExecution() {
        JHDepartmentApiExecution departmentApiExecution = new JHDepartmentApiExecution();
        init(departmentApiExecution);
        return departmentApiExecution;
    }

    @Bean
    public JHUserApiExecution userApiExecution() {
        JHUserApiExecution userApiExecution = new JHUserApiExecution();
        init(userApiExecution);
        return userApiExecution;
    }
}
