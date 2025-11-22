package com.jhinno.sdk.openapi;

import com.jhinno.sdk.openapi.api.JHRequestExecution;

public abstract class JHApiExecutionAbstract {


    protected JHRequestExecution execution;

    public void init(JHRequestExecution execution) {
        this.execution = execution;
    }

}
