package com.jhinno.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.ClientErrorCode;
import com.jhinno.sdk.openapi.ClientException;
import com.jhinno.sdk.openapi.CommonConstant;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.api.auth.AuthPathConstant;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * 提供请求的工具
 *
 * @author yanlongqi
 * @date 2024/1/29 10:31
 */
@Data
public class JHApiClient {

    /**
     * 基础的请求URL地址
     * <p>
     * 如：https:// 192.168.3.12/appform
     * </p>
     */
    private String baseUrl;


    /**
     * 设置自定义的jackson序列化配置
     */
    private ObjectMapper mapper;


    /**
     * API HTTP 客户端
     */
    private JHApiHttpClient apiHttpClient;


    public JHApiClient(String baseUrl) {
        if (StringUtils.isBlank(baseUrl)) {
            throw new ClientException("服务器的BaseUrl不能为空！");
        }
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        this.baseUrl = baseUrl;
        mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mapper.setDateFormat(new SimpleDateFormat(CommonConstant.NORM_DATETIME_PATTERN));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void initDefaultApiClient() {
        JHApiHttpClientImpl jhApiHttpClient = new JHApiHttpClientImpl();
        jhApiHttpClient.init();
        jhApiHttpClient.createHttpClients();
        this.apiHttpClient = jhApiHttpClient;
    }


    /**
     * 发送一个get请求
     *
     * @param path    接口路径
     * @param headers 请求头
     * @param type    返回数据类型
     * @param <T>     返回数据类型
     * @return 返回数据
     */
    public <T> T get(String path, Map<String, Object> headers, TypeReference<T> type) {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("url不能为空");
        }
        try (InputStream content = apiHttpClient.get(getUrl(path), headers)) {
            return mapper.readValue(content, type);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }


    /**
     * 发起一个get请求
     *
     * @param path 接口地址
     * @param type 返回数据的类型
     * @param <T>  返回数据的类型
     * @return 请求的数据
     */
    public <T> T get(String path, TypeReference<T> type) {
        return get(path, null, type);
    }

    /**
     * 获的一个url
     *
     * @param path   请求地址
     * @param params 请求参数
     * @return 添加路径参数后的URL
     */
    public static String getUrl(String path, Map<String, Object> params) {
        if (StringUtils.isBlank(path)) {
            throw new ClientException("path不能为空");
        }
        if (params == null || params.isEmpty()) {
            return path;
        }
        StringBuilder urlBuilder = new StringBuilder(path + "?");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                Object value = entry.getValue();
                // 如果值为空，则不添加该字段
                if (value == null) {
                    continue;
                }
                urlBuilder.append(entry.getKey()).append("=");
                if (value instanceof String) {
                    urlBuilder.append(URLEncoder.encode((String) value, StandardCharsets.UTF_8.name()));
                } else if (value instanceof Date) {
                    SimpleDateFormat format = new SimpleDateFormat(CommonConstant.NORM_DATETIME_PATTERN);
                    urlBuilder.append(URLEncoder.encode(format.format(value), StandardCharsets.UTF_8.name()));
                } else {
                    urlBuilder.append(value);
                }
                urlBuilder.append("&");
            } catch (UnsupportedEncodingException e) {
                throw new ClientException("url参数编码失败", ClientErrorCode.UNKNOWN, e);
            }
        }
        urlBuilder.setLength(urlBuilder.length() - 1);
        return urlBuilder.toString();
    }


    /**
     * 获取完整的请求路径
     *
     * @param path 文件路径
     * @return 请求URL
     */
    public String getUrl(String path) {
        return baseUrl + path;
    }

    /**
     * 发送post请求
     *
     * @param path    请求地址
     * @param body    请求体
     * @param headers 请求头
     * @param type    请求数据类型
     * @param <T>     t 返回的数据的类型
     * @param <K>     k body的类型
     * @return t
     */
    public <T, K> T post(String path, K body, Map<String, Object> headers, TypeReference<T> type) {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("path不能为空");
        }
        try {
            String bodyStr = null;
            if (body != null) {
                bodyStr = mapper.writeValueAsString(body);
            }
            try (InputStream content = apiHttpClient.post(getUrl(path), bodyStr, headers)) {
                return mapper.readValue(content, type);
            }
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }


    /**
     * 发起put请求
     *
     * @param path    请求地址
     * @param params  请求参数
     * @param body    请求体
     * @param headers 请求头
     * @param type    请求数据类型
     * @param <T>     t 返回的数据的类型
     * @param <K>     k body的类型
     * @return 请求返回的数据
     */
    public <T, K> T put(String path, Map<String, Object> params, K body, Map<String, Object> headers, TypeReference<T> type) {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("path不能为空");
        }
        try {
            String bodyStr = null;
            if (body != null) {
                bodyStr = mapper.writeValueAsString(body);
            }
            try (InputStream content = apiHttpClient.put(getUrl(path), bodyStr, headers)) {
                return mapper.readValue(content, type);
            }
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }


    /**
     * 发起put请求
     *
     * @param path    请求地址
     * @param body    请求体
     * @param headers 请求头
     * @param type    请求数据类型
     * @param <T>     t 返回的数据的类型
     * @param <K>     k body的类型
     * @return 请求返回的数据
     */
    public <T, K> T put(String path, K body, Map<String, Object> headers, TypeReference<T> type) {
        return put(path, null, body, headers, type);
    }


    /**
     * 发起put请求
     *
     * @param path 请求地址
     * @param body 请求体
     * @param type 请求数据类型
     * @param <T>  t 返回的数据的类型
     * @param <K>  k body的类型
     * @return 请求返回的数据
     */
    public <T, K> T put(String path, K body, TypeReference<T> type) {
        return put(path, body, null, type);
    }

    /**
     * @param path 请求地址
     * @param body 请求体
     * @param type 响应类型
     * @param <T>  相应返回数据类型
     * @param <K>  请求体数据类型
     * @return 响应数据
     */
    public <T, K> T post(String path, K body, TypeReference<T> type) {
        return post(path, body, null, type);
    }


    /**
     * 发起delete请求
     *
     * @param path    请求地址
     * @param headers 请求头
     * @param type    响应类型
     * @param <T>     响应数据类型
     * @return 响应数据
     */
    public <T> T delete(String path, Map<String, Object> headers, TypeReference<T> type) {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("path不能为空");
        }
        try (InputStream content = apiHttpClient.delete(getUrl(path), headers)) {
            return mapper.readValue(content, type);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }


    /**
     * 发起delete请求
     *
     * @param path 请求地址
     * @param type 响应类型
     * @param <T>  响应数据类型
     * @return 响应数据
     */
    public <T> T delete(String path, TypeReference<T> type) {
        return delete(path, null, type);
    }

    /**
     * @param path     请求路径
     * @param keyName  文件key
     * @param fileName 文件名
     * @param is       文件流
     * @param headers  请求头
     * @param body     请求体其他数据
     * @param type     返回数据类型
     * @return 请求类型
     */
    public ResponseResult<Object> upload(String path,
                                         String keyName,
                                         String fileName,
                                         InputStream is,
                                         Map<String, Object> headers,
                                         Map<String, Object> body,
                                         TypeReference<ResponseResult<Object>> type) {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("path不能为空");
        }
        try {
            try (InputStream content = apiHttpClient.upload(getUrl(path), keyName, fileName, is, body, headers)) {
                return mapper.readValue(content, type);
            }
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    public String getAppformServerCurrentTimeMillis() {
        try {
            long currentTimeMillis = apiHttpClient.getAppformServerCurrentTimeMillis(getUrl(AuthPathConstant.PING));
            return String.valueOf(currentTimeMillis);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }
}
