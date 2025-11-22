package com.jhinno.sdk.openapi.example.test.extend;

import com.jhinno.sdk.openapi.api.app.AppStartedInfo;
import com.jhinno.sdk.openapi.example.api.extend.FileSystemType;
import com.jhinno.sdk.openapi.example.api.extend.JHFileApiExtendExecution;
import com.jhinno.sdk.openapi.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.jhinno.sdk.openapi.api.app.JHAppApiExecution;

@SpringBootTest
public class JHFileApiExtendTest {

    @Autowired
    private JHFileApiExtendExecution jhFileApiExtendExecution;

    @Autowired
    private JHAppApiExecution jhAppApiExecution;

    @Test
    void testGetFileHomeEnvPath() {
        System.out.println(jhFileApiExtendExecution.getFileHomeEnvPath("jhadmin", FileSystemType.SYSTEM_TYPE_LINUX));
    }


    @Test
    void testStartApp() {
        AppStartedInfo linuxDesktop = jhAppApiExecution.desktopStart(null, "linux_desktop");
        System.out.println(JsonUtil.objectToString(linuxDesktop));
    }
}
