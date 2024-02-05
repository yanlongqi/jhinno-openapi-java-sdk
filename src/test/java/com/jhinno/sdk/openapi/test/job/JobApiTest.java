package com.jhinno.sdk.openapi.test.job;

import com.jhinno.sdk.openapi.api.job.JHJobApiExecution;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanlongqi
 * @date 2024/2/5 18:56
 */
public class JobApiTest {

    private static final JHJobApiExecution execution = new JHJobApiExecution(JHClientConfig.client);

    /**
     * 测试提交作业
     */
    @Test
    public void testSubmitJob() {
        Map<String, Object> params = new HashMap<>();
        params.put("JH_CAS", "$HOME/aaa.sh");
        params.put("JH_NCPU", "1");
        System.out.println(execution.submit("jhadmin", "common_sub", params));
    }
}
