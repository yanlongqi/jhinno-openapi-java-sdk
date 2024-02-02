package com.jhinno.sdk.openapi.test.auth;

import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.junit.Test;

/**
 * 鉴权相关测试累
 * @author yanlongqi
 * @date 2024/2/1 18:06
 */
public class AuthApiTest {

    /**
     * 启动会话
     */
    @Test
    public void testStartApp() {
        JHApiClient client = JHApiClient.build("https://192.168.87.25/appform");
        JHAppApiExecution jhAppApiExecution = new JHAppApiExecution(client);
        String token = jhAppApiExecution.getToken("jhadmin");
        System.out.println(token);
    }
}
