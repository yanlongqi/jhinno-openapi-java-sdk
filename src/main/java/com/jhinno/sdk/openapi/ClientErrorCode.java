package com.jhinno.sdk.openapi;

import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * 客户端错误相关常亮的定义
 *
 * @author yanlongqi
 * @date 2024/1/30 13:25
 */
public class ClientErrorCode {
    /**
     * 未知错误。这意味着错误不是预期的。
     */
    public static final String UNKNOWN = "Unknown";

    /**
     * 未知主机。当引发{@link java.net.UnknownHostException}时返回此错误。
     */
    public static final String UNKNOWN_HOST = "UnknownHost";

    /**
     * 连接超时。
     */
    public static final String CONNECTION_TIMEOUT = "ConnectionTimeout";

    /**
     * Socket超时
     */
    public static final String SOCKET_TIMEOUT = "SocketTimeout";

    /**
     * Socket异常
     */
    public static final String SOCKET_EXCEPTION = "SocketException";

    /**
     * 服务器端拒绝连接。
     */
    public static final String CONNECTION_REFUSED = "ConnectionRefused";

    /**
     * 输入流不可重复读取。
     */
    public static final String NONREPEATABLE_REQUEST = "NonRepeatableRequest";

    /**
     * 读取输入流时线程中断。
     */
    public static final String INPUTSTREAM_READING_ABORTED = "InputStreamReadingAborted";

    /**
     * SSL异常
     */
    public static final String SSL_EXCEPTION = "SslException";


    /**
     * 资源不存在
     */
    public static final String REQUEST_ERROR = "RequestError";
}
