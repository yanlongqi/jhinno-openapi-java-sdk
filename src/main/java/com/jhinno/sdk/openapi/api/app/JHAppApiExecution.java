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

}
