package com.jhinno.sdk.openapi.api.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 密码操作的类型
 *
 * @author yanlongqi
 * @date 2024/2/19 10:54
 */
@Getter
@AllArgsConstructor
public enum UpdateUserPasswordType {
    /**
     * 重置密码
     */
    RESET_UPDATE_PASSWORD_TYPE("reset"),

    /**
     * 首次登录强制修改密码
     */
    FORCE_UPDATE_PASSWORD_TYPE("force");

    private final String value;

}
