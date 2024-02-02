package com.jhinno.sdk.openapi.test.app;

import com.jhinno.sdk.openapi.api.app.AppStartRequest;
import com.jhinno.sdk.openapi.api.app.AppStartedInfo;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.junit.Test;

/**
 * 会话启动相关单元测试
 *
 * @author yanlongqi
 * @date 2024/2/1 16:47
 */

public class AppApiTest {

   public static final JHApiClient client = JHApiClient.build("https://192.168.87.25/appform");

    /**
     * 测试获取"jhadmin"的Linux桌面会话的JHClient链接
     */
    @Test
    public void testStartApp() {
        JHAppApiExecution jhAppApiExecution = new JHAppApiExecution(client);
        AppStartedInfo appStartedInfo = jhAppApiExecution.desktopStart("jhadmin", "linux_desktop", new AppStartRequest());
        System.out.println(appStartedInfo);
    }


}
