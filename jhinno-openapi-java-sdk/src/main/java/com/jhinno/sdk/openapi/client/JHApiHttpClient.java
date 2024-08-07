package com.jhinno.sdk.openapi.client;


import com.jhinno.sdk.openapi.AuthType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 该接口用户实现发送HTTP请求的方法
 */
public interface JHApiHttpClient {

    /**
     * 发起一个get请求
     *
     * @param url     请求url
     * @param headers 请求头
     * @return 数据流
     * @throws IOException io异常
     */
    InputStream get(String url, Map<String, Object> headers) throws IOException;

    /**
     * 发起一个post请求
     *
     * @param url     请求url
     * @param body    请求体
     * @param headers 请求头
     * @return 数据流
     * @throws IOException io异常
     */
    InputStream post(String url, String body, Map<String, Object> headers) throws IOException;


    /**
     * 发起一个put请求
     *
     * @param url     请求url
     * @param body    请求体
     * @param headers 请求头
     * @return 数据流
     * @throws IOException io异常
     */
    InputStream put(String url, String body, Map<String, Object> headers) throws IOException;

    /**
     * 发起一个GET请求
     *
     * @param url     请求url
     * @param headers 请求头
     * @return 数据流
     * @throws IOException io异常
     */
    InputStream delete(String url, Map<String, Object> headers) throws IOException;


    /**
     * 文件上传
     *
     * @param url     请求url
     * @param keyName 文件key
     * @param is      文件流
     * @param body    请求体
     * @param headers 请求头
     * @return 响应
     * @throws IOException io异常
     */
    InputStream upload(String url, String keyName, String fileName, InputStream is, Map<String, Object> body, Map<String, Object> headers) throws IOException;


    /**
     * 获取Appform服务器当前的时间
     * <p/>
     * {@link AuthType#TOKEN_MODE}模式，并且打开了获取服务器时间的开关需要实现改方法
     *
     * @return 服务器的时间
     */
    default long getAppformServerCurrentTimeMillis(String url) throws IOException {
        return System.currentTimeMillis();
    }

}
