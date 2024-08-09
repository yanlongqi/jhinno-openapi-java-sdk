package com.jhinno.sdk.openapi.api.app;

import lombok.Data;

/**
 * 应用
 */
@Data
public class AppstoreAppInfo {

    /**
     * 应ID
     */
    private String id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用图标
     */
    private String image;

    /**
     * 应用类型
     */
    private String type;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 应用状态
     */
    private Boolean status;

    /**
     * 是否 使用
     */
    private Boolean isUsed;

    /**
     * CPU使用
     */
    private String cpuUsage;

    /**
     * 空闲内存
     */
    private String idleMem;

    /**
     *
     */
    private String idleTime;

    /**
     * 应用路径
     */
    private String appPath;

    /**
     * 工作路径
     */
    private String appCwd;

    /**
     * 发布时间
     */
    private String appReleaseTime;

    /**
     * 启动选项
     */
    private String appStartOpt;

    /**
     * 启动前缀
     */
    private String appStartPrefix;

    /**
     * 启动后缀
     */
    private String appStartSuffix;

    /**
     * 应用详情
     */
    private String appDetail;

    /**
     * 启动数量
     */
    private String startNum;

    /**
     * 用途英文名
     */
    private String useLabelEnStr;

    /**
     * 用途中文名
     */
    private String useLabelCnStr;

    /**
     * 应用系统
     */
    private String appOs;

    /**
     * 申请状态
     */
    private String applyStatus;

}
