package com.jhinno.sdk.openapi.autoconfigure;

import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.api.data.JHDataApiExecution;
import com.jhinno.sdk.openapi.api.file.JHFileApiExecution;
import com.jhinno.sdk.openapi.api.job.JHJobApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHDepartmentApiExecution;
import com.jhinno.sdk.openapi.api.organization.JHUserApiExecution;
import com.jhinno.sdk.openapi.utils.JHOpenApiConfig;
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


    @Bean
    public JHAppApiExecution appApiExecution(JHOpenApiConfig jhOpenApiConfig) {
        return jhOpenApiConfig.configJHApiExecution(new JHAppApiExecution());
    }

    @Bean
    public JHDataApiExecution dataApiExecution(JHOpenApiConfig jhOpenApiConfig) {
        return jhOpenApiConfig.configJHApiExecution(new JHDataApiExecution());
    }


    @Bean
    public JHFileApiExecution fileApiExecution(JHOpenApiConfig jhOpenApiConfig) {
        return jhOpenApiConfig.configJHApiExecution(new JHFileApiExecution());
    }

    @Bean
    public JHJobApiExecution jobApiExecution(JHOpenApiConfig jhOpenApiConfig) {
        return jhOpenApiConfig.configJHApiExecution(new JHJobApiExecution());
    }

    @Bean
    public JHDepartmentApiExecution departmentApiExecution(JHOpenApiConfig jhOpenApiConfig) {
        return jhOpenApiConfig.configJHApiExecution(new JHDepartmentApiExecution());
    }

    @Bean
    public JHUserApiExecution userApiExecution(JHOpenApiConfig jhOpenApiConfig) {
        return jhOpenApiConfig.configJHApiExecution(new JHUserApiExecution());
    }
}
