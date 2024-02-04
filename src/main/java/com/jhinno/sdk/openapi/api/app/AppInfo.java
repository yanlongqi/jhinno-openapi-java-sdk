package com.jhinno.sdk.openapi.api.app;

import lombok.Data;

import java.util.List;

/**
 * 应用信息
 *
 * @author yanlongqi
 * @date 2024/2/4 16:13
 */
@Data
public class AppInfo {
    /**
     * 应用拆
     */
    private String id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 英文名
     */
    private String enName;

    /**
     * 应用图标
     */
    private String icon;
    private String copyAppClass;

    /**
     * 是否多个
     */
    private Boolean mutiple;

    /**
     * 最大化
     */
    private Boolean maximize;
    private Boolean maximized;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 高度
     */
    private Integer height;

    private String appFeature;

    private String acl;

    /**
     * 应用链接地址
     */
    private String url;

    private Boolean allowContext;

    private String mode;

    private String os;

    private String showin;

    private String splashImg;

    private Boolean uneditable;

    /**
     * 应用类型
     */
    private String type;

    /**
     * 应用分类
     */
    private String category;

    /**
     * 应用协议
     */
    private String protocol;

    /**
     * 帮助文档类型
     */
    private Integer helpDocumentType;

    /**
     * 帮助文档
     */
    private String helpDocument;


    private Boolean resizable;

    private Boolean simpleApp;

    /**
     * 系统
     */
    private Boolean system;

    /**
     * 资源
     */
    private String resource;

    /**
     * 上下文配置
     */
    private String contextConfig;

    /**
     * x
     */
    private Integer x;

    /**
     * y
     */
    private Integer y;

    /**
     * 会话列表
     */
    private List<String> sessionIds;

    /**
     * 开始前缀
     */
    private String startPrefix;

    /**
     * 应用名称
     */
    private String appname;

    /**
     * 路径
     */
    private String path;

    /**
     * 工作路径
     */
    private String cwd;


}
