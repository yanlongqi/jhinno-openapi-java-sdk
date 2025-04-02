package com.jhinno.sdk.openapi.autoconfigure;

import com.jhinno.sdk.openapi.AuthType;
import com.jhinno.sdk.openapi.client.DefaultHttpClientConfig;
import com.jhinno.sdk.openapi.constant.CommonConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yanlongqi
 * @date 2024/6/4 16:03
 */
@Data
@ConfigurationProperties(prefix = CommonConstants.CONFIG_PREFIX)
public class JHOpenapiProperties {

    /**
     * 接口服务的BaseURL, 列如：https://192.168.87.20
     */
    private String serverUrl;

    /**
     * 设置连接池的最大连接数，默认{@link DefaultHttpClientConfig#MAX_TOTAL}
     */
    private int maxTotal = DefaultHttpClientConfig.MAX_TOTAL;


    /**
     * 设置服务每次能并行接收的请求数量，默认{@link DefaultHttpClientConfig#MAX_PER_ROUTE}
     */
    private int maxPerRout = DefaultHttpClientConfig.MAX_PER_ROUTE;

    /**
     * 设置服务socket连接超时的时间(单位：毫秒)，默认{@link DefaultHttpClientConfig#SOCKET_TIMEOUT}
     */
    private int socketTimeout = DefaultHttpClientConfig.SOCKET_TIMEOUT;

    /**
     * 设置服务连接超时的时间(单位：毫秒)，默认{@link DefaultHttpClientConfig#CONNECT_TIMEOUT}
     */
    private int connectTimeout = DefaultHttpClientConfig.CONNECT_TIMEOUT;


    /**
     * 设置服务请求超时的时间(单位：毫秒)，默认{@link DefaultHttpClientConfig#CONNECTION_REQUEST_TIMEOUT}
     */
    private int connectRequestTimeout = DefaultHttpClientConfig.CONNECTION_REQUEST_TIMEOUT;


    /**
     * token的超时时间（单位：分钟）
     */
    private int tokenTimeout = DefaultHttpClientConfig.DEFAULT_TOKEN_EFFECTIVE_TIME;

    /**
     * token提前获取的时间（单位：分钟）
     */
    private int tokenResidueTime = DefaultHttpClientConfig.DEFAULT_TOKEN_RESIDUE_TIME;


    /**
     * 是否使用服务器时间
     */
    private boolean usedServerTime = DefaultHttpClientConfig.DEFAULT_IS_USED_SERVER_TIME;

    /**
     * 是否强制获取用户的token，默认{@link DefaultHttpClientConfig#DEFAULT_IS_FORCE_GET_TOKEN},
     * 如果强制获取token，则每次请求都去获取token；
     */
    private boolean isForceGetToken = DefaultHttpClientConfig.DEFAULT_IS_FORCE_GET_TOKEN;


    /**
     * 接口请求的认证类型
     */
    private AuthType authType = AuthType.ACCESS_SECRET_MODE;

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 访问密钥密码
     */
    private String accessKeySecret;
}
