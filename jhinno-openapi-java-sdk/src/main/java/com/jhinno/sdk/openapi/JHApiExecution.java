package com.jhinno.sdk.openapi;

import com.jhinno.sdk.openapi.api.JHRequestExecution;

public interface JHApiExecution {

    /**
     * 初始化API执行器
     */
    void init(JHRequestExecution execution);

}
