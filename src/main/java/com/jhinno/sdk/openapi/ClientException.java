package com.jhinno.sdk.openapi;

import lombok.Getter;

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
@Getter
public class ClientException extends RuntimeException {


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
        this(errorMessage, ClientErrorCode.UNKNOWN);
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
    }

    /**
     * 使用错误消息、错误代码、请求Id创建实例
     *
     * @param errorMessage 错误信息
     * @param errorCode    错误编码
     */
    public ClientException(String errorMessage, String errorCode) {
        this(errorMessage, errorCode, null);
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

    @Override
    public String getMessage() {
        return String.format("%s\n[ErrorCode]: %s", getErrorMessage(), errorCode != null ? errorCode : "");
    }
}
