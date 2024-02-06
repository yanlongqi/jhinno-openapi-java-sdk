package com.jhinno.sdk.openapi.api.organization;

import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;

/**
 * 部门相关接口执行器
 *
 * @author yanlongqi
 * @date 2024/2/6 17:37
 */
public class JHDepartmentApiExecution extends JHApiExecution {

    /**
     * 获取一个执行器的实例
     *
     * @param jhApiClient 请求的客户端
     */
    public JHDepartmentApiExecution(JHApiClient jhApiClient) {
        super(jhApiClient);
    }
}
