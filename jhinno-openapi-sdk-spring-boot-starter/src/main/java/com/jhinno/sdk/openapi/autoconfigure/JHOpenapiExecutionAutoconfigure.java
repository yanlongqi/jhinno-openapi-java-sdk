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

    @Bean
    public JHAppApiExecution appApiExecution() {
        JHAppApiExecution execution = new JHAppApiExecution(client);
        execution.setTokenTimeout(properties.getTokenTimeout());
        execution.setTokenResidueTime(properties.getTokenResidueTime());
        execution.setUsedServerTime(properties.isUsedServerTime());
        return execution;
    }

    @Bean
    public JHDataApiExecution dataApiExecution() {
        JHDataApiExecution execution = new JHDataApiExecution(client);
        execution.setTokenTimeout(properties.getTokenTimeout());
        execution.setTokenResidueTime(properties.getTokenResidueTime());
        execution.setUsedServerTime(properties.isUsedServerTime());
        return execution;
    }


    @Bean
    public JHFileApiExecution fileApiExecution() {
        JHFileApiExecution execution = new JHFileApiExecution(client);
        execution.setTokenTimeout(properties.getTokenTimeout());
        execution.setTokenResidueTime(properties.getTokenResidueTime());
        execution.setUsedServerTime(properties.isUsedServerTime());
        return execution;
    }

    @Bean
    public JHJobApiExecution jobApiExecution() {
        JHJobApiExecution execution = new JHJobApiExecution(client);
        execution.setTokenTimeout(properties.getTokenTimeout());
        execution.setTokenResidueTime(properties.getTokenResidueTime());
        execution.setUsedServerTime(properties.isUsedServerTime());
        return execution;
    }


    @Bean
    public JHDepartmentApiExecution departmentApiExecution() {
        JHDepartmentApiExecution execution = new JHDepartmentApiExecution(client);
        execution.setTokenTimeout(properties.getTokenTimeout());
        execution.setTokenResidueTime(properties.getTokenResidueTime());
        execution.setUsedServerTime(properties.isUsedServerTime());
        return execution;
    }


    @Bean
    public JHUserApiExecution userApiExecution() {
        JHUserApiExecution execution = new JHUserApiExecution(client);
        execution.setTokenTimeout(properties.getTokenTimeout());
        execution.setTokenResidueTime(properties.getTokenResidueTime());
        execution.setUsedServerTime(properties.isUsedServerTime());
        return execution;
    }
}
