package com.jhinno.sdk.openapi.api.app;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 启动会话的请求参数
 *
 * @author yanlongqi
 * @date 2024/2/1 16:31
 */
@Data
public class AppStartRequest {


    /**
     * 是否启动一个新的会话（非必填：默认：false）
     * <p>
     * 如果是true则会启动一个新的会话，否则复用已经启动好的会话
     */
    private boolean startNew;


    /**
     * 工作路径（会话启动的路径）
     */
    private String cwd;

    /**
     * 工作文件
     */
    private String workFile;

    /**
     * 指标
     */
    private String metrics;


    /**
     * <p>
     * 请求获取到的JHClient协议链接的过期目标时间。（必填）
     * </p>
     * <p>
     * 注意：
     * </p>
     * 1. 该时间戳是获取到的加密串的过期时间，需要传入一个未来的时间
     * （如：当前是2024-02-01 17:53:12，假设加密串5分钟之后过期，则该参数为：20240101175812）
     * </p>
     * <p>
     * 2. 该时间错由景行的JHClient进行验证，并不是在服务端进行验证，
     * 所以为了防止客户端的时间和服务器的时间不一致而导会话不能启动，
     * 因此此字段尽量在浏览器的生成
     * </p>
     */
    private String currentTimestamp;

    /**
     * 会话类型
     */
    private String sessionType;

    private String sessionOwner;

    /**
     * 启动参数
     * <p>
     * 例如启动一个demo.exe 该demo.exe需要传入用户名，在命令行的的操作为 demo.exe -u admin
     * </p>
     *
     * <p>
     * 此处传入的参数则为："-u admin"
     * </p>
     */
    private String param;

    private String loginUser;

    private String sessionId;

    private String remoteAddr;

    private String remotePort;

    private boolean isInMeetingRoom;

    private String currentMeetingId;

    private boolean jhClientInstalled;

}
