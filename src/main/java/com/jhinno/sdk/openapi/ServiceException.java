package com.jhinno.sdk.openapi;

/**
 * <p>
 * 这是表示任何预期或意外的景行API服务器端错误的基本异常类。
 * </p>
 *
 * <p>
 * {@link ServiceException｝是从景行API响应的错误代码转换而来的。
 * 例如，当请求的接口参数错误时，SDK会抛出一个｛@link ServiceException｝或其子类实例，
 * 并带有特定的错误代码，调用者可以用特定的逻辑来处理。
 * </p>
 *
 * <p>
 * 另一方面{@link ClientException｝表示景行API客户端任何异常的类。
 * 一般情况下，{@link ClientException｝要么发生在发送请求之前，要么发生在收到OSS服务器端的响应之后。
 * 例如，如果在尝试发送请求时网络断开，则SDK将抛出{@link ClientException｝实例。
 * </p>
 *
 * <p>
 * 所以一般来说，调用者只需要正确处理{@link ServiceException}，
 * 因为它意味着请求被处理，但由于不同的错误而没有完全完成。
 * 异常中的错误代码是良好的诊断信息。有时这些例外是完全预料到的。
 * </p>
 *
 * @author yanlongqi
 * @date 2024/1/30 11:35
 */
public class ServiceException extends RuntimeException {

    /**
     * 请求错误码
     */
    private int errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 请求的路径
     */
    private String requestPath;

    /**
     * 创建一个默认的实例
     */
    public ServiceException() {
        super();
    }


    /**
     * 创建一个包含错误消息的实例。
     *
     * @param requestPath  请求路径
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     */
    public ServiceException(String requestPath, int errorCode, String errorMessage) {
        this(requestPath, errorCode, errorMessage, null);
    }


    /**
     * 创建一个包含错误消息和异常的实例
     *
     * @param requestPath  请求路径
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     * @param cause        一个异常
     */
    public ServiceException(String requestPath, int errorCode, String errorMessage, Throwable cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
        this.requestPath = requestPath;
        this.errorCode = errorCode;
    }


    /**
     * 获取请求码
     *
     * @return 请求码
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * 获取错信息
     *
     * @return 错误信息
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 获取请求路径
     *
     * @return 请求路径
     */
    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public String getMessage() {
        return String.format("%s\n[请求路径]: %s\n[错误码]: %s", errorMessage, requestPath, errorCode);
    }
}
