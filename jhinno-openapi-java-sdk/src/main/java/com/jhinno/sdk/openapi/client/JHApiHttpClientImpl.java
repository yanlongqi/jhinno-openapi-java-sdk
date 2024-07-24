package com.jhinno.sdk.openapi.client;

import com.jhinno.sdk.openapi.ClientErrorCode;
import com.jhinno.sdk.openapi.ClientException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Data
@NoArgsConstructor
public class JHApiHttpClientImpl implements JHApiHttpClient {


    /**
     * HTTP的连接客户端
     */
    private CloseableHttpClient closeableHttpClient;


    /**
     * <p>
     * 每次发送请求的配置，如果该配置未进行设置则走 {@link DefaultHttpClientConfig｝ 中的默认配置
     * </p>
     *
     * <p>
     * {@link JHApiClient｝ 默认只配置了 socket连接超时的时间(socketTimeout) 、连接超时的时间(connectTimeout)、
     * 请求超时的时间(connectionRequestTimeout)这三项，其默认配置在{@link DefaultHttpClientConfig｝ 中。
     * 如果你要自定义你自己的配置，则可以通过{@link HttpClients ｝构建自己的RequestConfig来请求接口
     * </p>
     *
     * @param requestConfig HTTP请求的配置
     */
    private RequestConfig requestConfig;


    /**
     * Socket连接超时的时间(单位：毫秒，默认：{@link DefaultHttpClientConfig#SOCKET_TIMEOUT})
     */
    private int socketTimeout = DefaultHttpClientConfig.SOCKET_TIMEOUT;

    /**
     * 连接超时的时间(单位：毫秒，默认：{@link DefaultHttpClientConfig#CONNECT_TIMEOUT})
     */
    private int connectTimeout = DefaultHttpClientConfig.CONNECT_TIMEOUT;

    /**
     * 默认请求超时的时间(单位：毫秒，默认：{@link DefaultHttpClientConfig#CONNECTION_REQUEST_TIMEOUT})
     */
    private int connectRequestTimeout = DefaultHttpClientConfig.CONNECTION_REQUEST_TIMEOUT;

    /**
     * 设置最大连接数(默认：{@link DefaultHttpClientConfig#MAX_TOTAL})
     */
    private int maxTotal = DefaultHttpClientConfig.MAX_TOTAL;

    /**
     * 服务每次能并行接收的请求数量(默认：{@link DefaultHttpClientConfig#MAX_PER_ROUTE})
     */
    private int maxPerRoute = DefaultHttpClientConfig.MAX_PER_ROUTE;

    /**
     * 初始化一个HTTP客户端实例
     *
     * @return 返回一个可关闭的HTTP客户端示例
     */
    public void createHttpClients() {
        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, (x509Certificates, s) -> true);
            SSLConnectionSocketFactory sslref = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", new PlainConnectionSocketFactory()).register("https", sslref).build();
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(maxTotal);
            cm.setDefaultMaxPerRoute(maxPerRoute);
            closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslref).setConnectionManager(cm).setConnectionManagerShared(true).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new ClientException(e.getMessage(), ClientErrorCode.SSL_EXCEPTION, e);
        }
    }

    /**
     * 初始化客户端
     */
    public void init() {
        this.requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectRequestTimeout)
                .build();
    }


    /**
     * 原始发送请求
     *
     * @param httpRequest 请求体
     * @param headers     请求头
     * @return 响应体
     */
    public HttpEntity request(HttpRequestBase httpRequest, Map<String, Object> headers) {
        if (requestConfig == null) {
            throw new ClientException("请配置requestConfig");
        }

        if (httpRequest == null) {
            throw new ClientException("httpRequest不能为空");
        }
        httpRequest.setConfig(requestConfig);
        // 添加请求头
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    continue;
                }
                httpRequest.setHeader(key, value.toString());
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
            httpRequest.releaseConnection();
            throw new ClientException(e.getMessage());
        }
    }


    @Override
    public InputStream get(String url, Map<String, Object> headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return request(httpGet, headers).getContent();
    }

    @Override
    public InputStream post(String url, String body, Map<String, Object> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotBlank(body)) {
            httpPost.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
        }
        return request(httpPost, headers).getContent();
    }

    @Override
    public InputStream put(String url, String body, Map<String, Object> headers) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        if (StringUtils.isNotBlank(body)) {
            httpPut.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
        }
        return request(httpPut, headers).getContent();
    }

    @Override
    public InputStream delete(String url, Map<String, Object> headers) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        return request(httpDelete, headers).getContent();
    }

    @Override
    public InputStream upload(String url, String keyName, String fileName, InputStream is, Map<String, Object> body, Map<String, Object> headers) throws IOException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(StandardCharsets.UTF_8);
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addBinaryBody(keyName, is, ContentType.MULTIPART_FORM_DATA, fileName);
        if (body != null && !body.isEmpty()) {
            for (Map.Entry<String, Object> entry : body.entrySet()) {
                builder.addTextBody(entry.getKey(), entry.getValue().toString());
            }
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(builder.build());
        return request(httpPost, headers).getContent();
    }
}
