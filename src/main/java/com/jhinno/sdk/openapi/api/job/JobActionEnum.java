package com.jhinno.sdk.openapi.api.job;

import com.jhinno.sdk.openapi.ArgsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 作业的操作类型
 *
 * @author yanlongqi
 * @date 2024/2/6 15:01
 */

@Getter
@AllArgsConstructor
public enum JobActionEnum {

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

    /**
     * 置顶作业
     */
    TOP("top"),

    /**
     * 置低作业
     */
    BOT("bot"),
    ;

    private final String action;


    /**
     * 将作业的操作转换为JobActionEnum
     *
     * @param action 操作字符
     * @return 作业操作类型
     * @throws ArgsException action的类型不存在的异常
     */
    public static JobActionEnum getJobAction(String action) {
        if (StringUtils.isBlank(action)) {
            throw new ArgsException("操作标识不能为空！");
        }
        for (JobActionEnum value : values()) {
            if (StringUtils.equals(action, value.getAction())) {
                return value;
            }
        }
        throw new ArgsException(action + "类型不存在！");
    }

}
