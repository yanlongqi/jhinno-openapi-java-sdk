package com.jhinno.sdk.openapi.api.app;

import com.jhinno.sdk.openapi.api.JHRequestExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;
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
     *
     * <p>
     * 示例数据：/appform/images/apps/app_fluent.png
     * <p>
     * 需要使用 {@link  JHApiClient#getUrl(String)} 拼接景行的服务器地址;使用Spring，JHApiClient在容器中，可以使用注入的方式获得
     *
     * <pre>{@code
     *  @Autoward
     *  private JHApiClient apiClient;
     *
     *  public String getIconUrl(String icon){
     *      return apiClient.getUrl(icon);
     *  }
     *
     * }</pre>
     *
     * <p>
     * 拼接后的示例数据：https://192.168.87.24/appform/images/apps/app_fluent.png
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

    /**
     * 操作系统类型
     * <ul>
     *     <li>空：系统应用，如：我的会话、我的作业、应用仓库等。</li>
     *     <li>linux: 应用是Linux应用</li>
     *     <li>windows: 应用是Windows应用</li>
     * </ul>
     *
     * @see AppTypeConstant.AppOsType 定义了 os 可能的值
     * <p>
     * 如果需要获取特定分类的应用列表，可以使用 {@link AppTypeConstant.AppOsType#getAppList(List)}
     */
    private String os;

    private String showin;

    private String splashImg;

    private Boolean uneditable;

    /**
     * 应用类型
     *
     * <ul>
     *     <li>空：系统应用，如：我的会话、我的作业、应用仓库等。</li>
     *     <li>batch: 计算应用，如：通用计算、Fluent等</li>
     *     <li>desktop: 图形应用。如：Windows桌面、Linux桌面、Notepad等</li>
     * </ul>
     *
     * @see AppTypeConstant.AppType 定义了 os 可能的值
     * <p>
     * 如果需要获取特定分类的应用列表，可以使用 {@link AppTypeConstant.AppType#getAppList(List)}
     */
    private String type;

    /**
     * 应用分类
     * <p>
     * 系统应用返回system，其他为空字符串
     */
    private String category;

    /**
     * 应用协议
     * <p>
     * 图形会话返回jhapp,其他应用为空字符串
     * </p>
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


    private String apiKey;

    /**
     * 应用状态
     */
    private String appStatus;

    /**
     * 应用描述
     */
    private String description;

    
    private Boolean used;

}
