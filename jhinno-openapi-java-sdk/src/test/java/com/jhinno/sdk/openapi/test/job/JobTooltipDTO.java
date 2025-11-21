package com.jhinno.sdk.openapi.test.job;

import lombok.Data;

@Data
public class JobTooltipDTO {

    /**
     * 可用cpu核数
     */
    private Integer slotsAvail;

    /**
     * 等待作业数
     */
    private Integer totalPendJobs;

    /**
     * 调试信息
     */
    private String debugMessage;
}
