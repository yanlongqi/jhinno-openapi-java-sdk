package com.jhinno.sdk.openapi.api.data;

/**
 * 作业数据请求路径
 *
 * @author yanlongqi
 * @date 2024/2/4 17:02
 */
public class DataPathConstant {

    /**
     * 根据用户scope查询数据目录列表
     */
    public static final String DATA_SPOOLERS_PATH = "/appform/ws/api/spoolers";

    /**
     * 根据作业id查数据目录信息
     */
    public static final String DATA_SPOOLER_JOB_ID_PATH = "/appform/ws/api/spooler/{jobId}";

    /**
     * 根据作业id集合查询数据目录列表
     */
    public static final String DATA_SPOOLERS_LIST_PATH = "/appform/ws/api/spoolers/list";

    /**
     * 根据数据目录名称查询数据目录列表
     */
    public static final String DATA_SPOOLERS_NAME_PATH = "/appform/ws/api/spoolers/name";


    /**
     * 立即删除数据目录
     */
    public static final String DATA_SPOOLERS_DELETE_ID_PATH = "/appform/ws/api/spooler/del/{id}";


    /**
     * 设置过期时间删除数据目录
     */
    public static final String DATA_SPOOLERS_PURGE_PATH = "/appform/ws/api/spooler/purge";
}
