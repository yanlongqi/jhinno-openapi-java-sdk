package com.jhinno.sdk.openapi.test.auth;

import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import org.junit.Test;

/**
 * 鉴权相关测试累类
 *
 * @author yanlongqi
 * @date 2024/2/1 18:06
 */
public class AuthApiTest {

    JHAppApiExecution jhAppApiExecution = new JHAppApiExecution(JHClientConfig.client);

    /**
     * 测试获取token
     */
    @Test
    public void testGetToken() {
        String token = jhAppApiExecution.getToken("jhadmin");
        System.out.println("token:" + token);
    }

    /**
     * 测试退出登录
     */
    @Test
    public void testLogout(){
        jhAppApiExecution.logout("lqyan");
    }
}
