package com.jhinno.sdk.openapi.api.organization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.JHApiExecution;
import com.jhinno.sdk.openapi.api.JHRequestExecution;
import com.jhinno.sdk.openapi.api.PageResult;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.client.JHApiClient;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关接口执行器
 * <p>
 * 注：要调用以下接口，传入的username必须有user_manager的权限
 *
 * @author yanlongqi
 * @date 2024/2/6 17:37
 */
@NoArgsConstructor
public class JHUserApiExecution implements JHApiExecution {

    private JHRequestExecution execution;

    public void init(JHRequestExecution execution) {
        this.execution = execution;
    }

    /**
     * 分页查询用户列表
     *
     * @param username 用户名
     * @param keyword  检索关键字
     * @param depName  部门名称
     * @param userConf 密级
     * @return 分页的用户列表
     */
    public PageResult<UserInfo> getUserList(String username, String keyword, String depName, String userConf) {
        Map<String, Object> params = new HashMap<>(3);
        if (StringUtils.isNotBlank(keyword)) {
            params.put("keyWord", keyword);
        }
        if (StringUtils.isNotBlank(depName)) {
            params.put("depName", depName);
        }
        if (StringUtils.isNotBlank(userConf)) {
            params.put("userConf", userConf);
        }
        String path = JHApiClient.getUrl(UserPathConstant.USERS_PATH, params);
        return execution.get(path, username, new TypeReference<ResponseResult<PageResult<UserInfo>>>() {
        });
    }

    /**
     * 添加用户
     *
     * @param username 用户名
     * @param userInfo 用户信息
     */
    public void addUser(String username, AddUpdateUserInfo userInfo) {
        execution.post(UserPathConstant.USERS_PATH, username, userInfo);
    }

    /**
     * 修改用户信息
     *
     * @param username 用户名
     * @param userInfo 用户信息
     */
    public void updateUser(String username, AddUpdateUserInfo userInfo) {
        if (StringUtils.isBlank(userInfo.getUserName())) {
            throw new ArgsException("userInfo中userName的值不能为空");
        }
        String path = UserPathConstant.USERS_USERNAME_PATH.replace("{username}", userInfo.getUserName());
        execution.put(path, username, userInfo);
    }

    /**
     * 修改或重置用户密码
     *
     * <ul>
     * <li>当type值为{@link UpdateUserPasswordType#FORCE_UPDATE_PASSWORD_TYPE}重置密码后用户再次登录需要修改密码</li>
     * <li>当type值为{@link UpdateUserPasswordType#RESET_UPDATE_PASSWORD_TYPE}重置用户的密码</li>
     * <li>当type值为空时修改用户密码</li>
     * </ul>
     * 参数怎么传，还需进一步确认，此处需要增加三个重构，方便开发者调用
     *
     * @param username               用户名
     * @param updatePasswordUsername 被修改的用户名
     * @param oldPassword            旧密码
     * @param password               新密码
     * @param type                   类型，（非必填，取值见{@link UpdateUserPasswordType}）
     */
    public void updateUserPassword(String username, String updatePasswordUsername, String oldPassword, String password,
            String type) {
        if (StringUtils.isBlank(updatePasswordUsername)) {
            throw new ArgsException("updatePasswordUsername不能为空");
        }
        String path = UserPathConstant.USERS_RESET_PASSWORD_PATH.replace("{username}", updatePasswordUsername);
        Map<String, Object> params = new HashMap<>(4);
        if (StringUtils.isNotBlank(oldPassword)) {
            params.put("oldPassword", oldPassword);
        }
        if (StringUtils.isNotBlank("type")) {
            params.put("type", type);
        }
        params.put("password", password);
        execution.put(path, username, params);
    }

    /**
     * 修改用户的密码
     *
     * @param username               用户名
     * @param updatePasswordUsername 被修改的用户名
     * @param oldPassword            旧密码
     * @param password               新密码
     */
    public void updateUserPassword(String username, String updatePasswordUsername, String oldPassword,
            String password) {
        updateUserPassword(username, updatePasswordUsername, oldPassword, password, null);
    }

    /**
     * 重置用户密码后强制用户修改密码
     *
     * @param username               用户名
     * @param updatePasswordUsername 被修改的用户名
     * @param password               新的用户密码
     */
    public void resetForceUpdatePassword(String username, String updatePasswordUsername, String password) {
        updateUserPassword(username, updatePasswordUsername, null, password,
                UpdateUserPasswordType.FORCE_UPDATE_PASSWORD_TYPE);
    }

    /**
     * 重置用户名
     *
     * @param username               用户名
     * @param updatePasswordUsername 被修改的用户密码
     * @param password               新的用户密码
     */
    public void resetPassword(String username, String updatePasswordUsername, String password) {
        updateUserPassword(username, updatePasswordUsername, null, password,
                UpdateUserPasswordType.RESET_UPDATE_PASSWORD_TYPE);
    }

    /**
     * 删除用户
     *
     * @param username       用户名
     * @param deleteUsername 被删除的用户名
     */
    public void deleteUser(String username, String deleteUsername) {
        if (StringUtils.isBlank(deleteUsername)) {
            throw new ArgsException("deleteUsername不能为空");
        }
        execution.delete(UserPathConstant.USERS_USERNAME_PATH.replace("{username}", deleteUsername), username);
    }

}
