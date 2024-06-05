package com.jhinno.sdk.openapi.api;

import lombok.Data;

import java.util.List;

/**
 * 分页工具
 *
 * @author yanlongqi
 * @date 2024/2/6 18:14
 */
@Data
public class PageResult<T> {

    /**
     * 总记录数
     */
    private Long totalCount;
    /**
     * 每页记录数
     */
    private Long pageSize;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 当前页数
     */
    private Long currentPage;
    /**
     * 列表数据
     */
    private List<T> list;

}
