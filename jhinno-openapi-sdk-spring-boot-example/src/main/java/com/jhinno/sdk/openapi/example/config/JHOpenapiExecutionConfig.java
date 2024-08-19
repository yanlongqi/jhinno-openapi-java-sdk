package com.jhinno.sdk.openapi.example.config;

import com.jhinno.sdk.openapi.example.api.extend.JHFileApiExtendExecution;
import com.jhinno.sdk.openapi.utils.JHOpenApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class JHOpenapiExecutionConfig {


    @Bean
    public JHFileApiExtendExecution fileApiExtendExecution(JHOpenApiConfig jhOpenApiConfig) {
        return jhOpenApiConfig.configJHApiExecution(new JHFileApiExtendExecution());
    }
}
