package com.jhinno.sdk.openapi;

/**
 * <p>
 * 这个异常是客户端访问景行API时抛出的异常。
 * </p>
 *
 * <p>
 * {@link ClientException｝表示景行API客户端任何异常的类。
 * 一般情况下，{@link ClientException｝要么发生在发送请求之前，要么发生在收到OSS服务器端的响应之后。
 * 例如：如果在尝试发送请求时网络断开，则SDK将抛出{@link ClientException｝实例。
 * </p>
 *
 * <p>
 * {@link ServiceException｝是从景行API响应的错误代码转换而来的。
 * 例如，当请求的接口参数错误时，SDK会抛出一个｛@link ServiceException｝或其子类实例，
 * 并带有特定的错误代码，调用者可以用特定的逻辑来处理。
 * </p>
 *
 * @author yanlongqi
 * @date 2024/1/30 11:27
 */
public class ClientException extends RuntimeException {

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 错误编号
     */
    private String errorCode;


    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 创建一个默认的实例
     */
    public ClientException() {
        super();
    }

    /**
     * 创建一个包含错误消息的实例。
     *
     * @param errorMessage 错误信息
     */
    public ClientException(String errorMessage) {
        this(errorMessage, null);
    }

    /**
     * 创建具有异常的实例
     *
     * @param cause 一个异常
     */
    public ClientException(Throwable cause) {
        this(null, cause);
    }

    /**
     * 创建一个包含错误消息和异常的实例
     *
     * @param errorMessage 错误信息
     * @param cause        一个异常
     */
    public ClientException(String errorMessage, Throwable cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
        this.errorCode = ClientErrorCode.UNKNOWN;
        this.requestId = ClientErrorCode.UNKNOWN;
    }

    /**
     * 使用错误消息、错误代码、请求Id创建实例
     *
     * @param errorMessage 错误信息
     * @param errorCode    错误编码
     * @param requestId    请求id
     */
    public ClientException(String errorMessage, String errorCode, String requestId) {
        this(errorMessage, errorCode, requestId, null);
    }

    /**
     * 创建包含错误消息、错误代码、请求Id和异常的实例。
     *
     * @param errorMessage 错误信息
     * @param errorCode    错误编码
     * @param requestId    请求id
     * @param cause        一个异常
     */
    public ClientException(String errorMessage, String errorCode, String requestId, Throwable cause) {
        this(errorMessage, cause);
        this.errorCode = errorCode;
        this.requestId = requestId;
    }

    /**
     * 创建包含错误消息、错误代码、异常的实例。
     *
     * @param errorMessage 错误信息
     * @param errorCode    错误编码
     * @param cause        一个异常
     */
    public ClientException(String errorMessage, String errorCode, Throwable cause) {
        this(errorMessage, cause);
        this.errorCode = errorCode;
    }

    /**
     * 获取请求id。
     *
     * @return 请求Id
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * 获取错误代码。
     *
     * @return 错误代码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 获取错误消息。
     *
     * @return 字符串中的错误消息
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getMessage() {
        return String.format(
                "%s\n[ErrorCode]: %s\n[RequestId]:%s",
                getErrorMessage(),
                errorCode != null ? errorCode : "",
                requestId != null ? requestId : ""
        );
    }
}
