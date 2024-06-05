package com.jhinno.sdk.openapi.api.job;

import lombok.Data;

/**
 * 作业历史信息
 *
 * @author yanlongqi
 * @date 2024/2/6 16:00
 */
@Data
public class JobHistoryInfo {

    /**
     * 作业id
     */
    private String jobId;


    /**
     * 作业历史
     */
    private String jobhistory;
}
