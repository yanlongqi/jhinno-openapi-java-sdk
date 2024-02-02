package com.jhinno.sdk.openapi.api.auth;

import lombok.Data;

/**
 * 请求令牌，用户请求其他接口
 *
 * @author yanlongqi
 * @date 2024/1/31 10:30
 */
@Data
public class Token {

    /**
     * 令牌
     */
    private String token;

}
