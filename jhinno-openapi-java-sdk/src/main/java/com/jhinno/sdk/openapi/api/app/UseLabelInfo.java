package com.jhinno.sdk.openapi.api.app;

import lombok.Data;

import java.util.List;

@Data
public class UseLabelInfo {
    /**
     * 用途id
     */
    private String useLabelId;

    /**
     * 用途名称
     */
    private String useLabelName;

    /**
     * 用途图片
     */
    private String useLabelIcon;

    /**
     * 用途app列表
     */
    private List<AppstoreAppInfo> apps;
}
