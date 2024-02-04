package com.jhinno.sdk.openapi.api.app;

import lombok.Data;

/**
 * 会话启动信息
 *
 * @author yanlongqi
 * @date 2024/2/1 18:39
 */
@Data
public class AppStartedInfo {

    /**
     * JHClient的地址（用于拉起对应会话的JHClient客户端）
     *
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
     * 会话id
     */
    private String desktopId;

    /**
     * 作业id（有可能出现，但不是太明白）
     */
    private String jobId;
}
