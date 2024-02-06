package com.jhinno.sdk.openapi.api.organization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.api.PageResult;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关接口执行器
 *
 * @author yanlongqi
 * @date 2024/2/6 17:37
 */
public class JHUserApiExecution extends JHApiExecution {
    /**
     * 获取一个执行器的实例
     *
     * @param jhApiClient 请求的客户端
     */
    public JHUserApiExecution(JHApiClient jhApiClient) {
        super(jhApiClient);
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
        return get(path, username, new TypeReference<ResponseResult<PageResult<UserInfo>>>() {
        });
    }
}
