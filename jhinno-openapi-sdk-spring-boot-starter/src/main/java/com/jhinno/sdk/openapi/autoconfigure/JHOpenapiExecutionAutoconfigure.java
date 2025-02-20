package com.jhinno.sdk.openapi.autoconfigure;

import com.jhinno.sdk.openapi.JHApiExecution;
import com.jhinno.sdk.openapi.api.JHRequestExecution;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.api.data.JHDataApiExecution;
import com.jhinno.sdk.openapi.api.file.JHFileApiExecution;
import com.jhinno.sdk.openapi.api.job.JHJobApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHDepartmentApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHUserApiExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.config.BeanPostProcessor;
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
public class JHOpenapiExecutionAutoconfigure implements BeanPostProcessor {

    private final JHRequestExecution jhRequestExecution;

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof JHApiExecution) {
            ((JHApiExecution) bean).init(jhRequestExecution);
        }
        return bean;
    }

    @Bean
    public JHAppApiExecution appApiExecution() {
        return new JHAppApiExecution();
    }

    @Bean
    public JHDataApiExecution dataApiExecution() {
        return new JHDataApiExecution();
    }

    @Bean
    public JHFileApiExecution fileApiExecution() {
        return new JHFileApiExecution();
    }

    @Bean
    public JHJobApiExecution jobApiExecution() {
        return new JHJobApiExecution();
    }

    @Bean
    public JHDepartmentApiExecution departmentApiExecution() {
        return new JHDepartmentApiExecution();
    }

    @Bean
    public JHUserApiExecution userApiExecution() {
        return new JHUserApiExecution();
    }

}
