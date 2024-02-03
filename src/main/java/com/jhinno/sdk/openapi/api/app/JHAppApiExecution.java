package com.jhinno.sdk.openapi.api.app;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.CommonConstant;
import com.jhinno.sdk.openapi.ServiceException;
import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.apache.commons.lang3.StringUtils;

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
        ResponseResult<List<AppStartedInfo>> result = jhApiClient.post(path, appStartRequest, getHeaders(username), new TypeReference<ResponseResult<List<AppStartedInfo>>>() {
        });
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(path, result.getCode(), result.getMessage());
        }
        List<AppStartedInfo> data = result.getData();
        if (CollectionUtil.isEmpty(data)) {
            throw new ServiceException(path, result.getCode(), "获取到的会话信息为空");
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
    public List<Map<String, Object>> getDesktopList(String username) {
        ResponseResult<List<Map<String, Object>>> result = jhApiClient.get(
                AppPathConstant.APPS_SESSIONS_ALL_PATH,
                getHeaders(username),
                new TypeReference<ResponseResult<List<Map<String, Object>>>>() {
                });
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(AppPathConstant.APPS_SESSIONS_ALL_PATH, result.getCode(), result.getMessage());
        }
        return result.getData();

    }


}
