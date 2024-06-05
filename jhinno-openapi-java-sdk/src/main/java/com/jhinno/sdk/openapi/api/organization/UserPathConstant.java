package com.jhinno.sdk.openapi.api.organization;

/**
 * 用户相关接口路径
 *
 * @author yanlongqi
 * @date 2024/2/6 17:37
 */
public class UserPathConstant {

    /**
     * 查询用户和修改用户
     */
    public static final String USERS_PATH = "/ws/api/users";


    /**
     * 修改用户和删除用户
     */
    public static final String USERS_USERNAME_PATH = "/ws/api/users/{username}";


    /**
     * 用户密码操作
     */
    public static final String USERS_RESET_PASSWORD_PATH = "/ws/api/users/{username}/password/password_reset";

}
