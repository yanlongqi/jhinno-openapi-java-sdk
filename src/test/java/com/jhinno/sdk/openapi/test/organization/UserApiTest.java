package com.jhinno.sdk.openapi.test.organization;

import com.jhinno.sdk.openapi.api.PageResult;
import com.jhinno.sdk.openapi.api.organization.JHUserApiExecution;
import com.jhinno.sdk.openapi.api.organization.UserInfo;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import org.junit.Test;

/**
 * 用户相关执行器单元测试
 *
 * @author yanlongqi
 * @date 2024/2/6 18:09
 */
public class UserApiTest {

    private static final JHUserApiExecution execution = new JHUserApiExecution(JHClientConfig.client);


    /**
     * 测试获取组织列表
     */
    @Test
    public void testGetUserList() {
        PageResult<UserInfo> result = execution.getUserList("jhadmin", null, null, null);
        System.out.println("result = " + result);
    }
}
