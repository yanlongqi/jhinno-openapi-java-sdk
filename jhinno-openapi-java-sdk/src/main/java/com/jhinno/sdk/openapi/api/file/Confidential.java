package com.jhinno.sdk.openapi.api.file;

import lombok.Data;

@Data
public class Confidential {

    /**
     * 密级key
     */
    private String conf;

    /**
     * 密级英文名
     */
    private String confEn;

    /**
     * 密级中文名
     */
    private String confCn;

    /**
     * 密级权重
     */
    private int confLevel;

}
