package com.jhinno.sdk.openapi.api.app;

/**
 * 关于会话请求路径的path
 *
 * @author yanlongqi
 * @date 2024/2/1 16:27
 */
public class AppPathConstant {

    /**
     * 申请会话
     */
    public static final String APPS_START_PATH = "/ws/api/apps/{appId}/start";

    /**
     * 查询会列表
     */
    public static final String APPS_SESSIONS_ALL_PATH = "/ws/api/apps/sessions/all";
}
