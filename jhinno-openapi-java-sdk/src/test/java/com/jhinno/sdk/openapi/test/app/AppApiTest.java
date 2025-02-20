package com.jhinno.sdk.openapi.test.app;

import com.jhinno.sdk.openapi.api.app.*;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 会话启动相关单元测试
 *
 * @author yanlongqi
 * @date 2024/2/1 16:47
 */

public class AppApiTest {

    /**
     * 获得一个调用应用接口的执行器
     */
    public static final JHAppApiExecution jhAppApiExecution = JHClientConfig.API_EXECUTRON_MANAGE
            .getApiExecution(JHAppApiExecution.class);

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

    /**
     * 测试根据会话列表查询会话列表
     */
    @Test
    public void testGetDesktopsById() {
        List<SessionInfo> desktopList = jhAppApiExecution.getDesktopsById("jhadmin",
                Arrays.asList("7649", "7637", "123"));
        System.out.println(desktopList);
    }

    /**
     * 测试根据会话名称查询会话列表
     */
    @Test
    public void testGetDesktopsByName() {
        List<SessionInfo> desktopList = jhAppApiExecution.getDesktopsByName("jhadmin", "Windows桌面");
        System.out.println(desktopList);
    }

    /**
     * 测试会话共享
     */
    @Test
    public void testShareDesktop() {
        jhAppApiExecution.shareDesktop("jhadmin", "7649", null, null, null);
    }

    /**
     * 测试取消会话共享
     */
    @Test
    public void testCancelShare() {
        jhAppApiExecution.cancelShare("jhadmin", "7649");
    }

    /**
     * 测试传递会话操作权
     */
    @Test
    public void testTransferOperatorRight() {
        jhAppApiExecution.transferOperatorRight("jhadmin", "7649", "123");
    }

    /**
     * 测试链接会话
     */
    @Test
    public void testConnectJhapp() {
        AppStartedInfo appStartedInfo = jhAppApiExecution.connectJhapp("lqyan", "7666");
        System.out.println(appStartedInfo);
    }

    /**
     * 测试断开会话的连接
     */
    @Test
    public void testDisconnectSessionInfo() {
        jhAppApiExecution.disconnectSessionInfo("jhadmin", "7666");
    }

    /**
     * 测试批量断开会话
     */
    @Test
    public void testDisconnectSessionByIds() {
        jhAppApiExecution.disconnectSessionByIds("jhadmin", Arrays.asList("123", "456"));
    }

    /**
     * 测试注销会话
     */
    @Test
    public void testDestroySession() {
        jhAppApiExecution.destroySession("jhadmin", "63");
    }

    /**
     * 测试批量注销会话
     */
    @Test
    public void testDestroySessionByIds() {
        jhAppApiExecution.destroySessionByIds("jhadmin", Arrays.asList("123", "456"));
    }

    /**
     * 测试查询用户的应用列表
     */
    @Test
    public void testGetAppList() {
        List<AppInfo> appList = jhAppApiExecution.getAppList("jhadmin");
        System.out.println(appList);
    }

    /**
     * 测试获取应用链接
     */
    @Test
    public void testGetAppUrl() {
        System.out.println(jhAppApiExecution.getAppUrl("jhadmin", "myjobmana"));
    }

    /**
     * 测试根据文件后缀取应用列表
     */
    @Test
    public void testGetAppInfoSuffixList() {
        System.out.println(jhAppApiExecution.getAppInfoSuffixList("test", ".sh"));
    }

    /**
     * 测试根据用途获取应用列表
     */
    @Test
    public void testGetUseLabelList() {
        System.out.println(jhAppApiExecution.getUseLabelList("jhadmin"));
    }
}
