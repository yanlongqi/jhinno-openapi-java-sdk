package com.jhinno.sdk.openapi.autoconfigure;

import com.jhinno.sdk.openapi.JHApiExecutionAbstract;
import com.jhinno.sdk.openapi.api.JHRequestExecution;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.api.data.JHDataApiExecution;
import com.jhinno.sdk.openapi.api.file.JHFileApiExecution;
import com.jhinno.sdk.openapi.api.job.JHJobApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHDepartmentApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHUserApiExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof JHApiExecutionAbstract) {
            ((JHApiExecutionAbstract) bean).setExecution(jhRequestExecution);
        }
        return bean;
    }

    @Bean
    @ConditionalOnMissingBean
    public JHAppApiExecution appApiExecution() {
        return new JHAppApiExecution();
    }

    @Bean
    @ConditionalOnMissingBean
    public JHDataApiExecution dataApiExecution() {
        return new JHDataApiExecution();
    }

    @Bean
    @ConditionalOnMissingBean
    public JHFileApiExecution fileApiExecution() {
        return new JHFileApiExecution();
    }

    @Bean
    @ConditionalOnMissingBean
    public JHJobApiExecution jobApiExecution() {
        return new JHJobApiExecution();
    }

    @Bean
    @ConditionalOnMissingBean
    public JHDepartmentApiExecution departmentApiExecution() {
        return new JHDepartmentApiExecution();
    }

    @Bean
    @ConditionalOnMissingBean
    public JHUserApiExecution userApiExecution() {
        return new JHUserApiExecution();
    }

}
