package com.jhinno.sdk.openapi.test.job;

import com.jhinno.sdk.openapi.api.job.*;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作业相关测试类
 *
 * @author yanlongqi
 * @date 2024/2/5 18:56
 */
public class JobApiTest {

    private static final JHJobApiExecution execution = JHClientConfig.API_EXECUTRON_MANAGE
            .getApiExecution(JHJobApiExecution.class);

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

    /**
     * 测试根据作业id查询作业文件列表
     */
    @Test
    public void testGetJobFilesById() {
        System.out.println(execution.getJobFilesById("jhadmin", "42"));
    }

    /**
     * 测试分页查询作业列表
     */
    @Test
    public void testGetJobPage() {
        PageJobInfo pages = execution.getJobPage("jhadmin", 1, 5, null, JobStatusEnum.DONE, null);
        System.out.println(pages);
    }

    /**
     * 测试查询历史作业列表
     */
    @Test
    public void testGetHistoryJobs() {
        PageJobInfo pages = execution.getHistoryJobs("jhadmin", 1, 5, null, JobStatusEnum.DONE, null);
        System.out.println(pages);
    }

    /**
     * 测试查看作业详情
     */
    @Test
    public void tesGetJobDetail() {
        System.out.println(execution.getJobDetail("jhadmin", "42"));
    }

    /**
     * 测试使用作业名称检索
     */
    @Test
    public void testGetJobsByName() {
        System.out.println(execution.getJobsByName("jhadmin", "common", 1, 10));
    }

    /**
     * 测根据作业状态检索作业（我的理解，作业列表为空应该返回一个空的列表不能报错）
     */
    @Test
    public void testGetJobsByStatus() {
        System.out.println(execution.getJobsByStatus("jhadmin", JobStatusEnum.DONE, 1, 10));
    }

    /**
     * 测试使用作业拆id列表查询作业
     */
    @Test
    public void testGetJobsByIds() {
        System.out.println(execution.getJobsByIds("jhadmin", Arrays.asList("42", "41")));
    }

    /**
     * 测试查询作业历史
     */
    @Test
    public void testGetJobHistory() {
        JobHistoryInfo result = execution.getJobHistory("jhadmin", "42");
        System.out.println(result);
    }

    /**
     * 测试通过多个子哦也好查询作业历史
     */
    @Test
    public void testGetJobsHistory() {
        List<JobHistoryInfo> result = execution.getJobsHistory("jhadmin", Arrays.asList("41", "42"));
        System.out.println(result);
    }

    /**
     * 测试获取作业动态输出（作业动态输出的数据写在了message，我做了处理，需要产品改进）
     */
    @Test
    public void testGetJobPeek() {
        String peek = execution.getJobPeek("jhadmin", "45");
        System.out.println("peek = " + peek);
    }

    /**
     * 测试会话连接（目前没有测试条件）
     */
    @Test
    public void testConnectJobSession() {
        Object o = execution.connectJobSession("jhadmin", "45");
        System.out.println(o);
    }

    /**
     * 测试获取作业表单
     */
    @Test
    public void testGetSimulationAppForm() {
        List<JobAppFormItemInfo> simulationAppForm = execution.getSimulationAppForm("jhadmin", "common_sub");
        System.out.println(simulationAppForm);
    }

}
