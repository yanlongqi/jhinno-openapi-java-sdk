package com.jhinno.sdk.openapi.example.api.extend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.JHApiExecution;
import com.jhinno.sdk.openapi.api.JHRequestExecution;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JHFileApiExtendExecution implements JHApiExecution {

    private JHRequestExecution execution;

    @Override
    public void init(JHRequestExecution execution) {
        this.execution = execution;
    }


    /**
     * 删除作业
     *
     * @param username 用户名
     * @param jobId    作业ID
     */
    public void deleteJob(String username, String jobId) {
        execution.delete("/appform/ws/api/jobs/" + jobId, username);
    }

    /**
     * 获取集群的应用的CPU核数和排队作业数
     *
     * @param username    用户名
     * @param jobQueue    队列，没有要求就填写normal
     * @param jobPlatform 作业查询条件，如：type==LINUX64
     * @param appName     应用ID，如：common_sub
     * @return
     */
    public JobTooltipDTO getJobTooltipInfo(String username, String jobQueue, String jobPlatform, String appName) {
        Map<String, Object> params = new HashMap<>();
        params.put("jobQueue", jobQueue);
        params.put("jobPlatform", jobPlatform);
        params.put("isTestApp", false);
        params.put("appName", appName);
        String path = JHApiClient.getUrl("/appform/ws/api/jobs/tooltip", params);
        return execution.get(path, username, new TypeReference<ResponseResult<JobTooltipDTO>>() {
        });
    }

}
