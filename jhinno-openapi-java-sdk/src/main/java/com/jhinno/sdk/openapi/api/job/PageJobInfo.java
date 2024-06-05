package com.jhinno.sdk.openapi.api.job;

import lombok.Data;

import java.util.List;

/**
 * 查询分页作业数据
 *
 * @author yanlongqi
 * @date 2024/2/6 11:31
 */
@Data
public class PageJobInfo {

    /**
     * 作业总数
     */
    private Integer total;

    /**
     * 作业列表
     */
    private List<JobInfo> jobs;

}
