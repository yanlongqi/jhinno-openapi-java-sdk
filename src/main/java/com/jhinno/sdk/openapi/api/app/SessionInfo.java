package com.jhinno.sdk.openapi.api.app;

import com.sun.java.swing.plaf.windows.resources.windows;
import lombok.Data;

import java.util.Date;

/**
 * 会话信息
 *
 * @author yanlongqi
 * @date 2024/2/4 10:32
 */
@Data
public class SessionInfo {

    /**
     * 最后使用时间
     */
    private Date lastUseTime;
    /**
     * 转移操作员权限
     */
    private String transfer_operator_right;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 协议
     * <ul>
     *     <li>jhapp</li>
     *     <li>其他</li>
     * </ul>
     */
    private String protocol;

    /**
     * 主机
     */
    private String host;
    /**
     *
     */
    private String backend;

    /**
     * 会话id
     */
    private String id;
    /**
     * 应用id
     */
    private String app_id;
    /**
     *
     */
    private Boolean isMtUsing;
    /**
     * 桌面类型
     */
    private String desktop_type;
    /**
     * 密级英文名
     */
    private String confidentialEn;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 密级
     */
    private String confidential;
    /**
     * 会话属主
     */
    private String owner;
    /**
     * 密级中文名称
     */
    private String confidential_cn;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 启动模式
     */
    private String startmode;
    /**
     * JHClient的地址（用于拉起对应会话的JHClient客户端）
     * <ul>
     *     <li>
     *         测试时:可将其粘贴纸浏览器的地址栏里面拉起JHClient客户端
     *     </li>
     *     <li>
     *         开发时:通过使用a标签，或者使用iframe的方式拉起JHClient客户端
     *     </li>
     * </ul>
     */
    private String jhappUrl;

    /**
     * 会话属主姓名
     */
    private String ownername;

    /**
     * 是否分享给我
     */
    private Boolean shareMe;

    /**
     * 会话id
     */
    private String session_id;
    /**
     * 执行时间
     */
    private String executionTime;
    /**
     * 是否docker会话
     */
    private String isDocker;
    /**
     * 会话名称
     */
    private String name;
    /**
     * 是否共享
     */
    private Boolean isShare;
    /**
     * 状态
     */
    private String status;
}
