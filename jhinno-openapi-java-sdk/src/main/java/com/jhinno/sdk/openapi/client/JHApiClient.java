package com.jhinno.sdk.openapi.client;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.ClientErrorCode;
import com.jhinno.sdk.openapi.ClientException;
import com.jhinno.sdk.openapi.api.auth.AuthPathConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * 提供请求的工具
 *
 * @author yanlongqi
 * @date 2024/1/29 10:31
 */
public class JHApiClient {

    /**
     * &#x57FA;&#x7840;&#x7684;&#x8BF7;&#x6C42;URL&#x5730;&#x5740;
     * <p>
     * &#x5982;&#xFF1A;https://192.168.3.12/appform
     * </p>
     */
    private final String baseUrl;


    /**
     * 对象转换器
     */
    private ObjectMapper mapper;

    /**
     * 初始化一个JHApiClient的实例，可使用自定义的客户端
     *
     * @param baseUrl             景行接口服务的基础地址
     * @param closeableHttpClient 可关闭的HTTP客户端
     */
    private JHApiClient(CloseableHttpClient closeableHttpClient, String baseUrl, int socketTimeout, int connectTimeout, int connectRequestTimeout) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        this.closeableHttpClient = closeableHttpClient;
        this.requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectRequestTimeout)
                .build();
        mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
    }


    /**
     * HTTP的连接客户端
     */
    private final CloseableHttpClient closeableHttpClient;


    /**
     * 每次发送请求的配置，如果该配置未进行设置则走 {@link DefaultHttpClientConfig｝ 中的默认配置
     */
    private RequestConfig requestConfig;


    /**
     * 通过最大连接数和服务每次能并行接收的请求数量构建一个JHApiClient实例
     *
     * @param maxTotal   最大连接数
     * @param maxPerRout 服务每次能并行接收的请求数量
     * @param baseUrl    景行接口服务的基础地址 如: http://192.168.3.4/appform
     * @return JHApiClient的实例
     */
    public static JHApiClient build(String baseUrl, int maxTotal, int maxPerRout, int socketTimeout, int connectTimeout, int connectRequestTimeout) {
        return build(createHttpClients(maxTotal, maxPerRout), baseUrl, socketTimeout, connectTimeout, connectRequestTimeout);
    }

    /**
     * 通过{@link DefaultHttpClientConfig｝默认配置的最大连接数和服务每次能并行接收的请求数量构建一个JHApiClient实例
     * <p>
     *
     * @param baseUrl 景行接口服务的基础地址，如: http://192.168.3.4/appform
     * @return JHApiClient的实例
     */
    public static JHApiClient build(String baseUrl) {
        CloseableHttpClient client = createHttpClients(DefaultHttpClientConfig.MAX_TOTAL, DefaultHttpClientConfig.MAX_PER_ROUT);
        return build(client, baseUrl, DefaultHttpClientConfig.SOCKET_TIMEOUT, DefaultHttpClientConfig.CONNECT_TIMEOUT, DefaultHttpClientConfig.CONNECTION_REQUEST_TIMEOUT);
    }

    /**
     * 通过外部传入的{@link CloseableHttpClient｝构建一个请求客户端.
     * <p>
     *
     * @param httpClient 请求连接池
     * @param baseUrl    景行接口服务的基础地址
     * @return JHApiClient的实例
     */
    public static JHApiClient build(CloseableHttpClient httpClient, String baseUrl, int socketTimeout, int connectTimeout, int connectRequestTimeout) {
        return new JHApiClient(httpClient, baseUrl, socketTimeout, connectTimeout, connectRequestTimeout);
    }


    /**
     * 初始化一个HTTP客户端实例
     *
     * @param maxTotal    设置最大连接数
     * @param maxPerRoute 服务每次能并行接收的请求数量
     * @return 返回一个可关闭的HTTP客户端示例
     */
    public static CloseableHttpClient createHttpClients(int maxTotal, int maxPerRoute) {
        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, (x509Certificates, s) -> true);
            SSLConnectionSocketFactory sslref = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", new PlainConnectionSocketFactory()).register("https", sslref).build();
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(maxTotal);
            cm.setDefaultMaxPerRoute(maxPerRoute);
            return HttpClients.custom().setSSLSocketFactory(sslref).setConnectionManager(cm).setConnectionManagerShared(true).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new ClientException(e.getMessage(), ClientErrorCode.SSL_EXCEPTION, e);
        }
    }

    /**
     * 关闭JHApiClient实例 (释放所有资源)
     * 调用JHApiClient实例的shutdown() 后，JHApiClient实例不可用。
     * 如果客户端不存在则不进行任何操作
     */
    public void shutdown() {
        try {
            if (closeableHttpClient != null) {
                closeableHttpClient.close();
            }
        } catch (IOException e) {
            throw new ClientException("关闭JHApiClient失败", e);
        }
    }


    /**
     * 设置自定义的jackson序列化配置
     *
     * @param mapper 序列化器
     */
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * <p>
     * 设置一个HTTP请求的配置
     * </p>
     *
     * <p>
     * {@link JHApiClient｝ 默认只配置了 socket连接超时的时间(socketTimeout) 、连接超时的时间(connectTimeout)、
     * 请求超时的时间(connectionRequestTimeout)这三项，其默认配置在{@link DefaultHttpClientConfig｝ 中。
     * 如果你要自定义你自己的配置，则可以通过{@link HttpClients｝构建自己的RequestConfig来请求接口
     * </p>
     *
     * @param requestConfig HTTP请求的配置
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }

    /**
     * 原始发送请求
     *
     * @param httpRequest 请求体
     * @param headers     请求头
     * @return 响应体
     */
    public HttpEntity request(HttpRequestBase httpRequest, Map<String, String> headers) {
        if (requestConfig == null) {
            throw new ClientException("请配置requestConfig");
        }

        if (httpRequest == null) {
            throw new ClientException("httpRequest不能为空");
        }
        httpRequest.setConfig(requestConfig);

        // 添加请求头
        if (CollectionUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }

        try {
            HttpResponse response = closeableHttpClient.execute(httpRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                httpRequest.releaseConnection();
                throw new ClientException("发送HTTP请求失败，请求码：" + statusCode, ClientErrorCode.REQUEST_ERROR);
            }
            return response.getEntity();
        } catch (IOException e) {
            throw new ClientException(e.getMessage());
        }

    }


    public Long requestTimeMillis() {
        HttpGet httpGet = new HttpGet(getUrl(AuthPathConstant.PING));
        httpGet.setConfig(requestConfig);
        try {
            HttpResponse response = closeableHttpClient.execute(httpGet);
            Header header = response.getFirstHeader("Date");
            if (header == null) {
                return null;
            }
            String value = header.getValue();
            if (StringUtils.isBlank(value)) {
                return null;
            }
            return DatePattern.HTTP_DATETIME_FORMAT.parse(value).getTime();
        } catch (IOException e) {
            throw new ClientException(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 发送请求
     *
     * @param httpRequest HttpRequestBase
     * @param headers     请求头
     * @param type        返回数据类型
     * @param <T>         返回的数据类型
     * @return 返回的接口数据
     */
    public <T> T request(HttpRequestBase httpRequest, Map<String, String> headers, TypeReference<T> type) {
        try {
            InputStream content = request(httpRequest, headers).getContent();
            return mapper.readValue(content, type);
        } catch (IOException e) {
            throw new ClientException(e.getMessage());
        }

    }


    /**
     * 发送一个get请求
     *
     * @param path    接口地址
     * @param headers 请求头
     * @param type    请求返回值类型
     * @param <T>     t
     * @return t
     */
    public <T> T get(String path, Map<String, String> headers, TypeReference<T> type) {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("url不能为空");
        }
        HttpGet httpGet = new HttpGet(getUrl(path));
        return request(httpGet, headers, type);
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
                    SimpleDateFormat format = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
                    urlBuilder.append(URLEncoder.encode(format.format(value), "utf-8"));
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
    public <T, K> T post(String path, K body, Map<String, String> headers, TypeReference<T> type) {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("path不能为空");
        }
        HttpPost httpPost = new HttpPost(getUrl(path));
        try {
            if (body != null) {
                String bodyStr = mapper.writeValueAsString(body);
                httpPost.setEntity(new StringEntity(bodyStr, "utf-8"));
            }
            return request(httpPost, headers, type);
        } catch (Exception e) {
            throw new ClientException(e.getMessage());
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
    public <T, K> T put(String path, K body, Map<String, String> headers, TypeReference<T> type) {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("url不能为空");
        }
        HttpPut httpPost = new HttpPut(getUrl(path));
        try {
            if (body != null) {
                String bodyStr = mapper.writeValueAsString(body);
                httpPost.setEntity(new StringEntity(bodyStr, "utf-8"));
            }
            return request(httpPost, headers, type);
        } catch (Exception e) {
            throw new ClientException(e.getMessage());
        }
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
    public <T> T delete(String path, Map<String, String> headers, TypeReference<T> type) {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("url不能为空");
        }
        HttpDelete httpDelete = new HttpDelete(getUrl(path));
        return request(httpDelete, headers, type);
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
}
