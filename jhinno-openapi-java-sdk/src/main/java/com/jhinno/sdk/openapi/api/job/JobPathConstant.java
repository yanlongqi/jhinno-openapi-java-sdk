package com.jhinno.sdk.openapi.api.job;

/**
 * 作业相关接口路径
 *
 * @author yanlongqi
 * @date 2024/2/5 18:44
 */
public class JobPathConstant {

    /**
     * 提交作业
     */
    public static final String JOB_SUBMIT_PATH = "/appform/ws/api/jobs/jsub";

    /**
     * 使用作业id查询作业列表
     */
    public static final String JOB_FIND_JOB_FILES_PATH = "/appform/ws/api/jobs/{jobId}/files";

    /**
     * 分页查询作业列表
     */
    public static final String JOB_PAGE_PATH = "/appform/ws/api/jobs/page";

    /**
     * 查询历史作业列表
     */
    public static final String JOB_HISTORY_JOBS_PATH = "/appform/ws/api/jobs/historyPage";

    /**
     * 查询作业详情
     */
    public static final String JOB_DETAIL_INFO_PATH = "/appform/ws/api/jobs/{jobId}";

    /**
     * 分页检索作业名
     */
    public static final String JOB_LIST_BY_NAME_PATH = "/appform/ws/api/jobs/byName";

    /**
     * 作业号检索作业列表
     */
    public static final String JOB_LIST_BY_STATUS_PATH = "/appform/ws/api/jobs/byStatus/{status}";

    /**
     * 通过作业号查询作业列表
     */
    public static final String JOB_LIST_BY_IDS_PATH = "/appform/ws/api/jobs/list";

    /**
     * 作业操作
     */
    public static final String JOB_ACTION_PATH = "/appform/ws/api/jobs/{jobId}/{action}";

    /**
     * 批量操作作业
     */
    public static final String JOB_ACTION_IDS_PATH = "/appform/ws/api/jobs/{action}";

    /**
     * 获取作业历史
     */
    public static final String JOB_HISTORY_PATH = "/appform/ws/api/jobs/{jobId}/hist";

    /**
     * 通过多个作业号查询作业历史
     */
    public static final String JOB_HISTORY_IDS_PATH = "/appform/ws/api/jobs/hist";

    /**
     * 获取作业动态输出
     */
    public static final String JOB_PEEK_PATH = "/appform/ws/api/jobs/{jobId}/peek";

    /**
     * 连接作业会话
     */
    public static final String JOB_CONNECT_SESSION_PATH = "/appform/ws/api/jobs/{jobId}/connect";

    /**
     * 获取作业表单
     * <p/>
     * 该接口目前在Solutions下面维护，未经过产品的测试
     */
    public static final String JOB_GET_APP_FORM_PATH = "/appform/ws/api/jobs/{appId}/form_params";
}
