package com.jhinno.sdk.openapi.api.job;

import com.jhinno.sdk.openapi.ArgsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yanlongqi
 * @date 2024/2/6 15:42
 */
@Getter
@AllArgsConstructor
public enum JobsActionEnum {

    /**
     * 杀死作业
     */
    KILL("kill"),

    /**
     * 恢复作业
     */
    RESUME("resume"),

    /**
     * 暂停作业
     */
    STOP("stop"),

    ;

    private final String action;


    /**
     * 将作业的操作转换为JobsActionEnum
     *
     * @param action 操作字符
     * @return 作业操作类型
     * @exception  ArgsException action的类型不存在的异常
     */
    public static JobsActionEnum getJobAction(String action) {
        for (JobsActionEnum value : values()) {
            if (StringUtils.equals(action, value.getAction())) {
                return value;
            }
        }
        throw new ArgsException(action + "类型不存在！");
    }
}
