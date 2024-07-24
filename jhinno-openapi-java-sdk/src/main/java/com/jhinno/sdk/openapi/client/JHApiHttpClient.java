package com.jhinno.sdk.openapi.client;


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


}
