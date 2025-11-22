package com.jhinno.sdk.openapi.api.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.*;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.api.file.FileInfo;
import com.jhinno.sdk.openapi.client.JHApiClient;
import com.jhinno.sdk.openapi.utils.JsonUtil;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import com.jhinno.sdk.openapi.utils.CollectionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanlongqi
 * @date 2024/2/5 18:44
 */
@NoArgsConstructor
public class JHJobApiExecution extends JHApiExecutionAbstract {

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
        map.put("params", JsonUtil.objectToString(params));
        String path = JHApiClient.getUrl(JobPathConstant.JOB_SUBMIT_PATH, map);
        List<Map<String, String>> result = execution.post(path, username,
                new TypeReference<ResponseResult<List<Map<String, String>>>>() {
                });
        if (CollectionUtil.isEmpty(result)) {
            throw new ServiceException(path, 500, "作业提交返回为空！");
        }
        return result.get(0).get("jobid");
    }

    /**
     * 使用作业id查询作业文件列表
     *
     * @param username 用户名
     * @param jobId    作业id
     * @return 作业文件列表
     */
    public List<FileInfo> getJobFilesById(String username, String jobId) {
        if (StringUtils.isBlank(jobId)) {
            throw new ArgsException("jobId不能为空！");
        }
        String path = JobPathConstant.JOB_FIND_JOB_FILES_PATH.replace("{jobId}", jobId);
        return execution.get(path, username, new TypeReference<ResponseResult<List<FileInfo>>>() {
        });
    }

    /**
     * 分页查询作业列表
     * <p>
     * 注：name、status、condition均为删选条件，condition为 高级筛选
     * </p>
     *
     * @param username  用户名
     * @param page      页码（非必填，默认：1）
     * @param pageSize  每页大小（非必填，默认：20）
     * @param name      作业名（非必填）
     * @param status    作业状态（非必填）
     * @param condition 自定义条件（非必填）
     * @return 作业列表
     * @see JobStatusEnum
     */
    public PageJobInfo getJobPage(String username, Integer page, Integer pageSize, String name, JobStatusEnum status,
                                  Map<String, Object> condition) {
        Map<String, Object> params = new HashMap<>(5);
        if (page != null) {
            params.put("page", page);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        if (StringUtils.isNotBlank(name)) {
            params.put("jobName", name);
        }
        if (status != null) {
            params.put("status", status.getStatus());
        }
        if (CollectionUtil.isNotEmpty(condition)) {
            params.put("condition", JsonUtil.objectToString(params));
        }
        String path = JHApiClient.getUrl(JobPathConstant.JOB_PAGE_PATH, params);
        return execution.get(path, username, new TypeReference<ResponseResult<PageJobInfo>>() {
        });
    }

    /**
     * 分页查询作业列表
     * <p>
     * 注：name、status、condition均为删选条件，condition为 高级筛选
     * </p>
     *
     * @param username  用户名
     * @param page      页码（非必填，默认：1）
     * @param pageSize  每页大小（非必填，默认：20）
     * @param name      作业名（非必填）
     * @param status    作业状态（非必填），取值见{@link JobStatusEnum#getJobStatus(String)}
     * @param condition 自定义条件（非必填）
     * @return 作业列表
     * @see JobStatusEnum
     */
    public PageJobInfo getJobPage(String username, Integer page, Integer pageSize, String name, String status,
                                  Map<String, Object> condition) {
        return getJobPage(username, page, pageSize, name, JobStatusEnum.getJobStatus(status), condition);
    }

    /**
     * 分页查询作业列表
     * <p>
     * 注：name、status、condition均为删选条件，condition为 高级筛选
     * </p>
     *
     * @param username  用户名
     * @param page      页码（非必填，默认：1）
     * @param pageSize  每页大小（非必填，默认：20）
     * @param name      作业名（非必填）
     * @param status    作业名（非必填）
     * @param condition 作业状态（非必填），取值见{@link JobStatusEnum#getJobStatus(String)}
     * @return 作业列表
     * @see JobStatusEnum
     */
    public PageJobInfo getHistoryJobs(String username, Integer page, Integer pageSize, String name,
                                      JobStatusEnum status, Map<String, Object> condition) {

        Map<String, Object> params = new HashMap<>(5);
        if (page != null) {
            params.put("page", page);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        if (StringUtils.isNotBlank(name)) {
            params.put("jobName", name);
        }
        if (status != null) {
            params.put("status", status.getStatus());
        }
        if (CollectionUtil.isNotEmpty(condition)) {
            params.put("condition", JsonUtil.objectToString(params));
        }

        String path = JHApiClient.getUrl(JobPathConstant.JOB_HISTORY_JOBS_PATH, params);
        return execution.get(path, username, new TypeReference<ResponseResult<PageJobInfo>>() {
        });
    }

    /**
     * 分页查询作业列表
     * <p>
     * 注：name、status、condition均为删选条件，condition为 高级筛选
     * </p>
     *
     * @param username  用户名
     * @param page      页码（非必填，默认：1）
     * @param pageSize  每页大小（非必填，默认：20）
     * @param name      作业名（非必填）
     * @param status    作业名（非必填）
     * @param condition 作业状态（非必填），取值见{@link JobStatusEnum#getJobStatus(String)}
     * @return 作业列表
     * @see JobStatusEnum
     */
    public PageJobInfo getHistoryJobs(String username, Integer page, Integer pageSize, String name, String status,
                                      Map<String, Object> condition) {
        return getHistoryJobs(username, page, pageSize, name, JobStatusEnum.getJobStatus(status), condition);
    }

    /**
     * 查询作业详情
     *
     * @param username 用户名
     * @param jobId    作业id
     * @return 作业详情
     */
    public JobInfo getJobDetail(String username, String jobId) {
        if (StringUtils.isBlank(jobId)) {
            throw new ArgsException("jobId不能为空！");
        }
        String path = JobPathConstant.JOB_DETAIL_INFO_PATH.replace("{jobId}", jobId);
        return execution.get(path, username, new TypeReference<ResponseResult<JobInfo>>() {
        });
    }

    /**
     * 分页检索作业名称查询作业信息
     *
     * @param username 用户名
     * @param name     作业名 （必填）
     * @param page     页码 （非必填，默认：1）
     * @param pageSize 页大小（非必填，默认：20）
     * @return 作业列表
     */
    public List<JobInfo> getJobsByName(String username, String name, Integer page, Integer pageSize) {
        if (StringUtils.isBlank(name)) {
            throw new ArgsException("name不能为空！");
        }
        Map<String, Object> params = new HashMap<>(3);
        params.put("name", name);
        if (page != null) {
            params.put("page", page);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }

        String path = JHApiClient.getUrl(JobPathConstant.JOB_LIST_BY_NAME_PATH, params);
        return execution.get(path, username, new TypeReference<ResponseResult<List<JobInfo>>>() {
        });
    }

    /**
     * 分页检索作业状态
     * <p>
     * 可通过{@link JobStatusEnum#getJobStatus(String)} 获得{@link JobStatusEnum}
     *
     * @param username 用户名
     * @param status   作业状态
     * @param page     页码
     * @param pageSize 分页大小
     * @return 作业列表
     */
    public List<JobInfo> getJobsByStatus(String username, JobStatusEnum status, Integer page, Integer pageSize) {
        if (status == null) {
            throw new ArgsException("status不能为空！");
        }
        Map<String, Object> params = new HashMap<>(3);
        if (page != null) {
            params.put("page", page);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        String path = JHApiClient
                .getUrl(JobPathConstant.JOB_LIST_BY_STATUS_PATH.replace("{status}", status.getStatus()), params);
        return execution.get(path, username, new TypeReference<ResponseResult<List<JobInfo>>>() {
        });
    }

    /**
     * 作业的状态（必填），
     *
     * @param username 用户名
     * @param status   作业状态，取值见{@link JobStatusEnum#getStatus()}
     * @param page     页码
     * @param pageSize 分页大小
     * @return 作业列表
     * @see JobStatusEnum
     */
    public List<JobInfo> getJobsByStatus(String username, String status, Integer page, Integer pageSize) {
        return getJobsByStatus(username, JobStatusEnum.getJobStatus(status), page, pageSize);
    }

    /**
     * 通过作业id列表查询作业列表
     *
     * @param username 用户列表
     * @param jobIds   作业id列表
     * @return 作业列表
     */
    public List<JobInfo> getJobsByIds(String username, List<String> jobIds) {
        if (CollectionUtil.isEmpty(jobIds)) {
            throw new ArgsException("jobIds不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("jobIds", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, jobIds));
        String path = JHApiClient.getUrl(JobPathConstant.JOB_LIST_BY_IDS_PATH, params);
        return execution.get(path, username, new TypeReference<ResponseResult<List<JobInfo>>>() {
        });
    }

    /**
     * 操作作业
     * <p>
     * {@link JobActionEnum} 中的
     * 静态方法{@link JobActionEnum#getJobAction(String)}可以将前端的字符转换为{@link JobActionEnum}对象
     * <p>
     * 或者使用{@link #action(String, String, String)}直接操作，但最终还是调的这个方法，我们只不过是内部做了转换而已
     *
     * @param username 用户名
     * @param action   作业操作类型（必填）
     * @param jobId    作业id
     * @see JobActionEnum
     */
    public void action(String username, JobActionEnum action, String jobId) {
        if (action == null) {
            throw new ArgsException("action不能为空！");
        }
        if (StringUtils.isBlank(jobId)) {
            throw new ArgsException("jobId不能为空！");
        }
        String path = JobPathConstant.JOB_ACTION_PATH.replace("{jobId}", jobId).replace("{action}", action.getAction());
        execution.put(path, username);
    }

    /**
     * 操作作业
     *
     * @param username 用户名
     * @param action   作业的操作类型（必填），取值见{@link JobActionEnum}中的中值
     * @param jobId    作业id
     */
    public void action(String username, String action, String jobId) {
        action(username, JobActionEnum.getJobAction(action), jobId);
    }

    /**
     * 批量操作作业
     * <p>
     * {@link JobsActionEnum} 中的
     * 静态方法{@link JobsActionEnum#getJobAction(String)}可以将前端的字符转换为{@link JobsActionEnum}对象
     * <p>
     * 或者使用{@link #actions(String, String, List)}直接操作，但最终还是调的这个方法，我们只不过是内部做了转换而已
     *
     * @param username 用户名
     * @param action   操作类型
     * @param jobIds   作业id列表
     */
    public void actions(String username, JobsActionEnum action, List<String> jobIds) {
        if (action == null) {
            throw new ArgsException("action不能为空！");
        }
        if (CollectionUtil.isEmpty(jobIds)) {
            throw new ArgsException("jobIds不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("jobIds", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, jobIds));
        String path = JHApiClient.getUrl(JobPathConstant.JOB_ACTION_IDS_PATH.replace("{action}", action.getAction()),
                params);
        execution.put(path, username);
    }

    /**
     * 操作作业
     *
     * @param username 用户名
     * @param action   作业的操作类型（必填），取值见{@link JobsActionEnum}中的中值
     * @param jobIds   作业id列表
     */
    public void actions(String username, String action, List<String> jobIds) {
        actions(username, JobsActionEnum.getJobAction(action), jobIds);
    }

    /**
     * 查询制定作业的作业历史
     *
     * @param username 用户名
     * @param jobId    作业id
     * @return 作业历史
     */
    public JobHistoryInfo getJobHistory(String username, String jobId) {
        if (StringUtils.isBlank(jobId)) {
            throw new ArgsException("jobId不能为空！");
        }
        String path = JobPathConstant.JOB_HISTORY_PATH.replace("{jobId}", jobId);
        List<JobHistoryInfo> list = execution.get(path, username,
                new TypeReference<ResponseResult<List<JobHistoryInfo>>>() {
                });
        if (CollectionUtil.isEmpty(list)) {
            throw new ServiceException(path, 500, "历史作业信息不存在！");
        }
        return list.get(0);
    }

    /**
     * 通过多个id查询作业历史列表
     *
     * @param username 用户名
     * @param jobIds   作业id列表
     * @return 作业历史列表
     */
    public List<JobHistoryInfo> getJobsHistory(String username, List<String> jobIds) {
        if (CollectionUtil.isEmpty(jobIds)) {
            throw new ArgsException("jobIds不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("jobIds", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, jobIds));
        String path = JHApiClient.getUrl(JobPathConstant.JOB_HISTORY_IDS_PATH, params);
        return execution.get(path, username, new TypeReference<ResponseResult<List<JobHistoryInfo>>>() {
        });
    }

    /**
     * 获取作业的动态输出
     *
     * @param username 用户名
     * @param jobId    作业id
     * @return 作业的动态输出
     */
    public String getJobPeek(String username, String jobId) {
        if (StringUtils.isBlank(jobId)) {
            throw new ArgsException("jobId不能为空！");
        }
        String path = JobPathConstant.JOB_PEEK_PATH.replace("{jobId}", jobId);
        ResponseResult<String> result = execution.getJhApiClient().get(path, execution.getHeaders(username),
                new TypeReference<ResponseResult<String>>() {
                });
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(path, result.getCode(), result.getMessage());
        }
        // 产品BUG，这里作兼容性处理，保证SDK在BUG修复后能用
        if (StringUtils.isNotBlank(result.getData())) {
            return result.getData();
        }
        return result.getMessage();
    }

    /**
     * 连接作业会话
     *
     * @param username 用户名
     * @param jobId    作业id
     * @return 未知
     */
    public Object connectJobSession(String username, String jobId) {
        if (StringUtils.isBlank(jobId)) {
            throw new ArgsException("jobId不能为空！");
        }
        String path = JobPathConstant.JOB_CONNECT_SESSION_PATH.replace("{jobId}", jobId);
        return execution.post(path, username, new TypeReference<ResponseResult<Object>>() {
        });
    }

    /**
     * 查询用户作业表单信息
     *
     * @param username 用户名
     * @param appId    用户ID
     */
    public List<JobAppFormItemInfo> getSimulationAppForm(String username, String appId) {
        if (StringUtils.isBlank(appId)) {
            throw new ArgsException("appId不能为空！");
        }
        String path = JobPathConstant.JOB_GET_APP_FORM_PATH.replace("{appId}", appId);
        return execution.get(path, username, new TypeReference<ResponseResult<List<JobAppFormItemInfo>>>() {
        });
    }

}
