package com.jhinno.sdk.openapi.test.organization;

import com.jhinno.sdk.openapi.api.PageResult;
import com.jhinno.sdk.openapi.api.organization.AddUpdateUserInfo;
import com.jhinno.sdk.openapi.api.organization.JHUserApiExecution;
import com.jhinno.sdk.openapi.api.organization.UserInfo;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import com.jhinno.sdk.openapi.utils.JsonUtil;
import org.junit.Test;

/**
 * 用户相关执行器单元测试
 *
 * @author yanlongqi
 * @date 2024/2/6 18:09
 */
public class UserApiTest {

    private static final JHUserApiExecution execution = JHClientConfig.API_EXECUTION_MANAGE.getApiExecution(JHUserApiExecution.class);


    /**
     * 测试获取组织列表
     */
    @Test
    public void testGetUserList() {
        PageResult<UserInfo> result = execution.getUserList(null, null, null);
        System.out.println(JsonUtil.objectToString(result));
    }

    /**
     * 测试添加用户，密码可以不传
     */
    @Test
    public void addUser() {
        AddUpdateUserInfo addUpdateUserInfo = new AddUpdateUserInfo();
        addUpdateUserInfo.setUserName("zhangsan3");
        addUpdateUserInfo.setUserNameCn("张三3");
        addUpdateUserInfo.setUserPassword("Jhadmin123");
        addUpdateUserInfo.setDepName("defaultDep");
        execution.addUser(addUpdateUserInfo);
    }


    /**
     * 测试修改用户，需要修改，只传我需要修改的值，并且用户修改的接口报错
     */
    @Test
    public void updateUser() {
        AddUpdateUserInfo addUpdateUserInfo = new AddUpdateUserInfo();
        addUpdateUserInfo.setUserName("zhangsan3");
        addUpdateUserInfo.setUserNameCn("张三1");
        addUpdateUserInfo.setDepName("defaultDep");
        addUpdateUserInfo.setUserPassword("Jhadmin123");
        execution.updateUser(addUpdateUserInfo);
    }


    /**
     * 测试删除用户
     */
    @Test
    public void testDeleteUser() {
        execution.deleteUser("zhangsan3");
    }


    /**
     * 测试修改用户密码
     */
    @Test
    public void testUpdateUserPassword() {
        // 修改用户密码，应该是自己的密码需要自己的token修改
        execution.updateUserPassword("zhangsan1", "Jhadmin124", "Jhadmin1241");

    }
}
