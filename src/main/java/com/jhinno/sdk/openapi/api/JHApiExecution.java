package com.jhinno.sdk.openapi.api;

import com.alibaba.fastjson2.TypeReference;
import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.CommonConstant;
import com.jhinno.sdk.openapi.ServiceException;
import com.jhinno.sdk.openapi.api.auth.AuthPathConstant;
import com.jhinno.sdk.openapi.api.auth.Token;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanlongqi
 * @date 2024/1/30 19:39
 */
public class JHApiExecution {

    /**
     * JHApiClient实例
     */
    private static JHApiClient JH_API_CLIENT;


    /**
     * 用户令牌的缓存
     */
    private static final Map<String, TokenInfo> TOKEN_INFO_MAP = new HashMap<>(10);

    /**
     * 设置在JHApiClient实例的实例
     *
     * @param jhApiClient 客户端实例
     */
    public void setJHApiClient(JHApiClient jhApiClient) {
        this.JH_API_CLIENT = jhApiClient;
    }

    /**
     * 获取用户的Token
     *
     * @param username 用户名
     * @param isForce  是否强制获取token
     * @return 用户的token
     */
    public String getToken(String username, boolean isForce) {
        if (StringUtils.isBlank(username)) {
            throw new ArgsException("用户名称不能为空！");
        }
        TokenInfo tokenInfo = TOKEN_INFO_MAP.get(username);
        // 如果是强制获取、用户令牌为空、用户令牌过期等，则获取令牌
        if (isForce || tokenInfo == null || System.currentTimeMillis() - tokenInfo.getCurrentTimestamp() <= CommonConstant.DEFAULT_TOKEN_EFFECTIVE_TIME * 60 * 1000) {
            Map<String, Object> params = new HashMap<>(2);

            String url = JHApiClient.getUrl(AuthPathConstant.AUTH_TOKEN, params);
            ResponseResult<Token> result = JH_API_CLIENT.get(url, new TypeReference<ResponseResult<Token>>() {
            });
            if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
                throw new ServiceException("获取token失败");
            }
            Token token = result.getData();
            tokenInfo = new TokenInfo();
            tokenInfo.setUserName(username);
            tokenInfo.setToken(token.getToken());
            tokenInfo.setCurrentTimestamp(System.currentTimeMillis());
            TOKEN_INFO_MAP.put(username, tokenInfo);
        }
        return tokenInfo.getToken();
    }


}
