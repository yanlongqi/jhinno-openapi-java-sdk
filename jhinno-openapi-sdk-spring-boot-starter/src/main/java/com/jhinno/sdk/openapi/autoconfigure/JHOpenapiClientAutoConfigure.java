package com.jhinno.sdk.openapi.autoconfigure;

import com.jhinno.sdk.openapi.client.JHApiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * openapi客户端自动配置
 * @author yanlongqi
 * @date 2024/6/4 16:01
 */
@Configuration
@EnableConfigurationProperties(JHOpenapiProperties.class)
public class JHOpenapiClientAutoConfigure {


    @Bean
    @ConditionalOnMissingBean
    public JHApiClient jhApiClient(JHOpenapiProperties properties){
        return JHApiClient.build(
                properties.getServerUrl(),
                properties.getMaxTotal(),
                properties.getMaxPerRout(),
                properties.getSocketTimeout(),
                properties.getConnectTimeout(),
                properties.getConnectRequestTimeout()
        );
    }

}
