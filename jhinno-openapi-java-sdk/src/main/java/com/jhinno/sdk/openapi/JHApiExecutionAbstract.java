package com.jhinno.sdk.openapi;

import com.jhinno.sdk.openapi.api.JHRequestExecution;
import lombok.Setter;

@Setter
public abstract class JHApiExecutionAbstract {

    // 提供setter方法，支持依赖注入
    protected JHRequestExecution execution;

    // 默认构造函数，允许子类不实现构造方法
    public JHApiExecutionAbstract() {
    }

    // 带参数的构造函数，允许直接注入
    public JHApiExecutionAbstract(JHRequestExecution execution) {
        this.execution = execution;
    }

}
