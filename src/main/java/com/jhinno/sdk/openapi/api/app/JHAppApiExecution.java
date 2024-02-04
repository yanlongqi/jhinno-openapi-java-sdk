package com.jhinno.sdk.openapi.api.app;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.ServiceException;
import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanlongqi
 * @date 2024/2/1 16:26
 */
public class JHAppApiExecution extends JHApiExecution {

    /**
     * 获取一个执行器的实例
     *
     * @param jhApiClient 请求的客户端
     */
    public JHAppApiExecution(JHApiClient jhApiClient) {
        super(jhApiClient);
    }

    /**
     * 启动一个会话
     *
     * @param username        用户名
     * @param appId           应用拆
     * @param appStartRequest 启动参数
     * @return JHClient协议链接
     */
    public AppStartedInfo desktopStart(String username, String appId, AppStartRequest appStartRequest) {
        String path = AppPathConstant.APPS_START_PATH.replace("{appId}", appId);
        List<AppStartedInfo> data = post(path, username, appStartRequest, new TypeReference<ResponseResult<List<AppStartedInfo>>>() {
        });
        if (CollectionUtil.isEmpty(data)) {
            throw new ServiceException(path, 500, "获取到的会话信息为空");
        }
        return data.get(0);
    }

    /**
     * 使用默认参数启动应用
     *
     * @param username 用户名
     * @param appId    应用id
     * @return JHClient协议链接
     */
    public AppStartedInfo desktopStart(String username, String appId) {
        return this.desktopStart(username, appId, new AppStartRequest());
    }

    /**
     * 查询当前用户的会话列表（管理员则查看所有用户的会话）
     * <p>
     * 注：开启密集后，仅能查看自己的会话和比自己密级低的会话
     * </p>
     *
     * @return 会话列表
     */
    public List<SessionInfo> getDesktopList(String username) {
        return get(AppPathConstant.APPS_SESSIONS_ALL_PATH, username, new TypeReference<ResponseResult<List<SessionInfo>>>() {
        });
    }


    /**
     * 使用参数查询会话列表
     *
     * <p>
     * 注：sessionIds 和 sessionName 不能同时为空
     * </p>
     *
     * @param username    用户名
     * @param sessionIds  会话id列表 （非必填）
     * @param sessionName 会话名称 （非必填）
     * @return 会话列表
     */
    public List<SessionInfo> getDesktopsByParams(String username, List<String> sessionIds, String sessionName) {
        Map<String, Object> params = new HashMap<>(2);
        if (CollectionUtil.isNotEmpty(sessionIds)) {
            params.put("sessionIds", String.join(",", sessionIds));
        }
        if (StringUtils.isNotBlank(sessionName)) {
            params.put("sessionName", sessionName);
        }
        String path = JHApiClient.getUrl(AppPathConstant.APPS_SESSIONS_PATH, params);
        return get(path, username, new TypeReference<ResponseResult<List<SessionInfo>>>() {
        });
    }

}
