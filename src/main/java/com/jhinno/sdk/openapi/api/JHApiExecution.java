package com.jhinno.sdk.openapi.api;

import cn.hutool.crypto.symmetric.AES;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.CommonConstant;
import com.jhinno.sdk.openapi.ServiceException;
import com.jhinno.sdk.openapi.api.app.AppStartedInfo;
import com.jhinno.sdk.openapi.api.auth.AuthPathConstant;
import com.jhinno.sdk.openapi.api.auth.Token;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定义一个请求的执行器，
 *
 * @author yanlongqi
 * @date 2024/1/30 19:39
 */
public class JHApiExecution {

    /**
     * JHApiClient实例
     */
    public JHApiClient jhApiClient;

    /**
     * token的超时时间
     */
    private int tokenTimeout = CommonConstant.DEFAULT_TOKEN_EFFECTIVE_TIME;

    /**
     * token提前获取的时间
     */
    private int tokenResidueTime = CommonConstant.DEFAULT_TOKEN_RESIDUE_TIME;


    /**
     * 获取一个执行器的实例
     *
     * @param jhApiClient 请求的客户端
     */
    protected JHApiExecution(JHApiClient jhApiClient) {
        this.jhApiClient = jhApiClient;
    }

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
        this.jhApiClient = jhApiClient;
    }


    /**
     * 设置token超时的时间，单位：分钟
     *
     * @param tokenTimeout token的超时时间
     */
    public void setTokenTimeout(int tokenTimeout) {
        this.tokenTimeout = tokenTimeout;
    }

    /**
     * 设置提前获取token的时间，单位：分钟
     *
     * @param tokenResidueTime 提前获取token的时间
     */
    public void setTokenResidueTime(int tokenResidueTime) {
        this.tokenResidueTime = tokenResidueTime;
    }

    /**
     * 获取用户的Token
     *
     * @param username 用户名
     * @param isForce  是否强制获取toke
     * @return 用户的token
     */
    public String getToken(String username, boolean isForce) {
        if (StringUtils.isBlank(username)) {
            throw new ArgsException("用户名称不能为空！");
        }
        TokenInfo tokenInfo = TOKEN_INFO_MAP.get(username);

        // 防止因为服务器时间的问题二导致token不可用，可以通过此配置提前获取token
        int tokenEffectiveTime = (tokenTimeout - tokenResidueTime) * 60 * 1000;
        // 如果是强制获取、用户令牌为空、用户令牌过期等，则获取令牌
        if (isForce || tokenInfo == null || System.currentTimeMillis() - tokenInfo.getCurrentTimestamp() < tokenEffectiveTime) {
            Map<String, Object> params = new HashMap<>(2);
            params.put("timeout", tokenTimeout);

            AES aes = new AES(CommonConstant.DEFAULT_AES_KEY.getBytes());
            String base64 = aes.encryptBase64(String.format("%s,%s", username, System.currentTimeMillis()));
            params.put("username", base64);
            String url = JHApiClient.getUrl(AuthPathConstant.AUTH_TOKEN_PATH, params);
            Token token = get(url, new TypeReference<ResponseResult<Token>>() {
            });
            tokenInfo = new TokenInfo();
            tokenInfo.setUserName(username);
            tokenInfo.setToken(token.getToken());
            tokenInfo.setCurrentTimestamp(System.currentTimeMillis());
            TOKEN_INFO_MAP.put(username, tokenInfo);
        }
        return tokenInfo.getToken();
    }

    /**
     * 获取用户的Token，获取缓存，不强制获取
     *
     * @param username 用户名
     * @return 用户的token
     */
    public String getToken(String username) {
        return getToken(username, false);
    }


    /**
     * 构建一个带token的请求头
     *
     * @param username 用户名
     * @return 请求头
     */
    protected Map<String, String> getHeaders(String username) {
        if (StringUtils.isBlank(username)) {
            return new HashMap<>();
        }
        Map<String, String> headers = new HashMap<>();
        headers.put("token", getToken(username));
        return headers;
    }

    /**
     * 发起一个匿名的GET请求
     *
     * @param path 请求路径
     * @param type 响应数据类型
     * @param <T>  数据类型
     * @return 响应数据
     */
    public <T> T get(String path, TypeReference<ResponseResult<T>> type) {
        return get(path, null, type);
    }

    /**
     * 发起携带token的GET请求
     *
     * @param username 用户名
     * @param path     请求路径
     * @param type     响应的数据类型
     * @param <T>      数据类型
     * @return 返回的数据
     */
    public <T> T get(String path, String username, TypeReference<ResponseResult<T>> type) {
        ResponseResult<T> result = jhApiClient.get(path, getHeaders(username), type);
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(AuthPathConstant.AUTH_TOKEN_PATH, result.getCode(), result.getMessage());
        }
        return result.getData();
    }


    /**
     * 发起一个有返回值的POST请求
     *
     * @param path     请求路径
     * @param username 用户名
     * @param body     请求体
     * @param type     强求提的数据类型
     * @param <R>      返回的数据类型
     * @param <B>      请求体的数据类型
     * @return 请求后的数据
     */
    public <R, B> R post(String path, String username, B body, TypeReference<ResponseResult<R>> type) {
        ResponseResult<R> result = jhApiClient.post(path, body, getHeaders(username), type);
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(path, result.getCode(), result.getMessage());
        }
        return result.getData();
    }


    /**
     * 发起一个没有返回值的POST请求
     *
     * @param path     请求路径
     * @param username 用户名
     * @param body     请求体
     * @param <B>      请求体数据类型
     */
    public <B> void post(String path, String username, B body) {
        ResponseResult<?> result = jhApiClient.post(path, body, getHeaders(username), new TypeReference<ResponseResult<?>>() {
        });
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(path, result.getCode(), result.getMessage());
        }
    }

}
