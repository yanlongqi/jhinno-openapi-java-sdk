package com.jhinno.sdk.openapi.test.app;

import com.jhinno.sdk.openapi.api.app.AppStartRequest;
import com.jhinno.sdk.openapi.api.app.AppStartedInfo;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.api.app.SessionInfo;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.junit.Test;

import java.util.*;

/**
 * 会话启动相关单元测试
 *
 * @author yanlongqi
 * @date 2024/2/1 16:47
 */

public class AppApiTest {

    /**
     * 初始化JHApi客户端
     */
    public static final JHApiClient client = JHApiClient.build("https://192.168.0.22/appform");


    /**
     * 获得一个调用应用接口的执行器
     */
    public static final JHAppApiExecution jhAppApiExecution = new JHAppApiExecution(client);

    /**
     * 测测试使用自定义的参数启动jhadmin的Linux桌面
     */
    @Test
    public void testStartApp() {
        AppStartRequest appStartRequest = new AppStartRequest();
        appStartRequest.setStartNew(true);
        AppStartedInfo appStartedInfo = jhAppApiExecution.desktopStart("jhadmin", "linux_desktop", appStartRequest);
        System.out.println(appStartedInfo);
    }

    /**
     * 测试使用默认的参数启动jhadmin的Linux桌面
     */
    @Test
    public void testDefaultParamsStartApp() {
        AppStartedInfo appStartedInfo = jhAppApiExecution.desktopStart("jhadmin", "linux_desktop");
        System.out.println(appStartedInfo);
    }


    /**
     * 测试获取用户jhadmin的会话列表
     */
    @Test
    public void testGetSessionsList() {
        List<SessionInfo> desktopList = jhAppApiExecution.getDesktopList("jhadmin");
        System.out.println(desktopList);
    }

    /**
     * 测试根据参数查询会话列表
     */
    @Test
    public void testGetDesktopsByParams() {
        List<SessionInfo> desktopList = jhAppApiExecution.getDesktopsByParams("jhadmin", null, "Windows桌面");
        System.out.println(desktopList);
    }
}
