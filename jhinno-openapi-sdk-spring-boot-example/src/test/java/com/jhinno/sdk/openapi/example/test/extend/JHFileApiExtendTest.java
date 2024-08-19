package com.jhinno.sdk.openapi.example.test.extend;

import com.jhinno.sdk.openapi.example.api.extend.FileSystemType;
import com.jhinno.sdk.openapi.example.api.extend.JHFileApiExtendExecution;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JHFileApiExtendTest {

    @Autowired
    private JHFileApiExtendExecution jhFileApiExtendExecution;

    @Test
    void testGetFileHomeEnvPath() {
        System.out.println(jhFileApiExtendExecution.getFileHomeEnvPath("jhadmin", FileSystemType.SYSTEM_TYPE_LINUX));
    }
}
