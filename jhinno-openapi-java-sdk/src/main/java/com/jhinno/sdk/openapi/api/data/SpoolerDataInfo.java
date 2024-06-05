package com.jhinno.sdk.openapi.api.data;

import lombok.Data;

import java.util.Date;

/**
 * 作业数据信息
 *
 * @author yanlongqi
 * @date 2024/2/4 17:20
 */
@Data
public class SpoolerDataInfo {

    /**
     * 作业名称
     */
    private String jobName;

    /**
     * 作业用户名
     */
    private String jobUser;

    /**
     * 提交目录
     */
    private String subCwd;

    /**
     * 数据名称
     */
    private String dataName;

    /**
     * 作业项目
     */
    private String jobProject;

    /**
     * 作业路径
     */
    private String dataPath;

    /**
     * 作业id
     */
    private String jobId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 是否自定义作业
     */
    private Boolean is_custom_spooler;

    /**
     * 作业系统类型
     */
    private String job_os_type;

    /**
     * 密级中文名
     */
    private String confidentialCn;

    /**
     * 密级英文名
     */
    private String confidentialEn;

    /**
     * 密级
     */
    private String confidential;
}