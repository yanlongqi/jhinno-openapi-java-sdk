package com.jhinno.sdk.openapi.api.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.ArgsException;
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
 * @date 2024/2/5 18:44
 */
public class JHJobApiExecution extends JHApiExecution {

    /**
     * 获取一个执行器的实例
     *
     * @param jhApiClient 请求的客户端
     */
    public JHJobApiExecution(JHApiClient jhApiClient) {
        super(jhApiClient);
    }

    /**
     * 提交作业
     *
     * @param username 用户名
     * @param appId    应用id
     * @param params   作业提交参数
     * @return 作业id
     */
    public String submit(String username, String appId, Map<String, Object> params) {
        if (StringUtils.isBlank(appId)) {
            throw new ArgsException("appId不能为空");
        }
        if (CollectionUtil.isEmpty(params)) {
            throw new ArgsException("params不能为空");
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("appId", appId);
        map.put("params", JSONUtil.toJsonStr(params));
        String path = JHApiClient.getUrl(JobPathConstant.JOB_SUBMIT_PATH, map);
        List<Map<String, String>> result = post(path, username, null, new TypeReference<ResponseResult<List<Map<String, String>>>>() {
        });
        if (CollectionUtil.isEmpty(result)) {
            throw new ServiceException(path, 500, "作业提交返回为空！");
        }
        return result.get(0).get("jobid");
    }


}
