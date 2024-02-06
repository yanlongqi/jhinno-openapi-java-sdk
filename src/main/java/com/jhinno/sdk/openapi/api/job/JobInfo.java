package com.jhinno.sdk.openapi.api.job;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 作业详情
 *
 * @author yanlongqi
 * @date 2024/2/6 13:54
 */
@Data
public class JobInfo {
    /**
     * 作业id
     */
    private String id;

    /**
     * 作业索引（类型待确认）
     */
    private String index;

    /**
     * 作业id
     */
    private String jobId;

    /**
     * 作业名称
     */
    private String name;
    /**
     * 作业状态
     * <p>
     * 可能的取值：<br/>
     * <ol>
     *      <li>RUN（运行）</li>
     *      <li>PEND（等待）</li>
     *      <li>PSUSP（等待中挂起）</li>
     *      <li>USUSP（用户挂起）</li>
     *      <li>SSUSP（系统挂起）</li>
     *      <li>ZOMBI（僵尸）</li>
     *      <li>DONE（完成）</li>
     *      <li>EXIT（退出）</li>
     *      <li>UNKNOWN#UNKWN（状态不明）</li>
     * </ol>
     *
     * </p>
     * <p>
     * 数组作业（及arrayJob="true"）时status的类型是一个map<br/>
     * 如：{"done":1,"run":12}
     * </p>
     */
    private Object status;

    /**
     * 主机
     */
    private String host;

    /**
     * 队列
     */
    private String queue;

    /**
     * 排队号
     */
    private Integer queueNumber;

    /**
     * 属主
     */
    private String owner;

    /**
     * 执行节列表
     */
    private List<String> executionHost;

    /**
     * 插槽
     */
    private String slots;

    /**
     * 作业提交时间
     */
    private Date submitTime;

    /**
     * 作业执行时间
     */
    private Date executionTime;

    /**
     * 作业终止时间
     */
    private Date terminationTime;

    /**
     * 工作目录
     */
    private String cwd;

    /**
     * 作业提交工作目录
     */
    private String subCwd;

    /**
     * 密级
     */
    private String confidential;

    /**
     * 密级映射
     */
    private Integer confidential_map;

    /**
     * 作业数据删除时间
     */
    private Date deleteTime;

    /**
     * 项目
     */
    private String project;

    /**
     * 索引id
     */
    private String indexid;

    /**
     * 是否数组作业，true是，false不是
     */
    private String arrayJob;

    /**
     * 会话id
     */
    private String desktopid;


    private List<String> reasons;

    /**
     * 运行时间
     */
    private String runTime;

    /**
     * 作业类型
     */
    private Boolean jobType;

    /**
     *
     */
    private Boolean jobDesktopExpires;

    /**
     *
     */
    private Boolean jobDataNotExists;

    /**
     *
     */
    private Boolean hasAlarmHost;

    /**
     *
     */
    private String alarmInfo;

    /**
     *
     */
    private Boolean hasJobException;

    /**
     *
     */
    private String exceptionInfo;

    /**
     * 应用名称
     */
    private String appName;

    /**
     *
     */
    private Integer underRunThreshold;

    /**
     *
     */
    private Integer overRunThreshold;

    /**
     *
     */
    private Float idleThreshold;

    /**
     * 用户中文名
     */
    private String userNameCn;

    /**
     *
     */
    private String jobIndexIds;

    /**
     * 作业数据是否删除
     */
    private Boolean jobIsDeleted;

    /**
     * 是否独占
     */
    private Boolean exclusive;

    /**
     * 是否docker作业
     */
    private Boolean isDocker;

    /**
     * 图形脚本名称
     */
    private String graphicScriptName;

    /**
     * 绑定CPU列表
     */
    private List<Map<String, Object>> cpuBinds;

    /**
     * 绑定GPU列表
     */
    private List<Map<String, Object>> gpuBinds;

    /**
     *
     */
    private List<StageInfo> transferProgressIn;

    /**
     *
     */
    private List<StageInfo> transferProgressOut;

    /**
     * 组织树
     */
    private String organizationTree;

    /**
     * 是否本地作业
     */
    private Boolean isLocalSpoolerJob;

}
