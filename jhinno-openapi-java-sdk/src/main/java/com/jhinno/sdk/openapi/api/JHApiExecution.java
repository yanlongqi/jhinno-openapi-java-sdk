package com.jhinno.sdk.openapi.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.*;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.api.auth.AuthPathConstant;
import com.jhinno.sdk.openapi.client.DefaultHttpClientConfig;
import com.jhinno.sdk.openapi.client.JHApiClient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import com.jhinno.sdk.openapi.api.app.AppPathConstant;
import org.apache.http.entity.ContentType;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
@Data
@NoArgsConstructor
public class JHApiExecution {


    /**
     * JHApiClient实例
     */
    private JHApiClient jhApiClient;

    /**
     * token的超时时间
     */
    private int tokenTimeout = DefaultHttpClientConfig.DEFAULT_TOKEN_EFFECTIVE_TIME;

    /**
     * token提前获取的时间
     */
    private int tokenResidueTime = DefaultHttpClientConfig.DEFAULT_TOKEN_RESIDUE_TIME;


    /**
     * 是否使用服务器时间，开启可能会导致请求过慢，但是不会太慢，默认token会有缓存
     */
    private boolean isUsedServerTime = DefaultHttpClientConfig.DEFAULT_IS_USED_SERVER_TIME;

    /**
     * 是否强制获取用户的token，默认{@link DefaultHttpClientConfig#DEFAULT_IS_FORCE_GET_TOKEN},
     * 如果强制获取token，则每次请求都去获取token；
     */
    private boolean isForceGetToken = DefaultHttpClientConfig.DEFAULT_IS_FORCE_GET_TOKEN;

    /**
     * 接口请求的认证类型
     */
    private AuthType authType = AuthType.ACCESS_SECRET_MODE;

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 访问密钥密码
     */
    private String accessKeySecret;


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
     * @return 用户的token
     */
    public String getToken(String username) {
        if (StringUtils.isBlank(username)) {
            throw new ArgsException("用户名称不能为空！");
        }
        TokenInfo tokenInfo = TOKEN_INFO_MAP.get(username);

        // 防止因为服务器时间的问题二导致token不可用，可以通过此配置提前获取token
        int tokenEffectiveTime = (tokenTimeout - tokenResidueTime) * 60 * 1000;

        // 如果是强制获取、用户令牌为空、用户令牌过期等，则获取令牌
        if (isForceGetToken || tokenInfo == null || System.currentTimeMillis() - tokenInfo.getCurrentTimestamp() > tokenEffectiveTime) {
            Map<String, Object> params = new HashMap<>(2);
            params.put("timeout", tokenTimeout);
            String currentTimeMillis = getCurrentTimeMillis();
            String beforeEncryption = String.format(CommonConstant.TokenUserFormat, username, currentTimeMillis);
            try {
                SecretKeySpec secretKey = new SecretKeySpec(CommonConstant.DEFAULT_AES_KEY.getBytes(StandardCharsets.UTF_8), CommonConstant.AES_ALGORITHM);
                Cipher cipher = Cipher.getInstance(CommonConstant.AES_ECB_PADDING);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encryptBytes = cipher.doFinal(beforeEncryption.getBytes(StandardCharsets.UTF_8));
                params.put("username", Base64.getEncoder().encodeToString(encryptBytes));
            } catch (Exception e) {
                throw new ClientException("AES加密失败，失败原因：" + e.getMessage(), e);
            }

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
     * @return
     */
    public String getCurrentTimeMillis() {
        if (authType == AuthType.ACCESS_SECRET_MODE || !isUsedServerTime) {
            return String.valueOf(System.currentTimeMillis());
        }
        return jhApiClient.getAppformServerCurrentTimeMillis();
    }


    /**
     * 构建一个带token的请求头
     *
     * @param username      用户名
     * @param isContentType 是否携带默认的Content-type，默认为{@link ContentType#APPLICATION_JSON}
     * @return 请求头
     */
    protected Map<String, Object> getHeaders(String username, boolean isContentType) {
        Map<String, Object> headers = new HashMap<>();
        // 默认请求json数据
        if (isContentType) {
            headers.put("Content-type", ContentType.APPLICATION_JSON.getMimeType());
        }
        if (authType == AuthType.ACCESS_SECRET_MODE) {
            if (StringUtils.isBlank(accessKey)) {
                throw new ClientException("AccessKey不能为空");
            }
            if (StringUtils.isBlank(accessKeySecret)) {
                throw new ClientException("AccessKeySecret不能为空");
            }
            headers.put(CommonConstant.ACCESS_KEY, accessKey);

            if (StringUtils.isBlank(username)) {
                username = StringUtils.EMPTY;
            }
            headers.put(CommonConstant.USERNAME, username);

            String currentTimeMillis = getCurrentTimeMillis();
            headers.put(CommonConstant.CURRENT_TIME_MILLIS, currentTimeMillis);
            headers.put(CommonConstant.SIGNATURE, getsSignature(username, currentTimeMillis));
        } else if (authType == AuthType.TOKEN_MODE && StringUtils.isNotBlank(username)) {
            headers.put(CommonConstant.TOKEN, getToken(username));
        }
        return headers;
    }


    /**
     * 获取签名
     *
     * @param username          用户名
     * @param currentTimeMillis 时间戳
     * @return 签名
     */
    public String getsSignature(String username, String currentTimeMillis) {
        SecretKeySpec secretKey = new SecretKeySpec(accessKeySecret.getBytes(StandardCharsets.UTF_8), CommonConstant.HMAC_SHA_256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(CommonConstant.HMAC_SHA_256_ALGORITHM);
            mac.init(secretKey);
            String beforeSignature = String.format(CommonConstant.SIGNATURE_FORMAT, accessKey, username, currentTimeMillis);
            byte[] digest = mac.doFinal(beforeSignature.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(digest);
        } catch (Exception e) {
            throw new ClientException("签名加密失败，失败信息：" + e.getMessage(), e);
        }
    }

    /**
     * 构建一个带token的请求头
     *
     * @param username 用户名
     * @return 请求头
     */
    protected Map<String, Object> getHeaders(String username) {
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
     * 退出用户的登录，释放许可，当用户退出登录后，建议清除用户的token信息
     *
     * @param username 用户名
     */
    public void logout(String username) {
        delete(AuthPathConstant.AUTH_LOGOUT, username);
        TOKEN_INFO_MAP.remove(username);
    }
}
