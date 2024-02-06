package com.jhinno.sdk.openapi.api.app;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.CommonConstant;
import com.jhinno.sdk.openapi.ServiceException;
import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会话启动相关接口执行器
 *
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
     * <ul>
     *     <li>sessionIds 和 sessionName 不能同时为空</li>
     *     <li>开启密级后，仅能查看比自己密级低以及和自己密级一致的会话</li>
     * </ul>
     *
     * @param username    用户名
     * @param sessionIds  会话id列表 （非必填）
     * @param sessionName 会话名称 （非必填）
     * @return 会话列表
     */
    public List<SessionInfo> getDesktopsByParams(String username, List<String> sessionIds, String sessionName) {
        Map<String, Object> params = new HashMap<>(2);
        if (CollectionUtil.isNotEmpty(sessionIds)) {
            params.put("sessionIds", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, sessionIds));
        }
        if (StringUtils.isNotBlank(sessionName)) {
            params.put("sessionName", sessionName);
        }
        String path = JHApiClient.getUrl(AppPathConstant.APPS_SESSIONS_PATH, params);
        return get(path, username, new TypeReference<ResponseResult<List<SessionInfo>>>() {
        });
    }


    /**
     * 根据会话列表查询会话列表
     * <p>
     * 注：开启密集后，仅能查看自己的会话和比自己密级低的会话
     * </p>
     *
     * @param username 用户名
     * @param ids      会话id列表
     * @return 会话列表
     */
    public List<SessionInfo> getDesktopsById(String username, List<String> ids) {
        Map<String, Object> params = new HashMap<>(2);
        if (CollectionUtil.isEmpty(ids)) {
            throw new ArgsException("会话id列表不能为空！");
        }
        params.put("sessionIds", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, ids));
        String path = JHApiClient.getUrl(AppPathConstant.APPS_SESSIONS_IDS_PATH, params);
        return get(path, username, new TypeReference<ResponseResult<List<SessionInfo>>>() {
        });
    }

    /**
     * 根据会话名称查询会话
     * <p>
     * 注：开启密集后，仅能查看自己的会话和比自己密级低的会话
     * </p>
     *
     * @param username    用户名
     * @param sessionName 会话名称
     * @return 会话列表
     */

    public List<SessionInfo> getDesktopsByName(String username, String sessionName) {
        Map<String, Object> params = new HashMap<>(1);
        if (StringUtils.isEmpty(sessionName)) {
            throw new ArgsException("会话id列表不能为空！");
        }
        params.put("sessionName", sessionName);
        String path = JHApiClient.getUrl(AppPathConstant.APPS_SESSIONS_NAME_PATH, params);
        return get(path, username, new TypeReference<ResponseResult<List<SessionInfo>>>() {
        });
    }

    /**
     * 会话共享
     * <ul>
     *     <li>调用该接口需要打开景行会话共享的功能</li>
     *     <li>observers 和 interacts 不能同时为空</li>
     *     <li>会话共享有密级限制，仅能将会话共享给比会话密级高的用户</li>
     * </ul>
     *
     * @param username   用户名
     * @param sessionId  会话id （必填）
     * @param observers  观察者列表 （非必填）
     * @param interacts  协作者列表 （非必填）
     * @param isTransfer 是否传递操作权（不确定，需要咨询产品，非必填）
     */
    public void shareDesktop(String username, String sessionId, List<String> observers, List<String> interacts, String isTransfer) {
        if (StringUtils.isBlank(sessionId)) {
            throw new ArgsException("sessionId为必填字段");
        }
        Map<String, Object> params = new HashMap<>(4);
        if (CollectionUtil.isNotEmpty(observers)) {
            params.put("observer", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, observers));
        }
        if (CollectionUtil.isNotEmpty(interacts)) {
            params.put("interact", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, interacts));
        }
        if (StringUtils.isBlank(isTransfer)) {
            params.put("isTransfer", isTransfer);
        }
        String path = JHApiClient.getUrl(AppPathConstant.APPS_SESSIONS_SHARE_PATH.replace("{sessionId}", sessionId), params);
        post(path, username);
    }


    /**
     * 取消会话共享
     * <ul>
     *     <li>调用该接口需要打开景行会话共享的功能</li>
     *     <li>开启密级后，仅能操作比自己密级低或者和自己密级一致的会话</li>
     * </ul>
     *
     * @param username  用户名
     * @param sessionId 会话id
     */
    public void cancelShare(String username, String sessionId) {
        if (StringUtils.isBlank(sessionId)) {
            throw new ArgsException("sessionId为必填字段");
        }
        String path = AppPathConstant.APPS_SESSIONS_CANCEL_SHARE_PATH.replace("{sessionId}", sessionId);
        put(path, username);
    }

    /**
     * 传递会话操作权
     * <ul>
     *     <li>调用该接口需要打开景行会话共享的功能</li>
     *     <li>开启密级后，仅能操作比自己密级低或者和自己密级一致的会话</li>
     * </ul>
     *
     * @param username  用户名
     * @param sessionId 会话id（必填）
     * @param interact  操作权（必填）
     */
    public void transferOperatorRight(String username, String sessionId, String interact) {
        if (StringUtils.isBlank(sessionId)) {
            throw new ArgsException("sessionId为必填字段");
        }
        if (StringUtils.isBlank(interact)) {
            throw new ArgsException("interact为必填字段");
        }
        String path = AppPathConstant.APPS_SESSIONS_OPERATION_TRANSFER_PATH.replace("{sessionId}", sessionId);
        Map<String, Object> params = new HashMap<>(1);
        params.put("interact", interact);
        path = JHApiClient.getUrl(path, params);
        put(path, username);
    }


    /**
     * 连接会话
     *
     * @param sessionId 会话拆
     * @return JHClient协议链接信息
     */
    public AppStartedInfo connectJhapp(String username, String sessionId) {
        if (StringUtils.isBlank(sessionId)) {
            throw new ArgsException("sessionId为必填字段");
        }
        String path = AppPathConstant.APPS_SESSIONS_CONNECT_JHAPP_PATH.replace("{sessionId}", sessionId);
        List<AppStartedInfo> list = get(path, username, new TypeReference<ResponseResult<List<AppStartedInfo>>>() {
        });
        if (CollectionUtil.isEmpty(list)) {
            throw new ServiceException(path, 500, "获取到的会话信息为空");
        }
        return list.get(0);
    }


    /**
     * 断开会话连接（作业/应用）
     * <p>
     * 注：开启密级后，仅能操作比自己密级低或者和自己密级一致的会话
     * </p>
     *
     * @param username  用户名
     * @param sessionId 会话ID
     */
    public void disconnectSessionInfo(String username, String sessionId) {
        if (StringUtils.isBlank(sessionId)) {
            throw new ArgsException("sessionId为必填字段");
        }
        String path = AppPathConstant.APPS_SESSIONS_DISCONNECT_PATH.replace("{sessionId}", sessionId);
        put(path, username);
    }


    /**
     * 通过应用id批量断开会话（作业/应用）
     * <p>
     * 注：开启密级后，仅能操作比自己密级低或者和自己密级一致的会话
     * </p>
     *
     * @param username   用户名
     * @param sessionIds 用户表id列表
     */
    public void disconnectSessionByIds(String username, List<String> sessionIds) {
        if (CollectionUtil.isEmpty(sessionIds)) {
            throw new ArgsException("sessionIds不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("sessionIds", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, sessionIds));
        String path = JHApiClient.getUrl(AppPathConstant.APPS_SESSIONS_DISCONNECT_IDS_PATH, params);
        put(path, username);
    }


    /**
     * 注销会话
     * <p>
     * 注：开启密级后，仅能操作比自己密级低或者和自己密级一致的会话
     * </p>
     *
     * @param username  用户名
     * @param sessionId 会话id
     */
    public void destroySession(String username, String sessionId) {
        if (StringUtils.isBlank(sessionId)) {
            throw new ArgsException("sessionId为必填字段");
        }
        String path = AppPathConstant.APPS_SESSIONS_DESTROY_PATH.replace("{sessionId}", sessionId);
        put(path, username);
    }


    /**
     * 批量注销会话
     *
     * @param username   用户名
     * @param sessionIds 会话id列表
     */
    public void destroySessionByIds(String username, List<String> sessionIds) {
        if (CollectionUtil.isEmpty(sessionIds)) {
            throw new ArgsException("sessionIds不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("sessionIds", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, sessionIds));
        String path = JHApiClient.getUrl(AppPathConstant.APPS_SESSIONS_DESTROY_IDS_PATH, params);
        put(path, username);
    }


    /**
     * 获取当前用户的应用列表
     *
     * @param username 用户名
     * @return 应用列表
     */
    public List<AppInfo> getAppList(String username) {
        return get(AppPathConstant.APPS_LIST_PATH, username, new TypeReference<ResponseResult<List<AppInfo>>>() {
        });
    }


    /**
     * 获取应用链接
     *
     * @param username 应户名
     * @param appName  应用名
     * @return 应用链接地址
     */
    public String getAppUrl(String username, String appName) {
        if (StringUtils.isBlank(appName)) {
            throw new ArgsException("appName为必填字段");
        }
        String path = AppPathConstant.APPS_GET_URL_PATH.replace("{appName}", appName);
        List<Map<String, String>> apps = get(path, username, new TypeReference<ResponseResult<List<Map<String, String>>>>() {
        });
        if (CollectionUtil.isEmpty(apps)) {
            throw new ServiceException(path, 500, "应用信息为空！");
        }
        return apps.get(0).get("url");
    }
}
