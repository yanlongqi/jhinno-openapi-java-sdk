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

    /**
     * 使用参数查询会话列表
     */
    public static final String APPS_SESSIONS_PATH = "/ws/api/apps/sessions";


    /**
     * 根据会话id列表查询会话列表
     */
    public static final String APPS_SESSIONS_IDS_PATH = "/ws/api/apps/listBySessionIds";


    /**
     * 根据会话名称查询会话列表
     */
    public static final String APPS_SESSIONS_NAME_PATH = "/ws/api/apps/listBySessionName";


    /**
     * 会话共享
     */
    public static final String APPS_SESSIONS_SHARE_PATH = "/ws/api/apps/sessions/{sessionId}/share";


    /**
     * 取消会话共享
     */
    public static final String APPS_SESSIONS_CANCEL_SHARE_PATH = "/ws/api/apps/sessions/{sessionId}/share_cancel";

    /**
     * 传递会话的操作权
     */
    public static final String APPS_SESSIONS_OPERATION_TRANSFER_PATH = "/ws/api/apps/sessions/{sessionId}/operation_transfer";


    /**
     * 连接会话
     */
    public static final String APPS_SESSIONS_CONNECT_JHAPP_PATH = "/ws/api/apps/sessions/{sessionId}/connect";

    /**
     * 断开会话连接（作业/应用）
     */
    public static final String APPS_SESSIONS_DISCONNECT_PATH = "/ws/api/apps/sessions/{sessionId}/disconnect";


    /**
     * 批量断开会话
     */
    public static final String APPS_SESSIONS_DISCONNECT_IDS_PATH = "/ws/api/apps/sessions/disconnect";


    /**
     * 注销会话
     */
    public static final String APPS_SESSIONS_DESTROY_PATH = "/ws/api/apps/sessions/{sessionId}/close";

    /**
     * 批量注销会话
     */
    public static final String APPS_SESSIONS_DESTROY_IDS_PATH = "/ws/api/apps/sessions/close";


    /**
     * 查询应用列表
     */
    public static final String APPS_LIST_PATH = "/ws/api/apps";


    /**
     * 获取应用链接URL
     */
    public static final String APPS_GET_URL_PATH = "/ws/api/apps/{appName}/url";
}
