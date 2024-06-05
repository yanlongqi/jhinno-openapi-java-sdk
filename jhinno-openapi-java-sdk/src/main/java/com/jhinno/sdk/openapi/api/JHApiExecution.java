package com.jhinno.sdk.openapi.api;

import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.CommonConstant;
import com.jhinno.sdk.openapi.ServiceException;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.api.auth.AuthPathConstant;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.apache.commons.lang3.StringUtils;
import com.jhinno.sdk.openapi.api.app.AppPathConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定义一个请求的执行器，该执行器是一个可以执行任何景行API接口（包含定制新增的接口，特殊接口除外，如有需求，可自行修改源码）
 * <p>
 * 注：接口都需要传入username来获取接口调用的token，匿名调用的接口可将username传为null
 *
 * <p>
 * 对于定制接口，可参考以下步骤封装调用方法
 * <ol>
 *     <li>定义一个const类同来存放接口的路径，方便后期的维护，如：{@link AppPathConstant}</li>
 *     <li>继承{@link JHApiExecution}，如：{@link JHAppApiExecution}</li>
 *     <li>参考{@link JHAppApiExecution}中封装的方法，调用{@link JHApiExecution}中的get、post、put、delete等对新的接口封装</li>
 * </ol>
 *
 * @author yanlongqi
 * @date 2024/1/30 19:39
 * @see JHAppApiExecution
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
     * 是否使用服务器时间，开启可能会导致请求过慢，但是不会太慢，默认token会有缓存
     */
    private boolean isUsedServerTime = CommonConstant.DEFAULT_IS_USED_SERVER_TIME;


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
    private static final Map<String, TokenInfo> TOKEN_INFO_MAP = new ConcurrentHashMap<>(20);

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
     * 是否使用服务器时间
     */
    public boolean isUsedServerTime() {
        return isUsedServerTime;
    }

    /**
     * 设置是否使用服务器时间
     *
     * @param usedServerTime 是否使用服务器时间
     */
    public void setUsedServerTime(boolean usedServerTime) {
        isUsedServerTime = usedServerTime;
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
            long currentTimeMillis = isUsedServerTime ? jhApiClient.requestTimeMillis() : System.currentTimeMillis();
            AES aes = new AES(CommonConstant.DEFAULT_AES_KEY.getBytes());
            String base64 = aes.encryptBase64(String.format("%s,%s", username, currentTimeMillis));
            params.put("username", base64);
            String url = JHApiClient.getUrl(AuthPathConstant.AUTH_TOKEN_PATH, params);
            Map<String, String> token = get(url, new TypeReference<ResponseResult<Map<String, String>>>() {
            });
            tokenInfo = new TokenInfo();
            tokenInfo.setUserName(username);
            tokenInfo.setToken(token.get("token"));
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
     * @param username      用户名
     * @param isContentType 是否携带默认的Content-type，默认为{@link ContentType#JSON}
     * @return 请求头
     */
    protected Map<String, String> getHeaders(String username, boolean isContentType) {
        Map<String, String> headers = new HashMap<>();
        // 默认请求json数据
        if (isContentType) {
            headers.put("Content-type", ContentType.JSON.getValue());
        }
        if (StringUtils.isBlank(username)) {
            return headers;
        }
        headers.put("token", getToken(username));
        return headers;
    }

    /**
     * 构建一个带token的请求头
     *
     * @param username 用户名
     * @return 请求头
     */
    protected Map<String, String> getHeaders(String username) {
        return getHeaders(username, true);
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
            throw new ServiceException(path, result.getCode(), result.getMessage());
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
     * 发起一个有返回值的POST请求
     *
     * @param path     请求路径
     * @param username 用户名
     * @param type     强求提的数据类型
     * @param <R>      返回的数据类型
     * @return 请求后的数据
     */
    public <R> R post(String path, String username, TypeReference<ResponseResult<R>> type) {
        return post(path, username, null, type);
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


    /**
     * 发起一个没有请求体，没有数据返回的POST请求
     *
     * @param path     请求路径
     * @param username 用户名
     */
    public void post(String path, String username) {
        post(path, username, new TypeReference<ResponseResult<?>>() {
        });
    }


    /**
     * 发起一个有返回值的PUT请求
     *
     * @param path     请求路径
     * @param username 用户名
     * @param body     请求体数据
     * @param type     响应数据类型
     * @param <R>      返回数据
     * @param <B>      请求体数据
     * @return 返回数据
     */
    public <R, B> R put(String path, String username, B body, TypeReference<ResponseResult<R>> type) {
        ResponseResult<R> result = jhApiClient.put(path, body, getHeaders(username), type);
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(path, result.getCode(), result.getMessage());
        }
        return result.getData();
    }


    /**
     * 发起一个有返回值的PUT请求
     *
     * @param path     请求路径
     * @param username 用户名
     * @param type     强求提的数据类型
     * @param <R>      返回的数据类型
     * @return 请求后的数据
     */
    public <R> R put(String path, String username, TypeReference<ResponseResult<R>> type) {
        return put(path, username, null, type);
    }

    /**
     * 发一个没有返回值的PUT请求
     *
     * @param path     请求路径
     * @param username 用户名
     * @param body     请求体数据
     * @param <B>      请求体数据类型
     */
    public <B> void put(String path, String username, B body) {
        ResponseResult<?> result = jhApiClient.put(path, body, getHeaders(username), new TypeReference<ResponseResult<?>>() {
        });
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(path, result.getCode(), result.getMessage());
        }
    }


    /**
     * 发起一个没有请求体，没有数据返回的PUT请求
     *
     * @param path     请求路径
     * @param username 用户名
     */
    public void put(String path, String username) {
        put(path, username, new TypeReference<ResponseResult<?>>() {
        });
    }


    /**
     * 发起一个DELETE请求，有数据返回
     *
     * @param path     请求路径
     * @param username 用户名
     * @param type     响应类型
     * @param <R>      响应数据类型
     * @return 响应数据
     */
    public <R> R delete(String path, String username, TypeReference<ResponseResult<R>> type) {
        ResponseResult<R> result = jhApiClient.delete(path, getHeaders(username), type);
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(path, result.getCode(), result.getMessage());
        }
        return result.getData();
    }


    /**
     * 发起一个DELETE请求，没有数据返回
     *
     * @param path     请求路劲
     * @param username 用户名
     */
    public void delete(String path, String username) {
        ResponseResult<?> result = jhApiClient.delete(path, getHeaders(username), new TypeReference<ResponseResult<?>>() {
        });
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(path, result.getCode(), result.getMessage());
        }
    }


    /**
     * 退出用户的登录，释放许可
     *
     * @param username 用户名
     */
    public void logout(String username) {
        delete(AuthPathConstant.AUTH_LOGOUT, username);
        TOKEN_INFO_MAP.remove(username);
    }
}
