package com.jhinno.sdk.openapi.api.job;

import com.jhinno.sdk.openapi.ArgsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 作业状态
 * <p>
 * 该枚举表示作业的状态的类型，其中status标识接口中的值，name标识对应的中文状态
 *
 * @author yanlongqi
 * @date 2024/2/6 17:03
 */

@Getter
@AllArgsConstructor
public enum JobStatusEnum {

    RUN("RUN", "运行"),
    PEND("PEND", "等待"),
    PSUSP("PSUSP", "等待中挂起"),
    USUSP("USUSP", "用户挂起"),
    SSUSP("SSUSP", "系统挂起"),
    ZOMBI("ZOMBI", "僵尸"),
    DONE("DONE", "完成"),
    EXIT("EXIT", "退出"),
    UNKNOWN_UNKWN("UNKNOWN#UNKWN", "状态不明");

    /**
     * 状态标识
     */
    private final String status;

    /**
     * 状态名称
     */
    private final String name;

    /**
     * 使用标号获得JobStatusEnum对象
     *
     * @param status 作业状态
     * @return 作业状态
     * @throws ArgsException 参数异常
     */
    public static JobStatusEnum getJobStatus(String status) {
        if (StringUtils.isBlank(status)) {
            return null;
        }
        for (JobStatusEnum value : values()) {
            if (StringUtils.equals(status, value.getStatus())) {
                return value;
            }
        }
        throw new ArgsException(status + "类型不存在！");
    }

}
