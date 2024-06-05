package com.jhinno.sdk.openapi;

import lombok.Getter;

/**
 * 这个异常标识传入的SDK方法的参数错误
 * <p>
 * 通常情况下{@link ArgsException｝开发者额外处理它，因为它通常出现在开发的时候，
 * <p>
 * 例如： 调用重命名文件的接口，要求源文件路径和新的文件名是必填的参数，
 * 如果开发者在调用的过程中没填装个参数，则程序会抛出 {@link ArgsException｝
 * <p>
 *
 * @author yanlongqi
 * @date 2024/1/31 10:36
 */
@Getter
public class ArgsException extends RuntimeException {

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 构建一个没有任何参数的异常实例
     */
    public ArgsException() {
        super();
    }

    /**
     * 创建具有异常的实例
     *
     * @param cause 一个异常
     */
    public ArgsException(Throwable cause) {
        this(null, cause);
    }


    /**
     * 创建一个包含错误消息和异常的实例
     *
     * @param errorMessage 错误信息
     * @param cause        一个异常
     */
    public ArgsException(String errorMessage, Throwable cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
    }


    /**
     * 创建一个包含错误消息的实例。
     *
     * @param errorMessage 错误信息
     */
    public ArgsException(String errorMessage) {
        this(errorMessage, null);
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

}
