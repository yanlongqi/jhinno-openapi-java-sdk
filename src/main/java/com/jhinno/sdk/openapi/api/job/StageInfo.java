package com.jhinno.sdk.openapi.api.job;

import lombok.Data;

/**
 * 阶段信息（不明白）
 *
 * @author yanlongqi
 * @date 2024/2/6 14:11
 */
@Data
public class StageInfo {

    /**
     *
     */
    private Long op;

    /**
     *
     */
    private Long stageNo;

    /**
     *
     */
    private Integer transferStatus;

    /**
     *
     */
    private String stageType;

    /**
     * -f 操作符号前面的机器，默认是提交节点
     */
    private String srcHost;

    /**
     * -f 操作符号前面的文件
     */

    private String srcData;
    /**
     * -f 操作符号后面的机器，默认是计算头节点
     */
    private String desHost;
    /**
     * -f 操作符号后面的文件
     */
    private String desData;

    /**
     *
     */
    private String progress;

    /**
     *
     */
    private Long exitCode;
}
