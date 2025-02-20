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

    public static String GET_FILE_ENV_PATH = "/ws/api/files/path/{env}";

    public FilePath getFileEnvPath(String username, FileEnvType env, FileSystemType type) {
        Map<String, Object> params = new HashMap<>(1);
        if (StringUtils.isNotBlank(type.getType())) {
            params.put("type", type.getType());
        }
        String url = JHApiClient.getUrl(GET_FILE_ENV_PATH.replace("{env}", env.getEnv()), params);
        return execution.get(url, username, new TypeReference<ResponseResult<FilePath>>() {
        });
    }

    public FilePath getFileHomeEnvPath(String username, FileSystemType type) {
        return getFileEnvPath(username, FileEnvType.HOME_ENV, type);
    }

}
